package alancasasarevalo.com.commons.fragments

import alancasasarevalo.com.commons.R
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_list.view.*

abstract class BaseListFragment : GenericFragment(){

    lateinit var listAdapter: RecyclerView.Adapter<*>

    override fun getLayoutResId(): Int {
        return R.layout.fragment_list
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = getAdapter()

        view?.base_list_fragment?.let {
            with(view.base_list_fragment){
                adapter = listAdapter
                layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            }
        }

    }

    abstract fun getAdapter() : RecyclerView.Adapter<*>
}
















