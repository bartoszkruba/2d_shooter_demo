package com.ktx.shooter_demo.model

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2

class Projectile(val sprite: Sprite, x: Float, y: Float) {
    val bounds = Circle()
    val speed = Vector2()

    init {
        bounds.radius = sprite.width / 2
        bounds.x = x
        bounds.y = y
    }
}