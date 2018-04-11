package alancasasarevalo.com.commons.databinding

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DataBindingRecyclerAdapter <Model>(val itemVariableId: Int, val itemLayoutResId: Int) :RecyclerView.Adapter<DataBindingViewHolder<Model>>()  {

    private var handler: Any?
    private var handlerId: Int?
    var onClickElementListener: View.OnClickListener? = null

    init {
        handler = null
        handlerId = null
    }

    constructor(
            itemVariableId: Int,
            itemLayoutResId: Int,
            handler: Any,
            handlerId: Int
    ) : this(itemVariableId,itemLayoutResId){
        this.handler = handler
        this.handlerId = handlerId
    }
    val items : MutableList<Model> = mutableListOf()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<Model> {

        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                                                                itemLayoutResId,
                                                                parent,
                                                                false)

        return DataBindingViewHolder(itemVariableId, binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<Model>, position: Int) {
        val itemToShow = items[position]

        holder.bindItem(itemToShow)

        handler.let {
            holder.bindVariable(handlerId!!, handler!!)
        }

    }

}