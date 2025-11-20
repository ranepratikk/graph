public class TWOSUM {
    public static int[] twosum(int []arr,int sum){
        for(int i=0;i< arr.length;i++) {
            for(int j=i+1;j< arr.length;j++){
                if (arr[i] + arr[j]==sum) {
                    return new int []{i,j};

                }
            }

        }


        return new int[]{};
    }

    public static void main(String[] args) {
        int []arr={1,3,5,3,5};
        int []h=TWOSUM.twosum(arr,8);

        System.out.println(h[0]);
        System.out.println(h[1]);
    }
}
