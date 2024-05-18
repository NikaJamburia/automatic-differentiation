package ge.nika.operations

import ge.nika.Value
import ge.nika.operations.Sigmoid.Companion.sigmoid
import ge.nika.operations.Tanh.Companion.tanh

sealed class ActivationFunction {
    abstract fun activate(value: Value): Value

    data object Tanh : ActivationFunction() {
        override fun activate(value: Value): Value = value.tanh()
    }

    data object Sigmoid : ActivationFunction() {
        override fun activate(value: Value): Value = value.sigmoid()
    }
}


