package com.github.shmoe6.melody.handlers.math


// Context-Free Grammar:
// expr 	 → term | term + term | term – term
// term 	 → factor | factor * factor | factor / factor | factor % factor
// factor 	 → (expr) | digit-seq | digit-seq suffix | digit-seq.digit-seq | digit-seq.digit-seq suffix
// digit-seq → digit | digit digit-seq
// suffix 	 → k | m | b
// digit 	 → 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

object ArithmeticExpressionHandler {

    private val tokenizer = ArithmeticExpressionTokenizer
    private val parser = ArithmeticExpressionParser

    fun handle(s: String): Double {
        val tokens = tokenizer.generateTokens(s)
        val value = parser.valueOfExpr(tokens)

        return value
    }
}