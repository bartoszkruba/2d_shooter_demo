package com.ktx.shooter_demo

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ktx.shooter_demo.screen.LoadingScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.inject.Context

class Game : KtxGame<KtxScreen>() {

    private val context = Context()

    override fun create() {

        context.register {
            bindSingleton(this@Game)
            bindSingleton(SpriteBatch())
            bindSingleton(BitmapFont())
            bindSingleton(AssetManager())
            bindSingleton(OrthographicCamera().apply { setToOrtho(false, 800f, 400f) })

            addScreen(LoadingScreen(inject(), inject(), inject(), inject()))
        }

        changeToLoadingScreen()

        super.create()
    }

    private fun changeToLoadingScreen() = setScreen<LoadingScreen>()

    override fun dispose() {

        context.dispose()
        super.dispose()
    }
}
