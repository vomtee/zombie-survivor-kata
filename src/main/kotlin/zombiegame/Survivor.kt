import zombiegame.Equipment
import zombiegame.EquipmentType
import zombiegame.LevelType
import zombiegame.Score
import zombiegame.SkillTree
import zombiegame.SkillType
import zombiegame.SkillType.HOARD
import zombiegame.SkillType.PLUS_ONE_ACTION

const val MAX_WOUNDS = 2
const val STANDARD_MAX_ACTIONS = 3
const val UPGRADED_MAX_ACTIONS = 4

class Survivor(val name: String) : SurvivorObservable {
    val equipment = Equipment()
    var wounds = 0
        private set
    var dead = false
        private set
    private var actionCount = 0
    private var maxActionCount = STANDARD_MAX_ACTIONS
    private var observers = mutableListOf<SurvivorObserver>()

    private val score = Score()
    val experience
        get() = score.experience
    val level
        get() = score.level

    private val skillTree = SkillTree()
    val skills: Set<SkillType>
        get() {
            val (level, skill) = Score.levelAndSkill(experience)
            return skillTree.getAllSkillsUpTo(level, skill)
        }

    private var oldSkills = setOf<SkillType>()

    fun act(): Boolean {
        if (actionCount >= maxActionCount) {
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
        oldSkills = skills
        score.incrementExperience()

        val newSkills = skills - oldSkills
        if (newSkills.isNotEmpty()) {
            observers.forEach { it.notifyNewSkill(this, newSkills.first()) }
        }

        if (skills.contains(PLUS_ONE_ACTION)) {
            maxActionCount = UPGRADED_MAX_ACTIONS
        }

        if (skills.contains(HOARD)) {
            equipment.upgradeMaxAmount()
        }

        observers.forEach { it.notifyLevel(this) }

        if (score.levelHasGoneUp()) {
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
    fun notifyNewSkill(survivor: Survivor, skillType: SkillType)
}

