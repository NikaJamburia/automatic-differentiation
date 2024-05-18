package ge.nika.operations

import ge.nika.Value
import ge.nika.Value.Companion.asValue

class Multiplication(
    private val firstOperand: Value,
    private val secondOperand: Value,
): ValueOperation {

    companion object {
        fun Value.squared(): Value =
            this * this.data.asValue()
    }

    override val operands: List<Value>
        get() = listOf(firstOperand, secondOperand)

    override fun assignGradients(operationResult: Value) {
        firstOperand.incrementGradientBy(secondOperand.data * operationResult.gradient)
        secondOperand.incrementGradientBy(firstOperand.data * operationResult.gradient)
    }

    override fun toString(): String = "*"
}