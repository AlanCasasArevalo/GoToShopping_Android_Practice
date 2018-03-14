package alancasasarevalo.com.repository.network

import alancasasarevalo.com.activitytype.ErrorCompletion
import alancasasarevalo.com.activitytype.SuccessCompletion
import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.lang.ref.WeakReference

class GetJSONManagerVolleyImplementation (context: Context) : GetJSONManagerInterface {

    var weakContext: WeakReference<Context> = WeakReference(context)
    var requestQueue : RequestQueue? = null

    override fun execute(url: String, successCompletion: SuccessCompletion<String>, errorCompletion: ErrorCompletion) {

        val request = StringRequest(url, Response.Listener {
            Log.d("VOLLEY response", it)
            successCompletion.successCompletion(it)
        }, Response.ErrorListener {
            it.localizedMessage
        })

        requestQueue().add(request)
    }

    fun requestQueue() : RequestQueue {
        if (requestQueue != null){
            requestQueue = Volley.newRequestQueue(weakContext.get())
        }
        return requestQueue!!
    }


}
