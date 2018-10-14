package io.rybalkinsd.kotlinbootcamp

import java.util.Random

fun LongRange.random() = Random().nextInt((endInclusive.toInt() + 1) - start.toInt()) + start

class RawProfile(val rawData: String)

enum class DataSource {
    FACEBOOK,
    VK,
    LINKEDIN
}

/**
 * Here are Raw profiles to analyse
 */
val rawProfiles = listOf(
        RawProfile("""
            firstName=alice,
            age=16,
            source=facebook
            """.trimIndent()
        ),
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            firstName=alla,
            lastName=OloOlooLoasla,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=bella,
            age=36,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=angel,
            age=I will not tell you =),
            source=facebook
            """.trimIndent()
        ),
        RawProfile(
                """
            lastName=carol,
            source=vk,
            age=49,
            """.trimIndent()
        ),
        RawProfile("""
            firstName=bob,
            lastName=John,
            age=47,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            lastName=kent,
            firstName=dent,
            age=88,
            source=facebook
            """.trimIndent()
        ),
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            source=linkedin
            """.trimIndent()
        )
)

sealed class Profile(
    var id: Long,
    var firstName: String? = null,
    var lastName: String? = null,
    var age: Int? = null,
    var dataSource: DataSource
) {
    override fun toString(): String {
        return "Profile(id=$id, firstName=$firstName, lastName=$lastName, age=$age, dataSource=$dataSource)"
    }
}

/**
 * Returns a list containing Profiles from RawProfile's data
 */
fun rawProfilesParse(source: List<RawProfile>): List<Profile> = source.map {
    var t = when (it.rawData.substringAfter("source=").substringBefore(",").toUpperCase()) {
        "LINKEDIN" -> LinkedInProfile((0L..100000L).random())
        "VK" -> VKProfile((0L..100000L).random())
        "FACEBOOK" -> FacebookProfile((0L..100000L).random())
        else -> throw Exception("Error in profile source")
    }
    if (it.rawData.contains("firstName")) t.firstName = it.rawData.substringAfter("firstName=").substringBefore(",").capitalize()
    if (it.rawData.contains("lastName")) t.lastName = it.rawData.substringAfter("lastName=").substringBefore(",").capitalize()
    try {
        if (it.rawData.contains("age")) t.age = it.rawData.substringAfter("age=").substringBefore(",").toInt()
    } catch (e: Exception) {}
    t
}

val profiles = rawProfilesParse(rawProfiles)
/**
 * Task #1
 * Declare classes for all data sources
 */
class FacebookProfile(id: Long) : Profile(dataSource = DataSource.FACEBOOK, id = id)
class VKProfile(id: Long) : Profile(dataSource = DataSource.VK, id = id)
class LinkedInProfile(id: Long) : Profile(dataSource = DataSource.LINKEDIN, id = id)
/**
 * Task #2
 * Find the average age for each datasource (for profiles that has age)
 *
 * In case when no age in all profiles - result is NaN
 */
val avgAge: Map<DataSource, Double> = avg(profiles)

fun avg(src: List<Profile>): Map<DataSource, Double> = src.groupBy({ it.dataSource }, { it.age ?: 0 }).map {
        it.key to (it.value.filter { it != 0 }.sum().toDouble() / it.value.filter { it != 0 }.size)
    }.toMap()

/**
 * Task #3
 * Group all user ids together with all profiles of this user.
 * We can assume users equality by : firstName & lastName & age
 *
 *
 */
val groupedProfiles: Map<Long, List<Profile>> = groupProfiles(profiles)

fun groupProfiles(src: List<Profile>): Map<Long, List<Profile>> = src.groupBy {
    it.age.toString() + it.lastName + it.firstName }.values.associateBy { (0L..1000000L).random() }