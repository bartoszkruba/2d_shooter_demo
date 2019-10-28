package com.ktx.shooter_demo.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.ktx.shooter_demo.Game
import com.ktx.shooter_demo.assets.TextureAtlasAssets
import com.ktx.shooter_demo.assets.get
import com.ktx.shooter_demo.model.Player
import com.ktx.shooter_demo.settings.WINDOW_HEIGHT
import com.ktx.shooter_demo.settings.WINDOW_WIDTH
import ktx.app.KtxScreen
import ktx.graphics.use

class GameScreen(
        val camera: OrthographicCamera,
        val game: Game,
        val assets: AssetManager,
        val batch: SpriteBatch) : KtxScreen {

    val playerRegion = assets[TextureAtlasAssets.Game].findRegion("player")

    val player = Player(playerRegion, WINDOW_WIDTH / 2f, WINDOW_HEIGHT / 2f)
    val mousePosition = Vector2()

    override fun render(delta: Float) {

        mousePosition.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())

        val x = WINDOW_WIDTH / 2f
        val y = WINDOW_HEIGHT / 2f

        val dd = Gdx.graphics.width / 2f
        val cc = Gdx.graphics.height / 2f

        val rot = MathUtils.radiansToDegrees * MathUtils.atan2(mousePosition.y - dd, mousePosition.x - cc)

        player.apply {
            facingDirection = rot
            sprite.rotation = -(player.facingDirection)
            sprite.apply { setOrigin(this.width / 2f, this.height / 2f) }
            sprite.setPosition(x - sprite.width / 2f, y - sprite.height / 2f)
        }

        batch.use {
            player.sprite.draw(it)
        }
    }

    override fun resize(width: Int, height: Int) {
        camera.viewportWidth = WINDOW_WIDTH
        camera.viewportHeight = WINDOW_HEIGHT
    }

    override fun dispose() {
        super.dispose()
    }
}