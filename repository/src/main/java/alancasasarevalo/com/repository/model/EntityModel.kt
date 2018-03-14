package alancasasarevalo.com.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class EntityModel(
        val databaseId: Long,
        val id: Long,
        val name: String,

        val img: String,
        @JsonProperty("logo_img") val logo: String,
        val address: String,

        val url: String,
        val email: String,
        val telephone: String,

        val description_en: String,
        val description_es: String,
        val description_jp: String,
        val description_cn: String,

        @JsonProperty("gps_lat") val latitude: String,
        @JsonProperty("gps_lon") val longitude: String,
        @JsonProperty("opening_hours_en") val openingHoursEn: String,
        @JsonProperty("opening_hours_es") val openingHoursEs: String,
        @JsonProperty("opening_hours_jp") val openingHoursJp: String,
        @JsonProperty("opening_hours_cn") val openingHoursCn: String,

        val type: String? = null
)






















