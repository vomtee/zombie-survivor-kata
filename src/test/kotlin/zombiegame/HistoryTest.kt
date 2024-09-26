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
}