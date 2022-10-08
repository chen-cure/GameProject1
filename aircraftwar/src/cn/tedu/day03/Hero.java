package cn.tedu.day03;

import javax.swing.*;

/**
 * 英雄机
 */
public class Hero extends FlyingObject {

    public Hero(double x, double y) {
        super(x, y, Images.hero[0], Images.hero, Images.boms);
//        icon = Images.hero[0];//取hero数组第一张图片设为默认图片
//        this.width = icon.getIconWidth();
//        this.height = icon.getIconHeight();
//        this.images = Images.hero;//加载英雄机动画帧。
//        this.boms = Images.boms;//加载爆炸效果。
    }

    /*
     * 重写父类的move 方法  当前英雄机 不用 自行移动 是鼠标移动的
     *   所有 内容 不写即可！
     * */
    public void move() {
        //什么逻辑也不写
    }

    //这个方法才是后续会被调用的方法
    public void move(int x, int y) {
        this.x = x - width / 2; //将鼠标的位置减去 飞机宽度的一半
        this.y = y - height / 2; //将鼠标的位置减去 飞机高度的一半
    }

    //发射子弹的方法
    //思考    1.调用者需要给你什么内容吗？
    //      2.你需要返回给调用者什么内容吗？
    /*
     * 开火的方法 ： 每次被调用时则返回一颗子弹
     * */
    public Bullet fire() {
        //1.生成子弹要确定  X 和 y坐标
        double x = this.x + width / 2 - 5;//X在英雄机的正中间 减去子弹的宽度
        double y = this.y - 15; //y在英雄机的前方
        Bullet bullet = new Bullet(x, y);
        return bullet;
    }

    private int doubleFire = 0;//发射双枪的次数记录
    //如果英雄机吃到了双枪的奖励，那么应该将双枪次数设置 20
    public void doubleFire() {
        doubleFire = 20;//设置20
    }
    /**
     * 双枪的处理逻辑
     */
    public Bullet[] openFire() {
        if (doubleFire > 0) {//双枪的次数大于0 代表可以发射两颗子弹
            doubleFire--;//扣减一次双枪的次数
            double x = this.x + width / 2 - 5;
            double y = this.y - 15;
            Bullet b1 = new Bullet(x - 15, y);//第一个子弹
            Bullet b2 = new Bullet(x + 15, y);//第二个子弹
            return new Bullet[]{b1, b2};//返回这两颗子弹
        } else {//否则代表单枪
            Bullet b = fire();//调用之前写的返回一个子弹的方法
            return new Bullet[]{b};//将子弹返回
        }
    }

}
