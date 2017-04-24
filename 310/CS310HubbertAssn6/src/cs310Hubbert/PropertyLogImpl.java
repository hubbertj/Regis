package cs310Hubbert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * @author Jerum Hubbert
 *
 */
public class PropertyLogImpl {
	
	private LinkedList<MapEntry<String, Property>>[] hashTable;
	private HashMap<String, Property> propertyHashMap = new HashMap<String, Property>();
	private TreeMap<String, Property> treeMap;
	private int numKey;
	private final int START_CAPACITY = 17;

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public PropertyLogImpl() {
		super();
		this.hashTable = new LinkedList[this.START_CAPACITY];
		this.treeMap = new TreeMap<String, Property>();
	}

	/**
	 * Gets the hash map which holds all the properties
	 * 
	 * @return HashMap Holds all the properties
	 */
	public HashMap<String, Property> getPropertyHashMap() {
		return propertyHashMap;
	}

	/**
	 * Gets the current size of the hash table
	 * 
	 * @return int current size of the hash table
	 */
	public int getNumKey() {
		return numKey;
	}

	/**
	 * Gets the capacity of the hash table.
	 * 
	 * @return int Max capacity of the hash table.
	 */
	public int getSTART_CAPACITY() {
		return START_CAPACITY;
	}

	private Property insert(int hashCode, MapEntry<String, Property> entry) {
		int index = hashCode;

		if (index < 0 || index > this.hashTable.length) {
			index += this.hashTable.length;
			hashCode = this.hashTable.length;
		}

		if (this.hashTable[index] == null) {
			this.hashTable[index] = new LinkedList<MapEntry<String, Property>>();
		}

		for (MapEntry<String, Property> nextItem : this.hashTable[index]) {
			if (nextItem.key.equals(entry.key)) {
				Property oldVal = nextItem.value;
				nextItem.value = entry.value;
				return oldVal;
			}
		}

		this.hashTable[index].addFirst(entry);
		this.numKey++;
		return null;
	}

	private int getHashCode(String mls) {
		return new Integer(mls) % this.START_CAPACITY;
	}

	/**
	 * Adds a property to the log.
	 * 
	 * 
	 * @param property
	 *            Property a simple property object
	 * 
	 * @return Property If a property was updated it returns its old property
	 *         values if we inserted a new property this returns null
	 */
	public Property add(Property property) {
		return this.treeMap.put(property.getMlsNum(), property);
	}

	/**
	 * Finds a property in the log using a MLS number
	 * 
	 * @param mls
	 *            String a number which is unique to a property
	 * @return Property if found returns the property is not returns null
	 */
	public Property find(String mls) {
		if(mls == null){
			return null;
		}

		return this.treeMap.get(mls);
	}

	/**
	 * Displays a message of all the Properties in the hash table and at which
	 * index
	 */
	public void displayHash() {
		int index = 0;
		System.out.println("Property Map:");
		for (LinkedList<MapEntry<String, Property>> linkList : this.hashTable) {
			if (linkList == null) {
				System.out.println("\tIndex " + index + " is empty");
			} else {
				ListIterator<MapEntry<String, Property>> listIterator = linkList.listIterator();
				Vector<Property> propertyVector = new Vector<Property>();

				while (listIterator.hasNext()) {
					propertyVector.add(listIterator.next().value);
				}

				System.out.print("\tIndex " + index + " contains Properties: ");

				for (Property property : propertyVector) {
					System.out.print(property.getMlsNum() + " ");
				}

				System.out.print("\n");
			}

			index++;
		}
	}
	
	/**
	 *  Displays all data in the tree map.
	 */
	public void traverseDisplay() {
		System.out.println("Property List:");
		for (Map.Entry<String, Property> entry : this.treeMap.entrySet()) {
		     System.out.println(entry.getValue().toString());
		}
	}

	/**
	 * MapEntry is a inner class to implement the hash map
	 * 
	 * @author Jay
	 *
	 */
	public class MapEntry<K, V> {
		public K key;
		public V value;

		/**
		 * Constructor sets the key and value
		 * 
		 * @param key
		 *            The unique identifier for the value
		 * @param value
		 *            The stored body of this object
		 */
		public MapEntry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
}
