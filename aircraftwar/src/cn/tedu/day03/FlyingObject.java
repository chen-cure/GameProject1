package cn.tedu.day03;

import javax.swing.*;
import java.awt.*;

/**
 * 所有飞行物的父类 。
 */
public abstract class FlyingObject {
    public static final int Living = 1;//飞机活着的状态
    public static final int DEAD = 0;//死亡状态
    public static final int ZOMBIE = -1;//僵尸状态
    protected int state = Living;//初始状态
    protected int life = 1;//默认 都为1
    //将为子类提供的属性通过protected访问修饰符来修饰。
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double step;
    protected ImageIcon icon;
    protected ImageIcon[] images; //代表存储当前动画帧的图片，如果说没有动画帧图片 则赋值为null
    protected ImageIcon[] boms;   //代表爆炸动画效果的图片，没有则为null
    protected int index = 0;//播放动画计数器

    //父类无参数的构造器  防止子类的无参数构造器报错
    public FlyingObject() {
    }

    //有参数的构造器 供子类初始化数据
    FlyingObject(double x, double y, ImageIcon icon, ImageIcon[] images, ImageIcon[] boms) {
        this.x = x;
        this.y = y;
        this.width = icon.getIconWidth(); //获取图片宽
        this.height = icon.getIconHeight();//获取图片高
        this.icon = icon;//获取默认图片
        this.images = images;//获取动画帧数组
        this.boms = boms;//获取爆炸动画数组

    }

    //父类的移动方法  让子类去复用
    public abstract void move();

    /*
     *  打击的方法，每打击一次  扣减一次生命值  并返回true
     *  如果声明值扣减后为0 则设置为死亡状态，再次调用这个方法 则返回false 扣减不成功
     * */
    public boolean hit() {
        if (life > 0) {
            life--; //扣减
            if (life <= 0) {
                state = DEAD;//设置当前状态为死亡状态
            }
            return true;
        }
        return false;
    }


    /*
     *  立刻死亡的方法  主要给大飞机用的。
     * */
    public boolean goDead() {
        if (state == Living) {//如果状态是活着的话
            life = 0;
            state = DEAD;
            return true;
        }
        return false;
    }

    public boolean duang(FlyingObject bullet) { //判断当前传入子弹是否与飞机撞上的方法
        FlyingObject p = this;//哪个飞机对象调用了duang方法 那么this指的就是那个对象
        double r1 = Math.min(p.width, p.height) / 2;//获取飞机的内切圆半径
        double x1 = p.x + p.width / 2;
        double y1 = p.y + p.height / 2;
        double r2 = Math.min(bullet.width, bullet.height) / 2;
        double x2 = bullet.x + bullet.width / 2;
        double y2 = bullet.y + bullet.height / 2;
        double a = x2 - x1;//a边
        double b = y2 - y1;//b边
        double c = Math.sqrt(a * a + b * b);//开方求 c （两个圆心点直接的距离）
        return c < r1 + r2; // 若为true  则代表撞到了。
    }

    public String toString() {

        return getClass().getName() +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", step=" + step +
                '}';
    }

    /**
     * 切换动画帧图片的方法
     * 因为 不同状态下 播放的动画不同，那么此时需要通过判断来进行相应动画的播放
     */
    private int i = 0;//代表爆炸动画的切换 ”帧“

    public void nextImages() {
        switch (state) {
            case Living:
                if (images == null) {
                    return; //跳出方法 后面不执行
                }
                icon = images[(index++ / 20) % images.length];//根据index的值来决定当前显示哪张图
                break;
            case DEAD:
                int bomsIndex = i++ / 30;
//                System.out.println(bomsIndex);
                if (boms == null) {
                    return;//跳出方法 后面不执行
                }
                if (bomsIndex == boms.length) {//如果bomsIndex等于爆炸动画数组的长度 ，那么不能再继续播放了！
                    state = ZOMBIE;//设置为僵尸状态
                    return;//跳出方法 后面不执行
                }
                icon = boms[bomsIndex];//依次播放 爆炸动画
                break;
        }
    }

    public boolean isLiving() {//是否是活着的方法
        return state == Living;
    }

    public boolean isDead() {//是否是dead状态
        return state == DEAD;
    }

    public boolean isZombie() {//是否是僵尸状态
        return state == ZOMBIE;
    }

    public boolean outOfBounds(){//判断是否超出屏幕边界的方法
        if(y<(-height -50)){//如果当前对象y 小于 -一个图片高度-50
            return  true;
        }else if(y>(700+50)){//如果超出下边界
            return true;
        }
        else  return  false;
     //   return  y<(-height -50)|| y>(700+50);
    }


    //在父类中自定义 paint 方法  方便子类使用绘制方法 代表更加简洁！
    public void paint(Graphics g) {
        nextImages();//先 切换动画帧图片
        icon.paintIcon(null, g, (int) x, (int) y);//再绘制图片
    }

}
