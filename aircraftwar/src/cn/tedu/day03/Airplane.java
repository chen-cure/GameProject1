package cn.tedu.day03;

import javax.swing.*;
import java.util.Random;

/**
 * 敌飞机类    数据： x  y  宽  高  速度  step
 */
public class Airplane extends Plane implements Enemy{

    //随机出场的方式
    public Airplane() {
//        Random r = new Random();//创建随机数对象
//        icon = Images.airplanes[0];//表示读取第一个airplanes图片为默认呈现
//        this.width = icon.getIconWidth();//获取图片宽
//        this.height = icon.getIconHeight();//获取图片高
//        this.images = Images.airplanes;//加载动画帧
//        this.boms = Images.boms;//加载爆炸图片
//        this.x = r.nextInt(400 - (int) (this.width));//x随机在 0~400-w的区间
//        y = -this.height;//在屏幕的上方 负一个飞机的高度        、
        //      默认图片             动画数组        爆炸动画数组
        super(Images.airplanes[0], Images.airplanes, Images.boms);
        this.step = Math.random() * (5 - 1) + 1;//随机速度
    }

    //自定义的出场方式
    public Airplane(double x, double y, double step) {
//        super(x, y, width, height);
//        this.step = step;
//        icon = Images.airplanes[0];//表示读取第一个airplanes图片
//        this.width = icon.getIconWidth();//获取图片宽
//        this.height = icon.getIconHeight();//获取图片高
//        this.images = Images.airplanes;//加载动画帧
//        this.boms = Images.boms;//加载爆炸图片

        //x  y      默认图片           动画数组        爆炸动画数组
        super(x, y, Images.airplanes[0], Images.airplanes, Images.boms);
        this.step = step;//速度
    }

    @Override
    public int getScore() {
        return 10;
    }
}
