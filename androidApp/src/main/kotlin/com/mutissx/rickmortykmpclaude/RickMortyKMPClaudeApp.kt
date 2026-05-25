package com.mutissx.rickmortykmpclaude

import android.app.Application
import android.widget.Toast
import com.mutissx.rickmortykmpclaude.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RickMortyKMPClaudeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RickMortyKMPClaudeApp)
            modules(appModules())
        }
        Toast.makeText(this, getString(R.string.hello), Toast.LENGTH_SHORT).show()
    }
}
