package zombiegame

import zombiegame.LevelType.Yellow
import zombiegame.SkillType.PLUS_ONE_ACTION

typealias SkillList = List<SkillType>

class SkillTree(levelToSkills: MutableMap<LevelType, SkillList> = mutableMapOf()) :
    Map<LevelType, SkillList> by levelToSkills {

    init {
        levelToSkills[Yellow] = listOf(PLUS_ONE_ACTION)

    }
}

enum class SkillType(description: String) {
    PLUS_ONE_ACTION("+1 Action");
//    PLUS_ONE_DIE_RANGED("+1 Die (Ranged)"),
//    PLUS_ONE_DIE_MELEE("+1 Die (Melee)"),
//    PLUS_ONE_FREE_MOVE("+1 Free Move Action"),
//    HOARD("Hoard"),
//    SNIPER("Sniper"),
//    TOUGH("Tough");

    var locked: Boolean = true
        private set

    fun unlock() {
        locked = false
    }
}