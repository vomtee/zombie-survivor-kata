package zombiegame

class Equipment : EquipmentHolder {
    val inHands = InHands()
    val inReserve = InReserve()
    override val amount: Int
        get() = this.inHands.amount + this.inReserve.amount

    override val maxAmount: Int
        get() = this.inHands.maxAmount + this.inReserve.maxAmount

    override fun addItemOperation(item: EquipmentType): Boolean =
        if (inHands.add(item)) true else inReserve.add(item)
}

interface EquipmentHolder {
    val amount: Int
    val maxAmount: Int
    fun addItemOperation(item: EquipmentType): Boolean
}

class InHands : EquipmentHolder {
    private val items = mutableListOf<EquipmentType>()
    override val amount: Int
        get() = items.size
    override val maxAmount: Int
        get() = 2

    override fun addItemOperation(item: EquipmentType): Boolean {
        return items.add(item)
    }
}

class InReserve : EquipmentHolder {
    private val items = mutableListOf<EquipmentType>()
    override val amount: Int
        get() = items.size
    override val maxAmount: Int
        get() = 3

    override fun addItemOperation(item: EquipmentType): Boolean {
        return items.add(item)
    }
}

fun EquipmentHolder.add(item: EquipmentType): Boolean =
    if (amount < maxAmount) addItemOperation(item) else false


enum class EquipmentType {
    ANYTHING,
}