/**
 * Created by Rafael on 4/22/2016.
 */


class PriorityQueue<Base> {
    private class Node {
        private Base object;
        private int rank;
        private Node left;
        private Node right;

        public Node(Base object, int rank) {
            this.object = object;
            this.rank = rank;
            left = null;
            right = null;
        }
    }

    private Node root;

    public PriorityQueue() {
        root = new Node(null, Integer.MAX_VALUE);
    }

    public boolean isEmpty() {
        return root.left == null && root.right == null;
    }

    public Base dequeue() {
        Base temp;
        if (isEmpty()) {
            throw new IllegalStateException("The queue is empty");
        } else {
            Node above = root;
            Node below = root.left;
            while (true) { // because we force while to always run we only need one return to stop the loop. We could also break, but then we need a return outside the while loop. Lastly if we have a normal while loop we have to return for cases when its done in the loop or when it finishes looping and has to return something
                if (below.left == null) {
                    temp = below.object;
                    above.left = below.right;
                    return temp;
                } else {
                    below = below.left;
                    above = above.left;
                }
            }

        }
    }

    public void enqueue(Base object, int rank) {
        if (rank < 0)
        {
            throw new IllegalArgumentException("can't have negative rank");
        }
        else
        {
            Node temp = root;
            while (true)
            {
                if (rank <= temp.rank)
                {
                    if (temp.left == null)
                    {
                        temp.left = new Node(object, rank);
                        return;
                    }
                    else
                    {
                        temp = temp.left;
                    }
                }
                else if (rank > temp.rank)
                {
                    if (temp.right == null)
                    {
                        temp.right = new Node(object, rank);
                        return;
                    }
                    else
                    {
                        temp = temp.right;
                    }
                }
                /*
                else
                {
                    temp.rank = rank;
                    return;
                }
                */
            }
        }
    }
}


public class Snobbery {
    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<String>();

        System.out.println(queue.isEmpty());  //  true

        queue.enqueue("Fawlty", 7);
        queue.enqueue("Elizabeth", 0);
        queue.enqueue("Charles", 1);
        queue.enqueue("Turing", 7);
        queue.enqueue("Bill", 7);


        System.out.println(queue.dequeue());  //  Elizabeth
        System.out.println(queue.dequeue());  //  Charles
        System.out.println(queue.dequeue());  //  Bill
        System.out.println(queue.dequeue());  //  Turing
        System.out.println(queue.dequeue());  //  Fawlty

        System.out.println(queue.isEmpty());  //  true

    }
}
