package com.mifos.objects.voucher;

import com.google.gson.annotations.SerializedName;


public class RedeemVoucherRequest {

    @SerializedName("beneficiary_id")
    String beneficiary_id;

    @SerializedName("order")
    Order order;

    @SerializedName("auth")
    Auth auth;

}
