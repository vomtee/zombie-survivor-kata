package zombiegame

private const val MAX_IN_HAND_AMOUNT = 2
private const val STANDARD_MAX_IN_RESERVE_AMOUNT = 3
private const val UPGRADED_MAX_IN_RESERVE_AMOUNT = 4

enum class EquipmentType {
    BASEBALL_BAT,
    FRYING_PAN,
    KATANA,
    PISTOL,
    BOTTLED_WATER,
//    MOLOTOV,
}

interface EquipmentHolder {
    val amount: Int
    val maxAmount: Int
    val content: List<EquipmentType>
    fun add(item: EquipmentType): Boolean
}

interface SizableEquipmentHolder : EquipmentHolder {
    fun decreaseMaxAmount(): EquipmentType?
    fun upgradeMaxAmount()
}

class Equipment : SizableEquipmentHolder {
    val inHands: EquipmentHolder = InHands()
    val inReserve: SizableEquipmentHolder = InReserve()

    override val amount: Int
        get() = this.inHands.amount + this.inReserve.amount

    override val maxAmount: Int
        get() = this.inHands.maxAmount + this.inReserve.maxAmount

    override val content: List<EquipmentType>
        get() = inHands.content + inReserve.content

    override fun add(item: EquipmentType): Boolean =
        if (inHands.add(item))
            true
        else
            inReserve.add(item)

    override fun decreaseMaxAmount(): EquipmentType? =
        inReserve.decreaseMaxAmount()

    override fun upgradeMaxAmount() =
        inReserve.upgradeMaxAmount()

}

abstract class EquipmentList : EquipmentHolder {
    protected val items = mutableListOf<EquipmentType>()

    override val amount: Int
        get() = items.size

    override val content: List<EquipmentType>
        get() = items.toList()

    override fun add(item: EquipmentType): Boolean =
        if (amount < maxAmount) items.add(item) else false
}

class InHands : EquipmentList() {
    override val maxAmount: Int = MAX_IN_HAND_AMOUNT
}

class InReserve : EquipmentList(), SizableEquipmentHolder {
    override var maxAmount: Int = STANDARD_MAX_IN_RESERVE_AMOUNT
    private var wasUpgraded = false

    override fun decreaseMaxAmount(): EquipmentType? {
        if (maxAmount == 0) {
            return null
        }

        var result: EquipmentType? = null
        val lowerMaxAmount = maxAmount - 1

        if (amount > lowerMaxAmount) {
            result = items.removeLast()
        }

        maxAmount = lowerMaxAmount
        return result
    }

    override fun upgradeMaxAmount() {
        if (wasUpgraded) {
            return
        }
        wasUpgraded = true

        maxAmount = if (maxAmount == STANDARD_MAX_IN_RESERVE_AMOUNT) UPGRADED_MAX_IN_RESERVE_AMOUNT
        else maxAmount + 1
    }


}

