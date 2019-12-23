package com.scs.web.uni_space.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public static List<String> getImgSrc(String content) {

        List<String> list = new ArrayList<String>();

        //目前img标签标示有3种表达式
        //<img alt="" src="1.jpg"/>   <img alt="" src="1.jpg"></img>     <img alt="" src="1.jpg">
        //开始匹配content中的<img />标签
        Pattern p_img;
        p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");

        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                //获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2);

                //开始匹配<img />标签中的src
                Pattern p_src;
                p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    String str_src = m_src.group(3);
                    list.add(str_src);
                }
                //结束匹配<img />标签中的src

                //匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                result_img = m_img.find();
            }
        }
        return list;
    }
}
