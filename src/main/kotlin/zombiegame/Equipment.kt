package zombiegame

const val MAX_EQUIPMENT = 5
class Equipment {
    private val items = mutableListOf<EquipmentType>()
    val amount: Int
        get() = this.items.size

    fun add(item: EquipmentType) {
        items.add(item)
    }
}

enum class EquipmentType {
    BASEBALL_BAT,
    FRYING_PAN,
    KATANA,
    PISTOL,
    BOTTLED_WATER,
    MOLOTOV,
}