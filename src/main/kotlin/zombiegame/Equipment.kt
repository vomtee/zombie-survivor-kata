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

fun EquipmentHolder.add(item: EquipmentType): Boolean =
    if (amount < maxAmount) addItemOperation(item) else false

abstract class EquipmentList : EquipmentHolder {
    private val items = mutableListOf<EquipmentType>()
    override val amount: Int
        get() = items.size

    override fun addItemOperation(item: EquipmentType): Boolean {
        return items.add(item)
    }
}

private const val MAX_IN_HAND_AMOUNT = 2

class InHands : EquipmentList() {
    override val maxAmount: Int
        get() = MAX_IN_HAND_AMOUNT
}

private const val MAX_IN_RESERVE_AMOUNT = 3

class InReserve : EquipmentList() {
    override val maxAmount: Int
        get() = MAX_IN_RESERVE_AMOUNT
}

enum class EquipmentType {
    ANYTHING,
}