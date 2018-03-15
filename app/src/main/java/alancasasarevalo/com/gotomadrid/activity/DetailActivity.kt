package alancasasarevalo.com.gotomadrid.activity

import alancasasarevalo.com.domain.model.ActivityToDo
import alancasasarevalo.com.gotomadrid.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    var detailActivityToDo: ActivityToDo? = null

    companion object {
        const val DETAIL_ACTIVITY = "DETAIL_ACTIVITY"

        fun intent(context: Context): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)





    }
}
