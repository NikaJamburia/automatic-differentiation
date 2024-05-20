package ge.nika.digitrecognition

import ge.nika.Value

data class MnistTrainingParams(
    val inputLayer: List<Value>,
    val expectedOutput: List<Value>,
)