package zombiegame

import Survivor

class Game {
    private val nameToSurvivor = mutableMapOf<String, Survivor>()
    val amountOfSurvivors
        get() = nameToSurvivor.size

    fun add(survivor: Survivor) : Boolean {
        nameToSurvivor[survivor.name] = survivor
        return true
    }
}