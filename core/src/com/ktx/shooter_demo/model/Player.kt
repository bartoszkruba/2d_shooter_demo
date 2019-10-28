package com.ktx.shooter_demo.model

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Circle
import com.ktx.shooter_demo.settings.WINDOW_HEIGHT
import com.ktx.shooter_demo.settings.WINDOW_WIDTH

class Player(textureRegion: TextureRegion, x: Float, y: Float, var facingDirection: Float = 0f) {
    val sprite: Sprite = Sprite(textureRegion)
    val bounds = Circle()

    init {
        bounds.apply {
            radius = textureRegion.regionWidth / 2f
            setPosition(x, y)
        }
        sprite.setSize(textureRegion.regionWidth.toFloat(), textureRegion.regionHeight.toFloat())
    }
}
