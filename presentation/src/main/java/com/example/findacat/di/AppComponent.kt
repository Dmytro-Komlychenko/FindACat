package com.example.findacat.di

import com.example.findacat.ui.activities.MainActivity
import com.example.findacat.ui.fragments.splash.SplashFragment
import com.example.findacat.ui.fragments.game.menu.GameMenuFragment
import com.example.findacat.ui.fragments.game.play.CatchCatFragment
import com.example.findacat.ui.fragments.game.results.ResultsFragment
import com.example.findacat.ui.fragments.game.shop.ShopFragment
import dagger.Component

@Component(modules = [PresentationModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(splashFragment: SplashFragment)
    fun inject(gameMenuFragment: GameMenuFragment)
    fun inject(resultsFragment: ResultsFragment)
    fun inject(shopFragment: ShopFragment)
    fun inject(catchCatFragment: CatchCatFragment)
    fun inject(mainActivity: MainActivity)
}