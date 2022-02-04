/**
 * 
 */
package csc3a.miniproject.Models;

import java.util.Iterator;

/**
 * @author MG Sumba
 *
 */
public class ListIterator<T extends Comparable<T>> implements Iterator<T> {

	private Node<T> current;
	
	
	/**
	 * @param current
	 */
	public ListIterator(Node<T> current) {
		this.current = current;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		 return current != null;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		 if(hasNext()) {
             T item = current.getElement();
             current = current.getNext();
             return item;
         }
         return null;
	}

}
