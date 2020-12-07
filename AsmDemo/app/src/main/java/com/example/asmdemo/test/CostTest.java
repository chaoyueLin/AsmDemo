package com.example.asmdemo.test;

import android.util.Log;

import com.example.asmdemo.base.ACost;

/*****************************************************************
 * * File: - CostTest
 * * Description: 
 * * Version: 1.0
 * * Date : 2020/8/7
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/8/7    1.0         create
 ******************************************************************/
public class CostTest {
    @ACost(name = "dd")
    public void test() {
        Log.d("const", "test");
    }
}
