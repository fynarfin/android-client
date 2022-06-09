package com.mifos.api.services;

import com.mifos.api.model.APIEndPoint;
import com.mifos.objects.voucher.RedeemVoucherRequestBody;
import com.mifos.objects.voucher.RedeemVoucherResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface VoucherRedemptionService {

    @POST(APIEndPoint.VOUCHER + "/Agriculture10OFF/redemption")
    Observable<RedeemVoucherResponse> redeemVoucher(@Body RedeemVoucherRequestBody requestBody);
}
