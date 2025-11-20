//PRN:250844520061
//NAME:PRATIK GIRISH RANE
//EMAIL:IRANEPRATIK@GMAIL.COM

//Q1
import java.util.Scanner;

public class BinarySearch {

    public static int search(int a[], int key, int start, int end) {

        if (start <= end) {

            int mid = (start + end) / 2;

            if (a[mid] == key) {
                return mid;
            }

            if (key < a[mid]) {
                return search(a, key, start, mid - 1);
            } else {
                return search(a, key, mid + 1, end);
            }

        } else {
            return -1;
        }
    }

    public static void bubblesort(int a[]) {

        boolean flag;

        for (int i = 0; i < a.length; i++) {

            flag = false;

            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {

                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                    flag = true;
                }
            }

            if (flag == false) {
                break;
            }
        }
    }

    public static void printArray(int a[]) {
        for (int x : a) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int a[] = {14999, 8999, 12999, 19999, 9999, 17999, 7999};

        Scanner sc = new Scanner(System.in);

        System.out.print(" Prices are: ");
        printArray(a);

        bubblesort(a);

        System.out.print("Sorted Price is:   ");
        printArray(a);

        System.out.print("\nEnter price to search: ");
        int key = sc.nextInt();

        int index = search(a, key, 0, a.length - 1);

        if (index != -1)
            System.out.println("Price found at index: " + index);
        else
            System.out.println("Price NOT found.");
    }
}
