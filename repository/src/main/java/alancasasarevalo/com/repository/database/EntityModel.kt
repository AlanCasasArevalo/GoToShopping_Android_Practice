package alancasasarevalo.com.repository.database

data class EntityModel(
        val databaseId: Long,
        val id: Long,
        val name: String,

        val img: String,
        val logo: String,
        val address: String,

        val url: String,
        val email: String,
        val telephone: String,

        val description_en: String,
        val description_es: String,
        val description_jp: String,
        val description_cn: String,

        val latitude: String,
        val longitude: String,
        val openingHoursEn: String,
        val openingHoursEs: String,
        val openingHoursJp: String,
        val openingHoursCn: String,

        val type: String? = null
)

