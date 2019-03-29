package com.test1;

import java.awt.*;
import javax.swing.*;

public class MyTankGame1 extends JFrame{
	
	//����һ�������mp
	Mypanel mp=null;
	
	public static void main(String[] args)  {
		//����һ����mtg 
		MyTankGame1 mtg=new MyTankGame1();
	} 
	
	//���캯��
	//���캯��
	public MyTankGame1() {
		mp=new Mypanel();
		this.setTitle("sky");
		this.add(mp);
		//���ô��ڳߴ�
		this.setSize(400,300);
		//�����Ƿ���ʾ
		this.setVisible(true);
		
	}
	
	
}
//�ҵ������
class Mypanel extends JPanel{
	//����һ����̹��hero
	Hero hero=null;
	//���캯��
	public Mypanel() {
		//��̹�˸�ֵȷ������
		hero=new Hero(100,100);
	}
	//��д����
	public void paint(Graphics g) {
		super.paint(g);
		//���û����С
		g.fillRect(0, 0, 400,300);
		//����̹��
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
	}
	
	//����̹�˵ĺ���
	public void drawTank(int x,int y,Graphics g,int direct,int type) {
		//�ж�ʲô����̹��
		switch(type) {
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		
		//�жϷ���
		switch (direct) {
		//����
		case 0:
			//�����ҵ�̹��
			//������ߵľ���
			g.fill3DRect(x,y,5,30,false);
			//�����ұ߾���
			g.fill3DRect(x+15,y,5,30,false);
			//�����м����
			g.fill3DRect(x+5,y+5,10,20,false);
		    //����Բ��
			g.fillOval(x+5,y+10,10,10);
			//������
			g.drawLine(x+10, y+5, x+10, y+15);
			break;
	
		}
		
	}
}

//̹����
class Tank{
	//̹�˺�����
	int x=0;
	//̹��������
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
//�ҵ�̹����̳�̹����
class Hero extends Tank
{

	public Hero(int x, int y) {
		super(x, y);
	
	}
	
}
