package com.github.shmoe6.melody.handlers.math

object ArithmeticExpressionParser {

    fun valueOfExpr(tokens: ArrayDeque<String>): Double {

        // term
        var value = valueOfTerm(tokens)

        while (tokens.first().matches(Regex("[+-]"))) {
            // + term
            if (tokens.first() == "+") {
                tokens.removeFirst()
                value += valueOfTerm(tokens)

                // - term
            } else if (tokens.first() == "-") {
                tokens.removeFirst()
                value -= valueOfTerm(tokens)
            }
        }


        return value
    }

    private fun valueOfTerm(tokens: ArrayDeque<String>): Double {

        // factor
        var value = valueOfFactor(tokens)

        while (tokens.first().matches(Regex("[*/%]"))) {
            // * factor
            if (tokens.first() == "*") {
                tokens.removeFirst() // remove "*"
                value *= valueOfFactor(tokens)

                // / factor
            } else if (tokens.first() == "/") {
                tokens.removeFirst() // remove "/"
                value /= valueOfFactor(tokens)

                // % factor
            } else if (tokens.first() == "%") {
                tokens.removeFirst() // remove "%"
                value %= valueOfFactor(tokens)
            }
        }

        return value
    }

    private fun valueOfFactor(tokens: ArrayDeque<String>): Double {

        var value: Double

        // (expr)
        if (tokens.first() == "(") {
            tokens.removeFirst() // remove "("
            value = valueOfExpr(tokens)
            tokens.removeFirst() // remove ")"

            // |
        } else {
            // digit-seq
            val leftSide = valueOfDigitSequence(tokens)

            // .digit-seq
            if (tokens.first() == ".") {
                tokens.removeFirst() // remove "."
                value = "$leftSide.${valueOfDigitSequence(tokens)}".toDouble()

                // nondecimal case
            } else {
                value = leftSide.toDouble()
            }

            // suffix: handles both cases
            if (tokens.first().matches(Regex("[kmb]"))) {
                value *= valueOfSuffix(tokens)
            }
        }

        return value
    }

    private fun valueOfDigitSequence(tokens: ArrayDeque<String>): String {

        var value = valueOfDigit(tokens)

        if (tokens.first().matches(Regex("[0-9]"))) {
            value += valueOfDigitSequence(tokens)
        }

        return value
    }

    private fun valueOfSuffix(tokens: ArrayDeque<String>): Double {
        return when (tokens.removeFirst()) {
            "k" -> 1000.0
            "m" -> 1000000.0
            "b" -> 1000000000.0
            else -> 1.0
        }
    }

    private fun valueOfDigit(tokens: ArrayDeque<String>): String {
        return tokens.removeFirst()
    }
}