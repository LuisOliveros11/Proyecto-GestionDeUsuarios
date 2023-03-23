
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Ventana extends JFrame {
	private String actual = "login";
	private String anterior = "login";
	private String userName=" ";
	private JPanel gran_panel = null;
	private JMenuBar jmb;

	//CONSTRUCTOR
	public Ventana() {
		this.setVisible(true);
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Gestion de usuarios");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.route();
	}

	//METODO QUE AUXILIA AL CAMBIAR ENTRE PANELES
	public void route() {
		if (gran_panel != null) {
			this.remove(gran_panel);
		}
		if (actual.equals("splash")) {
			gran_panel = login();
		}
		if (actual.equals("login")) {
			gran_panel = login();
		}
		if (actual.equals("dash")) {
			gran_panel = dash();
		}
		if (actual.equals("miCuenta")) {
			gran_panel = miCuenta();
		}
		this.add(gran_panel);
		this.revalidate();
		this.repaint();
	}
	
	public JPanel login() {
		JPanel login = new JPanel();
		login.setVisible(true);
		login.setSize(500,600);
		login.setBackground(Color.black);
		login.setLayout(null);
		
		JLabel tag1 = new JLabel("Accede a tu cuenta", JLabel.CENTER);
		tag1.setFont(new Font("Comic Sans",Font.BOLD,23));
		tag1.setSize(300,40);
		tag1.setForeground(Color.white);
		tag1.setLocation(100,100);
		login.add(tag1);
		
		JLabel tag2 = new JLabel("Nombre de usuario");
		tag2.setFont(new Font("Arial", Font.BOLD, 15));
		tag2.setSize(200,20);
		tag2.setForeground(Color.white);
		tag2.setLocation(50,190);
		login.add(tag2);
		
		JTextField mail = new JTextField();
		mail.setSize(380,50);
		mail.setLocation(50, 220);
		mail.setFont(new Font("Comic Sans", Font.ITALIC, 15));
		login.add(mail);
		
		JLabel tag3 = new JLabel("Contraseña de acceso:");
		tag3.setFont(new Font("Arial", Font.BOLD, 15));
		tag3.setSize(200,20);
		tag3.setForeground(Color.white);
		tag3.setLocation(50,290);
		login.add(tag3);
		
		JPasswordField pwd = new JPasswordField();
		pwd.setSize(380,50);
		pwd.setLocation(48,320);
		login.add(pwd);
		
		JButton iniciar_Sesion = new JButton("Iniciar sesión");
		iniciar_Sesion.setSize(150,40);
		iniciar_Sesion.setForeground(Color.white);
		iniciar_Sesion.setLocation(270,420);
		iniciar_Sesion.setOpaque(true);
		iniciar_Sesion.setBackground(Color.decode("#080691"));
		login.add(iniciar_Sesion);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setSize(150,40);
		cancelar.setForeground(Color.white);
		cancelar.setLocation(62,420);
		cancelar.setOpaque(true);
		cancelar.setBackground(Color.decode("#D80000"));
		login.add(cancelar);
		
		iniciar_Sesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String email = mail.getText();
				String password = new String(pwd.getPassword());
				String[] datos = null;
				BufferedReader reader;
				Boolean acceso = false;				
				try {
					reader = new BufferedReader(new FileReader("Users.txt"));
					String line = reader.readLine();
					while(line != null) {
						datos = line.split(",");
						if(datos[2].equals(email)) {
							if(datos[3].equals(password)) {
								acceso = true;
								userName = datos[0];
							}
						}
						//Leer la siguiente linea
						line = reader.readLine();
					}
					if(acceso) {
						JOptionPane.showMessageDialog(null,"Bienvenido " + userName);
						anterior = actual;
						actual = "dash";
						route();
					}else {
						JOptionPane.showMessageDialog(null,"El usuario y contraseña no coinciden");
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mail.setText("");
				pwd.setText("");
			}
		});
		
		return login;
	}
	
	public JPanel dash() {
		
		JPanel dash = new JPanel();
		dash.setVisible(true);
		dash.setSize(500,600);
		dash.setBackground(Color.black);
		dash.setLayout(null);
		
		JLabel bienvenido = new JLabel("Hola " + userName);
		bienvenido.setFont(new Font("Comic Sans",Font.BOLD,23));
		bienvenido.setSize(300,40);
		bienvenido.setForeground(Color.white);
		bienvenido.setLocation(200,100);
		dash.add(bienvenido);
		
		jmb = new JMenuBar();
		this.setJMenuBar(jmb);
		
		JMenu jm1 = new JMenu("Cuenta");
		JMenu jm2 = new JMenu("Usuarios");
		JMenu jm3 = new JMenu("Ayuda");
		
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		
		JMenuItem jmi1 = new JMenuItem("Mi cuenta");
		JMenuItem jmi2 = new JMenuItem("Cerrar sesión");
		jm1.add(jmi1);
		jm1.add(jmi2);
		JMenuItem jmi3 = new JMenuItem("Lista de usuarios");
		JMenuItem jmi4 = new JMenuItem("Crear usuario");
		jm2.add(jmi3);
		jm2.add(jmi4);
		JMenuItem jmi5 = new JMenuItem("¿Cómo crear usuarios?");
		jm3.add(jmi5);
		
		
		jmi1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				anterior = actual;
				actual = "miCuenta";
				route();
			}
		});
		
		jmi2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				anterior = actual;
				actual = "login";
				route();
			}
		});
		
		return dash;
	}

	public JPanel miCuenta() {
		JPanel miCuenta = new JPanel();
		miCuenta.setVisible(true);
		miCuenta.setSize(500,600);
		miCuenta.setBackground(Color.black);
		miCuenta.setLayout(null);
		
		JLabel cuenta = new JLabel("Mi cuenta personal");
		cuenta.setFont(new Font("Comic Sans",Font.BOLD,23));
		cuenta.setSize(300,40);
		cuenta.setForeground(Color.white);
		cuenta.setLocation(140,100);
		miCuenta.add(cuenta);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setFont(new Font("Comic Sans",Font.BOLD,18));
		nombre.setSize(100,20);
		nombre.setForeground(Color.white);
		nombre.setLocation(70,190);
		miCuenta.add(nombre);
		
		JTextField in_nombre = new JTextField();
		in_nombre.setSize(340,30);
		in_nombre.setLocation(70, 220);
		in_nombre.setFont(new Font("Comic Sans", Font.ITALIC, 18));
		miCuenta.add(in_nombre);
		
		JLabel apellidos = new JLabel("Apellidos:");
		apellidos.setFont(new Font("Comic Sans",Font.BOLD,18));
		apellidos.setSize(100,20);
		apellidos.setForeground(Color.white);
		apellidos.setLocation(70,260);
		miCuenta.add(apellidos);
		
		JTextField in_apellidos = new JTextField();
		in_apellidos.setSize(340,30);
		in_apellidos.setLocation(70, 290);
		in_apellidos.setFont(new Font("Comic Sans", Font.ITALIC, 18));
		miCuenta.add(in_apellidos);
		
		JLabel email = new JLabel("Email:");
		email.setFont(new Font("Comic Sans",Font.BOLD,18));
		email.setSize(100,20);
		email.setForeground(Color.white);
		email.setLocation(70,320);
		miCuenta.add(email);
		
		JTextField in_email = new JTextField();
		in_email.setSize(340,30);
		in_email.setLocation(70, 350);
		in_email.setFont(new Font("Comic Sans", Font.ITALIC, 18));
		miCuenta.add(in_email);
		
		JLabel contra = new JLabel("Contraseña:");
		contra.setFont(new Font("Comic Sans",Font.BOLD,18));
		contra.setSize(200,20);
		contra.setForeground(Color.white);
		contra.setLocation(70,380);
		miCuenta.add(contra);
		
		JPasswordField pwd = new JPasswordField();
		pwd.setSize(340,30);
		pwd.setLocation(70,410);
		miCuenta.add(pwd);
		
		JButton actualizar_datos = new JButton("Actualizar datos");
		actualizar_datos.setSize(150,40);
		actualizar_datos.setForeground(Color.white);
		actualizar_datos.setLocation(270,460);
		actualizar_datos.setOpaque(true);
		actualizar_datos.setBackground(Color.decode("#080691"));
		miCuenta.add(actualizar_datos);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setSize(150,40);
		cancelar.setForeground(Color.white);
		cancelar.setLocation(62,460);
		cancelar.setOpaque(true);
		cancelar.setBackground(Color.decode("#D80000"));
		miCuenta.add(cancelar);
		
		return miCuenta;
	}
}
