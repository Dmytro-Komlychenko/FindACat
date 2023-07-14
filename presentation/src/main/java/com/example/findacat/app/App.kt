package com.example.findacat.app

import android.app.Application
import com.example.findacat.di.AppComponent
import com.example.findacat.di.DaggerAppComponent
import com.example.findacat.di.PresentationModule

class App: Application() {

     lateinit var appComponent: AppComponent

     override fun onCreate() {
         super.onCreate()
         appComponent = DaggerAppComponent.builder().presentationModule(PresentationModule(context = this)).build()
     }
}