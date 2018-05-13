package algorithm_labcourse;

import java.util.ArrayList;
import java.util.HashMap;

public class basicSorting {
	
	@SuppressWarnings("null")
	public static orientedPair checkOP(int [] pi, orientedPair op){

		for (int i=0;i<pi.length-1;i++){
			for (int j=i+1;j<pi.length;j++){
				if((pi[i]+pi[j]==1 || pi[i]+pi[j]==-1)&& (pi[i]*pi[j]<0) ||(pi[i]+pi[j]==-1 && pi[i]*pi[j]==0)){

					op.setI(i);
					op.setJ(j);
				}
				
			}
		
	}
		return op;
	}
	
	public static int[] reversal(orientedPair op, int[] pi,int start, int end){

		int [] piTemp = new int[pi.length];
		for (int i=0;i<pi.length;i++){
			if(i<start || i>end){
				piTemp[i]=pi[i];
			}else if(i>=start && i<=end){
				piTemp[i]=0-pi[start+end-i];
			}
		}
		return piTemp;
	}
	
	public static int getScore(int[] pi){
		int score=0;
		for(int i=0;i<pi.length-1;i++){
			for(int j=i+1;j<pi.length;j++){
				if(((pi[i]+pi[j]==1 || pi[i]+pi[j]==-1)&& (pi[i]*pi[j]<0)) ||(pi[i]+pi[j]==-1 && pi[i]*pi[j]==0) ){ //need to debug
					score++;
				}
			}
		}

		return score;
	}
	
	public static orientedPair oriPairScore(orientedPair op,int[] pi,int k){

		int[] piTemp;

		if(pi[op.getI(k)]+pi[op.getJ(k)]==1){
			piTemp=reversal(op,pi,op.getI(k),op.getJ(k)-1);

			op.setScore(getScore(piTemp));
		}
		else if(pi[op.getI(k)]+pi[op.getJ(k)]==-1){
			piTemp=reversal(op,pi,op.getI(k)+1,op.getJ(k));

			op.setScore(getScore(piTemp));
		}
		return op;
	}
	
	public static int[] recursionFunction(int[] pi){
		ArrayList<Integer> I = new ArrayList<Integer>();
		ArrayList<Integer> J = new ArrayList<Integer>();
		ArrayList<Integer> Score= new ArrayList<Integer>();
		orientedPair op=new orientedPair(I,J,Score);
		int maxScore=0 ;
		int maxIndex = 0;
		op=checkOP(pi,op);

		for(int i=0;i<op.i.size();i++){
			
			op=oriPairScore(op,pi,i);
			if(op.getScore(i)>maxScore){
				maxScore=op.getScore(i);
				maxIndex=i;
			}
		}
//		System.out.print(pi[op.getI(maxIndex)]+pi[op.getJ(maxIndex)]+"\n");
//		op.printOP(op);
		if(pi[op.getI(maxIndex)]+pi[op.getJ(maxIndex)]==-1){
			pi=reversal(op,pi,op.getI(maxIndex)+1,op.getJ(maxIndex));
		}else if (pi[op.getI(maxIndex)]+pi[op.getJ(maxIndex)]==1){
			pi=reversal(op,pi,op.getI(maxIndex),op.getJ(maxIndex)-1);
		}
		
//		for(int i=0; i<pi.length;i++){
//			System.out.print(pi[i]+" ");
//		}
//		System.out.println();
		return pi;
		
	}
	
	public static void main(String[] args) {

		HashMap<Integer, Integer> outPut= new HashMap<Integer,Integer>();
		
		HashMap<Integer,int[]> input= RandomInitial.initialSet(20,50,50);
		for(int i=0;i<input.size();i++){
			outPut.put(i, repeatBasicCall((int[])input.get(i)).get(0));
		}
//		for(int i=0;i<outPut.size();i++){
//			System.out.println("count="+(int)outPut.get(i));
//		}
		
	}
	public static ArrayList<Integer> repeatBasicCall(int[] pi){
		boolean b= true;
		ArrayList<Integer> resultHash= new ArrayList<Integer>();
		int result=0;
		while(result<=1000 && getScore(pi)!=0){
			result++;
			pi=recursionFunction(pi);
			
		}
		resultHash.add(result);
		for(int i=0; i<pi.length;i++){
			System.out.print(pi[i]+" ");
		}
		System.out.println();
		for(int i=0;i<pi.length;i++){
			if(pi[i]!=i){
				b=false;
				break;
			}
		}
		if(b==false) resultHash.add(1);
		else resultHash.add(0);
		
		return resultHash;
	}	
		
		
		
	}


