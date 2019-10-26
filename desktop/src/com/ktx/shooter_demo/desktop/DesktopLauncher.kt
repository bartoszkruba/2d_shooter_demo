package com.ktx.shooter_demo.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.ktx.shooter_demo.Game

fun main() {
    val config = LwjglApplicationConfiguration()
    LwjglApplication(Game(), config)
}
