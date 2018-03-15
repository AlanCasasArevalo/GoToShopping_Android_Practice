package alancasasarevalo.com.gotomadrid.activity

import alancasasarevalo.com.domain.model.ActivitiesToDo
import alancasasarevalo.com.gotomadrid.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_activities.*

class ActivitiesActivity : AppCompatActivity() {

    var activitiesToDo:ActivitiesToDo? = null

    companion object {
        const val ACTIVITIES_ACTIVITIESTODO = "ACTIVITIES_ACTIVITIESTODO"

        fun intent(context: Context): Intent {
            val intent = Intent(context, ActivitiesActivity::class.java)
            return intent
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        activities_navigation_view.setOnNavigationItemSelectedListener { item: MenuItem ->
            when(item.itemId){
                R.id.shops -> startActivity(ShopsActivity.intent(this))
                R.id.activities -> startActivity(ActivitiesActivity.intent(this))

                else -> startActivity(PresentationActivity.intent(this))
            }
            true
        }

    }
}
