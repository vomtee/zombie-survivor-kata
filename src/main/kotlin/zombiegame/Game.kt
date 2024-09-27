package zombiegame

import Survivor
import SurvivorObserver
import zombiegame.LevelType.Blue

class Game : SurvivorObserver, GameObservable {
    private var observer: GameObserver? = null

    private val nameToSurvivor = mutableMapOf<String, Survivor>()
    val amountOfSurvivors
        get() = nameToSurvivor.size

    val ended
        get() = amountOfSurvivors == 0

    var level: LevelType = Blue
        private set

    fun add(survivor: Survivor): Boolean = when {
        nameToSurvivor.containsKey(survivor.name) -> {
            false
        }

        else -> {
            nameToSurvivor[survivor.name] = survivor
            survivor.addObserver(this)

            if (amountOfSurvivors == 1) {
                observer?.notifyGameStart()
            }

            observer?.notifySurvivorAdded(survivor)

            true
        }
    }

    override fun notifyDead(survivor: Survivor) {
        nameToSurvivor.remove(survivor.name)
    }

    override fun notifyLevel(survivor: Survivor) {
        if (survivor.dead) {
            return
        }

        if (survivor.level > level) {
            level = survivor.level
        }
    }

    override fun notifyAddEquipment(survivor: Survivor, equipment: Equipment) {}

    override fun addObserver(observer: GameObserver) {
        this.observer = observer
    }
}

interface GameObservable {
    fun addObserver(observer: GameObserver)
}

interface GameObserver {
    fun notifyGameStart()
    fun notifySurvivorAdded(survivor: Survivor)
}

