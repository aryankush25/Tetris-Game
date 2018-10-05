import java.util.*;
import java.io.*;
import java.lang.*;

class Board {

	int r;
	int c;
	char[][] mat;
	int count[];
	Board(int r, int c)
	{
		this.r = r;
		this.c = c;
		mat = new char[r][c];
		count = new int[r];
	}

	boolean check(Shape s)
	{
		for(int i = 0; i < 4; i++)
		{
			if(s.line[i].getX() == 28)
			{
				return true;
			}
		}
		return false;
	}

	void assign()
	{
		for(int i = 0; i < r; i++)
		{
			for(int j = 0; j < c; j++)
			{
				if(i == 0 || j == 0 || i == r - 1 || j == c - 1)
				{
					mat[i][j] = '|';
				}
				else
				{
					mat[i][j] = ' ';
				}
			}
		}
	}

	boolean assign(Shape s1, Shape s2)
	{
		if(s2 == null)
		{
			for(int i = 0; i < 4; i++)
			{
				mat[s1.line[i].getX()][s1.line[i].getY()] = '#';
			}
			System.out.println("S2 is Null");
		}
		else
		{
			for(int i = 0; i < 4; i++)
			{
				mat[s2.line[i].getX()][s2.line[i].getY()] = ' ';
			}
			for(int i = 0; i < 4; i++)
			{
				mat[s1.line[i].getX()][s1.line[i].getY()] = '#';
			}
			System.out.println("S2 is Not Null");
		}

		if(check(s1) == true)
		{
			return true;
		}

		return false;
	}

	void display()
	{
		for(int i = 0; i < r; i++)
		{
			for(int j = 0; j < c; j++)
			{
				System.out.print(mat[i][j]);
			}
			System.out.print(" " + count[i] + "\n");
			count[i] = 0;
		}
	}
}

class Point {

	private int x, y;

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

class Direction {
	
	private int currentDirection;
	private int shape;

	Direction()
	{
		this.currentDirection = 1;

		Random rand = new Random();
		int  temp = rand.nextInt( 1) + 1;
		 
		this.shape = temp;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}

	public int getShape() {
		return shape;
	}

	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}
}

class Shape {
	Point[] line;
	Point[] temp;
	Direction dir;
	int r, c, cc, cr = 1;

	Shape(int r, int c)
	{
		this.r = r;
		this.c = c;
		this.cc = (c / 2) - 3;
		dir  = new Direction();

		line = new Point[4];
		for(int i = 0; i < 4; i++)
		{
			line[i] = new Point();
		}

		temp = new Point[4];
		for(int i = 0; i < 4; i++)
		{
			temp[i] = new Point();
		}

		for(int i = 0; i < 4; i++)
		{
			line[i].setX(cr);
			line[i].setY(cc + i);
		}
	}

	Shape(Shape s)
	{
		line = new Point[4];
		for(int i = 0; i < 4; i++)
		{
			line[i] = new Point();
		}

		for(int i = 0; i < 4; i++)
		{
			line[i].setX(s.line[i].getX());
			line[i].setY(s.line[i].getY());
		}
	}

	boolean validPonits(Point []temp)
	{
		for(int i = 0; i < 4; i++)
		{
			if(!(temp[i].getX() > 0 && temp[i].getX() < r - 1))
			{
				return false;
			}
			if(!(temp[i].getY() > 0 && temp[i].getY() < c - 1))
			{
				return false;
			}
		}
		return true;
	}

	void execute(String ch)
	{
		if(ch.equals("r") || ch.equals("l") || ch.equals("b") || ch.equals("d"))
		{
			if(dir.getCurrentDirection() == 1 || dir.getCurrentDirection() == 3)
				moveHorizontal(ch);
			
			if(dir.getCurrentDirection() == 2 || dir.getCurrentDirection() == 4)
				moveVertical(ch);
		}
		else if(ch.equals("rr"))
		{
			if(dir.getCurrentDirection() == 1)
			{
				rotate1();
			}
			else if(dir.getCurrentDirection() == 2)
			{
				rotate2();
			}
			else if(dir.getCurrentDirection() == 3)
			{
				rotate3();
				
			}
			else if(dir.getCurrentDirection() == 4)
			{
				rotate4();
				
			}
		}
	}

