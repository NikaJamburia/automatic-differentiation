package ge.nika

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

    }

    val isCalculated: Boolean
        get() = parentOperation != null

    val parents: List<Value>
        get() = parentOperation?.operands ?: emptyList()

    val parentOperationName: String
        get() = parentOperation.toString()

}