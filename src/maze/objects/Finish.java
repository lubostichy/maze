package maze.objects;

/**
 * Trieda rozsiruje triedu TapeObject
 * Akcie ciela
 * @author Lubos Tichy
 */
public class Finish extends maze.objects.TapeObject {
    
    /**
     * test ci sa da otovrit
     * @return false
     */
    @Override
    public boolean canBeOpen() {
        return false;
    }
    
    /**
     * test ci sa da obsadit
     * @return true
     */
    @Override
    public boolean canSeize() {
        return true;
    }
    
    /**
     * otvor ciel
     * @return false
     */
    @Override
    public boolean open() {
        return false;
    }
    /**
     * Vypis na obrazovku
     * @return F
     */
    @Override 
    public String show() {
        return "F";
    }
}
