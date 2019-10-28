package com.ktx.shooter_demo.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ktx.shooter_demo.Game
import com.ktx.shooter_demo.assets.TextureAtlasAssets
import com.ktx.shooter_demo.assets.load
import ktx.app.KtxScreen
import ktx.graphics.use

class LoadingScreen(
        val game: Game,
        val assets: AssetManager,
        val camera: OrthographicCamera,
        val batch: SpriteBatch,
        val font: BitmapFont
) : KtxScreen {

    override fun render(delta: Float) {
        assets.update()

        camera.update()
        batch.projectionMatrix = camera.combined

        batch.use {
            font.draw(it, "Welcome!!!", 100f, 150f)
            if (assets.isFinished) {
                font.draw(it, "Press enter to begin", 100f, 100f)
            } else {
                font.draw(it, "Loading assets", 100f, 100f)
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && assets.isFinished) game.changeToGameScreen()
    }

    override fun show() {
        TextureAtlasAssets.values().forEach { assets.load(it) }
        super.show()
    }

    override fun dispose() {
        super.dispose()
    }
}