package com.example.dexlib;

import android.content.Context;
import android.widget.Toast;

public class ShowToastImpl implements IShowToast {
    @Override
    public int showToast(Context context) {
        Toast.makeText(context, "我来自另一个dex文件", Toast.LENGTH_LONG).show();
        return 100;
    }
}