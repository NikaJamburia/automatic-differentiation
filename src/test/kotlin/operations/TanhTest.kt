package operations

import ge.nika.Value
import ge.nika.Value.Companion.asValue
import ge.nika.operations.Tanh.Companion.tanh
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

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
}