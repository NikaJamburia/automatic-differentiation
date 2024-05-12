package ge.nika.operations

import ge.nika.Value
import kotlin.math.exp

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
        TODO("Not yet implemented")
    }

    override fun toString(): String  = "tanh"
}