	void rotate1()
	{
		cr++;
        for(int  i = 0; i < 4; i++)
        {
            temp[i].setX(cr + i);
            temp[i].setY(cc);
		}
		if(validPonits(temp) == true)
		{
			for(int i = 0; i < 4; i++)
			{
				line[i].setX(temp[i].getX());
				line[i].setY(temp[i].getY());
			}
			dir.setCurrentDirection(2);
		}
		else
		{
			cr--;
		}
	}
	void rotate2()
	{
		cr++;
		cc = cc - 3;
        for(int  i = 0; i < 4; i++)
        {
            temp[i].setX(cr);
            temp[i].setY(cc + i);
		}
		if(validPonits(temp) == true)
		{
			for(int i = 0; i < 4; i++)
			{
				line[i].setX(temp[i].getX());
				line[i].setY(temp[i].getY());
			}
			dir.setCurrentDirection(3);
		}
		else
		{
			cc = cc + 3;
			cr--;
		}
	}
	void rotate3()
	{
		cr++;
		cc = cc + 3;
		cr = cr - 3;
        for(int  i = 0; i < 4; i++)
        {
            temp[i].setX(cr + i);
            temp[i].setY(cc);
		}
		if(validPonits(temp) == true)
		{
			for(int i = 0; i < 4; i++)
			{
				line[i].setX(temp[i].getX());
				line[i].setY(temp[i].getY());
			}
			dir.setCurrentDirection(4);
		}
		else
		{
			cr--;
			cc = cc - 3;
			cr = cr + 3;
		}
	}
	void rotate4()
	{
		cr++;
		cr = cr + 3;
        for(int  i = 0; i < 4; i++)
        {
            temp[i].setX(cr);
            temp[i].setY(cc + i);
		}
		if(validPonits(temp) == true)
		{
			for(int i = 0; i < 4; i++)
			{
				line[i].setX(temp[i].getX());
				line[i].setY(temp[i].getY());
			}
			dir.setCurrentDirection(1);
		}
		else
		{
			cr--;
			cr = cr - 3;
		}
	}

	void moveVertical(String ch)
	{
		if(ch.equals("r") && cc < c - 2)
		{
			moveRightVertical();
		}
		else if(ch.equals("l") && cc > 1)
		{
			moveLeftVertical();
		}
		else if(ch.equals("d") && line[3].getX() < r - 2)
		{
			moveDownVertical();
		}
		else if(ch.equals("b"))
		{
			moveBotomVertical();
		}
		if(line[3].getX() < r - 5)
		{
			moveDownVertical();
		}
	}
	void moveRightVertical()
	{
		cc++;
		for(int i = 0; i < 4; i++)
		{
			line[i].setY(cc);
		}
	}
	void moveLeftVertical()
	{
		cc--;
		for(int i = 0; i < 4; i++)
		{
			line[i].setY(cc);
		}
	}
	void moveDownVertical()
	{
		cr++;
		for(int i = 0; i < 4; i++)
		{
			line[i].setX(cr + i);
		}
	}
	void moveBotomVertical()
	{
		cr = r - 5;
		for(int i = 0; i < 4; i++)
		{
			line[i].setX(cr + i);
		}
	}

	void moveHorizontal(String ch)
	{
		if(ch.equals("r") && line[3].getY() < c - 2)
		{
			moveRightHorizontal();
		}
		else if(ch.equals("l") && line[0].getY() > 1)
		{
			moveLeftHorizontal();
		}
		else if(ch.equals("d") && cr < r - 2)
		{
			moveDownHorizontal();
		}
		else if(ch.equals("b"))
		{
			moveBotomHorizontal();
		}
		if(cr != r - 2)
		{
			moveDownHorizontal();
		}
	}
	void moveRightHorizontal()
	{
		cc++;
		for(int i = 0; i < 4; i++)
		{
			line[i].setY(cc + i);
		}
	}
	void moveLeftHorizontal()
	{
		cc--;
		for(int i = 0; i < 4; i++)
		{
			line[i].setY(cc + i);
		}
	}
	void moveDownHorizontal()
	{
		cr++;
		for(int i = 0; i < 4; i++)
		{
			line[i].setX(cr);
		}
	}
	void moveBotomHorizontal()
	{
		cr = r - 2;
		for(int i = 0; i < 4; i++)
		{
			line[i].setX(cr);
		}
	}
}

class Tetris {
	public static void main(String[] arags) {
		int r = 30, c = 50;
		Scanner sc = new Scanner(System.in);

		Board m = new Board(r, c);
		m.assign();
		m.display();

		Shape s1 = null, s2 = null;

		Boolean flag = true;

		while(true)
		{
			if(flag == true)
			{
				s1 = new Shape(r, c);
				flag = false;
				s2 = null;
				m.assign(s1, s2);
				m.display();
			}
			s2 = new Shape(s1);
			String ch = sc.next();
			s1.execute(ch);
			flag = m.assign(s1, s2);
			m.display();
		}
	}

}