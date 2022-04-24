package viacheslav.chugunov.minecraftsplashwidget.domain.repository

import android.content.Context
import android.graphics.*
import viacheslav.chugunov.minecraftsplashwidget.data.Splash
import viacheslav.chugunov.minecraftsplashwidget.data.dpToPx
import viacheslav.chugunov.minecraftsplashwidget.data.spToPx

interface CustomTextRepository {
    fun getBitmap(text: String): Bitmap
    fun getBitmap(splash: Splash): Bitmap = getBitmap(splash.name)


    class Default(
        private val context: Context,
        private val screenWidthDp: Float = 250f,
        private val screenHeightDp: Float = 40f,
        private val fontAssetPath: String = "fonts/minecraft.ttf",
        private val fontColorHex: String = "#ffffff00"
    ) : CustomTextRepository {

        override fun getBitmap(text: String): Bitmap {
            val width = dpToPx(context, screenWidthDp)
            val height = dpToPx(context, screenHeightDp)
            val bitmap = Bitmap.createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val textSizeSp = when (text.length) {
                in 0..5 -> 16f
                in 6..10 -> 14f
                in 11..15 -> 13f
                in 16..25 -> 12f
                in 26..35 -> 11f
                else -> 10f
            }
            val paint = Paint().apply {
                isAntiAlias = true
                isSubpixelText = true
                typeface = Typeface.createFromAsset(context.assets, fontAssetPath)
                style = Paint.Style.FILL
                color = Color.parseColor(fontColorHex)
                textSize = spToPx(context, textSizeSp)
                textAlign = Paint.Align.LEFT
            }
            val bounds = Rect()
            paint.getTextBounds(text, 0, text.length, bounds)
            val textWidth = bounds.width()
            val startWidth = width - textWidth
            canvas.drawText(text, startWidth, height, paint)
            return bitmap
        }
    }
}