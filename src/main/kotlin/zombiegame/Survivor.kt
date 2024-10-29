package zombiegame

private const val MAX_ACTIONS_PER_TURN = 3
private const val MINIMUM_INVENTORY_CAPACITY = 0
private const val MAX_INVENTORY_CAPACITY = 5

private const val MINIMUM_ACTIONS = 0

private const val INITIAL_WOUNDS = 0
private const val MAX_WOUNDS = 2

class Survivor(val name: String) {

    val inventory = Inventory()

    var wounds = INITIAL_WOUNDS
        private set

    var actions = MAX_ACTIONS_PER_TURN
        private set

    val alive
        get() = wounds < MAX_WOUNDS

    fun receiveWound() {
        if (wounds < MAX_WOUNDS) {
            inventory.reduceCapacity()
            wounds = wounds.inc()
        }
    }

    fun reduceActions() {
        actions = actions.inc().coerceAtLeast(MINIMUM_ACTIONS)
    }
}

class Inventory {

    private val inventory: MutableList<Equipment> = mutableListOf()

    val items: List<Equipment>
        get() = inventory
    val equipmentInHands = Hands()
    var capacity = MAX_INVENTORY_CAPACITY
        private set

    fun add(item: Equipment): Boolean {
        if (inventory.size < capacity) {
            return inventory.add(item)
        }
        return false
    }

    fun putInHand(item: Equipment): Boolean {
        if (inventory.contains(item)) {
            return equipmentInHands.takeInHand(item)
        }
        return false
    }

    fun removeFromHand(item: Equipment): Boolean = equipmentInHands.removeFromHand(item)

    fun reduceCapacity() {
        capacity = capacity.dec().coerceAtLeast(MINIMUM_INVENTORY_CAPACITY)
        if (inventory.size > capacity) {
            inventory.removeLastOrNull()?.apply {
                equipmentInHands.removeFromHand(this)
            }
        }
    }
}

class Hands {
    val items: List<Equipment>
        get() = handEquipment
    private val handEquipment: MutableList<Equipment> = mutableListOf()

    fun takeInHand(e: Equipment): Boolean {
        if (handEquipment.size < 2) {
            return handEquipment.add(e)
        }
        return false
    }

    fun removeFromHand(e: Equipment): Boolean = handEquipment.remove(e)
}

enum class Equipment {
    BASEBALL_BAT,
    FRYING_PAN,
    KATANA,
    PISTOL,
    BOTTLED_WATER,
    MOLOTOV
}