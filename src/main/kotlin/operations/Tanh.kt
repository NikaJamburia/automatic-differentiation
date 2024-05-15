package ge.nika.operations

import ge.nika.Value
import kotlin.math.exp
import kotlin.math.pow

class Tanh(
    private val input: Value
): ValueOperation {

    companion object {
        fun Value.tanh(): Value {
            return Value(
                data = (exp(2 * data) - 1) / (exp(2 * data) + 1),
                parentOperation = Tanh(this),
            )
        }
    }

    override val operands: List<Value>
        get() = listOf(input)

    override fun assignGradients(operationResult: Value) {
        input.gradient = (1 - operationResult.data.pow(2)) * operationResult.gradient
    }

    override fun toString(): String  = "tanh"
}