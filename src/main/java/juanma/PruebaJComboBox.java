/**
 * prueba commit git
 */
package juanma;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JFileChooser;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * @author jmalmeida
 *
 */
public class PruebaJComboBox {
	
	public JTextField tf;
	public JComboBox combo;
	public JFrame v;
	public Flight f;
	public static JFileChooser seleccionarArchivo;
	
	public static void main(String[] args) {
		new PruebaJComboBox();		
	}
	
	public PruebaJComboBox()
	{
		// Creacion del JTextField
		tf = new JTextField(20);
		
		// Creacion del JComboBox
		combo = new JComboBox();
		// creacion objeto con vuelos
		 f = new Flight();
		
		//añadir los items
		 combo.addItem(listFlights(f.getNombre()));
		 combo.addItem("barbecho");
		 combo.addItem("agaporni");
		 
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
			combo.addActionListener(new ActionListener() 
			{
				//@Override
				public void actionPerformed(ActionEvent e) {
						tf.setText(combo.getSelectedItem().toString());
			}
			});
			// Accion a realizar cuando se pulsa el boton Abrir
			final JButton jbt = new JButton("Abrir popUp");
			jbt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				seleccionarArchivo = new JFileChooser();
				JDialog dialogo = new JDialog(v);
				ImagePanel i  = new ImagePanel();
				dialogo.setSize(100, 100);
				dialogo.setLocationRelativeTo(v);
				int result = seleccionarArchivo.showOpenDialog(i);
		        if (result == JFileChooser.APPROVE_OPTION){
		            String filename = seleccionarArchivo.getSelectedFile().getAbsolutePath();
		            Image g = Toolkit.getDefaultToolkit().getImage(filename);
		            dialogo.setIconImage(g);
		            i.setVisible(true);
		            dialogo.setVisible(true);
		        }
			}
	});
		
		// Creacion de la ventana con los componentes
		v = new JFrame();
		v.setSize(500, 500);	
		v.getContentPane().setLayout(new FlowLayout());
		v.getContentPane().add(combo);
		v.getContentPane().add(tf);
		v.add(jbt,BorderLayout.CENTER);
		v.pack();
		v.setVisible(true);
		v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
}
	private String listFlights(String h)  {
   	
		BasicConfigurator.configure();
		Logger.getLogger("org.hibernate").setLevel(Level.WARN);
		// Se obtiene la sesion
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
       
        // Se instancia la clase Flight y se consultan sus datos
        List<Flight> result = (List<Flight>)s.createQuery("from Flight").list();
        
        //Se rellena el objeto flight con los datos obtenidos en la consulta
        
        //for (int i=0;i<result.size();i++)
        for (int i=0;i<=1;i++)
        {
        	if(result.isEmpty()!=true){
        		Flight g = new Flight();
        		h = g.setNombre((result.get(i).getNombre()));
        		((List) result).add(f);
        	}
        }
        s.getTransaction().commit();
        HibernateUtil.getSession().close();
		return h;
    
    }
}
