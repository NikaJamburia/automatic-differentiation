import ge.nika.Value.Companion.asValue
import ge.nika.network.sum
import ge.nika.operations.Multiplication.Companion.squared
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

    @Test
    fun `backpropagates gradients correctly if the value structure is 1 node deep`() {
        val a = 2.asValue()
        val b = 6.asValue()
        val result = a * b

        result.gradient = 1.0
        result.propagateGradientsBackward()

        a.gradient shouldBe 6.0
        b.gradient shouldBe 2.0
        result.gradient shouldBe 1
    }

    @Test
    fun `backpropagates correctly in case of deep structure`() {
        val a = 2.asValue()
        val b = (-3).asValue()
        val c = 10.asValue()
        val e = a * b
        val d = e + c
        val f = (-2).asValue()
        val result = d * f

        result.gradient = 1.0
        result.propagateGradientsBackward()

        result.gradient shouldBe 1.0

        f.gradient shouldBe 4.0
        d.gradient shouldBe -2.0
        e.gradient shouldBe -2.0
        c.gradient shouldBe -2.0
        b.gradient shouldBe -4.0
        a.gradient shouldBe 6.0
    }

    @Test
    fun `list of values can correctly be summed`() {
        val result = listOf(
            1.asValue(),
            2.asValue(),
            3.asValue(),
            4.asValue(),
        ).sum()

        result.data shouldBe 10
    }

    @Test
    fun `can be negated by multiplying itself by -1`() {
        val value = 2.asValue()
        val result = -value

        result.asClue {
            it.data shouldBe -2.0
            it.isCalculated shouldBe true
            it.parentOperationName shouldBe "*"
            it.parents[0] shouldBe value
            it.parents[1] shouldBe (-1).asValue()
        }
    }

    @Test
    fun `values can be subtracted from each other`() {
        val value1 = 3.asValue()
        val value2 = 5.asValue()

        val result = value1 - value2

        result.asClue {
            result.data shouldBe -2.0
            it.isCalculated shouldBe true
            it.parentOperationName shouldBe "+"
            it.parents[0] shouldBe value1
            it.parents[1].asClue { negatedOperand ->
                negatedOperand.data shouldBe -5.0
                negatedOperand.isCalculated shouldBe true
                negatedOperand.parentOperationName shouldBe "*"
                negatedOperand.parents[0] shouldBe value2
                negatedOperand.parents[1] shouldBe (-1).asValue()
            }
        }
    }

    @Test
    fun `value can be squared`() {
        val value = 2.asValue()
        val result = value.squared()

        result.asClue {
            result.data shouldBe 4.0
            result.isCalculated shouldBe true
            result.parentOperationName shouldBe "*"
            result.parents[0] shouldBe value
            result.parents[1] shouldBe value
        }
    }
}