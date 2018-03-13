package alancasasarevalo.com.domain.model

interface ReadAggregate <out T> {
    fun elementCount() : Int
    fun getAllElements() : List<T>
    fun getElementByPosition(position: Int) : T
}

interface WriteAggregate <in T> {
    fun addElement(element: T)
    fun deleteElementByPosition(position: Int)
    fun deleteElement(element: T)
}

interface Aggregate <T> : ReadAggregate <T> , WriteAggregate <T>