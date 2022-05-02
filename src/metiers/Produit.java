package metiers;

public class Produit implements I_Produit {
    private int id;
    private int quantiteStock;
    private String nom;
    private double prixUnitaireHT;
    private int idCatalogue;
    private static double tauxTVA = 0.2;

    public Produit(String nom, double prixUnitaireHT,int quantiteStock, int idCatalogue) {
        this.nom = nom.trim().replaceAll("\t", " ").replaceAll(" +", " ");
        this.prixUnitaireHT = prixUnitaireHT;
        this.quantiteStock = quantiteStock;
        this.idCatalogue = idCatalogue;
    }

    public Produit(String nom, double prixUnitaireHT,int quantiteStock) {
        this.nom = nom.trim().replaceAll("\t", " ").replaceAll(" +", " ");
        this.prixUnitaireHT = prixUnitaireHT;
        this.quantiteStock = quantiteStock;
        this.idCatalogue = -1;
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

    public boolean isValidQuantity() {
        return quantiteStock >= 0;
    }

    public boolean isValidPrix(){
        return prixUnitaireHT > 0;
    }

    @Override
    public boolean isValidNom() {
        return nom != null;
    }

    @Override
    public String toString() {
        String prixUnitaireHTAvecVirgule = String.format("%.2f", prixUnitaireHT).replaceAll("\\.", ",");
        String prixUnitaireTTCAvecVirgule = String.format("%.2f", getPrixUnitaireTTC()).replaceAll("\\.", ",");
        return nom+" - prix HT : "+prixUnitaireHTAvecVirgule+" € - prix TTC : "+prixUnitaireTTCAvecVirgule+" € - quantité en stock : "+quantiteStock;
    }

    @Override
    public void setIdCatalogue(int idCatalogue){
        this.idCatalogue = idCatalogue;
    }

    @Override
    public int getIdCatalogue() {
        return idCatalogue;
    }
}
