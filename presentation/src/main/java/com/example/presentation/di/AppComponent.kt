package com.example.presentation.di

import com.example.presentation.ui.activities.main.MainActivity
import dagger.Component

@Component(modules = [PresentationModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}