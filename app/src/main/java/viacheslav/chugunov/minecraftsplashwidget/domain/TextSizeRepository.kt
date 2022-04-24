package viacheslav.chugunov.minecraftsplashwidget.domain

import viacheslav.chugunov.minecraftsplashwidget.data.Splash

interface TextSizeRepository {
    fun getTextSizeSpByString(string: String): Float
    fun getTextSizeSpBySplash(splash: Splash): Float = getTextSizeSpByString(splash.name)


    class Default : TextSizeRepository {
        override fun getTextSizeSpByString(string: String): Float = when (string.length) {
            in 0..5 -> 16f
            in 6..10 -> 14f
            in 11..15 -> 13f
            in 16..25 -> 12f
            in 26..35 -> 11f
            else -> 10f
        }
    }
}