package com.qianzhimu.api.utils;

import com.qianzhimu.api.constant.Constant;
import com.qianzhimu.api.entity.TradeMarker;
import me.majiajie.tinypinyin.Pinyin;

public class PinyinHelper{
    public static String toPinyin(String chinese) {
        return Pinyin.toPinyin(chinese, "");
    }

    public static void main(String[] args) {
       /* System.out.println("中文:");
        int[] b = new int[]{2, 3, 6, 7};
        for (int num : b) {
            System.out.println((num >> 1) & 1);
        }
        System.out.println("英文：");
        b = new int[]{1, 3, 5, 7};
        for (int num : b) {
            System.out.println(num & 1);
        }

        System.out.println("数字：");
        b = new int[]{4, 5, 6, 7};
        for (int num : b) {
            System.out.println((num >> 2) & 1);
        }*/

        TradeMarker tm = new TradeMarker();

        tm.setName("拼音abc");

        System.out.println(tm.getContentType());
        System.out.println(tm.getLengthRanger());
    }
}
