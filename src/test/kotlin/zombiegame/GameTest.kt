package zombiegame

import Survivor
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.LevelType.Blue
import zombiegame.LevelType.Orange
import zombiegame.LevelType.Yellow


class GameTest {
    private val game = Game()

    @Test
    fun `game begins with 0 survivors`() {
        game.amountOfSurvivors shouldBeEqual 0
    }

    @Test
    fun `survivors can be added to the game`() {
        game.add(Survivor("Hanz"))
        game.amountOfSurvivors shouldBeEqual 1
    }

    @Test
    fun `survivor names must be unique`() {
        game.add(Survivor("Hanz")) shouldBe true
        game.add(Survivor("Hanz")) shouldBe false
        game.amountOfSurvivors shouldBeEqual 1
    }

    @Test
    fun `can add unique survivors`() {
        game.add(Survivor("Hanz")) shouldBe true
        game.add(Survivor("Frantz")) shouldBe true
        game.amountOfSurvivors shouldBeEqual 2
    }

    @Test
    fun `game ends when all survivors have died`() {
        val hanz = Survivor("Hanz")
        val frantz = Survivor("Frantz")

        game.add(hanz)
        game.add(frantz)
        game.ended shouldBe false

        hanz.wound()
        hanz.wound()
        game.amountOfSurvivors shouldBeEqual 1

        frantz.wound()
        frantz.wound()
        game.amountOfSurvivors shouldBeEqual 0
        game.ended shouldBe true
    }

    @Test
    fun `game starts with level Blue`() {
        game.level shouldBe Blue
    }

    @Test
    fun `game advances to level Yellow if player advances to this level`() {
        val hanz = Survivor("Hanz")
        game.add(hanz)

        repeat(7) {
            hanz.killZombie()
        }

        game.level shouldBe Yellow
    }

    @Test
    fun `game level is level of highest survivor level`() {
        val hanz = Survivor("Hanz")
        val frantz = Survivor("Frantz")

        game.add(hanz)
        game.add(frantz)

        repeat(7) {
            hanz.killZombie()
        }
        game.level shouldBe Yellow

        repeat(19) {
            frantz.killZombie()
        }
        game.level shouldBe Orange
    }

    @Test
    fun `game does not advance to level of dead player`() {
        game.level shouldBe Blue
        val hanz = Survivor("Hanz")
        game.add(hanz)

        hanz.wound()
        hanz.wound()
        hanz.dead shouldBe true

        repeat(7) {
            hanz.killZombie()
        }

        hanz.level shouldBe Yellow
        game.level shouldBe Blue
    }

}