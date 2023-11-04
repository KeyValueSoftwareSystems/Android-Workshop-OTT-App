package com.keyvalue.workshop.ottworkshopapp.activity


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.keyvalue.workshop.ottworkshopapp.TAG
import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetails

class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Name from Main Activity-> " + intent.getStringExtra("Name"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.i(
                TAG,
                "MovieDetails from Main Activity-> " + intent.getSerializableExtra(
                    "MovieDetail",
                    MovieDetails::class.java
                )
            )
        } else {
            Log.i(
                TAG,
                "MovieDetails from Main Activity-> " + intent.getSerializableExtra("MovieDetail")
            )

        }

    }

}