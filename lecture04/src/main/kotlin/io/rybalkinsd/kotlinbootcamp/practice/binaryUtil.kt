package io.rybalkinsd.kotlinbootcamp.practice

import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.math.BigInteger

/**
 * "8".toBinary() == "1000"
 *
 * @throws NumberFormatException if could not be parsed as Number
 * @throws IllegalArgumentException if target string is null
 */
fun String?.toBinary(): String = when(this) {
    null -> throw IllegalArgumentException()
    else -> toBigInteger().toString(2)
}

/*this ?: throw IllegalArgumentException("err")
    var num:BigInteger = this.toBigInteger()
    var res = ""
    while (num > 0.toBigInteger()){ res += (num % 2.toBigInteger()).toString()
        num /= 2.toBigInteger()
    }
    return res.reversed()*/


