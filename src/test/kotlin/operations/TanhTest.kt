package operations

import ge.nika.Value
import ge.nika.Value.Companion.asValue
import ge.nika.operations.Tanh.Companion.tanh
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.math.round

class TanhTest {

    @Test
    fun `tanh of a given value is correctly calculated`() {
        val inputsAndOutputs: Map<Value, Double> = mapOf(
            2.asValue() to 0.9640275800758169,
            30.asValue() to 1.0,
            4.asValue() to 0.999329299739067,
            (-14).asValue() to -0.9999999999986171,
            (-2).asValue() to -0.9640275800758168,
        )

        inputsAndOutputs.forEach { (input, output) ->
            val result = input.tanh()
            println(input)
            result.data shouldBe output
            result.isCalculated shouldBe true
            result.parentOperationName shouldBe "tanh"
            result.parents shouldBe listOf(input)
        }
    }

    @Test
    fun `correctly assigns gradient`() {
        val originalValue = 0.8813735801954321.asValue()
        val tanhResult = originalValue.tanh()
        tanhResult.gradient = 1.0
        tanhResult.propagateGradientsBackward()

        originalValue.gradient shouldBe 0.5000000048253751
    }

}