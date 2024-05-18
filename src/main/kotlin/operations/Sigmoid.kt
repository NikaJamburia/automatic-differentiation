package ge.nika.operations

import ge.nika.Value
import kotlin.math.exp

class Sigmoid(
    private val input: Value,
) : ValueOperation {

    companion object {
        fun Value.sigmoid(): Value =
            Value(
                data = 1 / (1.0 + exp(-data)),
                parentOperation = Sigmoid(this),
            )
    }

    override val operands: List<Value>
        get() = listOf(input)

    override fun assignGradients(operationResult: Value) {
        input.incrementGradientBy(
            (operationResult.data * (1 - operationResult.data)) * operationResult.gradient
        )
    }

    override fun toString(): String = "sigmoid"
}