DROP Table Clients CASCADE CONSTRAINT;
DROP Table Vendeurs CASCADE CONSTRAINT;
DROP Table EtreSuivi CASCADE CONSTRAINT;
DROP Table Achats CASCADE CONSTRAINT;

CREATE TABLE Clients
(numeroClient NUMBER, nomClient VARCHAR(20), prenomClient VARCHAR(20), categorieClient VARCHAR(10) DEFAULT 'PROSPECT', villeClient VARCHAR(30),
CONSTRAINT pk_Clients PRIMARY KEY (numeroClient),
CONSTRAINT ck_typeClient CHECK (categorieClient IN ('CLIENT', 'PROSPECT')));

CREATE TABLE Vendeurs
(numeroVendeur NUMBER, nomVendeur VARCHAR(20) NOT NULL, prenomVendeur VARCHAR(20) NOT NULL,
CONSTRAINT pk_Vendeurs PRIMARY KEY (numeroVendeur));

CREATE TABLE EtreSuivi
(numeroClient NUMBER, numeroVendeur NUMBER,
CONSTRAINT pk_etreSuivi PRIMARY KEY (numeroClient, numeroVendeur),
CONSTRAINT fk_etresuivi_numeroClient FOREIGN KEY (numeroClient) REFERENCES Clients(numeroClient),
CONSTRAINT fk_etresuivi_numeroVendeur FOREIGN KEY (numeroVendeur) REFERENCES Vendeurs(numeroVendeur));

CREATE TABLE Achats
(referenceAchat VARCHAR(3), montantAchat NUMBER, numeroClient NUMBER, numeroVendeur NUMBER,
CONSTRAINT pk_achats PRIMARY KEY (referenceAchat),
CONSTRAINT fk_achats FOREIGN KEY (numeroClient, numeroVendeur) REFERENCES EtreSuivi(numeroClient, numeroVendeur));

CREATE OR REPLACE TRIGGER limit_vendeur AFTER INSERT ON EtreSuivi
FOR EACH ROW
DECLARE nb_suivi NUMBER;
BEGIN
SELECT COUNT(*) INTO nb_suivi FROM EtreSuivi WHERE numeroClient = :NEW.numeroClient;
IF nb_suivi >= 2
THEN RAISE_APPLICATION_ERROR (-20002, 'Le client est déjà suivi par au moins 3 vendeurs');
END IF;
END;

CREATE OR REPLACE TRIGGER set_client AFTER INSERT ON Achats
FOR EACH ROW
BEGIN
UPDATE Clients
SET categorieClient = 'CLIENT'
WHERE numeroClient = :NEW.numeroClient;
END;