package metiers;

import javax.lang.model.type.NullType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Produit implements I_Produit {
    private int id;
    private int quantiteStock;
    private String nom;
    private double prixUnitaireHT;
    private static double tauxTVA = 0.2;

    public Produit(String nom, double prixUnitaireHT,int quantiteStock) {
        if(nom != null) {
            this.quantiteStock = quantiteStock;
            this.nom = nom.trim().replaceAll("\t", " ").replaceAll(" +", " ");
            this.prixUnitaireHT = prixUnitaireHT;
        }
    }

    @Override
    public boolean ajouter(int qteAchetee) {
        if(qteAchetee < 0)
            return false;
        quantiteStock += qteAchetee;
        return true;
    }

    @Override
    public boolean enlever(int qteVendue) {
        if(qteVendue < 0 || qteVendue > quantiteStock)
            return false;
        quantiteStock -= qteVendue;
        return true;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public int getQuantite() {
        return quantiteStock;
    }

    @Override
    public double getPrixUnitaireHT() {
        return prixUnitaireHT;
    }

    @Override
    public double getPrixUnitaireTTC() {
        return prixUnitaireHT+tauxTVA*prixUnitaireHT;
    }

    @Override
    public double getPrixStockTTC() {
        return getPrixUnitaireTTC()*quantiteStock;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nom+" - prix HT : "+String.format("%.2f", prixUnitaireHT)+" € - prix TTC : "+String.format("%.2f", getPrixUnitaireTTC())+" € - quantité en stock : "+quantiteStock;
    }
}
