#include <stdio.h>
#include <stdlib.h>
//#include <unistd.h>
#include <random>
#include <iostream>
#include <list>
#ifdef __APPLE__
#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#include <GLUT/glut.h>
#else
 
#include <GL/glut.h>
#include <GL/freeglut.h>
#endif








using namespace std;




//Record the properties of each planet
struct planet
{
	float color[3];
	float radius;
	float speed;
	float radius2;
	int creat_time;


	planet *parent;
	std::list<planet *> children;   //Satellites of the planet
};
planet sun;
std::list<planet *> planets;
list<planet *>::iterator sel_planet;
list<planet *>::iterator sel_moon;
void drawSphere()
{
	glutSolidSphere(1, 10, 10);
}

void InitSun()
{
	sun.radius = 2;
	sun.radius2 = 0;
	sun.creat_time = 0;
	sun.speed = 0;
	sun.color[0] = 1.0f;
	sun.color[1] = 1.0f;
	sun.color[2] = 0.0f;
}
//Globals
float camPos[] = { 0, 0, 80 }; //where the camera is
int t0 = 0, t1 = 0;
float t = 0;

bool isPause = false;
bool isSelecting = false;
bool isMoonSeleted = false;

float maxspeed = 4.0;
void init(void)
{

	InitSun();
	glClearColor(0, 0, 0, 0);
	glColor3f(1, 1, 1);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	/* Sets projection to perspective */
	gluPerspective(45, 1, 1, 100);
}

//get a random value
float GetRandom()
{
	return ((float)rand() / (float)RAND_MAX);
}


//Get a random color that is not green
void SetRandomColor(float &r, float &g, float& b)
{
	r = GetRandom();
	g = GetRandom();
	b = GetRandom();

	while (r == 0 && b == 0 && g > 0)
	{
		r = GetRandom();
		g = GetRandom();
		b = GetRandom();
	}
}
/* display function - GLUT display callback function
 * clears the screen, sets the camera position, draws the ground plane and movable box
 */
void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluLookAt(camPos[0], camPos[1], camPos[2], 0, 0, 0, 0, 1, 0);
	glColor3f(1, 1, 1);
	//draw the sun!
	glPushMatrix();
	glColor3f(1, 1, 0);
	glScalef(2, 2, 1);
	drawSphere();
	glPopMatrix();
	//Start rendering all planets
	for (std::list < planet *>::iterator iterator = planets.begin(); iterator != planets.end(); iterator++)
	{
		glPushMatrix();

		if (isSelecting && !isMoonSeleted && (*iterator) == *sel_planet)
		{
			glColor3f(0, 1, 0); //setclolour of the selected planet
		}
		else
		{
			glColor3f((*iterator)->color[0], (*iterator)->color[1], (*iterator)->color[2]);
		}
		//Set the planets to rotate and pan and zoom, which will affect their corresponding moons
		glRotated((t - (*iterator)->creat_time) * (*iterator)->speed, 0, 0, 1);
		glTranslatef((*iterator)->radius2, 0, 0);
		glScaled((*iterator)->radius, (*iterator)->radius, 1);
		drawSphere();
		// glPopMatrix();
		//render all Satellites
		for (std::list < planet *>::iterator itr = (*iterator)->children.begin(); itr != (*iterator)->children.end(); itr++)
		{
			glPushMatrix();
			glRotated((t - (*itr)->creat_time) * (*itr)->speed, 0, 0, 1);
			glTranslatef((*itr)->radius2, 0, 0);
			glScaled((*itr)->radius, (*itr)->radius, 1);
			if (isSelecting && isMoonSeleted && (*itr) == *sel_moon)
			{
				glColor3f(0, 1, 0);//set colour for selected stars
			}
			else
			{
				glColor3f((*itr)->color[0], (*itr)->color[1], (*itr)->color[2]);
			}

			drawSphere();
			glPopMatrix();
		}
		glPopMatrix();
	}
	//flush out to single buffer
	glFlush();
}

