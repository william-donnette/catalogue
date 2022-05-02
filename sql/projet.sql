-- Partie 2
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
-- Partie 3
CREATE SEQUENCE catalogues_seq START WITH 1;
CREATE TABLE CATALOGUES
(
 idCatalogue NUMBER , nom VARCHAR (30),
 CONSTRAINT pk_catalogues PRIMARY KEY (idCatalogue),
 CONSTRAINT uni_nom_catalogues UNIQUE (nom)
);

ALTER TABLE PRODUITS
ADD idCatalogue NUMBER;
ALTER TABLE PRODUITS
ADD CONSTRAINT fk_idCatalogue_produits FOREIGN KEY (idCatalogue) REFERENCES CATALOGUES(idCatalogue) ON DELETE CASCADE;

CREATE OR REPLACE FUNCTION creerCatalogue( p_nom IN CATALOGUES.nom%TYPE) RETURN NUMBER
IS
id NUMBER;
BEGIN
    id := catalogues_seq.NEXTVAL;
    INSERT INTO CATALOGUES(idCatalogue, nom) VALUES (id, p_nom);
    RETURN id;
END;

CREATE OR REPLACE FUNCTION creerProduit( p_nom IN PRODUITS.nom%TYPE,  p_quantite IN PRODUITS.quantiteStock%TYPE,  p_prixUnitaireHT IN PRODUITS.prixUnitaireHT%TYPE,  p_idCatalogue IN CATALOGUES.idCatalogue%TYPE)
RETURN NUMBER
IS can_create NUMBER;
BEGIN

    SELECT COUNT(*) INTO can_create FROM PRODUITS WHERE nom = p_nom AND idCatalogue = p_idCatalogue;
    IF can_create > 0
    THEN can_create := -1;
    ELSE
    INSERT INTO PRODUITS (id, nom, quantiteStock, prixUnitaireHT, idCatalogue) VALUES (produit_seq.NEXTVAL, p_nom, p_quantite, p_prixUnitaireHT, p_idCatalogue);
    can_create := 1;
    END IF;
    RETURN can_create;
END;

