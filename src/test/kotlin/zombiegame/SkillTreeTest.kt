package zombiegame

import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test
import zombiegame.LevelType.Yellow

class SkillTreeTest {
    private val skillTree = SkillTree()

    @Test
    fun `skill tree has one locked skill at level yellow`() {
        skillTree[Yellow]?.shouldBeEqual(listOf(SkillType.PLUS_ONE_ACTION))
    }
}