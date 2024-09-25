package zombiegame

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.LevelType.Yellow

class ExperienceLevelTest {
    val experienceLevel = ExperienceLevel(

    )

    @Test
    fun `more than 6 experience gives level yellow`() {
        repeat(7) {
            experienceLevel.incrementExperience()
        }

        experienceLevel.level shouldBe Yellow
    }
}