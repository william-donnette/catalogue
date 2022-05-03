package DAO;

import metiers.Catalogue;
import metiers.I_Catalogue;
import metiers.I_Produit;
import metiers.Produit;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CatalogueXMLDAO implements I_DAO<I_Catalogue> {
    private String uri = "C:/XAMPP/htdocs/catalogue2/rendu3/Catalogues.xml";
    private Document doc;

    public CatalogueXMLDAO(){
        SAXBuilder sdoc = new SAXBuilder();
        try {
            doc = sdoc.build(uri);
        } catch (Exception e) {
            System.out.println("erreur construction arbre JDOM");
        }
    }

    @Override
    public int create(I_Catalogue c) {
        try {
            Element root = doc.getRootElement();
            Element cat = new Element("catalogue");
            cat.setAttribute("id", String.valueOf(getLast().getId() + 1));
            Element nom = new Element("nom");
            cat.addContent(nom.setText(String.valueOf(c.getNom())));
            root.addContent(cat);
            return sauvegarde() ? 1 : 0;
        } catch (Exception e) {
            System.out.println("erreur creer produit");
            return 0;
        }
    }

    @Override
    public I_Catalogue read(String nom) {
        Element e = chercheCatalogue(nom);
        if (e != null)
            return new Catalogue(e.getAttributeValue("nom"));
        else
            return null;
    }

    @Override
    public boolean update(I_Catalogue object) {
        return false;
    }

    @Override
    public boolean delete(I_Catalogue object) {
        try {
            Element root = doc.getRootElement();
            Element cat = chercheCatalogue(object.getNom());
            if (cat != null) {
                root.removeContent(cat);
                return sauvegarde();
            } else
                return false;
        } catch (Exception e) {
            System.out.println("erreur supprimer catalogue");
            return false;
        }
    }

    @Override
    public List<I_Catalogue> findAll() {
        List<I_Catalogue> l = new ArrayList<I_Catalogue>();
        try {
            Element root = doc.getRootElement();
            List<Element> lCat = root.getChildren("catalogue");

            for (Element cat : lCat) {
                int id = Integer.parseInt(cat.getAttributeValue("id"));
                String nom = cat.getChild("nom").getText();
                l.add(new Catalogue(id, nom));
            }
        } catch (Exception e) {
            System.out.println("erreur lireTous tous les catalogues");
        }
        return l;
    }

    private Element chercheCatalogue(String nom) {
        Element root = doc.getRootElement();
        List<Element> lProd = root.getChildren("catalogue");
        int i = 0;
        while (i < lProd.size() && !lProd.get(i).getChild("nom").getText().equals(nom))
            i++;
        if (i < lProd.size())
            return lProd.get(i);
        else
            return null;
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

    private I_Catalogue getLast(){
        List<I_Catalogue> catalogues = findAll();
        return catalogues.get(catalogues.size()-1);
    }

}
