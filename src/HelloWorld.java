
public class HelloWorld {
    public static void main(String[] args) {
        //int[] x={2,5,1,9,3,12,42,6,4};
        int[] x = new int[10000];
        for(int i=0;i<x.length;i++){
            x[i]=(int)(Math.random()*10000);
        }

       // for(int i=0;i<x.length;i++){
        //    System.out.print(x[i]+" ");
      //  }
        System.out.println(" \n");


        long nanotime=System.nanoTime();
        int[]y=insertionSort(x);
        long finalNanotime=System.nanoTime()-nanotime;
        //int[]y=mergeSort(x);
        System.out.println(" \n");

        for(int i=0;i<y.length;i++){
            System.out.print(y[i]+" ");
        }

        System.out.println("Zeit: "+ finalNanotime);

    }
    static int[] insertionSort(int[] toSort){
        for(int i=1;i<toSort.length;i++){
            for(int j=i;j>0;j--){
                if(toSort[j]<toSort[j-1]){
                    int temp=toSort[j-1];
                    toSort[j-1]=toSort[j];
                    toSort[j]=temp;
                }else{
                    break;
                }
            }
        }
        return toSort;
    }

    static int[] mergeSort(int[] toSort){

        int jump=2; //Also Partition Size

        //Next step
        while(toSort.length>=jump) {

            //Iterate through individual partitions
            for (int i = 0; i < toSort.length; i+=jump) {

                //Save this partition in hilfsarray
                int[] hArr = new int[jump];
                for (int j = 0; j < jump; j++) {
                    hArr[j] = toSort[i + j];
                }

                hArr=merge(hArr);

                //Return merged partition to toSort
                for (int j = 0; j < jump; j++) {
                    toSort[i + j] = hArr[j];
                }
                //next jump
            }
            jump = jump * 2;
        }

        /*
        //First Step
        for(int i=0;i<toSort.length;i+=2){
            if(toSort[i]>toSort[i+1]) {
                int temp = toSort[i];
                toSort[i]=toSort[i+1];
                toSort[i+1]=temp;
            }
        }
        //Second Step

        System.out.println("\n");
        for(int i=0;i<toSort.length;i++){
            System.out.print(toSort[i]+" ");
        }
        System.out.println("\n");

        for(int j=0;j<toSort.length;j+=4) {
            for (int i = 0; i < 3; i++) {

                // [1,7,3,6]->[1,3,7,6]->[1,3,6,7]
                if (toSort[j+i] > toSort[j+i + 1]) {
                    int temp = toSort[j+i];
                    toSort[j+i] = toSort[j+i + 1];
                    toSort[j+i + 1] = temp;

                }


            }
        }


*/
        return toSort;
    }

    static int[] merge(int[] hArr){
        // ????????????????????????

        // ????????????????????????

        // ????????????????????????
        System.out.print("hArr: ");
        for(int i=0;i<hArr.length;i++){
            System.out.print(+hArr[i]+" ");
        }
        System.out.println("\n");
        return hArr;
    }



}
