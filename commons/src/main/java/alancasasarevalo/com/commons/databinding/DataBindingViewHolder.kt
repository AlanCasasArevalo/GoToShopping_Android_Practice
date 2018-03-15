package alancasasarevalo.com.commons.databinding

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class DataBindingViewHolder <Model> ( val itemVariableId: Int, val binding: ViewDataBinding): RecyclerView.ViewHolder ( binding.root ) {

    fun bindItem(item: Model){
        binding.setVariable(itemVariableId, item)
        binding.executePendingBindings()
    }
}