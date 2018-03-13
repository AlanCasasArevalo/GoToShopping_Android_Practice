package alancasasarevalo.com.domain.interactors.networkingstatus

import alancasasarevalo.com.activitytype.CodeClosure
import alancasasarevalo.com.activitytype.ErrorClosure
import android.content.Context

interface InternetStatusInteractor {
    fun execute(context: Context, success: CodeClosure, error: ErrorClosure)
}
