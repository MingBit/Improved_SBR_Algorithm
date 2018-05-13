package algorithm_labcourse;
import java.util.*;

import com.google.zxing.common.BitMatrix;


public class SbrReversal {

	private BitMatrix bm;
	private BitSet p;
	private ArrayList<Integer> score;
	
	
	public SbrReversal(BitMatrix bm, BitSet p, ArrayList<Integer> score){
		this.bm=bm;
		this.p=p;
		this.score=score;
	}
	
	public BitMatrix getBm(){
		return bm;
	}
	public BitSet getP(){
		return p;
	}
	public ArrayList<Integer> getScore(){
		return score;
	}
	public void setBm(BitMatrix bm){
		this.bm=bm;
	}
	public void setP(BitSet p){
		this.p=p;
	}
	public void setScore(ArrayList<Integer> score){
		this.score=score;
	}

	
	public int findCandidate(ArrayList<Integer> score, BitSet p){
		
		int countOP=0;
		int max=0;
		int index=0;
		for(int i=0;i<p.length();i++){
			if(p.get(i)==true){
				countOP++;
			}
		}
		for (int i=0;i<score.size();i++){
			if(score.get(i)>=max && p.get(i)==true){
				max=score.get(i);
				index=i;
			}
		}
//		System.out.println("index="+index);
		return index;
	}
	
	public static BitSet createParity(BitMatrix bm){
		int bm_row=bm.getHeight();
		int bm_col=bm.getWidth();
	
		BitSet bs= new BitSet();

		for(int i=0;i<bm_col;i++){
			int count=0;
			for(int j=0;j<bm_row;j++){
				
				if(bm.get(j,i)==true){
					count++;
				}
				else
					continue;
			}
			
			if(count%2==1){
				bs.set(i);
				
			}
		}
		
		return bs;
	}
	
	public SbrReversal reversal(SbrReversal sb,BitSet vi,int k){
		
		int bm_row=sb.getBm().getHeight();
		BitSet adj_vertex= new BitSet(bm_row);
		HashMap<Integer, BitSet> adj_vertex_update= new HashMap<Integer, BitSet>();
		
		vi.set(k, true);


		for(int i=0;i<vi.length();i++){ //find the each vertex adjacent to vi
			
			if(vi.get(i)==true && i!=k){
				
					adj_vertex= sb.extractVector(i,sb.getBm()); //extract each adjacent vector
					adj_vertex_update.put(i,adjOperation(sb,adj_vertex,i,vi));
			}
		
		}

		sb.p.xor(vi);

		Set set= adj_vertex_update.entrySet();
		Iterator i= set.iterator();
		while(i.hasNext()){
			Map.Entry adj= (Map.Entry)i.next();
			sb.bm=updateBm((int)adj.getKey(),bm,(BitSet)adj.getValue());

		}
		
		sb.p=createParity(sb.bm);

		sb.score=createScore(sb.bm,sb.p);

		return sb;
	}

	public BitSet extractVector(int k, BitMatrix bm){ //extract function need to debug
		int bm_row=bm.getHeight();
		BitSet extractBs= new BitSet();
		
		for(int i=0;i<bm_row;i++){
			if(bm.get(i, k)==true){
				extractBs.set(i,true);
			}else{
				extractBs.set(i,false);
			}
			
		}
		
		return extractBs;
	}
	
	public static BitSet adjOperation(SbrReversal sb, BitSet vj,int k,BitSet vi){
		
		vj.set(k,true);
		vj.xor(vi);
		

		return vj;
	}
	
	public static BitMatrix updateBm(int k, BitMatrix bm, BitSet vj){
		BitMatrix newBm= new BitMatrix(bm.getHeight(), bm.getWidth());
		for(int i=0;i<bm.getHeight();i++){
			for(int j=0;j<bm.getWidth();j++){
				if(bm.get(i, j)==true && i!=k && j!=k){
					newBm.set(i, j);
				}
			}
		}
		for(int i=0;i<bm.getHeight();i++){
			if(vj.get(i)==true){
				newBm.set(k,i);
				newBm.set(i,k);
			}
		}
		
		return newBm;
	} 
	
	public static ArrayList<Integer> createScore(BitMatrix bm, BitSet p){
		ArrayList<Integer> score= new ArrayList<Integer>();
		int OPcount=0;
		int adjOP=0;
		int adj=0;
		for(int i=0;i<p.length();i++){
			if(p.get(i)==true){
				OPcount++;
			}
		}
		for(int i=0;i<bm.getHeight();i++){
			adjOP=0; adj=0;
			for(int j=0;j<bm.getWidth();j++){
				if(bm.get(i, j)==true && p.get(j)==true){
					adjOP++;
				}else if(bm.get(i, j)==true && p.get(j)!=true){
					adj++;
				}
			}
			score.add(OPcount+adj-adjOP-1);
		}
		
		return score;
	}
	
	public static boolean checkOriented(BitSet v){
		int count=0;
		boolean b=false;
		for(int i=0;i<v.length();i++){
			if(v.get(i)==true){
				count++;
			}
		}
			if(count%2==1){
				b=true;
			}
			
		
		return b; 
	}
	
	public static ArrayList<Integer> scoreAddOperation(BitSet bs, ArrayList<Integer> score){
		ArrayList<Integer> newScore= new ArrayList<Integer>();
		int len_bs= bs.length();
		for(int i=0;i<len_bs;i++){
			if(bs.get(i)==true){
				newScore.add(score.get(i)+1);
			}else{
				newScore.add(score.get(i));
			}
		}
		if(newScore.size()<score.size()){
			for(int i=newScore.size();i<score.size();i++){
				newScore.add(score.get(i));
			}
		}
		
		return newScore;
	}
	
	public static ArrayList<Integer> scoreDelOperation(BitSet bs, ArrayList<Integer> score){
		ArrayList<Integer> newScore= new ArrayList<Integer>();
		int len_bs= bs.length();
		for(int i=0;i<len_bs;i++){
			if(bs.get(i)==true){
				newScore.add(score.get(i)-1);
			}else{
				newScore.add(score.get(i));
			}
		}
		if(newScore.size()<score.size()){
			for(int i=newScore.size();i<score.size();i++){
				newScore.add(score.get(i));
			}
		}
		
		return newScore;
}
	


	
	
}

