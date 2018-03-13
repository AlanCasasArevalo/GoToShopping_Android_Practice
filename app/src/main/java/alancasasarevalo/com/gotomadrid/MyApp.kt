package alancasasarevalo.com.gotomadrid

import alancasasarevalo.com.domain.ErrorCompletion
import alancasasarevalo.com.domain.GetAllActivitiesToDoInteractorFakeImplementation
import alancasasarevalo.com.domain.SuccessCompletion
import alancasasarevalo.com.domain.model.ActivitiesToDo
import android.support.multidex.MultiDexApplication
import android.util.Log

class MyApp : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()

        val allActivitiesToDo = GetAllActivitiesToDoInteractorFakeImplementation()
        allActivitiesToDo.execute(object : SuccessCompletion<ActivitiesToDo> {
            override fun successCompletion(elements: ActivitiesToDo) {
                Log.d("MyApp ActivitiesToDo","Hemos obtenidos ${elements.activities.count()} actividades")
            }
        }, object : ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                // TODO: Do something with errors
            }

        })
    }

    override fun onLowMemory() {
        super.onLowMemory()

    }
}