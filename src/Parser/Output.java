package Parser;

import java.util.List;

public class Output {
	public Output(String string, int n1, int n2, String tech, int i) {
		// TODO Auto-generated constructor stub
		 this.letter=string;
		 this.note1=n1;
		 this.note2=n2;
		 this.technique=tech;
		 this.index=i;
	}

	/*
	 * public void printData(List<Output> data) { data.forEach(obj ->
	 * System.out.println("item1" + obj.getletter() + " " + "item2" + obj.getnum() +
	 * " " + "item2" + obj.getindex())); }
	 */
	protected int getindex() {
		// TODO Auto-generated method stub
		return this.index;
	}
	protected int getnote1() {
		// TODO Auto-generated method stub
		return this.note1;
	}
	protected int getnote2() {
		// TODO Auto-generated method stub
		return this.note2;
	}
	protected String getletter() {
		// TODO Auto-generated method stub
		return this.letter;
	}
	protected String gettech() {
		// TODO Auto-generated method stub
		return this.technique;
	}
	String letter;
	 int note1;
	 int note2;
	 String technique;
	 int index;
}
