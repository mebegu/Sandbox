package utility;


class HashListNode<J,K> {
	private J key;
	private K value;

	protected HashListNode(J key, K value) {
		this.key = key;
		this.value = value;
	}

	protected void setKey(J key){
		this.key = key;
	}
	
	protected void setValue(K value){
		this.value = value;
	}

	protected J getKey() {
		return key;
	}

	protected K getValue() {
		return value;
	}

}
