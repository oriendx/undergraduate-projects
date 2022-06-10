#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <windows.h>
#include<time.h>
#include <iostream>
using namespace std;
#include "glut.h"
#include"Texture.h"
#include"room.h"
const GLfloat Pi = 3.1415926536;
const GLfloat R = 0.8f;
const int n = 200;
int model = 2;
static GLfloat angle = 2 * Pi;
float mx = 0, my = 0, mz = 0;
float  manlge = 0;//旋转角度和速度



GLfloat m_tranlate[3] = { 0,0,0 };//用于平移，对应X Y Z 平移量。按键W:上    S:下    A:左  D:右
GLfloat m_rorate[2] = { 0,0 };//用于旋转，分别绕X轴和Y轴旋转的角度，用鼠标左键控制
GLfloat m_scale = 2.1;//用于缩放，用鼠标中间滚轮控制
GLint   m_MouseDownPT[2] = { 0,0 };//记录鼠标坐标点，用于控制旋转角度
bool    m_bMouseDown = false;//记录鼠标左键是否按下，按下为TRUE,初始值为false
GLfloat m_robotMove = 0;
GLfloat m_robotMoveStep = 1;


void display()
{
	if (m_robotMove > 10) m_robotMoveStep = -0.01;
	if (m_robotMove < -10) m_robotMoveStep = 0.01;
	 m_robotMove += m_robotMoveStep;

	glTranslatef(0, 102.9, -331.11);

	//glTranslatef(m_tranlate[0], m_tranlate[1], m_tranlate[2]);//平移(X,Y,Z)
	//glRotatef(m_rorate[0], 1, 0, 0);//旋转 绕X轴
	glRotatef(m_rorate[1], 0, 1, 0);//旋转 绕Y轴
	glScalef(m_scale, m_scale, m_scale);//缩放（X,Y,Z）


	glPushMatrix();
	glScalef(10, 10, 10);
	//墙壁
	glPushMatrix(); 
	glScalef(1, 1, 2);
	DrawHouse();
	glPopMatrix(); 


	//桌子
	glPushMatrix();	
	glTranslatef(-6.7,-8.5,-1.639);//平移(X,Y,Z)
	glScalef(2.8, 4, 2.8);
	DrawDesk();
	glPopMatrix();

	//沙发
	glPushMatrix();
	glTranslatef(-15.6, -0.5, -0.7);//平移(X,Y,Z)
	glRotatef(180, 0, 1, 0);//旋转 绕Y轴
	draSofa();
	glPopMatrix();




	//茶壶
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glPushMatrix();	
	glTranslatef(-6.7, -5.6, 0);//平移(X,Y,Z)
	glutSolidTeapot(0.8);
	glDisable(GL_LIGHT0);
	glDisable(GL_LIGHTING);
	glPopMatrix();

	//表
	glPushMatrix();
	glTranslatef(-9.8, 2, 0);
	glRotatef(90, 0, 1, 0);
	glScalef(2, 2, 1);
	//DrawClock();
	glPopMatrix();

	//灯
	glPushMatrix();
	glTranslatef(0, 4.5, 0);
	glRotatef(90, 1, 0, 0);
	DrawLight();
	glPopMatrix();


	//圆的凳子
	glPushMatrix();
	glTranslatef(-6.4,-8.6,6.7);//平移(X,Y,Z)
	glRotatef(-90, 1, 0, 0);
	DrawChair();
	glPopMatrix();

	//robot
	glPushMatrix();	
	glTranslatef(3, -9.6, m_robotMove);//平移(X,Y,Z)
	glRotatef(-90, 1, 0, 0);
	DrawRobot();
	glPopMatrix();
	//前抽屉1
	glPushMatrix();
	glTranslatef(-17.4, -8.09, 7.09);//平移(X,Y,Z)
	glRotatef(90, 0, 1, 0);
	glScalef(3, 3, 5.5);
	DrawDrawer();
	glPopMatrix();

	//后抽屉2
	glPushMatrix();	
	glTranslatef(-17.4, -8.09, -11.79);//平移(X,Y,Z)
	glRotatef(90, 0, 1, 0);
	glScalef(3, 3, 5.5);
	DrawDrawer();
	glPopMatrix();

	//painting
	glPushMatrix();
	glTranslatef(-19.9, 2.39, 0);//平移(X,Y,Z)
	glRotatef(90, 0, 1, 0);
	DrawCube(6.5, 6.5, 0.1, g_bitmap[7]);
	glPopMatrix();

	//windows
	glPushMatrix();
	glTranslatef(0,0,-19.7);//平移(X,Y,Z)
	DrawCube(9, 6, 0.1, g_bitmap[6]);// 3 2
	glPopMatrix();

	//音响1
	glPushMatrix();
	glTranslatef(16.2, -6,-12.1);//平移(X,Y,Z)
	glRotatef(-90, 0, 1, 0);
	glScalef(2, 3, 2);
	DrawSound();
	glPopMatrix();
	//音响2
	glPushMatrix();
	glTranslatef(0,0,3.8);//平移(X,Y,Z)
	glTranslatef(16.2, -6, 12.1);//平移(X,Y,Z)
	glRotatef(-90, 0, 1, 0);
	glScalef(2, 3, 2);
	DrawSound();
	glPopMatrix();

	//TV
	glPushMatrix();
	glTranslatef(20,0,0);//平移(X,Y,Z)
	glRotatef(90, 0, 1, 0);
	DrawCube(12, 6, 0.2, g_bitmap[1]);// 3 2
	glPopMatrix();


	//衣柜
	glPushMatrix();
	
	glTranslatef(-11, 0, -16);//平移(X,Y,Z)
	glScalef(5, 7.5, 3);
	DrawCloset();
	glPopMatrix();

	//衣柜
	glPushMatrix();
	glTranslatef(16, 0, -16);//平移(X,Y,Z)
	glScalef(5, 7.5, 3);
	DrawCloset();
	glPopMatrix();

	glPopMatrix();
}

