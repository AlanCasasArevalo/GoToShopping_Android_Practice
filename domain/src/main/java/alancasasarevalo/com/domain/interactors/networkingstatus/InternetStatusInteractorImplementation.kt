package alancasasarevalo.com.domain.interactors.networkingstatus

import alancasasarevalo.com.activitytype.CodeClosure
import alancasasarevalo.com.activitytype.ErrorClosure
import android.content.Context
import android.net.ConnectivityManager


class InternetStatusInteractorImplementation : InternetStatusInteractor {
    override fun execute(context: Context, success: CodeClosure, error: ErrorClosure) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected){
            success()
        } else {
            error("Error trying to connect to internet")
        }
    }
}