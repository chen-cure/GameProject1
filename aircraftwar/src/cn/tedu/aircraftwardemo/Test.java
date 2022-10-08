package cn.tedu.aircraftwardemo;

import java.awt.image.Kernel;

public class Test {
    public static void main(String[] args) {
        Airplane airplane = new Airplane();
        Bigplane bigplane = new Bigplane();
        Bee bee = new Bee();
        Bullet bullet = new Bullet();
        Hero hero = new Hero();
        Sky sky = new Sky();

        System.out.println(airplane);

        System.out.println(bigplane);
        System.out.println(bee);
        System.out.println(bullet);
        System.out.println(hero);
        System.out.println(sky);


        // 8--表示图片成功加载到内存中 4--表示加载未成功
//        System.out.println(airplane.image.getImageLoadStatus());
//        Bigplane bigplane = new Bigplane(30,40,50,50,2);
//        System.out.println(bigplane.image.getImageLoadStatus());
//        Bee bee = new Bee(20,30,50,50,4);
//        System.out.println(bee.image.getImageLoadStatus());
//        Bullet bullet = new Bullet(20,40,30,30,3);
//        System.out.println(bullet.image.getImageLoadStatus());
//        Hero hero = new Hero(20,10,30,30);
//        System.out.println(hero.image.getImageLoadStatus());
//        Sky sky =new Sky(20,10,30,30,4);
//        System.out.println(sky.image.getImageLoadStatus());

    }
}
