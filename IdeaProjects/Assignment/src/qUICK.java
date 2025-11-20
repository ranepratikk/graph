//void qUICK(int a[],int start,int end)
//{
//    int i,j,pivot,temp;
//    i=start;
//    pivot=start;
//    j=end;
//    while(i<j)
//    {
//        while(a[i]<a[pivot])
//            i++;
//        while(a[j]>a[pivot])
//            j--;
//        if(i<j)
//        {
//            temp=a[i];
//            a[i]=a[j];
//            a[j]=temp;
//        }
//    }
//    //if pivot in start
//    if(i<=end)
//        qUICK(a,i+1,end);
//    //if pivot in end
//    if(j>start)
//        qUICK(a,start,j-1);
//}
