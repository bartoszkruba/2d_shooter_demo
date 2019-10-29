package com.ktx.shooter_demo.model

import com.badlogic.gdx.utils.TimeUtils

abstract class Weapon(private val reloadTime: Long) {
    private var lastShoot = 0L
    fun canShoot(): Boolean = TimeUtils.millis() - lastShoot > reloadTime


    fun fire() {
        lastShoot = TimeUtils.millis()
    }
}