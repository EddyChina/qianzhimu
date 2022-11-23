package com.qianzhimu.api.utils;

import me.majiajie.tinypinyin.Pinyin;

public class PinyinHelper{
    public static String toPinyin(String chinese) {
        return Pinyin.toPinyin(chinese, "");
    }
}
