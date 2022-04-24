package viacheslav.chugunov.minecraftsplashwidget.data

import android.content.Context
import android.util.TypedValue

fun spToPx(context: Context, sp: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics)

fun dpToPx(context: Context, dp: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)