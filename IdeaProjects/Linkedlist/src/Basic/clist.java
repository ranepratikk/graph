package Basic;




public class clist {
    Node root,last;//only data member we have

    //insert_left:Research new element to the left of the current node.
    public  void insert_left(int data) {
        Node n = new Node(data);//created node
        if (root == null){//no root
            root=last = n;//1st becomes root
            last.next=root;}
        else {
            n.next = root;//1
            root = n;//2
            last.next=root;
        }
    }

    public   void insert_right(int data) {
        Node n = new Node(data);//created node
        if (root == null) {//no root
            root = last = n;//1st becomes root
            last.next = root;
        }
        else {
           last.next=n;
           last=n;
           last.next=root;
        }
    }

    public  void delete_left() {
        if (root == null)//no root
            System.out.println("List is empty");
        else {
            Node t = root;//1
            root = root.next;//2
            System.out.println("Deleted:" + t.data);//3 response message of deletion
        }
    }

    public   void delete_right() {
        if (root == null)//no root
            System.out.println("List is empty");
        else {
            Node t, t2;
            t = t2 = root;//1
            while (t.next != null)//2
            {
                t2 = t;
                t = t.next;
            }
            if (t == root)//single node
                root = null;//manual deletion
            else
                t2.next = null;//break the link
            System.out.println("Deleted:" + t.data);//3 response message of deletion
        }
    }


    public  void print_list() {
        if (root == null)//no root
            System.out.println("List is empty");
        else {
            Node t = root;//1
            while (t != null) {
                System.out.println("| " + t.data + " |->");
                t = t.next;
            }
        }
    }

    public void search_list(int key) {
        if (root == null)//no root
            System.out.println("List is empty");
        else {
            Node t = root;//1
            while (t != null)//If found, stop.
            {
                if (t.data == key) {
                    System.out.println("\n" + key + " Found");
                    return;
                }
                t = t.next;
            }
            System.out.println("\n" + key + " Not Found");//At end if data is not found, t will be NULL.
        }
    }

    public   void delete_element(int key)//key if found need to be deleted
    {
        if (root == null)//no root
            System.out.println("List is empty");
        else {
            Node t, t2;
            t = t2 = root;//1
            while (t != null)//If found, stop.
            {
                if (t.data == key)
                    break;
                t2=t;
                t = t.next;
            }
            if (t == null)//not found
                System.out.println("\n" + key + " Not Found");
            else
            {
                System.out.println("\n" + key + " Found");
                //deletion part
                if(t==root)//case 1:delete left
                    root=root.next;
                else if(t.next==null)//case 2 : right most: delete right
                    t2.next=null;//deleted
                else //case 3 in-between deletion
                    t2.next=t.next;//redirect to next of t
                System.out.println("\n"+t.data+" deleted.");
            }
        }
    }
    //We'll search for the key element if found.
    //We'll create a new node with given data and insert it after that key node.
    public    void insert_after(int key,int data)
    {
        if (root == null)//no root
            System.out.println("List is empty");
        else {
            Node t;
            t = root;//1
            while (t != null)//If found, stop.
            {
                if (t.data == key)
                    break;
                t = t.next;
            }
            if (t == null)//not found
                System.out.println("\n" + key + " Not Found");
            else
            {
                System.out.println("\n" + key + " Found");
                Node n=new Node(data);//created new node
                //insertion part
                n.next=t.next;//1 ref to who ever is t.next
                t.next=n;//2 t ref to n

                System.out.println("\n"+t.data+" deleted.");
            }
        }
    }

    public  void sort_list()
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
