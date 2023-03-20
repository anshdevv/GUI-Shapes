package Drawing_shape;

public class Queue {
    public static Node head;
    public static Node tail;
public void enqueue(Shape shape) {
    if (head == null) {
        head = new Node(shape);
        tail = head;
    } else {
        Node current = new Node(shape);
        tail.next = current;
        tail = current;
    }
}
    public Shape dequeue(){
        if(head==null){
            return null;

        }else{
            Shape temp=head.shape;
            head=head.next;
            return temp;
        }
    }


}