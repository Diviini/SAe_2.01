import java.util.ArrayList;

public class Controleur 
{
	private FramePrincipale  ihm;
	private Application1GUI metier;


	public Controleur ()
	{
		this.ihm    = new FramePrincipale ( this );
		this.metier = new Application1GUI();
	}

	public Cuve creerCuve(int capacite, int posX, int posY, char posInfo)
	{
		return this.metier.creerCuve(capacite, posX, posY, posInfo);
	}

	public void fermerFrame()           { this.ihm.fermerFrame();           }
	public void boutonEnable()          { this.ihm.boutonEnable();          }
	public void boutonEnableStructure() { this.ihm.boutonEnableStructure(); }
	public void genererFichier()        {  this.metier.genererFichier();    }

	public String afficherMatrice(String typeStructure) { return this.metier.afficherMatrice(typeStructure); }

	public ArrayList<Cuve> getAlCuve() { return this.metier.getAlCuve(); }
	public ArrayList<Tube> getAlTube() { return this.metier.getAlTube(); }

	public int getNbCuve() { return this.metier.getNbCuve(); }
 	public int getNbTube() { return this.metier.getNbTube(); }

	public void setNbCuves(int nb)             { this.metier.setNbCuves(nb);        }
	public void setNbTubes(int nb)             { this.metier.setNbTubes(nb);        }
	public void setStructure ( String matrice) { this.metier.setStructure(matrice); }

	public boolean creerLiaison(char cuveE, char cuveS, int capa) { return this.metier.creerLiaison(cuveE,cuveS,capa); }

	public static void main(String[] a)
	{
		new Controleur();
	}
}