package cn.tedu.aircraftwardemo;

import javax.swing.*;
import java.awt.*;

/**
 * 根据子类所具备共同的共同方法和属性所归纳的父类
 */
public abstract class FlyingObject {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double step;
    protected ImageIcon icon;

    protected ImageIcon image; //每个子类所具备的图片属性
    protected ImageIcon[] images;//存储动画帧,没有动画帧的直接给null
    protected ImageIcon[] boms;//爆炸图片

    protected int index = 0;

    //静态常量,飞行物状态
    //活着  状态   1
    //死亡  状态   0
    //僵尸  状态   1
    protected static final int ALIVE = 1;
    protected static final int DIE = 0;
    protected static final int CORPSE = -1;

    //飞行物状态默认为  活着
    protected int state = ALIVE;

    //定义飞行物生命     1
    protected int life = 1;


    //带参构造
    public FlyingObject(double x, double y, ImageIcon image, ImageIcon[] images, ImageIcon[] boms) {
        this.x = x;
        this.y = y;
        this.image = image;//图片属性
        this.images = images;//动画帧属性
        this.boms = boms;//爆炸帧属性
        //初始化宽高
        this.width = image.getIconWidth();
        this.height = image.getIconHeight();
    }

    //无参构造
    public FlyingObject() {
    }

    //    //移动
//    protected void move() {
//        y += step;
//
//    }
    //抽象移动方法
    protected abstract void move();

    //封装绘制方法
    protected void paint(Graphics g) {
        nextImages();//切换动画帧
        image.paintIcon(null, g, (int) x, (int) y);
    }

    /**
     * 飞行物活着    播放动画
     * 死亡     爆炸
     * 当爆炸之后   僵尸
     */
    protected int i = 0;

    //切换动画帧方法
    public void nextImages() {
        switch (state) {
            case ALIVE:
                if (images == null) {
                    return; //跳出方法 后面不执行
                }
                image = images[(index++ / 20) % images.length];//根据index的值来决定当前显示哪张图
                break;
            case DIE:
                int bomsIndex = i++ / 5;
//                System.out.println(bomsIndex);
                if (boms == null) {
                    return;//跳出方法 后面不执行
                }
                if (bomsIndex == boms.length) {//如果bomsIndex等于爆炸动画数组的长度 ，那么不能再继续播放了！
                    state = CORPSE;//设置为僵尸状态
                    return;//跳出方法 后面不执行
                }
                image = boms[bomsIndex];//依次播放 爆炸动画
                break;
        }
    }

    @Override
    public String toString() {
        //获取类名
        String className = getClass().getName();
        return className + "{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", step=" + step +
                '}';
    }

    /**
     * 定义碰撞方法
     *
     * @param bu
     * @return c<r1 + r2 - - - - ->  true
     */
    public boolean duang(FlyingObject bu) {
        FlyingObject p = this;
        //计算飞机数据   r1,x1,y1
        double r1 = Math.min(p.width, p.height) / 2;
        double x1 = p.x + p.width / 2;
        double y1 = p.y + p.height / 2;

        //计算子弹的数据   r2,x2,y2
        double r2 = Math.min(bu.width, bu.height) / 2;
        double x2 = bu.x + bu.width / 2;
        double y2 = bu.y + bu.height / 2;

        //圆心距
        double a = y2 - y1;
        double b = x2 - x1;
        double c = Math.sqrt(a * a + b * b);
        return c < r1 + r2;

    }

    /**
     * 定义打击方法
     *
     * @return
     */
    public boolean hit() {
        if (life > 0) {
            life--;
            if (life == 0) {
                state = DIE;
            }
            return true;
        }
        return false;
    }

    public boolean goDie() {
        if (state == ALIVE) {
            life = 0;
            state = DIE;
            return true;
        }
        return false;
    }

    /**
     * 封装3个方法检测飞行物的状态
     * isLiving
     * isDead
     * isZombie
     */
    public boolean isLiving() {
        return this.state == ALIVE;
    }

    public boolean isDead() {
        return this.state == DIE;
    }

    public boolean isZombie() {
        return this.state == CORPSE;
    }

    //判断出界
    //检测飞行物出界
    //y<(-height-50)---出界
    public boolean outOfBound(){

        if(y<(-height-50)){
            return true;
        }else if (y>750){
            return true;
        }
        return false;
    }

}
