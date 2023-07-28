
public class Driver {
    public static void main(String[] args) {

        Deque u = new Deque<Integer>();


            u.addFirst(32);
            u.addFirst(45);
            System.out.println(u.first());
            u.addLast(200);
            u.addLast(1333);
            System.out.println("addFirst, addLast \n"+u);
            System.out.println("size "+u.size());
            u.addLast(12);
            u.addLast(25);
            u.addLast(22);
            u.addLast(13);
            System.out.println(u.first());
            System.out.println(u.last());
            System.out.println("remove dan önce \n"+u);
            u.removeFirst();
            System.out.println("size "+u.size());
            u.removeLast();
            System.out.println("size "+u.size());
            System.out.println("remove dan sonra \n"+u);
            u.addFirst(111);
            u.addFirst(1);
            u.addLast(777);
            System.out.println(u.first());
            System.out.println(u.last());
            System.out.println(u.last());
            System.out.println(u.size());
            System.out.println(u.isEmpty());
            System.out.println("reverse den önce "+u);
            u.reverse();
            System.out.println("reverse"+u);


       







    }
}
