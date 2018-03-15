package alancasasarevalo.com.gotomadrid.activity

import alancasasarevalo.com.gotomadrid.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class PresentationActivity : AppCompatActivity() {

    companion object {

        fun intent(context: Context): Intent {
            val intent = Intent(context, PresentationActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation)
    }
}
