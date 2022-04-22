package com.mifos.mifosxdroid.online.voucher

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.mifos.mifosxdroid.R
import com.mifos.mifosxdroid.core.MifosBaseActivity
import kotlinx.android.synthetic.main.activity_voucher_redemption.*

class VoucherRedemptionActivity : MifosBaseActivity(), VoucherRedemptionMVPView {

    private var buttonState = 1
    var presenter: VoucherRedemptionPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher_redemption)
        initView()
        presenter
    }

    private fun initView() {
        // Write a code for UI related task when this screen will start
        send_button.setOnClickListener {
            layout_mobile_no.visibility = View.GONE
            layout_voucher_details.visibility = View.VISIBLE
            buttonState = 2
        }
        handleButtonState()
        startBiomatricProcess()

    }

    private fun startBiomatricProcess() {
        // Write the code for biometric process for selection of finger and capture finger print
    }

    private fun handleButtonState() {
        // handle state of button here

    }

    override fun showProgressbar(b: Boolean) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}