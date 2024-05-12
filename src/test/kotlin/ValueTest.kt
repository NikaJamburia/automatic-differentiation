import ge.nika.Value.Companion.asValue
import io.kotest.assertions.asClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ValueTest {

    @Test
    fun `can be added and multiplied`() {
        val a = 2.asValue()
        val b = (-3).asValue()
        val c = 10.asValue()

        val result = a*b + c
        result.data shouldBe 4.0
    }

    @Test
    fun `correctly assigns addition and multiplication operations to result`() {
        val a = 2.asValue()
        val b = (-3).asValue()
        val c = 10.asValue()

        val result = a*b + c
        result.isCalculated shouldBe true
        result.parentOperationName shouldBe "+"
        result.parents[0].asClue {
            it.data shouldBe -6.0
            it.isCalculated shouldBe  true
            it.parentOperationName shouldBe "*"
            it.parents[0] shouldBe a
            it.parents[1] shouldBe b
        }
        result.parents[1].asClue {
            it shouldBe c
            it.isCalculated shouldBe false
        }
    }
}