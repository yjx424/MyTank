/*����̹����Ϸ2.0
 * 1.����̹��
 * 2.�ҵ�̹�˿����������ƶ�
 */
package com.test3;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.*;

public class MyTankGame3 extends JFrame{
	
	//����һ�������mp
	Mypanel mp=null;
	
	public static void main(String[] args)  {
		//����һ����mtg 
		MyTankGame3 mtg=new MyTankGame3();
	} 
	
	//���캯��
	public MyTankGame3() {
		mp=new Mypanel();
		Thread thread=new Thread(mp);
		thread.start();
		//ע�����
		this.addKeyListener(mp);
		this.setTitle("sky");
		this.setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		this.add(mp);
		//���ô��ڳߴ�
		this.setSize(400,300);
		//�����Ƿ���ʾ
		this.setVisible(true);
		
	}
	
	
}
//�ҵ������
class Mypanel extends JPanel implements KeyListener,Runnable{
	//����һ����̹��hero
	Hero hero=null;
	
	//�������̹����
	Vector<EnemyTank> enemyTanks=new 
			Vector<EnemyTank>();
	
	int enSize=3;
	
	//���캯��
	public Mypanel() {
		//��̹�˸�ֵȷ������
		hero=new Hero(100,100);
		
		//��ʼ�����˵�̹��
		for(int i=0;i<enSize;i++) {
			//����һ�����˵�̹�˶���
			EnemyTank eTank=new 
					EnemyTank((i+1)*50,0);
			eTank.setColor(0);
			eTank.setDirect(2);
			//����
			enemyTanks.add(eTank);  
			 
		}
	}
	//��д����
	public void paint(Graphics g) {
		super.paint(g);
		//���û����С
		g.fillRect(0, 0, 400,300);
		//�����ҵ�̹��
		this.drawTank(hero.getX(), 
				hero.getY(), g, this.hero.direct, 1);
		
		//�����ӵ�
		if(hero.shot!=null&&hero.shot.isLive==true) {
		g.draw3DRect(hero.shot.x, hero.shot.y, 
				1, 1, false);	
		}
		
		//��������̹��
		for(int i=0; i<enemyTanks.size();i++) {
			this.drawTank(enemyTanks.get(i).getX(),
					enemyTanks.get(i).getY(),
					g, enemyTanks.get(i).getDirect(),
					0);
		}
	}
	
	//����̹�˵ĺ���
	public void drawTank(int x,int y,
			Graphics g,int direct,int type) {
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
			
			//����
		case 1:
			//�����������
			g.fill3DRect(x, y, 30, 5, false);
			//�����������
			g.fill3DRect(x, y+15, 30, 5, false);
			//�����м����
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//����Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//������
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
			
			//����
		case 2:
			//������ߵľ���
			g.fill3DRect(x,y,5,30,false);
			//�����ұ߾���
			g.fill3DRect(x+15,y,5,30,false);
			//�����м����
			g.fill3DRect(x+5,y+5,10,20,false);
		    //����Բ��
			g.fillOval(x+5,y+10,10,10);
			//������
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
			
			//����
		case 3:
			//�����������
			g.fill3DRect(x, y, 30, 5, false);
			//�����������
			g.fill3DRect(x, y+15, 30, 5, false);
			//�����м����
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//����Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//������
			g.drawLine(x+15, y+10, x, y+10);
			break;
	
		}
		
	}
	//�����´��� a s d w
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_W) {
			//�����ҵ�̹�˵ķ���
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(arg0.getKeyCode()==KeyEvent.VK_D) {
			//����
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if (arg0.getKeyCode()==KeyEvent.VK_S) {
			//����	
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if (arg0.getKeyCode()==KeyEvent.VK_A) {
			//����
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		
		if (arg0.getKeyCode()==KeyEvent.VK_J) {
			//����
			this.hero.shotEnemy();
		}
		
		//�������»���Panel
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
		//ÿ��100����ȥ�ػ�
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

