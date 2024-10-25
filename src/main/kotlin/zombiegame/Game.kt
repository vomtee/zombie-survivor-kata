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
        if (ended) {
            observer?.notifyGameEnd()
        }
    }

    override fun notifyLevel(survivor: Survivor) {
        if (survivor.dead) {
            return
        }

        if (survivor.level > level) {
            level = survivor.level
            observer?.notifyLevelUp(level)
        }
    }

    override fun notifyAddEquipment(survivor: Survivor, item: EquipmentType) {}
    override fun notifyWound(survivor: Survivor) {}
    override fun notifyLevelChange(survivor: Survivor, level: LevelType) {}
    override fun notifyNewSkill(survivor: Survivor, skillType: SkillType) {}

    override fun addObserver(observer: GameObserver) {
        this.observer = observer
    }
}

interface GameObservable {
    fun addObserver(observer: GameObserver)
}

interface GameObserver {
    fun notifyGameStart()
    fun notifyGameEnd()
    fun notifySurvivorAdded(survivor: Survivor)
    fun notifyLevelUp(level: LevelType)
}

