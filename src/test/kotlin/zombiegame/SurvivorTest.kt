package zombiegame

import Survivor
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.beEmpty
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class SurvivorTest {
    lateinit var survivor: Survivor

    @BeforeEach
    fun setup() {
        survivor = Survivor(" Heinz")
    }

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
        survivor.act()
        survivor.act()
        survivor.act()

        survivor.act() shouldBe false
    }

    @Test
    fun `survivor should be able to perform actions again with the next turn`() {
        survivor.act()
        survivor.act()
        survivor.act()
        survivor.act() shouldBe false

        survivor.nextTurn()
        survivor.act() shouldBe true

    }

    @Test
    fun `survivor initially carries nothing` () {
        survivor.equipment.amount shouldBe 0
    }

    @Test
    fun `survivor can carry 5 items` () {
        repeat(5) {
            survivor.equipment.add(EquipmentType.ANYTHING)
        }
        survivor.equipment.amount shouldBe 5
    }

    @Test
    fun `survivor can carry not more than 5 items` () {
        repeat(6) {
            survivor.equipment.add(EquipmentType.ANYTHING)
        }
        survivor.equipment.amount shouldBe 5
    }

}