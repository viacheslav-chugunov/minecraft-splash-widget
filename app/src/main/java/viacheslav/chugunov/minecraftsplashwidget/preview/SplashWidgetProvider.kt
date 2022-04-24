package viacheslav.chugunov.minecraftsplashwidget.preview

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import viacheslav.chugunov.minecraftsplashwidget.R
import viacheslav.chugunov.minecraftsplashwidget.domain.repository.SplashRepository
import viacheslav.chugunov.minecraftsplashwidget.domain.repository.CustomTextRepository


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
        val customTextRepository = CustomTextRepository.Default(context)
        val textBitmap = customTextRepository.getBitmap(splash)
        views.setImageViewBitmap(R.id.splash, textBitmap)
        appWidgetManager.updateAppWidget(appWidgetIds, views)
    }
}