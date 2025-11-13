package Basic;

import java.util.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
     LinearLinkedList ll=new LinearLinkedList();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== Linkedlist ======");
            System.out.println("1. Insert Right");
            System.out.println("2. Insert Left");
            System.out.println("3. Delete Left");
            System.out.println("4. Delete Right");
            System.out.println("5. Print ");
            System.out.println("6. Search ");
            System.out.println("7. Delete ");
            System.out.println("8. Insert After ");
            System.out.println("9. Sort ");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value ");
                    int val = sc.nextInt();
                     ll.insert_left(val);
                    System.out.println(val+":value inserted");
                    break;

                case 2:
                    System.out.print("Enter value ");
                    int hal = sc.nextInt();
                    ll.insert_left(hal);
                    System.out.println(hal+":value inserted");
                    break;


                case 3:
                    ll.delete_left();


                    break;

                case 4:
                    ll.delete_right();


                    break;

                case 5:
                    ll.print();
                    break;
                case 6:
                    int h=sc.nextInt();
                    ll.search(h);
                    break;
                    case 7:
                        int val1= sc.nextInt();
                        ll.search(val1);
                        break;
                case 8:
                    System.out.println("Insert data");
                    int val2= sc.nextInt();
                    System.out.println("Insert key");

                    int key=sc.nextInt();
                    ll.insert_after(key,val2);
                    break;
                case 9:

                    ll.sort_list();
                    break;




                default:
                    System.out.println("Check option again ");
            }
        } while (choice != 0);

        sc.close();
    }
}

