package cn.tedu.day03;

import javax.swing.*;
import java.util.Random;

/**
 * 大飞机类
 */
public class BigAirplane extends Plane implements Enemy {
    //随机出场方式
    public BigAirplane() {
//        Random r = new Random();//创建随机数对象
//        icon = Images.bigAirplanes[0];//表示读取第一个airplanes图片为默认呈现
//        this.width = icon.getIconWidth();//获取图片宽
//        this.height = icon.getIconHeight();//获取图片高
//        this.images = Images.bigAirplanes;//加载动画帧
//        this.boms = Images.boms;//加载爆炸图片
//        this.x = r.nextInt(400 - (int) (this.width));//x随机在 0~400-w的区间
//        y = -this.height;//在屏幕的上方 负一个飞机的高度
        //        默认图片             动画数组            爆炸动画数组
        super(Images.bigAirplanes[0], Images.bigAirplanes, Images.boms);
        this.step = Math.random() * (3 - 0.7) + 0.7;//随机大飞机速度
        life = 5; //大飞机生命值设置为5
    }
    //自定义出场方式
    public BigAirplane(double x, double y, double step) {
        super(x, y, Images.bigAirplanes[0], Images.bigAirplanes, Images.boms);
        this.step = step;
        life = 5;//大飞机生命值设置为5
    }
    @Override
    public int getScore() {
        return 40;
    }
}
