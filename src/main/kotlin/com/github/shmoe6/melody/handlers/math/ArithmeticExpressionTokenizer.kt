package com.github.shmoe6.melody.handlers.math

object ArithmeticExpressionTokenizer {

    // 1. input: string of terminal symbols
    // 2. output: array containing each token
    // ex: (3k + 4) * 10k as input produces ["(", "3", "k", "+", "4", ")", "*", "10", "k"] for an output

    //val tokens = [[""]]

    fun generateTokens(s: String): ArrayDeque<String> {

        val inputWithoutSpaces = s.replace(" ", "")
        val tokens = ArrayDeque<String>()

        inputWithoutSpaces.forEach {
            if (it.toString().matches(Regex("[0-9()+\\-*/%.kmb]"))) {
                tokens.addLast(it.toString())
            }
        }
        tokens.addLast("END")

        return tokens
    }
}