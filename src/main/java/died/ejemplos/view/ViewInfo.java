package died.ejemplos.view;

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
	
	public void armarPanel() {
		
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
			
		constraints.gridx = 0;
		constraints.gridy = 0;
//		constraints.gridwidth = 3;
//		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		lblTitulo.setFont(lblTitulo.getFont().deriveFont(64.0f));
		
		Font font = lblTitulo.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblTitulo.setFont(font.deriveFont(attributes));
		
//		constraints.insets.set(0, 0, 0, 0);
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

}
