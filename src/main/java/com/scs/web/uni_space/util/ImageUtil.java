package com.scs.web.uni_space.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName ImageUtil
 * @Description 图形工具
 * @Author xiaobinggan
 * @Date 2019/12/3 9:49 上午
 * @Version 1.0
 **/
public class ImageUtil {
    public static BufferedImage getImage(String content, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        Color foreColor = new Color(243, 43, 41);
        Color backgroundColor = new Color(255, 255, 255);
        graphics2D.setColor(backgroundColor);
        graphics2D.drawRect(0, 0, width, height);

        graphics2D.setPaint(foreColor);
        graphics2D.setFont(new Font("Helvetica", Font.ITALIC, 28));
        graphics2D.drawString(content, width / 2, height / 2);
        return image;
    }

    public static void main(String[] args) throws IOException {
        //生成验证码
        String content = StringUtil.getRandomCode();
        //生成图片
        BufferedImage image = ImageUtil.getImage(content, 200, 100);
        //将image通过字节输出六输出到指定目录
        File file = new File("/Users/xiaobinggan/Pictures/code.jpg");
        ImageIO.write(image, "jpg", file);
    }
}
