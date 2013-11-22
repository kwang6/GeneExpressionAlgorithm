package gep_dc;

import java.util.ArrayList;
import java.util.Random;

public class GepPopulation {
	ArrayList<GPObject> liste_operateurs = new ArrayList<GPObject>();
	ArrayList<GPObject> liste_terminaux = new ArrayList<GPObject>();
	public ArrayList<GepKExpression> GetPopulation(int nbr_individus, int size,GepData data)
	{
		ArrayList<GepKExpression> population = new ArrayList<GepKExpression>();
		

		GPOperatorAdd add = new GPOperatorAdd();
		GPOperatorMul mul = new GPOperatorMul();
		GPOperatorExp exp = new GPOperatorExp();
		GPOperatorLog log = new GPOperatorLog();
		GPOperatorNeg neg = new GPOperatorNeg();
		GPOperatorSub sub = new GPOperatorSub();
		GPOperatorDiv div = new GPOperatorDiv();



		liste_operateurs.add(add);
		liste_operateurs.add(mul);
		liste_operateurs.add(exp);
		//liste_operateurs.add(log);
		liste_operateurs.add(neg);
		liste_operateurs.add(sub);
		liste_operateurs.add(div);

		GPTerminalVar x0 = new GPTerminalVar(data,0,"TC");		
		GPTerminalVar x1 = new GPTerminalVar(data,1,"PC");
		GPTerminalVar x2 = new GPTerminalVar(data,2,"VC");
		GPTerminalVar x3 = new GPTerminalVar(data,3,"ACEN");
		GPTerminalVar x4 = new GPTerminalVar(data,4,"MW");
		GPTerminalVar x5 = new GPTerminalVar(data,5,"DM");
		GPTerminalVar x6 = new GPTerminalVar(data,6,"NBP");		

		liste_terminaux.add(x0);
		liste_terminaux.add(x1);
		liste_terminaux.add(x2);
		liste_terminaux.add(x3);
		liste_terminaux.add(x4);
		liste_terminaux.add(x5);
		liste_terminaux.add(x6);
		
		Random random = new Random();
		double c1 = random.nextDouble() * random.nextInt(20)-10;
		double c2 = random.nextDouble() * random.nextInt(20)-10;
		double c3 = random.nextDouble() * random.nextInt(20)-10;
		double c4 = random.nextDouble() * random.nextInt(20)-10;
		GPTerminalCste cst1 = new GPTerminalCste(c1);
		GPTerminalCste cst2 = new GPTerminalCste(c2);
		GPTerminalCste cst3 = new GPTerminalCste(c3);
		GPTerminalCste cst4 = new GPTerminalCste(c4);
		liste_terminaux.add(cst1);
		liste_terminaux.add(cst2);
		liste_terminaux.add(cst3);
		liste_terminaux.add(cst4);

		for(int i= 0;i<nbr_individus;i++)
		{
			//System.out.println("=================================");
			GepKExpression gepKE = new GepKExpression(size);
			gepKE.randomInit(liste_operateurs, liste_terminaux);
			//System.out.println(gepKE.getExpressionTree());
			//System.out.println(gepKE.getExpressionTree().eval(1));
			population.add(gepKE);
		}

		return population;
	}

	public double EvaluationFitness(int substance,GepKExpression myExpression,GepData data )
	{
		double fitness = 100000;
		int K=data.get_NbrSubstance();
		double somme=0.0;

		for(int i=0;i<K;i++)
		{
			double delta=Math.abs(myExpression.getExpressionTree().eval(i)-data.getDC(i));		
			somme=somme+delta;		
		}

		fitness=somme/(K-1);


		return fitness;
	}

	public int TrouverMeilleurFitness(double[] fitnessArray)
	{
		int meilleur_Indice =0;
		for(int i =0;i<fitnessArray.length;i++)
		{
			if(fitnessArray[i]<fitnessArray[meilleur_Indice])
			{
				meilleur_Indice = i;
			}

		}
		return meilleur_Indice;
	}

