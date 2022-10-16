import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class PanelLiaison extends JPanel implements ActionListener
{
    private Controleur   ctrl      ;
    private FrameLiaison frm       ;

    private JTextField   txtPrc    ;
    private JTextField   txtSvt    ;
    private JTextField   txtSection;
    private JPanel       p;
    private JButton      btnValider;
    private int          tubeTraiter = 0;



	public PanelLiaison( Controleur ctrl, FrameLiaison frm )
	{  
        this.frm  = frm;
		this.ctrl = ctrl; 
		
        this.setLayout  ( new BorderLayout() );

        this.txtPrc     = new JTextField( 2 );
        this.txtSvt     = new JTextField( 2 );
        this.txtSection = new JTextField( 2 );
        this.btnValider = new JButton("Valider");

        this.p          = new JPanel();

        this.p.add(new JLabel("Cuve précédant : "));        
        this.p.add( this.txtPrc     );
        this.p.add(new JLabel("Cuve Suivant : "));
        this.p.add( this.txtSvt     );
        this.p.add(new JLabel("Section : "));
        this.p.add( this.txtSection );
        this.p.add(this.btnValider);
        this.add(this.p);

        btnValider.addActionListener(this);
    }

    public void actionPerformed ( ActionEvent e )    
	{
        if ( e.getSource() == this.btnValider )
        {  
            if(txtPrc.getText().isEmpty()|| txtSvt.getText().isEmpty() || this.txtSection.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Remplissez tous les champs   ","Erreur",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(Character.isLetter(txtPrc.getText().charAt(0)) && Character.isLetter(txtSvt.getText().charAt(0))  && verifSection(txtSection.getText()))
            {
                char cuveE,cuveS;
                int capa = Integer.parseInt(txtSection.getText());
                cuveE = txtPrc.getText().charAt(0);
                cuveS = txtSvt.getText().charAt(0);
                
                if(!this.ctrl.creerLiaison(cuveE,cuveS,capa))
                {
                    JOptionPane.showMessageDialog(null,"Liaison déja faites\nOu Cuve inexistante  ","Erreur",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                this.tubeTraiter++;
                
                if(this.ctrl.getNbTube() == this.tubeTraiter)
                {
                    this.ctrl.boutonEnableStructure();
                    this.frm.close();
                }
                else
                {
                    this.resetTxt();
                }
            }
			else
			{
				 JOptionPane.showMessageDialog(null,"Les cuves doivent être des caractères \nLa section doit être comprise entre 2 et 10","Erreur",JOptionPane.ERROR_MESSAGE);
			}
        }
        this.ctrl.genererFichier();
    }
	public boolean verifSection(String coord)
	{
		int capacite = 0;
		try{
			capacite = Integer.parseInt(coord);
		}catch(Exception e){return false;}

        if( capacite > 10 || capacite < 2 ) 
			return false;
		else
			return true;
	}
    public void resetTxt()
	{
		this.txtPrc.setText("");
		this.txtSvt.setText("");
        this.txtSection.setText("");
	}
}