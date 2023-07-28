
import java.util.*;

public class Surucu {
    public static void main(String[] args) {

        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();



        tree.addOrdered(6);
        tree.addOrdered(4);
        tree.addOrdered(5);
        tree.addOrdered(7);
        tree.addOrdered(3);
        tree.addOrdered(9);
        tree.addOrdered(8);
        tree.addOrdered(11);
        tree.addOrdered(0);
        tree.addOrdered(32);
        tree.addOrdered(10);
        tree.addOrdered(2
        );
        tree.addOrdered(27);
        tree.addOrdered(20);



        for(Position<Integer> pos: tree.diagonalTraversal() ){

            System.out.print(pos.getElement() + " ");
        }




    }


}