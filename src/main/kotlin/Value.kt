package ge.nika

import ge.nika.operations.Addition
import ge.nika.operations.Multiplication
import ge.nika.operations.ValueOperation


data class Value(
    val data: Double,
    private val parentOperation: ValueOperation?,
    var label: String? = null
) {

    var gradient: Double = 0.0
        private set

    companion object {
        fun Number.asValue(label: String? = null): Value =
            Value(
                data = this.toDouble(),
                parentOperation = null,
                label = label
            )
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

    operator fun minus(other: Value): Value = this + (-other)

    operator fun unaryMinus(): Value = this * (-1).asValue()

    fun propagateGradientsBackward() {
        if (parentOperation != null) {
            parentOperation.assignGradients(this)
            parentOperation.operands.forEach { it.propagateGradientsBackward() }
        }
    }

    fun incrementGradientBy(double: Double): Double {
        gradient += double
        return gradient
    }

    val isCalculated: Boolean
        get() = parentOperation != null

    val parents: List<Value>
        get() = parentOperation?.operands ?: emptyList()

    val parentOperationName: String
        get() = parentOperation.toString()

    override fun toString(): String = "Value($data)"

}