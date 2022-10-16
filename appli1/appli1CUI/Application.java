/**
 * Application 1 CUI
 */

import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

import java.lang.Character;

import java.util.ArrayList;

import iut.algo.Clavier;

public class Application 
{
	/*-------------*/
	/* Constantes  */
	/*-------------*/
	
	final int NB_CUVE_MAX = 26 ;
	final int NB_TUBE_MAX = 325;         // 325 car on a calculé le nombre de tubes possible avec 26 cuves.

	/*------------*/
	/* Variables  */
	/*------------*/
	
	private ArrayList<Cuve> alCuve;
	private ArrayList<Tube> alTube;

	private int [][]   matrice;
	private String     structure;

	public Application( ) 
	{
		this.alCuve = new ArrayList<>();
		this.alTube = new ArrayList<>();
		this.matrice   = null;
		this.structure ="";
	}

	public void lancerProgramme()
	{
		char   capaSaisie     ;
		char   instru   = 'Z' ;

		int    capaCuve = 0   ;
		int nbCuve;
		String sret     = ""  ;
		//Création des Cuves

		System.out.print( "Indiquer le nombre de cuves :  " );

		nbCuve = Clavier.lire_int();

		while ( nbCuve > this.NB_CUVE_MAX || nbCuve <= 0 )
		{
			System.out.print( "Incorrecte : Indiquer un nombre de cuves entre 1 et 26 :  " );
			nbCuve = Clavier.lire_int();
		}

		
		this.matrice = new int[nbCuve][nbCuve];

		System.out.print( "Voulez-vous saisir une seule capacité pour toutes les cuves ? [o/n] : " );
		capaSaisie = Clavier.lire_char();

		while ( Character.toUpperCase( capaSaisie ) != 'O' && Character.toUpperCase( capaSaisie ) != 'N' )
		{
			System.out.print( "Erreur : voulez-vous saisir une seule capacité pour toutes les cuves ? [o/n] : " );
			capaSaisie = Clavier.lire_char();
		}

		if ( Character.toUpperCase( capaSaisie ) == 'O' )
		{
			System.out.print( "Indiquer la capacité des cuves : " );
			capaCuve = Clavier.lire_int();
			
			while ( capaCuve > 1000 || capaCuve < 200 )
			{
				System.out.print( "Incorrecte : Indiquer la capacité des cuves ( 200 - 1000 ):  " );
				capaCuve = Clavier.lire_int();
			}

			for( int i = 0; i < nbCuve; i++ )
			{
				if(i%2 == 0 )
					this.alCuve.add(Cuve.creerCuve( capaCuve, (i * 200) + 500, 100, 'H' ));
				else
					this.alCuve.add(Cuve.creerCuve( capaCuve, (i * 200) + 500, 400, 'B' ));
			}
		}
		else
		{
			for( int i = 0; i < nbCuve; i++ )
			{
				System.out.print( "Indiquer la capacité de la cuve numéro " + (char)(i + (int)('A')) + " : " );
				capaCuve = Clavier.lire_int();

				while ( capaCuve > 1000 || capaCuve < 200 )
				{
					System.out.print( "Incorrecte : Indiquer la capacité de la cuve ( 200 - 1000 ):  " );
					capaCuve = Clavier.lire_int();
				}

				if(i%2 == 0 )
					this.alCuve.add(Cuve.creerCuve( capaCuve, (i * 200) + 250, 100, 'H' ));
				else
					this.alCuve.add(Cuve.creerCuve( capaCuve, (i * 200) + 250, 400, 'B' ));

			}
		}
		
		
		sret += "Quel type de structure voulez-vous?\n"   ;
		sret += "- Liste d'adjacence            ( lst )\n";
		sret += "- Matrice de cout              (  mc )\n";
		sret += "- Matrice de cout optimisée    ( mco )\n";
		
		do
		{
			System.out.print( sret + "\n" );
			this.structure = Clavier.lireString().toUpperCase();

		}while( !this.structure.equals( "LST" ) && !this.structure.equals( "MC" ) && !this.structure.equals( "MCO" ) );
		
		// Interface CUI

		while( Character.toUpperCase( instru ) != 'Q' )
		{
			System.out       .println     ( this.menu() );
			instru = Clavier .lire_char   (             );
			instru = Character.toUpperCase( instru      );
			
			switch( instru ) 
			{
				case 'T' -> this.creerLiaison               (                     );
				case 'A' -> this.ajouterCuve                (                     );
				case 'V' -> System.out.println              ( this.etatActuel ( ) );        
				case 'C' -> this.ajouterContenu             (                     );
				case 'M' -> {
								if( this.structure.equals   ( "MC"  ) )  
									System.out.println      ( this.afficherMatriceCUI ( this.matrice ) );
							}
				case 'L' -> {
								if( this.structure.equals   ( "LST" ) ) 
									System.out.println      ( this.afficherListeAdjacence( this.matrice ) );
							}
				case 'O' -> {
								if( this.structure.equals   ( "MCO" )) 
									System.out.println      ( this.afficherMatriceOpti( this.matrice ) );
							}
			}
		}
		this.genererFichier( );
	}
	
