/*功能坦克游戏2.0
 * 1.画出坦克
 * 2.我的坦克可上下左右移动
 */
package com.test3;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.*;

public class MyTankGame3 extends JFrame{
	
	//定义一个空面板mp
	Mypanel mp=null;
	
	public static void main(String[] args)  {
		//定义一个类mtg 
		MyTankGame3 mtg=new MyTankGame3();
	} 
	
	//构造函数
	public MyTankGame3() {
		mp=new Mypanel();
		Thread thread=new Thread(mp);
		thread.start();
		//注册监听
		this.addKeyListener(mp);
		this.setTitle("sky");
		this.setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		this.add(mp);
		//设置窗口尺寸
		this.setSize(400,300);
		//设置是否显示
		this.setVisible(true);
		
	}
	
	
}
//我的面板类
class Mypanel extends JPanel implements KeyListener,Runnable{
	//定义一个空坦克hero
	Hero hero=null;
	
	//定义敌人坦克组
	Vector<EnemyTank> enemyTanks=new 
			Vector<EnemyTank>();
	
	int enSize=3;
	
	//构造函数
	public Mypanel() {
		//给坦克赋值确定坐标
		hero=new Hero(100,100);
		
		//初始化敌人的坦克
		for(int i=0;i<enSize;i++) {
			//创建一辆敌人的坦克对象
			EnemyTank eTank=new 
					EnemyTank((i+1)*50,0);
			eTank.setColor(0);
			eTank.setDirect(2);
			//加入
			enemyTanks.add(eTank);  
			 
		}
	}
	//重写画板
	public void paint(Graphics g) {
		super.paint(g);
		//设置画板大小
		g.fillRect(0, 0, 400,300);
		//画出我的坦克
		this.drawTank(hero.getX(), 
				hero.getY(), g, this.hero.direct, 1);
		
		//画出子弹
		if(hero.shot!=null&&hero.shot.isLive==true) {
		g.draw3DRect(hero.shot.x, hero.shot.y, 
				1, 1, false);	
		}
		
		//画出敌人坦克
		for(int i=0; i<enemyTanks.size();i++) {
			this.drawTank(enemyTanks.get(i).getX(),
					enemyTanks.get(i).getY(),
					g, enemyTanks.get(i).getDirect(),
					0);
		}
	}
	
	//画出坦克的函数
	public void drawTank(int x,int y,
			Graphics g,int direct,int type) {
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
			
			//向右
		case 1:
			//画出上面矩形
			g.fill3DRect(x, y, 30, 5, false);
			//画出下面矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//画出线
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
			
			//向下
		case 2:
			//画出左边的矩形
			g.fill3DRect(x,y,5,30,false);
			//画出右边矩形
			g.fill3DRect(x+15,y,5,30,false);
			//画出中间矩形
			g.fill3DRect(x+5,y+5,10,20,false);
		    //画出圆形
			g.fillOval(x+5,y+10,10,10);
			//画出线
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
			
			//向左
		case 3:
			//画出上面矩形
			g.fill3DRect(x, y, 30, 5, false);
			//画出下面矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//画出线
			g.drawLine(x+15, y+10, x, y+10);
			break;
	
		}
		
	}
	//键按下处理 a s d w
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_W) {
			//设置我的坦克的方向
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(arg0.getKeyCode()==KeyEvent.VK_D) {
			//向右
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if (arg0.getKeyCode()==KeyEvent.VK_S) {
			//向下	
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if (arg0.getKeyCode()==KeyEvent.VK_A) {
			//向左
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		
		if (arg0.getKeyCode()==KeyEvent.VK_J) {
			//开火
			this.hero.shotEnemy();
		}
		
		//必须重新绘制Panel
		this.repaint();
 	
	}
	
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//每个100毫秒去重画
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			this.repaint();
		}
	}
}

