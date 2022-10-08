package cn.tedu.day03;

import javax.swing.*;

/**
 * 子弹类
 * */
public class Bullet extends FlyingObject {

    public Bullet(double x,double y) {
        super(x,y,Images.bullet,null,null);
        this.step = 4;
    }

    public  void move(){
        y -= step;
    }
}
