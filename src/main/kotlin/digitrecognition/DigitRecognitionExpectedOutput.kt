package ge.nika.digitrecognition

import ge.nika.Value
import ge.nika.Value.Companion.asValue

data class DigitRecognitionExpectedOutput(
    val neuronValues: List<Value>
) {
    companion object {
        fun of(number: Number): DigitRecognitionExpectedOutput {
            require(number in 0..9) {
                "DigitRecognitionExpectedOutput can only be between 0 and 9"
            }

            val zeroList = mutableListOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
            zeroList[number.toInt()] = 1.0
            return DigitRecognitionExpectedOutput(zeroList.map { it.asValue() })
        }
    }

    init {
        require(neuronValues.size == 10) {
            "Size of the output should be 10!"
        }
    }
}
