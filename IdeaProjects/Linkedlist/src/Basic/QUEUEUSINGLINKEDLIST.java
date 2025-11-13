package Basic;


import java.util.Scanner;

public class QUEUEUSINGLINKEDLIST
{
    Node front,rear;

    void enqueue(int data)
    {
        Node n = new Node(data);//created node
        if (rear == null)//no root
        {
            front=rear=n;//both on one
        }
        else {
            rear.next=n;//1 connect to last
            rear=n;//2 move rear
        }
    }
    void dequeue() {
        if (front == null)//no root
            System.out.println("Queue is empty");
        else
        {
            Node t=front;
            if(front==rear)//single node
                front=rear=null;//manual reset
            else
                front=front.next;//move ahead
            System.out.println("Deleted:" + t.data);//3 response message of deletion
        }
    }
    void print_queue() {
        if (front == null)//no root
            System.out.println("Queue is empty");
        else {
            Node t = front;//1
            while (t != null) {
                System.out.print("-|" + t.data + "|_");
                t = t.next;
            }
        }
    }
    public static void main(String[] args)
    {
        QUEUEUSINGLINKEDLIST obj=new QUEUEUSINGLINKEDLIST();
        int choice;
        Scanner in=new Scanner(System.in);

        do
        {
            System.out.println("\nQueue Menu");
            System.out.println("==========");
            System.out.println("1.Enqueue");
            System.out.println("2.Dequeue");
            System.out.println("3.Print");
            System.out.println("0.Exit");
            System.out.println("--------");
            System.out.print(":");
            choice=in.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.println("Enter element to enter:");
                    int e=in.nextInt();
                    obj.enqueue(e);
                    System.out.println("Element Enqueued");
                    break;
                case 2:
                    obj.dequeue();
                    break;
                case 3:
                    obj.print_queue();
                    break;
                case 0:
                    System.out.println("Thanks for using the code: amar.career");
                    break;
                default:
                    System.out.println("check the option selected.");
                    break;

            }
        }while(choice!=0);
    }
}
