package com.test2;

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

