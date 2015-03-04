import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import de.bezier.guido.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Minesweeper extends PApplet {


public final static int NUM_ROWS = 20;
public final static int NUM_COLS = 20;
public final static int NUM_BOMBS = 10;
private MSButton[][] buttons; //2d array of minesweeper buttons
private ArrayList <MSButton> bombs; //ArrayList of just the minesweeper buttons that are mined
<<<<<<< HEAD
boolean gameOver = false;
=======

>>>>>>> 12b83bcd0f9bd14e212af3ea60f3d07e3421bd3a
public void setup ()
{
    size(400, 400);
    textAlign(CENTER,CENTER);
<<<<<<< HEAD
=======
    
>>>>>>> 12b83bcd0f9bd14e212af3ea60f3d07e3421bd3a
    // make the manager
    Interactive.make( this );
    buttons = new MSButton[NUM_ROWS][NUM_COLS];
    for(int i = 0; i < NUM_ROWS; i++)
    {
        for(int k = 0; k < NUM_COLS; k++)
        {
            buttons[i][k] = new MSButton(i, k);
        }
    }
    bombs = new ArrayList <MSButton>();
    setBombs();
}
public void setBombs()
{
    for(int i = 0; i < NUM_BOMBS; i++)
    {
        int row = (int)(Math.random() * NUM_ROWS);
        int col = (int)(Math.random() * NUM_COLS);
        if(!bombs.contains(buttons[row][col]))
            bombs.add(buttons[row][col]);
    }
}
public void draw ()
{
<<<<<<< HEAD
    background(0);
=======
    //background(0);
>>>>>>> 12b83bcd0f9bd14e212af3ea60f3d07e3421bd3a
    if(isWon())
        displayWinningMessage();
}
public boolean isWon()
{
    int bombCount = 0;
<<<<<<< HEAD
    int buttonsCount = 0;
=======
>>>>>>> 12b83bcd0f9bd14e212af3ea60f3d07e3421bd3a
    for(int i = 0; i < bombs.size(); i++)
        if(bombs.get(i).marked == true)
            bombCount++;
    if(bombCount == NUM_BOMBS)
<<<<<<< HEAD
    {
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int k = 0; k < NUM_COLS; k++)
            {
                if(buttons[i][k].isClicked() == true)
                    buttonsCount++;
            }
        }
        if(buttonsCount == NUM_ROWS*NUM_COLS)
            return true;
    }
    return false;
=======
        return true;
    else
        return false;
>>>>>>> 12b83bcd0f9bd14e212af3ea60f3d07e3421bd3a
}
public void displayLosingMessage()
{
    buttons[0][0].setLabel("G");
    buttons[0][1].setLabel("a");
    buttons[0][2].setLabel("m");
    buttons[0][3].setLabel("e");
    buttons[0][4].setLabel(" ");
    buttons[0][5].setLabel("O");
    buttons[0][6].setLabel("v");
    buttons[0][7].setLabel("e");
    buttons[0][8].setLabel("r");
<<<<<<< HEAD
    gameOver = true;
=======
>>>>>>> 12b83bcd0f9bd14e212af3ea60f3d07e3421bd3a
}
public void displayWinningMessage()
{

    buttons[0][0].setLabel("Y");
    buttons[0][1].setLabel("o");
    buttons[0][2].setLabel("u");
    buttons[0][3].setLabel(" ");
    buttons[0][4].setLabel("W");
    buttons[0][5].setLabel("i");
    buttons[0][6].setLabel("n");
    buttons[0][7].setLabel("!");
<<<<<<< HEAD
    gameOver = true;
=======
>>>>>>> 12b83bcd0f9bd14e212af3ea60f3d07e3421bd3a
}

public class MSButton
{
    private int r, c;
    private float x,y, width, height;
    private boolean clicked, marked;
    private String label;
<<<<<<< HEAD
    public MSButton (int rr, int cc)
=======
    public MSButton ( int rr, int cc )
>>>>>>> 12b83bcd0f9bd14e212af3ea60f3d07e3421bd3a
    {
        width = 400/NUM_COLS;
        height = 400/NUM_ROWS;
        r = rr;
        c = cc; 
        x = c*width;
        y = r*height;
        label = "";
        marked = clicked = false;
        Interactive.add( this ); // register it with the manager
    }
    public boolean isMarked()
    {
        return marked;
    }
    public boolean isClicked()
    {
        return clicked;
    }
    // called by manager
    
