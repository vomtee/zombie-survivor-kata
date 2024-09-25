package zombiegame

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.LevelType.Yellow

class ScoreTest {
    val score = Score()

    @Test
    fun `more than 6 experience gives level yellow`() {
        repeat(7) {
            score.incrementExperience()
        }

        score.level shouldBe Yellow
    }
}