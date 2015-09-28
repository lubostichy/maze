package maze.objects;

/**
 * Predstavuje bránu.
 * @author Ľuboš Tichý
 */
public class Gate extends maze.objects.TapeObject
{

    /** Informácia o otvorenej bráne. Na začiatku je zatvorená. */
    protected boolean OpenDoor = false;

    /**
     * Testuje, či je možné bránu otvoriť.
     * @return Ak je zavretá tak true, inak false.
     */
    @Override
    public boolean canBeOpen() 
    {
        return !this.OpenDoor;
    }
    
    /**
     * Testuje, či je možné bránu obsadiť.
     * @return Ak je otvorená true inak false.
     */
    @Override
    public boolean canSeize() 
    {
        return (OpenDoor == true);
    }
    
    /**
     * Otvorí bránu.
     * @return Ak je už otvorená false, inak true.
     */
    @Override
    public boolean open() 
    {
        if (this.OpenDoor) 
        {
            return false;
        }     

        this.OpenDoor = true;
        return true;
    }    
    
    /**
     * Vypíše na obrazovku.
     * @return Ak je otvorená tak "O" inak "G".
     */
    @Override
    public String show()
    {
        if (OpenDoor) 
        {
            return "O";
        }
        return "G";
    }
}
