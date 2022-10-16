import java.awt.*;
import javax.swing.*;


public class FrameLiaison extends JFrame 
{
    private Controleur   ctrl      ;
    private PanelLiaison panelLiaison;

	public FrameLiaison( Controleur ctrl )
	{
		this.ctrl = ctrl                      ;
		this.setTitle   ("Creer liaison "    );
		this.setSize    ( 600, 250           );
        this.setLocation( 1, 450             );
        this.setLayout  ( new BorderLayout() );

        this.panelLiaison = new PanelLiaison(this.ctrl,this);

        this.add(this.panelLiaison);

        this.setVisible( true );
    }

    public void close()
	{
		this.setVisible ( false );
	}         
}