package io.rybalkinsd.kotlinbootcamp.assignments

/**
 * Returns the greatest of `int` values.
 *
 * @param values an argument. !! Assume values.length > 0. !!
 * @return the largest of values.
 */
fun max(values: List<Int>): Int = values.max() ?: 0

/**
 * Returns the sum of all `int` values.
 *
 * @param values an argument. Assume values.length > 0.
 * @return the sum of all values.
 */
fun sum(values: List<Int>): Long = values.sumByDouble { it.toDouble() }.toLong()