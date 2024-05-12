package ge.nika

interface ValueOperation {
    val operands: List<Value>
    fun assignGradients(operationResult: Value)
    override fun toString(): String
}

class Multiplication(
    private val firstOperand: Value,
    private val secondOperand: Value,
): ValueOperation {
    override val operands: List<Value>
        get() = listOf(firstOperand, secondOperand)

    override fun assignGradients(operationResult: Value) {
        firstOperand.gradient = secondOperand.data * operationResult.gradient
        secondOperand.gradient = firstOperand.data * operationResult.gradient
    }


    override fun toString(): String = "*"
}

class Addition(
    private val firstOperand: Value,
    private val secondOperand: Value,
): ValueOperation {
    override val operands: List<Value>
        get() = listOf(firstOperand, secondOperand)

    override fun assignGradients(operationResult: Value) {
        firstOperand.gradient = operationResult.gradient
        secondOperand.gradient = operationResult.gradient
    }

    override fun toString(): String = "+"
}