package zombiegame

import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.EquipmentType.BASEBALL_BAT
import zombiegame.EquipmentType.BOTTLED_WATER
import zombiegame.EquipmentType.FRYING_PAN
import zombiegame.EquipmentType.KATANA
import zombiegame.EquipmentType.PISTOL

class EquipmentTest {
    val equipment = Equipment()
    
    @Test
    fun `equipment initially is empty nothing` () {
        equipment.amount shouldBe 0
    }

    @Test
    fun `equipment can hold 5 items` () {
        repeat(5) {
            equipment.add(BASEBALL_BAT)
        }

        equipment.amount shouldBe 5
    }

    @Test
    fun `equipment can hold not more than 5 items` () {
        repeat(6) {
            equipment.add(BASEBALL_BAT)
        }

        equipment.amount shouldBe 5
    }

    @Test
    fun `equipment holds 2 items in hand and 3 items in reserve` () {
        repeat(5) {
            equipment.add(BASEBALL_BAT)
        }

        equipment.inHands.amount shouldBe 2
        equipment.inReserve.amount shouldBe 3
    }

    @Test
    fun `equipment holds 2 specific items in hand and 3 specific items in reserve` () {
        equipment.add(BASEBALL_BAT)
        equipment.add(FRYING_PAN)
        equipment.add(KATANA)
        equipment.add(PISTOL)
        equipment.add(BOTTLED_WATER)

        equipment.inHands.content shouldBeEqual listOf(BASEBALL_BAT, FRYING_PAN)
        equipment.inReserve.content shouldBeEqual listOf(KATANA, PISTOL, BOTTLED_WATER)
    }
}