package view;

import controllers.CatalogueController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private CatalogueController cc;
	private JLabel labelConfirmation;
	public FenetreAchat(String[] lesProduits, CatalogueController cc) {
		this.cc = cc;
		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		labelConfirmation = new JLabel("");
		contentPane.add(labelConfirmation);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(cc.acheterProduit((String)combo.getSelectedItem(), txtQuantite.getText())){
			labelConfirmation.setText("Produit acheté");
		}else{
			labelConfirmation.setText("Le produit n'a pas pu être acheté");
		}

	}

}
