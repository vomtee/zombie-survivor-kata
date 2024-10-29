package zombiegame

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test
import zombiegame.Equipment.BASEBALL_BAT
import zombiegame.Equipment.BOTTLED_WATER
import zombiegame.Equipment.FRYING_PAN
import zombiegame.Equipment.KATANA
import zombiegame.Equipment.MOLOTOV
import zombiegame.Equipment.PISTOL

class SurvivorTest {

    @Test
    fun `iteration 01`() {
        val survivor = Survivor("Mark")
        assertEquals("Mark", survivor.name)
        assertTrue(survivor.alive)
        assertEquals(3, survivor.actions)
        assertEquals(0, survivor.wounds)

        survivor.receiveWound()

        assertTrue(survivor.alive)
        assertEquals(1, survivor.wounds)

        survivor.receiveWound()

        assertFalse(survivor.alive)
        assertEquals(2, survivor.wounds)


        survivor.receiveWound()

        assertFalse(survivor.alive)
        assertEquals(2, survivor.wounds)
    }

    @Test
    fun `iteration 02`() {
        val survivor = Survivor("Gleb")
        assertEquals(5, survivor.inventory.capacity)

        assertTrue(survivor.inventory.add(KATANA))
        assertEquals(1, survivor.inventory.items.size)

        assertTrue(survivor.inventory.add(PISTOL))
        assertEquals(2, survivor.inventory.items.size)

        assertTrue(survivor.inventory.add(FRYING_PAN))
        assertEquals(3, survivor.inventory.items.size)

        assertTrue(survivor.inventory.add(BASEBALL_BAT))
        assertEquals(4, survivor.inventory.items.size)

        assertTrue(survivor.inventory.add(BOTTLED_WATER))
        assertEquals(5, survivor.inventory.items.size)

        assertFalse(survivor.inventory.add(MOLOTOV))
        assertEquals(5, survivor.inventory.items.size)

        assertEquals(listOf(KATANA,  PISTOL, FRYING_PAN, BASEBALL_BAT, BOTTLED_WATER),
            survivor.inventory.items)

        assertFalse(survivor.inventory.putInHand(MOLOTOV))
        assertTrue(survivor.inventory.equipmentInHands.items.isEmpty())

        assertTrue(survivor.inventory.putInHand(Equipment.entries.first()))

    }
}
