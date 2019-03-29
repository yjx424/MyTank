package com.test4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;







//坦克类
class Tank{
	//坦克横坐标
	int x=0;
	//坦克纵坐标
	int y=0;
	
	//坦克方向
	//0表示上 1表示右 2表示下 3表示左
	int direct=0;
	//颜色
	int color;
	boolean isLive=true;
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}

	//设置坦克的速度
	int speed=1;
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
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


//敌人的坦克
class EnemyTank extends Tank implements Runnable{
	int times=0;
	
	//定义一个向量，可以存放敌人的子弹
	Vector<Shot> shots=new Vector<Shot>();
	//敌人添加子弹，应当刚刚创建坦克和敌人的子弹消失后
	  public EnemyTank(int x,int y) {
		  super(x, y);
	}

	
	public void run() {
		while(true) {
			switch (this.direct) {
			case 0:
				//说明坦克正在向上
				for(int i=0;i<30;i++)
				{
					if(y>0)
					{
						y-=speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			case 1:
				//向右
				for(int i=0;i<30;i++)
				{
					//保证坦克不出边界
					if(x<380)
					{
						x+=speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			case 2:
				//向下
				for(int i=0;i<30;i++)
				{
					if(y<270)
					{
						y+=speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			case 3:
				//向左
				for(int i=0;i<30;i++)
				{
					if(x>0)
					{
						x-=speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			default:
				break;
			}
			this.times++;
			
			if(times%2==0)
			{
				if(isLive)
				{
					if(shots.size()<5)
					{
						//System.out.println("et.ss.size()<5="+et.ss.size());
						Shot s=null;
						//没有子弹
						//添加
						switch(direct)
						{
						case 0:
							//创建一颗子弹
							 s=new Shot(x+10,y,0);
							//把子弹加入向量
							shots.add(s);
							break;
						case 1:
							s=new Shot(x+30,y+10,1);
							shots.add(s);
							break;
						case 2:
							 s=new Shot(x+10,y+30,2);
							shots.add(s);
							break;
						case 3:
							s=new Shot(x,y+10,3);
							shots.add(s);
							break;
						}
						
						//启动子弹
						Thread t=new Thread(s);
						t.start();
					}
				}
			}
			
			
			//让坦克随机产生一个新的方向
			this.direct=(int) (Math.random()*4);
			//判断敌人坦克是否死亡
			if(this.isLive==false) {
				break;
			}
			
			
		}
	}
}

//我的坦克类继承坦克类
class Hero extends Tank
{
	//子弹
	//Shot shot=null;
	Vector<Shot>shots=new Vector<Shot>();
	Shot shot=null;
	
	//开火
	public void shotEnemy() {
		
		switch (this.direct) {
		case 0:
			shot=new Shot(x+10, y,0);
			shots.add(shot);
			break;
		case 1:
			shot=new Shot(x+30, y+10,1);
			shots.add(shot);
			break;
		case 2:
			shot=new Shot(x+10, y+30,2);
			shots.add(shot);
			break;
		case 3:
			shot=new Shot(x, y+10,3);
			shots.add(shot);
			break;
		default:
			break;
		}
		//启动子弹线程
		Thread thread=new Thread(shot);
		thread.start();
	}
	
	//坦克向上移动	
	public void moveUp() {
		y-=speed;
		
	}
	//坦克向右移动	
	public void moveRight() {
		x+=speed;
			
	}
	//坦克向下移动	
	public void moveDown() {
		y+=speed;
					
	}
	//坦克向左 移动	
	public void moveLeft() {
		x-=speed;
					
	}

	public Hero(int x, int y) {
		super(x, y);
	
	}
	
}

class Bomb{
	int x;
	int y;
	//炸弹的生命
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y) {
		this.x=x;
		this.y=y;
	}
	//减少生命值
	public void lifeDown() {
		if(life>0) {
			life--;
		}else {
			this.isLive=false;
		}
	}
	
}

class Shot implements Runnable{
	int x;
	int y;
	int direct;
	int speed=5;	
	//是否还活着
	boolean isLive=true;
	
	public Shot(int x,int y,int direct) {
		this.x=x;
		this.y=y;
		this.direct=direct;	

	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
			switch (direct) {
			case 0:
				//上
				y-=speed;
				break;
			case 1:
				//右
				x+=speed;
				break;
			case 2:
				//下
				y+=speed;
				break;
			case 3:
				//左
				x-=speed;
				break;
			
			}
		
			//判断该子弹是否碰到边缘
			if (x<0||x>400||y<0||y>300) {
				this.isLive=false;
				break;
			}

		}
	}
	
}

