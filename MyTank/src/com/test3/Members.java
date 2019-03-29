package com.test3;

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
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}

	//设置坦克的速度
	int speed=10;
	
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
class EnemyTank extends Tank{
	  public EnemyTank(int x,int y) {
		  super(x, y);
	}
}

//我的坦克类继承坦克类
class Hero extends Tank
{
	//子弹
	Shot shot=null;
	
	//开火
	public void shotEnemy() {
		
		switch (this.direct) {
		case 0:
			shot=new Shot(x+10, y,0);
			break;
		case 1:
			shot=new Shot(x+30, y+10,1);
			break;
		case 2:
			shot=new Shot(x+10, y+30,2);
			break;
		case 3:
			shot=new Shot(x, y+10,3);
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
				// TODO: handle exception
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
			System.out.println("子弹坐标x="+x+"y="+y);
			//判断该子弹是否碰到边缘
			if (x<0||x>400||y<0||y>300) {
				this.isLive=false;
				break;
			}

		}
	}
	
}
