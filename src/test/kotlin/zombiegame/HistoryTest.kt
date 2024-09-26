package zombiegame

import Survivor
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.TimeUtils.Companion.FIXED_CLOCK

class HistoryTest {
    private val game = Game()
    private val history = History(FIXED_CLOCK, game)

    @Test
    fun `history records beginning of game`() {
        game.add(Survivor("Hantz"))

        history.list[0] shouldBe "Game started at 12:34."
    }

    @Test
    fun `history records that a survivor has been added to the game`() {
        game.add(Survivor("Hantz"))

        history.list[1] shouldBe """Survivor "Hantz" has been added to the game."""
    }
}