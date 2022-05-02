package views;

import controllers.CatalogueController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private CatalogueController cc;
	private JLabel labelConfirmation;
	public FenetreVente(String[] lesProduits, CatalogueController cc) {
		this.cc = cc;
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		labelConfirmation = new JLabel("");
		contentPane.add(labelConfirmation);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(cc.vendreProduit((String)combo.getSelectedItem(), txtQuantite.getText())){
			labelConfirmation.setText("Produit vendu");
		}else{
			labelConfirmation.setText("Le produit n'a pas pu être vendu");
		}


	}

}
