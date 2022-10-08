package cn.tedu.aircraftwardemo;

import javax.swing.*;

/**
 * 英雄子弹
 */
public class Bullet extends FlyingObject {

    //构造方法
    public Bullet(double x, double y) {
//        super(x,y,图片属性,动画帧,爆炸帧)
        super(x, y, Images.bulletImage, null, null);
        this.step = 2.5;
//        this.image = new ImageIcon("images/bullet.png");
    }

    public Bullet() {
    }

    //重写移动方法
    @Override
    public void move() {
        y -= step;

//        this.x = x-width/2;
//        this.y = y-height/2;
    }

}
