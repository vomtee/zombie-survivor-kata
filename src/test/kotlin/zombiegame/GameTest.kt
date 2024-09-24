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
}