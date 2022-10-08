package cn.tedu.aircraftwardemo;

//import cn.tedu.day02.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static cn.tedu.aircraftwardemo.Award.DOUBLE_FILE;
import static cn.tedu.aircraftwardemo.Award.LIFE;

/**
 * Swing是Java中内嵌的一套图形用户界面的API,可以用它来快速构建桌面应用程序
 * JFrame类
 * JPanel类
 */
public class World extends JPanel {
    //1.加载图片到内存
    /**
     * ImageIcon
     * 根据字节数组创建一个ImageIcon,包含png,jpg,gif
     * 1.相对路径(推荐)
     * 相对于项目文件所在的文件夹
     * 2.绝对路径
     * 是指文件存放在硬盘中的完整路径
     */

    //添加飞机大战中的对象(对象有单个用变量，多个用)
    private FlyingObject[] planes;
    private Bullet[] bullets;
    private Sky sky;
    private Hero hero;


    private int scores;
    private int life=2;

    public static final int READY = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;

    private int state = READY;

    //添加无参构造给属性赋值
    public World() {
        //使用多态数组的好处,可以管理不同的类
        planes = new Plane[0];

        bullets = new Bullet[0];

        sky = new Sky();
        hero = new Hero(160, 560);
    }

    //2.利用JPanel中所提供的绘制方法将图片绘制到面板上
    @Override
    public void paint(Graphics g) {
        /**
         * 参数this可以为null
         * 参数g--画笔
         * 参数x,y--要绘制的位置坐标
         * x:0~435
         */
        //绘制天空,英雄机,大敌机,小蜜蜂
        sky.paint(g);

        hero.paint(g);
        for (int i = 0; i < planes.length; i++) {

            planes[i].paint(g);
        }

        for (int i = 0; i < bullets.length; i++) {
            bullets[i].paint(g);
        }

        //绘制方法
        //g.setFont("样式",Font.BOLD,20);
        g.setColor(Color.GREEN);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString("SCORE:" + scores, 10, 20);
        g.drawString("LIFE:" + life, 10, 50);

        if (state == READY) {
            Images.startImage.paintIcon(null, g, 0, 0);
        }

        if (state == PAUSE) {
            Images.pauseImage.paintIcon(null, g, 0, 0);
        }

        if (state==GAME_OVER){
            Images.gameoverImage.paintIcon(null, g, 0, 0);
        }
    }

