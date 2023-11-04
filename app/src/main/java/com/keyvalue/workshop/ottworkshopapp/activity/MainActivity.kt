package com.keyvalue.workshop.ottworkshopapp.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat.Action
import androidx.core.content.ContextCompat
import com.keyvalue.workshop.ottworkshopapp.Choice
import com.keyvalue.workshop.ottworkshopapp.Choice.*
import com.keyvalue.workshop.ottworkshopapp.TAG
import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetails
import com.keyvalue.workshop.ottworkshopapp.ui.theme.OTTWorkshopAppTheme

class MainActivity : ComponentActivity() {

    private var choice: Choice? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        choice = INTENT_EMAIL
        val action: String? = intent?.action
        val data: Uri? = intent?.data
        Log.i(TAG, "Action-> " + action + "Data-> " + data)
        setContent {
            Surface {
                val launcher = rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) { isGranted ->
                    if (isGranted) {
                        // Open camera
                        when (choice) {
                            INTENT_YOUTUBE -> {
                                Intent(Intent.ACTION_MAIN).let {
                                    it.`package` = "com.google.android.youtube"
                                    startActivity(it)
                                }
                            }

                            INTENT_EMAIL -> {
                                val intent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_EMAIL, arrayOf("test@test.com"))
                                    putExtra(Intent.EXTRA_SUBJECT, "This is my subject")
                                    putExtra(Intent.EXTRA_TEXT, "This is the content of my email")
                                }
                                if (intent.resolveActivity(packageManager) != null)
                                    startActivity(intent)
                            }

                            INTENT_DETAILS_ACTIVITY -> {
                                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                                intent.putExtra("Name", "John Doe")
                                intent.putExtra(
                                    "MovieDetail", MovieDetails(
                                        0, isKidsMovie = false,
                                        name = "Leo"
                                    )
                                )
                                startActivity(intent)
                            }

                            null -> {
                                Toast.makeText(this@MainActivity, "Not a choice", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }


                    } else {
                        // Show dialog
                    }
                }
                LaunchedEffect(key1 = 0, block ={
                    checkAndRequestCameraPermission(this@MainActivity,android.Manifest.permission.CAMERA,launcher)

                } )
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableArrayExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableArrayExtra(Intent.EXTRA_STREAM)
        }
        Log.i(TAG, "Image URI-> $uri")
    }

    private fun checkAndRequestCameraPermission(
        context: Context,
        permission: String,
        launcher: ManagedActivityResultLauncher<String, Boolean>
    ) {
        val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            // Open camera because permission is already granted
        } else {
            // Request a permission
            launcher.launch(permission)
        }
    }

}
