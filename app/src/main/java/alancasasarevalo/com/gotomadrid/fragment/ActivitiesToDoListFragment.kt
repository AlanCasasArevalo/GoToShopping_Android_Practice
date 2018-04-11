package alancasasarevalo.com.gotomadrid.fragment


import alancasasarevalo.com.commons.databinding.DataBindingRecyclerAdapter
import alancasasarevalo.com.commons.fragments.BaseListFragment
import alancasasarevalo.com.domain.model.ActivitiesToDo
import alancasasarevalo.com.domain.model.ActivityToDo
import alancasasarevalo.com.gotomadrid.BR
import alancasasarevalo.com.gotomadrid.R
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast

class ActivitiesToDoListFragment : BaseListFragment() {

    lateinit var arrayActivitiesToDo: ActivitiesToDo

    companion object {
        private val EXTRA_ACTIVITYTODO = "EXTRA_ACTIVITYTODO"

        fun newInstance (activitiesToDo: ActivitiesToDo) : ActivitiesToDoListFragment{
            var fragment = ActivitiesToDoListFragment()
            val arguments = Bundle()
            arguments.putSerializable(EXTRA_ACTIVITYTODO, activitiesToDo)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun getAdapter(): RecyclerView.Adapter<*> {
        val adapter = DataBindingRecyclerAdapter<ActivityToDo>(
                itemVariableId = BR.activityToDo,
                itemLayoutResId = R.layout.item_activity,
                handler = Handler(),
                handlerId = BR.handler
        )

        return adapter
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayActivitiesToDo = arguments.getSerializable(EXTRA_ACTIVITYTODO) as ActivitiesToDo

        (listAdapter as DataBindingRecyclerAdapter<ActivityToDo>).items.addAll(getActivities())

        listAdapter.notifyDataSetChanged()

    }

    fun getActivities(): ArrayList<ActivityToDo> {
        var activitiesToDo = ArrayList<ActivityToDo>()

        arrayActivitiesToDo.activities.forEach {
            activitiesToDo.add(it)
        }

        return activitiesToDo
    }

    fun getDummyShops(): ArrayList<ActivityToDo> {

        var dummyArrayActivityToDo = ArrayList<ActivityToDo>()

        (0..9).forEach { i ->
            val dummyActivityToDo = ActivityToDo(1,
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i",
                    "Apellido $i"
            )

            dummyArrayActivityToDo.add(dummyActivityToDo)
        }

        return dummyArrayActivityToDo

    }

    class Handler {
        fun onClick(view: View, item: ActivityToDo){
            Toast.makeText(view.context, "${item.name}", Toast.LENGTH_SHORT).show()
        }
    }
}