package alancasasarevalo.com.gotomadrid.activity

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.activitytype.ErrorCompletion
import alancasasarevalo.com.activitytype.SuccessCompletion
import alancasasarevalo.com.domain.interactors.getallelements.GetAllActivitiesToDoInteractor
import alancasasarevalo.com.domain.interactors.getallelements.GetAllElementsInteractorImplementation
import alancasasarevalo.com.domain.model.ActivitiesToDo
import alancasasarevalo.com.domain.model.ActivityToDo
import alancasasarevalo.com.gotomadrid.R
import alancasasarevalo.com.gotomadrid.fragment.ActivitiesToDoListFragment
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ActivitiesActivity : AppCompatActivity() {

    private var map: GoogleMap? = null
    lateinit var activityType: ActivityType
    val allActivitiesToDo : GetAllActivitiesToDoInteractor = GetAllElementsInteractorImplementation(this)
    val hashMarkerMap: HashMap<String, String>? = null

    companion object {
        private val EXTRA_ACTIVITY_TYPE = "EXTRA_ACTIVITY_TYPE"

        fun intent (context: Context, activityType: ActivityType?) : Intent {
            val intent = Intent(context, ActivitiesActivity::class.java)
            intent.putExtra(EXTRA_ACTIVITY_TYPE, activityType)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        activityType = intent.getSerializableExtra(EXTRA_ACTIVITY_TYPE) as ActivityType

        if (activityType != null){
            setupMap(activityType)
        }

    }

    private fun initializeMap(element: ActivitiesToDo) {
        val mapFragement = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragement.getMapAsync({
            map = it

            map?.uiSettings?.isRotateGesturesEnabled = false
            map?.uiSettings?.isZoomControlsEnabled = true

            centerPositionOnMap(it, 40.416775, -3.703790)
            showUserPosition(baseContext, it)
            addAllPins(element)

            map?.setOnMarkerClickListener {marker ->

                element.activities.forEach {activityToDoInArray ->
                    if ( marker.title == activityToDoInArray.name ){
                        val intent = DetailActivity.intent(this, activityToDoInArray)
                        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
                        startActivity(intent)
                    }
                }

                return@setOnMarkerClickListener false
            }

        })
    }

    fun centerPositionOnMap(map: GoogleMap, latitude: Double, longitude: Double){
        val coordinate = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition.Builder().target(coordinate).zoom(12f).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    // TODO: dependiendo del tipo agregar el pin a actividades o shops
    fun addPin(map: GoogleMap, latitude: Double, longitude: Double, activityToDo: ActivityToDo){
        var coordinate = LatLng(latitude, longitude)

        val marker = MarkerOptions().position(coordinate).title(activityToDo.name)

        map.addMarker(marker)

        hashMarkerMap?.put(marker.title, activityToDo.name)

    }

    fun addAllPins( activitiesToDo: ActivitiesToDo){

        for (i in 0 until activitiesToDo.elementCount()){
            val activityToDo = activitiesToDo.getElementByPosition(i)

            addPin(map!!, activityToDo.latitude.toDouble(), activityToDo.longitude.toDouble(), activityToDo)

        }

    }

    fun showUserPosition(context: Context, map: GoogleMap){

        // TODO: Si puedes usa Dexter de Karumi
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    10)

            // TODO: Alerta para que muestre al usuario alerta para que nos de permisios y rellamar a la funcion si no quiere el usuario return

            return
        } else {
            map.isMyLocationEnabled = true
        }
    }

    private fun setupMap(activityType: ActivityType){

        allActivitiesToDo.executeForType(activityType, successCompletion = object : SuccessCompletion<ActivitiesToDo> {
            override fun successCompletion(element: ActivitiesToDo) {
                initializeList(element)
                initializeMap(element)
            }

        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                // TODO:Devolver fallo al cliente en cristiano
                Toast.makeText(baseContext, errorMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10){
            try{
                map?.isMyLocationEnabled = true
            } catch (errorMessage: SecurityException){
                // TODO:Hacer un mensaje para el user
                Toast.makeText(baseContext, errorMessage.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initializeList(elements: ActivitiesToDo){

        if (supportFragmentManager.findFragmentById(R.id.activity_list_fragment) == null ){
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.activity_list_fragment, ActivitiesToDoListFragment.newInstance(elements))
                    .commit()
        }

    }
}
