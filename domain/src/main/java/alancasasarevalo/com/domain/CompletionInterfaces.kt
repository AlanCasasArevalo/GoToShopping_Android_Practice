package alancasasarevalo.com.domain


interface SuccessCompletion <in T>{
    fun successCompletion(elements: T )
}

interface ErrorCompletion {
    fun errorCompletion( errorMessage: String)
}


