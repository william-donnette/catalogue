package metiers;

import DAO.I_DAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Catalogue implements I_Catalogue {

    private List<I_Produit> lesProduits;
    private I_DAO dao;

    public Catalogue(){
        lesProduits = new ArrayList<>();
    }
    public Catalogue(I_DAO dao){
        this.dao =dao;
        lesProduits = new ArrayList<>();
    }


    @Override
    public boolean addProduit(I_Produit produit) {
        try {
            if(produit.getQuantite() < 0 || produit.getPrixUnitaireHT() <= 0 || existe(produit.getNom()) != null)
                return false;
            return lesProduits.add(produit);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        if(qte < 0 || prix <= 0 || existe(nom) != null || nom == null)
            return false;
        return lesProduits.add(new Produit(nom, prix, qte));
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        try {
            int added = 0;
            for(I_Produit pr : l){
                if(existe(pr.getNom()) == null && pr.getPrixUnitaireHT() > 0 && pr.getQuantite() >= 0){
                    lesProduits.add(pr);
                    added++;
                }
            }
            return added;
        } catch (NullPointerException e) {
            e.printStackTrace();
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
        String[] s = new String[lesProduits.size()];
        lesProduits.sort(Comparator.comparing(I_Produit::getNom));
        for(I_Produit p : lesProduits){
            s[lesProduits.indexOf(p)] = p.getNom();
        }
        return s;
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
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public String toString() {
        String s = "";
        for (I_Produit p : lesProduits){
            s += p.toString()+"\n";
        }

        s += "\nMontant total TTC du stock : "+String.format("%.2f", getMontantTotalTTC())+" €";

        return s;
    }
}
