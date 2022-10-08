package cn.tedu.aircraftwardemo;

import javax.swing.*;

/**
 * 地方打飞机
 */
public class Bigplane extends Plane implements Enemy{


    //构造方法
    public Bigplane(double x, double y, double step) {
        super(x, y, Images.bigPlaneImages[0], Images.bigPlaneImages, Images.bomImages);
        this.step = step;
//        this.life = 5;
//        this.image = new ImageIcon("images/bigairplane0.png");
//        images = Images.bigPlaneImages;
//        boms = Images.bomImages;
    }

    //随机出场
    public Bigplane() {
        super(Images.bigPlaneImages[0], Images.bigPlaneImages, Images.bomImages);
        this.life = 5;
    }

    @Override
    public int getScore() {

        return 10; //打中一次10分
    }
}
