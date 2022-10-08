package cn.tedu.day03;

import javax.swing.*;

/**
 * 类的职责：用来初始化所有的图片资源。
 */
public class Images {
    //声明如果是单张图片 就直接一个类型变量存储
    //如果是多张图片  就用数组来存储。
    public static ImageIcon[] airplanes;
    public static ImageIcon[] bees;
    public static ImageIcon[] bigAirplanes;
    public static ImageIcon[] boms;
    public static ImageIcon[] hero;
    public static ImageIcon sky;
    public static ImageIcon bullet;
    public static ImageIcon gameOver;
    public static ImageIcon pause;
    public static ImageIcon start;

    //静态的变量初始化 放到静态代码块中去做
    static {
        airplanes = new ImageIcon[2];
        for (int i = 0; i < airplanes.length; i++) {
            airplanes[i] = new ImageIcon("aircraftwar/images/airplane"+i+".png");
        }

        bees = new ImageIcon[2];
        for (int i = 0; i <bees.length ; i++) {
            bees[i] = new ImageIcon("aircraftwar/images/bee"+i+".png");
        }
        bigAirplanes = new ImageIcon[2];
        for (int i = 0; i <bigAirplanes.length ; i++) {
            bigAirplanes[i] = new ImageIcon("aircraftwar/images/bigairplane"+i+".png");
        }
        boms = new ImageIcon[4];
        for (int i = 0; i <boms.length ; i++) {
            boms[i] = new ImageIcon("aircraftwar/images/bom"+(i+1)+".png");
        }
        hero = new ImageIcon[2];
        for (int i = 0; i < hero.length; i++) {
            hero[i] = new ImageIcon("aircraftwar/images/hero"+i+".png");
        }
        sky = new ImageIcon("aircraftwar/images/background.png");
        bullet = new ImageIcon("aircraftwar/images/bullet.png");
        pause = new ImageIcon("aircraftwar/images/pause.png");
        start = new ImageIcon("aircraftwar/images/start.png");
        gameOver = new ImageIcon("aircraftwar/images/gameover.png");
    }

    public static void main(String[] args) {
        for (int i = 0; i < airplanes.length; i++) {
            System.out.println( airplanes[i].getImageLoadStatus());
        }
        for (int i = 0; i <bees.length ; i++) {
            System.out.println( bees[i].getImageLoadStatus());
        }
        for (int i = 0; i <bigAirplanes.length ; i++) {
            System.out.println( bigAirplanes[i].getImageLoadStatus());
        }
        for (int i = 0; i <boms.length ; i++) {
            System.out.println( boms[i].getImageLoadStatus());
        }
        for (int i = 0; i < hero.length; i++) {
            System.out.println( hero[i].getImageLoadStatus());
        }
        System.out.println( sky.getImageLoadStatus());
        System.out.println( gameOver.getImageLoadStatus());
        System.out.println( bullet.getImageLoadStatus());
        System.out.println( pause.getImageLoadStatus());
        System.out.println( start.getImageLoadStatus());
    }
}
