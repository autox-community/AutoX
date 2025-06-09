package org.autojs.phonefarm.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.autojs.phonefarm.ui.main.MainActivity
import org.autojs.phonefarm.R

/**
 * Created by Stardust on 2017/7/7.
 */
class SplashActivity : ComponentActivity() {
    private var mAlreadyEnterNextActivity = false
    private var mPaused = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        lifecycleScope.launch {
            delay(INIT_TIMEOUT)
            enterNextActivity()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                window.attributes = window.attributes.apply {
                    layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                }
            }

            WindowCompat.setDecorFitsSystemWindows(window, false)
            val controller = ViewCompat.getWindowInsetsController(window.decorView)
            controller?.hide(WindowInsetsCompat.Type.systemBars())
            controller?.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun init() {
        setContent {
            val systemUiController = rememberSystemUiController()
            val color = MaterialTheme.colors.background
            SideEffect {
                systemUiController.setNavigationBarColor(color = color, darkIcons = true)
            }
            Column(Modifier.fillMaxSize()) {
                Spacer(
                    modifier = Modifier
                        .windowInsetsTopHeight(WindowInsets.statusBars)
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.powered_by_autojs),
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                    )
                }
                Spacer(
                    modifier = Modifier
                        .windowInsetsBottomHeight(WindowInsets.navigationBars)
                )
            }

        }
    }

    override fun onPause() {
        super.onPause()
        mPaused = true
    }

    override fun onResume() {
        super.onResume()
        if (mPaused) {
            mPaused = false
            enterNextActivity()
        }
    }

    private fun enterNextActivity() {
        if (mAlreadyEnterNextActivity) return
        if (mPaused) {
            return
        }
        mAlreadyEnterNextActivity = true
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    companion object {
        private val LOG_TAG = SplashActivity::class.java.simpleName
        private const val INIT_TIMEOUT: Long = 800
    }
}