
const val NOT_SPECIFIED_CITY = "не указан"
const val NOT_SPECIFIED_AGE = "не указан"
const val NOT_SPECIFIED_NAME = "не указано"

fun formatName(raw: String?): String =
    raw
        ?.trim()
        ?.takeIf { it.isNotEmpty() }
        ?.replaceFirstChar { it.titlecase() }
        ?: NOT_SPECIFIED_NAME


fun parseAge(raw: String?): Int? =
    raw
        ?.trim()
        ?.toIntOrNull()
        ?.takeIf { it in 0..150 }

fun formatCity(raw: String?): String =
    raw
        ?.trim()
        ?.replace(Regex("\\s+"), " ")
        ?.takeIf { it.isNotEmpty() }
        ?: NOT_SPECIFIED_CITY

fun getStatus(age: Int?): String = when {
    age == null -> "недостаточно данных"
    age < 18 -> "несовершеннолетний"
    age in 18..60 -> "взрослый пользователь"
    else -> "пожилой пользователь"
}

fun buildReport(name: String, age: Int?, city: String, status: String): String {
    val ageText = age?.toString() ?: NOT_SPECIFIED_AGE   // ?.

    return """
        Профиль пользователя:
        Имя: $name
        Возраст: $ageText
        Город: $city

        Статус: $status
    """.trimIndent()
}

fun main() {
    print("Имя: ")
    val rawName = readlnOrNull()

    print("Возраст: ")
    val rawAge = readlnOrNull()

    print("Город: ")
    val rawCity = readlnOrNull()

    val name = formatName(rawName)
    val age = parseAge(rawAge)
    val city = formatCity(rawCity)
    val status = getStatus(age)

    println()
    println(buildReport(name, age, city, status))
}