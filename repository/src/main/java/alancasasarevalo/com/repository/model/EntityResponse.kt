package alancasasarevalo.com.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal class EntityResponse(
        val result: List<EntityModel>
)
