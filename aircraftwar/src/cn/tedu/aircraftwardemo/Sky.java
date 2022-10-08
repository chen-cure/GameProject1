package cn.tedu.aircraftwardemo;

import javax.swing.*;
import java.awt.*;

/**
 * 天空类
 */
public class Sky extends FlyingObject {
    //第二张图片的Y轴坐标
    private double y0;

    //构造方法
    public Sky() {
        super(0, 0, Images.backgroundImage, null, null);
        this.step = 0.8;
//        this.image = new ImageIcon("images/background0.png");
//        this.y = 0;
        this.y0 = -height;
        this.height=image.getIconHeight();
        this.width=image.getIconWidth();
    }

    //重写move
    @Override
    public void move() {
        y += step;
        y0 += step;
//        y = y >= height ? -height : y;
//        y0 = y0 >= height ? -height : y0;
        if (y > height) {
            y = -height;
        }
        if (y0 > height) {
            y0 = -height;
        }
    }

    //重写绘制方法
    @Override
    public void paint(Graphics g) {
        image.paintIcon(null, g, (int) x, (int) y);
        image.paintIcon(null, g, (int) x, (int) y0);
    }

}
