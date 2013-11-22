package gep_dc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class GepData {
	private double data[][]; 
	public ArrayList<String> substance_list;
	private double DC[];

	public GepData(String FileName) {
		// TODO Auto-generated constructor stub
		String tmp;
		substance_list = new ArrayList<String>();
		try {
	        BufferedReader br = new BufferedReader(new FileReader(FileName));
	        br.readLine();
	        while ( (tmp=br.readLine())!=null ) {
				String[] splitted = tmp.split(";");
				substance_list.add(splitted[0]);
			}
	        br.close();
	        br = new BufferedReader(new FileReader(FileName));

	        br.readLine();
	        DC = new double[substance_list.size()];
	        data = new double[substance_list.size()][7];
	        int subs = 0;
	        while ( (tmp=br.readLine())!=null ) {
				String[] splitted = tmp.split(";");
				DC[subs] = Double.parseDouble(splitted[1]);
				for (int i=2;i<splitted.length;i++) {
					data[subs][i-2] = Double.parseDouble(splitted[i]);
				}
				subs ++;
			}
           br.close();
	    }
	    catch (Exception e){
	    		e.printStackTrace();
	    }
	}
	
	public double getData(int substance, int variable){
		return data[substance][variable];
	}
	
	public double getDC(int substance) {
		return DC[substance];
	}
	
	public String toString() {
		String S = "";
		for (int i = 0; i<substance_list.size() ; i++ ) {
			S += substance_list.get(i)+":";
			S+= "DC="+String.valueOf(DC[i]);
			for (int j=0;j<7;j++) {
				S += ", x"+String.valueOf(j)+"="+String.valueOf(data[i][j]);
			}
			S += "\n";
		}
		return S;
	}

	public int get_NbrSubstance()
	{
		
		return substance_list.size();
	}
}