//-----------------事件函数---------------------------------------//
void Glinit() //初始化  
{  
	glShadeModel(GL_SMOOTH);							// Enable Smooth Shading
	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);				// Black Background
	glClearDepth(1.0f);									// Depth Buffer Setup
	glEnable(GL_DEPTH_TEST);							// //启动深度检测
	glDepthFunc(GL_LEQUAL);								// The Type Of Depth Testing To Do
	//glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);	// Really Nice Perspective Calculations
										// Initialization Went OK
}    
void reshape(int width, int height)//窗口大小改变事件  
{  
	if(height == 0)
		height = 1;

	glViewport(0,0,width,height);//视口在屏幕的大小位置 Reset The Current Viewport
    
	glMatrixMode(GL_PROJECTION);// 设置投影方式  Select The Projection Matrix
	glLoadIdentity();           // Reset The Projection Matrix
	gluPerspective(45.0f,(GLfloat)width/(GLfloat)height,0.1f,10000.0f);// Calculate The Aspect Ratio Of The Window

	glMatrixMode(GL_MODELVIEW);							// Select The Modelview Matrix
	glLoadIdentity();
	
	  
}  
void idle()//空闲事件处理事件
{
       // glutPostRedisplay(); //程序修改了窗口的内容时 需要调用
}
void keyboard(unsigned char key, int x, int y) //键盘事件 
{  
	switch (key)
	{
	case 'W'://上移动
		m_tranlate[2] += 1;
		break;
	case 'S'://下移动
		m_tranlate[2] -= 1;
		break;
	case 'A'://左移动
		m_tranlate[0] -= 1;
		break;
	case 'D'://右移动
		m_tranlate[0] += 1;
		break;
	case 'Q'://左移动
		m_tranlate[1] -= 1;
		break;
	case 'E'://右移动
		m_tranlate[1] += 1;
		break;
	}
} 

