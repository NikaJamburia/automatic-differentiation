package digitrecognition

import ge.nika.Value.Companion.asValue
import ge.nika.digitrecognition.data.MnistDigit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MnistDigitTest {
    @Test
    fun `creates values out of original pixels and returns them as a list of inputs`() {
        val subject = MnistDigit(
            pixels = floatArrayOf(0.25f, 0.454f, 0.9965f),
            label = 5f,
        )

        val result = subject.toTrainingParams()

        result.inputLayer shouldBe listOf(
            0.25.asValue(),
            0.45399999618530273.asValue(),
            0.9965000152587891.asValue(),
        )
    }

    @ParameterizedTest
    @MethodSource("outputLayerTestSourceData")
    fun `converts label to the array of neuron activations`(label: Float, expectedList: List<Double>) {
        val subject = MnistDigit(
            pixels = floatArrayOf(0.25f, 0.454f, 0.9965f),
            label = label,
        )

        val result = subject.toTrainingParams()

        result.expectedOutput shouldBe expectedList.map { it.asValue() }
    }

    private fun outputLayerTestSourceData(): List<Arguments> {
        return listOf(
            Arguments.of("0", listOf(1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)),
            Arguments.of("1", listOf(0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)),
            Arguments.of("2", listOf(0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)),
            Arguments.of("3", listOf(0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)),
            Arguments.of("4", listOf(0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0)),
            Arguments.of("5", listOf(0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0)),
            Arguments.of("6", listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0)),
            Arguments.of("7", listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0)),
            Arguments.of("8", listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0)),
            Arguments.of("9", listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0)),
        )
    }
}