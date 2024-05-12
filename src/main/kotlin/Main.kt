package ge.nika

import ge.nika.operations.Tanh.Companion.tanh
import ge.nika.Value.Companion.asValue
import ge.nika.visualisation.drawToSvg

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
   // inputs
   val x1 = 2.asValue("x1")
   val x2 = 0.asValue("x2")

   // Weights
   val w1 = (-3).asValue("w1")
   val w2 = 1.asValue("w2")

   // bias
   val b = (6.7).asValue("b")

   //sum
   val x1w1 = x1 * w1; x1w1.label = "x1w1"
   val x2w2 = x2 * w2; x2w2.label = "x2w2"
   val inputsSum = x1w1 + x2w2; inputsSum.label = "inputsSum"
   val output = (inputsSum + b).tanh(); output.label = "output"

   output.gradient = 1.0
//   output.propagateGradientsBackward()
   drawToSvg(output)
}