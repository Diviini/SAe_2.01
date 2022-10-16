import javax.swing.JFrame;
import java.awt.*;

public class FrameReseau extends JFrame 
{
	private Controleur2   ctrl;
	private PanelVisuel  panelVisuel;
	private PanelValider panelValider;

	public FrameReseau( Controleur2 ctrl ) 
	{
		this.setTitle( "Réseau de cuves" );
		this.setSize ( 1800,1000         );

		this.ctrl = ctrl;

		this.panelVisuel  = new PanelVisuel( this.ctrl );
		this.panelValider = new PanelValider(this.ctrl);

		this.add( this.panelVisuel);
		this.add(this.panelValider, BorderLayout.SOUTH);

		this.setVisible( true );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	public void majIHM() {this.panelVisuel.majIHM();}
	
}
