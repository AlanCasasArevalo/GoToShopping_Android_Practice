package alancasasarevalo.com.gotomadrid

import android.app.Application
import android.support.multidex.MultiDexApplication

class MyApp : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()

    }
}