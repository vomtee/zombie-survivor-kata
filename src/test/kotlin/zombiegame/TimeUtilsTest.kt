package zombiegame

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import zombiegame.TimeUtils.Companion.FIXED_CLOCK

class TimeUtilsTest {
    @Test
    fun `fixed clock short format should be twelve hours and 34 minutes`() {
        FIXED_CLOCK.toShortFormat() shouldBe "12:34"
    }
}