package input;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 
 * @author Debreczeni Mate
 * 
 * Szoveg bevitelekor megjeleno ablak osztalya.
 */
public class TextInputWindow extends JFrame implements ActionListener{


	private static final long serialVersionUID = -1070174884217187169L;
	/**
	 * Kepernyon megjelenitheto gomb
	 */
	private JButton button;
	/**
	 * szovegdoboz melybe a jatekos neve kerul
	 */
	private JTextField textField;
	/**
	 * a jatekos nevet tarolo sztring
	 */
	private String playerName;
	/**
	 * igazsagertek mely eltarolja ha a felhasznalo beallitott egy nevet
	 */
	private boolean nameSet;
	
	/**
	 * Az ablakot a kello kinezettel letrehozo konstruktor.
	 */
	public TextInputWindow(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		button = new JButton("Submit");
		button.addActionListener(this);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(250,40));
		textField.setFont(new Font("Consolas",Font.PLAIN,35));
		textField.setForeground(Color.white);
		textField.setBackground(Color.black);
		textField.setCaretColor(Color.white);
		textField.setText("Player name");
		
		
		this.add(button);
		this.add(textField);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		nameSet=false;
	}
	
	/**
	 * Az ablakon megjeleno gomb lenyomasakor elvart viselkedest definialo fuggveny.
	 * gombnyomasra az ablak bezarul es elmenti a szovegdobozba irt szoveget.
	 * @param e A gombnyomas esemenye
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			playerName = textField.getText();
			this.dispose();
			nameSet=true;
		}
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public boolean isNameSet() {
		return nameSet;
	}

}