	public boolean creerLiaison()
	{
		if ( this.alTube.size() == this.NB_TUBE_MAX )
        {
            System.out.println( "Nombre de tube maximum atteint" );
            return false;
        }
		char    prc, svt     ;
		int     capa         ;
		Cuve    cuveE = null ;
		Cuve    cuveS = null ;
		boolean test  = false;
		

		
		if ( this.alCuve.size() == 0 )
		{
			System.out.println( "Erreur : il n'y a aucune cuve" );
			return false;
		}

		System.out     .print       ( "Cuve de départ :  " );
		prc = Clavier  .lire_char   ();
		prc = Character.toUpperCase (prc);

		
		while ( test == false )
		{
			for( int i = 0; i < this.alCuve.size(); i++ )
				if ( prc == this.alCuve.get(i).getId() ) { test = true; }
			if ( test == false )
			{
				System.out     .println    ( "Erreur : cette cuve n'existe pas, indiquez une cuve : " );
				prc = Clavier  .lire_char  (                                                          );
				prc = Character.toUpperCase( prc                                                      );
			}
		}

		for ( int i = 0; i < this.alCuve.size(); i++ )
		{
			if( this.alCuve.get(i).getId( ) == prc )
			{
				cuveE  = this.alCuve.get(i);
			}
		}
			
		System.out     .print       ( "Cuve d'arrivée :  ");
		svt = Clavier  .lire_char   ();
		svt = Character.toUpperCase (svt);

		test = false;
		while ( test == false )
		{
			for( int i = 0; i < this.alCuve.size(); i++ )
			{
				if ( svt == this.alCuve.get(i).getId() ) 
				{
					test = true; 
				}
			}
			if ( test == false )
			{
				System.out     .println    ( "Erreur : cette cuve n'existe pas, indiquez une cuve : " );
				svt = Clavier  .lire_char  (                                                          );
				svt = Character.toUpperCase( svt                                                      );
			}
		}

		for( int i = 0; i < this.alCuve.size(); i++ ) 
		{
			if( this.alCuve.get(i).getId( ) == svt  ) 
			{
				cuveS  = this.alCuve.get(i);
			}
		}

		for( Tube tube : this.alTube )
		{
			if( tube == null )
				break;
			if( tube.getEntree( ).getId( ) == prc && tube.getSortie( ).getId( ) == svt )
			{
				System.out.println( "Erreur : Cette liaison est déjà faites \n" );
				return false;
			}
				
		}
		
		System.out.print( "Quelle est sa section :   " );
		capa = Clavier.lire_int( );
		
		if( capa >=2 && capa <=10 )
		{
			this.alTube.add(Tube.creerTube( cuveE, cuveS, capa ));

			System.out.println( this.alTube.get(this.alTube.size() - 1 ));
		
			this.matrice [(int)(cuveE.getId())-(int)('A')][(int)(cuveS.getId())-(int)('A')] = capa;
			this.matrice [(int)(cuveS.getId())-(int)('A')][(int)(cuveE.getId())-(int)('A')] = capa;

			return true;
		}
		else
		{
			System.out.println( "Erreur : la section doit etre comprise entre 2 et 10\n" );
		}
		return false;
	}

