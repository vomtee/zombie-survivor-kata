import zombiegame.Equipment
import zombiegame.EquipmentType
import zombiegame.LevelType
import zombiegame.Score

const val MAX_WOUNDS = 2
const val MAX_ACTIONS = 3

class Survivor(val name: String) : SurvivorObservable {
    val equipment = Equipment()
    var wounds: Int = 0
        private set
    var dead: Boolean = false
        private set
    private var actionCount: Int = 0
    private var observers = mutableListOf<SurvivorObserver>()

    private val score = Score()
    val experience
        get() = score.experience
    val level
        get() = score.level

    fun act(): Boolean {
        if (actionCount >= MAX_ACTIONS) {
            return false
        }

        actionCount++
        return true
    }

    fun nextTurn() {
        actionCount = 0
    }

    fun wound() {
        if (dead) {
            return
        }

        wounds++
        equipment.decreaseMaxAmount()
        observers.forEach { it.notifyWound(this) }

        if (wounds >= MAX_WOUNDS) {
            dead = true
            observers.forEach { it.notifyDead(this) }
        }
    }

    fun killZombie() {
        score.incrementExperience()
        observers.forEach { it.notifyLevel(this) }

        if(score.levelHasGoneUp()) {
            observers.forEach { it.notifyLevelChange(this, level) }
        }
    }

    override fun addObserver(observer: SurvivorObserver) {
        observers.add(observer)
    }

    fun addEquipment(item: EquipmentType): Boolean {
        val result = equipment.add(item)
        observers.forEach { it.notifyAddEquipment(this, item) }
        return result
    }
}

interface SurvivorObservable {
    fun addObserver(observer: SurvivorObserver)
}

interface SurvivorObserver {
    fun notifyDead(survivor: Survivor)
    fun notifyLevel(survivor: Survivor)
    fun notifyAddEquipment(survivor: Survivor, item: EquipmentType)
    fun notifyWound(survivor: Survivor)
    fun notifyLevelChange(survivor: Survivor, level: LevelType)
}

