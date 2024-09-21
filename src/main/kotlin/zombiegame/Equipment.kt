package zombiegame

const val MAX_EQUIPMENT = 5
class Equipment : EquipmentHolder {
    private val items = mutableListOf<EquipmentType>()
    override val amount: Int
        get() = this.items.size

    override val maxAmount: Int
        get() = MAX_EQUIPMENT

    override fun add(item: EquipmentType): Boolean {
        return items.add(item)
    }
}

interface EquipmentHolder {
    val amount: Int
    val maxAmount: Int
    fun add(item: EquipmentType) : Boolean
}

enum class EquipmentType {
    ANYTHING,
}