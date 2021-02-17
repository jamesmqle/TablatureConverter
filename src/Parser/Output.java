package Parser;

import java.util.List;

public class Output {
	public Output(String string, int n, int i) {
		// TODO Auto-generated constructor stub
		 this.letter=string;
		 this.num=n;
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
	protected int getnum() {
		// TODO Auto-generated method stub
		return this.num;
	}
	protected String getletter() {
		// TODO Auto-generated method stub
		return this.letter;
	}
	String letter;
	 int num;
	 int index;
}
