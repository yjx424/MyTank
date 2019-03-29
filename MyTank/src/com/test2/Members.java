package com.test2;

//̹����
class Tank{
	//̹�˺�����
	int x=0;
	//̹��������
	int y=0;
	
	//̹�˷���
	//0��ʾ�� 1��ʾ�� 2��ʾ�� 3��ʾ��
	int direct=0;
	//��ɫ
	int color;
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}

	//����̹�˵��ٶ�
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


//���˵�̹��
class EnemyTank extends Tank{
	  public EnemyTank(int x,int y) {
		  super(x, y);
	}
}

//�ҵ�̹����̳�̹����
class Hero extends Tank
{
	//̹�������ƶ�	
	public void moveUp() {
		y-=speed;
		
	}
	//̹�������ƶ�	
	public void moveRight() {
		x+=speed;
			
	}
	//̹�������ƶ�	
	public void moveDown() {
		y+=speed;
					
	}
	//̹������ �ƶ�	
	public void moveLeft() {
		x-=speed;
					
	}

	public Hero(int x, int y) {
		super(x, y);
	
	}
	
}
