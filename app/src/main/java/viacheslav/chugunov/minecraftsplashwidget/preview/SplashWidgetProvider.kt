package viacheslav.chugunov.minecraftsplashwidget.preview

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
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
        val intent = Intent(context, SplashWidgetProvider::class.java)
        intent.action = ACTION_CHANGE_SPLASH
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }
        views.setOnClickPendingIntent(R.id.title, pendingIntent)
        views.setOnClickPendingIntent(R.id.splash, pendingIntent)
        appWidgetManager.updateAppWidget(appWidgetIds, views)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_CHANGE_SPLASH) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val componentName = ComponentName(context, SplashWidgetProvider::class.java)
            val views = RemoteViews(context.packageName, R.layout.splash_widget)
            val splashRepository = SplashRepository.Default(context)
            val splash = splashRepository.getRandomSplash()
            val customTextRepository = CustomTextRepository.Default(context)
            val textBitmap = customTextRepository.getBitmap(splash)
            views.setImageViewBitmap(R.id.splash, textBitmap)
            appWidgetManager.updateAppWidget(componentName, views)
        }
    }

    companion object {
        private const val ACTION_CHANGE_SPLASH = "action-change-splash"
    }
}