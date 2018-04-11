package alancasasarevalo.com.repository.repository

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.activitytype.ErrorCompletion
import alancasasarevalo.com.activitytype.SuccessCompletion
import alancasasarevalo.com.repository.cache.CacheImplementation
import alancasasarevalo.com.repository.cache.CacheInterface
import alancasasarevalo.com.repository.model.EntityModel
import alancasasarevalo.com.repository.model.EntityResponse
import alancasasarevalo.com.repository.network.GetJSONManagerInterface
import alancasasarevalo.com.repository.network.GetJSONManagerVolleyImplementation
import alancasasarevalo.com.repository.network.json.JsonEntitiesParser
import android.content.Context
import android.widget.Toast
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import java.lang.ref.WeakReference

class RepositoryImplementation(context: Context) : RepositoryInterface {

    private var weakContext = WeakReference<Context>(context)
    private var cache: CacheInterface = CacheImplementation(weakContext.get()!!)

    override fun getAllElements(successCompletion: (element: List<EntityModel>) -> Unit, errorCompletion: (errorMessage: String) -> Unit) {

        cache.getAllActivities(success = {
            successCompletion(it)
        }, error = {
            populateCache(ActivityType.SHOP, successCompletion, errorCompletion)
            populateCache(ActivityType.ACTIVITY, successCompletion, errorCompletion)
        })
    }

    override fun getAllActivitiesForType(type: ActivityType, success: (activities: List<EntityModel>) -> Unit, error: (errorMessage: String) -> Unit) {
        cache.getAllActivitiesForType(type, success = {
            success(it)
        }, error = {
            // TODO: Hacer que el usuario se entere del error
            populateCache(ActivityType.SHOP, success, error)
            populateCache(ActivityType.ACTIVITY, success, error)
        })
    }

    override fun deleteAllElements(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit) {
        cache.deleteAllActivities(success = {
            successCompletion
        }, error = {
            errorCompletion
        })
    }

    private fun populateCache(activityToDo: ActivityType, successCompletion: (element: List<EntityModel>) -> Unit, errorCompletion: (errorMessage: String) -> Unit) {

        val jsonManager: GetJSONManagerInterface = GetJSONManagerVolleyImplementation(weakContext.get()!!)

        var url: String = ""

        url = when (activityToDo) {
            ActivityType.SHOP -> {
                "http://madrid-shops.com/json_new/getShops.php"
            }

            ActivityType.ACTIVITY -> {
                "http://madrid-shops.com/json_new/getActivities.php"
            }
            else -> ""
        }

        jsonManager.execute(url, successCompletion = object : SuccessCompletion<String> {
            override fun successCompletion(elements: String) {
                val parser = JsonEntitiesParser()
                val responseEntity: EntityResponse

                responseEntity = try {
                    parser.parse(elements)
                }catch (error: InvalidFormatException){
                    // TODO:Ponerlo en cristiano para el usuario final
                    error("Error al parsear")
                    return
                }

                responseEntity.result

                cache.saveAllActivities(activityToDo, responseEntity.result, success = {
                    successCompletion(responseEntity.result)
                }, error = {
                    // TODO:Ponerlo en cristiano para el usuario final
                    error("Algo fue mal al gravar y parsear las entidades $it")
                })
            }
        }, errorCompletion = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(weakContext.get(), errorMessage, Toast.LENGTH_LONG).show()
            }
        })


    }

}
