package maze.objects;

/**
 * Predstavuje cieľové políčko.
 * 
 * @author Ľuboš Tichý
 */
public class Finish extends TapeObject {

	/**
	 * Testuje, že cieľ nie je možné otvoriť kľúčom.
	 * 
	 * @return false
	 */
	@Override
	public boolean canBeOpen() {
		return false;
	}

	/**
	 * Cieľ je možné obsadiť.
	 * 
	 * @return true
	 */
	@Override
	public boolean canSeize() {
		return true;
	}

	/**
	 * Cieľ nie je možné otvoriť kľúčom.
	 * 
	 * @return false
	 */
	@Override
	public boolean open() {
		return false;
	}

	/**
	 * Vypíše na obrazovku symbol cieľového políčka.
	 * 
	 * @return F
	 */
	@Override
	public String show() {
		return EObject.FINISH.getSymbol().toUpperCase();
	}
}
