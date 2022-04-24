package viacheslav.chugunov.minecraftsplashwidget.preview

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.TypedValue
import android.widget.RemoteViews
import viacheslav.chugunov.minecraftsplashwidget.R
import viacheslav.chugunov.minecraftsplashwidget.domain.SplashRepository
import viacheslav.chugunov.minecraftsplashwidget.domain.TextSizeRepository

class SplashWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        val views = RemoteViews(context.packageName, R.layout.splash_widget)
        val splashRepository = SplashRepository.Default(context)
        val splash = splashRepository.getRandomSplash()
        views.setTextViewText(R.id.splash, splash.name)
        val textSizeRepository = TextSizeRepository.Default()
        val textSize = textSizeRepository.getTextSizeSpBySplash(splash)
        views.setTextViewTextSize(R.id.splash, TypedValue.COMPLEX_UNIT_SP, textSize)
        appWidgetManager.updateAppWidget(appWidgetIds, views)
    }
}