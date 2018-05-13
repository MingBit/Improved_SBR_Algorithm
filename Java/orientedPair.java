package algorithm_labcourse;

import java.util.ArrayList;

public class orientedPair {
	
	
	public ArrayList<Integer> i;
	public ArrayList<Integer> j;
	public ArrayList<Integer> score;
	
	public orientedPair(ArrayList<Integer> i, ArrayList<Integer> j, ArrayList<Integer> score){
		this.i=i;
		this.j=j;
		this.score=score;
	}
	
	public void setI(int i){
		this.i.add(i);
	}
	
	public void setJ(int j){
		this.j.add(j);
	}
	
	public void setScore(int score){
		this.score.add(score);
	}
	
	public int getI(int k){
		return this.i.get(k);
	}
	public int getJ(int k){
		return this.j.get(k);
	}
	public int getScore(int k){
		return this.score.get(k);
	}
	public static void printOP(orientedPair op){
		System.out.print(op.i+"\n");
		System.out.print(op.j+"\n");
		System.out.print(op.score+"\n");
	}
	
}
