package alancasasarevalo.com.repository.network

import alancasasarevalo.com.activitytype.ErrorCompletion
import alancasasarevalo.com.activitytype.SuccessCompletion

interface GetJSONManagerInterface {
    fun execute(url: String, successCompletion: SuccessCompletion<String>, errorCompletion: ErrorCompletion)
}
