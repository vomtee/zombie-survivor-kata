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
    fun `equipment initially is empty nothing`() {
        equipment.amount shouldBe 0
    }

    @Test
    fun `equipment can hold 5 items`() {
        repeat(5) {
            equipment.add(BASEBALL_BAT)
        }

        equipment.amount shouldBe 5
    }

    @Test
    fun `equipment can hold not more than 5 items`() {
        repeat(6) {
            equipment.add(BASEBALL_BAT)
        }

        equipment.amount shouldBe 5
    }

    @Test
    fun `upgraded equipment can hold 6 items` () {
        equipment.upgradeMaxAmount()

        repeat(6) {
            equipment.add(BASEBALL_BAT)
        }

        equipment.amount shouldBe 6
    }

    @Test
    fun `equipment holds 2 items in hand and 3 items in reserve`() {
        repeat(5) {
            equipment.add(BASEBALL_BAT)
        }

        equipment.inHands.amount shouldBe 2
        equipment.inReserve.amount shouldBe 3
    }

    @Test
    fun `equipment holds 2 specific items in hand and 3 specific items in reserve`() {
        equipment.add(BASEBALL_BAT)
        equipment.add(FRYING_PAN)
        equipment.add(KATANA)
        equipment.add(PISTOL)
        equipment.add(BOTTLED_WATER)

        equipment.inHands.content shouldBeEqual listOf(BASEBALL_BAT, FRYING_PAN)
        equipment.inReserve.content shouldBeEqual listOf(KATANA, PISTOL, BOTTLED_WATER)
    }

    @Test
    fun `decreaseMaxAmount() should not remove items if InReserve holds 2 items`() {
        val reserve: SizableEquipmentHolder = InReserve()
        reserve.add(BASEBALL_BAT)
        reserve.add(FRYING_PAN)

        reserve.decreaseMaxAmount() shouldBe null
        reserve.maxAmount shouldBe 2

        reserve.content shouldBeEqual listOf(BASEBALL_BAT, FRYING_PAN)
    }

    @Test
    fun `decreaseMaxAmount() should remove 1 item if InReserve holds 3 items`() {
        val reserve: SizableEquipmentHolder = InReserve()
        reserve.add(BASEBALL_BAT)
        reserve.add(FRYING_PAN)
        reserve.add(KATANA)

        reserve.decreaseMaxAmount() shouldBe KATANA
        reserve.maxAmount shouldBe 2

        reserve.content shouldBeEqual listOf(BASEBALL_BAT, FRYING_PAN)
    }

    @Test
    fun `decreaseMaxAmount() should not be executed if maxAmount is already 0`() {
        val reserve: SizableEquipmentHolder = InReserve()
        reserve.add(BASEBALL_BAT)
        reserve.add(FRYING_PAN)

        reserve.decreaseMaxAmount() shouldBe null
        reserve.maxAmount shouldBe 2
        reserve.decreaseMaxAmount() shouldBe FRYING_PAN
        reserve.maxAmount shouldBe 1
        reserve.decreaseMaxAmount() shouldBe BASEBALL_BAT
        reserve.maxAmount shouldBe 0

        reserve.decreaseMaxAmount() shouldBe null
        reserve.maxAmount shouldBe 0

    }

    @Test
    fun `equipment should lose one item when max amount is less then amount of items in equipment`() {
        equipment.add(BASEBALL_BAT)
        equipment.add(FRYING_PAN)
        equipment.add(KATANA)
        equipment.add(PISTOL)
        equipment.add(BOTTLED_WATER)

        equipment.decreaseMaxAmount() shouldBe BOTTLED_WATER
        equipment.maxAmount shouldBe 4
    }
}