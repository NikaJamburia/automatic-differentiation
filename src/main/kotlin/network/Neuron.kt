package ge.nika.network

import ge.nika.Value
import ge.nika.Value.Companion.asValue
import ge.nika.operations.ActivationFunction
import ge.nika.operations.Tanh.Companion.tanh
import kotlin.math.absoluteValue
import kotlin.random.Random

class Neuron private constructor(
    val numberOfInputs: Int,
    val activationFunction: ActivationFunction,
    weights: List<Value>,
    bias: Value,
) {

    companion object {
        fun withRandomWeights(
            numberOfInputs: Int,
            activationFunction: ActivationFunction,
        ): Neuron = Neuron(
            numberOfInputs = numberOfInputs,
            activationFunction = activationFunction,
            weights = (1..numberOfInputs).map { randomValue(-1.0, 1.0, "WEIGHT") },
            bias = randomValue(-1.0, 1.0, "BIAS")
        )

        fun withPredefinedWeights(
            numberOfInputs: Int,
            activationFunction: ActivationFunction,
            weights: List<Value>,
            bias: Value,
        ): Neuron = Neuron(
            numberOfInputs = numberOfInputs,
            activationFunction = activationFunction,
            weights = weights,
            bias = bias
        )
    }

    init {
        require(weights.size == numberOfInputs) {
            "Number of given weights must equal number of inputs!"
        }
    }

    var weights: List<Value> = weights
        private set
    var bias: Value = bias
        private set

    fun forwardPass(inputs: List<Value>): Value {
        require(inputs.size == numberOfInputs) {
            "Number of given inputs must equal predefined number!"
        }

        val weightedSum = weights.zip(inputs)
            .map { (weight, input) -> weight * input }
            .sum() + bias
        return activationFunction.activate(weightedSum)
    }

    fun adjustParameters(learningRate: Double) {
        val newWeights = weights.map {
            it.adjustToDecreaseLoss(learningRate)
        }
        val newBias = bias.adjustToDecreaseLoss(learningRate)

        weights = newWeights
        bias = newBias
    }

    private fun Value.adjustToDecreaseLoss(learningRate: Double): Value {
        val negRate = -(learningRate.absoluteValue)
        return (data + (negRate * gradient)).asValue(label)
    }
}

fun randomValue(
    from: Double,
    to: Double,
    label: String? = null
): Value = Random.nextDouble(from,to).asValue(label)
