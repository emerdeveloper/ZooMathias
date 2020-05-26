package co.com.zoomathias.zoomathias

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_credits.*


class CreditsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        img_return.setOnClickListener { finish() }

        container_first_page.setOnClickListener { view ->
            val url = "https://es.vecteezy.com/"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }

        container_second_page.setOnClickListener { view ->
            val url = "https://www.freepik.es/"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }

        container_third_page.setOnClickListener { view ->
            val url = "https://lottiefiles.com/"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }

    }
}