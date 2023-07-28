public class Driver {
    public static void main(String[] args) {
        // Create a new instance of CircularLinkedPositionalList
        CircularLinkedPositionalList<Integer> list = new CircularLinkedPositionalList<>();

        // Test case 1: Empty list
        System.out.println("Test Case 1: Empty list");
        System.out.println("Size: " + list.size()); // Output: Size: 0
        System.out.println("Is Empty: " + list.isEmpty()); // Output: Is Empty: true
        System.out.println(list); // Output: (empty line)
        
        System.out.println();
        System.out.println();
        System.out.println();

        // Test case 2: Adding elements to the list
        System.out.println("Test Case 2: Adding elements to the list");
        list.addLast(5);
        list.addLast(6);
        list.addLast(3);
        list.addLast(12);
        System.out.println("Size: " + list.size()); // Output: Size: 4
        System.out.println("Is Empty: " + list.isEmpty()); // Output: Is Empty: false
        System.out.println(list); // Output:

        System.out.println();
        System.out.println();
        System.out.println();

        // Test case 3: Adding more elements to the list
        System.out.println("Test Case 3: Adding more elements to the list");
        list.addLast(4);
        list.addLast(8);
        list.addLast(1);
        list.addLast(10);
        list.addLast(7);
        list.addLast(29);
        System.out.println("Size: " + list.size()); // Output: Size: 10
        System.out.println("Is Empty: " + list.isEmpty()); // Output: Is Empty: false
        System.out.println(list); // Output:

        System.out.println();
        System.out.println();
        System.out.println();
        
        // Test case 4: Removing elements from the list
        System.out.println("Test Case 4: Removing elements from the list");
        Position<Integer> firstPos = list.first();
        Position<Integer> lastPos = list.last();
        System.out.println("Removed First: " + list.remove(firstPos)); // Output: Removed First: 6
        System.out.println("Removed Last: " + list.remove(lastPos)); // Output: Removed Last: 29
        System.out.println("Size: " + list.size()); // Output: Size: 8
        System.out.println("Is Empty: " + list.isEmpty()); // Output: Is Empty: false
        System.out.println(list); // Output:

        System.out.println();
        System.out.println();
        System.out.println();

        // Test case 5: Replacing an element in the list
        System.out.println("Test Case 5: Replacing an element in the list");
        Position<Integer> pos = list.after(list.first());
        System.out.println("Replaced Element: " + list.set(pos, 9)); 
        System.out.println("Size: " + list.size()); 
        System.out.println("Is Empty: " + list.isEmpty()); 
        System.out.println(list); // Output:
        
        System.out.println();
        System.out.println();
        System.out.println();
        


        
    }

}