void DrawGLScene() //重新绘制 
{  

	

	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	// Clear Screen And Depth Buffer
	glLoadIdentity();// Reset The Current Modelview Matrix
	gluLookAt(0.0,0.0,5.0, 0.0,0.0,0.0, 0.0,1.0,0.0);//相机
	display();//绘图
	 
	glutSwapBuffers();//交换缓存 
	glutPostRedisplay();

}  
//鼠标事件
void MouseEvent(int button, int state, int x, int y)
{

	//1、鼠标缩放：往上滚动放大；往下滚动缩小
	if (state == GLUT_UP && button == GLUT_WHEEL_UP)//滚轮往上滚动
	{
		//m_scale += 0.1;
	}

	else if (state == GLUT_UP && button == GLUT_WHEEL_DOWN)//滚轮往下滚动
	{
		//m_scale -= 0.1;
		//if (m_scale < 0.1) m_scale = 0.1;
	}


	//【2 鼠标左键是否按下】
	if (state == GLUT_DOWN && button == GLUT_LEFT_BUTTON)
	{
		m_bMouseDown = true;//鼠标左键按下
		m_MouseDownPT[0] = x;//记录当前X坐标
		m_MouseDownPT[1] = y;////记录当前Y坐标
	}
	else
	{
		m_bMouseDown = false;//鼠标左键弹起，结束旋转
	}


	/*第一个参数表明哪个鼠标键被按下或松开，这个变量可以是下面的三个值中的一个：
	GLUT_LEFT_BUTTON
	GLUT_MIDDLE_BUTTON
	GLUT_RIGHT_BUTTON
	第二个参数表明，函数被调用发生时，鼠标的状态，也就是是被按下，或松开，可能取值如下：
	GLUT_DOWN
	GLUT_UP
	第三四个参数 XY 以左上角位原点
	*/



}
//按下鼠标按钮移动鼠标事件
void MotionEvent(int x, int y)
{
	if (m_bMouseDown)//如果鼠标左键被按下
	{
		m_rorate[0] += y - m_MouseDownPT[1];//通过滑动鼠标改变旋转的角度
		m_rorate[1] += x - m_MouseDownPT[0];//通过滑动鼠标改变旋转的角度

		m_MouseDownPT[0] = x;//记录当前X坐标
		m_MouseDownPT[1] = y;//记录当前Y坐标
	}
}
//检测鼠标进入或离开窗口
void MouseEntry(int state)
{
	/*state有两个值表明是离开还是进入窗口:
	GLUT_LEFT
	GLUT_ENTERED*/
}
//点击菜单响应事件
void MenuEvent(int choose)
{
	switch (choose)
	{
	case 1://复位：把旋转平移缩放的值复位
		//用于平移，对应X Y Z 平移量。按键W:上  S:下   A:左  D：右
		m_tranlate[0] = 0;
		m_tranlate[1] = 0;
		m_tranlate[2] = -10;

		//用于旋转，分别是绕X轴 和Y轴旋转的角度，用鼠标左键控制
		m_rorate[0] = 0;
		m_rorate[1] = 0;

		//用于缩放，用鼠标中间滚轮控制
		m_scale = 1.0;

		//记录鼠标坐标点，用于控制旋转角度；
		m_MouseDownPT[0] = 0;
		m_MouseDownPT[1] = 0;

		//记录鼠标左键是否按下，按下为true,初始值为false
		m_bMouseDown = false;
		break;
	case 2://功能待定：暂时不做处理
		break;

	}
}

void main(int argc, char **argv)
{
   
	
   glutInit(&argc, (char**)argv); //对GLUT进行初始化 
   glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH); //模式 双缓冲 ，深度缓存区
   glutInitWindowPosition(100, 100); //指定窗口左上角的
   glutInitWindowSize(600,400); //指定窗口的大小
   glutCreateWindow("Hello");  //创建gl窗口
   Glinit();
   LoadTextMap(); //加载图	
   //-------------注册回调函数---------//
   glutDisplayFunc(DrawGLScene);    // 重新绘制事件 
   glutKeyboardFunc(keyboard);  // 键盘事件
   glutEntryFunc(MouseEntry);       //检测鼠标进入或离开窗口
   glutMotionFunc(MotionEvent);     //鼠标移动事件
   glutMouseFunc(MouseEvent);       //鼠标事件
   glutReshapeFunc(reshape);    //窗口大小发生变化事件
   {
	   glutCreateMenu(MenuEvent);       //创建菜单
	   glutAddMenuEntry("复位", 1);     //菜单项1
	   glutAddMenuEntry("待定", 2);     //菜单项2
	   glutAttachMenu(GLUT_RIGHT_BUTTON);//鼠标右键按下弹出菜单
   }
   glutMainLoop();            //调用已注册的回调函数           
}
