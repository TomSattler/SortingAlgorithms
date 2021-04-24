package hsos;

import java.util.ArrayList;

public class SortierAlgorithmen {
	 public static long insertionSort(int[] toSort){
		 long nT = System.nanoTime();
	        for(int i=1;i<toSort.length;i++){
	            for(int j=i;j>0;j--){
	            	//Gehe nach hinten und vertausche ggf.
	                if(toSort[j]<toSort[j-1]){
	                    int temp=toSort[j-1];
	                    toSort[j-1]=toSort[j];
	                    toSort[j]=temp;
	                }else{
	                	//Wenn vorherige kleiner ist Rest auch kleiner (Sortierter Bereich)
	                    break;
	                }
	            }
	        }
	        
	        long nanoResult = System.nanoTime()-nT;
	        ueberpruefung(toSort);
	        return nanoResult;
	    }
	 
	 public static long heapSort(int[] toSort) {
		 long nT=System.nanoTime();
		 //Array -> ArrayList
		 ArrayList<Integer> heapAL = new ArrayList<>();
		 for(int i=0;i< toSort.length;i++){
			 heapAL.add(toSort[i]);
		 }
		 //Sortiertes Array
		 int[] result = new int[heapAL.size()];
		 
		 for(int r=0;r<result.length;r++) {
				for(int i=0;i<heapAL.size();i++) {
					int position = i;
					//Heapify - Reheapify
					while (position > 0 && heapAL.get(getPreviousPos(position)) > heapAL.get(position)) {
						int copy = heapAL.get(getPreviousPos(position));
						 heapAL.set(getPreviousPos(position), heapAL.get(position));
						 heapAL.set(position, copy);

						position = getPreviousPos(position);
					}
				}
				//Pop first
				result[r]=heapAL.get(0);
				heapAL.remove(0);
		 }
		 long resultTime = System.nanoTime()-nT;
		 ueberpruefung(result);
		 return resultTime;
	 }
	 
	 private static int getPreviousPos(int x){
	        //Gerade
	        if(x%2==0){
	            return (x/2)-1;
	        }
	        //Ungerade
	        else{
	            return x/2;
	        }
	    }
	        
	 private static void ueberpruefung(int[] arr) {
		 boolean io=true;
		 //Ueberprueft, ob n-1 < als n
		 for(int i=1;i<arr.length;i++) {
			 if(arr[i]<arr[i-1]) {
				 io=false;
			 }
		 }
		 if(io==true) {
			 System.out.println("In Ordnung / Korrekt Sortiert");
		 }
		 else {
			 System.err.println("Sortierfehler");
		 }
	 }
	 
	 public static void main(String[] args) {
		int[] x = {3,1,6,16,4,8,2,19,5,42,1,2};
		ueberpruefung(x);
		System.out.println(heapSort(x));
		System.out.println(insertionSort(x));
		
	}
}
