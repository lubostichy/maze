package maze.objects;

/**
 * Charakteristika kluca
 * @author Lubos Tichy
 */
public class Key extends maze.objects.TapeObject {
    /**
     * test ci sa da kluc  otvorit
     * @return false
     */
    @Override
    public boolean canBeOpen() {
        return false;
    }
    
    /**
     * kluc nie je mozne obsadit
     * @return false
     */
    @Override
    public boolean canSeize() {
        return false;
    }
    
    /**
     * kluc sa neda otvorit
     * @return false
     */
    @Override
    public boolean open() {
        return false;
    }
    /**
     * Vypis na obrazovku
     * @return K
     */
    @Override
    public String show() {
        return "K";
    }
    
}
