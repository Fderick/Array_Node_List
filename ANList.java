package problem6_37;

import java.util.Iterator;

public class ANList<T> implements ListInterface<T> {

	protected final int DEFCAP = 100;
	protected ArrayNode<T>[] elements;
	protected int first = ArrayNode.NUL;
	protected int free = 0;
	protected int numElements = 0;

	protected int location = first;
	protected int previous = ArrayNode.NUL;
	protected boolean found;

	public ANList() {
		// elements = (ArrayNode<T>[]) new Object[DEFCAP];
		elements = new ArrayNode[DEFCAP];
		for (int i = 0; i < DEFCAP; i++) {
			ArrayNode<T> node = new ArrayNode<T>(null);
			elements[i] = node;
		}
		for (int i = 0; i < DEFCAP; i++) {
			elements[i].setIndex(i + 1);
		}
	}

	public ANList(int capacity) {
		// elements = (ArrayNode<T>[]) new Object[capacity];
		elements = new ArrayNode[capacity];
		for (int i = 0; i < capacity; i++) {
			ArrayNode<T> node = new ArrayNode<T>(null);
			elements[i] = node;
		}
		for (int i = 0; i < capacity; i++) {
			elements[i].setIndex(i + 1);
		}
	}

	public ArrayNode<T> getNode() {
		return elements[first];
	}

	@Override
	public boolean add(T element) {
		// Cases, Array Empty, Array Full
		if (isEmpty()) {
			elements[free].setInfo(element);
			first = free;
			free = elements[free].getIndex();
			elements[first].setIndex(ArrayNode.NUL);
			numElements++;
			return true;

		} else if (numElements == elements.length) {
			// Java Default Exception
			throw new ArrayStoreException("Array is Full");

		} else {
			// Add Elements to first
			elements[free].setInfo(element);
			int newIndex = free;
			free = elements[free].getIndex();
			int listEnd = first;
			while (elements[listEnd].getIndex() != ArrayNode.NUL) {
				listEnd = elements[listEnd].getIndex();
			}
			elements[listEnd].setIndex(newIndex);
			elements[newIndex].setIndex(ArrayNode.NUL);
			numElements++;
			return true;
		}
	}

	protected void find(T target) {

		location = first;
		found = false;

		if (elements[first].getInfo().equals(target)) {
			found = true;
			return;
		}

		while (elements[location].getIndex() != ArrayNode.NUL) {
			previous = location;
			location = elements[location].getIndex();
			if (elements[location].getInfo().equals(target)) {
				found = true;
				break;
			}
		}

	}

	@Override
	public T get(T target) {
		find(target);
		if (found) {
			return (T) elements[location].getInfo();
		} else {
			return null;
		}
	}

	@Override
	public boolean contains(T target) {
		find(target);
		return found;
	}

	@Override
	public boolean remove(T target) {
		if (isEmpty()) {
			return false;
		}
		find(target);
		if (found) {
			if (numElements == 1) {
				elements[first].setInfo(null);
				elements[first].setIndex(free);
				free = first;
				first = ArrayNode.NUL;
				numElements--;
				return true;
			} else if (location == first) {
				elements[location].setInfo(null);
				first = elements[location].getIndex();
				elements[location].setIndex(free);
				free = location;
				numElements--;
				return true;
			} 
			elements[previous].setIndex(elements[location].getIndex());
			elements[location].setInfo(null);
			elements[location].setIndex(free);
			free = location;
			numElements--;
			return true;
		}

		return false;
	}

	public boolean isFull() {
		return (numElements == elements.length);
	}

	public boolean isEmpty() {
		return (numElements == 0);
	}

