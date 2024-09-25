package zombiegame

class ExperienceLevel {
    val experience: Int = 0
    val level: LevelType = LevelType.Blue

    fun incremenentExperience() {
    }
}

enum class LevelType {
    Blue,
    Yellow,
//    Orange,
//    Red
}
