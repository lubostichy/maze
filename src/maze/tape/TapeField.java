package maze.tape;

import maze.objects.*;

/**
 * Trieda vytvori objekty(stena,brana..)
 * @author Ľuboš Tichý
 */
public class TapeField {
    
    /** riadok pásky */
    protected int x;
    
    /** stĺpec pásky */
    protected int y;
    
    /** páska */
    protected Tape tape;
    
    /** objekt */
    protected TapeObject object = null;
    
    /** výskyt hlavy */
    protected boolean isHead = false;
    
    /**
     * Položí objekt na políčko na základe predaného parametru.
     * @param tape páska 
     * @param x riadok
     * @param y stlpec
     * @param type typ objektu
     */
    public TapeField(Tape tape, int x, int y, EObject type) {
        this.tape = tape;
        this.x = x;
        this.y = y;
        this.object = null;
        this.isHead = false;
        switch (type) {
            case WALL:
                this.object = new Wall();
                break;
            case GATE:
                this.object = new Gate();
                break;
            case KEY:
                this.object = new Key();
                break;
            case FINISH:
                this.object = new Finish();  
                break;
		default:
			break;
        }
        
    }
    /**
     * Testuje, či je možné objekt otvoriť.
     * @return ak je objekt brána vracia true alebo false, inak false
     */
    public boolean canBeOpen() {
        if (this.object != null) {
            return this.object.canBeOpen();
        }
        return false;
    }
    
    /**
     * Testuje, či je možné objekt obsadiť.
     * @return true alebo false
     */
    public boolean canSeize() {
        if (this.object != null) {
            return object.canSeize();
        }
        return true;
    }
    
}
