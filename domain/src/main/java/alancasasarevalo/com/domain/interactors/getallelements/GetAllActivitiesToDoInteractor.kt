package alancasasarevalo.com.domain.interactors.getallelements

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.domain.ErrorCompletion
import alancasasarevalo.com.domain.SuccessCompletion
import alancasasarevalo.com.domain.model.ActivitiesToDo


interface GetAllActivitiesToDoInteractor {
    fun execute(successCompletion: SuccessCompletion<ActivitiesToDo>, error: ErrorCompletion)
    fun executeForType(type: ActivityType, successCompletion: SuccessCompletion<ActivitiesToDo>, error: ErrorCompletion)
}