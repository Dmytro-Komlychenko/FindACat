package com.example.presentation.di

import com.example.presentation.ui.activities.main.MainActivity
import com.example.presentation.ui.fragments.game.menu.GameMenuFragment
import com.example.presentation.ui.fragments.game.play.CatchCatFragment
import com.example.presentation.ui.fragments.game.results.ResultsFragment
import com.example.presentation.ui.fragments.game.shop.ShopFragment
import dagger.Component

@Component(modules = [PresentationModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(gameMenuFragment: GameMenuFragment)
    fun inject(catchCatFragment: CatchCatFragment)
    fun inject(resultsFragment: ResultsFragment)
    fun inject(shopFragment: ShopFragment)
}