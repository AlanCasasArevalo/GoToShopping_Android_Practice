package alancasasarevalo.com.repository.cache

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.repository.model.EntityModel

internal interface CacheInterface {
    fun getAllActivities(success: (activities: List<EntityModel>) -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllActivitiesForType(type: ActivityType, success: (activities: List<EntityModel>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllActivities(type: ActivityType, activities: List<EntityModel>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllActivities(success: () -> Unit, error: (errorMessage: String) -> Unit)
}
