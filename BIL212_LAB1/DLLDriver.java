
public class DLLDriver {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.addLast(2);
        dll.addLast(12);
        dll.addLast(22);
        dll.addLast(32);
        dll.addLast(2);
        dll.addLast(10);
        dll.addLast(3);
        dll.addLast(1);
        dll.addLast(11);
        dll.addLast(1);
        dll.addLast(2);
        dll.addLast(2);
        dll.addLast(20);

        System.out.println(dll);
        System.out.println();
        System.out.println("contains metodu:");
        System.out.println(dll.contains(22));
        System.out.println(dll.contains(99));
        System.out.println();
        System.out.println("nContains metodu:");
        System.out.println(dll.nContains(22));
        System.out.println(dll.nContains(2));
        System.out.println();
        System.out.println("removeThis metodu:");
        System.out.println(dll.removeThis(2));
        System.out.println(dll.removeThis(32));
        System.out.println(dll.removeThis(12));
        System.out.println(dll);
        System.out.println("removeThese metodu:");
        System.out.println(dll.removeThese(2));
        System.out.println(dll);
        System.out.println("reverseFirstK metodu:");
        dll.reverseFirstK(20);
        System.out.println(dll);
    }
}