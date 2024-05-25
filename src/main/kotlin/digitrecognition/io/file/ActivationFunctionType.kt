package ge.nika.digitrecognition.io.file

import ge.nika.operations.ActivationFunction

enum class ActivationFunctionType {
    TANH, SIGMOID
}

fun ActivationFunction.toActivationFunctionType(): ActivationFunctionType = when(this) {
    ActivationFunction.Tanh -> ActivationFunctionType.TANH
    ActivationFunction.Sigmoid -> ActivationFunctionType.SIGMOID
}

fun ActivationFunctionType.toActivationFunction(): ActivationFunction = when(this) {
    ActivationFunctionType.TANH -> ActivationFunction.Tanh
    ActivationFunctionType.SIGMOID -> ActivationFunction.Sigmoid
}