    public void mousePressed () 
    {
<<<<<<< HEAD
        if(gameOver == false)
            {
            clicked = true;
            if(keyPressed)
            {
                marked = !marked;
            }
            else if(bombs.contains(this))
            {
                for(int i = 0; i < bombs.size(); i++)
                    bombs.get(i).setLabel("X");
                displayLosingMessage();
            }
            else if(countBombs(r, c) > 0)
            {
                label = str(countBombs(r, c));
            }
            else
            {
                if(isValid(r, c-1) && buttons[r][c-1].isClicked() == false)
                    buttons[r][c-1].mousePressed();
                if(isValid(r-1, c) && buttons[r-1][c].isClicked() == false)
                    buttons[r-1][c].mousePressed();
                if(isValid(r, c+1) && buttons[r][c+1].isClicked() == false)
                    buttons[r][c+1].mousePressed();
                if(isValid(r+1, c) && buttons[r+1][c].isClicked() == false)
                    buttons[r+1][c].mousePressed();
                if(isValid(r+1, c+1) && buttons[r+1][c+1].isClicked() == false)
                    buttons[r+1][c+1].mousePressed();
                if(isValid(r-1, c-1) && buttons[r-1][c-1].isClicked() == false)
                    buttons[r-1][c-1].mousePressed();
                if(isValid(r+1, c-1) && buttons[r+1][c-1].isClicked() == false)
                    buttons[r+1][c-1].mousePressed();
                if(isValid(r-1, c+1) && buttons[r-1][c+1].isClicked() == false)
                    buttons[r-1][c+1].mousePressed();
            }
=======
        clicked = true;
        if(keyPressed)
        {
            marked = !marked;
        }
        else if(bombs.contains(this))
        {
            displayLosingMessage();
        }
        else if(countBombs(r, c) > 0)
        {
            label = str(countBombs(r, c));
        }
        else
        {
            if(isValid(r, c-1) && buttons[r][c-1].isClicked() == false)
                buttons[r][c-1].mousePressed();
            if(isValid(r-1, c) && buttons[r-1][c].isClicked() == false)
                buttons[r-1][c].mousePressed();
            if(isValid(r, c+1) && buttons[r][c+1].isClicked() == false)
                buttons[r][c+1].mousePressed();
            if(isValid(r+1, c) && buttons[r+1][c].isClicked() == false)
                buttons[r+1][c].mousePressed();
            if(isValid(r+1, c+1) && buttons[r+1][c+1].isClicked() == false)
                buttons[r+1][c+1].mousePressed();
            if(isValid(r-1, c-1) && buttons[r-1][c-1].isClicked() == false)
                buttons[r-1][c-1].mousePressed();
            if(isValid(r+1, c-1) && buttons[r+1][c-1].isClicked() == false)
                buttons[r+1][c-1].mousePressed();
            if(isValid(r-1, c+1) && buttons[r-1][c+1].isClicked() == false)
                buttons[r-1][c+1].mousePressed();
>>>>>>> 12b83bcd0f9bd14e212af3ea60f3d07e3421bd3a
        }
    }
    public void draw ()
    {    
        if (marked)
            fill(0, 0, 255);
        else if(clicked && bombs.contains(this)) 
            fill(255,0,0);
        else if(clicked)
            fill(200);
        else 
            fill(100);
        rect(x, y, width, height);
        fill(0);
        text(label,x+width/2,y+height/2);
    }
    public void setLabel(String newLabel)
    {
        label = newLabel;
    }
    public boolean isValid(int r, int c)
    {
        if(r >= 0 && r < NUM_ROWS && c >= 0 && c < NUM_COLS)
            return true;
        return false;
    }
    public int countBombs(int row, int col)
    {
        int numBombs = 0;
        if(isValid(row, col-1))
            if(bombs.contains(buttons[row][col-1]))
                numBombs++;
        if(isValid(row-1, col-1))
            if(bombs.contains(buttons[row-1][col-1]))
                numBombs++;
        if(isValid(row-1, col))
            if(bombs.contains(buttons[row-1][col]))
                numBombs++;
        if(isValid(row-1, col+1))
            if(bombs.contains(buttons[row-1][col+1]))
                numBombs++;
        if(isValid(row, col+1))
            if(bombs.contains(buttons[row][col+1]))
                numBombs++;
        if(isValid(row+1, col+1))
            if(bombs.contains(buttons[row+1][col+1]))
                numBombs++;
        if(isValid(row+1, col))
            if(bombs.contains(buttons[row+1][col]))
                numBombs++;
        if(isValid(row+1, col-1))
            if(bombs.contains(buttons[row+1][col-1]))
                numBombs++;
        return numBombs;
    }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Minesweeper" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
