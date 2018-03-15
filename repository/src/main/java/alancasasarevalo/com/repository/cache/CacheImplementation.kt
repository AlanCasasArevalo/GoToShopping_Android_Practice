package alancasasarevalo.com.repository.cache

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.activitytype.DispatchOnMainThread
import alancasasarevalo.com.repository.DBHelper
import alancasasarevalo.com.repository.buildDBHelper
import alancasasarevalo.com.repository.database.dao.EntityDAO
import alancasasarevalo.com.repository.model.EntityModel
import android.content.Context
import android.util.Log
import java.lang.ref.WeakReference

internal class CacheImplementation(context: Context) : CacheInterface {

    val weakContext = WeakReference<Context>(context)
    val dataBase = cacheDBHelper()

    override fun getAllActivities(success: (activities: List<EntityModel>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var activities = EntityDAO(dataBase).queryListElement()

            DispatchOnMainThread(Runnable {

                if (activities.count() > 0) {
                    success(activities)
                } else {
                    error("No hay actividades")
                }

            })
            dataBase.close()

        }).run()
    }

    override fun getAllActivitiesForType(type: ActivityType, success: (activities: List<EntityModel>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {

            var activities = EntityDAO(dataBase).queryType(type)
            DispatchOnMainThread(Runnable {

                if (activities.count() > 0) {
                    success(activities)
                } else {
                    error("No hay actividades")
                }

            })
            dataBase.close()

        }).run()
    }

    override fun saveAllActivities(type: ActivityType, activities: List<EntityModel>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {

            try {
                activities.forEach {
                    EntityDAO(dataBase).insertElement(type, it)
                }

                DispatchOnMainThread(Runnable {
                    success()
                })

            } catch (exeption: Exception) {
                DispatchOnMainThread(Runnable {
                    error("Error al guardar en la base de datos ${exeption.localizedMessage}")
                })
            }
            dataBase.close()

        }).run()

    }

    override fun deleteAllActivities(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var deleted = EntityDAO(dataBase).deleteAllElementList()

            DispatchOnMainThread(Runnable {
                if (deleted) {
                    success()
                    Log.d("ActividadesBorradas", "Se han borrado todas las actividades")
                    dataBase.close()
                } else {
                    error("Error al borrar la base de datos")
                    dataBase.close()
                }

            })

        }).run()
    }

    private fun cacheDBHelper(): DBHelper {
        return buildDBHelper(weakContext.get()!!, "MadridShops.sqlite", 1)
    }
}
































