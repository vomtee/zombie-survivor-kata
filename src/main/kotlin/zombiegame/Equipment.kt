package zombiegame

const val MAX_EQUIPMENT = 5
class Equipment : EquipmentHolder {
    private val items = mutableListOf<EquipmentType>()
    override val amount: Int
        get() = this.items.size

    override val maxAmount: Int
        get() = MAX_EQUIPMENT

    override fun addItemOperation(item: EquipmentType): Boolean {
        return items.add(item)
    }
}

interface EquipmentHolder {
    val amount: Int
    val maxAmount: Int
    fun addItemOperation(item: EquipmentType): Boolean
}

fun EquipmentHolder.add(item: EquipmentType): Boolean {
    return if (amount < maxAmount) {
        addItemOperation(item)
    } else {
        false
    }
}

enum class EquipmentType {
    ANYTHING,
}