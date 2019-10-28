package com.ktx.shooter_demo

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ktx.shooter_demo.screen.GameScreen
import com.ktx.shooter_demo.screen.LoadingScreen
import com.ktx.shooter_demo.settings.WINDOW_HEIGHT
import com.ktx.shooter_demo.settings.WINDOW_WIDTH
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
            bindSingleton(OrthographicCamera().apply { setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT) })

            addScreen(LoadingScreen(inject(), inject(), inject(), inject(), inject()))
        }

        changeToLoadingScreen()

        super.create()
    }

    fun changeToLoadingScreen() = setScreen<LoadingScreen>()

    fun changeToGameScreen() {
        removeScreen<LoadingScreen>()
        addScreen(GameScreen(context.inject(), context.inject(), context.inject(), context.inject()))
        setScreen<GameScreen>()
    }

    override fun dispose() {

        context.dispose()
        super.dispose()
    }
}
