public class Tube 
{

	private Cuve entree;
	private Cuve sortie;
	private int section;

	private Tube( Cuve entree, Cuve sortie, int section )
	{
		this.entree  = entree;
		this.sortie  = sortie;
		this.section = section;
	}

	private Tube( int section )
	{
		this( null, null, section );
	}

	public static Tube creerTube(Cuve e, Cuve s, int section)
	{
		if(section >=2 && section <=10)
			return new Tube(e, s, section);
		else 
			return null;
	}
	
	public boolean setEntree( Cuve cuve )
	{
		if( cuve == null ) return false;
		
		this.entree = cuve;
		return true;
	}

	public boolean setSortie( Cuve cuve )
	{
		if( cuve == null ) return false;

		this.sortie = cuve;
		return true;
	}
	
	public Cuve getEntree () { return this.entree ; }

	public Cuve getSortie () { return this.sortie ; }

	public int  getSection() { return this.section; }

	public String toString()
	{
		String sRet = "";
		
		sRet += " Tube :  "  ;
		sRet += " Entree : " ;
		sRet += String.format( "%-10s", this.entree.getId() );

		sRet += " Sortie : " ;
		sRet += String.format( "%-10s", this.sortie.getId() );

		sRet += " Section : ";
		sRet += String.format( "%-10s", this.section        );
		sRet += "\n";

		return sRet ; 
	}
}