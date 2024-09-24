package zombiegame

import Survivor
import SurvivorObserver

class Game: SurvivorObserver {
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
            survivor.addObserver(this)
            true
        }
    }

    override fun notifyDead(survivor: Survivor) {
        nameToSurvivor.remove(survivor.name)
    }
}

