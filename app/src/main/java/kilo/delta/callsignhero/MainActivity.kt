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

        //TODO - fix this trash
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
        tvStatus.text = getString(R.string.status, searchResponse.status)
        tvType.text = getString(R.string.type, searchResponse.type)
        tvCurrentCallsign.text = getString(R.string.current_callsign, searchResponse.current?.callsign)
        tvCurrentOperClass.text = getString(R.string.current_operclass, searchResponse.current?.operClass)
        tvPreviousCallSign.text = getString(R.string.previous_callsign, searchResponse.previous?.callsign)
        tvPreviousOperClass.text = getString(R.string.previous_operclass, searchResponse.previous?.operClass)
        tvTrusteeCallSign.text = getString(R.string.trustee_callsign, searchResponse.trustee?.callsign)
        tvTrusteeName.text = getString(R.string.trustee_name, searchResponse.trustee?.name)
        tvName.text = getString(R.string.name, searchResponse.name)
        tvAddressLine1.text = getString(R.string.address_line1, searchResponse.address?.line1)
        tvAddressLine2.text = getString(R.string.address_line2, searchResponse.address?.line2)
        tvAddressAttn.text = getString(R.string.address_attn, searchResponse.address?.attn)
        tvLocationLatitude.text = getString(R.string.location_latitude, searchResponse.location?.latitude)
        tvLocationLongitude.text = getString(R.string.location_longitude, searchResponse.location?.longitude)
        tvLocationGridsquare.text = getString(R.string.location_gridsquare, searchResponse.location?.gridsquare)
        tvOtherInfoGrantDate.text = getString(R.string.other_info_grant_date, searchResponse.otherInfo?.grantDate)
        tvOtherInfoExpiryDate.text = getString(R.string.other_info_expiry_date, searchResponse.otherInfo?.expiryDate)
        tvOtherInfoLastActionDate.text = getString(R.string.other_info_last_action_date, searchResponse.otherInfo?.lastActionDate)
        tvOtherInfoFrn.text = getString(R.string.other_info_frn, searchResponse.otherInfo?.frn)
        tvOtherInfoUlsUrl.text = getString(R.string.other_info_uls_url, searchResponse.otherInfo?.ulsUrl)

        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast("Search failure for some unknown reason...")
    }
}