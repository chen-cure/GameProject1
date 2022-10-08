package cn.tedu.aircraftwardemo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 向下飞行物抽象类
 * 小蜜蜂,小敌机,大敌机随机产生
 */
public abstract class Plane extends FlyingObject {

    //向下飞行物抽象移动方法
    @Override
    protected void move() {
        y += step;
    }

    //1. 添加无参构造避免子类继承错误
    public Plane() {
    }

    //2. 根据位置初始化对象数据
    public Plane(double x, double y, ImageIcon image, ImageIcon[] images, ImageIcon[] boms) {
        super(x, y, image, images, boms);
    }

    //3. 利用算法实现飞机从上方出现
    public Plane(ImageIcon image, ImageIcon[] images, ImageIcon[] boms) {
        this.image = image;
        this.width = image.getIconWidth();
        this.height = image.getIconHeight();

        Random random = new Random();
        x = random.nextInt(400 - (int) width);
        y = -height;
        this.images = images;
        this.boms = boms;
        this.step = Math.random() * 4 + 1;
    }
}
