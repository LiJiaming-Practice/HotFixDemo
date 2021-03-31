package com.example.dexlibs;

import javax.naming.Context;

public class ShowToastImpl implements IShowToast {
    @Override
    public int showToast(Context context) {
        Toast.makeText(context, "我来自另一个dex文件", Toast.LENGTH_LONG).show();
        return 100;
    }
}
