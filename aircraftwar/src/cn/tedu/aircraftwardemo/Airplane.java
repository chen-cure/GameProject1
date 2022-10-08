package cn.tedu.aircraftwardemo;

import javax.swing.*;

/**
 * 敌方小飞机
 */
public class Airplane extends Plane implements Enemy{

    //自定义位置出场
    public Airplane(double x, double y, double step) {
        super(x, y, Images.airPlaneImages[0], Images.airPlaneImages, Images.bomImages);
        this.step = step;
//        this.image = new ImageIcon("images/airplane0.png");
//        images = Images.airPlaneImages;
//        boms = Images.bomImages;
    }

    //随机出场
    public Airplane() {
        //构造3 super(图片,动画帧,爆炸)
        super(Images.airPlaneImages[0], Images.airPlaneImages, Images.bomImages);
    }

    @Override
    public int getScore() {
        return 5;
    }
}
