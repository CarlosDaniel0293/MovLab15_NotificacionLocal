package com.miempresa.lab15_notificacionlocal

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import android.Manifest

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun MainScreen(){
    val contexto = LocalContext.current
    val notificationService = NotificationService(contexto)
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                val permissionState = rememberPermissionState(
                    permission = Manifest.permission.POST_NOTIFICATIONS
                )
                if (!permissionState.status.isGranted){
                    OutlinedButton(onClick = {permissionState.launchPermissionRequest()}) {
                        Text(
                            text = "Permitir notificación",
                            fontSize = 22.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = {notificationService.showNotification()}) {
                Text(
                    text = "Show Notification",
                    fontSize = 22.sp
                )
            }
        }
    }
}
