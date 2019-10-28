package com.ktx.shooter_demo.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.*
import com.badlogic.gdx.utils.Array
import com.ktx.shooter_demo.Game
import com.ktx.shooter_demo.assets.TextureAtlasAssets
import com.ktx.shooter_demo.assets.get
import com.ktx.shooter_demo.model.Player
import com.ktx.shooter_demo.model.Projectile
import com.ktx.shooter_demo.settings.WINDOW_HEIGHT
import com.ktx.shooter_demo.settings.WINDOW_WIDTH
import ktx.app.KtxScreen
import ktx.collections.iterate
import ktx.graphics.use
import ktx.assets.pool


class GameScreen(
        val camera: OrthographicCamera,
        val game: Game,
        val assets: AssetManager,
        val batch: SpriteBatch) : KtxScreen {

    val playerRegion = assets[TextureAtlasAssets.Game].findRegion("player")

    val player = Player(playerRegion, WINDOW_WIDTH / 2f, WINDOW_HEIGHT / 2f)
    val projectile = Texture(Gdx.files.internal("images/projectile.png"))
    val mousePosition = Vector2()

    val projectiles = Array<Projectile>()
    val projectilePool = pool {
        val sprite = Sprite(projectile)
        sprite.setSize(projectile.width.toFloat(), projectile.height.toFloat())
        Projectile(sprite, 0f, 0f)
    }

    override fun render(delta: Float) {

        mousePosition.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())

        val x = WINDOW_WIDTH / 2f
        val y = WINDOW_HEIGHT / 2f

        val originX = player.sprite.originX + player.sprite.x
        val originY = player.sprite.originY + player.sprite.y
        val mouse = getMousePosInGameWorld()
        val angle = MathUtils.atan2(mouse.y - originY, mouse.x - originX) * MathUtils.radDeg

        player.apply {
            facingDirection = angle
            sprite.rotation = (player.facingDirection)
            sprite.apply { setOrigin(this.width / 2f, this.height / 2f) }
            sprite.setPosition(x - sprite.width / 2f, y - sprite.height / 2f)
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            spawnProjectile(MathUtils.cosDeg(angle) * 200, MathUtils.sinDeg(angle) * 200,
                    player.bounds.x, player.bounds.y)
        }

        batch.use {
            player.sprite.draw(it)
            projectiles.iterate { projectile, iterator ->
                projectile.bounds.setX(projectile.bounds.x + projectile.speed.x * delta)
                projectile.bounds.setY(projectile.bounds.y + projectile.speed.y * delta)
                projectile.sprite.apply {
                    setPosition(projectile.bounds.x - width / 2f, projectile.bounds.y - height / 2f)
                }
                val screen = Rectangle(0f, 0f, WINDOW_WIDTH, WINDOW_HEIGHT)
                if (Intersector.overlaps(projectile.bounds, screen)) projectile.sprite.draw(it)
                else {
                    iterator.remove()
                    projectilePool.free(projectile)
                }
            }
        }
    }

    fun getMousePosInGameWorld(): Vector3 {
        return camera.unproject(Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f))
    }

    fun spawnProjectile(xSpeed: Float, ySpeed: Float, xPosition: Float, yPosition: Float) {
        projectiles.add(projectilePool.obtain().apply {
            bounds.setPosition(xPosition, yPosition)
            speed.set(xSpeed, ySpeed)
        })
    }

    override fun resize(width: Int, height: Int) {
        camera.viewportWidth = WINDOW_WIDTH
        camera.viewportHeight = WINDOW_HEIGHT
    }

    override fun dispose() {
        super.dispose()
    }
}