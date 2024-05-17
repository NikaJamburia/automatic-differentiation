package ge.nika.network

import ge.nika.Value
import ge.nika.Value.Companion.asValue
import ge.nika.operations.Multiplication.Companion.squared


fun List<List<Double>>.asInputDataSet(): List<List<Value>> = map { outer ->
    outer.map { inner -> inner.asValue() }
}

fun List<Value>.unwrapSingleValue(): Value = single()

fun loss(groundTruths: List<Value>, predictions: List<Value>): List<Value> = groundTruths
    .zip(predictions)
    .map { (groundTruth, prediction) ->
        (prediction - groundTruth).squared()
    }

fun List<Value>.sum(): Value = fold(0.asValue()) { acc, value -> acc + value }
