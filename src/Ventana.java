import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame {
	private String actual = "login";
	private String anterior = "login";
	private String[] datos = null;
	private JPanel gran_panel = null;
	private JMenuBar jmb;

	
	// CONSTRUCTOR
	public Ventana() {
		this.setVisible(true);
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Gestion de usuarios");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.route();
	}

	// METODO QUE AUXILIA AL CAMBIAR ENTRE PANELES
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
		if (actual.equals("listaUsuarios")) {
			gran_panel = listaUsuarios();
		}
		if (actual.equals("crearUsuario")) {
			gran_panel = crearUsuario();
		}
		if (actual.equals("comoCrearUsuario")) {
			gran_panel = comoCrearUsuario();
		}
		this.add(gran_panel);
		this.revalidate();
		this.repaint();
	}

	public JPanel login() {
		JPanel login = new JPanel();
		login.setVisible(true);
		login.setSize(500, 600);
		login.setBackground(Color.black);
		login.setLayout(null);

		ImageIcon img = new ImageIcon("ingresar.jpg");
		JLabel logo = new JLabel();
		logo.setBounds(190, 30, 120, 120);
		;
		logo.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
		login.add(logo);

		JLabel tag1 = new JLabel("Accede a tu cuenta", JLabel.CENTER);
		tag1.setFont(new Font("Comic Sans", Font.BOLD, 23));
		tag1.setSize(300, 40);
		tag1.setForeground(Color.white);
		tag1.setLocation(100, 160);
		login.add(tag1);

		JLabel tag2 = new JLabel("Nombre de usuario");
		tag2.setFont(new Font("Arial", Font.BOLD, 15));
		tag2.setSize(200, 20);
		tag2.setForeground(Color.white);
		tag2.setLocation(50, 250);
		login.add(tag2);

		JTextField userName = new JTextField();
		userName.setSize(380, 50);
		userName.setLocation(50, 280);
		userName.setFont(new Font("Comic Sans", Font.ITALIC, 15));
		login.add(userName);

		JLabel tag3 = new JLabel("Contraseña de acceso:");
		tag3.setFont(new Font("Arial", Font.BOLD, 15));
		tag3.setSize(200, 20);
		tag3.setForeground(Color.white);
		tag3.setLocation(50, 350);
		login.add(tag3);

		JPasswordField pwd = new JPasswordField();
		pwd.setSize(380, 50);
		pwd.setLocation(48, 380);
		login.add(pwd);

		JButton iniciar_Sesion = new JButton("Iniciar sesión");
		iniciar_Sesion.setSize(150, 40);
		iniciar_Sesion.setForeground(Color.white);
		iniciar_Sesion.setLocation(270, 480);
		iniciar_Sesion.setOpaque(true);
		iniciar_Sesion.setBackground(Color.decode("#080691"));
		login.add(iniciar_Sesion);

		JButton cancelar = new JButton("Cancelar");
		cancelar.setSize(150, 40);
		cancelar.setForeground(Color.white);
		cancelar.setLocation(62, 480);
		cancelar.setOpaque(true);
		cancelar.setBackground(Color.decode("#D80000"));
		login.add(cancelar);

		iniciar_Sesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String usuario = userName.getText();
				String password = new String(pwd.getPassword());
				BufferedReader reader;
				Boolean acceso = false;
				try {
					reader = new BufferedReader(new FileReader("Users.txt"));
					String line = reader.readLine();
					while (line != null) {
						datos = line.split(",");
						if (datos[1].equals(usuario)) {
							if (datos[3].equals(password)) {
								acceso = true;
								break;
							}
						}
						// Leer la siguiente linea
						line = reader.readLine();
					}
					if (acceso) {
						JOptionPane.showMessageDialog(null, "Bienvenido " + datos[0]);
						anterior = actual;
						actual = "dash";
						route();
					} else {
						JOptionPane.showMessageDialog(null, "El usuario y contraseña no coinciden");
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
				userName.setText("");
				pwd.setText("");
			}
		});

		return login;
	}

	public JPanel dash() {
		
		JPanel dash = new JPanel();
		dash.setVisible(true);
		dash.setSize(500, 600);
		dash.setBackground(Color.black);
		dash.setLayout(null);

		JLabel bienvenido = new JLabel("Hola " + datos[0], JLabel.CENTER);
		bienvenido.setFont(new Font("Comic Sans", Font.BOLD, 23));
		bienvenido.setSize(500, 40);
		bienvenido.setForeground(Color.white);
		bienvenido.setLocation(0, 100);
		dash.add(bienvenido);

		ImageIcon img = new ImageIcon("dash.jpg");
		JLabel logo = new JLabel();
		logo.setBounds(190, 160, 120, 120);
		;
		logo.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
		dash.add(logo);

		jmb = new JMenuBar();
		jmb.setLocation(0, 0);
		jmb.setSize(500, 20);
		dash.add(jmb);

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

		jmi3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				anterior = actual;
				actual = "listaUsuarios";
				route();
			}
		});

		jmi4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				anterior = actual;
				actual = "crearUsuario";
				route();
			}
		});

		jmi5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				anterior = actual;
				actual = "comoCrearUsuario";
				route();
			}
		});

		return dash;
	}

	public JPanel miCuenta() {
		JPanel miCuenta = new JPanel();
		miCuenta.setVisible(true);
		miCuenta.setSize(500, 600);
		miCuenta.setBackground(Color.black);
		miCuenta.setLayout(null);

		miCuenta.add(jmb);

		JLabel cuenta = new JLabel("Mi cuenta personal", JLabel.CENTER);
		cuenta.setFont(new Font("Comic Sans", Font.BOLD, 23));
		cuenta.setSize(500, 20);
		cuenta.setForeground(Color.white);
		cuenta.setLocation(0, 25);
		miCuenta.add(cuenta);

		ImageIcon img = new ImageIcon("dash.jpg");
		JLabel logo = new JLabel();
		logo.setBounds(200, 70, 100, 100);
		;
		logo.setIcon(new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		miCuenta.add(logo);

		JLabel nombre = new JLabel("Nombre:");
		nombre.setFont(new Font("Comic Sans", Font.BOLD, 18));
		nombre.setSize(100, 20);
		nombre.setForeground(Color.white);
		nombre.setLocation(70, 190);
		miCuenta.add(nombre);

		JTextField in_nombre = new JTextField();
		in_nombre.setSize(340, 30);
		in_nombre.setLocation(70, 220);
		in_nombre.setFont(new Font("Comic Sans", Font.ITALIC, 18));
		in_nombre.setText(datos[0]);
		miCuenta.add(in_nombre);

		JLabel apellidos = new JLabel("Apellidos:");
		apellidos.setFont(new Font("Comic Sans", Font.BOLD, 18));
		apellidos.setSize(100, 20);
		apellidos.setForeground(Color.white);
		apellidos.setLocation(70, 260);
		miCuenta.add(apellidos);

		JTextField in_apellidos = new JTextField();
		in_apellidos.setSize(340, 30);
		in_apellidos.setLocation(70, 290);
		in_apellidos.setFont(new Font("Comic Sans", Font.ITALIC, 18));
		miCuenta.add(in_apellidos);

		JLabel email = new JLabel("Email:");
		email.setFont(new Font("Comic Sans", Font.BOLD, 18));
		email.setSize(100, 20);
		email.setForeground(Color.white);
		email.setLocation(70, 325);
		miCuenta.add(email);

		JTextField in_email = new JTextField();
		in_email.setSize(340, 30);
		in_email.setLocation(70, 350);
		in_email.setFont(new Font("Comic Sans", Font.ITALIC, 18));
		in_email.setText(datos[2]);
		miCuenta.add(in_email);

		JLabel contra = new JLabel("Contraseña:");
		contra.setFont(new Font("Comic Sans", Font.BOLD, 18));
		contra.setSize(200, 20);
		contra.setForeground(Color.white);
		contra.setLocation(70, 385);
		miCuenta.add(contra);

		JPasswordField pwd = new JPasswordField();
		pwd.setSize(340, 30);
		pwd.setLocation(70, 410);
		miCuenta.add(pwd);

		JButton actualizar_datos = new JButton("Actualizar datos");
		actualizar_datos.setSize(150, 40);
		actualizar_datos.setForeground(Color.white);
		actualizar_datos.setLocation(270, 460);
		actualizar_datos.setOpaque(true);
		actualizar_datos.setBackground(Color.decode("#080691"));
		miCuenta.add(actualizar_datos);

		JButton cancelar = new JButton("Cancelar");
		cancelar.setSize(150, 40);
		cancelar.setForeground(Color.white);
		cancelar.setLocation(62, 460);
		cancelar.setOpaque(true);
		cancelar.setBackground(Color.decode("#D80000"));
		miCuenta.add(cancelar);

		actualizar_datos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// SE GUARDAN LOS DATOS ORIGINALES

				String[] datos_Orig = { datos[0], datos[1], datos[2], datos[3] };
				// SE GUARDAN LOS DATOS ACTUALIZADOS EN UNA SOLA CADENA
				String datos_Actualizados = "";
				datos_Actualizados = datos_Actualizados + in_nombre.getText() + " ";
				datos_Actualizados = datos_Actualizados + in_apellidos.getText() + ",";
				datos_Actualizados = datos_Actualizados + datos[1] + ",";
				datos_Actualizados = datos_Actualizados + in_email.getText() + ",";
				String password = new String(pwd.getPassword());
				datos_Actualizados = datos_Actualizados + password;
				BufferedReader reader;
				boolean usuarioDoble = false;
				// MATRIZ PARA ALMACENAR LOS DATOS EXISTENTES EN EL TXT QUE DESPUES SERAN
				// VACIADOS
				String[][] copiaDatos = new String[100][1];
				try {
					reader = new BufferedReader(new FileReader("Users.txt"));
					String line = reader.readLine();
					while (line != null) {
						String[] datos = null;
						datos = line.split(",");
						if (datos[2].equals(in_email.getText()) && !datos_Orig[2].equals(datos[2])) {
							JOptionPane.showMessageDialog(null, "Error, ya hay un usuario registrado con este correo");
							usuarioDoble = true;
							break;
						} else {
							line = reader.readLine();
						}
					}
					if (!in_nombre.getText().isEmpty() && !in_apellidos.getText().isEmpty()
							&& !in_email.getText().isEmpty() && !password.isEmpty() && !usuarioDoble) {
						// SE GUARDAN LOS DATOS EXISTENTES DEL TXT ANTES DE ELIMINARLOS
						int i = 0;
						reader = new BufferedReader(new FileReader("Users.txt"));
						line = reader.readLine();
						while (line != null) {
							copiaDatos[i][0] = line;
							line = reader.readLine();
							i++;
						}
						i = 0;
						// SE ELIMINAN LOS DATOS EXISTENTES EN EL TXT
						BufferedWriter writter = new BufferedWriter(new FileWriter("Users.txt"));
						// SE VUELVE A LLENAR EL TXT CON LOS DATOS GUARDADOS EN LA COPIA Y LOS
						// ACTUALIZDOS
						while (copiaDatos[i][0] != null) {
							if (copiaDatos[i][0].contains(datos_Orig[2])) {
								i++;
							} else {
								writter.write(copiaDatos[i][0]);
								writter.newLine();
								i++;
							}
						}
						writter.write(datos_Actualizados);
						writter.newLine();
						writter.close();
						datos = datos_Actualizados.split(",");
						JOptionPane.showMessageDialog(null, "Información actualizada");
					} else if (!usuarioDoble) {
						JOptionPane.showMessageDialog(null, "Error, todos los campos deben ser llenados");
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
				anterior = actual;
				actual = "dash";
				route();
			}
		});

		return miCuenta;
	}

	public JPanel listaUsuarios() {
		JPanel listaUsuarios = new JPanel();
		listaUsuarios.setVisible(true);
		listaUsuarios.setSize(500, 600);
		listaUsuarios.setBackground(Color.black);
		listaUsuarios.setLayout(null);

		listaUsuarios.add(jmb);

		JLabel titulo = new JLabel("Lista de usuarios");
		titulo.setSize(200, 40);
		titulo.setLocation(150, 30);
		titulo.setFont(new Font("Comic Sans", Font.BOLD, 23));
		titulo.setForeground(Color.white);
		listaUsuarios.add(titulo);

		JLabel editar = new JLabel("Editar");
		editar.setSize(100, 20);
		editar.setLocation(20, 90);
		editar.setFont(new Font("Comic Sans", Font.BOLD, 18));
		editar.setForeground(Color.white);
		listaUsuarios.add(editar);

		JComboBox usuarios = new JComboBox();
		usuarios.setSize(440, 40);
		usuarios.setLocation(20, 120);
		listaUsuarios.add(usuarios);
		datos = null;
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("Users.txt"));
			String line = reader.readLine();
			while (line != null) {
				datos = line.split(",");
				usuarios.addItem(datos[0]);
				// Leer la siguiente linea
				line = reader.readLine();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton btn_editar = new JButton("Editar a " + usuarios.getSelectedItem().toString());
		btn_editar.setSize(440, 40);
		btn_editar.setLocation(20, 170);
		btn_editar.setForeground(Color.white);
		btn_editar.setOpaque(true);
		btn_editar.setBackground(Color.decode("#D80000"));
		listaUsuarios.add(btn_editar);

		usuarios.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_editar.setText("Editar a " + usuarios.getSelectedItem().toString());
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		btn_editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader("Users.txt"));
					String line = reader.readLine();
					while (line != null) {
						datos = null;
						datos = line.split(",");
						if (datos[0].equals(usuarios.getSelectedItem().toString())) {
							break;
						} else {
							line = reader.readLine();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				anterior = actual;
				actual = "miCuenta";
				route();
			}
		});

		String nombresColumna[] = { "Usuario", "Nombre", "Acciones" };
		JButton eliminar = new JButton("Eliminar");
		
		try {
			reader = new BufferedReader(new FileReader("Users.txt"));
			String line = reader.readLine();
			JTable tabla = new JTable();;
			DefaultTableModel tablaModel = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			tablaModel.setColumnIdentifiers(nombresColumna);

			for (int i = 0; i < 100; i++) {
				if (line != null) {
					datos = null;
					datos = line.split(",");
					tablaModel.addRow(new Object[] {datos[1], datos[0], eliminar});
					line = reader.readLine();
				} else {
					i = 100;
				}
			}
			
			tabla.setRowHeight(25);
			tabla.setDefaultRenderer(Object.class, new BotonTabla());
			tabla.setModel(tablaModel);
			JScrollPane sp = new JScrollPane(tabla);
			sp.setSize(440, 300);
			sp.setLocation(20, 220);
			listaUsuarios.add(sp);
			reader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("aaaaaa");
			}
		});
		return listaUsuarios;
	}

	public JPanel crearUsuario() {
		JPanel crearUsuario = new JPanel();
		crearUsuario.setVisible(true);
		crearUsuario.setSize(500, 600);
		crearUsuario.setBackground(Color.black);
		crearUsuario.setLayout(null);

		crearUsuario.add(jmb);

		JLabel titulo = new JLabel("Crear Usuario", JLabel.CENTER);
		titulo.setSize(500, 20);
		titulo.setLocation(0, 25);
		titulo.setFont(new Font("Comic Sans", Font.BOLD, 23));
		titulo.setForeground(Color.white);
		crearUsuario.add(titulo);

		ImageIcon img = new ImageIcon("user.png");
		JLabel logo = new JLabel();
		logo.setBounds(200, 60, 90, 90);
		;
		logo.setIcon(new ImageIcon(img.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
		crearUsuario.add(logo);

		JLabel nombre = new JLabel("Nombre:");
		nombre.setSize(100, 20);
		nombre.setLocation(20, 150);
		nombre.setFont(new Font("Comic Sans", Font.BOLD, 17));
		nombre.setForeground(Color.white);
		crearUsuario.add(nombre);

		JTextField in_Nombre = new JTextField();
		in_Nombre.setSize(440, 30);
		in_Nombre.setLocation(20, 175);
		in_Nombre.setFont(new Font("Comic Sans", Font.ITALIC, 15));
		crearUsuario.add(in_Nombre);

		JLabel usuario = new JLabel("Usuario:");
		usuario.setSize(100, 20);
		usuario.setLocation(20, 210);
		usuario.setFont(new Font("Comic Sans", Font.BOLD, 17));
		usuario.setForeground(Color.white);
		crearUsuario.add(usuario);

		JTextField in_Usuario = new JTextField();
		in_Usuario.setSize(440, 30);
		in_Usuario.setLocation(20, 235);
		in_Usuario.setFont(new Font("Comic Sans", Font.ITALIC, 15));
		crearUsuario.add(in_Usuario);

		JLabel email = new JLabel("Email:");
		email.setSize(100, 20);
		email.setLocation(20, 270);
		email.setFont(new Font("Comic Sans", Font.BOLD, 17));
		email.setForeground(Color.white);
		crearUsuario.add(email);

		JTextField in_Email = new JTextField();
		in_Email.setSize(440, 30);
		in_Email.setLocation(20, 295);
		in_Email.setFont(new Font("Comic Sans", Font.ITALIC, 15));
		crearUsuario.add(in_Email);

		JLabel pwd = new JLabel("Contraseña:");
		pwd.setSize(100, 20);
		pwd.setLocation(20, 330);
		pwd.setFont(new Font("Comic Sans", Font.BOLD, 17));
		pwd.setForeground(Color.white);
		crearUsuario.add(pwd);

		JPasswordField in_Pwd = new JPasswordField();
		in_Pwd.setSize(440, 30);
		in_Pwd.setLocation(20, 355);
		crearUsuario.add(in_Pwd);

		JLabel confirmar_Pwd = new JLabel("Confirmar contraseña:");
		confirmar_Pwd.setSize(200, 20);
		confirmar_Pwd.setLocation(20, 390);
		confirmar_Pwd.setFont(new Font("Comic Sans", Font.BOLD, 17));
		confirmar_Pwd.setForeground(Color.white);
		crearUsuario.add(confirmar_Pwd);

		JPasswordField confirmar_In_Pwd = new JPasswordField();
		confirmar_In_Pwd.setSize(440, 30);
		confirmar_In_Pwd.setLocation(20, 415);
		crearUsuario.add(confirmar_In_Pwd);

		JButton crear_Usuario = new JButton("Crear Usuario");
		crear_Usuario.setSize(150, 40);
		crear_Usuario.setForeground(Color.white);
		crear_Usuario.setLocation(270, 480);
		crear_Usuario.setOpaque(true);
		crear_Usuario.setBackground(Color.decode("#080691"));
		crearUsuario.add(crear_Usuario);

		JButton cancelar = new JButton("Cancelar");
		cancelar.setSize(150, 40);
		cancelar.setForeground(Color.white);
		cancelar.setLocation(62, 480);
		cancelar.setOpaque(true);
		cancelar.setBackground(Color.decode("#D80000"));
		crearUsuario.add(cancelar);

		crear_Usuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String newUser = "";
				newUser = newUser + in_Nombre.getText() + ",";
				newUser = newUser + in_Usuario.getText() + ",";
				newUser = newUser + in_Email.getText() + ",";
				String pwd = new String(in_Pwd.getPassword());
				String conf_pwd = new String(confirmar_In_Pwd.getPassword());
				newUser = newUser + pwd;

				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader("Users.txt"));
					String line = reader.readLine();

					while (line != null) {
						String[] datos = null;
						datos = line.split(",");
						if (datos[2].equals(in_Email.getText()) || datos[1].equals(in_Usuario.getText())) {
							JOptionPane.showMessageDialog(null, "Error, ya hay un usuario registrado con este correo");
							newUser = "";
							break;
						} else {
							line = reader.readLine();
						}
					}
					if (!pwd.equals(conf_pwd) && newUser != "") {
						JOptionPane.showMessageDialog(null, "Error, las contraseñas no coinciden");
						newUser = "";
					}
					if (newUser != "" && !in_Nombre.getText().isEmpty() && !in_Usuario.getText().isEmpty()
							&& !in_Email.getText().isEmpty() && !pwd.isEmpty() && !conf_pwd.isEmpty()) {
						FileWriter fw = new FileWriter("Users.txt", true);
						PrintWriter writer = new PrintWriter(fw);
						writer.println(newUser);
						JOptionPane.showMessageDialog(null, "Usuario creado");
						datos[0] = in_Nombre.getText();
						datos[1] = in_Usuario.getText();
						datos[2] = in_Email.getText();
						datos[3] = pwd;
						writer.close();
						fw.close();
						anterior = actual;
						actual = "miCuenta";
						route();
					} else if (newUser != "") {
						JOptionPane.showMessageDialog(null, "Error, todos los campos deben ser llenados");
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
				anterior = actual;
				actual = "dash";
				route();
			}
		});

		return crearUsuario;
	}

	
	public JPanel comoCrearUsuario() {
		JPanel comoCrearUsuario = new JPanel();
		comoCrearUsuario.setVisible(true);
		comoCrearUsuario.setSize(500, 600);
		comoCrearUsuario.setBackground(Color.black);
		comoCrearUsuario.setLayout(null);

		comoCrearUsuario.add(jmb);

		JLabel titulo = new JLabel("¿Como crear un usuario?", JLabel.CENTER);
		titulo.setSize(500, 40);
		titulo.setLocation(0, 30);
		titulo.setFont(new Font("Comic Sans", Font.BOLD, 23));
		titulo.setForeground(Color.white);
		comoCrearUsuario.add(titulo);

		ImageIcon img = new ImageIcon("Crearuser.jpg");
		JLabel logo = new JLabel();
		logo.setBounds(200, 80, 90, 90);
		;
		logo.setIcon(new ImageIcon(img.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
		comoCrearUsuario.add(logo);

		JLabel paso1 = new JLabel("1.- Hacer click en la opción ''Usuarios'' en el menu");
		paso1.setSize(500, 20);
		paso1.setLocation(20, 190);
		paso1.setFont(new Font("Comic Sans", Font.BOLD, 17));
		paso1.setForeground(Color.white);
		comoCrearUsuario.add(paso1);

		JLabel paso1_1 = new JLabel("superior");
		paso1_1.setSize(500, 20);
		paso1_1.setLocation(20, 220);
		paso1_1.setFont(new Font("Comic Sans", Font.BOLD, 17));
		paso1_1.setForeground(Color.white);
		comoCrearUsuario.add(paso1_1);

		JLabel paso2 = new JLabel("2.- Hacer click en la opción ''Crear usuario'' en el menu");
		paso2.setSize(500, 20);
		paso2.setLocation(20, 250);
		paso2.setFont(new Font("Comic Sans", Font.BOLD, 17));
		paso2.setForeground(Color.white);
		comoCrearUsuario.add(paso2);

		JLabel paso2_1 = new JLabel("desplegado");
		paso2_1.setSize(500, 20);
		paso2_1.setLocation(20, 280);
		paso2_1.setFont(new Font("Comic Sans", Font.BOLD, 17));
		paso2_1.setForeground(Color.white);
		comoCrearUsuario.add(paso2_1);

		JLabel paso3 = new JLabel("3.- Llenar los campos solicitados");
		paso3.setSize(500, 20);
		paso3.setLocation(20, 310);
		paso3.setFont(new Font("Comic Sans", Font.BOLD, 17));
		paso3.setForeground(Color.white);
		comoCrearUsuario.add(paso3);

		JLabel paso4 = new JLabel("4.- Escribir una pequeña descripción de ti");
		paso4.setSize(500, 20);
		paso4.setLocation(20, 340);
		paso4.setFont(new Font("Comic Sans", Font.BOLD, 17));
		paso4.setForeground(Color.white);
		comoCrearUsuario.add(paso4);

		JLabel paso5 = new JLabel("5.- Seleccionar tu comida favorita");
		paso5.setSize(500, 20);
		paso5.setLocation(20, 370);
		paso5.setFont(new Font("Comic Sans", Font.BOLD, 17));
		paso5.setForeground(Color.white);
		comoCrearUsuario.add(paso5);

		JLabel paso6 = new JLabel("6.- Seleccionar tu color favorito");
		paso6.setSize(500, 20);
		paso6.setLocation(20, 400);
		paso6.setFont(new Font("Comic Sans", Font.BOLD, 17));
		paso6.setForeground(Color.white);
		comoCrearUsuario.add(paso6);

		JLabel paso7 = new JLabel("7.- Hacer click en el botón ''Crear Usuario''");
		paso7.setSize(500, 20);
		paso7.setLocation(20, 430);
		paso7.setFont(new Font("Comic Sans", Font.BOLD, 17));
		paso7.setForeground(Color.white);
		comoCrearUsuario.add(paso7);

		JLabel paso8 = new JLabel("8.- Listo, el usuarios se ha creado");
		paso8.setSize(500, 20);
		paso8.setLocation(20, 460);
		paso8.setFont(new Font("Comic Sans", Font.BOLD, 17));
		paso8.setForeground(Color.white);
		comoCrearUsuario.add(paso8);

		JButton crear_Usuario = new JButton("Crear un usuario ahora");
		crear_Usuario.setSize(180, 40);
		crear_Usuario.setForeground(Color.white);
		crear_Usuario.setLocation(150, 500);
		crear_Usuario.setOpaque(true);
		crear_Usuario.setBackground(Color.decode("#080691"));
		comoCrearUsuario.add(crear_Usuario);

		crear_Usuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				anterior = actual;
				actual = "crearUsuario";
				route();
			}
		});

		return comoCrearUsuario;
	}
}
