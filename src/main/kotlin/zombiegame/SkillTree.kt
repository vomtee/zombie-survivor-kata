package zombiegame

import kotlin.math.min
import zombiegame.LevelType.Orange
import zombiegame.LevelType.Red
import zombiegame.LevelType.Yellow
import zombiegame.SkillType.HOARD
import zombiegame.SkillType.PLUS_ONE_ACTION
import zombiegame.SkillType.PLUS_ONE_DIE_MELEE
import zombiegame.SkillType.PLUS_ONE_DIE_RANGED
import zombiegame.SkillType.PLUS_ONE_FREE_MOVE
import zombiegame.SkillType.SNIPER

class SkillTree {
    private val levelToSkills = mapOf(
        Yellow to listOf(PLUS_ONE_ACTION),
        Orange to listOf(PLUS_ONE_DIE_RANGED, PLUS_ONE_DIE_MELEE),
        Red to listOf(PLUS_ONE_FREE_MOVE, HOARD, SNIPER)
    )

    fun unlockedSkills(level: LevelType, skillLevel: Int): List<SkillType> {
        if (!levelToSkills.contains(level)) {
            return emptyList()
        }

        val skills: List<SkillType> = levelToSkills[level]!!
        return skills.take(min(skills.size, skillLevel))
    }
}

enum class SkillType(description: String) {
    PLUS_ONE_ACTION("+1 Action"),
    PLUS_ONE_DIE_RANGED("+1 Die (Ranged)"),
    PLUS_ONE_DIE_MELEE("+1 Die (Melee)"),
    PLUS_ONE_FREE_MOVE("+1 Free Move Action"),
    HOARD("Hoard"),
    SNIPER("Sniper");
//    TOUGH("Tough");
}