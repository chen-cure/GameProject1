package cn.tedu.day03;

import javax.swing.*;
import java.util.Random;

/**
 * 敌飞机 、 大飞机、蜜蜂类的父类
 */
public abstract class Plane extends FlyingObject {

    //为plane加上无参构造器
    public Plane() {
    }
    //自定义的出场方式  有参构造器
    public Plane(double x, double y, ImageIcon icon, ImageIcon[] images, ImageIcon[] boms) {
        super(x, y, icon, images, boms);
    }
    //随机的出场方式    有参构造器
    public Plane(ImageIcon icon, ImageIcon[] images, ImageIcon[] boms) {
        Random r = new Random();//创建Random对象
        this.icon = icon;//设置初始默认的图片
        this.width = this.icon.getIconWidth();//获取宽
        this.height = this.icon.getIconHeight();//获取高
        x = r.nextInt(400 - (int) (this.width));//随机x   0~400-w
        y = -this.height;// 负一个图片的高度
        this.images = images;//赋值动画数组
        this.boms = boms;//赋值爆炸动画数组
    }

    @Override
    public void move() {
        y += step;
    }
}
