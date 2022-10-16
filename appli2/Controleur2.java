import java.util.ArrayList;

public class Controleur2
{
	private FrameReseau   ihm;
	private Application metier;

	public Controleur2 ()
	{
		this.metier = new Application( this );
		this.ihm    = new FrameReseau  ( this );
	}

	public ArrayList<Tube> getAlTube()   { return this.metier.getAlTube();  }
	public ArrayList<Cuve> getAlCuves()  { return this.metier.getAlCuve();  }

	public void iterationSuivante()      { this.metier.iterationSuivante(); }
	public void majIHM()                 { this.ihm.majIHM();               }
	

	public static void main( String[] a ) 	
	{
		new Controleur2(); 
	}
}