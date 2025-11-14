package Basic;

import Basic.CRUD_Node;

import java.util.LinkedList;
import java.util.Scanner;

public class CRUD
{
    public static void main(String[] args) {
        LinkedList<CRUD_Node> list=new LinkedList<>();
        Scanner in=new Scanner(System.in);
        //insert
        String name;String gender;int eid;int salary;
        for(int i=0;i<2;i++) {
            System.out.println("\nEnter name,gender,id,salary for record "+(i+1));
            name = in.next();
            gender = in.next();
            eid = in.nextInt();
            salary = in.nextInt();
            CRUD_Node n = new CRUD_Node( name,  eid,  salary,  gender);
            list.add(n);
        }
        //print series
        for(CRUD_Node i:list)
        {
            i.print();
        }

        //search for id
        System.out.println("Enter id to search:");
        int id=in.nextInt();
        boolean found=false;
        for(CRUD_Node i:list)
        {
            if(i.eid==id)
            {
                System.out.println("Iyla found");
                i.print();
                found=true;
                break;
            }
        }
        if(!found)
            System.out.println("Not Found");






        System.out.println("Enter id to update:");
        int hid = in.nextInt();
        boolean updated = false;

        for (CRUD_Node i : list) {
            if (i.eid == hid) {
                System.out.println("Employee found. Current details:");
                i.print();

                System.out.println("Enter new name:");
                i.ename = in.next();

                System.out.println("Enter new gender:");
                i.gender = in.next();

                System.out.println("Enter new salary:");
                i.salary = in.nextInt();

                System.out.println("Record updated ");
                i.print();

                updated = true;
                break;
            }
        }

        if (!updated)
            System.out.println("Record not found for update.");
    }
}