void keyboard(unsigned char key, int x, int y)
{
	int mod;
	switch (key)
	{
	case 'a':
	case 'A':
		// If a planet or satellite of a planet is selected, add the satellite of that planet
		if (isSelecting)
		{
			planet *s = new planet();

			SetRandomColor(s->color[0], s->color[1], s->color[2]);
			s->creat_time = t;

			float monnminorbit = 3;
			float maxorbit = 10;
			s->radius2 = GetRandom() * maxorbit + monnminorbit;
			s->parent = *sel_planet;
			float minmoonsize = 0.05;
			float  maxmoonsize = 1;
			s->radius = GetRandom() * maxmoonsize + minmoonsize;
			s->speed = GetRandom() * maxspeed + (*sel_planet)->speed;
			(*sel_planet)->children.push_back(s);
		}
		else // otherwise generate planets
		{
			planet *s = new planet();
			SetRandomColor(s->color[0], s->color[1], s->color[2]);
			float minplanetorbit = 3;
			s->creat_time = t;
			float maxplanetorbit = 16;
			s->radius2 = GetRandom() * maxplanetorbit + minplanetorbit;
			s->parent = &sun;
			float minplanetsize = 0.1;
			float maxplanetsize = 2;
			s->radius = GetRandom() * maxplanetsize + minplanetsize;
			s->speed = GetRandom() * maxspeed;
			planets.push_back(s);
		}
		break;
	case 'r':
	case 'R':
		planets.clear();
		break;

	case 'p':
	case 'P':
		isPause = isPause ? false : true;
		break;
	case ' ':
		isSelecting = isSelecting ? false : true;
		isMoonSeleted = false;
		if (isSelecting)
		{
			sel_planet = planets.begin();
		}
		break;
	case 'q':
	case 'Q':
		exit(0);
		break;

	case 'd':
	case 'D':
		if (isSelecting) //delet this star
		{
			if (isMoonSeleted)//Delete the last satellite if it is selected
			{
				list<planet *>::iterator tmp = sel_moon;
				if (sel_moon != (*sel_planet)->children.end())
				{
					sel_moon++;
				}
				(*sel_planet)->children.erase(tmp);
				if (sel_moon == (*sel_planet)->children.end())
				{
					isMoonSeleted = false;
				}
			}
			else  //otherwise delet planet
			{
				list<planet *>::iterator tmp = sel_planet;
				if (sel_planet != planets.end())
				{
					sel_planet++;
				}
				planets.erase(tmp);
				if (planets.size() == 0)
				{
					isSelecting = false;
				}
			}
		}
		break;

	case '+':
		if (isSelecting)  //Increase the speed of the selected planet or moon
		{
			if (isMoonSeleted)
			{
				(*sel_moon)->speed += 0.34;
			}
			else
			{
				(*sel_planet)->speed += 0.34;
			}
		}
		else //If not selected, increase the speed of all planets and moons
		{
			for (std::list < planet *>::iterator iterator = planets.begin(); iterator != planets.end(); iterator++)
			{

				(*iterator)->speed += 0.34;

				for (std::list < planet *>::iterator iterator2 = (*iterator)->children.begin(); iterator2 != (*iterator)->children.end(); iterator2++)
				{
					(*iterator2)->speed += 0.34;
				}

			}
		}
		break;
	case '-':
		if (isSelecting)  //decrease the speed of the selected planet or moon
	
		{
			if (isMoonSeleted)
			{
				(*sel_moon)->speed -= 0.34;
			}
			else
			{
				(*sel_planet)->speed -= 0.34;
			}
		}
		else   //If not selected, decrease the speed of all planets and moons
		{
			for (std::list < planet *>::iterator iterator = planets.begin(); iterator != planets.end(); iterator++)
			{
				(*iterator)->speed -= 0.34;
				for (std::list < planet *>::iterator iterator2 = (*iterator)->children.begin(); iterator2 != (*iterator)->children.end(); iterator2++)
				{
					(*iterator2)->speed -= 0.34;
				}
			}
		}
		break;
	case '>':
		if (isSelecting)   //Increase the radius of all selected planets or moons 
		{
			if (isMoonSeleted)
			{
				(*sel_moon)->radius -= 0.1;
			}
			else
			{
				(*sel_planet)->radius -= 0.1;
			}
		}
		else  //otherwise do what you know
		{
			for (std::list < planet *>::iterator iterator = planets.begin(); iterator != planets.end(); iterator++)
			{
				(*iterator)->radius -= 0.1;
				for (std::list < planet *>::iterator iterator2 = (*iterator)->children.begin(); iterator2 != (*iterator)->children.end(); iterator2++)
				{
					(*iterator2)->radius -= 0.1;
				}

			}
		}
		break;
	case '<':
		if (isSelecting)
		{
			if (isMoonSeleted)
			{
				(*sel_moon)->radius += 0.1;
			}
			else
			{
				(*sel_planet)->radius += 0.1;
			}
		}
		else
		{
			for (std::list < planet *>::iterator iterator = planets.begin(); iterator != planets.end(); iterator++)
			{
				(*iterator)->radius += 0.1;
				for (std::list < planet *>::iterator iterator2 = (*iterator)->children.begin(); iterator2 != (*iterator)->children.end(); iterator2++)
				{
					(*iterator2)->radius += 0.1;
				}

			}
		}
		break;
	case 27:
		exit(0);
		break;
	default:
		cout << (int)key << endl;
		break;
	}
}

