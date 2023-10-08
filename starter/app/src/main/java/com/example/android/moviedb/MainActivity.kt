package com.example.android.moviedb

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.android.moviedb.databinding.ActivityMainBinding
import com.example.android.moviedb.extensions.gone
import com.example.android.moviedb.extensions.hide
import com.example.android.moviedb.extensions.show

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        sendNotification(0)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        navController?.run {
            addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.itemHotPage -> {
                        binding.layoutBanner.show()
                        binding.bottomNavigationView.show()
                    }

                    R.id.itemFavoritePage -> {
                        binding.layoutBanner.show()
                        binding.bottomNavigationView.show()
                    }

                    else -> {
                        binding.layoutBanner.gone()
                        binding.bottomNavigationView.gone()
                    }
                }
            }
            binding.bottomNavigationView.setupWithNavController(this)
        }
        setContentView(binding.root)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "description"

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(
        notificationId: Int
    ) {
        val intent = Intent(this, MainActivity::class.java)

        val pendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.bg_logo)
            .setContentTitle("Project: The Cinema")
            .setContentText("Welcome to the Cinema")
            .addAction(R.drawable.bg_logo, "Click here to go to detail screen", pendingIntent)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(
            notificationId,
            builder.build()
        )
    }

    companion object {
        private const val CHANNEL_ID = "channelId"
        private const val CHANNEL_NAME = "channelName"
    }
}
