package net.hanan.testcicd.domain

sealed interface ExpressionPart {
    data class Number(val number: Double) : ExpressionPart
    data class Op(val operation: Operation) : ExpressionPart
    data class Parentheses(val type: ParenthesesTypes) : ExpressionPart
}

sealed interface ParenthesesTypes {
    object Opening : ParenthesesTypes
    object Closing : ParenthesesTypes
}