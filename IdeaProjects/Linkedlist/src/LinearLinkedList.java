public class LinearLinkedList {
    Node root;

    void insert_left(int data) {
        Node n = new Node(data);
        if (root == null) {
            root = n;
        } else {
            n.next = root;
            root = n;
        }
    }

    void delete_left() {
        if (root == null) {
            System.out.println("empty linkedlist");
        } else {
            Node t = root;
            root = root.next;
            System.out.println("deleted:" + t.data);      //Response msg just to let know its deleted
        }
    }

    void insert_right(int data) {
        Node n = new Node(data);
        if (root == null) {
            root = n;
        } else {
            Node t = root;
            while (t.next != null)
                t = t.next;
            t.next = n;
        }
    } // close insert_right

    void delete_right() {
        //tail method-trailer method
        //make temp available for previous of link

        if (root == null) {
            System.out.println("empty linkedlist");
        } else {

            Node t = root;
            Node t2 = root;

            while (t.next != null) {
                t2 = t;
                t = t.next;

            }


            if (root.next== null) {
                root = null;
            } else {
                t2.next = null;
                System.out.println("Deleted" + t.data);
            }
        }

    }
    void print(){
        if(root==null){
            System.out.println("List Empty");
        }
        else{
            Node t=root;
            while(t!=null){
                System.out.print("-"+t.data+"-");
                t=t.next;
            }
        }

    }
    void search(int key){
        if(root==null){
            System.out.println("list is empty");
        }
        else{
            Node t=root;
            while(t!=null){
                if(key==t.data){
                    System.out.println("Element Found");
                    return;
                }
                t=t.next;
            }
        }
    }

    void delete(int key){
        if(root==null){
            root=null;
        }
        Node t1 = root,t2=root;
        while(t1!=null){
            if(t1.data==key){
                System.out.println("found"+key);
                break;
            }
            t2=t1;
           t1= t1.next;

        } if (t1==null){
            System.out.println("not found"+key);
        }
        else{
            System.out.println("found"+key);
            if(t1==root){
               root=root.next;
            } else if (t1.next==null) {
                t2.next=null;
            }
            else t2.next= t1.next;
            System.out.println(t1.data+"deleted");

        }
    }
    void insert_after(int key, int data)
    {
        if (root == null) {
            System.out.println("List is empty");
        }
        else {
            Node t;
            t = root;                // t = root

            while (t != null) {      // if found, stop.
                if (t.data == key)
                    break;
                t = t.next;
            }

            if (t == null) {         // not found
                System.out.println("\n" + key + " Not Found");
            }
            else {
                System.out.println("\n" + key + " Found");
                Node n = new Node(data);   // created new node

                // insertion part
                n.next = t.next;     // n ref to whoever is next
                t.next = n;          // t ref to n

                System.out.println("\n" + t.data + " deleted.");
            }
        }
    }
    void sort_list()
    {
        if(root==null)
            System.out.println("Empty list");
        else
        {
            for(Node i=root;i.next!=null;i=i.next)//for passes
            {
                for(Node t=root,t2=t.next;t2!=null;t=t.next,t2=t2.next)//sorts
                {
                    if(t.data>t2.data)
                    {
                        int temp=t.data;t.data=t2.data;t2.data=temp;
                    }
                }
            }
        }
        System.out.println("\nSorting done..");
    }

}
