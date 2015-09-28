package maze.objects;

/**
 * Predstavuje kľúč.
 * @author Ľuboš Tichý.
 */
public class Key extends maze.objects.TapeObject {

    /**
     * Kľúč nie je možné otvoriť.
     * @return false
     */
    @Override
    public boolean canBeOpen() {
        return false;
    }
    
    /**
     * Kľúč nie je možné obsadiť.
     * @return false
     */
    @Override
    public boolean canSeize() {
        return false;
    }
    
    /**
     * Kľúč nie je možné otvoriť.
     * @return false
     */
    @Override
    public boolean open() {
        return false;
    }

    /**
     * Vypíše na obrazovku symbol kľúča.
     * @return K
     */
    @Override
    public String show() {
        return "K";
    }
}
