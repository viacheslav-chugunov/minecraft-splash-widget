package viacheslav.chugunov.minecraftsplashwidget.domain

import android.content.Context
import viacheslav.chugunov.minecraftsplashwidget.R
import viacheslav.chugunov.minecraftsplashwidget.data.Splash
import java.util.*

interface SplashRepository {
    fun getRandomSplash(): Splash


    class Default(context: Context) : SplashRepository {
        private val splashNames = context.resources.getStringArray(R.array.splashes).toList()

        override fun getRandomSplash(): Splash {
            val index = Random().nextInt(splashNames.size)
            val splashName = splashNames[index]
            return Splash.Default(splashName)
        }
    }
}