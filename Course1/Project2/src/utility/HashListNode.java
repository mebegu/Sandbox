package utility;


class HashListNode<K> {
	private int key;
	private K value;

	protected HashListNode(int key, K value) {
		this.key = key;
		this.value = value;
	}

	protected void setKey(int key){
		this.key = key;
	}
	
	protected void setValue(K value){
		this.value = value;
	}

	protected int getKey() {
		return key;
	}

	protected K getValue() {
		return value;
	}

}
