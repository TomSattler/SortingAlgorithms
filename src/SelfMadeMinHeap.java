public class SelfMadeMinHeap {
    private int[] heap;
    SelfMadeMinHeap(int [] toHeap){
        this.heap = new int[toHeap.length];
        for(int i=0;i< toHeap.length;i++){
            heap[i]=toHeap[i];

            //Heap Bedingung

            //Go back to check
            //check gerade/ungerade (in getPreviousPos)

            int position=i;
                    // Wenn ursprung < pos
            while(position>0 && heap[getPreviousPos(position)]>heap[position]){
                //switch ursprung <-> pos
                swapBranch(getPreviousPos(position),position);
                //Für check davor
                position=getPreviousPos(position);
            }
        }
    }
    static int getPreviousPos(int x){
        //Gerade
        if(x%2==0){
            return (x/2)-1;
        }
        //Unerade
        else{
            return x/2;
        }
    }

    private void swapBranch(int i, int i1) {
       // System.out.println("Swapping: "+ i + " "+ this.heap[i] + " " + i1+" "+ this.heap[i1]);
        int copy = this.heap[i];
        this.heap[i]=this.heap[i1];
        this.heap[i1]=copy;
       // System.out.println("SWAPED");
      //  System.out.println(this.heap[i] + " " + this.heap[i1]);
    }

    public int popFirst(){
        int firstNr=this.heap[0];


        int[] old=this.heap;
        int newHeap[]=new int[old.length-1];
        for(int i=0;i<old.length-1;i++){
            newHeap[i]=old[i+1];
        }
        this.heap= newHeap;

        this.reheapify();
        return firstNr;
    }

    public void reheapify(){
        /*
        int[] old=this.heap;
        int newHeap[]=new int[old.length-1];
        for(int i=0;i<old.length-1;i++){
            newHeap[i]=old[i+1];
        }
        this.heap= newHeap;
        */
        for(int i=0;i<this.heap.length;i++) {
            int position = i;
            // Wenn ursprung < pos
            while (position > 0 && heap[getPreviousPos(position)] > heap[position]) {
                //switch ursprung <-> pos
                swapBranch(getPreviousPos(position), position);
                //Für check davor
                position = getPreviousPos(position);
            }
        }

    }

    public static void main(String[] args) {
    final int laengeDesArrays=10000;
        int[] x = new int[laengeDesArrays];
        for (int i = 0; i < x.length; i++) {
            x[i] = (int) (Math.random() * 10000);
        }

        for (int i = 0; i < x.length; i++) {
       //     System.out.print(x[i] + " ");
        }
        long nanotime=System.nanoTime();
        SelfMadeMinHeap hp = new SelfMadeMinHeap(x);
        int[] sorted=new int[laengeDesArrays];

    for(int z=0;z<laengeDesArrays;z++) {

    /*
        int[] y = hp.heap;

        System.out.println("\n");
        for (int i = 0; i < y.length; i++) {
            System.out.print(y[i] + " ");
        }
*/
       // System.out.println("\n");
       // System.out.println("\n");

       // System.out.println("First "+hp.popFirst());
        sorted[z]=hp.popFirst();
        /*
        int[] m = hp.heap;
        for (int i = 0; i < m.length; i++) {
            System.out.print(m[i] + " ");
        }

        */

    }
    long finalNanotime=System.nanoTime()-nanotime;
        System.out.println("\nSORDED with HEAPSORT: \n");
        for (int i = 0; i < laengeDesArrays; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.println("Zeit: " + finalNanotime);
    }
}

