package com.github.wuyuanyi135.huaweibypass;


import android.app.AndroidAppHelper;
import android.content.Context;
import android.widget.Toast;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!lpparam.packageName.equals("com.plagh.heartstudy")) {
            return;
        }
        XposedBridge.log("com.plagh.heartstudy loaded. Injected by huaweibypass");

        XposedHelpers.findAndHookMethod("com.plagh.heartstudy.view.activity.SplashActivity", lpparam.classLoader, "b", String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Context context = AndroidAppHelper.currentApplication();
                Toast.makeText(context, "Device Check Bypassed!", Toast.LENGTH_SHORT).show();
                param.setResult(true);
            }
        });
    }
}
