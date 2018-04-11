package alancasasarevalo.com.gotomadrid.activity

import alancasasarevalo.com.domain.model.ActivityToDo
import alancasasarevalo.com.gotomadrid.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*



class DetailActivity : AppCompatActivity() {

    public var detailActivityToDo: ActivityToDo? = null

    companion object {
        const val DETAIL_ACTIVITY = "DETAIL_ACTIVITY"

        fun intent(context: Context, activityToDo: ActivityToDo): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.DETAIL_ACTIVITY, activityToDo)
            intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailActivityToDo = intent.getSerializableExtra(DETAIL_ACTIVITY) as ActivityToDo

        if (detailActivityToDo != null){
            val activityToDo = detailActivityToDo
            setupUIWithModel(activityToDo!!)
        }

    }

    private fun setupUIWithModel(detailActivityToDo: ActivityToDo) {

        title = detailActivityToDo.name

        Glide.with(this)
                .load(detailActivityToDo.img)
                .apply(RequestOptions()
                                .error(R.drawable.default_photo)
                                .placeholder(R.drawable.default_photo)
                                .fitCenter())
                .into(detail_activity_image)

        detail_activity_opening.text = detailActivityToDo.openingHoursEn
        detail_activity_url.text = detailActivityToDo.url
        detail_activity_email.text = detailActivityToDo.email
        detail_activity_telephone.text = detailActivityToDo.telephone
        detail_activity_description.text = detailActivityToDo.descriptionEn

    }

}
