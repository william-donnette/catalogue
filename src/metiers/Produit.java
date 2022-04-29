package metiers;

public class Produit implements I_Produit {
    private I_Catalogue catalogue;
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

    public String getNomCatalogue(){
        return catalogue.getNom();
    }

    public void setCatalogue(I_Catalogue catalogue) {
        this.catalogue = catalogue;
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
        String prixUnitaireHTAvecVirgule = String.format("%.2f", prixUnitaireHT).replaceAll("\\.", ",");
        String prixUnitaireTTCAvecVirgule = String.format("%.2f", getPrixUnitaireTTC()).replaceAll("\\.", ",");
        return nom+" - prix HT : "+prixUnitaireHTAvecVirgule+" € - prix TTC : "+prixUnitaireTTCAvecVirgule+" € - quantité en stock : "+quantiteStock;
    }
}
