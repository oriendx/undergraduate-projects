#pragma once
GLuint g_bitmap[16];//纹理对应的编号 定义纹理的映射个数 

unsigned  char *LoadBMP(const char *path, int &width, int &height)//打开纹理图片
{
	unsigned char *imagedate = NULL;
	FILE *pFile = fopen(path, "rb");
	if (pFile)
	{
		BITMAPFILEHEADER bfh;
		fread(&bfh, sizeof(BITMAPFILEHEADER), 1, pFile);
		if (bfh.bfType = 0x4d42)
		{
			BITMAPINFOHEADER bih;
			fread(&bih, sizeof(BITMAPINFOHEADER), 1, pFile);
			width = bih.biWidth;
			height = bih.biHeight;
			imagedate = new unsigned char[width*height * 3];
			fseek(pFile, bfh.bfOffBits, SEEK_SET);

			fread(imagedate, 1, width*height * 3, pFile);
			fclose(pFile);
		}
	}
	return  imagedate;
}

bool  LoadGLTextures1(char *filename, GLuint &texture)	//加载位图文件 方法1
{
	int imageWidth, imageHeight;
	unsigned char * imageDate = LoadBMP(filename, imageWidth, imageHeight);
	glGenTextures(1, &texture); //生成贴图
	glBindTexture(GL_TEXTURE_2D, texture); //贴图生效
	//纹理参数定义
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR); //缩小时线性滤波
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR_MIPMAP_LINEAR); //扩大时线性滤波
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, imageWidth, imageHeight, 0, GL_BGR_EXT, GL_UNSIGNED_BYTE, imageDate);
	//gluBuild2DMipmaps(GL_TEXTURE_2D, 4, imageWidth, imageHeight, GL_RGB, GL_UNSIGNED_BYTE, imageDate); //贴图数据
	return true;
}

void  LoadTextMap(void) ////纹理加载
{

	LoadGLTextures1("res/wood1.bmp", g_bitmap[0]);
	LoadGLTextures1("res/computer2.bmp", g_bitmap[1]);
	LoadGLTextures1("res/wall2.bmp", g_bitmap[2]);
	LoadGLTextures1("res/wood2.bmp", g_bitmap[3]);
	LoadGLTextures1("res/1wall.bmp", g_bitmap[4]);
	LoadGLTextures1("res/bed.bmp", g_bitmap[5]);
	LoadGLTextures1("res/windows.bmp", g_bitmap[6]);
	LoadGLTextures1("res/painting.bmp", g_bitmap[7]);
	LoadGLTextures1("res/robot.bmp", g_bitmap[8]);
	LoadGLTextures1("res/stone1.bmp", g_bitmap[9]);
	LoadGLTextures1("res/table.bmp", g_bitmap[10]);
	LoadGLTextures1("res/bu1.bmp", g_bitmap[11]);
	LoadGLTextures1("res/sound.bmp", g_bitmap[12]);
	LoadGLTextures1("res/closet.bmp", g_bitmap[13]);
	LoadGLTextures1("res/stone2.bmp", g_bitmap[14]);
}
