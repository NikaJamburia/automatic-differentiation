package ge.nika.network

import ge.nika.Value

data class NeuronParameters(
    val weights: List<Value>,
    val biases: List<Value>,
)