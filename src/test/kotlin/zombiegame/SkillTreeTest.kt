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
    fun `skill tree has two skills at level orange`() {
        skillTree[Orange]?.let { it ->
            it.size shouldBe 2
            it.filter { skillType -> !skillType.locked } shouldBe emptyList()
        }
    }

    @Test
    fun `skill tree has three skills at level red`() {
        skillTree[Red]?.let {
            it.size shouldBe 3
            it.filter { skillType -> !skillType.locked } shouldBe emptyList()
        }
    }
}