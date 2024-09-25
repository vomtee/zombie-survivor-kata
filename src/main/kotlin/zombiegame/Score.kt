package zombiegame

class Score {
    var experience: Int = 0
        private set
    var level: LevelType = LevelType.Blue
        private set

    fun incrementExperience() {
        experience++
        when {
            experience > 6 -> level = LevelType.Yellow
        }
    }
}

enum class LevelType {
    Blue,
    Yellow,
//    Orange,
//    Red
}
