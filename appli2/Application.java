
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

import iut.algo.Decomposeur;


public class Application 
{
	/*------------*/
	/* Variables  */
	/*------------*/

	private Controleur ctrl;
	private ArrayList<Tube> alTube;
	private ArrayList<Cuve> alCuve;
	private int [][]   matrice;

	private HashMap<Cuve, Float> tmpCout = new HashMap<>();

	public Application( Controleur ctrl  ) 
	{ 
		this.ctrl = ctrl;
		this.alCuve  = new ArrayList<>();
		this.alTube  = new ArrayList<>();
		this.matrice = null;
		this.recupererFichier();
	}
	public void etatInstenT( int t )
	{
		for( int i = 0; i < t; i++ )
			this.iterationSuivante( );
			
		return;
	}

	public void recupererFichier()
	{
		Scanner sc = null;
		int     nb = 0;
		int nbCuve = 0;
		int nbTube = 0;
		try { sc = new Scanner( new FileReader("./data/text.txt") ); } catch (FileNotFoundException e)  { }

		while ( sc.hasNext() ) 
		{
			String line = sc.nextLine();
			try 
			{
				Decomposeur dec = new Decomposeur(line);
				String param = dec.getString(0);

				// On utilise ici des if else car les switch ne fonctionnent pas avec les type String
				// Nombre de cuves
				if( param.equals( "NbCuve" ) )
				{
					nbCuve = dec.getInt(1);
				
					this.matrice = new int[nbCuve][nbCuve];
					nbTube = 0;
				}
				if( param.equals( "Cuve" ) )
				{
					this.alCuve.add( Cuve.creerCuve( dec.getInt(2) , dec.getInt(4), dec.getInt(5), dec.getChar(1) ) );
					this.alCuve.get(this.alCuve.size() - 1).setContenu((float)(dec.getDouble(3)));					
				}
				if( param.equals("Structure")) 
				{
					String structure = dec.getString(1);

					while( sc.hasNext() ) 
					{
						
						line = sc.nextLine();
						dec = new Decomposeur(line);
						if(structure.equals("LST")) 
						{
							for( int cpt=1; cpt< line.length()/3; cpt++ )
								this.matrice[(int)(dec.getChar(0) - 'A')][(int)(dec.getString(cpt).split(":")[0].charAt(0) -'A')] = Integer.valueOf( dec.getString(cpt).split(":")[1] );
						}

						if(structure.equals("MC"))
						{

							for(int cpt=0; cpt<nbCuve; cpt++)
							{
								
								for(int cpt1=0; cpt1<nbCuve; cpt1++)
								{
									
									this.matrice[cpt][cpt1] = dec.getInt(cpt1);
									
								}
								if(sc.hasNext()) {
									line = sc.nextLine();
									dec = new Decomposeur(line);
								}
								
							}

						}
						if(structure.equals("MCO"))
						{
							for(int cpt=0; cpt<nbCuve; cpt++)
							{
								
								for(int cpt1=cpt; cpt1<nbCuve; cpt1++)
								{
									if(cpt != cpt1)
									{
										this.matrice[cpt][cpt1] = dec.getInt(cpt1);
									}
										
									
								}
								if(sc.hasNext()) {
									line = sc.nextLine();
									dec = new Decomposeur(line);
								}
							}
						}
					}
					
				}
			} catch(Exception e){e.printStackTrace();}
		}

		for( int cpt0=0; cpt0<nbCuve; cpt0++ )
		{
			for( int cpt1=0; cpt1<nbCuve; cpt1++ )
			{
				if( this.matrice[cpt0][cpt1] != 0 )
					this.alTube.add(Tube.creerTube( this.alCuve.get(cpt0) , this.alCuve.get(cpt1), this.matrice[cpt0][cpt1]));					

			}
		}
	}

	public Tube getTube(Cuve cuveE, Cuve cuveS)
	{
		for(Tube t: this.alTube)
		{
			if (t.getSortie().getId() == cuveE.getId() || t.getEntree().getId() == cuveS.getId() )
				return t;
		}
		return null;
	}

	public void iterationSuivante()
	{
		for(Cuve cuve : this.alCuve)
		{
			tmpCout.put(cuve, 0.0f);
		}

		this.alCuve = trierOrdreCroissant(this.alCuve);
	
		for( int i = 0; i < this.alCuve.size(); i++ )
			for( int j = 0; j < this.alCuve.size(); j++ )
				this.verser( this.alCuve.get(i),this.alCuve.get(j));

		for(Cuve cuve : this.alCuve)
			cuve.setContenu(tmpCout.get(cuve));
		
	}

	// Trie bulle sur l'arrayList
	public ArrayList<Cuve> trierOrdreCroissant(ArrayList<Cuve> arrayCuve)
	{
		Cuve tmp  = null;
		Cuve tmp2 = null;
						
		for(int cpt=0; cpt<arrayCuve.size();cpt++)
		{
			for(int i=0;i<arrayCuve.size()-1;i++)
			{
				if(arrayCuve.get(i).getContenu() > arrayCuve.get(i+1).getContenu())
				{
					tmp=arrayCuve.get(i);
					arrayCuve.set(i, arrayCuve.get(i+1));
					arrayCuve.set(i+1,tmp);
				}
			}
		}
		return arrayCuve;
	}

	public void verser( Cuve cuveE, Cuve cuveS)
	{
		if (this.getTube(cuveE,cuveS) == null ) return;
		if ( null == cuveE || null == cuveS ) return;
		if ( cuveS.getContenu() > cuveE.getContenu() )return;
		if ( cuveE.getContenu() == 0 && cuveS.getContenu() == 0 ) return;

		// Si l'entrée est plus grande que la sortie
		if ( cuveE.getContenu()  > cuveS.getContenu() )
		{
			// Test si l'on peut ajouter
			if ( (cuveS.getCapacite() - cuveS.getContenu()) > 0 )
			{
				float moyenneAjouter = (cuveE.getContenu() - cuveS.getContenu())/2;
				Tube tube = this.getTube(cuveE, cuveS);
				float nouvelleValeur = tube.getSection();

				if( moyenneAjouter < tube.getSection())
					nouvelleValeur = moyenneAjouter;

				// Le contenu de E n'est pas assez grand pour donner le max de la section
				if(cuveE.getContenu() - tube.getSection()  < 0)
				{
					float moy = (cuveE.getContenu() + cuveS.getContenu())/2;
					nouvelleValeur = cuveE.getContenu() - moy;
				}
					
				this.tmpCout.put(cuveE, this.tmpCout.get(cuveE)- nouvelleValeur);
				this.tmpCout.put(cuveS, this.tmpCout.get(cuveS)+ nouvelleValeur);

				return;
			}
		}
	}

	public ArrayList<Tube> getAlTube() { return this.alTube;}
	public ArrayList<Cuve> getAlCuve() { return this.alCuve;}

}