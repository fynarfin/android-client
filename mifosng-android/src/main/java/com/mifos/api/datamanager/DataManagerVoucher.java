package com.mifos.api.datamanager;

import com.mifos.api.BaseApiManager;
import com.mifos.api.local.databasehelper.DatabaseHelperVoucher;
import com.mifos.objects.voucher.RedeemVoucherRequestBody;
import com.mifos.objects.voucher.RedeemVoucherResponse;

import javax.inject.Inject;

import rx.Observable;

public class DataManagerVoucher {

    public final BaseApiManager mBaseApiManager;
    public final DatabaseHelperVoucher mDatabaseHelperVocher;

    @Inject
    public DataManagerVoucher(BaseApiManager mBaseApiManager, DatabaseHelperVoucher mDatabaseHelperVocher) {
        this.mBaseApiManager = mBaseApiManager;
        this.mDatabaseHelperVocher = mDatabaseHelperVocher;
    }

    public Observable<RedeemVoucherResponse> redeemVoucher(RedeemVoucherRequestBody requestBody) {
        return mBaseApiManager.getVoucherRedemptionService().redeemVoucher(requestBody);
    }
}
