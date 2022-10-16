import java.awt.*;
import javax.swing.*;

public class FrameCuveInfo extends JFrame 
{
	private Controleur    ctrl;

    private PanelCuveInfo panel     ;

	public FrameCuveInfo( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setTitle   ("Cuve "    );
		this.setSize    ( 500, 250           );
        this.setLocation( 760, 100            );
        this.setLayout  ( new BorderLayout() );

        this.panel      = new PanelCuveInfo   (this.ctrl, this);


        this.add( this.panel  );
        this.setVisible(true);
    }
    
    public void close()
	{
		this.setVisible ( false );
	}  
}