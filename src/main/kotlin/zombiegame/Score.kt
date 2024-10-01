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
        when {
            experience >= Red.experience -> level = Red
            experience >= Orange.experience -> level = Orange
            experience >= Yellow.experience -> level = Yellow
        }

        if (level > oldLevel) {
            previousLevel = oldLevel
        }
    }

    fun levelHasGoneUp() : Boolean = level > previousLevel
}

enum class LevelType(val experience: Int) {
    Blue(0),
    Yellow(7),
    Orange(19),
    Red(43),
}
