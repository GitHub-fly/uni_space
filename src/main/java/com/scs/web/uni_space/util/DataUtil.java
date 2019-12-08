package com.scs.web.uni_space.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

/**
 * @author xunmi
 * @ClassName DataUtil
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
@Component
public class DataUtil {

    private static Random random = new Random();

    /**
     * 获得电话号码
     *
     * @return
     */
    public static String getMobile() {
        StringBuilder mobile = new StringBuilder("139");
        for (int i = 0; i < 8; i++) {
            int num = random.nextInt(10);
            mobile.append(num);
        }
        return mobile.toString();
    }

    /**
     * 获取随机密码
     *
     * @return
     */
    public static String getPassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int num = random.nextInt(10);
            password.append(num);
        }
        return DigestUtils.md5Hex(password.toString());
    }

    /**
     * 获取随机性别
     *
     * @return
     */
    public static String getGender() {
        String[] genders = new String[]{"男", "女"};
        int index = random.nextInt(2);
        return genders[index];
    }

    /**
     * 获取随机生日数据
     *
     * @return
     */
    public static LocalDate getBirthday() {
        LocalDate now = LocalDate.now();
        int bound = random.nextInt(8888);
        return now.minusDays(bound);
    }

    /**
     * 获取随机星座
     */
    public static String getConstellation() {
        String[] constellations = {"白羊座", "金牛座", "双子座", "巨蟹座",
                "狮子座", "处女座", "天秤座", "天蝎座",
                "射手座", "摩羯座", "水瓶座", "双鱼座"};
        int i = random.nextInt(12);
        return constellations[i];
    }

    /**
     * 随机获取相册类型
     *
     * @return
     */
    public static String getType() {
        String[] types = {"励志", "田园风光", "", "亚洲", "欧洲",
                "建筑", "户外", "公园", "绘画", "休闲",
                "运动", "商务园", "风景", "微笑", "仰视",
                "传统", "安静", "健康", "现代", "思考",
                "特写", "白天", "彩色", "玩具", "登山",
                "线条", "边框", "生活", "室内", "文艺",};
        return types[random.nextInt(30)];
    }
}
