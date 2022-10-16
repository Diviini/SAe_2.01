import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.*;

public class PanelValider extends JPanel implements ActionListener 
{
    private JButton btnValider;
    private Controleur ctrl;

    public PanelValider(Controleur ctrl)
    {
        this.setLocation(50, 50);
        this.setSize(250,250);
        
        this.ctrl = ctrl;
        this.btnValider = new JButton("Itération Suivante");
        this.btnValider.addActionListener(this);
        this.add(this.btnValider);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnValider)
            this.ctrl.majIHM();        
    }      
}