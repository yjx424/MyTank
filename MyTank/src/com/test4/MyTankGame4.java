/*功能坦克游戏2.0
 * 1.画出坦克
 * 2.我的坦克可上下左右移动
 * 3.子弹可以连发(最多五颗)
 * 4.子弹击中坦克，坦克会消失，并做出爆炸效果
 */
package com.test4;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.*;




public  class MyTankGame4 extends JFrame{
	
	//定义一个空面板mp
	Mypanel mypanel=null;
	
	public static void main(String[] args)  {
		//定义一个类mtg 
		MyTankGame4 myTankGame4=new MyTankGame4();			
		}
	
	
	//构造函数
	public MyTankGame4() {
		mypanel=new Mypanel();

		Thread thread=new Thread(mypanel);
		thread.start();
		//注册监听
		this.addKeyListener(mypanel);
		
		this.setTitle("sky");
		this.setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		this.add(mypanel);
		//设置窗口尺寸
		this.setSize(600,500);
		//设置是否显示
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(false);
		
		}
		
		
		
	
	
}
//我的面板类
class Mypanel extends JPanel implements KeyListener,Runnable{
	//定义一个空坦克hero
	Hero hero=null;
	
	
	//定义敌人坦克组
	Vector<EnemyTank> enemyTanks=
			new Vector<EnemyTank>();
	
	//定义炸弹集合
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	int enSize=5;
	
	//定义三张图片
	Image image1=null;
	Image image2=null;
	Image image3=null;

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
			//启动敌人坦克
			Thread thread=new Thread(eTank);
			thread.start();
			//给敌人坦克添加一颗子弹
			Shot shot=new Shot(eTank.x+10, eTank.y+30, 2);
			eTank.shots.add(shot);
			Thread thread2=new Thread(shot);
			thread2.start();
			//加入
			enemyTanks.add(eTank);  			 
		}
		//初始化图片
		image1=Toolkit.getDefaultToolkit().
				getImage(Panel.class.
						getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().
				getImage(Panel.class.
						getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().
				getImage(Panel.class.
						getResource("/bomb_3.gif"));
	}
	
	//重写画板
	public void paint(Graphics g) {
		super.paint(g);
		//设置画板大小
		g.fillRect(0,0,400,300);
		//画出我的坦克
		if(hero.isLive==true)
		{this.drawTank(hero.getX(), 
				hero.getY(), g, this.hero.direct, 1);}
		//从shots中取出每颗子弹，并绘制
		for(int i=0;i<this.hero.shots.size();i++) {
			Shot myShot=hero.shots.get(i);
		//画出子弹
		if(myShot!=null&&myShot.isLive==true) {
		g.draw3DRect(myShot.x, myShot.y, 
				1, 1, false);	
		}
		if (myShot.isLive==false) {
			//从shots中删除掉该子弹
			hero.shots.remove(myShot);
		}
		
		}
		
		
		//画出炸弹
		for (int i = 0; i < bombs.size(); i++) {
			
			//取出炸弹
			Bomb bomb=bombs.get(i);
			if (bomb.life>6) {
				g.drawImage(image1, bomb.x, 
						bomb.y, 30, 30, this);
								}
			else if (bomb.life>3) {
				g.drawImage(image2, bomb.x, 
						bomb.y, 30, 30, this);
				
			}else {
				g.drawImage(image3, bomb.x, 
						bomb.y, 30, 30, this);
			}
			//让bomb生命值减小
			bomb.lifeDown();
			if (bomb.life==0) {
				System.out.println("爆炸");
				bombs.remove(bomb);
			}
		}
		
		//画出敌人坦克
		for(int i=0; i<enemyTanks.size();i++) 
		{
			EnemyTank enemyTank=enemyTanks.get(i);
			
			if (enemyTank.isLive)
			{
				this.drawTank(enemyTank.getX(),
						enemyTank.getY(),
						g, enemyTank.getDirect(),
						0);
				
				//画出敌人子弹
				for (int j = 0; j < enemyTank.shots.size(); j++) {
					//取出子弹
					Shot enemyShot=enemyTank.shots.get(j);
					if (enemyShot.isLive) {
						g.draw3DRect(enemyShot.x, 
								enemyShot.y, 
								1, 1, false);
					}else {
						//如果敌人坦克死亡就从Vector删除
						enemyTank.shots.remove(enemyShot);
					}
				}
				
			}
			
		}
	}
	
	//敌人的子弹是否击中我
	public void hitMe() {
		int a = 0;
		//取出每一个敌人的坦克
		for (int i = 0; i < this.enemyTanks.size(); i++) {
			//取出坦克
			EnemyTank enemyTank=enemyTanks.get(i);
			//取出每一颗子弹
			for (int j = 0; j < enemyTank.shots.size(); j++) {
				//取出子弹
				Shot enemyShot =enemyTank.shots.get(j);
				this.hitTank(enemyShot, hero);
				if (hero.isLive==false) {
					JOptionPane.showMessageDialog
					(null,  "You  Dead", "sky",
							JOptionPane.ERROR_MESSAGE);
					}
			}
			
		}
		
		
		

		
	}
	//判断我的子弹是否击中敌人的坦克
	public void hitEnemyTank() {
		//判断是否击中敌人的坦克
		for( int i = 0;i<hero.shots.size();i++) {
			//取出子弹
			Shot myShot=hero.shots.get(i);
			//判断子弹是否有效
			if(myShot.isLive) 
			{
				//取出每一个坦克与之匹配
				for(int j=0;j<enemyTanks.size();j++) 
				{
					//取出坦克
					EnemyTank enemyTank=enemyTanks.get(j);
					if (enemyTank.isLive) 
					{
						this.hitTank(myShot, enemyTank);
					}
				}
			}
		}
	}
	
	
	//写一个函数判断子弹是否击中坦克
	public void hitTank(Shot shot,Tank tank) {
		//判断该坦克的方向
		switch (tank.direct) {

		case 1:
		case 3:
			if (shot.x>tank.x
				&&shot.x<tank.x+30
				&&shot.y>tank.y
				&&shot.y<tank.y+20) {
			//击中
			//子弹死亡
				shot.isLive=false;
				//坦克死亡
				tank.isLive=false;
				//创建一颗炸弹，放入Vector
				Bomb bomb=new Bomb(tank.x, tank.y);
				bombs.add(bomb);
			
					
					
				}
				

				
		
			break;
		case 0:
		case 2:
			if (shot.x>tank.x
					&&shot.x<tank.x+20
					&&shot.y>tank.y
					&&shot.y<tank.y+30) {
				//击中
				//子弹死亡
				shot.isLive=false;
				//敌人坦克死亡
				tank.isLive=false;
				//创建一颗炸弹，放入Vector
				Bomb bomb=new Bomb(tank.x, tank.y);
				bombs.add(bomb);
				
				if (tank.isLive==false) {
					
					
					
				}
				
			}
			

			break;
			
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
			//System.out.println(this.hero.shots.size());
			
				this.hero.shotEnemy();
			
		}
		
		//必须重新绘制Panel
		this.repaint();
 	
	}
	
	public void keyReleased(KeyEvent arg0) {
		
	}
	
	public void keyTyped(KeyEvent arg0) {
		
	}
	@Override
	public void run() {
		//每个100毫秒去重画
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
						
			this.hitEnemyTank();	
			
			
			//函数,判断敌人子弹是否击中我
			this.hitMe();
			
			this.repaint();
		}
	}
}


