package alancasasarevalo.com.domain

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.domain.model.ActivitiesToDo


interface GetAllActivitiesToDoInteractor {
    fun execute(successCompletion: SuccessCompletion<ActivitiesToDo>, error:ErrorCompletion)
    fun executeForType(type: ActivityType, successCompletion: SuccessCompletion<ActivitiesToDo>, error: ErrorCompletion)
}