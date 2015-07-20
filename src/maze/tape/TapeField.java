package maze.tape;

import maze.objects.*;

/**
 * Trieda vytvori objekty(stena,brana..)
 * @author xtichy23,xvlkov01
 */
public class TapeField {
    /**riadok*/
    int x;
    /**stlpec*/
    int y;
    /**Paska*/
    protected Tape tape;
    /**Objekt*/
    protected TapeObject object = null;
    /**ci je hlava*/
    protected int isHead = 0;
    
    /**
     * Vytvori objekty na zakladade predaneho argumentu
     * @param tape paska 
     * @param x riadok
     * @param y stlpec
     * @param type typ objektu
     */
    public TapeField (Tape tape, int x, int y, String type) {
        this.tape = tape;
        this.x = x;
        this.y = y;
        this.object = null;
        this.isHead = 0;
        if (type.equals("w")) {
            this.object = new Wall();
        }
        if (type.equals("g")) {
            this.object = new Gate();
        }   
        if (type.equals("k")) {
            this.object = new Key();
        }  
        if (type.equals("f")) {
            this.object = new Finish();
        }  
        
    }
    /**
     * Testuje ci sa objekt da otvorit
     * @return ak je objekt brana vracia true inak false
     */
    public boolean canBeOpen() {
        if (this.object != null) {
            return this.object.canBeOpen();
        }
        return false;
    }
    
    /**
     * Testuje ci je policko mozne obsadit
     * @return pri wall,zatvorenej gate false inak true
     */
    public boolean canSeize() {
        if (this.object != null) {
            return object.canSeize();
        }
        return true;
    }
    
}
