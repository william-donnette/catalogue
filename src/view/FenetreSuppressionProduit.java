package view;

import controllers.CatalogueController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	private JLabel labelConfirmation;
	private CatalogueController cc;
	public FenetreSuppressionProduit(CatalogueController cc) {
		this.cc = cc;
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(cc.listeNomProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		labelConfirmation = new JLabel("");
		contentPane.add(labelConfirmation);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(cc.supprimerProduit((String)combo.getSelectedItem())){
			labelConfirmation.setText("Produit supprimé");
		}else{
			labelConfirmation.setText("Le produit n'a pas pu être supprimé");
		}
	}

}
