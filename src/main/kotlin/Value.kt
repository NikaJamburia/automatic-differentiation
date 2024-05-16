package ge.nika

import ge.nika.operations.Addition
import ge.nika.operations.Multiplication
import ge.nika.operations.ValueOperation


data class Value(
    val data: Double,
    private val parentOperation: ValueOperation?,
    var gradient: Double = 0.0,
    var label: String? = null
) {

    companion object {
        fun Number.asValue(label: String? = null): Value =
            Value(
                data = this.toDouble(),
                parentOperation = null,
                label = label
            )

        fun List<Value>.sum(): Value = fold(0.asValue()) { acc, value -> acc + value }
    }

    operator fun plus(other: Value): Value {
        return Value(
            data = data + other.data,
            parentOperation = Addition(this, other)
        )
    }

    operator fun times(other: Value): Value {
        return Value(
            data = data * other.data,
            parentOperation = Multiplication(this, other)
        )
    }

    fun propagateGradientsBackward() {
        if (parentOperation != null) {
            parentOperation.assignGradients(this)
            parentOperation.operands.forEach { it.propagateGradientsBackward() }
        }

    }

    val isCalculated: Boolean
        get() = parentOperation != null

    val parents: List<Value>
        get() = parentOperation?.operands ?: emptyList()

    val parentOperationName: String
        get() = parentOperation.toString()

    override fun toString(): String = "Value($data)"


}