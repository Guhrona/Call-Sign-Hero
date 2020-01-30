package kilo.delta.callsignhero

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kilo.delta.callsignhero.data.CallookResponse
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

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.searchListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(searchResponse: CallookResponse) {
        progress_bar.hide()
        tvResponse.text = searchResponse.status
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast("Search failure...")
    }

}