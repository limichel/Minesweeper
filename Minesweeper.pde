import de.bezier.guido.*;
public final static int NUM_ROWS = 20;
public final static int NUM_COLS = 20;
public final static int NUM_BOMBS = 10;
private MSButton[][] buttons; //2d array of minesweeper buttons
private ArrayList <MSButton> bombs; //ArrayList of just the minesweeper buttons that are mined
boolean gameOver = false;
void setup ()
{
    size(400, 400);
    textAlign(CENTER,CENTER);
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
    background(0);
    if(isWon())
        displayWinningMessage();
}
public boolean isWon()
{
    int bombCount = 0;
    int buttonsCount = 0;
    for(int i = 0; i < bombs.size(); i++)
        if(bombs.get(i).marked == true)
            bombCount++;
    if(bombCount == NUM_BOMBS)
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
    gameOver = true;
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
    gameOver = true;
}

public class MSButton
{
    private int r, c;
    private float x,y, width, height;
    private boolean clicked, marked;
    private String label;
    public MSButton (int rr, int cc)
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