void reshape(int w, int h)
{
	glViewport(0, 0, (GLsizei)w, (GLsizei)h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45.0, (GLfloat)w / (GLfloat)h, 1.0, 100.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glTranslatef(0.0, 0.0, -1.5);
}


void specialkey(int key, int x, int y)
{
	int mod;
	switch (key)
	{
	case GLUT_KEY_RIGHT:
		mod = glutGetModifiers();
		isMoonSeleted = (mod == GLUT_ACTIVE_SHIFT);

		if (isMoonSeleted)
		{
			sel_moon++;
			if (sel_moon == (*sel_planet)->children.end())
			{
				sel_moon = (*sel_planet)->children.begin();
			}
		}
		else if (isSelecting)
		{
			sel_planet++;
			if (sel_planet == planets.end())
			{
				sel_planet = planets.begin();
			}
		}
		break;
	case GLUT_KEY_LEFT:
		mod = glutGetModifiers();
		isMoonSeleted = (mod == GLUT_ACTIVE_SHIFT);

		if (isMoonSeleted)
		{
			if (planets.size() > 0)
			{
				if (sel_moon == (*sel_planet)->children.begin())
				{
					sel_moon = (--(*sel_planet)->children.end());
				}
				else
				{
					sel_moon--;
				}
			}
		}
		else if (isSelecting)
		{
			if (sel_planet == planets.begin())
			{
				sel_planet = (--planets.end());
			}
			else
			{
				sel_planet--;
			}
			sel_moon = (*sel_planet)->children.begin();
		}
		break;

	default:
		break;
	}
}

void idle()
{
	if (0 == t0 && 0 == t1)
	{
		t0 = t1 = glutGet(GLUT_ELAPSED_TIME);
	}
	else
	{
		t0 = t1;
		t1 = glutGet(GLUT_ELAPSED_TIME);

		if (!isPause)
		{
			t += (t1 - t0) * 0.001f;
		}

	}

	glutPostRedisplay();
}

/* main function - program entry point */
int main(int argc, char **argv)
{
	//srand(time(0));
	glutInit(&argc, argv); //starts up GLUT
	glutInitDisplayMode(GLUT_RGBA);
	glutInitWindowSize(600, 600);
	glutInitWindowPosition(800, 200);
	glutCreateWindow("SolarSystem"); //creates the window
	glutDisplayFunc(display); //registers "display" as the display callback function
	init();
	glutIdleFunc(idle);
	glutReshapeFunc(reshape);
	glutKeyboardFunc(keyboard);
	glutSpecialFunc(specialkey);
	glutMainLoop(); //starts the event glutMainLoop
	return (0); //return may not be necessary on all compilers
}
