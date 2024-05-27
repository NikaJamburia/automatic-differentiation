package ge.nika.digitrecognition.data

import ge.nika.Value

data class FeedForwardResult (
    val guessedDigit: Int,
    val guessedActivations: List<Double>
) {
    companion object {
        fun from(mlpOutputLayer: List<Value>): FeedForwardResult {
            require(mlpOutputLayer.size == 10)

            val doubles = mlpOutputLayer.map(Value::data)
            val maxIndex = doubles.indexOf(doubles.max())

            return FeedForwardResult(
                guessedDigit = maxIndex,
                guessedActivations = doubles
            )
        }
    }
}