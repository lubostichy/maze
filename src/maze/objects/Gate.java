package maze.objects;

/**
 * Charakteristika brany
 * @author Lubos Tichy
 */
public class Gate extends maze.objects.TapeObject{
/**Brana je na zaciatku zavreta*/
    protected boolean OpenDoor = false;

    /**
     * test ci sa da brana otovorit
     * @return ak je zavreta tak true inak false
     */
    @Override
    public boolean canBeOpen() {
        return !this.OpenDoor;
    }
    
    /**
     * test ci sa da brana obsadit
     * @return ak je otvorena true inak false
     */
    @Override
    public boolean canSeize() {
        return (OpenDoor == true);
    }
    
    /**
     * Otvor branu
     * @return ak je uz otvorena false inak true
     */
    @Override
    public boolean open() {
        if (this.OpenDoor) {
            return false;
        }        
        this.OpenDoor = true;
        return true;
    }    
    
    /**
     * Vypis na obrazovku
     * @return ak je otvorena tak O inak G
     */
    @Override
    public String show() {
        if (OpenDoor) {
            return "O";
        }
        return "G";
    }
}
