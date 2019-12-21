package maze.tape;

import maze.objects.*;

/**
 * Trieda definuje správanie sa hlavy(hráča) ako sú pohyby, zber kľúčov, otvorenie brány..
 * @author Ľuboš Tichý
 */
public class TapeHead 
{
    
    /** identifikátor hlavy */
    protected int ident = 0;
    
    /** políčko, na ktorej sa hlava nachádza */
    protected TapeField field = null;
    
    /** počet kľúčov, ktoré vlastní */
    protected int keys = 0;
    
    /** smer, ktorým sa pozerá */
    protected String dir = "north";
    
    /**
     * Inicializuje hráča.
     * @param id identifikátor
     * @param f políčko, na ktoré má byť hlava umiestnená
     */
    public TapeHead (int id, TapeField f) 
    {
        ident = id;        
        field = f;
    }

    /**
     * Pridá kľúč.
     */
    public void addKey () 
    {
        keys++;
    }
    
    /**
     * Vráti počet klúčov.
     * @return počet kľúčov
     */
    public int keys() 
    {
        return keys;
    }
    
    /**
     * Otočí hráča vľavo.
     */    
    public void left () 
    {
        switch (dir) {
            case "north":
                dir = "west";
                break;
            case "west":
                dir = "south";
                break;
            case "south":
                dir = "east";
                break;
            case "east":
                dir = "north";
                break;
        }
    }
    
    /**
     * Otočí hráča vpravo.
     */
    public void right () 
    {        
        switch (dir) {
            case "north":
                dir = "east";
                break;
            case "east":
                dir = "south";
                break;
            case "south":
                dir = "west";
                break;
            case "west":
                dir = "north";
                break;
        }
    }
    
    /**
     * Zistí, aké políčko je pred objektom.
     * @return vráti políčko pred objektom
     */
    public TapeField frontField() 
    {
        
        /** pomocné políčko  */
        TapeField tmp = field;

        /** index pomocného políčka  */
        int tmpX = field.x;
        
        /** index pomocného políčka */
        int tmpY = field.y;
        
        switch (dir) 
        {
            case "north":
                if (field.x == 0)
                {
                    return null;
                }
                tmp = field.tape.arrField[tmp.x-1][tmp.y];
                tmp.x = tmpX-1;
                tmp.y = tmpY;
                tmp.object = field.tape.arrField[tmp.x][tmp.y].object;
                tmp.tape = field.tape;
                return tmp;
            case "east":
                if (this.field.tape.columnCount == (field.y + 1))
                {
                    return null;
                }
                tmp = field.tape.arrField[tmp.x][tmp.y+1];
                tmp.x = tmpX;
                tmp.y = tmpY+1;
                tmp.object = field.tape.arrField[tmp.x][tmp.y].object;
                tmp.tape = field.tape;            
                return tmp;
            case "south":
                if (this.field.tape.rowCount == (field.x + 1))
                {
                    return null;
                }
                tmp = field.tape.arrField[tmp.x+1][tmp.y];
                tmp.x = tmpX+1;
                tmp.y = tmpY;
                tmp.object = field.tape.arrField[tmp.x][tmp.y].object;
                tmp.tape = field.tape;
                return tmp;
            case "west":
                if (this.field.y == 0)
                {
                    return null;
                }
                tmp = field.tape.arrField[tmp.x][tmp.y-1];
                tmp.x = tmpX;
                tmp.y = tmpY-1;
                tmp.object = field.tape.arrField[tmp.x][tmp.y].object;
                tmp.tape = field.tape;
                return tmp;
            default:
                return null;
        }
    }
    
    /**
     * Hráč zoberie klúč.
     * @return ak je pred ním klúč vracia true inak false
     */
    public boolean take() 
    {        
        TapeField tmp = frontField();
        if (tmp.object instanceof Key) 
        {
            field.tape.arrField[tmp.x][tmp.y].object = null;
            keys++;
            return true;
        }
        return false;       
    }
    
    /**
     * Otvorí bránu.
     * @return ak je pred hráčom brána a hráč má klúč, tak vracia true, inak false
     */
    public boolean open() 
    {
        TapeField tmp = frontField();
        if (tmp.object instanceof Gate) 
        {
            if (tmp.object.canBeOpen()) 
            {
                if (tmp.object.open()) 
                {
                    keys--;
                    return true;
                }
            }
        }        
        return false;
    }
    
    /**
     * Krok dopredu smerom, ktorým je hráč otočený.
     * @return ak je krok možný, tak vracia true, inak false
     */
    public boolean step() 
    {        
        TapeField tmp = frontField();
        if (tmp != null) 
        {
            if (tmp.canSeize()) 
            {
                this.field.tape.arrField[field.x][field.y].isHead = false;
                this.field.tape.arrField[tmp.x][tmp.y].isHead = true;
                this.field = tmp;
                return true;
            }
        }        
        return false;
    }
}
