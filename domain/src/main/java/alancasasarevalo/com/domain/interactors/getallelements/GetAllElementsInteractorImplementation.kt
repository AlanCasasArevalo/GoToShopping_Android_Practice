package alancasasarevalo.com.domain.interactors.getallelements

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.activitytype.ErrorCompletion
import alancasasarevalo.com.activitytype.SuccessCompletion
import alancasasarevalo.com.domain.model.ActivitiesToDo
import alancasasarevalo.com.domain.model.ActivityToDo
import alancasasarevalo.com.repository.model.EntityModel
import alancasasarevalo.com.repository.repository.RepositoryImplementation
import alancasasarevalo.com.repository.repository.RepositoryInterface
import android.content.Context
import android.util.Log
import java.lang.ref.WeakReference

class GetAllElementsInteractorImplementation(context: Context) : GetAllActivitiesToDoInteractor {

    val weakContext = WeakReference<Context>(context)

    private val repository: RepositoryInterface = RepositoryImplementation(weakContext.get()!!)

    override fun execute(successCompletion: SuccessCompletion<ActivitiesToDo>, error: ErrorCompletion) {
        repository.getAllElements({
            Log.d("GetAllElementsIntImp", "Hemos recibido $it y tiene ${it.count()} elements")
            val activitiesToDo = entityMapper(it)
            successCompletion.successCompletion(activitiesToDo)
        }, {
            error("Ha habido un error al mapear las actividades $it")
        })
    }

    override fun executeForType(type: ActivityType, successCompletion: SuccessCompletion<ActivitiesToDo>, error: ErrorCompletion) {
        repository.getAllActivitiesForType(type,
                {
                    val activitiesToDo = entityMapper(it)
                    successCompletion.successCompletion(activitiesToDo)
                }, {
                    error("Hubo un error al recuperar las actividades y mapearlas $it")
                })
    }


    private fun entityMapper(list: List<EntityModel>): ActivitiesToDo {

        val tempList = ArrayList<ActivityToDo>()

        list.forEach {
            val activity = ActivityToDo(
                    it.id,
                    it.name,
                    it.img,
                    it.logo,
                    it.address,
                    it.url,
                    it.email,
                    it.telephone,
                    it.description_en,
                    it.description_es,
                    it.description_jp,
                    it.description_cn,
                    getCorrectCoordinateComponent(it.latitude),
                    getCorrectCoordinateComponent(it.longitude),
                    it.openingHoursEn,
                    it.openingHoursEs,
                    it.openingHoursJp,
                    it.openingHoursCn,
                    it.type.toString()
            )

            tempList.add(activity)
        }

        val activities = ActivitiesToDo(tempList)
        return activities
    }

    private fun getCorrectCoordinateComponent(coordinateComponent: String): String {
        var coordinate = 0.0f
        val s = coordinateComponent.replace(",", "")
        try {
            coordinate = java.lang.Float.parseFloat(s)
        } catch (e: Exception) {
            Log.d("ERROR CONVERTING", String.format("Can't convert %s", coordinateComponent))
        }
        return coordinate.toString()
    }
}