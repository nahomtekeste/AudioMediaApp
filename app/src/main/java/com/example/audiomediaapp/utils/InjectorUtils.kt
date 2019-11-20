package com.example.audiomediaapp.utils

import android.app.Application
import android.content.ComponentName
import android.content.Context
import com.example.audiomediaapp.viewmodels.MainActivityViewModel
import com.example.audiomediaapp.viewmodels.MediaItemFragmentViewModel
import com.example.audiomediaapp.viewmodels.NowPlayingFragmentViewModel

/**

 * Static methods used to inject classes needed for various Activities and Fragments.

 */

object InjectorUtils {
    private fun provideMediaSessionConnection(context: Context): MediaSessionConnection {
        return MediaSessionConnection.getInstance(context,
            ComponentName(context, MusicService::class.java)
        )
    }
    fun provideMainActivityViewModel(context: Context): MainActivityViewModel.Factory {
        val applicationContext = context.applicationContext
        val mediaSessionConnection = provideMediaSessionConnection(applicationContext)
        return MainActivityViewModel.Factory(mediaSessionConnection)
    }


    fun provideMediaItemFragmentViewModel(context: Context, mediaId: String)
            : MediaItemFragmentViewModel.Factory {
        val applicationContext = context.applicationContext
        val mediaSessionConnection = provideMediaSessionConnection(applicationContext)
        return MediaItemFragmentViewModel.Factory(mediaId, mediaSessionConnection)
    }
    fun provideNowPlayingFragmentViewModel(context: Context)
            : NowPlayingFragmentViewModel.Factory {
        val applicationContext = context.applicationContext
        val mediaSessionConnection = provideMediaSessionConnection(applicationContext)
        return NowPlayingFragmentViewModel.Factory(
            applicationContext as Application, mediaSessionConnection)
    }
}