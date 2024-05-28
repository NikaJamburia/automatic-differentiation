package digitrecognition.io

import ge.nika.Value
import ge.nika.Value.Companion.asValue
import ge.nika.network.sum
import ge.nika.operations.Multiplication.Companion.squared

data class DigitRecognitionExpectedOutput(
    val expectedNeuronValues: List<Value>
) {
    companion object {
        private val DIGITS: Array<Array<Double>> = arrayOf(
            arrayOf(1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
            arrayOf(0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0),
            arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0),
        )
        fun ofFloat(number: Float): DigitRecognitionExpectedOutput {
            require(number in 0f..9f) {
                "DigitRecognitionExpectedOutput can only be between 0 and 9"
            }

            val array = DIGITS[number.toInt()]
            return DigitRecognitionExpectedOutput(array.map { it.asValue() })
        }
    }

    init {
        require(expectedNeuronValues.size == 10) {
            "Size of the output should be 10!"
        }
    }

    fun calculateLoss(output: List<Value>): Value =
        expectedNeuronValues.zip(output).map { (expectedValue, receivedValue) ->
            (expectedValue - receivedValue).squared()
        }.sum() * (1.0/20).asValue()
}
