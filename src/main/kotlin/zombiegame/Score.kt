package zombiegame

import zombiegame.LevelType.Blue
import zombiegame.LevelType.Orange
import zombiegame.LevelType.Red
import zombiegame.LevelType.Yellow

class Score {
    var experience: Int = 0
        private set
    var previousLevel: LevelType = Blue
        private set
    var level: LevelType = Blue
        private set

    fun incrementExperience() {
        val oldLevel = level
        experience++
        level = getLevel(experience)

        if (level > oldLevel) {
            previousLevel = oldLevel
        }
    }

    fun levelHasGoneUp() : Boolean = level > previousLevel

    companion object {
        fun getLevel(experience: Int): LevelType = when {
            experience >= Red.experience -> Red
            experience >= Orange.experience -> Orange
            experience >= Yellow.experience -> Yellow
            else -> Blue
        }

        fun levelAndSkill(experience: Int): Pair<LevelType, Int> {
            val skill = (experience / (Red.experience + 1)) + 1

            val experienceWithoutSkill = experience - (skill - 1) * Red.experience
            val level = getLevel(experienceWithoutSkill)

            return Pair(level, skill)
        }
    }
}

enum class LevelType(val experience: Int) {
    Blue(0),
    Yellow(7),
    Orange(19),
    Red(43),
}
