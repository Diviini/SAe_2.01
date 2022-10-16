import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelAction extends JPanel implements ActionListener 
{
	
	private Controleur ctrl              ;

	private JPanel     panelInfo         ;
	private JPanel     panelStats        ; 

	private JTextField txtCreerLiaison   ; 
	private JButton    btnCreerLiaison   ;
	private JButton    btnAfficherMatrice;
	private JButton    btnStructure;
	

	public PanelAction( Controleur ctrl ) 
	{

		this.ctrl = ctrl;
		this.setLayout( new GridLayout(2,1)  );

		this.panelInfo              = new JPanel    (                    );
		this.panelStats             = new JPanel    (                    );

		this.btnCreerLiaison        = new JButton   ( "Creer liaison"    );
		this.btnAfficherMatrice     = new JButton   ( "Afficher matrice" );
		this.btnStructure           = new JButton   (" Choix structure"  );

		this.txtCreerLiaison        = new  JTextField(2);

		this.btnCreerLiaison.setEnabled(false);
		this.btnStructure.setEnabled(false);
		
		this.panelInfo.setLayout( new FlowLayout());
 
		this.panelInfo.add(new JLabel("Nombres de liaison : "));
		this.panelInfo.add( this.txtCreerLiaison              );
		this.panelInfo.add( this.btnCreerLiaison              );
		this.panelInfo.add(this.btnStructure                  );
		this.panelInfo.add( this.btnAfficherMatrice           );


		this.btnCreerLiaison   .addActionListener( this );
		this.btnAfficherMatrice.addActionListener( this );
		this.txtCreerLiaison   .addActionListener(this);
		this.btnStructure      .addActionListener(this);


		this.add( this.panelInfo  );
		this.add( this.panelStats );
	}

	public void boutonEnable()
	{
		this.btnCreerLiaison.setEnabled(true);
	}

	public void boutonEnableStructure()
    {
        this.btnStructure.setEnabled(true);
    }

	@Override
	public void actionPerformed( ActionEvent e ) 
	{      
		if( e.getSource() == this.btnCreerLiaison   )
		{
			if(verifNbLiaison(this.txtCreerLiaison.getText()) && Integer.parseInt(this.txtCreerLiaison.getText()) != 0)
			{
				this.ctrl.setNbTubes(this.getNbTube());

				FrameLiaison frameLiaison = new FrameLiaison( this.ctrl );
				this.btnCreerLiaison.setEnabled(false);

			}
			else
			{
				JOptionPane.showMessageDialog(null,"Entrer un nombre différent de 0 ","Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}

		if(e.getSource() == this.btnStructure)
		{
			FrameStructure frameStructure = new FrameStructure(this.ctrl);
		}

		if( e.getSource() == this.btnAfficherMatrice )
		{
		    FrameMatrice frameMatrice = new FrameMatrice( this.ctrl );
		}	
		this.ctrl.genererFichier();
	}

	public int getNbTube()
	{
		return Integer.parseInt(this.txtCreerLiaison.getText());
	}

	public boolean verifNbLiaison(String nb)
	{
		int capacite = 0;
		try{
			capacite = Integer.parseInt(nb);
			return true;
		}catch(Exception e){return false;}
	}
}