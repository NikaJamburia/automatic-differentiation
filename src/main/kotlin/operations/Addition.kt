package ge.nika.operations

import ge.nika.Value

class Addition(
    private val firstOperand: Value,
    private val secondOperand: Value,
): ValueOperation {
    override val operands: List<Value>
        get() = listOf(firstOperand, secondOperand)

    override fun assignGradients(operationResult: Value) {
        firstOperand.gradient += operationResult.gradient
        secondOperand.gradient += operationResult.gradient
    }

    override fun toString(): String = "+"
}