package co.com.zoomathias.zoomathias

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_splash.*
import android.content.Intent
import android.os.Handler
import android.support.v4.os.HandlerCompat.postDelayed



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        /*setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        Handler().postDelayed(Runnable {
            // This method will be executed once the timer is over
            val i = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(i)
            finish()
        }, 1200)
    }

}
