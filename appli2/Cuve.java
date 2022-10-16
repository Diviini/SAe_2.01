public class Cuve
{
    private static char nbId = 'A';

    private char    id       ;
    private char    posInfo  ;
    private int     capacite ;
    private float   contenu  ;
    private int     x, y     ;

    private Cuve( int capacite, int x, int y, char posInfo )
    {
        this.id          = Cuve.nbId++  ;
        this.capacite    = capacite     ;
        this.contenu     = 0            ;
        this.x           = x            ;
        this.y           = y            ;
        this.posInfo     = posInfo      ;   
    }

    public static Cuve creerCuve( int capacite, int x, int y, char posInfo )
    {
        
        if( Cuve.nbId == 'Z' ) 
        {
            System.out.println( "Limite de cuves atteintes" );
            return null;
        }
        if( capacite > 1000 || capacite < 200 ) 
        {
            System.out.println( "Capacité incorrecte"       );
            return null;
        }
        return new Cuve( capacite, x, y, posInfo );
    }

    public float setContenu(float contenu)
    {
        this.contenu += contenu;
        return this.contenu    ;
    }

    public          int    getCapacite()  { return this.capacite          ;}

    public          float  getContenu ()  { return this.contenu           ;}
    
    public          char   getId      ()  { return this.id                ;}

    public static   char   getNbId    ()  { return Cuve.nbId              ;}

    public static   int    getNumID   ()  { return (int) Cuve.getNbId()   ;}
    
    public          int    getY       ()  { return this.y                 ;}
    
    public          int    getX       ()  { return this.x                 ;}

    public          char   getPosInfo ()  { return this.posInfo           ;}

    public          String toString   ()
    {
        String sRet = "";
        
        sRet += "\n ID : ";
        sRet += String.format( "%-10s", this.id         );

        sRet += "\n posInfo : ";
        sRet += String.format( "%-10s", this.posInfo    );

        sRet += "\n capacite : ";
        sRet += String.format( "%-10s", this.capacite   );

        sRet += "\n contenu : ";
        sRet += String.format( "%-10s", this.contenu    );

        sRet += "\n x : ";
        sRet += String.format( "%-10s", this.x          );

        sRet += "\n y : ";
        sRet += String.format( "%-10s", this.y          );

        sRet += "\n";

        return sRet;
    } 
}
