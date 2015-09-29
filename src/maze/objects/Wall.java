package maze.objects;

/**
 * Predstavuje stenu.
 * @author Ľuboš Tichý
 */
public class Wall extends maze.objects.TapeObject 
{    
    
    /**
     * Stenu nie je možné obsadiť.
     * @return false
     */
    @Override
    public boolean canBeOpen() 
    {
        return false;
    }
    
    /**
     * Stenu nie je možné obsadiť.
     * @return false
     */
    @Override
    public boolean canSeize() 
    {
        return false;
    }
    
    /**
     * Stenu nie je možné otvoriť.
     * @return false
     */
    @Override
    public boolean open() 
    {
        return false;
    }
    
    /**
     * Vypíše symbol steny.
     * @return W
     */
    @Override
    public String show() 
    {
        return "W";
    }
}
