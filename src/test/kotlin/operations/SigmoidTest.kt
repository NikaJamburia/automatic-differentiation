package operations

import ge.nika.Value.Companion.asValue
import ge.nika.operations.Sigmoid.Companion.sigmoid
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SigmoidTest {

    @ParameterizedTest
    @CsvSource(
        "2.0,   0.8807970779778823",
        "-10.0, 0.000045397868702434395",
        "6.0,   0.9975273768433653",
        "0.25,  0.5621765008857981",
    )
    fun `sigmoid of a given value can correctly be calculated`(input: Double, output: Double) {
        val result = input.asValue().sigmoid()

        result.data shouldBe output
        result.isCalculated shouldBe true
        result.parents shouldBe listOf(input.asValue())
        result.parentOperationName shouldBe "sigmoid"
    }

    @ParameterizedTest
    @CsvSource(
        "2.0,  0.10499358540350662",
        "3.0,  0.045176659730912",
        "4.0,  0.017662706213291107",
        "0.25, 0.24613408273759835",
        "-2.0, 0.1049935854035065",
        "-3.0, 0.04517665973091214",
        "-4.0, 0.017662706213291118",
        "0.0,  0.25",
    )
    fun `sigmoids derivative can be calculated and assigned to the input`(input: Double, expectedGradient: Double) {
        val inputValue = input.asValue()
        val sigmoidResult = inputValue.sigmoid()

        sigmoidResult.incrementGradientBy(1.0)
        sigmoidResult.propagateGradientsBackward()

        inputValue.gradient shouldBe expectedGradient
    }

    @Test
    fun `sigmoids derivative is multiplied by results gradient and resulting gradient is assigned to the input`() {
        val inputValue = 0.0.asValue()
        val sigmoidResult = inputValue.sigmoid()

        sigmoidResult.incrementGradientBy(2.0)
        sigmoidResult.propagateGradientsBackward()

        inputValue.gradient shouldBe 0.5
    }
}