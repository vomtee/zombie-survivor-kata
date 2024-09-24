package zombiegame

import Survivor

class Game {
    private val nameToSurvivor = mutableMapOf<String, Survivor>()
    val amountOfSurvivors
        get() = nameToSurvivor.size

    val ended
        get() = amountOfSurvivors == 0

    fun add(survivor: Survivor) : Boolean = when {
        nameToSurvivor.containsKey(survivor.name) -> {
            false
        }

        else -> {
            nameToSurvivor[survivor.name] = survivor
            true
        }
    }
}