package zombiegame

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AdvancementTest {
    @Test
    fun `when experience is beyond 43 the survivor advances to the next round of levels`() {
        advancement(44) shouldBe(2)
    }

    @Test
    fun `when experience is beyond 2 times 43 the survivor advances to the next round of levels`() {
        advancement(2 * 43 + 1) shouldBe(3)
    }
}