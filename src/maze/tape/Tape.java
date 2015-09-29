package maze.tape;

/**
 * Trieda vytvorí pásku s objektami, hlavy a vypíše na výstup podobu bludiska.
 * @author Ľuboš Tichý
 */
public class Tape 
{

    /** 2D páska pre objekty */
    public TapeField[][] arrField;
    
    /** riadok pásky */
    protected int row;
    
    /** stĺpec pásky */
    protected int col;

    /** počet hláv */
    protected int h;
    
    /** reťazec objektov */
    protected String whole;
    
    /**
     * Vytvorí 2D pole políčok.
     * @param r riadky
     * @param c stĺpce
     * @param h počet hráčov
     * @param format reťazec objektov, ktoré sa vytvoria
     */
    public Tape(int r,int c, int h, String format)
    {
        this.row = r;
        this.col = c;
        this.arrField = new TapeField[r][c];        
        this.h = h ;
              
        int j = 0;
        int i = 0;
        String ch;          
        for(int k = 0; format.length() > k; k++)  
        { 
            if (j == c)
            {
                j=0;
                i++;
            }
            
            ch = format.substring(k, k + 1);
            
            if(null != ch)
            switch (ch) 
            {
                case "w":
                    this.arrField[i][j] = new TapeField(this, i, j, "w");
                    j++;
                    break;
                case "g":
                    this.arrField[i][j] = new TapeField(this, i, j, "g");
                    j++;
                    break;
                case "p":
                    this.arrField[i][j] = new TapeField(this, i, j, "p");
                    j++;
                    break;
                case "k":
                    this.arrField[i][j] = new TapeField(this, i, j, "k");
                    j++;
                    break;
                case "F":
                    this.arrField[i][j] = new TapeField(this, i, j, "f");
                    j++;
                    break;
            }
        }
    }
    
    /**
     * Vytvorí hráča(hlavu).
     * @param i ID hráča
     * @return novú hlavu alebo null
     */
    public TapeHead createHead(int i)
    {
        //int j=0;
        for(int x = 0; x < this.col; x++) 
        {
            for(int y=0; y<this.row; y++) 
            {
                if(arrField[x][y].canSeize()) 
                {
                    arrField[x][y].isHead = 1;
                    TapeHead tmp = new TapeHead(i, arrField[x][y]);
                    return tmp;
                }
            
            }
        }
        return null;    
    }
    /**
     * Vypíše na výstup bludisko aj s hráčmi.
     * @param h hlava
     */
    public void show(TapeHead h) 
    {        
        for (int i = 0; i < col; i++) 
        {
            for (int j = 0; j < row; j++) 
            {
                if (arrField[i][j].object != null) // ak je voľné políčko
                { 
                    if (arrField[i][j].isHead == 1)  // je na ňom hráč
                    {
                        switch (h.dir) 
                        {
                            case "north":
                                System.out.print("^");
                                break;
                            case "west":
                                System.out.print("<");
                                break;
                            case "south":
                                System.out.print("v");
                                break;
                            case "east":
                                System.out.print(">");
                                break;
                        }
                    }
                    else 
                    {
                        System.out.print(arrField[i][j].object.show()+" "); // voľné políčko
                    }
                } 
                else { // ak je políčko s objektom
                    if (arrField[i][j].isHead == 1)  // je na ňom hráč
                    {
                        switch (h.dir) 
                        {
                            case "north":
                                System.out.print("^ ");
                                break;
                            case "west":
                                System.out.print("< ");
                                break;
                            case "south":
                                System.out.print("v ");
                                break;
                            case "east":
                                System.out.print("> ");
                                break;
                        }
                    }
                    else 
                    {
                        System.out.print("P ");
                    }
                }
            }
            System.out.println();
        }
    }
    
}
