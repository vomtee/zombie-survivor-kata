package zombiegame

import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.LevelType.Blue
import zombiegame.LevelType.Orange
import zombiegame.LevelType.Red
import zombiegame.LevelType.Yellow
import zombiegame.Score.Companion.levelAndSkill

class ScoreTest {
    private val score = Score()

    @Test
    fun `previous and current level are initially the same`() {
        score.level shouldBe score.previousLevel
        score.level shouldBe Blue
    }

    @Test
    fun `more than 6 experience gives level Yellow`() {
        repeat(7) {
            score.incrementExperience()
        }

        score.experience shouldBeEqual 7
        score.level shouldBeGreaterThan score.previousLevel
        score.levelHasGoneUp() shouldBe true
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

    @Test
    fun `testing levels for skill 1 experience`() {
        levelAndSkill(Blue.experience) shouldBe Pair(Blue, 1)
        levelAndSkill(Yellow.experience) shouldBe Pair(Yellow, 1)
        levelAndSkill(Orange.experience) shouldBe Pair(Orange, 1)
        levelAndSkill(Red.experience) shouldBe Pair(Red, 1)
    }

    @Test
    fun `testing levels for skill 2 experience`() {
        levelAndSkill(Red.experience + Blue.experience + 1) shouldBe Pair(Blue, 2)
        levelAndSkill(Red.experience + Yellow.experience) shouldBe Pair(Yellow, 2)
        levelAndSkill(Red.experience + Orange.experience) shouldBe Pair(Orange, 2)
        levelAndSkill(2 * Red.experience) shouldBe Pair(Red, 2)
    }

    @Test
    fun `testing levels for skill 3 experience`() {
        levelAndSkill(2 * Red.experience + Blue.experience + 2) shouldBe Pair(Blue, 3)
        levelAndSkill(2 * Red.experience + Yellow.experience) shouldBe Pair(Yellow, 3)
        levelAndSkill(2 * Red.experience + Orange.experience) shouldBe Pair(Orange, 3)
        levelAndSkill(3 * Red.experience) shouldBe Pair(Red, 3)
    }

    @Test
    fun `testing levels for skill 4 experience`() {
        levelAndSkill(3 * Red.experience + Blue.experience + 3) shouldBe Pair(Blue, 4)
        levelAndSkill(3 * Red.experience + Yellow.experience) shouldBe Pair(Yellow, 4)
        levelAndSkill(3 * Red.experience + Orange.experience) shouldBe Pair(Orange, 4)
        levelAndSkill(4 * Red.experience) shouldBe Pair(Red, 4)
    }

}