package ge.nika.network

import ge.nika.Value
import ge.nika.Value.Companion.asValue
import ge.nika.Value.Companion.sum
import ge.nika.operations.Tanh.Companion.tanh
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

    private val weights: List<Value> = weights
        ?: (1..numberOfInputs).map { randomValue(-1.0, 1.0) }
    private val bias: Value = bias ?: randomValue(-1.0, 1.0)

    fun call(inputs: List<Value>): Value {
        require(inputs.size == numberOfInputs) {
            "Number of given inputs must equal predefined number!"
        }

        val weightedSum = weights.zip(inputs)
            .map { (weight, input) -> weight * input }
            .sum()
        val activation = weightedSum + bias
        return activation.tanh()
    }

    private fun randomValue(from: Double, to: Double): Value = Random.nextDouble(from,to).asValue()
}
