package maze.objects;

public class Place extends TapeObject {

	@Override
	public boolean canBeOpen() {
		return false;
	}

	@Override
	public String show() {
		return EObject.PLACE.getSymbol().toUpperCase();
	}

	@Override
	public boolean canSeize() {
		return true;
	}

	@Override
	public boolean open() {
		return false;
	}

}
