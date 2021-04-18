package Parser;

import java.util.List;

public class Output {
	public Output(String string, int n1, int n2, String tech, int i) {
		// TODO Auto-generated constructor stub
		this.letter = string;
		this.note1 = n1;
		this.note2 = n2;
		this.tech = tech;
		this.index = i;
	}

	public Output(String string, int n1, int n2, String tech, int i, boolean isChord) {
		// TODO Auto-generated constructor stub
		this.letter = string;
		this.note1 = n1;
		this.note2 = n2;
		this.tech = tech;
		this.index = i;
		this.isChord = isChord;
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
	public String getTech() {
		// TODO Auto-generated method stub
		return this.tech;
	}

	public String getTech2(){
		return this.tech2;
	}
	String letter;
	int note1;
	int note2;
	String tech;
	String tech2;
	int index;
	boolean isChord;
	int line;
	int lineCol;

	public int getLine() {
		return this.line;
	}
	public int getLineCol() {
		return this.lineCol;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public void setLineCol(int lineCol) {
		this.lineCol = lineCol;
	}
}