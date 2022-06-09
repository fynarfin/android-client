package com.mifos.mifosxdroid.online.voucher

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.*
import androidx.annotation.StringRes
import com.mifos.api.model.biometric.Opts
import com.mifos.api.model.biometric.PidData
import com.mifos.api.model.biometric.PidOptions
import com.mifos.mifosxdroid.R
import com.mifos.mifosxdroid.core.MifosBaseActivity
import com.mifos.utils.PrefManager
import com.mifos.utils.Utils
import kotlinx.android.synthetic.main.activity_voucher_redemption.*
import kotlinx.android.synthetic.main.dialog_result.*
import org.simpleframework.xml.core.Persister
import java.io.StringWriter
import java.util.*


class VoucherRedemptionActivity : MifosBaseActivity(), VoucherRedemptionMVPView {

    private var buttonState = 1
    var presenter: VoucherRedemptionPresenter? = null
    private var fingerPosition: String? = null
    private var pidData = PidData()
    private var encodedString: String? = null
    //val spinner: Spinner = findViewById(R.id.input_id_spinner)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher_redemption)
        initView()
        presenter
    }


    private fun initView() {
        // Write a code for UI related task when this screen will start
        send_button.setOnClickListener {
            if (buttonState == 1) {
                layout_mobile_no.visibility = View.GONE
                layout_id_details.visibility = View.VISIBLE
                et_beneficiary_id.setText("41267855")
                buttonState = 2
            } else if (buttonState == 2) {
//                et_beneficiary_id.setText(PrefManager.getUserId())
                layout_id_details.visibility = View.GONE
                layout_voucher_details.visibility = View.VISIBLE
                buttonState = 3
            } else if (buttonState == 3) {
                finish()
            }
        }
        val id = resources.getStringArray(R.array.select_id)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, id)
        input_id_spinner.adapter = adapter

        input_id_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                //      fingerPosition = fingers[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }

        }
        val fingers = resources.getStringArray(R.array.Finger)
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, fingers)
        input_fingerprint.adapter = adapter1

        input_fingerprint.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                fingerPosition = fingers[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }

        }

        fingerprint_button.setOnClickListener {
            try {
                val pidOption = getPIDOptions()
                if (pidOption != null) {
                    Log.e("PidOptions", pidOption)
                    val intent2 = Intent()
                    intent2.action = "in.gov.uidai.rdservice.fp.CAPTURE"
                    intent2.putExtra("PID_OPTIONS", pidOption)
                    startActivityForResult(intent2, 1)
                }
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }




        handleButtonState()
        startBiomatricProcess()

    }

    private fun showAlertDialog(result: Boolean) {

        val dialog = Dialog(this)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view: View = layoutInflater.inflate(R.layout.dialog_result, null)
        val btnOk: Button = view.findViewById(R.id.btn_ok)
        val desc: TextView = view.findViewById(R.id.tv_description)
        val success: ImageView = view.findViewById(R.id.iv_success)
        dialog.setCancelable(false)
        dialog.setContentView(view)
        btnOk.setOnClickListener {
            finish()
        }
        if(result) {
            desc.text = getString(R.string.voucher_successful)
            success.setImageResource(R.drawable.check_circle)
        }
        else {
            desc.text = getString(R.string.voucher_failure)
            success.setImageResource(R.drawable.close_circle)
        }
        dialog.show()
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
        if (resultCode == Activity.RESULT_OK) {
            try {
                if (data != null) {
                    val result = data.getStringExtra("PID_DATA")
                    if (result != null) {
                        val serializer = Persister()
                        pidData = serializer.read(PidData::class.java, result)
                        if (pidData._Resp?.errCode == "0") {
                            encodedString =
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    Base64.getEncoder().encodeToString(result.toByteArray())
                                } else {
                                    TODO("VERSION.SDK_INT < O")
                                }
                            showAlertDialog(true)
                        } else
                            showAlertDialog(false)
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "Error while deserialze pid data", e)
            }
        }
    }

    private fun getPIDOptions(): String? {
        try {
            if (fingerPosition.equals("Select")) {
                showErrorToast(R.string.error_finger_selection)
            } else {
                val opts = Opts()
                opts.fCount = "1"
                opts.fType = "0"
                opts.format = "0"
                opts.env = "P"
                opts.iCount = "0"
                opts.iType = "0"
                opts.pCount = "0"
                opts.pType = "0"
                opts.pidVer = "2.0"
                opts.timeout = "10000"
                opts.posh = fingerPosition
                val pidOptions = PidOptions()
                pidOptions.ver = "1.0"
                pidOptions.Opts = opts
                val serializer = Persister()
                val writer = StringWriter()
                serializer.write(pidOptions, writer)
                return writer.toString()
            }
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }
        return null
    }

    fun showErrorToast(@StringRes stringResource: Int) {
        Toast.makeText(
            this, this.getString(stringResource),
            Toast.LENGTH_LONG
        ).show()
    }


}