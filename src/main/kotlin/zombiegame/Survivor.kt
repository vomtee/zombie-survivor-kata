import zombiegame.Equipment
import zombiegame.EquipmentType
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
    private var observer: SurvivorObserver? = null

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

        if (wounds >= MAX_WOUNDS) {
            dead = true
            observer?.notifyDead(this)
        }
    }

    fun killZombie() {
        score.incrementExperience()
        observer?.notifyLevel(this)
    }

    override fun addObserver(observer: SurvivorObserver) {
        this.observer = observer
    }

    fun add(item: EquipmentType) {

    }
}

interface SurvivorObservable {
    fun addObserver(observer: SurvivorObserver)
}

interface SurvivorObserver {
    fun notifyDead(survivor: Survivor)
    fun notifyLevel(survivor: Survivor)
}

