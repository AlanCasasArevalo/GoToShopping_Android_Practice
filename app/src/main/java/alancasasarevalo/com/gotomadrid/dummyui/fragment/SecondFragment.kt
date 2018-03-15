package alancasasarevalo.com.gotomadrid.dummyui.fragment


import alancasasarevalo.com.commons.BR
import alancasasarevalo.com.commons.databinding.DataBindingRecyclerAdapter
import alancasasarevalo.com.commons.fragments.BaseListFragment
import alancasasarevalo.com.gotomadrid.R
import alancasasarevalo.com.gotomadrid.dummyui.model.Person
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View

class SecondFragment : BaseListFragment() {

    companion object {
        fun newInstance () : SecondFragment{
            var fragment = SecondFragment()
            return fragment
        }
    }

    override fun getAdapter(): RecyclerView.Adapter<*> {
        val adapter = DataBindingRecyclerAdapter<Person>(BR.person, R.layout.item_to_show)
        return adapter
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (listAdapter as DataBindingRecyclerAdapter<Person>).items.addAll(getDummyShops())
        listAdapter.notifyDataSetChanged()

    }

    fun getDummyShops(): ArrayList<Person> {

        var dummyArrayShop = ArrayList<Person>()

        (0..9).forEach { i ->
            val dummyShop = Person("Nombre $i",
                    "Apellido $i",
                    i
            )

            dummyArrayShop.add(dummyShop)
        }

        return dummyArrayShop

    }

}
