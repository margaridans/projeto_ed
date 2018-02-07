package interfaces;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
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
