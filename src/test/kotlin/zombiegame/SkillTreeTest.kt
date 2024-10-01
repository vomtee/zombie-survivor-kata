package zombiegame

import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.LevelType.Orange
import zombiegame.LevelType.Red
import zombiegame.LevelType.Yellow

class SkillTreeTest {
    private val skillTree = SkillTree()

    @Test
    fun `skill tree has one locked skill at level yellow`() {
        skillTree[Yellow]?.let {
            it.shouldBeEqual(listOf(SkillType.PLUS_ONE_ACTION))
            it.first().locked shouldBe true
        }
    }

    @Test
    fun `all skills should be locked in the beginning`() {
        skillTree[Red]?.filter { skillType -> !skillType.locked } shouldBe emptyList()
    }

    @Test
    fun `skill tree has two skills at level orange`() {
        skillTree[Orange]?.size shouldBe 2
    }

    @Test
    fun `skill tree has three skills at level red`() {
        skillTree[Red]?.size shouldBe 3
    }

}