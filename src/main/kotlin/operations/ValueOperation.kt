package ge.nika.operations

import ge.nika.Value

interface ValueOperation {
    val operands: List<Value>
    fun assignGradients(operationResult: Value)
    override fun toString(): String
}