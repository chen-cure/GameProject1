package cn.tedu.day03;


import com.sun.javaws.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 测试和运行游戏的类
 */
public class GameWorld extends JPanel {
    public static final int READY = 0;//准备状态
    public static final int RUNNING = 1;//运行状态
    public static final int PAUSE = 2;//暂停状态
    public static final int GAME_OVER = 3; //结束状态
    //当前状态  默认为  准备状态
    private int currentState = READY;
    //用于给打掉的敌飞机或大飞机做一个加分的方法
    private int score = 0;//代表当前游戏累加的分数
    //声明各种类型数组
//    private Airplane[] airplanes;
//    private BigAirplane[] bigAirplanes;
    private FlyingObject[] planes;//声明父类型数组 来存储不同的具体子类对象
    private Bullet[] bullets;
    private Hero hero;
    private Sky sky;
    private int life = 3;//代表当前英雄机的生命
    //无参构造器
    GameWorld() {
        sky = new Sky();
        planes = new FlyingObject[0];//创建数组对象  但是该对象内没有开辟空间
        bullets = new Bullet[0];    //创建数组对象  但是该对象内没有开辟空间
        hero = new Hero(150, 400);
    }

    public void paint(Graphics g) {
        sky.paint(g);//绘制天空一定要在绘制方法的第一行！
        hero.paint(g);
        for (int i = 0; i < planes.length; i++) {
            planes[i].paint(g);
        }
        for (int i = 0; i < bullets.length; i++) {
            bullets[i].paint(g);
        }
        g.setColor(Color.white);//设置为白色
                        // 1.字体    2.字体风格    3.字体大小
        g.setFont(new Font("宋体",Font.BOLD,20));
        g.drawString("SCORE:" + score, 20, 40);
        g.drawString("LIFE:"+life,20,60);
        if(currentState == READY){//如果是准备状态
            Images.start.paintIcon(this,g,0,0);//绘制准备状态的图片
        }
        if(currentState == PAUSE){//如果是暂停状态
            Images.pause.paintIcon(this,g,0,0);//绘制暂停状态的图片
        }
        if(currentState == GAME_OVER){//如果是结束状态
            Images.gameOver.paintIcon(this,g,0,0);//绘制游戏结束状态的图片
        }
    }

    //执行
    public void action() {
        //1.创建具体定时器
        Timer timer = new Timer();
        //2.创建具体任务
        MyTask task = new MyTask();
        //3.给定时器添加具体任务
        timer.schedule(task, 1000, 1000 / 100);
//---------------------以下是鼠标移动的逻辑----------------------------------------------
        MouseAdapter mouse = new MouseAdapter() {//创建子类 并实现了继承！
            @Override
            //通过方法中形参 e 来获取鼠标的X 和鼠标的 Y
            public void mouseMoved(MouseEvent e) {
                if(currentState == RUNNING) { //如果是运行状态下
                    int x = e.getX();//获取鼠标的x
                    int y = e.getY();//获取鼠标的y
                    if(hero.isLiving()) { //如果英雄机是活着的状态
                        hero.move(x, y); //再移动
                    }
                }
            }
        };
        //添加到检测的代码中
        addMouseMotionListener(mouse);
//---------------------以下是鼠标单击的逻辑----------------------------------
       //使用匿名内部类 来创建 具体子类的逻辑
        MouseAdapter mouseClick = new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(currentState == READY){//如果当前状态是准备状态的话
                    currentState = RUNNING;//将当前状态修改为运行状态！
                }
                else if(currentState == GAME_OVER){//如果当前状态是GameOver的话
                    score = 0;  //重置分数
                    life =3;  //重置
                    planes = new FlyingObject[0];
                    bullets = new Bullet[0];
                    hero = new Hero(150, 400);
                    currentState = READY;//重置为准备状态
                }
            }
        };
        addMouseListener(mouseClick);
