import javax.swing.*;
import java.awt.*;

public class PanelVisuel extends JPanel
{
	private Controleur2 ctrl;
	private JPanel panelInfo;
	private JButton btnValider;

	public PanelVisuel( Controleur2   ctrl )
	{
		this.ctrl = ctrl;
		this.panelInfo    = new JPanel();
		this.btnValider   = new JButton("Passer une itération");

		this.add(this.panelInfo, BorderLayout.EAST);
		this.add(this.panelInfo, BorderLayout.SOUTH);

		this.setVisible(true);

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Color color;
		
		for( Tube tube : this.ctrl.getAlTube() )
		{
			g2d.setStroke(new BasicStroke(tube.getSection()));
			g2d.setColor( Color.GRAY );
			g2d.drawLine(	tube.getEntree().getX() + tube.getEntree().getCapacite()/10 /2, 
							tube.getEntree().getY() + tube.getEntree().getCapacite()/10 /2,
							tube.getSortie().getX() + tube.getSortie().getCapacite()/10 /2, 
							tube.getSortie().getY() + tube.getSortie().getCapacite()/10 /2);
		}

		g2d.setStroke(new BasicStroke(3));

		for(Cuve cuve : this.ctrl.getAlCuves())
		{
			color = Color.white;
			String information = cuve.getId()+" : " + (Math.round(cuve.getContenu() * 100.0) / 100.0) + "/" + cuve.getCapacite();

			if(cuve.getContenu() < 500) 
			{
				float saturation = ((float) cuve.getContenu() / (float) 1000) * 2;
				color = Color.getHSBColor(0, saturation, 1F);
			}
			else 
			{
				float brightness = (((float)1000 - (float)cuve.getContenu()) / (float) 1000) * 2;
				color = Color.getHSBColor(0, 1F, brightness);
			}


			g2d.setColor( color );
			g2d.fillOval( cuve.getX(), cuve.getY(), cuve.getCapacite()/10, cuve.getCapacite()/10 ); 
			 
			g2d.setColor( Color.BLACK );
			g2d.drawOval( cuve.getX(), cuve.getY(), cuve.getCapacite()/10, cuve.getCapacite()/10 );
			
			switch(cuve.getPosInfo())
			{
				case'D' -> g2d.drawString(information, cuve.getX()+110, cuve.getY()+30);
				case'H' -> g2d.drawString(information, cuve.getX()-5, cuve.getY()-10);
				case'G' -> g2d.drawString(information, cuve.getX()-75, cuve.getY()+50);
				case'B' -> g2d.drawString(information, cuve.getX()+5, cuve.getY()+125);
				default -> System.out.println("Erreur : posInfo incorrecte");
			}
		}
	}

	public void majIHM() {
		this.repaint();
		this.ctrl.iterationSuivante();
	}

}