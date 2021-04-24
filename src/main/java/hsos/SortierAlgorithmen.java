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

	 public static long selectionSort(int[] sorted) {
		long nT=System.nanoTime();
		for (int i = 0; i < sorted.length - 1; i++) {
			for (int j = i + 1; j < sorted.length; j++) {
				if (sorted[i] > sorted[j]) {
					int x = sorted[i];
					sorted[i] = sorted[j];
					sorted[j] = x;
				}
			}
		}
		long nanoResult = System.nanoTime()-nT;
		ueberpruefung(sorted);
		return nanoResult;
	}

	 public static long quickSort(int[] arr){
	 	long nT=System.nanoTime();
	 	quickSortRekursiv(arr,0,arr.length-1);
	 	long nanoResult = System.nanoTime()-nT;
	 	ueberpruefung(arr);
	 	return nanoResult;
	}
	 private static void quickSortRekursiv(int[] liste, int kleinsterWert, int groessterWert) {
		int pivotPosition = partitionieren(liste, kleinsterWert, groessterWert);

		if (kleinsterWert<pivotPosition-1) {
			quickSortRekursiv(liste, kleinsterWert, pivotPosition-1);
		}
		if (groessterWert>pivotPosition) {
			quickSortRekursiv(liste, pivotPosition, groessterWert);
		}
	}
	 private static int partitionieren(int []liste, int kleinsterWert, int groessterWert) {
		int Pivot = liste[(kleinsterWert+groessterWert)/2];
		while(kleinsterWert<=groessterWert) {
			while(liste[kleinsterWert]<Pivot) {
				kleinsterWert++;
			}
			while(liste[groessterWert]>Pivot)
				groessterWert--;
			if(kleinsterWert<=groessterWert) {
				tauschen(liste, kleinsterWert, groessterWert);
				kleinsterWert++;
				groessterWert--;
			}
		}
		return kleinsterWert;
	}
	 private static void tauschen(int[] liste, int a, int b) {
		int temp = liste[a];
		liste[a]=liste[b];
		liste[b]=temp;
	 }



	public static void mergesorting(int links, int rechts, int[]arr) {

		if (rechts > links) {

			int q = (int) Math.floor( (links + rechts) /2);

			mergesorting(links, q, arr);
			mergesorting(q + 1, rechts, arr);
			merge(links, q, rechts, arr);
		}
	}
	private static void merge(int links, int q, int rechts, int[]arr) {
		int[] array = new int[arr.length];
		int i;
		int j;


		i = links;
		while(i<=q) {
			array[i] = arr[i];
			i++;
		}


		j = q+1;
		while(j<= rechts) {
			array[rechts + q + 1 - j] = arr[j];
			j++;
		}



		i = links;
		j = rechts;
		i = links;
		j = rechts;

		int k = links;

		while (k <= rechts){
			if (array[i] <= array[j]) {
				arr[k] = array[i];
				i++;
			} else {
				arr[k] = array[j];
				j--;
			}
			k++;
		}
	}
	public static long mergeSort(int []arr) {
		long nT = System.nanoTime();
		mergesorting(0, arr.length-1,arr);
		long nanoResult = System.nanoTime()-nT;
		ueberpruefung(arr);
		return nanoResult;
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
		//ueberpruefung(x);
		System.out.println(mergeSort(x));
		//System.out.println(insertionSort(x));
		
	}
}
