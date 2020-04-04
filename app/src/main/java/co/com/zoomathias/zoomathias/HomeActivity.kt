package co.com.zoomathias.zoomathias

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        animationLeftToRight();
    }

    fun animationLeftToRight()
    {
        var animation : Animation = AnimationUtils.loadAnimation(this, R.anim.lefttoright)
        container.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                //override
            }

            override fun onAnimationEnd(animation: Animation?) {
                animationBounce();
            }

            override fun onAnimationStart(animation: Animation?) {
                //override
            }
            // All the other override functions
        })
    }

    fun animationBounce()
    {
        var animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        recognize.startAnimation(animation)
        count.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                //override
            }

            override fun onAnimationEnd(animation: Animation?) {
                animationRotate()
            }

            override fun onAnimationStart(animation: Animation?) {
                //override
            }
            // All the other override functions
        })
    }

    fun animationRotate()
    {
        var animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        img_config.startAnimation(animation)
        img_info.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                //override
            }

            override fun onAnimationEnd(animation: Animation?) {
                timerAnimationRecognize()
                timerAnimationCount()
            }

            override fun onAnimationStart(animation: Animation?) {
                //override
            }
            // All the other override functions
        })
    }

    fun timerAnimationRecognize()
    {
        val timer = Timer("schedule", true)

        timer.scheduleAtFixedRate(5000, 25000) {
            animationZoomOut(imageView, imageView2, imageView3)
        }
    }

    fun timerAnimationCount()
    {
        val timer = Timer("scheduleTwo", true)

        timer.scheduleAtFixedRate(10000, 25000) {
            animationZoomOut(imageView4, imageView5, null)
        }
    }

    private fun animationZoomOut(view1 : View, view2 : View, view3 : View?) {
        var animation = AnimationUtils.loadAnimation(this, R.anim.zoomout)
        view1.startAnimation(animation)
        view2.startAnimation(animation)
        view3?.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                //override
            }

            override fun onAnimationEnd(animation: Animation?) {
                animationZoomReset(view1, view2, view3)
            }

            override fun onAnimationStart(animation: Animation?) {
                //override
            }
            // All the other override functions
        })
    }

    fun animationZoomReset(view1 : View, view2 : View, view3 : View?) {
        var animation = AnimationUtils.loadAnimation(this, R.anim.zoomreset)
        view1.startAnimation(animation)
        view2.startAnimation(animation)
        view3?.startAnimation(animation)
    }
}
