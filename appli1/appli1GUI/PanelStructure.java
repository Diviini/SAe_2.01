import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelStructure extends JPanel implements ActionListener
{
	private Controleur   ctrl      ;
	private FrameStructure frm;

	private JPanel       p;
	private JButton      btnListe,btnMatrice,btnMatriceOpti;
	private int          tubeTraiter = 0;



	public PanelStructure( Controleur ctrl,FrameStructure frm)
	{  
		this.ctrl = ctrl; 
		this.frm = frm;
		
		this.setLayout  ( new BorderLayout() );

		this.btnListe       = new JButton("Liste d'adjacence");
		this.btnMatrice     = new JButton("Matrice de cout ");
		this.btnMatriceOpti = new JButton("Matrice de cout optimisé");

		this.p              = new JPanel();
		this.p.setLayout(new GridLayout(3,1));

		this.p.add( this.btnListe     );

		this.p.add( this.btnMatrice     );

		this.p.add(this.btnMatriceOpti);
		this.add(this.p);

		this.btnListe      .addActionListener(this);
		this.btnMatrice    .addActionListener(this);
		this.btnMatriceOpti.addActionListener(this);

	}

	public void actionPerformed ( ActionEvent e )    
	{
		if ( e.getSource() == this.btnMatrice)
		{
			this.ctrl.setStructure("MC");
			this.ctrl.genererFichier();
			this.frm.fermerFrame();
		}
		if ( e.getSource() == this.btnMatriceOpti)
		{
			this.ctrl.setStructure("MCO");
			this.ctrl.genererFichier();
			this.frm.fermerFrame();
		}
		if ( e.getSource() == this.btnListe)
		{
		   	this.ctrl.setStructure("LST");
			this.ctrl.genererFichier();
			this.frm.fermerFrame();
		}
	}
}