	public int size() {
		return numElements;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int previousPos = -1;

			public boolean hasNext() {
				if (previousPos == -1) {
					return first != ArrayNode.NUL;
				}

				return (elements[previousPos].getIndex() != ArrayNode.NUL);
			}

			public T next() {
				if (!hasNext())
					throw new IndexOutOfBoundsException("Illegal " + "Invocation of Next in ANList Iterator.\n");
				else if (previousPos == -1) {
					previousPos = first;
					return (T) elements[first].getInfo();
				} else {
					previousPos = elements[previousPos].getIndex();
					return (T) elements[previousPos].getInfo();
				}

			}

		};
	}

	@Override
	public void add(int index, T element) {
		// Full List
		if (isFull()) {
			throw new ArrayStoreException("Array Full");
		}
		// Empty List
		if (index == 0 && isEmpty()) {
			elements[free].setInfo(element);
			first = free;
			free = elements[free].getIndex();
			elements[first].setIndex(ArrayNode.NUL);
		}
		// Inexistent index
		else if ((index < 0) || (index > size())) {
			throw new IndexOutOfBoundsException("Index out of bounds in ANList");
			// Index 0
		} else if (index == 0) {
			elements[free].setInfo(element);
			int temp = first;
			first = free;
			free = elements[free].getIndex();
			elements[first].setIndex(temp);

		} else if (index == size()) { // Index is Last element
			add(element);
		} else {// Every other case

			int position = first;
			for (int i = 0; i < index - 1; i++) { // Get to position in "list"
				position = elements[position].getIndex();
			}
			// Insert element in list index.
			int temp = free;
			elements[free].setInfo(element);
			int nextIndex = elements[position].getIndex();
			elements[position].setIndex(free);
			free = elements[free].getIndex();
			elements[temp].setIndex(nextIndex);

		}
		numElements++;

	}

	@Override
	public T set(int index, T newElement) {

		if ((index < 0) || (index >= size())) {
			throw new IndexOutOfBoundsException("Index out of bounds in ANList");
		} else {
			int position = first;
			for (int i = 0; i < index; i++) {
				position = elements[position].getIndex();
			}
			T hold = elements[position].getInfo();
			elements[position].setInfo(newElement);
			return hold;
		}
	}

	@Override
	public T get(int index) {
		if ((index < 0) || (index >= size())) {
			throw new IndexOutOfBoundsException("Index out of bounds in ANList");
		} else {
			int position = first;
			for (int i = 0; i < index; i++) {
				position = elements[position].getIndex();
			}
			T hold = elements[position].getInfo();
			return hold;
		}
	}

	/**
	 * If this list contains an element e such that e.equals(target), then returns
	 * the index of the first such element. Otherwise, returns -1.
	 * 
	 * @param target - element to find.
	 */
	public int indexOf(T target) {
		Iterator<T> iter = iterator();
		int index = 0;
		while (iter.hasNext()) {
			if (iter.next().equals(target))
				return index;
			index++;
		}

		return -1;

	}

	/**
	 * Remove the element at the given index.
	 */
	public T remove(int index) {
		// Throws IndexOutOfBoundsException if passed an index argument
		// such that index < 0 or index >= size().
		// Otherwise, removes element on this list at position index and
		// returns the removed element.
		if ((index < 0) || (index >= size())) {
			throw new IndexOutOfBoundsException("Index out of bounds in ANList");
		} else if (index == 0 && size() == 1) {
			T hold = elements[first].getInfo();
			elements[first].setInfo(null);
			elements[first].setIndex(free);
			free = first;
			first = ArrayNode.NUL;
			numElements--;
			return hold;
		} else if (index == 0) {
			T hold = elements[first].getInfo();
			elements[first].setInfo(null);
			int temp = elements[first].getIndex();
			elements[first].setIndex(free);
			first = temp;
			free = first;
			numElements--;
			return hold;
		} else {
			int position = first;
			int previous = ArrayNode.NUL;
			for (int i = 0; i < index; i++) {
				previous = position;
				position = elements[position].getIndex();
			}
			T hold = elements[position].getInfo();
			elements[previous].setIndex(elements[position].getIndex());
			elements[position].setInfo(null);
			elements[position].setIndex(free);
			free = position;
			numElements--;
			return hold;
		}
	}

	public String toString() {
		String toReturn = "[";
		if (isEmpty()) {
			return "[ ]";
		}
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			toReturn += iter.next() + ", ";
		}
		toReturn = toReturn.substring(0, toReturn.length() - 2) + "]";
		return toReturn;
	}
}
