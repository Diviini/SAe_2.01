import java.awt.*;
import javax.swing.*;


public class FrameStructure extends JFrame 
{

	private Controleur      ctrl;

	private PanelStructure  panelStructure;

	public FrameStructure( Controleur ctrl ) 
	{  

		this.setTitle( "Choix structure" );
		this.setSize ( 400,400           );
		this.setLocation(760,500);
		this.setLayout(new GridLayout(3,1));
		this.ctrl= ctrl;

		this.panelStructure  = new PanelStructure( this.ctrl,this );

		this.setLayout               ( new BorderLayout()  );

		this.add                     ( this.panelStructure );
		this.setVisible              ( true                );
	}
	
	public void fermerFrame() 
	{
		this.setVisible(false);
	}
}
