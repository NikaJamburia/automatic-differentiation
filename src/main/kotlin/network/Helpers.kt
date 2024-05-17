package ge.nika.network

import ge.nika.Value
import ge.nika.Value.Companion.asValue
import ge.nika.operations.Multiplication.Companion.squared


fun List<List<Double>>.asInputDataSet(): List<List<Value>> = map { outer ->
    outer.map { inner -> inner.asValue("INPUT") }
}

fun List<Double>.asValues(): List<Value> = map { it.asValue() }
fun values(vararg doubles: Double): List<Value> = doubles.map { it.asValue() }

fun List<Value>.unwrapSingleValue(): Value = single()

fun loss(groundTruth: Value, prediction: Value): Value =
    (prediction - groundTruth).squared()
fun List<Value>.sum(): Value = fold(0.asValue()) { acc, value -> acc + value }