    //定义飞行物的移动方法
    public void planeMove() {
        //小敌机的移动
        for (int i = 0; i < planes.length; i++) {
            if (planes[i].isLiving()) {
                planes[i].move();
            }
        }

        //子弹的移动
        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i].isLiving()) {
                bullets[i].move();
            }
        }
        sky.move();
        //重绘
        repaint();
    }

    //创建飞行物方法
    private int index = 0;

    public void createPlane() {
//        index++;
        if (index % 30 == 0) {
            index++;
            Random random = new Random();
            int n = random.nextInt(10);
            Plane plane;
            if (n <= 6) {
                plane = new Airplane();
            } else if (n > 6 & n <= 8) {
                plane = new Bigplane();
            } else {
                plane = new Bee();
            }
            planes = Arrays.copyOf(planes, planes.length + 1);
            planes[planes.length - 1] = plane;
        }
    }

    private int index1 = 0;

    //创建子弹
    public void createBullet() {
        if(!hero.isLiving()){ //如果是死亡状态
            return; //退出当前方法
        }
        index1++;
        if (index1 % 15 == 0) {
            Bullet[] bubu = hero.doubleFire();
            Bullet[] arr = Arrays.copyOf(bullets, bubu.length + bullets.length);
            System.arraycopy(bubu, 0, arr, bullets.length, bubu.length);
            bullets = arr;
        }
    }

    /**
     * 定义内部类来执行飞机移动
     * 目的:可以共享wolrd
     */
    class MoveTask extends TimerTask {

        @Override
        public void run() {
            index++;
            if (state == RUNNING) {
                //飞行物的移动
                if (index%20==0)
                    planeMove();
                //创建飞行物
                createPlane();
                for (int i = 0; i < planes.length; i++) {
                    if (planes[i].isLiving()) {//如果是活着的状态
                        planes[i].move();//移动
                    }
                }
                //创建子弹
                createBullet();
                for (int i = 0; i < bullets.length; i++) {
                    if (bullets[i].isLiving()) {//如果是活着的状态
                        bullets[i].move();//移动
                    }
                }
                //检测飞行物与子弹是否发生碰撞
                hitDectection();
                //检测飞行物与英雄机是否发生碰撞
                hitHeroDectection();
                //清除掉僵尸状态的飞机
                clean();

            }
            sky.move();
            //调用重绘
            repaint();
        }
    }

    //飞机碰撞方法
    //检测飞行物与子弹是否发生碰撞
    public void hitDectection() {
        //先拿一架飞机
        for (int i = 0; i < planes.length; i++) {
            if (!planes[i].isLiving()) {
                continue;
            }
            //先拿一个子弹
            for (int j = 0; j < bullets.length; j++) {
                if (!bullets[j].isLiving()) {
                    continue;
                }
                if (planes[i].duang(bullets[j]) ) {
                    //System.out.println("1");
                    bullets[j].goDie();
                    planes[i].hit();

                    /**
                     * 1.发生爆炸
                     * 2.敌机死亡
                     * 3.消除死亡敌机
                     */
                    //得分
                    scores(planes[i]);
                    //奖励

                }
            }
        }

    }

    //检测飞行物与英雄机是否发生碰撞
    public void hitHeroDectection() {
        //先拿一架飞机
            if (hero.isLiving()) {
                //先拿一个子弹
                for (int j = 0; j < planes.length; j++) {
                    if (!planes[j].isLiving()) {
                        continue;
                    }
                    if(planes[j].duang(hero) ){
                        hero.goDie();
                        planes[j].goDie();
                        break;
                    }
                        /**
                         * 1.发生爆炸
                         * 2.敌机死亡
                         * 3.消除死亡敌机
                         */
                }
            }else if (hero.isZombie()){
                if (life>0){
                    hero =new Hero(150,560);
                    //清除掉当前全部飞机
                    for (int i = 0; i < planes.length; i++) {
                        planes[i].goDie();
                    }
                    life--;
                    if (life==0){
                        state=GAME_OVER;
                        hero.goDie();
                        for (int i = 0; i < bullets.length; i++) {
                            bullets[i].goDie();
                        }
                    }
                }
            }



    }

    //清除掉僵尸状态的飞机
    public void clean() {
        //先清除飞机
        //新数组长度为原数组长度,默认为null
        FlyingObject[] plane = new FlyingObject[planes.length];
        //index  变量
        int index = 0;
        //从原有数组中开始遍历
        for (int i = 0; i < planes.length; i++) {
            if (planes[i].isZombie() || planes[i].outOfBound()) {//如果是僵尸就跳过去
                continue;
            }
            //如果不是,则加入
            plane[index++] = planes[i];

        }
        //对数组缩容 长度index
        planes = Arrays.copyOf(plane, index);


        Bullet[] arr = new Bullet[bullets.length];
        index = 0;
        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i].isDead() || bullets[i].outOfBound()) {//如果是僵尸就跳过去
                continue;

            }
            //如果不是,则加入
            arr[index++] = bullets[i];

        }
        bullets = Arrays.copyOf(arr, index);
    }

    //用来积分
    public void scores(FlyingObject flyingObject) {
        //先判断飞机是否死亡---true
        if (flyingObject.isDead()) {
            if (flyingObject instanceof Enemy) {
                Enemy enemy = (Enemy) flyingObject;
                scores += enemy.getScore();
            }
            if (flyingObject instanceof Award) {
                Award award = (Award) flyingObject;
                int type = award.getAward();
                if (type == DOUBLE_FILE) {
//                    Hero. = 20;
                    hero.doublefire();
                } else if (type == LIFE) {
                    life++;
                }
            }
        }
    }

    //封装定时器到方法中
    public void action() {
        //实例化定时器
        Timer timer = new Timer();
        //实例化定时器任务类
        MoveTask moveTask = new MoveTask();
        //执行定时器任务
        timer.schedule(moveTask, 1000, 1000 / 100);

/*        //创建鼠标事件
        Mouse mouse=new Mouse();
        this.addMouseMotionListener(mouse);*/
        //匿名内部类监听事件
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {//
                if (state == RUNNING) {

                    //需要将鼠标坐标赋值给英雄机
                    int x = e.getX();
                    int y = e.getY();
                    //System.out.println("优化了代码~~~~");
                    hero.move(x, y);
                }
            }
        };
        this.addMouseMotionListener(mouseAdapter);

        //
        this.addMouseListener(new MouseAdapter() {
            //鼠标点击
            @Override
            public void mouseClicked(MouseEvent e) {
                if (state == READY) {
                    state = RUNNING;
                }else if (state == GAME_OVER){
                    scores=0;
                    life=3;
                    planes=new FlyingObject[0];
                    bullets=new Bullet[0];
                    hero=new Hero(150,560);
                    state = READY;
                }
            }
            //鼠标移入

            @Override
            public void mouseEntered(MouseEvent e) {
                if (state == PAUSE) {
                    state = RUNNING;
                }
            }

            //鼠标移出

            @Override
            public void mouseExited(MouseEvent e) {
                if (state == RUNNING) {
                    state = PAUSE;
                }
            }

        });

    }

    //3.显示窗口
    public static void main(String[] args) {
        //实例化JFrame类
        JFrame jFrame = new JFrame();
        //设置窗体的标题
        jFrame.setTitle("飞机大战");
        //设置窗体大小
        jFrame.setSize(435, 740);
        //设置默认显示的位置
        jFrame.setLocationRelativeTo(null);
        //设置默认关闭
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //实例化面板容器类,添加到窗体中
        World jPanel = new World();
        jFrame.add(jPanel);
        //显示窗体可见
        jFrame.setVisible(true);
        //执行移动
        jPanel.action();
    }


}
