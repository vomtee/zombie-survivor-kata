package zombiegame

fun advancement(experience: Int): Int {
    return (experience / LevelType.Red.experience) + 1
}
