package alancasasarevalo.com.gotomadrid.dummyui.fragment


import alancasasarevalo.com.commons.fragments.GenericFragment
import alancasasarevalo.com.gotomadrid.R

class FirstFragment : GenericFragment() {

    companion object {
        fun newInstance () : FirstFragment{
            var fragment = FirstFragment()
            return fragment
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_first
    }

}
