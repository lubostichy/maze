package maze.tape;

import maze.objects.*;

/**
 * Trieda definuje správanie sa hlavy(hráča) ako sú pohyby, zber kľúčov, otvorenie brány..
 * @author Ľuboš Tichý
 */
public class TapeHead {
    
    protected int ident = 0;
    protected TapeField field = null;
    protected int keys = 0;
    protected String dir = "north";
    /**
     * Inicializacia hraca
     * @param id identifikator
     * @param f policko na kt. ma byt umiestneni
     */
    public TapeHead (int id, TapeField f) {
        ident = id;        
        field = f;
    }
    
    /*public TapeField seizedField () {
        return field;
    }*/
    /**
     * Prida kluc
     * @param n pocet aktualnych klucov
     */
    public void addKey (int n) {
        keys++;
    }
    /**
     * Vrati pocet klucov
     * @return kluce
     */
    public int keys() {
        return keys;
    }
    /**
     * Otocenie hraca vlavo
     */
    
    public void left () {
        if (dir.equals("north")) {
            dir = "west";
            //System.out.print(dir);
            return;
        }
        if (dir.equals("west")) {
            dir = "south";
            //System.out.print(dir);
            return;
        }
        if (dir.equals("south")) {
            dir = "east";
            //System.out.print(dir);
            return;
        }
        if (dir.equals("east")) {
            dir = "north";
            //System.out.print(dir);
        }
    }
    /**
     * Otocenie hraca vpravo
     */
    public void right () {
        
        if (dir.equals("north")) {
            dir = "east";
            //System.out.print(dir);
            return;
        }
        if (dir.equals("east")) {
            dir = "south";
            //System.out.print(dir);
            return;
        }
        if (dir.equals("south")) {
            dir = "west"; 
            //System.out.print(dir);
            return;
        }
        if (dir.equals("west")) {
            dir = "north";
            //System.out.print(dir);
        }
    }
    
    public TapeField frontField() {
        TapeField tmp = field;
        
        /*if (field.object != null) {
            System.out.println("aktualne policko: "+field.object.show());
        }
        else {
            System.out.println("aktualne policko: prazdne");
        }*/
        int tmpX = field.x;
        int tmpY = field.y;
        if (dir.equals("north")) {
            if (field.x == 0) {
                return null;
            }
            //System.out.println(dir);
            //System.out.println("x: "+field.x+" y: "+field.y);
            tmp = field.tape.arrField[tmp.x-1][tmp.y];
            tmp.x = tmpX-1;
            tmp.y = tmpY;
            tmp.object = field.tape.arrField[tmp.x][tmp.y].object;
            tmp.tape = field.tape;
            
            //System.out.println("x: "+tmp.x+" y: "+tmp.y);
            return tmp;
        }
        if (dir.equals("east")) {
            if (this.field.tape.col == (field.y + 1)) {
                return null;
            }
            //System.out.println(dir);
            tmp = field.tape.arrField[tmp.x][tmp.y+1];
            tmp.x = tmpX;
            tmp.y = tmpY+1;
            tmp.object = field.tape.arrField[tmp.x][tmp.y].object;
            tmp.tape = field.tape;
            
            //System.out.println("x: "+tmp.x+" y: "+tmp.y);
            return tmp;
        }
        if (dir.equals("south")) {
            if (this.field.tape.row == (field.x + 1)) {
                return null;
            } 
            //System.out.println(dir);
            tmp = field.tape.arrField[tmp.x+1][tmp.y];
            tmp.x = tmpX+1;
            tmp.y = tmpY;
            tmp.object = field.tape.arrField[tmp.x][tmp.y].object;
            tmp.tape = field.tape;
            
            //System.out.println("x: "+tmp.x+" y: "+tmp.y);
            return tmp;
        }
        if (dir.equals("west")) {
            if (this.field.y == 0) {
                return null;
            } 
            //System.out.println(dir);
            tmp = field.tape.arrField[tmp.x][tmp.y-1];
            tmp.x = tmpX;
            tmp.y = tmpY-1;
            tmp.object = field.tape.arrField[tmp.x][tmp.y].object;
            tmp.tape = field.tape;
            
            //System.out.println("x: "+tmp.x+" y: "+tmp.y);
            return tmp;
        }
        return null;
    }
    /**
     * Hrac zoberie kluc
     * @return ak je pred nim kluc vracia true inak false
     */
    public boolean take() {        
        TapeField tmp = frontField();
        if (tmp.object instanceof Key) {
            field.tape.arrField[tmp.x][tmp.y].object = null;
            keys++;
            return true;
        }
        return false;       
    }
    
    /**
     * Otvori branu
     * @return ak je pred hracom brana a ma kluc vracia true inak false
     */
    public boolean open() {
        TapeField tmp = frontField();
        if (tmp.object instanceof Gate) {
            if (tmp.object.canBeOpen()) {
                if (tmp.object.open()) {
                    keys--;
                    return true;
                }
            }
        }
        
        return false;
    }
    /**
     * Krok dopredu smerom ktorym je hrac otoceny.
     * 
     * @return Ak je krok mozny vracia true inak false
     */
    public boolean step() {
        TapeField tmp = frontField();
        /*if (tmp.object != null) {
            System.out.print("predo mnou policko: "+tmp.object.show());
        }
        else {
            System.out.print("predo mnou policko je prazdne");
        }*/
        if (tmp != null) {
            if (tmp.canSeize()) {
                //System.out.print("kracam z: "+field.x+" "+field.y);
                this.field.tape.arrField[field.x][field.y].isHead = 0;
                //System.out.println(" na: "+tmp.x+" "+tmp.y);
                this.field.tape.arrField[tmp.x][tmp.y].isHead = 1;
                this.field = tmp;
                return true;
            }
        }
        
        return false;
    }
    
}
