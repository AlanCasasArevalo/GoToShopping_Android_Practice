package alancasasarevalo.com.domain.interactors.deleteallelements

import alancasasarevalo.com.activitytype.CodeClosure
import alancasasarevalo.com.activitytype.ErrorClosure

interface DeleteAllElementsInteractorInterface  {
    fun execute(successClosure: CodeClosure, errorClosure: ErrorClosure)
}



