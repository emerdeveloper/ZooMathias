package co.com.zoomathias.zoomathias

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_count.*

class CountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        animationSequencialUpDown()
        option_one.setOnClickListener { OnClickOptionOne() }
        option_two.setOnClickListener { OnClickOptionTwo() }
        option_three.setOnClickListener { OnClickOptionThree() }
    }

    fun OnClickOptionThree()
    {
        var animation : Animation = AnimationUtils.loadAnimation(this, R.anim.sequencialup_down)
        option_three.startAnimation(animation)
    }

    fun OnClickOptionOne()
    {
        animationSequencialUpDown()
    }

    fun OnClickOptionTwo()
    {
        var animation : Animation = AnimationUtils.loadAnimation(this, R.anim.uptodown)
        option_two.startAnimation(animation)

    }

    fun animationSequencialUpDown()
    {
        var animation : Animation = AnimationUtils.loadAnimation(this, R.anim.sequencialup_down)
        option_one.startAnimation(animation)
        option_two.startAnimation(animation)
        option_three.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                //override
            }

            override fun onAnimationEnd(animation: Animation?) {
                //animationBounce();
            }

            override fun onAnimationStart(animation: Animation?) {
                //override
            }
            // All the other override functions
        })
    }
}
