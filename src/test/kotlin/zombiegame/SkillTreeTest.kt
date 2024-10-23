package zombiegame

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.LevelType.Blue
import zombiegame.LevelType.Orange
import zombiegame.LevelType.Red
import zombiegame.LevelType.Yellow
import zombiegame.SkillType.HOARD
import zombiegame.SkillType.PLUS_ONE_ACTION
import zombiegame.SkillType.PLUS_ONE_DIE_MELEE
import zombiegame.SkillType.PLUS_ONE_DIE_RANGED
import zombiegame.SkillType.PLUS_ONE_FREE_MOVE

class SkillTreeTest {
    private val skillTree = SkillTree()

    @Test
    fun `there are no unlocked skills for level blue`() {
        skillTree.getSkillsFor(Blue, 0) shouldBe emptyList()
    }

    @Test
    fun `there are no unlocked skills for level blue with any skill`() {
        skillTree.getSkillsFor(Blue, 1000) shouldBe emptyList()
    }

    @Test
    fun `there is 1 unlocked skill for level yellow with skill 1`() {
        skillTree.getSkillsFor(Yellow, 1).size shouldBe 1
    }

    @Test
    fun `level yellow only has one skill to unlock`() {
        skillTree.getSkillsFor(Yellow, 2).size shouldBe 1
    }

    @Test
    fun `there is 1 unlocked skill for level orange with skill 1`() {
        skillTree.getSkillsFor(Orange, 1).size shouldBe 1
    }

    @Test
    fun `there are 2 unlocked skills for level orange with skill 2`() {
        skillTree.getSkillsFor(Orange, 2).size shouldBe 2
    }

    @Test
    fun `there are 3 unlocked skills for level red with skill 3`() {
        skillTree.getSkillsFor(Red, 3).size shouldBe 3
    }

    @Test
    fun `there are no skills for levels until blue and any skill`() {
        skillTree.getAllSkillsUpTo(Blue, 5000).size shouldBe 0
    }

    @Test
    fun `there is 1 skill for levels until yellow and any skill`() {
        skillTree.getAllSkillsUpTo(Yellow, 5000).size shouldBe 1
    }

    @Test
    fun `there are 2 skills for levels until orange and skill 1`() {
        skillTree.getAllSkillsUpTo(Orange, 1).size shouldBe 2
    }

    @Test
    fun `there are 5 skills for levels until red and skill 2`() {
        skillTree.getAllSkillsUpTo(Red, 2).size shouldBe 5
    }

    @Test
    fun `there are 6 skills for levels until red and skill 3`() {
        skillTree.getAllSkillsUpTo(Red, 3).size shouldBe 6
    }

    @Test
    fun `there should be a specific list of skills for levels until red and skill 2`() {
        skillTree.getAllSkillsUpTo(Red, 2) shouldBe setOf(
            PLUS_ONE_ACTION, PLUS_ONE_DIE_RANGED, PLUS_ONE_DIE_MELEE,
            PLUS_ONE_FREE_MOVE, HOARD,
        )
    }
}