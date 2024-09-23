package zombiegame

import Survivor
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.beEmpty
import org.junit.jupiter.api.Test

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
    fun `survivor should be able to perform actions again with the next turn`() {
        repeat(3) {
            survivor.act()
        }

        survivor.act() shouldBe false

        survivor.nextTurn()
        survivor.act() shouldBe true

    }
}