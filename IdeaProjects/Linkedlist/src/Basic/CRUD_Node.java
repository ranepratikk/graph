package Basic;

public class CRUD_Node {
    String ename;
    public int eid;
    String gender;
    int salary;
    CRUD_Node next;
    public CRUD_Node(String name, int id, int salary, String gender){
         ename=name;
         eid=id;
         this.salary=salary;
         this.gender=gender;
         next=null;


    }
    void print(){
        System.out.println(ename+"  "+eid+" "+salary+
                " "+gender);
    }
}
