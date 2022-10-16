import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;

public class PanelStop extends JPanel implements ActionListener
{
	private Controleur      ctrl;
	private FramePrincipale fci;
    private JButton         btnStop;


	public PanelStop(Controleur   ctrl, FramePrincipale fci)
	{
		this.fci  = fci;
		this.ctrl = ctrl;

		this.setSize ( 500, 250 );
		this.setLayout(new GridLayout());
        this.btnStop = new JButton("STOP");

        this.btnStop.addActionListener(this);

        this.add(this.btnStop);
    }

    public void actionPerformed ( ActionEvent e )    
	{
        if(e.getSource() == this.btnStop)
            System.exit(0);
    }
}