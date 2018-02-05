package interfaces;

/**
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public interface OrderedListADT<T> extends ListADT<T> {

	/**
	* Adds the specified element to this list at
	* the proper location
	*
	* @param element the element to be added to this list
	*/
	public void add(T element);
}
