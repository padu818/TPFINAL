package tpdied2020.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewInfo extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo = new JLabel("Integrantes:");
	private JLabel lblTitulo2 = new JLabel("-   Paduli, Juan Diego.");
	private JLabel lblTitulo3 = new JLabel("-   Rafart, Yamil Arturo.");
	
	private JLabel lblTitulo4 = new JLabel("* Datos paro uso de BD:");
	private JLabel lblTitulo5 = new JLabel("- Programa: PostgreSQL");
	private JLabel lblTitulo6 = new JLabel("- Usuario:  postgres");
	private JLabel lblTitulo7 = new JLabel("- Pass:     12345");
	private JLabel lblTitulo8 = new JLabel("- URL:      jdbc:postgresql://localhost:5432/TPDIED2020");
	private JLabel lblTitulo9 = new JLabel("- Si las tablas no estan creadas, hay que ejecutar el codigo de la clase 'DB'");
	private JLabel lblTitulo10 = new JLabel("Y los 'INSERT' con datos estan en el paquete 'tpdied2020.SQL'");
	private JLabel lblTitulo11 = new JLabel("* Diagramas: Se encuentran en el paquete 'tpdied2020.diagramas'");
	
	public void armarPanel() {
		
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
			
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		lblTitulo.setFont(lblTitulo.getFont().deriveFont(64.0f));
		
		Font font = lblTitulo.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblTitulo.setFont(font.deriveFont(attributes));
		add(lblTitulo, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		lblTitulo2.setFont(lblTitulo2.getFont().deriveFont(64.0f));
		constraints.insets.set(0, 0, 0, 0);
		add(lblTitulo2, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		lblTitulo3.setFont(lblTitulo3.getFont().deriveFont(64.0f));
		constraints.insets.set(20, 20, 0, 0);
		add(lblTitulo3, constraints);
		
	}
	
public void armarPanel2() {
		
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
			
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		lblTitulo4.setFont(lblTitulo.getFont().deriveFont(20.0f));
		add(lblTitulo4, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		lblTitulo5.setFont(lblTitulo.getFont().deriveFont(20.0f));
		constraints.insets.set(20, 0, 0, 0);
		add(lblTitulo5, constraints);
		constraints.gridy = 2;
		lblTitulo6.setFont(lblTitulo.getFont().deriveFont(20.0f));
		add(lblTitulo6, constraints);
		constraints.gridy = 3;
		lblTitulo7.setFont(lblTitulo.getFont().deriveFont(20.0f));
		add(lblTitulo7, constraints);
		constraints.gridy = 4;
		lblTitulo8.setFont(lblTitulo.getFont().deriveFont(20.0f));
		add(lblTitulo8, constraints);
		constraints.gridy = 5;
		lblTitulo9.setFont(lblTitulo.getFont().deriveFont(20.0f));
		add(lblTitulo9, constraints);
		constraints.gridy = 6;
		lblTitulo10.setFont(lblTitulo.getFont().deriveFont(20.0f));
		add(lblTitulo10, constraints);
		constraints.gridx = 0;
		constraints.gridy = 8;
		lblTitulo11.setFont(lblTitulo.getFont().deriveFont(20.0f));
		add(lblTitulo11, constraints);
		
	}

}
