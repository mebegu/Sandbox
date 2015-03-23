package utility;

import java.io.FileNotFoundException;
import java.util.ArrayList;



class HashList <K>{
	
	
	private HashListNode<K>[] memo;
	private int size;

	@SuppressWarnings("unchecked")
	protected HashList() {
			try {
				size = Integer.parseInt(FileIO.getInstance().read(FileIO.hashTableSize)[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}


		memo = new HashListNode[size];
	}
	
	private int hash(int key){
		return key % memo.length;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected  void put(int key, K value){
		int hashKey = hash(key);
		int count = 0;
		
		while(count < memo.length && memo[hashKey] != null) {
			hashKey++;
			count++;
			hashKey = hash(hashKey);
		}
		
		if(count < memo.length){
			memo[hashKey] = new HashListNode(key, value);
		}
		else
			throw new RuntimeException("Hash list is full.");
		
	}
	
	protected K getValue(int key){
		int hashKey = hash(key);
		int count = 0;
		
		while(count < memo.length && memo[hashKey].getKey() != key) {
			hashKey++;
			count++;
			hashKey = hash(hashKey)%20;
		}
		
		if(count < memo.length){
			return memo[hashKey].getValue();
		}
		else
			return null;
	}

	protected ArrayList<K> toArrayList() {
		ArrayList<K> result = new ArrayList<K>();
		for(int i=0; i<memo.length; i++)
			if(memo[i] != null)
				result.add(memo[i].getValue());
		
		for(int i=0; i<result.size(); i++)
				System.out.println(result.get(i));
		return result;
	}
	
	protected  void delete(int key){
		int hashKey = hash(key);
		int count = 0;
		
		while(count < memo.length && memo[hashKey].getKey() != key) {
			hashKey++;
			count++;
			hashKey = hash(hashKey);
		}
		
		if(count < memo.length){
			memo[hashKey] = null;
		}
		
	}
	

}
