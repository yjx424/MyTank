/*����̹����Ϸ2.0
 * 1.����̹��
 * 2.�ҵ�̹�˿����������ƶ�
 * 3.�ӵ���������(������)
 * 4.�ӵ�����̹�ˣ�̹�˻���ʧ����������ըЧ��
 */
package com.test4;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.*;




public  class MyTankGame4 extends JFrame{
	
	//����һ�������mp
	Mypanel mypanel=null;
	
	public static void main(String[] args)  {
		//����һ����mtg 
		MyTankGame4 myTankGame4=new MyTankGame4();			
		}
	
	
	//���캯��
	public MyTankGame4() {
		mypanel=new Mypanel();

		Thread thread=new Thread(mypanel);
		thread.start();
		//ע�����
		this.addKeyListener(mypanel);
		
		this.setTitle("sky");
		this.setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		this.add(mypanel);
		//���ô��ڳߴ�
		this.setSize(600,500);
		//�����Ƿ���ʾ
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(false);
		
		}
		
		
		
	
	
}
//�ҵ������
class Mypanel extends JPanel implements KeyListener,Runnable{
	//����һ����̹��hero
	Hero hero=null;
	
	
	//�������̹����
	Vector<EnemyTank> enemyTanks=
			new Vector<EnemyTank>();
	
	//����ը������
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	int enSize=5;
	
	//��������ͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;

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
			//��������̹��
			Thread thread=new Thread(eTank);
			thread.start();
			//������̹�����һ���ӵ�
			Shot shot=new Shot(eTank.x+10, eTank.y+30, 2);
			eTank.shots.add(shot);
			Thread thread2=new Thread(shot);
			thread2.start();
			//����
			enemyTanks.add(eTank);  			 
		}
		//��ʼ��ͼƬ
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
	
	//��д����
	public void paint(Graphics g) {
		super.paint(g);
		//���û����С
		g.fillRect(0,0,400,300);
		//�����ҵ�̹��
		if(hero.isLive==true)
		{this.drawTank(hero.getX(), 
				hero.getY(), g, this.hero.direct, 1);}
		//��shots��ȡ��ÿ���ӵ���������
		for(int i=0;i<this.hero.shots.size();i++) {
			Shot myShot=hero.shots.get(i);
		//�����ӵ�
		if(myShot!=null&&myShot.isLive==true) {
		g.draw3DRect(myShot.x, myShot.y, 
				1, 1, false);	
		}
		if (myShot.isLive==false) {
			//��shots��ɾ�������ӵ�
			hero.shots.remove(myShot);
		}
		
		}
		
		
		//����ը��
		for (int i = 0; i < bombs.size(); i++) {
			
			//ȡ��ը��
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
			//��bomb����ֵ��С
			bomb.lifeDown();
			if (bomb.life==0) {
				System.out.println("��ը");
				bombs.remove(bomb);
			}
		}
		
		//��������̹��
		for(int i=0; i<enemyTanks.size();i++) 
		{
			EnemyTank enemyTank=enemyTanks.get(i);
			
			if (enemyTank.isLive)
			{
				this.drawTank(enemyTank.getX(),
						enemyTank.getY(),
						g, enemyTank.getDirect(),
						0);
				
				//���������ӵ�
				for (int j = 0; j < enemyTank.shots.size(); j++) {
					//ȡ���ӵ�
					Shot enemyShot=enemyTank.shots.get(j);
					if (enemyShot.isLive) {
						g.draw3DRect(enemyShot.x, 
								enemyShot.y, 
								1, 1, false);
					}else {
						//�������̹�������ʹ�Vectorɾ��
						enemyTank.shots.remove(enemyShot);
					}
				}
				
			}
			
		}
	}
	
	//���˵��ӵ��Ƿ������
	public void hitMe() {
		int a = 0;
		//ȡ��ÿһ�����˵�̹��
		for (int i = 0; i < this.enemyTanks.size(); i++) {
			//ȡ��̹��
			EnemyTank enemyTank=enemyTanks.get(i);
			//ȡ��ÿһ���ӵ�
			for (int j = 0; j < enemyTank.shots.size(); j++) {
				//ȡ���ӵ�
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
	//�ж��ҵ��ӵ��Ƿ���е��˵�̹��
	public void hitEnemyTank() {
		//�ж��Ƿ���е��˵�̹��
		for( int i = 0;i<hero.shots.size();i++) {
			//ȡ���ӵ�
			Shot myShot=hero.shots.get(i);
			//�ж��ӵ��Ƿ���Ч
			if(myShot.isLive) 
			{
				//ȡ��ÿһ��̹����֮ƥ��
				for(int j=0;j<enemyTanks.size();j++) 
				{
					//ȡ��̹��
					EnemyTank enemyTank=enemyTanks.get(j);
					if (enemyTank.isLive) 
					{
						this.hitTank(myShot, enemyTank);
					}
				}
			}
		}
	}
	
	
	//дһ�������ж��ӵ��Ƿ����̹��
	public void hitTank(Shot shot,Tank tank) {
		//�жϸ�̹�˵ķ���
		switch (tank.direct) {

		case 1:
		case 3:
			if (shot.x>tank.x
				&&shot.x<tank.x+30
				&&shot.y>tank.y
				&&shot.y<tank.y+20) {
			//����
			//�ӵ�����
				shot.isLive=false;
				//̹������
				tank.isLive=false;
				//����һ��ը��������Vector
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
				//����
				//�ӵ�����
				shot.isLive=false;
				//����̹������
				tank.isLive=false;
				//����һ��ը��������Vector
				Bomb bomb=new Bomb(tank.x, tank.y);
				bombs.add(bomb);
				
				if (tank.isLive==false) {
					
					
					
				}
				
			}
			

			break;
			
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
			//System.out.println(this.hero.shots.size());
			
				this.hero.shotEnemy();
			
		}
		
		//�������»���Panel
		this.repaint();
 	
	}
	
	public void keyReleased(KeyEvent arg0) {
		
	}
	
	public void keyTyped(KeyEvent arg0) {
		
	}
	@Override
	public void run() {
		//ÿ��100����ȥ�ػ�
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
						
			this.hitEnemyTank();	
			
			
			//����,�жϵ����ӵ��Ƿ������
			this.hitMe();
			
			this.repaint();
		}
	}
}


