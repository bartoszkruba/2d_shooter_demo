package com.ktx.shooter_demo.model

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Circle

class Player(textureRegion: TextureRegion, x: Float, y: Float, var facingDirection: Float = 0f, var weapon: Weapon) {
    val sprite: Sprite = Sprite(textureRegion)
    val bounds = Circle()

    init {
        bounds.apply {
            radius = textureRegion.regionWidth / 2f
            setPosition(x, y)
        }
        sprite.setSize(textureRegion.regionWidth.toFloat(), textureRegion.regionHeight.toFloat())
    }

    fun fire() = weapon.fire()
    fun canShoot() = weapon.canShoot()
}
