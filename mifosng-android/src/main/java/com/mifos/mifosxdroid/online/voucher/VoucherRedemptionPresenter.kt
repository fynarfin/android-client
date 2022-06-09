package com.mifos.mifosxdroid.online.voucher

import com.mifos.api.datamanager.DataManagerLoan
import com.mifos.api.datamanager.DataManagerVoucher
import com.mifos.mifosxdroid.base.BasePresenter
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class VoucherRedemptionPresenter  @Inject constructor(private val mDataManagerVoucher: DataManagerVoucher) : BasePresenter<VoucherRedemptionMVPView?>() {

    private val mSubscriptions: CompositeSubscription = CompositeSubscription()

    override fun attachView(mvpView: VoucherRedemptionMVPView?) {
        super.attachView(mvpView)
    }

    override fun detachView() {
        super.detachView()
        mSubscriptions.unsubscribe()
    }

    fun redeemVoucher() {

    }

}