package kilo.delta.callsignhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kilo.delta.callsignhero.databinding.ActivityMainBinding
import kilo.delta.callsignhero.util.toast

class MainActivity : AppCompatActivity(), SearchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.searchListener = this
    }

    override fun onStarted() {
        toast("Search started...")
    }

    override fun onSuccess() {
        toast("Search success...")
    }

    override fun onFailure(message: String) {
        toast("Search failure...")
    }

}