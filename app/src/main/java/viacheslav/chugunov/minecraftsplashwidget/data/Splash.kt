package viacheslav.chugunov.minecraftsplashwidget.data

interface Splash {
    val name: String


    @JvmInline
    value class Default(override val name: String) : Splash
}