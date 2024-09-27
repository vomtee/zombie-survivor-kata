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

    override fun notifySurvivorAdded(survivor: Survivor) {
        mutableList.add("""Survivor "${survivor.name}" has been added to the game.""")
        survivor.addObserver(this)
    }

    override fun notifyDead(survivor: Survivor) {
        TODO("Not yet implemented")
    }

    override fun notifyLevel(survivor: Survivor) {
        TODO("Not yet implemented")
    }

    override fun notifyAddEquipment(survivor: Survivor, item: EquipmentType) {
        mutableList.add("""Survivor "${survivor.name}" has added "${item}" to its equipment.""")
    }
}


