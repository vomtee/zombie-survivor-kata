package zombiegame

import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test


class GameTest {
    private val game = Game()

    @Test
    fun `game begins with 0 survivors`() {
        game.amountOfSurvivors shouldBeEqual 0
    }
}