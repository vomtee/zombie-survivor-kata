package zombiegame

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.beEmpty
import org.junit.jupiter.api.Test
import zombiegame.EquipmentType.BASEBALL_BAT
import zombiegame.EquipmentType.BOTTLED_WATER
import zombiegame.EquipmentType.FRYING_PAN
import zombiegame.EquipmentType.KATANA
import zombiegame.EquipmentType.PISTOL
import zombiegame.LevelType.Blue
import zombiegame.LevelType.Red
import zombiegame.LevelType.Yellow
import zombiegame.SkillType.HOARD
import zombiegame.SkillType.PLUS_ONE_ACTION

class SurvivorTest {
    private val survivor: Survivor = Survivor("Local Horst")

    @Test
    fun `survivor has a name`() {
        survivor.name shouldNot beEmpty()
    }

    @Test
    fun `survivor has no wounds`() {
        survivor.wounds shouldBe 0
    }

    @Test
    fun `survivor should be alive`() {
        survivor.dead shouldNotBe true
    }

    @Test
    fun `survivor should be alive after receiving 1 wound`() {
        survivor.wound()
        survivor.dead shouldNotBe true
    }

    @Test
    fun `survivor with 2 wounds should be dead`() {
        survivor.wound()
        survivor.wound()
        survivor.dead shouldBe true
    }

    @Test
    fun `survivor should ignore receiving more than 2 wounds`() {
        survivor.wound()
        survivor.wound()
        survivor.wound()

        survivor.dead shouldBe true
        survivor.wounds shouldBe 2
    }

    @Test
    fun `survivor should initially be able to perform 3 actions`() {
        var canAct = true
        canAct = canAct && survivor.act()
        canAct = canAct && survivor.act()
        canAct = canAct && survivor.act()

        canAct shouldBe true
    }

    @Test
    fun `survivor cannot perform more than 3 actions`() {
        repeat(3) {
            survivor.act()
        }

        survivor.act() shouldBe false
    }

    @Test
    fun `survivor with skill 'plus one action' can perform 4 actions`() {
        repeat(7) {
            survivor.killZombie()
        }

        survivor.skills shouldContain PLUS_ONE_ACTION

        repeat(3) {
            survivor.act()
        }

        survivor.act() shouldBe true
    }

    @Test
    fun `survivor should be able to perform actions again with the next turn`() {
        repeat(3) {
            survivor.act()
        }

        survivor.act() shouldBe false

        survivor.nextTurn()
        survivor.act() shouldBe true
    }

    @Test
    fun `equipment should lose one item when max amount is less then amount of items in equipment `() {
        val equipment = survivor.equipment
        equipment.add(BASEBALL_BAT)
        equipment.add(FRYING_PAN)
        equipment.add(KATANA)
        equipment.add(PISTOL)
        equipment.add(BOTTLED_WATER)

        survivor.wound()
        equipment.content shouldBe listOf(BASEBALL_BAT, FRYING_PAN, KATANA, PISTOL)
        survivor.wound()
        equipment.content shouldBe listOf(BASEBALL_BAT, FRYING_PAN, KATANA)
        survivor.wound()
        equipment.content shouldBe listOf(BASEBALL_BAT, FRYING_PAN, KATANA)
    }

    @Test
    fun `survivor can carry one more item when unlocking hoard skill`() {
        val equipment = survivor.equipment

        var wasAdded = true
        repeat(5) {
            wasAdded = wasAdded && equipment.add(BOTTLED_WATER)
        }
        wasAdded shouldBe true

        equipment.add(BOTTLED_WATER) shouldBe false

        repeat(2 * Red.experience) {
            survivor.killZombie()
        }
        Score.levelAndSkill(survivor.experience) shouldBe Pair(Red, 2)
        survivor.skills shouldContain HOARD

        equipment.add(BOTTLED_WATER) shouldBe true
    }

    @Test
    fun `survivor begins with 0 experience and level blue`() {
        survivor.experience shouldBe 0
        survivor.level shouldBe Blue
    }

    @Test
    fun `survivor killing a zombie gains 1 experience `() {
        survivor.killZombie()
        survivor.experience shouldBe 1
    }

    @Test
    fun `survivor killing 7 zombies levels up `() {
        survivor.level shouldBe Blue
        repeat(7) {
            survivor.killZombie()
        }

        survivor.level shouldBe Yellow
    }
}