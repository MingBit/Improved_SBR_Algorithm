package algorithm_labcourse;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

import com.google.zxing.common.BitMatrix;

public class SbrCallFunction {
	
	public static void main(String[] args) {
		HashMap<Integer,Integer> FinaloutPut= callFunction(20,50,50);
		for(int i=0;i<FinaloutPut.size();i++){
			System.out.println("count="+(int)FinaloutPut.get(i));
		}
		
	}
	
	public static HashMap<Integer, Integer>callFunction(int instanceCount, int length, int max) {

		HashMap<Integer,Integer> outPut= new HashMap<Integer,Integer>();
		HashMap<Integer, int[]> arraySet= new HashMap<Integer,int[]>();
		arraySet=RandomInitial.initialSet(instanceCount,length,max);
		for(int i=0;i<instanceCount;i++){
			outPut.put(i, repeatCall((int[])arraySet.get(i)));
		}
		return outPut;
	}
	
	public static int repeatCall(int[] pi){
		
			int count=0;	
			SbrReversal sb= new SbrReversal(null, null, null);
			ArrayList<Integer> score = new ArrayList<Integer>();
			ArrayList<Integer> breakpoints=SbrToBitmatrix.createBreakpoint(pi);
			BitMatrix bm = SbrToBitmatrix.createBitmatrix(breakpoints);
			BitSet p=SbrReversal.createParity(bm);
			score=SbrReversal.createScore(bm, p);
//			SbrToBitmatrix.printMatrix(bm);
			sb.setBm(bm);
			sb.setP(p);
			sb.setScore(score);
			
			while(sb.getP().length()!=0){
				if(count>150){
					break;
				}
				count++;
				int index= sb.findCandidate(sb.getScore(),sb.getP()); 
				BitSet vi= sb.extractVector(index,sb.getBm()); 
				sb=sb.reversal(sb,vi,index); 
				
			}
		return count;
	}
	
	
		
		
		
	}
	
	


