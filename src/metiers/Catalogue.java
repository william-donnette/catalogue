package metiers;

import DAO.I_DAOProduits;
import Factory.DAOAbstracteFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue {

    private List<I_Produit> lesProduits;
    private int id;
    private String nom;
    private I_DAOProduits dao;

    public Catalogue(){
        nom = "test";
        lesProduits = new ArrayList<>();
        dao = DAOAbstracteFactory.getInstance().createDAOProduit();
        id = -1;
    }

    public Catalogue(String nom){
        this();
        this.nom = nom;
        id = -1;
    }

    public Catalogue(int id, String nom){
        this(nom);
        this.id = id;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public boolean addProduit(I_Produit produit) {
        if(produit.isValidQuantity() && produit.isValidPrix() || existe(nom) != null || produit.isValidNom()){
            produit.setIdCatalogue(id);
            if (dao.create(produit) != -1){
                lesProduits.add(produit);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        return addProduit(new Produit(nom, prix, qte, id));
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        try {
            int added = 0;
            for(I_Produit pr : l){
                if (addProduit(pr)){
                    added++;
                }
            }
            return added;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public boolean removeProduit(String nom)  {
        I_Produit p;
        if((p = existe(nom)) == null)
            return false;
        if(!lesProduits.remove(p))
            return false;
        return dao.delete(p);
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee)  {
        I_Produit p;
        if((p = existe(nomProduit)) == null || qteAchetee <= 0)
            return false;
        if(!p.ajouter(qteAchetee))
            return false;
        return dao.update(p);
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        I_Produit p;
        if ((p = existe(nomProduit)) == null || qteVendue <= 0)
            return false;
        if(!p.enlever(qteVendue))
            return false;
        return dao.update(p);
    }

    @Override
    public String[] getNomProduits() {
        List<String> listeNomProduits = new ArrayList<>();
        for (I_Produit produit: lesProduits) {
            listeNomProduits.add(produit.getNom());
        }
        String [] listeNoms = new String [listeNomProduits.size()];
        listeNomProduits.toArray(listeNoms);
        return listeNoms;
    }

    @Override
    public double getMontantTotalTTC() {
        double montant = 0;
        for(I_Produit p : lesProduits){
            montant += p.getPrixStockTTC();
        }
        BigDecimal bg = new BigDecimal(montant).setScale(2, RoundingMode.HALF_UP);

        return bg.doubleValue();
    }

    @Override
    public void clear() {
        lesProduits.clear();
    }

    @Override
    public int getNbrProduits() {
        return lesProduits.size();
    }

    @Override
    public boolean isValidNom() {
        return nom != null;
    }

    @Override
    public List<I_Produit> getProduits() {
        lesProduits = dao.findAllByCatalogue(id);
        return lesProduits;
    }

    private I_Produit existe(String nom){
        I_Produit p = null;
        try {
            for (I_Produit produit : lesProduits) {
                if (produit.getNom().equals(nom.trim().replaceAll("\t", " ").replaceAll(" +", " "))) {
                    p = produit;
                    break;
                }
            }
        }catch(NullPointerException e){
        }
        return p;
    }

    @Override
    public String toString() {
        String s = "";
        for (I_Produit p : lesProduits){
            s += p.toString()+"\n";
        }
        String montantTotalTTCAvecVirgule = String.format("%.2f", getMontantTotalTTC()).replaceAll("\\.", ",");
        s += "\nMontant total TTC du stock : "+ montantTotalTTCAvecVirgule +" €";

        return s;
    }

    @Override
    public void setId(int id){
        this.id = id;
    }

    @Override
    public int getId(){
        return id;
    }
}
