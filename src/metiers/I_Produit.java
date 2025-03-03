package metiers;

public interface I_Produit {

	public abstract boolean ajouter(int qteAchetee);
	public abstract boolean enlever(int qteVendue);
	public abstract String getNom();
	public abstract int getQuantite();
	public abstract double getPrixUnitaireHT();
	public abstract double getPrixUnitaireTTC();
	public abstract double getPrixStockTTC();
	public abstract int getId();
	public abstract String toString();
	public abstract void setIdCatalogue(int idCatalogue);
	public abstract int getIdCatalogue();
    boolean isValidQuantity();

	boolean isValidPrix();

	boolean isValidNom();
}