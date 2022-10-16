import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;


public class FramePrincipale extends JFrame 
{

	private Controleur ctrl        ;

	private PanelAction  panelAction;
	private PanelForm    panelForm;
	private PanelStop    panelStop;


	public FramePrincipale( Controleur ctrl ) 
	{

		this.setTitle( "Accueil" );
		this.setSize ( 700,320           );
		this.setLocation(1,100);
		this.setLayout(new GridLayout(4,1));
		this.ctrl        = ctrl ;

		JLabel label = new JLabel("Accueil");
		Font   font  = new Font("bold",Font.BOLD,50);
		JPanel p     = new JPanel();

		this.panelAction  = new PanelAction( this.ctrl );
		this.panelForm    = new PanelForm(this.ctrl);
		this.panelStop    = new PanelStop(this.ctrl,this);

		label.setFont(font);
		p.add(label) ; 

		this.add                     ( p);
		this.add                     ( this.panelForm);
		this.add                     ( this.panelAction );
		this.add                     ( this.panelStop, BorderLayout.SOUTH );
		
		this.setVisible              ( true                                );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE                );

	}

	public void boutonEnable()
	{
		this.panelAction.boutonEnable();
	}
	
	public void boutonEnableStructure()
    {
        this.panelAction.boutonEnableStructure();
    }

	public void fermerFrame() 
	{
		this.dispose();
	}
}
