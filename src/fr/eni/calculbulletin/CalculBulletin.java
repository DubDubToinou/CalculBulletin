package fr.eni.calculbulletin;

import java.util.Scanner;



public class CalculBulletin {

	static final int CADRE = 1;
	static final int AGENT_DE_MAITRISE = 2;
	static final int EMPLOYE_DE_BUREAU = 3;
	static final int TAUX_CADRE = 25;
	static final int TAUX_AGENT_DE_MAITRISE = 20;
	static final int TAUX_EMPLOYE_DE_BUREAU = 15;
	
	public static Scanner scan;
	
		
	public static void main(String[] args) 
	{
		/* Déclaration variable */
		
		scan = new Scanner(System.in);
		
		String nom = "";
		String prenom = "";
		int statut = 0;
		int nbHeures = 0;
		float taux = 0.0f;
		float salaire = 0.0f;
		float salaireNet = 0.0f;
		float cotisation = 0.0f;
		int nbEnfants = 0;
		int prime = 0;
		
		/*
		 Do While pour la saisie : Nom, Prénom, Statut, Heures, sachant qu'il faut que toutes les  valeurs soit renseignée).
		 */
		
		do
		{
			System.out.println("Nom : ");
			nom = SaisirLine();
			if(nom.isEmpty()) //isEmpty() pour savoir si il y a des données non.
			{
				System.out.println(("____Le Nom est obligatoire____"));
			}
		}while(nom.isEmpty());
		
			do
			{
				System.out.println("Prenom : ");
				prenom = SaisirLine();
				if(prenom.isEmpty())
				{
					System.out.println(("____Le Prenom est obligatoire____"));
				}
			}while(prenom.isEmpty()); 
							
					do
					{
						System.out.println("Statut: 1 : Cadre | 2 : Agent de Maitrise | 3 : Employe de Bureau ");
						statut = SaisirInt();
						if(statut <= 0 || statut > 3)
						{
							System.out.println(("____Faites le choix 1, 2 ou 3 !____"));
						}
					}while(statut <= 0 || statut > 3);
			
						do
						{
							System.out.println("Combien d'heure " + prenom +" " +nom + " à fait d'heures ce mois-ci :");
							nbHeures = SaisirInt();
							if(nbHeures < 0)
							{
								System.out.println(("____Le nombre d'heures ne peut pas être nÃ©gatif ! ____"));
							}
						}while(nbHeures < 0);
						
						/*
						 * Initialisation des différents taux horaires.
						 */
						
				if(statut == CADRE)
				{
					taux = TAUX_CADRE;
				}else if(statut == AGENT_DE_MAITRISE)
					{
						taux = TAUX_AGENT_DE_MAITRISE;
					}else
						taux = TAUX_EMPLOYE_DE_BUREAU;
				
				
				/*
				 * Calcul du salaire net - cotisation selon le nombre d'heures
				 */
				
				if(nbHeures < 169)
				{
					salaire = (taux * nbHeures);
					cotisation  = ((salaire * 0.0349f) + (salaire * 0.0615f) + (salaire * 0.0095f) + (salaire * 0.0844f) + (salaire * 0.0305f) + (salaire * 0.0381f) + (salaire * 0.0102f));
					salaireNet = salaire - cotisation;
					//System.out.println("Le salaire Net de " +nom + " " +prenom + " est de " +salaireNet );
				}
					else if(nbHeures < 180)
					{
						//float taux50 = taux +(taux*50/100);
						salaire = ((taux * 169) + ((nbHeures-169)*(taux*1.5f)));
						cotisation  = ((salaire * 0.0349f) + (salaire * 0.0615f) + (salaire * 0.0095f) + (salaire * 0.0844f) + (salaire * 0.0305f) + (salaire * 0.0381f) + (salaire * 0.0102f));
						salaireNet = salaire - cotisation;
						//System.out.println("Le salaire Net de " +nom + " " +prenom + " est de " +salaireNet );
					}else if(nbHeures > 180)
						{
							//float taux60 = taux +(taux*60/100);
							salaire = ((taux * 169) + (11*(taux*1.5f)) + ((nbHeures-180)*(taux*1.6f)) );
							cotisation  = ((salaire * 0.0349f) + (salaire * 0.0615f) + (salaire * 0.0095f) + (salaire * 0.0844f) + (salaire * 0.0305f) + (salaire * 0.0381f) + (salaire * 0.0102f));
							salaireNet = salaire - cotisation;
							//System.out.println("Le salaire Net de " +nom + " " +prenom + " est de " +salaireNet );
						}
						
				
				/*
				 * On demande le nombre d'enfants pour calculer la prime !
				 */
				
				System.out.println("Merci de saisir le nombre d'enfants pour le  calcul de la prime : ");
				nbEnfants = SaisirInt();
				
				switch(nbEnfants)
				{
				
					case 0: 
						prime = 0; break;
				
					case 1:
						prime = 20; break;
						
					case 2:
						prime = 50;
						
					default : prime = 70 + (nbEnfants - 2)*20;
						
				}
				salaireNet = salaireNet + prime;
				System.out.println("Votre salaire Net est de " +salaireNet +" euros dont " +cotisation + " euros de cotisation et " +prime + "euros de Prime ! pour vos " +nbEnfants +" enfants.");
				
			scan.close();
						
	}

	
	/*
	 * 2 fonctions pour la saisie via un scanner.
	 */
	
	public static String SaisirLine()
	{
		String result = scan.nextLine();
		return result;
	}
	
	public static int SaisirInt()
	{	
		int result = scan.nextInt();
		return result;
	}
}
