package zombiegame

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.LevelType.Blue
import zombiegame.LevelType.Orange
import zombiegame.LevelType.Red
import zombiegame.LevelType.Yellow

class SkillTreeTest {
    private val skillTree = SkillTree()

    @Test
    fun `there are no unlocked skills for level blue`() {
        skillTree.unlockedSkills(Blue, 0) shouldBe emptyList()
    }

    @Test
    fun `there are no unlocked skills for level blue with any skill`() {
        skillTree.unlockedSkills(Blue, 1000) shouldBe emptyList()
    }

    @Test
    fun `there is 1 unlocked skill for level yellow with skill 1`() {
        skillTree.unlockedSkills(Yellow, 1).size shouldBe 1
    }

    @Test
    fun `level yellow only has one skill to unlock`() {
        skillTree.unlockedSkills(Yellow, 2).size shouldBe 1
    }

    @Test
    fun `there is 1 unlocked skill for level orange with skill 1`() {
        skillTree.unlockedSkills(Orange, 1).size shouldBe 1
    }

    @Test
    fun `there are 2 unlocked skills for level orange with skill 2`() {
        skillTree.unlockedSkills(Orange, 2).size shouldBe 2
    }

    @Test
    fun `there are 3 unlocked skills for level red with skill 3`() {
        skillTree.unlockedSkills(Red, 3).size shouldBe 3
    }
}