	public boolean ajouterCuve()
	{
		int  x, y   ;
		char posInfo;
		
		if ( this.alCuve.size() == this.NB_CUVE_MAX )
		{
			System.out.println( "Nombre de cuve maximum atteint" );
			return false;
		}

		System.out        .println ( "Entrez une capacité : " );
		int capa = Clavier.lire_int(                          );
		
		while ( capa > 1000 || capa < 200 )
		{
			System.out    .print   ( "Incorrecte : Indiquez une capacité entre 200 et 1000 :  " );
			capa = Clavier.lire_int(                                                            );
		}

		System.out       .print    ( "Quelle est sa position en x : "                               );
		x = Clavier      .lire_int (                                                                );

		System.out       .print    ( "Quelle est sa position en y : "                               );
		y = Clavier      .lire_int (                                                                );

		System.out       .print    ( "Entrer où mettre le texte lors (répondre par: H, B, G, D) : " );
		posInfo = Clavier.lire_char(                                                                );

		while ( posInfo != 'H' && posInfo != 'B' && posInfo != 'D' && posInfo != 'G')
		{
			System.out       .print    ( "Entrer où mettre le texte lors (répondre par: H, B, G, D) : " );
			posInfo = Clavier.lire_char(                                                                );
		}

		this.alCuve.add(Cuve.creerCuve( capa , x, y, posInfo ));
		this.genererFichier( );

		return true;
	}

	public void ajouterContenu()
	{
		char  charCuve   ;
		Cuve  cuve = null;
		float quantite   ;

		System.out          .println    ( "A quel cuve voulez-vous ajouter du contenu ?" );
		charCuve = Clavier  .lire_char  (                                                );

		charCuve = Character.toUpperCase( charCuve                                       );

		for ( int i = 0; i < this.alCuve.size(); i++ ) 
		{
			if( this.alCuve.get(i).getId() == charCuve )
			{
				cuve  = this.alCuve.get(i);
			}
		}
		if(cuve == null) 
		{
			System.out.println ( "Cette cuve n'existe pas" );
			return                                          ;
			
		}

		System.out        .println     ( "Quelle quantité voulez-vous ajouter ?" );
		quantite = Clavier.lireFloat   (                                         );

		while( quantite > cuve.getCapacite( ) - cuve.getContenu( ) || quantite <= 0 ) 
		{
			if ( quantite <= 0 ) 
			{
				System.out.println ( "Entrez une quantité positive non nulle" );
			}
			else
			{
			System.out.println  ( "Entrez une capacité inférieur à : " 
								+ String.format( "%.2f", cuve.getCapacite()-cuve.getContenu() ) );
			}
			quantite = Clavier.lireFloat();
		}

		cuve.setContenu( quantite );
		
	}

	public String etatActuel( )
	{
		String ret = "";
		
		for( Cuve cuve : this.alCuve ) 
		{
			if( cuve == null )
				ret += ""         ;
			else
				ret +=  cuve.toString()+"\n" ;
		}
		for( Tube tube : this.alTube )
		{
			if( tube == null )
				ret += "";
			else
				ret += "\n" + tube.toString();
		}
		return ret;
	}
	public String afficherCuve( )
	{
		String ret = "";
		
		ret = this.alCuve.get(0) + "";

		for( int i = 1; i < this.alCuve.size(); i++ )
		{
			if( this.alCuve.get(i) == null )
				ret += "";
			else
				ret += "\n" + this.alCuve.get(i).toString();
		}
		return ret;
	}
	public String afficherListeAdjacence( int[][] matrice )
	{
		String res= ""  ;

		for( int i = 0; i < matrice.length; i++ )
		{
			res += (char)(i + (int)('A')) + "\t";
			for( int j = 0; j < matrice[0].length; j++ )
			{
				if (0 != matrice[i][j] )
				{
					res += (char)(j+(int)('A')) + ":" ;
					res += matrice[i][j]        + "\t";
				}
			}
			res += "\n";
		}
		return res;
	}

