void DrawCube(float x, float y, float z, GLuint _bitmap)
{
	//glColor3f(1, 1, 1);
	glPushMatrix();
	glScalef(x, y, z);
	//6面体
	glEnable(GL_TEXTURE_2D);//---------------------------启动纹理
	//qian	
	glBindTexture(GL_TEXTURE_2D, _bitmap);//---------对纹理进行绑定 不能放在glbegin() 和glend()之前 ，否则多次加载无限
	glBegin(GL_QUADS);
	glTexCoord2f(0.0, 0.0); glVertex3f(-1.0f, -1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(0.0, 1.0); glVertex3f(1.0f, -1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 1.0); glVertex3f(1.0f, 1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 0.0); glVertex3f(-1.0f, 1.0f, 1.0f);//纹理坐标映射
	glEnd();
	//hou
	//glBindTexture(GL_TEXTURE_2D, g_bitmap[1]);//---------对纹理进行绑定 不能放在glbegin() 和glend()之前 ，否则多次加载无限
	glBegin(GL_QUADS);
	glTexCoord2f(0.0, 0.0); glVertex3f(-1.0f, -1.0f, -1.0f);//纹理坐标映射
	glTexCoord2f(0.0, 1.0); glVertex3f(1.0f, -1.0f, -1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 1.0); glVertex3f(1.0f, 1.0f, -1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 0.0); glVertex3f(-1.0f, 1.0f, -1.0f);//纹理坐标映射
	glEnd();
	
	//zuo
	//glBindTexture(GL_TEXTURE_2D, g_bitmap[2]);//---------对纹理进行绑定 不能放在glbegin() 和glend()之前 ，否则多次加载无限
	glBegin(GL_QUADS);
	glTexCoord2f(0.0, 0.0); glVertex3f(-1.0f, -1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(0.0, 1.0); glVertex3f(-1.0f, 1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 1.0); glVertex3f(-1.0f, 1.0f, -1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 0.0); glVertex3f(-1.0f, -1.0f, -1.0f);//纹理坐标映射
	glEnd();
	//you
	//glBindTexture(GL_TEXTURE_2D, g_bitmap[3]);//---------对纹理进行绑定 不能放在glbegin() 和glend()之前 ，否则多次加载无限
	glBegin(GL_QUADS);
	glTexCoord2f(0.0, 0.0); glVertex3f(1.0f, -1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(0.0, 1.0); glVertex3f(1.0f, 1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 1.0); glVertex3f(1.0f, 1.0f, -1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 0.0); glVertex3f(1.0f, -1.0f, -1.0f);//纹理坐标映射
	glEnd();
	//shang
	//glBindTexture(GL_TEXTURE_2D, g_bitmap[4]);//---------对纹理进行绑定 不能放在glbegin() 和glend()之前 ，否则多次加载无限
	glBegin(GL_QUADS);
	glTexCoord2f(0.0, 0.0); glVertex3f(-1.0f, 1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(0.0, 1.0); glVertex3f(1.0f, 1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 1.0); glVertex3f(1.0f, 1.0f, -1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 0.0); glVertex3f(-1.0f, 1.0f, -1.0f);//纹理坐标映射
	glEnd();
	//xia
	//glBindTexture(GL_TEXTURE_2D, g_bitmap[5]);//---------对纹理进行绑定 不能放在glbegin() 和glend()之前 ，否则多次加载无限
	glBegin(GL_QUADS);
	glTexCoord2f(0.0, 0.0); glVertex3f(-1.0f, -1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(0.0, 1.0); glVertex3f(1.0f, -1.0f, 1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 1.0); glVertex3f(1.0f, -1.0f, -1.0f);//纹理坐标映射
	glTexCoord2f(1.0, 0.0); glVertex3f(-1.0f, -1.0f, -1.0f);//纹理坐标映射
	glEnd();

	glPopMatrix();
	glDisable(GL_TEXTURE_2D);
}
void DrawDesk()
{
	//233 216 133
	glPushMatrix();

	float ambientlight1[] = { 116.0 / 250.0,165.0 / 250.0 ,182.0 / 250.0,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight1);
	//桌子面
	glColor3f(1, 1, 1);
	glPushMatrix();
	glTranslatef(0, 0.43, 0);
	DrawCube(1, 0.1, 2, g_bitmap[9]);
	//DrawCube(1, 0.1, 1, 0);
	glPopMatrix();

	float ambientlight2[] = { 0.1, 0.1, 0.1,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight2);
	//桌子腿1
	glPushMatrix();
	glTranslatef(0.8, 0.0, 1.6);
	DrawCube(0.1, 0.4, 0.1, g_bitmap[10]);
	//DrawCube(0.1, 0.8, 0.1, 0);
	glPopMatrix();
	//桌子腿2
	glPushMatrix();
	glTranslatef(-0.8, 0.0, 1.6);
	DrawCube(0.1, 0.4, 0.1, g_bitmap[10]);
	//DrawCube(0.1, 0.8, 0.1, 0);
	glPopMatrix();
	//桌子腿3
	glPushMatrix();
	glTranslatef(-0.8, 0.0, -1.6);
	DrawCube(0.1, 0.4, 0.1, g_bitmap[10]);
	//DrawCube(0.1, 0.8, 0.1, 0);
	glPopMatrix();
	//桌子腿4
	glPushMatrix();
	glTranslatef(0.8, 0.0, -1.6);
	DrawCube(0.1, 0.4, 0.1, g_bitmap[10]);
	//DrawCube(0.1, 0.8, 0.1, 0);
	glPopMatrix();



	glPopMatrix();
}


void DrawHouse()
{
	//229 118 63
	glColor3f(1, 1, 1);
	//地面
	glPushMatrix();
	glTranslatef(0, -10, 0);
	DrawCube(20, 0.1, 10, g_bitmap[4]);
	glPopMatrix();

	glColor3f(0.4, 0.4, 0.4);
	float ambientlight1[] = { 0.4, 0.4, 0.4,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight1);
	//屋顶
	glPushMatrix();
	glColor3f(0.4, 0.4, 0.4);
	glTranslatef(0, 10, 0);
	DrawCube(20, 0.1, 10, g_bitmap[2]);
	glPopMatrix();

	//后墙
	glColor3f(0.8, 0.8, 0.8);
	float ambientlight2[] = { 0.8, 0.8, 0.8,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight2);
	glPushMatrix();
	glColor3f(0.8, 0.8, 0.8);
	glTranslatef(0, 0, -10);
	DrawCube(20, 10, 0.1, g_bitmap[2]);
	glPopMatrix();

	//后墙
	glColor3f(0.8, 0.8, 0.8);
	//float ambientlight4[] = { 0.8, 0.8, 0.8,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight2);
	glPushMatrix();
	glColor3f(0.8, 0.8, 0.8);
	glTranslatef(0, 0, 10);
	DrawCube(20, 10, 0.1, g_bitmap[2]);
	glPopMatrix();
	//左墙
	glColor3f(229.0 / 250.0, 118.0 / 250.0, 63.0 / 250.0);
	//float ambientlight3[] = { 229.0/250.0, 118.0/250.0, 63.0/250.0,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight2);
	glPushMatrix();
	glColor3f(0.5, 0.5, 0.0);
	glTranslatef(-20, 0, -0);
	DrawCube(0.1, 10, 10, g_bitmap[2]);
	glPopMatrix();

	//右墙
	glPushMatrix();
	glColor3f(0.5, 0.5, 0.0);
	glTranslatef(20, 0, -0);
	DrawCube(0.1, 10, 10, g_bitmap[2]);
	glPopMatrix();
}
void Circle(float r)
{
	glBegin(GL_TRIANGLE_FAN);//扇形连续填充三角形串
	glVertex3f(0.0f, 0.0f, 0.0f);
	float i = 0;
	for (i = 0; i <= 390; i += 15)
	{
		float p = i * 3.14 / 180;
		glNormal3f(0, 0, 1); glTexCoord2f(sin(p) / 2.0 + 0.5, cos(p) / 2.0 + 0.5); glVertex3f(r*sin(p), r*cos(p), 0.0f);
	}
	glEnd();
}
void Cylinder(float r, float h, GLuint UpId, GLuint DownId, GLuint ID)
{
	glBindTexture(GL_TEXTURE_2D, UpId);
	glPushMatrix();
	glTranslatef(0, 0, h / 2.0f + 0.01);//上盖子
	Circle(r);
	glPopMatrix();

	glBindTexture(GL_TEXTURE_2D, DownId);
	glPushMatrix();
	glTranslatef(0, 0, -h / 2.0f - 0.01);//下盖子
	Circle(r);
	glPopMatrix();


	glBindTexture(GL_TEXTURE_2D, ID);
	glBegin(GL_QUAD_STRIP);//连续填充四边形串
	float i = 0;
	for (i = 0; i <= 390; i += 15)
	{
		float p = i * 3.14 / 180;
		glNormal3f(r*sin(p), r*cos(p), 0); glTexCoord2f(i / 390.0, 1.0); glVertex3f(r*sin(p), r*cos(p), h / 2.0f);
		glNormal3f(r*sin(p), r*cos(p), 0); glTexCoord2f(i / 390.0, 0.0); glVertex3f(r*sin(p), r*cos(p), -h / 2.0f);
	}
	glEnd();

}

void DrawComputer()
{

	//底部
	glPushMatrix();
	glColor3f(0.1, 0.1, 0.1);
	float ambientlight1[] = { 0.1f,0.1f,0.1f,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight1);
	glTranslatef(0, -0.1, 0);
	DrawCube(1.2, 0.05, 0.7, 0);
	glPopMatrix();

	//屏幕
	glColor3f(1.1, 1.1, 1.1);
	float ambientlight2[] = { 1.1, 1.1, 1.1,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight2);

	glPushMatrix();
	glTranslatef(-0.1, 0.4, -1.6);
	glRotatef(45, 1, 0, 0);
	DrawCube(1.2, 0.05, 0.9, g_bitmap[1]);
	glPopMatrix();
}
void DrawLight()
{//
	glColor3f(1.0, 1.0, 1.0);
	float ambientlight3[] = { 229.0 / 250.0, 63.0 / 250.0, 118.0 / 250.0,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight3);
	glPushMatrix();
	glTranslatef(0.0, 0, -0.8);
	glRotatef(180, 1, 0, 0);
	glutSolidCone(2.0, 1.2, 50, 50);
	glPopMatrix();

	glEnable(GL_TEXTURE_2D);
	float ambientlight[] = { 0.2,0.2,0.2,1.0 };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight);
	glPushMatrix();
	glTranslatef(0.0, 0, -3.5);
	//	Cylinder(0.15, 4.5);
	glPopMatrix();
}
void DrawDrawer()
{
	//240 183 93

	glPushMatrix();
	glRotatef(90, 0, 0, 1);
	glTranslatef(0, 0.5, 0);
	glScalef(1.0, 2.0, 1.0);
	float ambientlight1[] = { 0.1, 0.1, 0.1,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight1);
	glPushMatrix();
	glTranslatef(-0.3, 0.01, 0.4);
	DrawCube(0.02, 0.1, 0.1, g_bitmap[10]);
	glPopMatrix();
	glPushMatrix();
	glTranslatef(0.3, 0.01, 0.4);
	DrawCube(0.02, 0.1, 0.1, g_bitmap[10]);
	glPopMatrix();



	float ambientlight[] = { 240.0 / 250.0, 183.0 / 250.0, 93.0 / 250.0,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight);
	glPushMatrix();
	glTranslatef(-0.305, 0.005, 0);
	DrawCube(0.3, 0.4, 0.4, g_bitmap[3]);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(0.305, 0.005, 0);
	DrawCube(0.3, 0.4, 0.4, g_bitmap[3]);
	glPopMatrix();




	glPopMatrix();

}

void DrawSound()
{
	//240 183 93

	glPushMatrix();
	glRotatef(90, 0, 0, 1);
	glTranslatef(0, 0.5, 0);
	glScalef(1.0, 2.0, 1.0);
	float ambientlight1[] = { 0.1, 0.1, 0.1,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight1);
	glPushMatrix();
	glTranslatef(-0.3, 0.01, 0.4);
	DrawCube(0.9, 0.4, 0.05, g_bitmap[12]);
	glPopMatrix();




	float ambientlight[] = { 240.0 / 250.0, 183.0 / 250.0, 93.0 / 250.0,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight);
	glPushMatrix();
	glTranslatef(-0.305, 0.005, 0);
	DrawCube(0.9, 0.4, 0.4, g_bitmap[0]);
	glPopMatrix();





	glPopMatrix();

}
void DrawChair()
{

	float ambientlight3[] = { 229.0 / 250.0, 120.0 / 250.0, 80.0 / 250.0,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight3);
	glPushMatrix();
	glTranslatef(0, 0, 1.3);//平移(X,Y,Z)
	Cylinder(1.25, 0.5, g_bitmap[3], g_bitmap[3], g_bitmap[3]);
	glPopMatrix();
	glPushMatrix();
	Cylinder(1.25, 2.5, g_bitmap[5], g_bitmap[5], g_bitmap[5]);
	glPopMatrix();
}
void DrawRobot()
{

	float ambientlight3[] = { 229.0 / 250.0, 120.0 / 250.0, 80.0 / 250.0,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight3);
	glPushMatrix();
	glTranslatef(0, 0, 0.4);//平移(X,Y,Z)
	Cylinder(2.5, 0.3, g_bitmap[8], g_bitmap[8], g_bitmap[8]);
	glPopMatrix();
	glPushMatrix();
	Cylinder(2.5, 0.8, g_bitmap[6], g_bitmap[6], g_bitmap[6]);
	glPopMatrix();
}
void DrawBox(GLfloat x, GLfloat y, GLfloat z, GLfloat w, GLfloat h, GLfloat d)
{
	glPushMatrix();
	glTranslatef(x, y, z);
	glBegin(GL_QUADS);
	// 前表面
	glNormal3f(0.0f, 0.0f, 1.0f);
	glTexCoord2i(0, 0); glVertex3f(-w, -h, d);
	glTexCoord2i(1, 0); glVertex3f(w, -h, d);
	glTexCoord2i(1, 1); glVertex3f(w, h, d);
	glTexCoord2i(0, 1); glVertex3f(-w, h, d);
	// 后表面
	glNormal3f(0.0f, 0.0f, -1.0f);
	glTexCoord2i(1, 0); glVertex3f(-w, -h, -d);
	glTexCoord2i(1, 1); glVertex3f(-w, h, -d);
	glTexCoord2i(0, 1); glVertex3f(w, h, -d);
	glTexCoord2i(0, 0); glVertex3f(w, -h, -d);
	// 上表面
	glNormal3f(0.0f, 1.0f, 0.0f);
	glTexCoord2i(0, 1); glVertex3f(-w, h, -d);
	glTexCoord2i(0, 0); glVertex3f(-w, h, d);
	glTexCoord2i(1, 0); glVertex3f(w, h, d);
	glTexCoord2i(1, 1); glVertex3f(w, h, -d);
	// 下表面
	glNormal3f(0.0f, -1.0f, 0.0f);
	glTexCoord2i(1, 1); glVertex3f(-w, -h, -d);
	glTexCoord2i(0, 1); glVertex3f(w, -h, -d);
	glTexCoord2i(0, 0); glVertex3f(w, -h, d);
	glTexCoord2i(1, 0); glVertex3f(-w, -h, d);
	// 右侧面
	glNormal3f(1.0f, 0.0f, 0.0f);
	glTexCoord2i(1, 0); glVertex3f(w, -h, -d);
	glTexCoord2i(1, 1); glVertex3f(w, h, -d);
	glTexCoord2i(0, 1); glVertex3f(w, h, d);
	glTexCoord2i(0, 0); glVertex3f(w, -h, d);
	// 左侧面
	glNormal3f(-1.0f, 0.0f, 0.0f);
	glTexCoord2i(0, 0); glVertex3f(-w, -h, -d);
	glTexCoord2i(1, 0); glVertex3f(-w, -h, d);
	glTexCoord2i(1, 1); glVertex3f(-w, h, d);
	glTexCoord2i(0, 1); glVertex3f(-w, h, -d);
	glEnd();
	glPopMatrix();
}
void draSofa()
{
	float ambientlight2[] = { 1.0, 1.0, 1.0,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight2);
	glPushMatrix();
	//*************************床
	glEnable(GL_TEXTURE_2D);
	glScalef(0.2, 0.2, 0.15);
	glTranslatef(0, -9, 90);
	glBindTexture(GL_TEXTURE_2D, g_bitmap[11]);
	DrawBox(-57.0f + 75, -27.0f, -92.0f, 4.0f, 12.0f, 35.0f);	       //床头板	

	glBindTexture(GL_TEXTURE_2D, g_bitmap[3]);
	DrawBox(2.1f, -34.2f, -92.3f, 12.0f, 4.0f, 35.0f);		       // 床腿2
	//glPopMatrix();
	//DrawBox(-17.0f , -33.0f, -119.0f     , 12.0f, 6.0f, 35.0f);
	glBindTexture(GL_TEXTURE_2D, g_bitmap[5]);
	glColor3f(1, 1, 1);

	DrawBox(2.0f, -28.0f, -92.0f, 12.0f, 2.0f, 35.0f);             //床板
	glPopMatrix();
}


void DrawCloset()
{
	//240 183 93

	glPushMatrix();
	glRotatef(90, 0, 0, 1);
	glTranslatef(0, 0.5, 0);
	glScalef(1.0, 2.0, 1.0);
	float ambientlight1[] = { 0.1, 0.1, 0.1,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight1);
	glPushMatrix();
	glTranslatef(-0.3, 0.01, 0.4);
	DrawCube(0.9, 0.4, 0.05, g_bitmap[13]);
	glPopMatrix();




	float ambientlight[] = { 240.0 / 250.0, 183.0 / 250.0, 93.0 / 250.0,1.0f };
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambientlight);
	glPushMatrix();
	glTranslatef(-0.305, 0.005, 0);
	DrawCube(0.9, 0.4, 0.4, g_bitmap[14]);
	glPopMatrix();





	glPopMatrix();

}
