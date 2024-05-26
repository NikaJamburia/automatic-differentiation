package ge.nika.digitrecognition.io.file

import ge.nika.Value.Companion.asValue
import ge.nika.network.Neuron

data class NeuronFileDto(
    val activationFunctionType: ActivationFunctionType,
    val numberOfInputs: Int,
    val weights: List<Double>,
    val bias: Double,
) {
    companion object {
        fun Neuron.toFileDto(): NeuronFileDto = NeuronFileDto(
            activationFunctionType = activationFunction.toActivationFunctionType(),
            numberOfInputs = numberOfInputs,
            weights = weights.map { it.data },
            bias = bias.data
        )
    }
    
    fun toNeuron(): Neuron =
        Neuron.withPredefinedWeights(
            activationFunction = activationFunctionType.toActivationFunction(),
            numberOfInputs = numberOfInputs,
            weights = weights.map { it.asValue() },
            bias = bias.asValue(),
        )
}