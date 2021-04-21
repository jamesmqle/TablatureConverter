package Parser;

import java.util.List;

public class Output {

	String letter;
	int note1;
	int note2;
	int note3;
	String tech;
	String tech2;
	String grace = "";
	int index;
	boolean isChord;
	int line;
	int lineCol;

	public int hammerEnd = 0;
	public int pullEnd = 0;
	public int slurEnd = 0;
	public int slideEnd = 0;

	double durationRatio = -1;
	int lastNoteToEndDist = -1;

	public double getDurationRatio() {
		return durationRatio;
	}
	public boolean isChord() {
		return this.isChord;
	}

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

	// g0h1
	public Output(String string, int n1, int n2, String tech, int i, String grace) {
		// TODO Auto-generated constructor stub
		this.letter = string;
		this.note1 = n1;
		this.note2 = n2;
		this.tech = tech;
		this.index = i;
		this.grace = grace;
	}

	public Output(String string, int n1, int n2, String tech, int i, String tech2, int n3) {
		// TODO Auto-generated constructor stub
		this.letter = string;
		this.note1 = n1;
		this.note2 = n2;
		this.note3 = n3;
		this.tech = tech;
		this.tech2 = tech2;
		this.index = i;
	}

	// 2h4p2
	public Output(String string, int n1, int n2, String tech, String tech2, int n3){
		this.letter = string;
		this.note1 = n1;
		this.note2 = n2;
		this.tech = tech;
		this.tech2 = tech2;
		this.note3 = n3;
	}

	public Output(){}

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
	public String getGrace(){
		return this.grace;
	}

	public String getTech2(){
		return this.tech2;
	}

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

	@Override
	public boolean equals(Object o) {
		Output other = (Output) o;
		return this.index==other.index && this.letter.equals(other.letter) &&this.note1==other.note1 && this.note2==other.note2 && this.note3==other.note3;
	}
}