package zombiegame

import Survivor
import SurvivorObserver
import java.time.Clock
import java.time.LocalTime

class History(private val clock: Clock, gameObservable: GameObservable): GameObserver, SurvivorObserver {
    private val mutableList = mutableListOf<String>()

    val list
        get() = mutableList.toList()

    init {
        gameObservable.addObserver(this)
    }

    override fun notifyGameStart() {
        mutableList.add("Game started at ${LocalTime.now(clock).toShortFormat()}.")
    }

    override fun notifyGameEnd() {
        mutableList.add("Game has ended.")
    }

    override fun notifySurvivorAdded(survivor: Survivor) {
        mutableList.add("""Survivor "${survivor.name}" has been added to the game.""")
        survivor.addObserver(this)
    }

    override fun notifyLevelUp(level: LevelType) {
        mutableList.add("""Game has levelled up to "${level}".""")
    }

    override fun notifyDead(survivor: Survivor) {
        mutableList.add("""Survivor "${survivor.name}" has died.""")
    }

    override fun notifyLevel(survivor: Survivor) {}

    override fun notifyAddEquipment(survivor: Survivor, item: EquipmentType) {
        mutableList.add("""Survivor "${survivor.name}" has added "${item}" to its equipment.""")
    }

    override fun notifyWound(survivor: Survivor) {
        mutableList.add("""Survivor "${survivor.name}" has been wounded.""")
    }

    override fun notifyLevelChange(survivor: Survivor, level: LevelType) {
        mutableList.add("""Survivor "${survivor.name}" has levelled up to "${level}".""")
    }
}


