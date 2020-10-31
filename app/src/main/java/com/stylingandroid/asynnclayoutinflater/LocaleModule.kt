package com.stylingandroid.asynnclayoutinflater

import android.content.Context
import android.os.Build
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Module
@InstallIn(ActivityComponent::class)
object LocaleModule {

    @Provides
    fun provideCurrentLocale(@ActivityContext context: Context): Locale =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0]
        } else {
            @Suppress("DEPRECATION")
            context.resources.configuration.locale
        }

    @Provides
    fun provideDefaultZoneId(): ZoneId = ZoneId.systemDefault()

    @Provides
    fun providesTimeFormatter(locale: Locale, zoneId: ZoneId): DateTimeFormatter =
        DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
            .withLocale(locale)
            .withZone(zoneId)
}
