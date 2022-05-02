DROP TABLE PRODUITS CASCADE CONSTRAINT;
CREATE SEQUENCE produit_seq START WITH 1;
CREATE TABLE PRODUITS
(
id NUMBER, nom VARCHAR(30), quantiteStock NUMBER, prixUnitaireHT NUMBER,
CONSTRAINT pk_produits PRIMARY KEY (id),
CONSTRAINT uni_nom UNIQUE (nom)
)

CREATE OR REPLACE FUNCTION creerProduit( p_nom IN PRODUITS.nom%TYPE,  p_quantite IN PRODUITS.quantiteStock%TYPE, p_prixUnitaireHT   IN PRODUITS.prixUnitaireHT%TYPE) IS
can_create NUMBER
BEGIN
    INSERT INTO PRODUITS (id, nom, quantiteStock, prixUnitaireHT) VALUES (produit_seq.NEXTVAL, p_nom, p_quantite, p_prixUnitaireHT);
    can_create := 1;
    RETURN can_create;
END;