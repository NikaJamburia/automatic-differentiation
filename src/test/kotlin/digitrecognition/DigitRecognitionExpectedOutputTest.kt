package digitrecognition

import ge.nika.Value.Companion.asValue
import ge.nika.digitrecognition.params.DigitRecognitionExpectedOutput
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DigitRecognitionExpectedOutputTest {

    @Test
    fun `can only contain 10 neuron values`() {
        shouldThrow<IllegalArgumentException> {
            DigitRecognitionExpectedOutput(
                listOf(
                    0.asValue(),
                    1.asValue(),
                    2.asValue(),
                    3.asValue(),
                    4.asValue(),
                    5.asValue(),
                    6.asValue(),
                    7.asValue(),
                    8.asValue(),
                )
            )
        }.message shouldBe "Size of the output should be 10!"

        shouldThrow<IllegalArgumentException> {
            DigitRecognitionExpectedOutput(
                listOf(
                    0.asValue(),
                    1.asValue(),
                    2.asValue(),
                    3.asValue(),
                    4.asValue(),
                    5.asValue(),
                    6.asValue(),
                    7.asValue(),
                    8.asValue(),
                    9.asValue(),
                    10.asValue(),
                )
            )
        }.message shouldBe "Size of the output should be 10!"
    }

    @Test
    fun `of static method only accepts numbers from 0 to 9`() {
        shouldThrow<IllegalArgumentException> {
            DigitRecognitionExpectedOutput.of(-1)
        }.message shouldBe "DigitRecognitionExpectedOutput can only be between 0 and 9"

        shouldThrow<IllegalArgumentException> {
            DigitRecognitionExpectedOutput.of(10)
        }.message shouldBe "DigitRecognitionExpectedOutput can only be between 0 and 9"

        (0..9).forEach {
            shouldNotThrow<Exception> {
                DigitRecognitionExpectedOutput.of(it)
            }
        }
    }
}