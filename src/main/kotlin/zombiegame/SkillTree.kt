package zombiegame

import zombiegame.LevelType.Orange
import zombiegame.LevelType.Red
import zombiegame.LevelType.Yellow
import zombiegame.SkillType.HOARD
import zombiegame.SkillType.PLUS_ONE_ACTION
import zombiegame.SkillType.PLUS_ONE_DIE_MELEE
import zombiegame.SkillType.PLUS_ONE_DIE_RANGED
import zombiegame.SkillType.PLUS_ONE_FREE_MOVE
import zombiegame.SkillType.SNIPER

typealias SkillList = List<SkillType>

class SkillTree(levelToSkills: MutableMap<LevelType, SkillList> = mutableMapOf()) :
    Map<LevelType, SkillList> by levelToSkills {

    init {
        levelToSkills[Yellow] = listOf(PLUS_ONE_ACTION)
        levelToSkills[Orange] = listOf(PLUS_ONE_DIE_RANGED, PLUS_ONE_DIE_MELEE)
        levelToSkills[Red] = listOf(PLUS_ONE_FREE_MOVE, HOARD, SNIPER)
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

    var locked: Boolean = true
        private set

    fun unlock() {
        locked = false
    }
}