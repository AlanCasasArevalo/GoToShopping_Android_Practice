package alancasasarevalo.com.domain.interactors.deleteallelements

import alancasasarevalo.com.activitytype.CodeClosure
import alancasasarevalo.com.activitytype.ErrorClosure
import alancasasarevalo.com.repository.repository.RepositoryImplementation
import android.content.Context
import java.lang.ref.WeakReference

class DeleteAllElementsImplementation (context: Context) : DeleteAllElementsInteractorInterface {
    var weakContext = WeakReference<Context>(context)

    override fun execute(successClosure: CodeClosure, errorClosure: ErrorClosure) {
        val repository = RepositoryImplementation(weakContext.get()!!)

        repository.deleteAllElements(successClosure, errorClosure)
    }
}