package cs310Hubbert;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jerum Hubbert
 *
 */
public class PropertyLogImpl {
	private TreeMap<String, Property> treeMap;
	private int numKey;

	/**
	 * Constructor
	 */
	public PropertyLogImpl() {
		super();
		this.treeMap = new TreeMap<String, Property>();
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
		Property pProperty = this.treeMap.put(property.getMlsNum(), property);
		if (pProperty == null) {
			this.numKey++;
		}
		return pProperty;
	}

	/**
	 * Finds a property in the log using a MLS number
	 * 
	 * @param mls
	 *            String a number which is unique to a property
	 * @return Property if found returns the property is not returns null
	 */
	public Property find(String mls) {
		if (mls == null) {
			return null;
		}

		return this.treeMap.get(mls);
	}

	/**
	 * Displays all data in the tree map.
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
