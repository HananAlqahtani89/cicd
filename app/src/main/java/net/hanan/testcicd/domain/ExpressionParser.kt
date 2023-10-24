package net.hanan.testcicd.domain

class ExpressionParser(
    private val calculator: String
) {
    fun parse(): List<ExpressionPart> {
        val result = mutableListOf<ExpressionPart>()
        var i = 0
        while (i < calculator.length) {
            val curChar = calculator[i]
            when {
                curChar in operationSymbols -> {
                    result.add(
                        ExpressionPart.Op(operationFromSymbol(curChar))
                    )
                }

                curChar.isDigit() -> {
                    i = parseNumber(i, result)
                    continue
                }

                curChar in "()" -> {
                    parsePareentheses(curChar, result)
                }
            }
            i++
        }
        return result
    }

    private fun parseNumber(
        startingIndex: Int,
        result: MutableList<ExpressionPart>
    ): Int {
        var i = startingIndex
        val numberAsString = buildString {
            while (i < calculator.length && calculator[i] in "0123456789.") {
                append(calculator[i])
                i++
            }
        }
        result.add(ExpressionPart.Number(numberAsString.toDouble()))
        return i
    }

    private fun parsePareentheses(
        curChar: Char,
        result: MutableList<ExpressionPart>
    ) {
        result.add(
            ExpressionPart.Parentheses(
                type = when (curChar) {
                    '(' -> ParenthesesTypes.Opening
                    ')' -> ParenthesesTypes.Closing
                    else -> throw IllegalArgumentException("Invalid Parentheses type")
                }
            )
        )
    }
}