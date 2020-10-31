package com.stylingandroid.asynnclayoutinflater

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import com.stylingandroid.asynnclayoutinflater.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var formatter: DateTimeFormatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val content = findViewById<ViewGroup>(android.R.id.content)
        Timber.tag("MainActivity").d("begin ${formatter.format(LocalTime.now())}")
        AsyncLayoutInflater(this)
            .inflate(R.layout.activity_main, content) { view, _, _ ->
                Timber.tag("MainActivity").d("inflated ${formatter.format(LocalTime.now())}")
                binding = ActivityMainBinding.bind(view)
                setContentView(view)
            }
    }
}
