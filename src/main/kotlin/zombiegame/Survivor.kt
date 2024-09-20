const val MAX_WOUNDS = 2
const val MAX_ACTIONS = 3

class Survivor(val name: String) {
    var wounds: Int = 0
        private set
    var dead: Boolean = false
        private set
    var actionCount: Int = 0
        private set

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
        if (wounds >= MAX_WOUNDS) {
            dead = true
        }
    }
}