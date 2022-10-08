package cn.tedu.day03;
/**
 *
 *  蜜蜂类 (奖品)...
 * */
public class Bee extends Plane implements Award {
    private  double offsetX = 1;//飞的方向

    public Bee(){
            //随机出场初始化
        super(Images.bees[0],Images.bees,Images.boms);//初始化
        offsetX = Math.random()>0.5 ? offsetX : -offsetX;//随机方向
        this.step = 1;//设置速度....
    }
    @Override
    public void move() {
        super.move(); //y+=step
        x+=offsetX;
        if(x <= 0){//当到达左边界
            offsetX = 1;
        }
        if (x >= (420 - width)){//当到达右边界
            offsetX = -1;
        }
    }

    @Override/**奖品有两种：一种是双枪  一种是加命*/
    public int getAward() {
        return  Math.random()>0.5 ? DOUBLE_FIRE : LIFE;
    }
}
