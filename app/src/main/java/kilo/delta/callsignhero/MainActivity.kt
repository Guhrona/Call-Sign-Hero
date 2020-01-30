package kilo.delta.callsignhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kilo.delta.callsignhero.databinding.ActivityMainBinding
import kilo.delta.callsignhero.util.hide
import kilo.delta.callsignhero.util.show
import kilo.delta.callsignhero.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity(), SearchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(listOf()) //TODO - create modules
        }

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.searchListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(searchResponse: LiveData<String>) {
        progress_bar.hide()
        searchResponse.observe(this, Observer {
            tvResponse.text = it
        })
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast("Search failure...")
    }

}