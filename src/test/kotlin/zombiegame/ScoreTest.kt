package zombiegame

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.LevelType.Orange
import zombiegame.LevelType.Red
import zombiegame.LevelType.Yellow

class ScoreTest {
    val score = Score()

    @Test
    fun `more than 6 experience gives level Yellow`() {
        repeat(7) {
            score.incrementExperience()
        }
        score.level shouldBe Yellow
    }

    @Test
    fun `more than 18 experience gives level Orange`() {
        repeat(19) {
            score.incrementExperience()
        }
        score.level shouldBe Orange
    }

    @Test
    fun `more than 42 experience gives level Red`() {
        repeat(43) {
            score.incrementExperience()
        }
        score.level shouldBe Red
    }

    @Test
    fun `level Red is the final level`() {
        repeat(100) {
            score.incrementExperience()
        }
        score.level shouldBe Red
    }
}