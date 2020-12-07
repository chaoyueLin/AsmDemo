package com.example.asmdemo.base;

import java.util.HashMap;

/*****************************************************************
 * * File: - CostTimeUtil
 * * Description: 
 * * Version: 1.0
 * * Date : 2020/8/7
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/8/7    1.0         create
 ******************************************************************/
public class CostTimeUtil {
    private static HashMap<String, Cost> map = new HashMap();

    public static class Cost {
        private String funName;
        private long start;
        private long end;

        public Cost(String name) {
            this.funName = name;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public void setEnd(long end) {
            this.end = end;
        }

        public long cost() {
            return end - start;
        }
    }

    public static void setStartTime(String name, long start) {
        Cost cost = new Cost(name);
        cost.setStart(start);
        map.put(name, cost);
    }

    public static void setEndTime(String name, long end) {
        Cost cost = map.get(name);
        if (cost != null) {
            cost.setEnd(end);
        }
    }
}
