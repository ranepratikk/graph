class LString{
//public static int binary_search(int a[],int start,int end)
//{
//    if(start<=end)
//    {
//        int mid=(start+end)/2;
//        int key = 0;
//        if(a[mid]==key)
//            return mid;
//        else
//        {
//            if(key<a[mid])
//                return binary_search(a,start,mid-1);
//            else
//                return binary_search(a,mid+1,end);
//        }
//    }
//    else
//    {
//        return (-1);
//    }
//}


public static int[] optimized_bubble_sort(int[] a) {
    boolean done = true;
    for (int i = a.length - 1; i > 0; i--)//limit passes
    {
        done = true;
        for (int j = 0; j < i; j++)//j stops at second last
        {
            if (a[j] > a[j + 1]) {
                int t = a[j];
                a[j] = a[j + 1];
                a[j + 1] = t;
                done = false;
            }
        }
        if (done == true)
            break;
    }
return a;
}}