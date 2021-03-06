package maze.objects;

/**
 * Objekty hracej plochy.
 * 
 * @author Ľuboš Tichý
 */
public abstract class TapeObject {

	/** Typ objektu, ktorý sa má vytvoriť. */
	protected String name;

	/** Test, či je možné otvoriť políčko. */
	public abstract boolean canBeOpen();

//    /**
//     * Vytvorí objekt podľa predaného parametru.
//     * @param format typ objektu
//     * @return objekt
//     */
//    public static TapeObject create(String format) 
//    {
//        switch (format) 
//        {
//            case "w":
//            {
//                Wall obj = new Wall();
//                obj.name = "w";
//                return obj;
//            }
//            case "g":
//            {
//                Gate obj = new Gate();
//                obj.name = "g";
//                return obj;
//            }
//            case "k":
//            {
//                Key obj = new Key();
//                obj.name = "k";
//                return obj;
//            }
//            case "f":
//            {
//                Finish obj = new Finish();
//                obj.name = "f";
//                return obj;
//            }
//        }
//        
//        return null;
//    }

	/**
	 * Vypíše symbol objektu.
	 */
	public abstract String show();

	/**
	 * Testuje, či je možné obsadiť políčko.
	 */
	public abstract boolean canSeize();

	/**
	 * Otvorí objekt.
	 * 
	 * @return Výsledok otvorenia.
	 */
	public boolean open() {
		if (this instanceof Gate) {
			return this.canBeOpen();
		}
		return false;
	}

}
