package io.rybalkinsd.kotlinbootcamp.practice

/**
 * NATO phonetic alphabet
 */
val alphabet = setOf("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliett", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "Xray", "Yankee", "Zulu")

/**
 * A mapping for english characters to phonetic alphabet.
 * [ a -> Alfa, b -> Bravo, ...]
 */
val association: Map<Char, String> = alphabet.associateBy { it[0].toLowerCase() }

/**
 * Extension function for String which encode it according to `association` mapping
 *
 * @return encoded string
 *
 * Example:
 * "abc".encode() == "AlfaBravoCharlie"
 *
 */
fun String.encode(): String = map { it.toLowerCase() }. map { association[it] ?: it }.joinToString("")

/**
 * A reversed mapping for association
 * [ alpha -> a, bravo -> b, ...]
 */
val reversedAssociation: Map<String, Char> = alphabet.associate { it to it[0].toLowerCase() }

/**
 * Extension function for String which decode it according to `reversedAssociation` mapping
 *
 * @return encoded string or null if it is impossible to decode
 *
 * Example:
 * "alphabravocharlie".decode() == "abc"
 * "charliee".decode() == null
 *
 */
fun String.decode(): String? {
    if (this.isEmpty()) return ""
    if (this[0] in '0'..'9' || this[0] == ' ') {
        val tail = this.substring(1).decode()
        return this[0] + (tail ?: return null)
    }
    val lowerStr = this.toLowerCase()
    val foo: String = reversedAssociation.keys.fold("") { foo, it ->
                        if (lowerStr.indexOf(it.toLowerCase()) == 0) foo + reversedAssociation.get(it).toString()
                        else foo + "" }
    if (foo == "") return null
    val res = lowerStr.substring(association[foo[0]]!!.length).decode()
    return foo + (res ?: return null)
}