package alancasasarevalo.com.gotomadrid.activity

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.activitytype.ErrorCompletion
import alancasasarevalo.com.activitytype.SuccessCompletion
import alancasasarevalo.com.domain.interactors.getallelements.GetAllActivitiesToDoInteractor
import alancasasarevalo.com.domain.interactors.getallelements.GetAllElementsInteractorImplementation
import alancasasarevalo.com.domain.interactors.networkingstatus.InternetStatusInteractorImplementation
import alancasasarevalo.com.domain.model.ActivitiesToDo
import alancasasarevalo.com.gotomadrid.R
import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_presentation.*

class PresentationActivity : AppCompatActivity() {

    val allActivitiesToDo : GetAllActivitiesToDoInteractor = GetAllElementsInteractorImplementation(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation)
        checkInternetConnection()

        presentation_shop_download_button.setOnClickListener{
            startActivity(ShopsActivity.intent(this, ActivityType.SHOP))
        }

        presentation_activity_download_button.setOnClickListener{
            startActivity(ActivitiesActivity.intent(this, ActivityType.ACTIVITY))
        }
    }

    private fun checkInternetConnection(){

        InternetStatusInteractorImplementation().execute(this, success = {
            downloadAllDataFromServer()
        }, error = {
            AlertDialog.Builder(this)
                    .setTitle("Necesitamos internet").setMessage("Esta aplicacion necesita conexion a internet al menos la primera ver").setPositiveButton("OK", { dialog, _ ->
                dialog.dismiss()
                this.finish()
            }).setNegativeButton("Usar base de datos", { dialog, _ ->
                        dialog.dismiss()
                        downloadAllDataFromServer()
                    })
                    .show()
        })
    }

    fun downloadAllDataFromServer(){
        allActivitiesToDo.execute(object : SuccessCompletion<ActivitiesToDo> {
            override fun successCompletion(elements: ActivitiesToDo) {
                Log.d("MadridShops Data", "Data base loaded succesfully")
                Log.d("CORIO HomeActivity", "" + elements.activities.count())
            }
        }, object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, errorMessage, Toast.LENGTH_LONG).show()
            }
        })

    }

}
