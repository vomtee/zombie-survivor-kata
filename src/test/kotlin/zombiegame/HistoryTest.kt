package zombiegame

import Survivor
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.EquipmentType.KATANA
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

        history.list shouldContain """Survivor "Hantz" has been added to the game."""
    }

    @Test
    fun `history records that a survivor has added an item to his equipment`()
    {
        val hantz = Survivor("Hantz")
        game.add(hantz)
        hantz.addEquipment(KATANA)

        history.list shouldContain """Survivor "Hantz" has added "KATANA" to its equipment."""
    }

    @Test
    fun `history records that a survivor has been wounded`()
    {
        val hantz = Survivor("Hantz")
        game.add(hantz)
        hantz.wound()

        history.list shouldContain """Survivor "Hantz" has been wounded."""
    }

    @Test
    fun `history records that a survivor has died`() {
        val hantz = Survivor("Hantz")
        game.add(hantz)
        hantz.wound()
        hantz.wound()

        history.list shouldContain """Survivor "Hantz" has died."""
    }

    @Test
    fun `history records that a survivor has levelled up`()
    {
        val hantz = Survivor("Hantz")
        game.add(hantz)

        repeat(7) {
            hantz.killZombie()
        }

        history.list shouldContain  """Survivor "Hantz" has levelled up to "Yellow"."""
    }

    @Test
    fun `history records that game has levelled up`()
    {
        val hantz = Survivor("Hantz")
        game.add(hantz)

        repeat(7) {
            hantz.killZombie()
        }

        history.list shouldContain  """Game has levelled up to "Yellow"."""
    }
}