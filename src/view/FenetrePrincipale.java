package view;

import controllers.CatalogueController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class FenetrePrincipale extends JFrame implements ActionListener,
		WindowListener {

	private JButton btAfficher;
	private JButton btNouveauProduit;
	private JButton btSupprimerProduit;
	private JButton btNouvelleCategorie;
	private JButton btSupprimerCategorie;
	private JButton btAchat;
	private JButton btVente;
	private JButton btQuitter;

	private CatalogueController cc;
	
	public FenetrePrincipale(String nomCatalogue) {
		
		setTitle("exercice Produits");
		setBounds(500, 500, 320, 250);
		JPanel panAffichage = new JPanel();
		JPanel panNouveauSupprimerProduit = new JPanel();
		JPanel panNouveauSupprimerCategorie = new JPanel();
		JPanel panAchatVente = new JPanel();
		JPanel panQuitter = new JPanel();
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAfficher = new JButton("Quantités en stock");
		btNouveauProduit = new JButton("Nouveau Produit");
		btSupprimerProduit = new JButton("Supprimer Produit");
//		btNouvelleCategorie = new JButton("Nouvelle Categorie");
//		btSupprimerCategorie = new JButton("Supprimer Categorie");
		btAchat = new JButton("Achat Produits");
		btVente = new JButton("Vente Produits");
		btQuitter = new JButton("Quitter");
		panAffichage.add(btAfficher);
		panNouveauSupprimerProduit.add(btNouveauProduit); 
		panNouveauSupprimerProduit.add(btSupprimerProduit);
//		panNouveauSupprimerCategorie.add(btNouvelleCategorie);
//		panNouveauSupprimerCategorie.add(btSupprimerCategorie);
		panAchatVente.add(btAchat); 
		panAchatVente.add(btVente);  
		panQuitter.add(btQuitter);

		contentPane.add(panAffichage);
//		contentPane.add(panNouveauSupprimerCategorie);
		contentPane.add(panNouveauSupprimerProduit);
		contentPane.add(panAchatVente);
		contentPane.add(panQuitter);

		btAfficher.addActionListener(this);
		btNouveauProduit.addActionListener(this);
		btSupprimerProduit.addActionListener(this);
//		btNouvelleCategorie.addActionListener(this);
//		btSupprimerCategorie.addActionListener(this);
		btAchat.addActionListener(this);
		btVente.addActionListener(this);
		btQuitter.addActionListener(this);
		
		addWindowListener(this);
		setVisible(true);

		cc = new CatalogueController(nomCatalogue);
	}

	public void actionPerformed(ActionEvent e) {
/* tabProduits permet de tester le fonctionnement des fen�tres avec un tableau de noms de produits "en dur"
   Quand l'application fonctionnera, il faudra bien s�r r�cup�rer les noms des produits dans le Catalogue */
		//String[] tabProduits = cc.listeNomProduits();

/* M�me chose pour tabCategories (partie 4) */
//		String[] tabCategories = new String[] {"Bio", "Luxe" };
		System.out.println("action");

		if (e.getSource() == btAfficher)
			new FenetreAffichage(cc.lister());
		if (e.getSource() == btNouveauProduit)
//			new view.FenetreNouveauProduit(tabCategories);
			new FenetreNouveauProduit(cc);
		if (e.getSource() == btSupprimerProduit)
			new FenetreSuppressionProduit(cc);
//		if (e.getSource() == btNouvelleCategorie)
//			new FenetreNouvelleCategorie();
//		if (e.getSource() == btSupprimerCategorie)
//			new FenetreSuppressionCategorie(tabCategories);
		if (e.getSource() == btAchat){
			new FenetreAchat(cc);//
			System.out.println("cc");
		}
		if (e.getSource() == btVente)
			new FenetreVente(cc);
		if (e.getSource() == btQuitter){
			dispose();
			new FenetreAccueil();
			System.out.println("Au revoir");
			//System.exit(0);
		}	
	}

	public void windowClosing(WindowEvent arg0) {
		System.out.println("Au revoir");
		System.exit(0);
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

	
	
	public static void main(String[] args) {
		new FenetrePrincipale("test");
	}

}
