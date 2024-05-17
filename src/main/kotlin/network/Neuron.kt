package ge.nika.network

import ge.nika.Value
import ge.nika.Value.Companion.asValue
import ge.nika.operations.Tanh.Companion.tanh
import kotlin.math.absoluteValue
import kotlin.random.Random

class Neuron(
    private val numberOfInputs: Int,
    weights: List<Value>? = null,
    bias: Value? = null,
) {

    init {
        require((weights?.size ?: numberOfInputs) == numberOfInputs) {
            "Number of given weights must equal numer of inputs!"
        }
    }

    private var weights: List<Value> = weights
        ?: (1..numberOfInputs).map { randomValue(-1.0, 1.0, "WEIGHT") }
    private var bias: Value = bias ?: randomValue(-1.0, 1.0, "BIAS")

    fun forwardPass(inputs: List<Value>): Value {
        require(inputs.size == numberOfInputs) {
            "Number of given inputs must equal predefined number!"
        }

        val weightedSum = weights.zip(inputs)
            .map { (weight, input) -> weight * input }
            .sum()
        val activation = weightedSum + bias
        return activation.tanh()
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


    private fun randomValue(
        from: Double,
        to: Double,
        label: String? = null
    ): Value = Random.nextDouble(from,to).asValue(label)
}
