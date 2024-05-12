import ge.nika.Multiplication
import ge.nika.Value.Companion.asValue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MultiplicationTest {

    @Test
    fun `assigns gradients to the operands of multiplication if result is a forward pas root(grad = 1)`() {
        val value1 = 5.0.asValue()
        val value2 = 3.0.asValue()
        val result = 15.0.asValue(); result.gradient = 1.0

        val multiplication = Multiplication(value1, value2)
        multiplication.assignGradients(result)

        value1.gradient shouldBe 3.0
        value2.gradient shouldBe 5.0
    }

    @Test
    fun `assigns gradients to the operands and applies chain rule if result has its own gradient`() {
        val value1 = 5.0.asValue()
        val value2 = 3.0.asValue()
        val result = 15.0.asValue(); result.gradient = 0.5

        val multiplication = Multiplication(value1, value2)
        multiplication.assignGradients(result)

        value1.gradient shouldBe 1.5
        value2.gradient shouldBe 2.5
    }

    @Test
    fun `assigns 0 to both operands if result value has no gradient assigned`() {
        val value1 = 5.0.asValue()
        val value2 = 3.0.asValue()
        val result = 15.0.asValue()

        val multiplication = Multiplication(value1, value2)
        multiplication.assignGradients(result)

        value1.gradient shouldBe 0.0
        value2.gradient shouldBe 0.0
    }
}