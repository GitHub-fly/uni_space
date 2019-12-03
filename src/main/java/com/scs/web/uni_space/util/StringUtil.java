package com.scs.web.uni_space.util;

import java.util.Random;

/**
 * @ClassName StringUtil
 * @Description 文本工具
 * @Author xiaobinggan
 * @Date 2019/12/3 9:24 上午
 * @Version 1.0
 **/
public class StringUtil {
    //获取随机生成6位验证码
    public static String getVerifyCode() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(String.valueOf(random.nextInt(10)));
        }
        return stringBuilder.toString();
    }

    //获取随机生成4位验证码
    public static String getRandomCode() {
        //设置随机数
        Random random = new Random();
        //设置字符
        char[] chars = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        // 获取4位随机数
        StringBuilder stringBuilder = new StringBuilder();
        int index;
        for (int i = 0; i < 4; i++) {
            //获取随机chars下标
            index = random.nextInt(chars.length);
            stringBuilder.append(chars[index]);
        }
        return stringBuilder.toString();
    }
}
