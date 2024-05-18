package operations

import ge.nika.operations.Addition
import ge.nika.Value.Companion.asValue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AdditionTest {

    @Test
    fun `assigns 1 as gradient to the operands of addition if result is a forward pas root(grad = 1)`() {
        val value1 = 5.0.asValue()
        val value2 = 3.0.asValue()
        val result = 8.0.asValue(); result.incrementGradientBy(1.0)

        val addition = Addition(value1, value2)
        addition.assignGradients(result)

        value1.gradient shouldBe 1
        value2.gradient shouldBe 1
    }

    @Test
    fun `assigns 1 as gradient to the operands and applies chain rule if result has its own gradient`() {
        val value1 = 5.0.asValue()
        val value2 = 3.0.asValue()
        val result = 8.0.asValue(); result.incrementGradientBy(0.2)

        val addition = Addition(value1, value2)
        addition.assignGradients(result)

        value1.gradient shouldBe 0.2
        value2.gradient shouldBe 0.2
    }
}