package com.lalabib.mhsid.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewPropertyAnimator
import com.lalabib.mhsid.databinding.ActivitySplashScreenBinding
import com.lalabib.mhsid.ui.home.HomeActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val alpha0 = 0f
    private val alpha1 = 1f
    private var propertyAnim: ViewPropertyAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ivLogo.alpha = alpha0
            tvTitle.alpha = alpha0

            propertyAnim = tvTitle.animate().setDuration(time).alpha(alpha1)
            propertyAnim = ivLogo.animate().setDuration(time).alpha(alpha1).withEndAction {
                Intent(this@SplashScreenActivity, HomeActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        }
    }

    override fun onDestroy() {
        propertyAnim?.cancel()
        super.onDestroy()
    }

    companion object {
        private const val time: Long = 1800
    }
}