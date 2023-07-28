
import java.util.LinkedList;



	


class HashNode<K, V> 
{ 
    K key; 
    V value; 

    HashNode<K, V> next; 

    public HashNode(K key, V value) 
    { 
        this.key = key; 
        this.value = value; 
    } 
} 

public class hashTable<K, V> 
{ 
	
	
	public static void main(String args[]) {
		
        hashTable<Integer, String> hashtable = new hashTable<>();
        
        hashtable.add(2015, "Selim");
        hashtable.add(2040, "Ahmet");
        hashtable.add(3045, "Mehmet");
        hashtable.add(4006, "Ayþe");
        hashtable.add(5123, "Fatma");
        hashtable.add(6104, "Ali");

        //Prints the HashTable
        System.out.println(hashtable.toString());
        System.out.println();
        System.out.println("-------------");
        System.out.println();
        System.out.println("Size: " + hashtable.size());
        System.out.println();
        System.out.println("-------------");
        System.out.println();
        System.out.println("Value for key 2015: " + hashtable.get(2015));
        System.out.println();
        System.out.println("-------------");
        System.out.println();
        System.out.println("Remove value for key 2040: " + hashtable.remove(2040));
        System.out.println();
        System.out.println(hashtable.toString());
        System.out.println();
        System.out.println("-------------");
        System.out.println();
        System.out.println("Contains key 2015: " + hashtable.containsKey(2015));
        System.out.println("Contains key 9999: " + hashtable.containsKey(9999));
        System.out.println();
        System.out.println("-------------");
        System.out.println();
        System.out.println("Contains value 'Ali': " + hashtable.contains("Ali"));
        System.out.println("Contains value 'XYZ': " + hashtable.contains("XYZ"));
        System.out.println();
        System.out.println("-------------");
        System.out.println();
	}
	
	
    private LinkedList<HashNode<K, V>> bucketArray; 
    private int numBuckets; 
    private int size; 

    public hashTable() 
    { 
        bucketArray = new LinkedList<>(); 
        numBuckets = 11; 
        size = 0; 

        for (int i = 0; i < numBuckets; i++) 
            bucketArray.add(null); 
    } 

    public int size() { return size; } 

    public boolean isEmpty() { return size() == 0; } 
    

    private int getBucketIndex(K key) 
    { 
        int hashCode = hashCode(key); 
        int index = hashCode % numBuckets; 
        return index; 
    } 

    
    public V remove(K key) 
    { 
        int bucketIndex = getBucketIndex(key); 
        HashNode<K, V> head = bucketArray.get(bucketIndex); 
        HashNode<K, V> prev = null; 
        while (head != null) 
        { 
            if (head.key.equals(key)) 
                break; 
            prev = head; 
            head = head.next; 
        } 

        if (head == null) 
            return null; 

        size--; 

        if (prev != null) 
            prev.next = head.next; 
        else
            bucketArray.set(bucketIndex, head.next); 

        return head.value; 
    } 

    public V get(K key) 
    { 
        int bucketIndex = getBucketIndex(key); 
        HashNode<K, V> head = bucketArray.get(bucketIndex); 

        while (head != null) 
        { 
            if (head.key.equals(key)) 
                return head.value; 
            head = head.next; 
        } 
        return null; 
    } 

    public void add(K key, V value) 
    { 
        int bucketIndex = getBucketIndex(key); 
        HashNode<K, V> head = bucketArray.get(bucketIndex); 

        while (head != null) 
        { 
            if (head.key.equals(key)) 
            { 
                head.value = value; 
                return; 
            } 
            head = head.next; 
        } 

        size++; 
        head = bucketArray.get(bucketIndex); 
        HashNode<K, V> newNode = new HashNode<K, V>(key, value); 
        newNode.next = head; 
        bucketArray.set(bucketIndex, newNode); 

        if ((1.0*size)/numBuckets >= 0.6) 
        { 
            LinkedList<HashNode<K, V>> temp = bucketArray; 
            bucketArray = new LinkedList<>(); 
            numBuckets = 2 * numBuckets; 
            size = 0; 
            for (int i = 0; i < numBuckets; i++) 
                bucketArray.add(null); 

            for (HashNode<K, V> headNode : temp) 
            { 
                while (headNode != null) 
                { 
                    add(headNode.key, headNode.value); 
                    headNode = headNode.next; 
                } 
            } 
        } 
    } 

    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }

        return false;
    }

    public boolean contains(V value) {
        for (int i = 0; i < bucketArray.size(); i++) {
            HashNode<K, V> head = bucketArray.get(i);

            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }

        return false;
    }

    
    /*Bu metot, her bir "bucket" ve içerisindeki her bir "node" (düðüm) için bir döngü kullanarak
     *  string bir çýktý oluþturur. Bu çýktýyý okurken, bir bucket'daki birden fazla deðerin olmasý durumunu
     *   bir çakýþma olarak anlamalýyýz. Bu, çakýþma çözme mekanizmamýzýn (ayrý zincirleme) uygulandýðý yerdir.
     *    Her bir çakýþan anahtar-deðer çifti, bucket içindeki baðlantýlý liste üzerinde ilerleyen bir düðüm olarak saklanýr.*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bucketArray.size(); i++) {
            sb.append("\nBucket ").append(i).append(" :\n");
            HashNode<K,V> entry = bucketArray.get(i);
            while(entry != null) {
                sb.append("[Key: ").append(entry.key.toString()).append(", Value: ").append(entry.value.toString()).append("]");
                if(entry.next != null) {
                    sb.append(" -> ");
                }
                entry = entry.next;
            }
        }
        return sb.toString();
    }

    
    private int hashCode(K key) 
    { 
        String keyString = String.valueOf(key);
        int hashValue = 0;
        int length = keyString.length();
        for(int i = 0; i < length; i++) {
            int digit = Character.getNumericValue(keyString.charAt(i));
            hashValue += digit * (length - i);
        }
        return hashValue;
    }

} 

