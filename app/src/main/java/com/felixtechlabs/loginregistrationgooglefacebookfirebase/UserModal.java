package com.felixtechlabs.loginregistrationgooglefacebookfirebase;

public class UserModal {
    final String mId, mFname, mPassword, mMobile;

    public UserModal(String mId, String mFname, String mPassword, String mMobile) {
        this.mId = mId;
        this.mFname = mFname;
        this.mPassword = mPassword;
        this.mMobile = mMobile;
    }

    public String getmId() {
        return mId;
    }

    public String getmFname() {
        return mFname;
    }

    public String getmPassword() {
        return mPassword;
    }

    public String getmMobile() {
        return mMobile;
    }
}