package com.example.domain.models

enum class AppActionE(val id: Int) {
    GAME(0),
    WEB(1),
}

data class AppAction(val value: AppActionE)