	public ArrayList<GepKExpression> SelectionDesParents(ArrayList<GepKExpression> population,int n,GepData data)
	{
		ArrayList<GepKExpression> selection_Parents = new ArrayList<GepKExpression>();
		int m = population.size();
		for(int i = 0;i<n;i++)
		{
			Random R = new Random();
			int j=R.nextInt(m); 
			int k=R.nextInt(m);

			double fitness_j = EvaluationFitness(data.get_NbrSubstance(), population.get(j), data);
			double fitness_K = EvaluationFitness(data.get_NbrSubstance(), population.get(k), data);

			if(fitness_j < fitness_K)
			{
				selection_Parents.add(population.get(j));
			}
			else
			{
				selection_Parents.add(population.get(k));
			}
		}

		return selection_Parents;
	}

	public ArrayList<GepKExpression> GenerationDesEnfants(ArrayList<GepKExpression> parentsArrayList,int nbr_pop,double taux_mutation)
	{
		ArrayList<GepKExpression> generation_Enfant= new ArrayList<GepKExpression>();
		int size = parentsArrayList.size();
		int nbr_mutation = (int) (nbr_pop*taux_mutation);
		//int [] mutation_position = new int[nbr_mutation];
		int random_mutation_position;
		
		Random r = new Random();
		int parent1Indice = r.nextInt(size);
		int parent2Indice = r.nextInt(size);
		
		GepKExpression parent1 = parentsArrayList.get(parent1Indice);
		GepKExpression parent2 = parentsArrayList.get(parent2Indice);
		
		for(int i =0;i< nbr_mutation;i++)
		{
			Random random = new Random();
			random_mutation_position = random.nextInt(nbr_mutation); 
			//mutation_position[i]=random_mutation_position;
			for(int j=0;j<nbr_pop;j++)
			{
				if(j==random_mutation_position)
					generation_Enfant.add(Mutation(Croisement(parent1, parent2), liste_operateurs, liste_terminaux));
				else
				{
					generation_Enfant.add(Croisement(parent1, parent2));
				}
			}
			
		}
		
		
		return generation_Enfant;
	}

	private GepKExpression Croisement(GepKExpression parent1,GepKExpression parent2)
	{				
		int length_gene = parent1.getSize();
		GepKExpression enfantExpression = new GepKExpression(length_gene);		

		Random R = new Random();
		int point=R.nextInt(length_gene);

		for(int i=0;i<point;i++)
		{
			enfantExpression.add(parent1.getGpObject(i));
		}
		for(int i=point;i<length_gene;i++)
		{
			enfantExpression.add(parent2.getGpObject(i));
		}		

		return enfantExpression;
	}

	private GepKExpression Mutation(GepKExpression enfantExpression,ArrayList<GPObject> liste_operateurs ,ArrayList<GPObject> liste_terminaux)
	{
		GepKExpression enfantMutation=enfantExpression;

		Random random = new Random();
		int length = enfantExpression.getSize();
		int nbr_position = random.nextInt(length);

		for(int i =0; i<nbr_position;i++)
		{
			if (i<=(length/2)-1) //head
			{
				Random random2 = new Random();
				Random random3 = new Random();
				Random random4 = new Random();

				int r2=random2.nextInt(liste_operateurs.size());
				int r3=random3.nextInt(liste_terminaux.size());
				int r4=random3.nextInt(1);

				if(r4==0)
					enfantMutation.setGpObject(i,liste_operateurs.get(r2));
				else 
				{
					enfantMutation.setGpObject(i, liste_terminaux.get(r3));
				}
			}
			else// tail
			{
				Random random3 = new Random();
				int r3=random3.nextInt(liste_terminaux.size());

				enfantMutation.setGpObject(i, liste_terminaux.get(r3));
			}
		}



		return enfantMutation;		
	}
}
