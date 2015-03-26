package utility;


/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 *
 * 
 */
class HashListNode<J,K> {
	
	/**
	 * The key parameter of Node.
	 */
	private J key;
	
	/**
	 * The value parameter of Node.
	 */
	private K value;

	/**
	 * Constructs a Hash List Node with given parameters.
	 */
	protected HashListNode(J key, K value) {
		setKey(key);
		setValue(value);
	}

	/**
	 * Sets the key parameter of Node.
	 * @param key
	 */
	protected void setKey(J key){
		this.key = key;
	}
	
	/**
	 * Sets the value parameter of Node.
	 * @param value
	 */
	protected void setValue(K value){
		this.value = value;
	}

	/**
	 * @return Key parameter of Node.
	 */
	protected J getKey() {
		return key;
	}

	/**
	 * @return Value paramater of Node.
	 */
	protected K getValue() {
		return value;
	}

}
