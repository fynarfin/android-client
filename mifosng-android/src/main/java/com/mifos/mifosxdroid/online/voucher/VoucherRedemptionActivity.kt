package com.mifos.mifosxdroid.online.voucher

import android.os.Bundle
import android.view.View
import com.mifos.mifosxdroid.R
import com.mifos.mifosxdroid.core.MifosBaseActivity
import kotlinx.android.synthetic.main.activity_voucher_redemption.*

class VoucherRedemptionActivity: MifosBaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher_redemption)
        send_button.setOnClickListener {
            layout_mobile_no.visibility=View.GONE
         layout_voucher_details.visibility=View.VISIBLE
        }
    }
}