package cn.tedu.day03;
/**
 *  奖品的接口    ---给Bee用的
 *  加命  和双枪
 * */
public interface Award {
    /**双倍火力*/
    int DOUBLE_FIRE = 1;
    /**命*/
    int LIFE = 2;
    /**共实现类重写具体的获取奖励的逻辑*/
    int getAward();
}
