package zombiegame

import Survivor
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


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
}