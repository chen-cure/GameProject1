package cn.tedu.day03;

import javax.swing.*;
import java.awt.*;

/**
 * 天空类
 */
public class Sky extends FlyingObject {
    //private 表示y0 只能在当前类内中访问。
    private double y0;//代表第二个图片的高度
    //右键  找到Generate 点击  然后选择弹出框的第一个选项，然后选项内容全选  点击ok即可！
    public Sky() {
             //x   y    对象的图片    动画数组    爆炸动画数组
        super(0,0,Images.sky,null,null);
        this.step = 0.8;//速度 0.8
        y0 =-height; //第二个天空的高度
    }
    public void move() {
        y +=step;
        y0+=step;
        if(y > height){   //如果第一张图片超出下边界
            y = -height;  //快速移动到画框最上方
        }
        if (y0 > height){ //如果第二张图片超出下边界
            y0 = -height; //快速移动到画框最上方
        }
    }

    @Override
    public void paint(Graphics g) {
        icon.paintIcon(null,g,(int)x,(int)y);//绘制画框中的天空图片
        icon.paintIcon(null,g,(int)x,(int)y0);//绘制到画框上方的天空图片
    }
}
