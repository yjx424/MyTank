package com.test4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;







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
	boolean isLive=true;
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}

	//����̹�˵��ٶ�
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


//���˵�̹��
class EnemyTank extends Tank implements Runnable{
	int times=0;
	
	//����һ�����������Դ�ŵ��˵��ӵ�
	Vector<Shot> shots=new Vector<Shot>();
	//��������ӵ���Ӧ���ոմ���̹�˺͵��˵��ӵ���ʧ��
	  public EnemyTank(int x,int y) {
		  super(x, y);
	}

	
	public void run() {
		while(true) {
			switch (this.direct) {
			case 0:
				//˵��̹����������
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
				//����
				for(int i=0;i<30;i++)
				{
					//��֤̹�˲����߽�
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
				//����
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
				//����
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
						//û���ӵ�
						//���
						switch(direct)
						{
						case 0:
							//����һ���ӵ�
							 s=new Shot(x+10,y,0);
							//���ӵ���������
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
						
						//�����ӵ�
						Thread t=new Thread(s);
						t.start();
					}
				}
			}
			
			
			//��̹���������һ���µķ���
			this.direct=(int) (Math.random()*4);
			//�жϵ���̹���Ƿ�����
			if(this.isLive==false) {
				break;
			}
			
			
		}
	}
}

//�ҵ�̹����̳�̹����
class Hero extends Tank
{
	//�ӵ�
	//Shot shot=null;
	Vector<Shot>shots=new Vector<Shot>();
	Shot shot=null;
	
	//����
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
		//�����ӵ��߳�
		Thread thread=new Thread(shot);
		thread.start();
	}
	
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

class Bomb{
	int x;
	int y;
	//ը��������
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y) {
		this.x=x;
		this.y=y;
	}
	//��������ֵ
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
	//�Ƿ񻹻���
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
				//��
				y-=speed;
				break;
			case 1:
				//��
				x+=speed;
				break;
			case 2:
				//��
				y+=speed;
				break;
			case 3:
				//��
				x-=speed;
				break;
			
			}
		
			//�жϸ��ӵ��Ƿ�������Ե
			if (x<0||x>400||y<0||y>300) {
				this.isLive=false;
				break;
			}

		}
	}
	
}

