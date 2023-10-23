package net.hanan.testcicd.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `Simple expression is properly parsed`() {
        //1. Given
        parser = ExpressionParser("3+5-3x4/3")

        //2. Do
        val actual = parser.parse()

        //Assert
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with parratheses is properly parsed`() {
        parser = ExpressionParser("4-(4x5)")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ParenthesesTypes.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesTypes.Closing)
        )

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with double number`() {
        parser = ExpressionParser("4.5-(4.5x5.3)")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(4.5),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ParenthesesTypes.Opening),
            ExpressionPart.Number(4.5),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.3),
            ExpressionPart.Parentheses(ParenthesesTypes.Closing)
        )

        assertThat(expected).isEqualTo(actual)
    }
}