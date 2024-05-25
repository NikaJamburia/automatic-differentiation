package digitrecognition.io

import ge.nika.Value

data class DigitRecognitionTrainingParams(
    val inputLayer: List<Value>,
    val expectedOutput: DigitRecognitionExpectedOutput,
)