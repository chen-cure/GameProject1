package cn.tedu.aircraftwardemo;

import javax.swing.*;

/**
 * 英雄飞机
 */
public class Hero extends FlyingObject {

    private double doublefile = 0;

    public void doublefire() {
        doublefile = 20;//设置20
    }


    //构造方法
    public Hero(double x, double y) {
        super(x, y, Images.heroImages[0], Images.heroImages, Images.bomImages);
//        this.image = new ImageIcon("images/hero0.png");
    }

    //构造方法的重载
    public Hero() {
    }

    /**
     * 移动方法
     *
     * @param x 鼠标位置X
     * @param y 鼠标位置Y
     */
    public void move(int x, int y) {

        this.x = x - width / 2;
        this.y = y - height / 2;
    }

    //重写父类的移动方法
    @Override
    public void move() {
    }

    //射击方法(1颗)
    public Bullet fire() {
        //找到子弹的x,y
        double x = this.x + width / 2 - 6;
        double y = this.y + height-150;

        return new Bullet(x, y);
    }

    //射击方法(2颗)
    public Bullet[] doubleFire() {
        Bullet[] bullets = new Bullet[2];
        //1.判断是否是双倍火力的状态
        if (doublefile > 0) {
            doublefile--;
            double x1 = this.x + width / 2 -2;
            double y = this.y + height-150;
            bullets[0] = new Bullet(x1, y);
            double x2 = this.x + width / 2 - 10;
            bullets[1] = new Bullet(x2, y);

            return bullets;
        } else {
            Bullet b = fire();
            return new Bullet[] {b};
        }

    }
}
