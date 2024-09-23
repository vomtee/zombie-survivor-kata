package zombiegame

private const val MAX_IN_HAND_AMOUNT = 2
private const val MAX_IN_RESERVE_AMOUNT = 3

enum class EquipmentType {
    BASEBALL_BAT,
    FRYING_PAN,
    KATANA,
    PISTOL,
    BOTTLED_WATER,
    MOLOTOV,
}

interface EquipmentHolder {
    val amount: Int
    val maxAmount: Int
    val content: List<EquipmentType>
    fun addItemOperation(item: EquipmentType): Boolean
}

interface ShrinkableEquipmentHolder : EquipmentHolder {
    fun decreaseMaxAmount(): EquipmentType?
}

fun EquipmentHolder.add(item: EquipmentType): Boolean =
    if (amount < maxAmount) addItemOperation(item) else false

class Equipment : EquipmentHolder {
    val inHands: EquipmentHolder = InHands()
    val inReserve: ShrinkableEquipmentHolder = InReserve()

    override val amount: Int
        get() = this.inHands.amount + this.inReserve.amount

    override val maxAmount: Int
        get() = this.inHands.maxAmount + this.inReserve.maxAmount

    override val content: List<EquipmentType>
        get() = inHands.content + inReserve.content

    override fun addItemOperation(item: EquipmentType): Boolean =
        if (inHands.add(item))
            true
        else
            inReserve.add(item)
}

abstract class EquipmentList : EquipmentHolder {
    protected val items = mutableListOf<EquipmentType>()

    override val amount: Int
        get() = items.size

    override val content: List<EquipmentType>
        get() = items.toList()

    override fun addItemOperation(item: EquipmentType): Boolean =
        items.add(item)
}

class InHands : EquipmentList() {
    override val maxAmount: Int = MAX_IN_HAND_AMOUNT

}

class InReserve : EquipmentList(), ShrinkableEquipmentHolder {
    override var maxAmount: Int = MAX_IN_RESERVE_AMOUNT

    override fun decreaseMaxAmount(): EquipmentType? {
        var result: EquipmentType? = null

        val lowerMaxAmount = maxAmount - 1
        if (amount > lowerMaxAmount) {
            result = items.removeLast()
        }

        maxAmount = lowerMaxAmount
        return result
    }
}

