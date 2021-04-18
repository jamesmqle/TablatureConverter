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
		//this.isChord=false;
	}

	public Output(String string, int n1, int n2, String tech, int i, boolean isChord) {
		// TODO Auto-generated constructor stub
		this.letter = string;
		this.note1 = n1;
		this.note2 = n2;
		this.technique = tech;
		this.index = i;
		this.isChord = isChord;
	}

	public Output(String string, int n1, int n2, String tech, int i, String gracee) {
		// TODO Auto-generated constructor stub
		this.letter = string;
		this.note1 = n1;
		this.note2 = n2;
		this.technique = tech;
		this.index = i;
		this.grace = gracee;
	}

	public Output(String string, int n1, int n2, String tech, int i, String tech2, int n3) {
		// TODO Auto-generated constructor stub
		this.letter = string;
		this.note1 = n1;
		this.note2 = n2;
		this.note3 = n3;
		this.technique = tech;
		this.technique2 = tech2;
		this.index = i;
	}

	public Output(){}

	/*
	 * public void printData(List<Output> data) { data.forEach(obj ->
	 * System.out.println("item1" + obj.getletter() + " " + "item2" + obj.getnum() +
	 * " " + "item2" + obj.getindex())); }
	 */

	public int getindex() {
		// TODO Auto-generated method stub
		return this.index;
	}
	public int getnote1() {
		// TODO Auto-generated method stub
		return this.note1;
	}
	public int getnote2() {
		// TODO Auto-generated method stub
		return this.note2;
	}
	public String getletter() {
		// TODO Auto-generated method stub
		return this.letter;
	}
	public String gettech() {
		// TODO Auto-generated method stub
		return this.technique;
	}
	String letter;
	int note1;
	int note2;
	int note3;
	String technique;
	String technique2;
	String grace;
	int index;
	boolean isChord;
}