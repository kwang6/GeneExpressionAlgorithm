package gep_dc;
//author: kai wang
import java.util.ArrayList;

public class GepMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GepData data = new GepData("DC_TC_PC_VC_ACEN_MW_DM_NBP.data.csv");

		int nbr_Population =100;
		int length_Gene = 15;
		int nbr_Generation = 5;
		int selection_de_parents = 25;
		double taux_mutation = 0.02;

		System.out.println("======================================");
		System.out.println("Number Population : "+nbr_Population);
		System.out.println("Length of Gene: "+length_Gene);
		System.out.println("Number of Generation: "+nbr_Generation);
		System.out.println("Percentage of selection of parents:"+selection_de_parents/nbr_Generation);
		System.out.println();

		System.out.println("========Generer Iini_Population========");
		GepPopulation gep_Population = new GepPopulation();
		ArrayList<GepKExpression> myPopulation= gep_Population.GetPopulation(nbr_Population,length_Gene,data);

		for(int nbr = 0; nbr<nbr_Generation; nbr++)
		{
			System.out.println("===================Generation "+(nbr+1)+"==========================");
			ArrayList<GepKExpression> parentsArrayList= new ArrayList<GepKExpression>();
			ArrayList<GepKExpression> enfantsArrayList= new ArrayList<GepKExpression>();
			
			System.out.println("==========================Selection of Parents===========================");
			parentsArrayList = gep_Population.SelectionDesParents(myPopulation, selection_de_parents,data);
			
			System.out.println("==========================Generation of infants===========================");
			enfantsArrayList = gep_Population.GenerationDesEnfants(parentsArrayList,nbr_Population,taux_mutation);
			
			System.out.println("======================Évaluation=========================");
			double[] fitnessArray = new double[nbr_Population];
			for(int i = 0 ; i< nbr_Population;i++)
			{
				double myfitness=gep_Population.EvaluationFitness(data.get_NbrSubstance(),myPopulation.get(i),data);
				fitnessArray[i]=myfitness;
				//System.out.println("MyFitness:"+fitnessArray[i]);
			}
			System.out.println("=========solution =========");
			int meilleurFitness_Indice = gep_Population.TrouverMeilleurFitness(fitnessArray);
			System.out.println("Fitness:"+fitnessArray[meilleurFitness_Indice]);
			System.out.println("tree:"+myPopulation.get(meilleurFitness_Indice).getExpressionTree());
			System.out.println("expression:"+myPopulation.get(meilleurFitness_Indice).toString());
			
			myPopulation = enfantsArrayList;
			
		}
	}

}
