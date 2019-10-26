package com.ktx.shooter_demo.screen

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen
import ktx.graphics.use

class LoadingScreen(
        val assets: AssetManager,
        val camera: OrthographicCamera,
        val batch: SpriteBatch,
        val font: BitmapFont
) : KtxScreen {

    override fun render(delta: Float) {
        super.render(delta)

        batch.use {
            font.draw(it, "Welcome!!!", 100f, 150f)
            font.draw(it, "Tap anywhere to begin", 100f, 100f)
        }

    }

    override fun dispose() {
        super.dispose()
    }
}