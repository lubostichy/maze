package maze.tape;

/**
 * Trieda vytvori pasku z objektami, hlavu a vypise na vystup stav bludiska
 * @author Lubos Tichy
 */
public class Tape {
    /**2-d pole pre objekty(stena,brana...)*/
    public TapeField[][] arrField;
    //public TapeHead[] headArr;
    /**riadok*/
    protected int row;
    /**stlpec*/
    protected int col;
    /**pocet hlav*/
    protected int h;
    /**retazec objektov*/
    protected String whole;
    
    /**
     * Vytvori 2-d pole objektov TapeField
     * @param r riadky
     * @param c stlpce
     * @param h pocet hracov
     * @param format retazec objektov ktore maju byt vytvorene(stena,brana,...)
     */
    public Tape(int r,int c, int h, String format)
    {
        this.row=r;
        this.col=c;
        this.arrField=new TapeField[r][c];
        //fieldArr= new Tape();
        this.h=h;
      // this.headArr= new TapeHead[h];
        int j=0;
        int i=0;
       String ch;  
       for(int k=0;format.length()>k;k++)  
        { 
            if(j==c)
            {
                j=0;
                i++;
            }
        
            ch=format.substring(k,k+1);
            //System.out.printf ("%s\n",ch);
           // if(" ".equals(ch)) continue;
            if("w".equals(ch))
            {
                //System.out.printf ("%s %d %d\n",ch,i,j);
                this.arrField[i][j]=new TapeField(this,i,j,"w");
                j++;
            }
            else if(("g".equals(ch)))
            {
                //System.out.printf ("%s %d %d\n",ch,i,j);
                this.arrField[i][j]=new TapeField(this,i,j,"g");
               j++;
            }
            else if(("p".equals(ch))){
                //System.out.printf ("%s %d %d\n",ch,i,j);
                this.arrField[i][j]=new TapeField(this,i,j,"p");
                j++;
            }
            else if("k".equals(ch))
            {
                //System.out.printf ("%s %d %d\n",ch,i,j);
                this.arrField[i][j]=new TapeField(this,i,j,"k");
                j++;
            }
            else if("F".equals(ch))
            {
                //System.out.printf ("%s %d %d\n",ch,i,j);
                this.arrField[i][j]=new TapeField(this,i,j,"f");
                j++;
            }
        }
        
    }
    

    
    /*public TapeField fieldAt(int i, int j)
    {
        if(i>=this.row) return null;
        if(j>=this.col) return null;
        
        return this.arrField[i][j];
        
    }*/
    /**
     * Vytvori hraca(hlavu)
     * @param i id hraca
     * @return novu hlavu alebo null
     */
   public TapeHead createHead(int i)
    {
        //int j=0;
        for(int x = 0; x < this.col; x++) {
            for(int y=0; y<this.row; y++) {
                if(arrField[x][y].canSeize()) {
                    arrField[x][y].isHead = 1;
                    TapeHead tmp = new TapeHead(i, arrField[x][y]);
                    return tmp;
                }
            
            }
        }
        return null;    
    }
    /**
     * Vypise na vystup bludisko aj s hracom
     * @param h hlava
     */
    public void show(TapeHead h) {
        
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (arrField[i][j].object != null) {
                    if (arrField[i][j].isHead == 1) {
                        //System.out.print("X "); 
                        if (h.dir.equals("north")) {
                            System.out.print("^");
                        }
                        if (h.dir.equals("west")) {
                            System.out.print("<");
                        }
                        if (h.dir.equals("south")) {
                            System.out.print("v");
                        }
                        if (h.dir.equals("east")) {
                            System.out.print(">");
                        }
                    }
                    else {
                        System.out.print(arrField[i][j].object.show()+" ");
                    }
                } 
                else {
                    if (arrField[i][j].isHead == 1) {
                        //System.out.print("X ");    
                        if (h.dir.equals("north")) {
                            System.out.print("^ ");
                        }
                        if (h.dir.equals("west")) {
                            System.out.print("< ");
                        }
                        if (h.dir.equals("south")) {
                            System.out.print("v ");
                        }
                        if (h.dir.equals("east")) {
                            System.out.print("> ");
                        }
                    }
                    else {
                        System.out.print("P ");
                    }
                }
            }
            System.out.println();
        }
    }
    
}
