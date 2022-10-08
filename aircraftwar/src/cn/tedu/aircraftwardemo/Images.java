package cn.tedu.aircraftwardemo;
import javax.swing.*;
import java.util.Arrays;

/**
 * 用于加载飞机大战中所用到的所有图片资源
 * 定义静态变量
 * 1. 单张图片使用静态变量
 * 2. 多张图片利用静态数组
 * 定义静态代码块来给属性复制
 */
public class Images {
    //定义图片变量
    protected static ImageIcon[] airPlaneImages;//小敌机图片
    protected static ImageIcon[] bigPlaneImages;//大敌机图片
    protected static ImageIcon[] beeImages;//小蜜蜂图片
    protected static ImageIcon[] heroImages;//英雄机图片
    protected static ImageIcon[] bomImages;//爆炸图片
    protected static ImageIcon backgroundImage;//背景图片
    protected static ImageIcon bulletImage;//子弹图片
    protected static ImageIcon startImage;//开始图片
    protected static ImageIcon pauseImage;//暂停图片
    protected static ImageIcon gameoverImage;//结束图片

    //图片变量初始化
    static {
        //加载小敌机
        airPlaneImages = new ImageIcon[2];
        for (int i = 0; i < airPlaneImages.length; i++) {
            airPlaneImages[i] = new ImageIcon("aircraftwar/images/airplane" + i + ".png");
        }
        //加载大敌机
        bigPlaneImages = new ImageIcon[2];
        for (int i = 0; i < bigPlaneImages.length; i++) {
            bigPlaneImages[i] = new ImageIcon("aircraftwar/images/bigairplane" + i + ".png");
        }
        //加载小蜜蜂
        beeImages = new ImageIcon[2];
        for (int i = 0; i < beeImages.length; i++) {
            beeImages[i] = new ImageIcon("aircraftwar/images/bee" + i + ".png");
        }
        //加载英雄机
        heroImages = new ImageIcon[2];
        for (int i = 0; i < heroImages.length; i++) {
            heroImages[i] = new ImageIcon("aircraftwar/images/hero" + i + ".png");
        }
        //加载爆炸图片
        bomImages = new ImageIcon[4];
        for (int i = 0; i < bomImages.length; i++) {
            bomImages[i] = new ImageIcon("aircraftwar/images/bom" + (i + 1) + ".png");
        }

        backgroundImage = new ImageIcon("aircraftwar/images/background.png");
        bulletImage = new ImageIcon("aircraftwar/images/bullet.png");
        startImage = new ImageIcon("aircraftwar/images/start.png");
        pauseImage = new ImageIcon("aircraftwar/images/pause.png");
        gameoverImage = new ImageIcon("aircraftwar/images/gameover.png");
    }

    public  static void main(String[] args) {
        for (int i = 0; i < airPlaneImages.length; i++) {
            System.out.println("小敌机"+airPlaneImages[i].getImageLoadStatus());
        }
        for (int i = 0; i < bigPlaneImages.length; i++) {
            System.out.println("大敌机"+bigPlaneImages[i].getImageLoadStatus());
        }
        for (int i = 0; i < beeImages.length; i++) {
            System.out.println("小蜜蜂"+beeImages[i].getImageLoadStatus());
        }
        for (int i = 0; i < heroImages.length; i++) {
            System.out.println("英雄机"+heroImages[i].getImageLoadStatus());
        }
        for (int i = 0; i < bomImages.length; i++) {
            System.out.println("爆炸"+bomImages[i].getImageLoadStatus());
        }
        System.out.println("天空:"+backgroundImage.getImageLoadStatus());
        System.out.println("子弹:"+bulletImage.getImageLoadStatus());
        System.out.println("开始:"+startImage.getImageLoadStatus());
        System.out.println("暂定:"+pauseImage.getImageLoadStatus());
        System.out.println("结束:"+gameoverImage.getImageLoadStatus());
    }
}
