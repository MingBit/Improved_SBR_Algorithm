package algorithm_labcourse;

import java.util.ArrayList;
import com.google.zxing.common.BitMatrix;
public class SbrToBitmatrix {
	
	private ArrayList<Integer> breakpoints;
	private BitMatrix bm;

	public SbrToBitmatrix(ArrayList<Integer> breakpoints, BitMatrix bm){
		this.bm=bm;
		this.breakpoints=breakpoints;
		
	}
	public ArrayList<Integer> getPoints(){ return this.breakpoints;}
	public BitMatrix getBm(){ return this.bm;}
	public void setPoints(ArrayList<Integer> breakpoints){ this.breakpoints=breakpoints;}
	public void setBm(BitMatrix bm){ this.bm=bm;}
	
	public static ArrayList<Integer> createBreakpoint(int [] pi){
		ArrayList<Integer> breakpoint = new ArrayList<Integer>();
		int lenPi=pi.length;
		breakpoint.add(0);
		for (int i=1;i<lenPi-1;i++){
			if(pi[i]>0){
				breakpoint.add(2*pi[i]-1);
				breakpoint.add(2*pi[i]);
			}
			if(pi[i]<0){
				breakpoint.add(2*(-pi[i]));
				breakpoint.add(2*(-pi[i])-1);
			}
		}
		breakpoint.add(2*pi[lenPi-1]-1);
		return breakpoint;
	}
	
	public static BitMatrix createBitmatrix(ArrayList<Integer> breakpoint){
		int lenBreak=breakpoint.size();
		int list_index = 0;
		int pair_index=0;
//		int matrix_col=0;

		BitMatrix bitMatrix= new BitMatrix(lenBreak/2, lenBreak/2);

		
		for (int i=0;i<lenBreak;i++){
			int matrix_row=breakpoint.get(i)/2;
			list_index=findPairIndex(i,breakpoint);
			for (int t=i+1;t<list_index;t++){
				pair_index=findPairIndex(t,breakpoint);
				if(pair_index>list_index || pair_index<i){
					int matrix_col=breakpoint.get(t)/2;
					bitMatrix.set(matrix_row,matrix_col);
					bitMatrix.set(matrix_col,matrix_row);
				}
				
			}
		}
		return bitMatrix;
		
	}
	public static void printMatrix(BitMatrix bm){
				
		for (int i=0;i<bm.getWidth();i++){
			for (int j=0;j<bm.getHeight();j++){
				if(bm.get(i, j)==true){
					System.out.print(1+" ");
				}
				else{
					System.out.print(0+" ");
				}
			}
			System.out.println();
		}
	}
	
	public static int findPairIndex(int t, ArrayList<Integer> list){
		int index=0;
		if(list.get(t)%2==0){
			index=list.indexOf(list.get(t)+1);
		}
		if(list.get(t)%2==1){
			index=list.indexOf(list.get(t)-1);
		}
		return index;
	}
	

}
