import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class PanelForm extends JPanel implements ActionListener
{
	private Controleur  ctrl;
    private JLabel      nbCuve;
    private JTextField  txtNbCuve;
    private JButton     btnValider;
    private JPanel      panel1;

	public PanelForm(Controleur   ctrl)
	{
	
        this.ctrl=ctrl;
		this.setSize ( 500, 250 );
        this.setLayout(new BorderLayout()); 

        this.panel1     = new JPanel();

        this.txtNbCuve  = new JTextField(2);
        this.btnValider = new JButton("Valider");


        this.btnValider.addActionListener(this);
        this.panel1.setLayout(new FlowLayout());

        this.panel1.add(new JLabel("Nombres de cuves :"));
        this.panel1.add(this.txtNbCuve);
        this.panel1.add(this.btnValider);
       
        this.add(panel1);   
    }

    public void actionPerformed( ActionEvent e ) 
    {
        
		if( e.getSource() == this.btnValider    )
		{
            if(verifNbCuves(this.txtNbCuve.getText()))
            {
                this.ctrl.setNbCuves(this.getNbCuves());
                
                this.txtNbCuve.setEditable(false);
                this.btnValider.setEnabled(false);

			    FrameCuveInfo frameCuveInfo = new FrameCuveInfo( this.ctrl );
            }
            else
            {
				JOptionPane.showMessageDialog(null,"Nombres de cuves doit être compris entre 1 et 26","Erreur",JOptionPane.ERROR_MESSAGE);
            }  
		}
        this.ctrl.genererFichier();
    }

    public int getNbCuves() 
    {
        return Integer.parseInt(this.txtNbCuve.getText());
    }

    public boolean verifNbCuves(String nb)
    {

        int capacite = 0;
		try{
			capacite = Integer.parseInt(nb);
		}catch(Exception e){return false;}

        if( capacite > 26 || capacite <= 0 ) 
			return false;
		else
			return true;
    }
}