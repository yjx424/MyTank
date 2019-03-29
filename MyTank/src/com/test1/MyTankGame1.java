package com.test1;

import java.awt.*;
import javax.swing.*;

public class MyTankGame1 extends JFrame{
	
	//定义一个空面板mp
	Mypanel mp=null;
	
	public static void main(String[] args)  {
		//定义一个类mtg 
		MyTankGame1 mtg=new MyTankGame1();
	} 
	
	//构造函数
	//构造函数
	public MyTankGame1() {
		mp=new Mypanel();
		this.setTitle("sky");
		this.add(mp);
		//设置窗口尺寸
		this.setSize(400,300);
		//设置是否显示
		this.setVisible(true);
		
	}
	
	
}
//我的面板类
class Mypanel extends JPanel{
	//定义一个空坦克hero
	Hero hero=null;
	//构造函数
	public Mypanel() {
		//给坦克赋值确定坐标
		hero=new Hero(100,100);
	}
	//重写画板
	public void paint(Graphics g) {
		super.paint(g);
		//设置画板大小
		g.fillRect(0, 0, 400,300);
		//画出坦克
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
	}
	
	//画出坦克的函数
	public void drawTank(int x,int y,Graphics g,int direct,int type) {
		//判断什么类型坦克
		switch(type) {
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		
		//判断方向
		switch (direct) {
		//向上
		case 0:
			//画出我的坦克
			//画出左边的矩形
			g.fill3DRect(x,y,5,30,false);
			//画出右边矩形
			g.fill3DRect(x+15,y,5,30,false);
			//画出中间矩形
			g.fill3DRect(x+5,y+5,10,20,false);
		    //画出圆形
			g.fillOval(x+5,y+10,10,10);
			//画出线
			g.drawLine(x+10, y+5, x+10, y+15);
			break;
	
		}
		
	}
}

//坦克类
class Tank{
	//坦克横坐标
	int x=0;
	//坦克纵坐标
	int y=0;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Tank(int x,int y)	{
		this.x=x;
		this.y=y;
	}
}
//我的坦克类继承坦克类
class Hero extends Tank
{

	public Hero(int x, int y) {
		super(x, y);
	
	}
	
}
