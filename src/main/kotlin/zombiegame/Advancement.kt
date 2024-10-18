package zombiegame

fun advancement(experience: Int): Int {
    if (experience > 2 * LevelType.Red.experience) {
        return 3
    }

    if (experience > LevelType.Red.experience) {
        return 2
    }

    return 1
}
