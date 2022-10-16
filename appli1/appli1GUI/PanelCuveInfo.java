import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;

public class PanelCuveInfo extends JPanel implements ActionListener
{
	private Controleur    ctrl;
	private FrameCuveInfo fci;
	private JLabel        capacite,posX,posY,posInfo, contenu;
	private JTextField    txtCapacite, txtposX,txtposY,txtPosInfo, txtContenu;
	private JPanel        panel1,panel2,panel3,panel4,panel5,panel6;
	private JButton       btnValider;
	private int           nbCuveTraiter;


	public PanelCuveInfo(Controleur   ctrl, FrameCuveInfo fci)
	{
		this.fci = fci;
		this.ctrl=ctrl;
		this.setSize ( 500, 250 );
		this.setLayout(new GridLayout(6,1));
		this.nbCuveTraiter = 0;
		// Creation des composants
				


		this.capacite    = new JLabel("Capacite :");
		this.txtCapacite = new JTextField(10);

		this.posX        = new JLabel("Postion X :");
		this.txtposX     = new JTextField(10);

		this.posY        = new JLabel("Postion Y :");
		this.txtposY     = new JTextField(10);
	

		this.posInfo     = new JLabel("Position Info :");
		this.txtPosInfo  = new JTextField(5);

		this.contenu     = new JLabel("Contenu :");
		this.txtContenu  = new JTextField("00.00",10);

		this.btnValider = new JButton("Valider");

		this.panel1 = new JPanel();
		this.panel2 = new JPanel();
		this.panel3 = new JPanel();
		this.panel4 = new JPanel();
		this.panel5 = new JPanel();
		this.panel6 = new JPanel();

		this.panel1.setLayout(new FlowLayout());
		this.panel2.setLayout(new FlowLayout());
		this.panel3.setLayout(new FlowLayout());
		this.panel4.setLayout(new FlowLayout());
		this.panel5.setLayout(new FlowLayout());
		this.panel6.setLayout(new FlowLayout());

		this.btnValider.addActionListener(this);

		this.panel1.add(this.capacite);
		this.panel1.add(this.txtCapacite);

		this.panel2.add(this.posX);
		this.panel2.add(this.txtposX);

		this.panel3.add(this.posY);
		this.panel3.add(this.txtposY);

		this.panel4.add(this.posInfo);
		this.panel4.add(this.txtPosInfo);

		this.panel5.add(this.btnValider);

		this.panel6.add(this.contenu);
		this.panel6.add(this.txtContenu);

		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel6);
		this.add(panel5);	
	}

	public void actionPerformed( ActionEvent e ) 
	{
		
		if( e.getSource() == this.btnValider    )
		{
            if(this.txtCapacite.getText().isEmpty()|| this.txtposX.getText().isEmpty() || this.txtposY.getText().isEmpty() || this.txtPosInfo.getText().isEmpty() || this.txtContenu.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Remplissez tous les champs ","Erreur",JOptionPane.ERROR_MESSAGE);
                return;
            }
			if(verifCapa(this.txtCapacite.getText()) && verifCoord(this.txtposX.getText()) && verifCoord(this.txtposY.getText()) && verifPosInfo(Character.toUpperCase(this.txtPosInfo.getText().charAt(0))) && verifContenu(this.txtContenu.getText()) )
			{
				int capacite = Integer.parseInt(this.txtCapacite.getText());
				int posX     = Integer.parseInt(this.txtposX.getText());
				int posY     = Integer.parseInt(this.txtposY.getText());
				char posInfo = Character.toUpperCase(this.txtPosInfo.getText().charAt(0));

				float cont = Float.parseFloat(this.txtContenu.getText());
	
				Cuve cuve = this.ctrl.creerCuve(capacite,posX, posY, posInfo );
				cuve.setContenu(cont);
				this.nbCuveTraiter++;
		
				if(this.ctrl.getNbCuve() == this.nbCuveTraiter)
				{
					this.ctrl.boutonEnable();
					this.fci.close();
				}
				else
				{
					this.resetTxt();
				}
			}
			else
			{
				 JOptionPane.showMessageDialog(null,"Capacité doit être entre comprise 200 et 1000\nPosition info égal à H ou B ou D ou G \nLes coordonnées doivent être des nombres\nLa contenu doit être inférieur à la capacité","Erreur",JOptionPane.ERROR_MESSAGE);
			}	
		}
		this.ctrl.genererFichier();
	}

	public boolean verifCapa(String cap)
	{
		int capacite = 0;
		try{
			capacite = Integer.parseInt(cap);
		}catch(Exception e){return false;}

        if( capacite > 1000 || capacite < 200 ) 
			return false;
		else
			return true;
	}

	public boolean verifPosInfo(char info)
	{
        if(info != 'H' && info != 'B' && info != 'D' && info != 'G'  ) 
			return false;
		else
			return true;
	}

	public boolean verifCoord(String coord)
	{
		try{
			Integer.parseInt(coord);
			return true;
		}catch(Exception e){return false;}
	}

	public boolean verifContenu(String contenu)
	{
		try{
			Float.parseFloat(contenu);
		}catch(Exception e){return false;}

	    if( Integer.parseInt(this.txtCapacite.getText()) < Float.parseFloat(this.txtContenu.getText()) ) 
			return false;
		else
			return true;
	}

	public void resetTxt()
	{
		this.txtCapacite.setText("");
		this.txtposX.setText("");
		this.txtposY.setText("");
		this.txtPosInfo.setText("");
		this.txtContenu.setText("");
	}
}