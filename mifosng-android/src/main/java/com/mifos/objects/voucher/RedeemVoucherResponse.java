package com.mifos.objects.voucher;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RedeemVoucherResponse {

    @SerializedName("id")
    String id;

    @SerializedName("version")
    String version;

    @SerializedName("responseTime")
    String responseTime;

    @SerializedName("transactionID")
    String transactionID;

    @SerializedName("response")
    Response response;

    @SerializedName("errors")
    Errors errors;

}
