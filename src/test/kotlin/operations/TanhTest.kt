package operations

import ge.nika.Value.Companion.asValue
import ge.nika.operations.Tanh.Companion.tanh
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TanhTest {

    @ParameterizedTest
    @CsvSource(
        "2.0,    0.9640275800758169",
        "30.0,   1.0",
        "4.0,    0.999329299739067",
        "-14.0, -0.9999999999986171",
        "-2.0,  -0.9640275800758168",
    )
    fun `tanh of a given value is correctly calculated`(input: Double, output: Double) {
        val inputValue = input.asValue()
        val result = inputValue.tanh()

        result.data shouldBe output
        result.isCalculated shouldBe true
        result.parentOperationName shouldBe "tanh"
        result.parents shouldBe listOf(inputValue)
    }

    @Test
    fun `correctly assigns gradient`() {
        val originalValue = 0.8813735801954321.asValue()
        val tanhResult = originalValue.tanh()
        tanhResult.incrementGradientBy(1.0)
        tanhResult.propagateGradientsBackward()

        originalValue.gradient shouldBe 0.5000000048253751
    }

}