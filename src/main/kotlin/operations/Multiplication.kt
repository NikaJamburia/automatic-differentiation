package ge.nika.operations

import ge.nika.Value

class Multiplication(
    private val firstOperand: Value,
    private val secondOperand: Value,
): ValueOperation {

    override val operands: List<Value>
        get() = listOf(firstOperand, secondOperand)

    override fun assignGradients(operationResult: Value) {
        firstOperand.gradient += secondOperand.data * operationResult.gradient
        secondOperand.gradient += firstOperand.data * operationResult.gradient
    }

    override fun toString(): String = "*"
}