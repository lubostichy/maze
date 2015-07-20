package maze.objects;

/**
 * Trieda rozsiruje triedu TapeObject
 * Objekt typu stena
 * @author xtichy23,xvlkov01
 */

public class Wall extends maze.objects.TapeObject {    
    
    /**
     * test ci je mozne otvorit stenu
     * @return false
     */
    @Override
    public boolean canBeOpen() {
        return false;
    }
    
    /**
     * test ci je mozne obsadit 
     * @return false
     */
    @Override
    public boolean canSeize() {
        return false;
    }
    
    /**
     * test ci je mozne otvorit stenu
     * @return false
     */
    @Override
    public boolean open() {
        return false;
    }
    
    /**
     * co sa ma vypisat na obrazovku
     * @return W
     */
    @Override
    public String show() {
        return "W";
    }
    
    
    
}
