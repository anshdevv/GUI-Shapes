package Drawing_shape;

public class Stack {
    Node head=null;

    public void push(Shape data){
        Node node=new Node(data);
        if(head==null){
            head=node;
        }else {
            Node temp=new Node(data);
            temp.next=head;
            head=temp;
        }
    }
    public Shape pop(){
        Shape shape;

        if(head==null){
            return null;
        }else{
          shape=head.shape;
          head=head.next;
        }
        return shape;
    }


}
