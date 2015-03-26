package utility;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;



class HashList <J,K>{
	
	
	private HashListNode<J,K>[] memo;
	private int capacity;
	private int size;

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
	
	private int hash(int key){
		return key % memo.length;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected  void put(J key, K value){
		int hashKey = hash(key.hashCode());
		int count = 0;
		
		while(count < memo.length && memo[hashKey] != null) {
			hashKey++;
			count++;
			hashKey = hash(hashKey);
		}
		
		if(count < memo.length){
			memo[hashKey] = new HashListNode(key, value);
			size++;
			//System.err.println("MemoLoc: "+this+" =>Key:" +key+" Hash of key: "+hash(key)+" value: "+value+" hashKey: "+hashKey);
			//System.err.println("MemoLocParent: "+this+" => Element: "+hashKey+" MemoLocValue:" +memo[hashKey]+" Value: "+memo[hashKey].getKey());
		}
		else
			throw new RuntimeException("Hash list is full.");
		
	}
	
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
	
	protected  void remove(J key){
		
		int hashKey = hash(key.hashCode());
		int count = 0;
		
		while(count < memo.length && memo[hashKey] != null && memo[hashKey].getKey() != null && memo[hashKey].getKey().hashCode() != key.hashCode()) {
			hashKey++;
			count++;
			hashKey = hash(hashKey);
		}
		//System.err.println("Key: "+key+" Hash of key: "+hash(key)+" hashKey: "+hashKey);
		if(count < memo.length && memo[hashKey] != null){
			memo[hashKey] = new HashListNode<J,K>(null, null);
			size--;
		}
		//System.err.println("after: "+memo[hashKey].getValue());
		
	}
	
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

	
	protected int size(){
		return size;
	}
	
	protected boolean isEmpty(){
		return size==0;
	}

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
