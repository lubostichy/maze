package maze.tape;

import maze.objects.EObject;
import maze.objects.Finish;
import maze.objects.Gate;
import maze.objects.Key;
import maze.objects.TapeObject;
import maze.objects.Wall;

/**
 * Trieda vytvori objekty(stena,brana..)
 * @author Ľuboš Tichý
 */
public class TapeField {
    
    /** riadok pásky */
	private int x;
    
    /** stĺpec pásky */
	private int y;
    
    /** páska */
	private Tape tape;
    
    /** objekt */
	private TapeObject object;
    
    /** výskyt hlavy */
	private boolean isHead;
    
    /**
     * Položí objekt na políčko na základe predaného parametru.
     * @param tape páska 
     * @param x riadok
     * @param y stlpec
     * @param type typ objektu
     */
    public TapeField(final Tape tape, final int x, final int y, final EObject type) {
        this.tape = tape;
        this.x = x;
		this.y = y;
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
		if (object != null) {
			return object.canBeOpen();
        }
        return false;
    }
    
    /**
     * Testuje, či je možné objekt obsadiť.
     * @return true alebo false
     */
    public boolean canSeize() {
		if (object != null) {
			return object.canSeize();
        }
        return true;
    }

	public boolean isHead() {
		return isHead;
	}

	public void setHead(final boolean isHead) {
		this.isHead = isHead;
	}

	public Tape getTape() {
		return tape;
	}

	public void setTape(final Tape tape) {
		this.tape = tape;
	}
	public TapeObject getObject() {
		return object;
	}
	public void setObject(final TapeObject object) {
		this.object = object;
	}
	public int getX() {
		return x;
	}
	public void setX(final int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(final int y) {
		this.y = y;
	}
    
}
