package com.mifos.objects.voucher;

import android.os.Parcel;
import android.os.Parcelable;

public class RedeemVoucherRequestBody implements Parcelable {
    protected RedeemVoucherRequestBody(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RedeemVoucherRequestBody> CREATOR = new Creator<RedeemVoucherRequestBody>() {
        @Override
        public RedeemVoucherRequestBody createFromParcel(Parcel in) {
            return new RedeemVoucherRequestBody(in);
        }

        @Override
        public RedeemVoucherRequestBody[] newArray(int size) {
            return new RedeemVoucherRequestBody[size];
        }
    };
}
