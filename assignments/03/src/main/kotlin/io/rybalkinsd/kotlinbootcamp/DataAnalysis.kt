package io.rybalkinsd.kotlinbootcamp

class RawProfile(val rawData: String)

enum class DataSource {
    FACEBOOK,
    VK,
    LINKEDIN
}

sealed class Profile(
    var id: Long,
    var firstName: String? = null,
    var lastName: String? = null,
    var age: Int? = null,
    var dataSource: DataSource
)

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
 * TODO
 */
val avgAge: Map<DataSource, Double> = emptyMap()

/**
 * Task #3
 * Group all user ids together with all profiles of this user.
 * We can assume users equality by : firstName & lastName & age
 *
 * TODO
 */
val groupedProfiles: Map<Long, List<Profile>> = emptyMap()

fun parse(s: String){
    Profile(1, if (s.contains("firstName")) s.substringAfter("firstName=").substringBefore(",") else null,
            if (s.contains("lastName")) s.substringAfter("lastName=").substringBefore(",") else null ,
            if (s.contains("age")) s.substringAfter("age=").substringBefore(",").toInt() else null,DataSource.LINKEDIN )
    DataSource.valueOf("VK")
    println(s.substringAfter("source=").substringBefore(",").toUpperCase())
    println(s.substringAfter("age=").substringBefore(",").toInt())
    println(s.substringAfter("fisrtName=").substringBefore(","))
    if (s.contains("lastName")) s.substringAfter("lastName=").substringBefore(",") else null )
    println(s.substringAfter("lastName=").substringBefore("\n").substringBefore(","))

}

fun main(vararg : Array<String>){
    parse(rawProfiles[0].rawData)
}


fun rawProfilesParse(profiles: List<String>) : List<Profile> {
    var a = listOf{profiles.forEach {
        //when(values)
    }}
    return emptyList()
}
/**
 * Here are Raw profiles to analyse
 */
val rawProfiles = listOf(
    RawProfile("""
            fisrtName=alice,
            age=16,
            source=facebook
            """.trimIndent()
    ),
    RawProfile("""
            fisrtName=Dent,
            lastName=kent,
            age=88,
            source=linkedin
            """.trimIndent()
    ),
    RawProfile("""
            fisrtName=alla,
            lastName=OloOlooLoasla,
            source=vk
            """.trimIndent()
    ),
    RawProfile("""
            fisrtName=bella,
            age=36,
            source=vk
            """.trimIndent()
    ),
    RawProfile("""
            fisrtName=angel,
            age=I will not tell you =),
            source=facebook
            """.trimIndent()
    ),

    RawProfile(
        """
            lastName=carol,
            source=vk
            age=49,
            """.trimIndent()
    ),
    RawProfile("""
            fisrtName=bob,
            lastName=John,
            age=47,
            source=linkedin
            """.trimIndent()
    ),
    RawProfile("""
            lastName=kent,
            fisrtName=dent,
            age=88,
            source=facebook
            """.trimIndent()
    )
)
