package alancasasarevalo.com.gotomadrid.dummyui.activity

import alancasasarevalo.com.gotomadrid.R
import alancasasarevalo.com.gotomadrid.dummyui.fragment.FirstFragment
import alancasasarevalo.com.gotomadrid.dummyui.fragment.SecondFragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class DummyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)


        if (supportFragmentManager.findFragmentById(R.id.activity_first_fragment) == null || supportFragmentManager.findFragmentById(R.id.activity_second_fragment) == null){
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.activity_first_fragment, FirstFragment.newInstance())
                    .add(R.id.activity_second_fragment, SecondFragment.newInstance())
                    .commit()
        }

    }
}
