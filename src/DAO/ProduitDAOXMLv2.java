package DAO;

import metiers.I_Produit;
import metiers.Produit;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProduitDAOXMLv2 implements I_DAOProduits<I_Produit> {
    private String uri = "C:/XAMPP/htdocs/catalogue2/rendu3/Produits.xml";
    private Document doc;

    public ProduitDAOXMLv2() {
        SAXBuilder sdoc = new SAXBuilder();
        try {
            doc = sdoc.build(uri);
        } catch (Exception e) {
            System.out.println("erreur construction arbre JDOM");
        }
    }

    @Override
    public int create(I_Produit p) {
        try {
            Element root = doc.getRootElement();
            Element prod = new Element("produit");
            Element prodCherche = chercheProduit(p.getNom(), p.getIdCatalogue());
            if(prodCherche == null){
                prod.setAttribute("nom", p.getNom());
                prod.setAttribute("idCatalogue", String.valueOf(p.getIdCatalogue()));
                Element prix = new Element("prixHT");
                prod.addContent(prix.setText(String.valueOf(p.getPrixUnitaireHT())));
                Element qte = new Element("quantite");
                prod.addContent(qte.setText(String.valueOf(p.getQuantite())));
                root.addContent(prod);
                return sauvegarde() ? 1 : -1;
            }
            return -1;


        } catch (Exception e) {
            System.out.println("erreur creer produit");
            return -1;
        }
    }

    @Override
    public I_Produit read(String nom) {
        return null;
    }

    @Override
    public boolean update(I_Produit p) {
        try {
            Element prod = chercheProduit(p.getNom(), p.getIdCatalogue());
            if (prod != null) {
                prod.getChild("quantite").setText(String.valueOf(p.getQuantite()));
                return sauvegarde();
            }
            return false;
        } catch (Exception e) {
            System.out.println("erreur maj produit");
            return false;
        }
    }

    @Override
    public boolean delete(I_Produit p) {
        try {
            Element root = doc.getRootElement();
            Element prod = chercheProduit(p.getNom(), p.getIdCatalogue());
            if (prod != null) {
                root.removeContent(prod);
                return sauvegarde();
            } else
                return false;
        } catch (Exception e) {
            System.out.println("erreur supprimer produit");
            return false;
        }
    }

    @Override
    public List<I_Produit> findAll() {
        return null;
    }
    @Override
    public List<I_Produit> findAllByCatalogue(int idCatalogue) {
        List<I_Produit> l = new ArrayList<>();
        try {
            Element root = doc.getRootElement();
            List<Element> lProd = root.getChildren("produit");
            Predicate<Element> filterByCatalogue = prod -> prod.getAttributeValue("idCatalogue").equals(String.valueOf(idCatalogue));
            lProd = lProd.stream().filter(filterByCatalogue).collect(Collectors.toList());

            for (Element prod : lProd) {
                String nomP = prod.getAttributeValue("nom");
                Double prix = Double.parseDouble(prod.getChild("prixHT").getText());
                int qte = Integer.parseInt(prod.getChild("quantite").getText());
                int idCatalogueProd = Integer.parseInt(prod.getAttributeValue("idCatalogue"));
                l.add(new Produit(nomP, prix, qte, idCatalogueProd));
            }
        } catch (Exception e) {
            System.out.println("erreur lireTous tous les produits");
        }
        return l;
    }

    private boolean sauvegarde() {
        System.out.println("Sauvegarde");
        XMLOutputter out = new XMLOutputter();
        try {
            out.output(doc, new PrintWriter(uri));
            return true;
        } catch (Exception e) {
            System.out.println("erreur sauvegarde dans fichier XML");
            return false;
        }
    }

    private Element chercheProduit(String nom, int idCatalogue) {
        Element root = doc.getRootElement();
        List<Element> lProd = root.getChildren("produit");
        int i = 0;
        while (i < lProd.size()){
            boolean sameNameAndCatalogue =  lProd.get(i).getAttributeValue("nom").equals(nom) && lProd.get(i).getAttributeValue("idCatalogue").equals(String.valueOf(idCatalogue));
            if (sameNameAndCatalogue)
                return lProd.get(i);
            i++;

        }
        return null;
    }
}
