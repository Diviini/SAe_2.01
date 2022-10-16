
//Application 1 GUI

import iut.algo.Clavier   ;
import java.lang.Character;
import java.io.IOException;

import java.io.PrintWriter;
import java.io.File       ;
import java.util.ArrayList;

public class Application 
{
	final int NB_CUVE_MAX = 26 ;
	final int NB_TUBE_MAX = 325; // car le nombre de tube maximum possible avec 26 cuve est est égal 325
	
	private int [][]   matrice;

	private ArrayList<Cuve> alCuve;
	private ArrayList<Tube> alTube;

	private int nbCuve;
	private int nbTube;

	private String structure;

	public Application() 
	{

		// this.matrice = null;				

		this.alCuve   = new ArrayList<>();
		this.alTube   = new ArrayList<>();

		this.nbCuve = 0;
		this.nbTube = 0;
	}

	public void setNbCuves ( int nb )		   
	{
		this.nbCuve = nb;
		this.matrice = new int[this.nbCuve][this.nbCuve];
	}
	public void setStructure( String matrice ) { this.structure = matrice ;}

	public Cuve creerCuve( int capacite, int posX, int posY, char posInfo )
	{
		this.alCuve.add(Cuve.creerCuve( capacite, posX, posY, posInfo ));
		return this.alCuve.get(this.alCuve.size() - 1);
	}

	public boolean creerLiaison(char pPrc, char pSvt, int pCapa)
	{
		char    prc, svt     ;
		int     capa         ;
		Cuve    cuveE = null ;
		Cuve    cuveS = null ;
		boolean test  = false;

		prc = pPrc;
		prc = Character.toUpperCase ( prc );	

		for( int i = 0; i < this.nbCuve; i++ )
		{
			if ( prc == this.alCuve.get(i).getId() )
			{
				test  = true;
				cuveE = this.alCuve.get(i);
			}
		}
		if ( test == false )
		{
			return test;
		}

		svt = pSvt;
		svt = Character.toUpperCase ( svt );
		test = false;
		
		for( int i = 0; i < this.alCuve.size(); i++ )
		{
			if ( svt == this.alCuve.get(i).getId() ) 
			{
				test  = true; 
				cuveS = this.alCuve.get(i);
			}
		}

		if ( test == false )
		{
			return test;
		}

		for( Tube tube : this.alTube )
		{
			if( tube == null )
				break;
			if( tube.getEntree( ).getId( ) == svt && tube.getSortie( ).getId( ) == prc || tube.getEntree( ).getId( ) == prc && tube.getSortie( ).getId( ) == svt )
			{
				return false;
			}
		}

		if( svt == prc )
		{
			return false;
		}

		capa = pCapa;
		
		this.alTube.add(Tube.creerTube( cuveE, cuveS, capa ));
	
		this.matrice            [(int)(cuveE.getId())-(int)('A')][(int)(cuveS.getId())-(int)('A')] = capa;
		this.matrice            [(int)(cuveS.getId())-(int)('A')][(int)(cuveE.getId())-(int)('A')] = capa;
		
		return true;
	
	}
	public void genererFichier()
	{
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter ( new File("../../appli2/data/text.txt" ), "utf-8" ); 
		}
		catch( IOException e ){}

		pw.println( "NbCuve\t" + this.alCuve.size());
		pw.println( this.afficherCuve()            );
		pw.println( "Structure\t" + this.structure );

		pw.println( this.afficherMatrice( this.structure ) );
		pw.close( );
	}

	public String afficherListeAdjacence( int[][] matrice )
	{
		String res = "";

		for( int i = 0; i < this.alCuve.size(); i++ )
		{
			res += (char)(i + (int)('A')) + "\t";
			for(int j=0 ; j< this.alCuve.size() ; j++)
			{
				if (0 != matrice[i][j] )
				{
					res += (char)(j + (int)('A')) + ":";
					res += matrice[i][j] + "\t";
				}
			}
			res += "\n";
		}
		return res;
	}

	public String afficherCuve()
	{
		if(this.alCuve.isEmpty()) return "";

		String ret = "";		
		ret = this.alCuve.get(0) + "";

		for(int i=1 ; i<this.alCuve.size() ; i++)
		{
			if( this.alCuve.get(i) == null )
				ret += "";
			else
				ret +=  "\n" + this.alCuve.get(i).toString();
		}
		return ret;
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

	public String afficherMatrice( String typeStructure )
	{
		if(typeStructure == null) return "";
		String  res     = "";

		if( typeStructure.equals( "LST" ) )
		{
			return this.afficherListeAdjacence( this.matrice );
		}
		
		if( typeStructure.equals( "MCO" ) )
		{
			return this.afficherMatriceOpti( this.matrice );
		}	

		// typeStructure.equals( "MC" )
		
		for( int i = 0; i < this.matrice.length; i++ )
		{
			for( int j = 0; j < this.matrice[0].length; j++ )
			{
				res += this.matrice[i][j] + "\t";
			}
			res += "\n";
		}
		return res;
	}

	public ArrayList<Tube> getAlTube() { return this.alTube; }
	public ArrayList<Cuve> getAlCuve() { return this.alCuve; }

	public int getNbCuve()  { return this.nbCuve ; }
	public int getNbTube()  { return this.nbTube ; }

	public void setNbTubes(int nb) { this.nbTube = nb; }

	public static void main( String[] args ) 
	{
		new Application();
	}
}