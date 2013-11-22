package tp_mdr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Lire_BD {
	static String CASRN[]= new String[582];
	static private double DC[]=new double[582];
	static private int TC[]=new int[582];
	static private double PC[]=new double[582];
	static private int VC[] = new int[582];
	static private double ACEN[]=new double[582];
	static private double MW[]=new double[582];
	static private double DM[]=new double[582];
	static private double NBP[]=new double[582];

	public static void main(String[] args) throws IOException 
	{
		/*CSVReader reader = new CSVReader(new FileReader("C:\\Documents and Settings\\Administrateur\\workspace\\Tp_MDR\\DC_TC_PC_VC_ACEN_MW_DM_NBP.data.csv"));
		String strLine[];

		int i =1;
		while ((strLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
		//strLine = reader.readNext();
	        System.out.println(strLine[0]);
	        i++;
	    }
		System.out.println(i);
		 */
		BufferedReader br = null;
		String [] sCurrentLine_split;
	//	String [] CASRN = new String[582];
		int i=0;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(".\\DC_TC_PC_VC_ACEN_MW_DM_NBP.data.csv"));
			br.readLine();
			while ((sCurrentLine = br.readLine()) != null) 
			{

				//	System.out.println(sCurrentLine);
				sCurrentLine_split =sCurrentLine.split(";");
				//CASRN[i]=sCurrentLine_split[0];
				DC[i]= Double.parseDouble(sCurrentLine_split[1]);
				TC[i]= Integer.parseInt((sCurrentLine_split[2]));
				PC[i]= Double.parseDouble(sCurrentLine_split[3]);
				VC[i]= Integer.parseInt((sCurrentLine_split[4]));
				ACEN[i]= Double.parseDouble(sCurrentLine_split[5]);
				MW[i]= Double.parseDouble(sCurrentLine_split[6]);
				DM[i]= Double.parseDouble(sCurrentLine_split[7]);
				NBP[i]= Double.parseDouble(sCurrentLine_split[8]);
				i++;
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		for(int j=0;j<CASRN.length;j++)
		{
			System.out.println(DC[j]+"  "+TC[j]+"  "+PC[j]+"  "+VC[j]+"  "+ACEN[j]+"  "+MW[j]+"  "+DM[j]+"  "+NBP[j] );
		
		}
		

	}

}
