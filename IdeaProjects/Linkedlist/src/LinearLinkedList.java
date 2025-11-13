public class LinearLinkedList {
    Node root;
    void insert_left(int data){
        Node n=new Node(data);
        if(root==null){
            root=n;
        }
        else{
            n.next=root;
            root=n;
        }
    }

    void delete_left(){
        if(root==null){
            System.out.println("empty linkedlist");
        }
        else{
            Node t=root;
            root=root.next;
            System.out.println("deleted:"+t.data);      //Response msg just to let know its deleted
        }
    }
}
