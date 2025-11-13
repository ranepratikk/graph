//package Basic;
//
//
//import java.util.Scanner;
//
//public class STACKUSINGLINKEDLIST
//{
//    Node tos;//only data member we have
//
//
//    void push(int data)
//    {
//        Node n = new Node(data);//created node
//        if (tos == null)//no root
//            tos = n;//1st becomes root
//        else {
//            n.next = tos;//1
//            tos= n;//2
//        }
//    }
//    void pop() {
//        if (tos == null)//no root
//            System.out.println("Stack is empty");
//        else {
//            Node t = tos;//1
//            tos = tos.next;//2
//            System.out.println("poped:" + t.data);//3 response message of deletion
//        }
//    }
//
//    void print_stack() {
//        if (tos == null)//no root
//            System.out.println("Stack is empty");
//        else {
//            Node t = tos;//1
//            while (t != null) {
//                System.out.println(t.data );
//                System.out.println("======");
//                t = t.next;
//            }
//        }
//    }
//    int  peek() {
//        if (tos == null)//no root
//        {
//            System.out.println("Stack is empty");
//            return (-1);
//        }
//        else {
//            return (tos.data);
//        }
//    }
//    public static void main(String[] args)
//    {
//        STACKUSINGLINKEDLIST obj=new STACKUSINGLINKEDLIST();
//        int choice;
//        Scanner in=new Scanner(System.in);
//        do
//        {
//            System.out.println("\nStack Menu");
//            System.out.println("==========");
//            System.out.println("1.Push");
//            System.out.println("2.Pop");
//            System.out.println("3.Peek");
//            System.out.println("4.Print");
//            System.out.println("0.Exit");
//            System.out.println("--------");
//            System.out.print(":");
//            choice=in.nextInt();
//            switch(choice)
//            {
//                case 1:
//                    System.out.println("Enter element to push:");
//                    int e=in.nextInt();
//                    obj.push(e);
//                    System.out.println("Element pushed");
//                    break;
//                case 2:
//                    obj.pop();
//                    break;
//                case 3:
//                    int res=obj.peek();
//                    if (res!=-1)//if not empty
//                        System.out.println("At peek:"+res);
//                    break;
//                case 4:
//                    obj.print_stack();
//                    break;
//                case 0:
//                    System.out.println("Thanks for using the code: amar.career");
//                    break;
//                default:
//                    System.out.println("check the option selected.");
//                    break;
//
//            }
//        }while(choice!=0);
//    }
//
//}
