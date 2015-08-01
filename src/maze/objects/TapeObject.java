package maze.objects;

/**
 * Vytvori objekty(stena,..) na zakladade predaneho parametru
 * @author Lubos Tichy
 */
public abstract class TapeObject {
    /**typ objektu ktory sa ma vytvorit*/
    protected String name;
    
    public abstract boolean canBeOpen();
    /**
     * Vytvori objekt na zaklade typ
     * @param format typ objektu
     * @return objekt
     */
    public static TapeObject create(String format) {
        if (format.equals("w")) {            
            Wall obj = new Wall();
            obj.name = "w";
            return obj;
        }
        if (format.equals("g")) {
            Gate obj = new Gate();
            obj.name = "g";
            return obj;
        }
        if (format.equals("k")) {
            Key obj = new Key();
            obj.name = "k";
            return obj;
        }
        if (format.equals("f")) {
            Finish obj = new Finish();
            obj.name = "f";
            return obj;
        }
        
        return null;
    }
   
    public abstract String show();
    
    public abstract boolean canSeize();
    
    public boolean open() {
        if (this instanceof Gate) {
            return this.canBeOpen();
        }
        return false;
    }
    
}
