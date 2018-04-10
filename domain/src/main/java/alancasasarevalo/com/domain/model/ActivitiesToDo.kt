package alancasasarevalo.com.domain.model

import java.io.Serializable

class ActivitiesToDo (var activities: MutableList<ActivityToDo>) : Aggregate<ActivityToDo>, Serializable {

    override fun elementCount(): Int {
        return activities.size
    }

    override fun getAllElements(): List<ActivityToDo> {
        return activities
    }

    override fun getElementByPosition(position: Int): ActivityToDo {
        return activities[position]
    }

    override fun addElement(element: ActivityToDo) {
        activities.add(element)
    }

    override fun deleteElementByPosition(position: Int) {
        activities.removeAt(position)
    }

    override fun deleteElement(element: ActivityToDo) {
        activities.remove(element)
    }
}