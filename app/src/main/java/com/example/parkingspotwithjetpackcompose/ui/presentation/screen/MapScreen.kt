package com.example.parkingspotwithjetpackcompose.ui.presentation.screen
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.example.parkingspotwithjetpackcompose.ui.presentation.MapsViewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen(
    viewModel: MapsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MapEvent.ToggleFalloutMap)
            }, contentColor = Color.Yellow){
                Icon(imageVector = if(viewModel.state.isFalloutView){
                    Icons.Default.ToggleOff
                } else Icons.Default.ToggleOn, contentDescription = "Fallout Map")
            }
        }
    ) {
        val markerPosition = LatLng(36.0,42.0)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(markerPosition, 1f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            onMapLongClick = {
                viewModel.onEvent(MapEvent.onMapLongClick(it))
                //cameraPositionState.move(CameraUpdateFactory.zoomIn())
            }
        ){
            viewModel.state.parkingSpots.forEach { spot ->
                Marker(
                    position = LatLng(spot.latitude, spot.longitude),
                    title = "Parking spot, Latitude = ${String.format("%.2f", spot.latitude)}" +
                            " Longitude = ${String.format("%.2f", spot.longitude)}",
                    snippet = "Long click to delete",
                    onInfoWindowLongClick = {
                        viewModel.onEvent(MapEvent.onInfoWindowLongClick(spot))
                    },
                    onClick = { marker ->
                        marker.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                )
            }
        }
    }
}

/* Use later */
private fun bitmapDescriptorFromVector(
    context: Context,
    @DrawableRes vectorDrawableResourceId: Int
): BitmapDescriptor? {
    val background = ContextCompat.getDrawable(context, vectorDrawableResourceId)
    background!!.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
    val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
    vectorDrawable!!.setBounds(
        40,
        20,
        vectorDrawable.intrinsicWidth + 40,
        vectorDrawable.intrinsicHeight + 20
    )
    val bitmap = Bitmap.createBitmap(
        background.intrinsicWidth,
        background.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    background.draw(canvas)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

