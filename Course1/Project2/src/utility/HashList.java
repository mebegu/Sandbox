package utility;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;



/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 *
 * 
 */
class HashList <J,K>{


	/**
	 * HashListNode array that contain hash list information.
	 */
	private HashListNode<J,K>[] memo;

	/**
	 * Capacity of Hash List.
	 */
	private int capacity;
	/**
	 * Number of elements that is contained by Hash List.
	 */
	private int size;


	/**
	 * Constructs a Hash List.
	 * Capacity will be read from HashTableSize File from directory.
	 */

	@SuppressWarnings("unchecked")
	protected HashList() {
		try {
			capacity = Integer.parseInt(FileIO.getInstance().read(FileIO.hashTableSize)[0]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		memo = new HashListNode[capacity];
	}

	/**
	 * Takes a integer key, then hash it.
	 * @param key
	 * @return Hashed key.
	 */
	private int hash(int key){
		return key % memo.length;
	}


	/**
	 * <br> Finds a proper location for associated Key, Value pair.
	 * <br> Creates a HashListNode with given Key, Value pair.
	 * <br> Puts the created node to proper location.
	 * <br> If Hash List full throws a runtime exception.
	 * @param key
	 * @param value
	 */
	protected  void put(J key, K value){
		int hashKey = hash(key.hashCode());
		int count = 0;

		while(count < memo.length && memo[hashKey] != null) {
			hashKey++;
			count++;
			hashKey = hash(hashKey);
		}

		if(count < memo.length){
			memo[hashKey] = new HashListNode<J,K>(key, value);
			size++;
		}
		else
			throw new RuntimeException("Hash list is full.");

	}

	/**
	 * <br>Searches for key
	 * <br>Returns associated value of the key.
	 * <br>If the key does not exists in Hash List, returns null.
	 * 
	 * @param key
	 * @return value
	 */
	protected K getValue(J key){

		int hashKey = hash(key.hashCode());
		int count = 0;
		while(count < memo.length && memo[hashKey] != null && memo[hashKey].getKey() != null && memo[hashKey].getKey().hashCode() != key.hashCode()) {
			hashKey++;
			count++;
			hashKey = hash(hashKey)%20;

		}

		if(count < memo.length && memo[hashKey] != null && memo[hashKey].getValue() != null){
			return memo[hashKey].getValue();
		}
		else
			return null;
	}

	/**
	 * <br>Searches for key
	 * <br>Replaces the node with selected key with a new node that has key and value as nulls.
	 * <br>If the key does not exists in Hash List, returns null.
	 * @param key
	 */
	protected  void remove(J key){

		int hashKey = hash(key.hashCode());
		int count = 0;

		while(count < memo.length && memo[hashKey] != null && memo[hashKey].getKey() != null && memo[hashKey].getKey().hashCode() != key.hashCode()) {
			hashKey++;
			count++;
			hashKey = hash(hashKey);
		}
		
		if(count < memo.length && memo[hashKey] != null){
			memo[hashKey] = new HashListNode<J,K>(null, null);
			size--;
		}

	}

	
	/**
	 * @return Containing key set as Iterator.
	 */
	protected Iterator<J> keySet(){

		return new Iterator<J>() {
			private int curr = 0;

			@Override
			public boolean hasNext() {
				int count = curr;
				while(count < memo.length && (memo[count] == null || memo[count].getValue() == null))
					count++;

				if(count == memo.length)
					return false;
				else
					return true;
			}

			@Override
			public J next() {
				while(curr <= memo.length && (memo[curr] == null || memo[curr].getValue() == null))
					curr++;

				if(curr == memo.length)
					throw new NoSuchElementException();	
				else
					return memo[curr++].getKey();				
			}


		};
	}


	/**
	 * @return Containing values as Iterator.
	 */
	protected Iterator<K> values(){

		return new Iterator<K>() {
			private int curr = 0;

			@Override
			public boolean hasNext() {
				int count = curr;
				while(count < memo.length && (memo[count] == null || memo[count].getValue() == null))
					count++;

				if(count == memo.length)
					return false;
				else
					return true;

			}

			@Override
			public K next() {
				while(curr <= memo.length && (memo[curr] == null || memo[curr].getValue() == null))
					curr++;

				if(curr == memo.length)
					throw new NoSuchElementException();	
				else
					return memo[curr++].getValue();
			}
		};
	}

	
	/**
	 * @return Containing entry set set as Iterator.
	 */
	protected Iterator<HashListNode<J,K>> entrySet(){

		return new Iterator<HashListNode<J,K>>() {
			private int curr = 0;

			@Override
			public boolean hasNext() {
				int count = curr;
				while(count < memo.length && (memo[count] == null || memo[count].getValue() == null))
					count++;

				if(count == memo.length)
					return false;
				else
					return true;
			}

			@Override
			public HashListNode<J,K> next() {
				while(curr <= memo.length && (memo[curr] == null || memo[curr].getValue() == null))
					curr++;

				if(curr == memo.length)
					throw new NoSuchElementException();	
				else
					return memo[curr++];
			}
		};
	}


	/**
	 * @return Number of elements that is contained by Hash List.
	 */
	protected int size(){
		return size;
	}

	/**
	 * @return True, if Hash List is empty; Otherwise, returns false
	 */
	protected boolean isEmpty(){
		return size==0;
	}

	/**
	 * Prints the structure of Hash List to console.
	 */
	@SuppressWarnings("rawtypes")
	public void printStructure() {
		for(int i=0; i<memo.length; i++){
			if(memo[i] != null)
				System.out.println("Element: "+i+" =>"+memo[i]+" | Key: "+memo[i].getKey());
			else
				System.out.println("Element: "+i+" =>"+memo[i]);

			if(memo[i] != null && memo[i].getValue() instanceof HashList){
				System.out.println("Printing Child's structure: ");
				((HashList)memo[i].getValue()).printStructure();
				System.err.println("^^^^^^^^^^^^^^");
			}
		}

	}


}
