package algorithm_labcourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomInitial {
	
	public static  int[] randomNumbers(int n, int max) {
	    Random r = new Random();
	    Random s= new Random();
	   
	    Set<Integer> taken = new HashSet<Integer>();
	    int[] arr = new int[n+2];
	    arr[0]=0;
	    for (int rnd, i = 1; i < n+1; i++) {
	    	boolean symbol=s.nextBoolean();
	        while (!taken.add(rnd = r.nextInt(max)));
	        if(symbol==true){
	        	arr[i] = rnd+1;
	        }else{
	        	arr[i]=-rnd-1;
	        }
	        
	    }
	    arr[n+1]=max+1;
	   
    return arr;
}
	public static HashMap<Integer, int[]> initialSet(int n,int length, int max){
		HashMap<Integer,int[]> arraySet= new HashMap<Integer, int[]>();
		for(int i=0;i<n;i++){
			arraySet.put(i, randomNumbers(length,max));
		}
		return arraySet;
		
	}
	
	

}
