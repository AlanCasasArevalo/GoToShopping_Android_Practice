package alancasasarevalo.com.gotomadrid

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.activitytype.ErrorCompletion
import alancasasarevalo.com.activitytype.SuccessCompletion
import alancasasarevalo.com.domain.interactors.getallelements.GetAllActivitiesToDoInteractor
import alancasasarevalo.com.domain.interactors.getallelements.GetAllElementsInteractorImplementation
import alancasasarevalo.com.domain.model.ActivitiesToDo
import android.support.multidex.MultiDexApplication
import android.util.Log
import android.widget.Toast

class MyApp : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()

//        val allActivitiesAllActivities = GetAllElementsInteractorImplementation(this)
//        allActivitiesAllActivities.execute(object : SuccessCompletion<ActivitiesToDo> {
//            override fun successCompletion(elements: ActivitiesToDo) {
//                Log.d("MyApp ActivitiesToDo","Hemos obtenidos ${elements.activities.count()} actividades")
//            }
//        }, object : ErrorCompletion {
//            override fun errorCompletion(errorMessage: String) {
//            }
//
//        })
//
//        DeleteAllElementsImplementation(this)
//                .execute({
//                    Log.d("DeleteSuccess","Success")
//                },{
//                    Log.d("DeleteError", "Error al borrar las entidades $it")
//                })


        val allActivitiesToDo : GetAllActivitiesToDoInteractor = GetAllElementsInteractorImplementation(this)

        allActivitiesToDo.executeForType(ActivityType.ACTIVITY, successCompletion = object : SuccessCompletion<ActivitiesToDo> {
            override fun successCompletion(element: ActivitiesToDo) {
                element.activities.forEach {
                    Log.d("MyApp", "${it.name} y hemos devuelto  ${element.activities.size} elementos")
                }
            }

        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
            }
        })


    }

    override fun onLowMemory() {
        super.onLowMemory()

    }
}