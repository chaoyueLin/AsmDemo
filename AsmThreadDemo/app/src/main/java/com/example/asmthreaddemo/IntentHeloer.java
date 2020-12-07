package com.example.asmthreaddemo;

import android.content.Intent;

/*****************************************************************
 * * File: - IntentHeloer
 * * Description: 
 * * Version: 1.0
 * * Date : 2020/9/2
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/2    1.0         create
 ******************************************************************/
public class IntentHeloer {
    public static String getSafeIntent(Intent intent, String key) {
        String value=null;
        try {
            value = intent.getStringExtra(key);
        } catch (Exception e) {

        }
        return value;
    }
}
