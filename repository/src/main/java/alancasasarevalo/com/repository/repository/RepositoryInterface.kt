package alancasasarevalo.com.repository.repository

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.repository.model.EntityModel

interface RepositoryInterface{
    fun getAllElements(successCompletion: (element: List<EntityModel>) -> Unit, errorCompletion: (errorMessage: String) -> Unit)
    fun getAllActivitiesForType(type: ActivityType, success: (shoptivities: List<EntityModel>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllElements(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit)
}