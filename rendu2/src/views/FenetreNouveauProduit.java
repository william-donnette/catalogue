package views;

import controllers.CatalogueController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;
	private JLabel labConfirmation;

	private CatalogueController cc;

//	public view.FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit(CatalogueController cc) {
		this.cc = cc;
		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantité en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		labConfirmation = new JLabel("");

		btValider = new JButton("Valider");
		contentPane.add(btValider);
		contentPane.add(labConfirmation);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(cc.creerProduit(txtNom.getText(), txtPrixHT.getText(), txtQte.getText())){
			labConfirmation.setText("Produit Enregistré");
		}
		else {
			labConfirmation.setText("Le produit n'a pas pu être enregistré");
		}
	}

}