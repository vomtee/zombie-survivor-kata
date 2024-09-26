package zombiegame

import java.time.Clock
import java.time.LocalTime

class History(private val clock: Clock, gameObservable: GameObservable): GameObserver {
    private val mutableList = mutableListOf<String>()

    val list
        get() = mutableList.toList()

    init {
        gameObservable.addObserver(this)
    }

    override fun notifyGameStart() {
        mutableList.add("Game started at ${LocalTime.now(clock).toShortFormat()}.")
    }
}


