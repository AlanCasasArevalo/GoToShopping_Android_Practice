package alancasasarevalo.com.commons.fragments

import alancasasarevalo.com.commons.extensions.inflate
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class GenericFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = container?.inflate(getLayoutResId())

        return rootView
    }

    abstract fun getLayoutResId () : Int

}