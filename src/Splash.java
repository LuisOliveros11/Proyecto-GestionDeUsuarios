import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

public class Splash extends JFrame {
	public Splash() {
		this.setVisible(true);
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Gestion de usuarios");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.black);
		
		ImageIcon img = new ImageIcon("logo.jpg");
		JLabel logo = new JLabel();
		logo.setBounds(150, 100, 200, 200);;
		logo.setIcon(new ImageIcon(img.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		this.add(logo);
		
		JLabel autor = new JLabel("Power By Luis Manuel Parada Oliveros", JLabel.CENTER);
		autor.setSize(500,20);
		autor.setLocation(0,450);
		autor.setForeground(Color.white);
		autor.setFont(new Font("Comic Sans", Font.BOLD, 12));
		this.add(autor);
		
		this.revalidate();
		this.repaint();
		
		pantallaSiguiente();
	}

	private void pantallaSiguiente() {
		Thread hilo = new Thread(new Runnable() {
			int x = 0;
			@Override
			public void run() {
				try {
					while (x < 100) {
						x+=5;
						Thread.sleep(100);
					}
					dispose();
					Ventana miVen = new Ventana();
					miVen.setVisible(true);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		hilo.start();
	}
}
