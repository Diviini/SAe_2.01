import java.awt.*;
import javax.swing.*;

import java.io.FileReader;
import java.util.Scanner;

public class FrameMatrice extends JFrame 
{
	private Controleur  ctrl;
	private JTextArea   txtPrc    ;

	private JPanel       panel     ;

	public FrameMatrice( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setTitle   ("Matrice "    );
		this.setSize    ( 500, 250           );
		this.setLocation( 750, 200           );
		this.setLayout  ( new BorderLayout() );

		this.panel      = new JPanel   ();
		this.txtPrc     = new JTextArea();
		this.txtPrc.setEditable(false);

		FileReader frEdit;
		Scanner scanEdit;
		String sret="";

		try
		{
			//Lecture du fichier editeur.data

			frEdit   = new FileReader ( "../../appli2/data/text.txt" );
			scanEdit = new Scanner ( frEdit );
			
			while ( scanEdit.hasNextLine() )
			{
				String test = scanEdit.nextLine();
				
				if( test.equals("Structure	MC") || test.equals("Structure	LST") || test.equals("Structure	MCO"))
					break;
			}

			while(scanEdit.hasNextLine())
				sret+= scanEdit.nextLine()+"\n";

			scanEdit.close();
		}
		catch (Exception e){ e.printStackTrace(); } 

		this.txtPrc.append(sret);

		this.panel.add( this.txtPrc     );
		this      .add( this.panel      );

		this.pack();

		this.setVisible(true);
	}  
}