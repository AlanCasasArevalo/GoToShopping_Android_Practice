package alancasasarevalo.com.repository.database

import alancasasarevalo.com.activitytype.ActivityType
import android.database.Cursor

interface DAOReadOperations<out T> {
    fun queryWithId(id: Long): T
    fun queryListElement(): List<T>
    fun queryType(type: ActivityType): List<T>
    fun queryCursor(id: Long): Cursor
}

interface DAOWriteOperations<in T> {
    fun insertElement(type: ActivityType, element: T) : Long
    fun updateElementById( id: Long, element: T ) : Long
    fun deleteElement (element: T) : Long
    fun deleteElementById (id: Long) : Long
    fun deleteAllElementList() : Boolean
}

interface DAOPersistable <T> : DAOReadOperations<T>, DAOWriteOperations<T>