//---------------------以下是鼠标移入当前游戏界面和离开游戏界面离开的逻辑--------------------------------
        addMouseListener(
                new MouseAdapter() {
                    @Override  //鼠标移出界面时所执行的方法
                    public void mouseExited(MouseEvent e) {
                        if(currentState == RUNNING){ //如果状态为 运行状态
                            currentState = PAUSE; //切换为暂停状态
                        }
                    }
                    @Override //鼠标移入界面所执行的方法
                    public void mouseEntered(MouseEvent e) {
                        if(currentState == PAUSE){ //如果状态为 暂停状态
                            currentState = RUNNING;//切换为运行状态
                        }
                    }
                }
        );
    }

    private int index = 0;//代表创建的速度

    //myTask 是 GameWorld的内部类
    class MyTask extends TimerTask {
        public void run() {
            index++;
            if(currentState == RUNNING) {
                createBullte();//调用创建子弹的方法
                //间隔16帧 执行1次创建
                createPlane();//调用创建飞机的方法
                for (int i = 0; i < planes.length; i++) {
                    if (planes[i].isLiving()) {//如果是活着的状态
                        planes[i].move();//移动
                    }
                }
                for (int i = 0; i < bullets.length; i++) {
                    if (bullets[i].isLiving()) {//如果是活着的状态
                        bullets[i].move();//移动
                    }
                }
                hitDetection();//调用子弹与飞机碰撞检测的方法
                runAway();//调用
                clear();
            }
            //执行 敌飞机飞行的任务
            sky.move();
            repaint();//每次移动以后要重新绘制一次！
        }
    }

    public void hitDetection() {//实现检测所有子弹与飞机的碰撞
        for (int i = 0; i < bullets.length; i++) {//轮数
            FlyingObject bullet = bullets[i];
            if (!bullet.isLiving()) {//如果当前子弹是死亡状态为false 然后取反 为true 则进入if语句
                continue;//中止当前循环轮数，执行下一轮
            }
            for (int j = 0; j < planes.length; j++) {//次数
                FlyingObject plane = planes[j];//比较一次切换下一个飞机对象。
                if (!plane.isLiving()) {//如果当前飞机是死亡状态为false 然后取反 为true 则进入if语句
                    continue;//中止当前循环次数，执行下一次
                }
                if (plane.duang(bullet)) {//如果为true代表撞到
                    plane.hit(); //扣减飞机的生命
                    bullet.hit();//扣减子弹的生命
                    scores(plane);//将当前撞到的飞机传入方法参数中
                }
            }
        }
    }
    /** 用于检查英雄机与敌飞机碰撞的方法*/
    public void  runAway(){
        if(hero.isLiving()){  //如果是活的话
            for (int i = 0; i <planes.length ; i++) {
                if(!planes[i].isLiving()){ //如果飞机是死亡状态的话
                    continue;//中止当前循环，执行下一次循环。
                }
                if(hero.duang(planes[i])){ //判断英雄机与当前敌飞机对象是否碰到  若true 则表示碰到
                    hero.goDead();   //立马死亡会播放爆炸动画
                    planes[i].goDead();//立马死亡会播放爆炸动画
                    break;
                }
            }
        }else if(hero.isZombie()){ //播放完爆炸动画后的状态
            if(life > 0){ //如果还有命
               hero = new Hero(150,400);//重新生成
                life--;
                //生成以后还要清理屏幕上的所有飞机
                for (int i = 0; i <planes.length ; i++) {
                    planes[i].goDead(); //遍历当前敌飞机数组  都设置为死亡
                }
            }else {
                currentState = GAME_OVER;
            }
        }
    }




    public void scores(FlyingObject plane) {
        if (plane.isDead()) {//当前传入被撞到的对象死没死 ，如果死了才能加分
            if (plane instanceof Enemy) {//如果撞到的飞机是Enemy类型的
                Enemy enemy = (Enemy) plane;//强转为Enemy类型
                score += enemy.getScore();
//                    System.out.println(score);//打印分数
            }
            if(plane instanceof Award){//如果plane这个对象类型是 Award类型 若为true 一定是蜜蜂
                Award award = (Award)plane;//强转为 Award类型
                int awardType = award.getAward();//获取奖品类型
                if(awardType == Award.DOUBLE_FIRE){ //如果奖品是 双枪
                    hero.doubleFire();// 给20次双枪的次数
                }
                else if(awardType == Award.LIFE){//如果奖品是加命的话
                    life++;//加一条命
                }
            }
        }
    }

    public void clear() {
        //设定一个新数组 , 新数组中存的都是 非僵尸状态的飞机.
        FlyingObject[] living = new FlyingObject[planes.length];
        int index = 0;
        for (int i = 0; i < planes.length; i++) {//遍历当前planes（飞机数组）
            if (planes[i].isZombie() || planes[i].outOfBounds()) {//如果当前飞机是僵尸飞机
                continue;//中止当前循环，执行下次循环
            }
            //将有生命的飞机装载到新数组中。
            living[index++] = planes[i];
        }
        planes = Arrays.copyOf(living, index);//将living数组copy给planes,更新planes数组
        //清除子弹的逻辑
        Bullet[] bulletArr = new Bullet[bullets.length];
        index = 0;
        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i].isDead() || bullets[i].outOfBounds()) {//如果子弹对象是死亡状态的话
                continue;//中止当前循环，执行下次循环
            }
            bulletArr[index++] = bullets[i];
        }
        bullets = Arrays.copyOf(bulletArr, index);//将bulletArr数组copy给planes,更新bullets数组
//        System.out.println("子弹数量"+bullets.length+"飞机数量"+planes.length);
    }


    private void createBullte() {
        if(!hero.isLiving()){ //如果是死亡状态
            return; //退出当前方法
        }
        if (index % 16 == 0) {//间隔 16次（0.01s）创建一次子弹
            Bullet[] biubiu = hero.openFire();//调用开火的方法
            int len = bullets.length;//获取源数组的长度
            Bullet[] arr = Arrays.copyOf(bullets, len + biubiu.length);//在源数组基础上扩容
                    //      1.需要拷贝的数组 2.代表从需要拷贝的数组索引哪里开始拷贝
                    //      3.拷贝到的目标数组  4.从目标数组的最后开始装   5.biubiu数组长度 代表有多少装多少。
            System.arraycopy(biubiu,0,arr,len,biubiu.length);
            bullets = arr; //因为bullets数组  负责子弹的移动和绘制 所以用最新的arr将替换掉bullets中的内容。
        }
    }

    //创建 蜜蜂。敌飞机、大飞机的方法
    //0~6区间的数字创建敌飞机
    //7~8区间的数组创建大飞机
    //9 则创建蜜蜂
    public void createPlane() {
        if (index % 32 == 0) {//每次创建飞机的间隔时间
            int random = (int) (Math.random() * 10);
            Plane p;//声明父类型的变量
            switch (random) {
                case 7:   //匹配的是7
                case 8:     //或者是8
                    p = new BigAirplane();//创建大飞机
                    break;
                case 9:
                    p = new Bee();//创建蜜蜂
                    break;
                default:
                    p = new Airplane();//创建敌飞机
                    break;
            }
            planes = Arrays.copyOf(planes, planes.length + 1);
            planes[planes.length - 1] = p;
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame(); //画框
        GameWorld gameWorld = new GameWorld(); //画板
        jFrame.add(gameWorld); //画框添加画板
        jFrame.setSize(400, 700); //设置
        jFrame.setLocationRelativeTo(null); //居中
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭程序
        jFrame.setVisible(true); //设置是否可见

        gameWorld.action();//开启定时器 执行任务
    }
}
