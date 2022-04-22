package com.mifos.api.local.databasehelper;

import com.mifos.objects.voucher.RedeemVoucherRequestBody;
import com.mifos.objects.voucher.RedeemVoucherResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func0;

public class DatabaseHelperVoucher {

    @Inject
    public DatabaseHelperVoucher() {}

    public Observable<RedeemVoucherResponse> redeemVoucher(
            final RedeemVoucherRequestBody requestBody) {

        return Observable.defer(new Func0<Observable<RedeemVoucherResponse>>() {
            @Override
            public Observable<RedeemVoucherResponse> call() {

                return Observable.just(new RedeemVoucherResponse());
            }
        });
    }
}
