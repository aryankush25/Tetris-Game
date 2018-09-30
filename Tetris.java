import java.util.*;
import java.io.*;
import java.lang.*;

class Board {

	int r;
	int c;
	char[][] mat;

	Board(int r, int c)
	{
		this.r = c;
		this.c = r;
		mat = new char[r][c];
	}

	boolean checkX(int t, Shape s)
	{
		for(int i = 0; i < 4; i++)
		{
			if(t == s.line[i].getX())
			{
				return true;
			}
		}
		return false;
	}

	boolean checkY(int t, Shape s)
	{
		for(int i = 0; i < 4; i++)
		{
			if(t == s.line[i].getY())
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

	void assign(Shape s)
	{
		for(int i = 0; i < r; i++)
		{
			for(int j = 0; j < c; j++)
			{
				if(i == 0 || j == 0 || i == r - 1 || j == c - 1)
				{
					mat[i][j] = '|';
				}
				else if(checkX(i, s) && checkY(j, s))
				{
					mat[i][j] = '#';
				}
				else
				{
					mat[i][j] = ' ';	
				}
			}
		}
	}

	void display()
	{
		for(int i = 0; i < r; i++)
		{
			for(int j = 0; j < c; j++)
			{
				System.out.print(mat[i][j]);
			}
			System.out.println();
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

class Shape {
	Point[] line;
	int r, c, cc = 13, cr = 1;

	Shape(int r, int c)
	{
		this.r = r;
		this.c = c;
		line = new Point[4];

		for(int i = 0; i < 4; i++)
		{
			line[i] = new Point();
		}

		/*Random rand = new Random();
		int  temp = rand.nextInt( c - 4) + 1;*/

		for(int i = 0; i < 4; i++)
		{
			line[i].setX(cr);
			line[i].setY(cc + i);
		}
	}

	void execute(String ch)
	{
		if(ch.equals("r") || ch.equals("l") || ch.equals("b") || ch.equals("d"))
		{
			move(ch);
		}
		else if(ch.equals("rr"))
		{
			rotate(ch);
		}
	}

	void rotate(String ch)
	{
		
	}

	void move(String ch)
	{
		if(ch.equals("r") && cc != c - 6)
		{
			moveRight();
		}
		else if(ch.equals("l") && cc != 1)
		{
			moveLeft();
		}
		else if(ch.equals("d") && cr != r - 2)
		{
			moveDown();
		}
		else if(ch.equals("b"))
		{
			moveBotom();
		}
		if(cr != 28)
		{
			moveDown();
		}
	}
	void moveRight()
	{
		cc++;
		for(int i = 0; i < 4; i++)
		{
			line[i].setY(cc + i);
		}
	}
	void moveLeft()
	{
		cc--;
		for(int i = 0; i < 4; i++)
		{
			line[i].setY(cc + i);
		}
	}
	void moveDown()
	{
		cr++;
		for(int i = 0; i < 4; i++)
		{
			line[i].setX(cr);
		}
	}
	void moveBotom()
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
		int r = 30, c = 30;
		Scanner sc = new Scanner(System.in);
		
		Board m = new Board(r, c);
		m.assign();
		m.display();

		Shape s = new Shape(r, c);

		while(true)
		{
			m.assign(s);
			m.display();
			String ch = sc.next();
			s.execute(ch);
		}
	}
	
}