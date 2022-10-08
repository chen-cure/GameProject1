package cn.tedu.aircraftwardemo;

import javax.swing.*;

/**
 * 小蜜蜂
 */
public class Bee extends Plane implements Award,Enemy{

    //构造方法
    public Bee(double x, double y, double step) {
        super(x, y, Images.beeImages[0], Images.beeImages, Images.bomImages);
        this.step = step;
//        this.image = new ImageIcon("images/bee0.png");
//        images = Images.beeImages;
//        boms = Images.bomImages;
    }

    //无参构造随机出场
    public Bee() {
        super(Images.beeImages[0], Images.beeImages, Images.bomImages);
    }

    @Override
    public int getAward() {
        //奖励随机  火力  生命

        return Math.random()>0.5?DOUBLE_FILE:LIFE;
    }

    @Override
    public int getScore() {
        return 5;
    }

    //重写移动方法
//    @Override
//    public void move() {
//        y += step;
//    }
}
