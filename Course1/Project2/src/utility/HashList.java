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
			//System.err.println("MemoLoc: "+this+" =>Key:" +key+" Hash of key: "+hash(key)+" value: "+value+" hashKey: "+hashKey);
			//System.err.println("MemoLocParent: "+this+" => Element: "+hashKey+" MemoLocValue:" +memo[hashKey]+" Value: "+memo[hashKey].getKey());
		}
		else
			throw new RuntimeException("Hash list is full.");
		
	}
	
	protected K getValue(int key){
		int hashKey = hash(key);
		int count = 0;
		while(count < memo.length && memo[hashKey] != null && memo[hashKey].getKey() != key) {
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

	protected ArrayList<K> toArrayList() {
		ArrayList<K> result = new ArrayList<K>();
		for(int i=0; i<memo.length; i++)
			if(memo[i] != null && memo[i].getValue() != null)
				result.add(memo[i].getValue());
		return result;
	}
	
	protected  void delete(int key){
		int hashKey = hash(key);
		int count = 0;
		
		while(count < memo.length && memo[hashKey] != null && memo[hashKey].getKey() != key) {
			hashKey++;
			count++;
			hashKey = hash(hashKey);
		}
		//System.err.println("Key: "+key+" Hash of key: "+hash(key)+" hashKey: "+hashKey);
		if(count < memo.length && memo[hashKey] != null){
			memo[hashKey] = new HashListNode<K>(-1, null);
		}
		//System.err.println("after: "+memo[hashKey].getValue());
		
	}

	@SuppressWarnings("rawtypes")
	public void printStructure() {
		for(int i=0; i<memo.length; i++){
			if(memo[i] != null)
				System.out.println("Element: "+i+" =>"+memo[i]+" | Key: "+memo[i].getKey());
			else
				System.out.println("Element: "+i+" =>"+memo[i]);
			
			if(memo[i] != null && memo[i].getValue() instanceof HashList){
				System.err.println("Printing Child's structure: ");
				((HashList)memo[i].getValue()).printStructure();
				System.err.println("^^^^^^^^^^^^^^");
			}
		}
		
	}
	

}