	public String afficherMatrice(int[][] matrice)
	{
		String res = "";

		for( int i = 0; i < matrice.length; i++ )
		{
			for( int j = 0; j < matrice[0].length; j++ )
			{
				res += matrice[i][j] + "\t";
			}
			res += "\n";
		}
		return res;
	}

	public String afficherMatriceOpti(int[][] matrice)
	{
		String res = "";

		for( int i = 0; i < matrice.length; i++ )
		{
			for( int j = 0; j < matrice[0].length; j++ )
			{
				if ( i == j || j < i )
				{
					res += "\t";
				}
				else 
				{
					res += matrice[i][j] + "\t";
				}
				
			}
			res += "\n";
		}
		return res;
	}

	public String afficherMatriceCUI( int[][] matrice )
	{
	
		int    i   = 0 ;
		String res = "";



		for( int j = 0; j < matrice[0].length; j++ )
		{
			res += "    " + (char)('A' + j);
		}

		res += "\n" ;
		res += "   ";
		i=0;
		for(int j = 0; j < matrice[0].length; j++ )
		{
			res += "+----";
		}

		res += "+" ;
		res += "\n";

		for( i = 0; i < matrice.length; i++)
		{
			res += " " + (char)('A' + i) + " |";

			for( int j = 0; j < matrice[0].length; j++ )
			{
				res += " " + String.format( "%2d", matrice[i][j] ) + " |";  
			}

			res += "\n" ;
			res += "   ";
		
			for(int k = 0; k < matrice.length; k++)
			{
				res += "+----";
			}
				res += "+"    ;
				res += "\n"   ;
		}
		return res;
	}

	public String menu()
	{
		switch( this.structure ) 
		{
			case "LST" ->{
							return "\n[T] Créer une liaison avec un tube \n"    +
								   "[A] Ajouter une nouvelle cuve \n"           +
								   "[V] Voir l'état actuel\n"                   +
								   "[C] Ajouter du contenu a une cuve\n"        +
								   "[L] Afficher la liste d'adjacence\n"        +
								   "[Q] Quitter";
						 }

			case "MC" -> {
							return "\n[T] Créer une liaison avec un tube \n"    +
								   "[V] Voir l'état actuel\n"                   +
								   "[A] Ajouter une nouvelle cuve \n"           +
								   "[M] Afficher la matrice de cout  \n"        +
								   "[C] Ajouter du contenu a une cuve\n"        +
								   "[Q] Quitter";
						 }

			case "MCO"-> {
							return "\n[T] Créer une liaison avec un tube \n"    +
								   "[A] Ajouter une nouvelle cuve \n"           +
								   "[V] Voir l'état actuel\n"                   +
								   "[C] Ajouter du contenu a une cuve\n"        +
								   "[O] Afficher la matrice de cout optimisé\n" +
								   "[Q] Quitter";
						 }
		}
		return "erreur";
	}
	public void genererFichier()
	{
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter ( new File("../../appli2/data/text.txt" ), "utf-8" ); 
		}
		catch( IOException e ){}

		pw.println( "NbCuve\t"    + this.alCuve.size()   );
		pw.println( this.afficherCuve()           );
		pw.println( "Structure\t" + this.structure);

		if (this.structure.equals( "LST" ) )
			pw.println( this.afficherListeAdjacence( this.matrice  ) );
		
		if( this.structure.equals( "MC" ) )
			pw.println( this.afficherMatrice( this.matrice ) );
		
		if( this.structure.equals( "MCO" ) )
			pw.println( this.afficherMatriceOpti( this.matrice ) );
		
		pw.close( );
	}

	public ArrayList<Cuve> getAlCuves() { return this.alCuve;         }
	public ArrayList<Tube> getAlTubes() { return this.alTube;         }
	public int    getNbCuve()           { return this.alCuve.size();  }

	public static void main(String[] args) 
	{
		new Application().lancerProgramme();
	}
}