CONTATTIPIU'


DEFINIZIONE DEL DATABASE IN SQL :


-------------------------------------------------------------------------------
Tabella contenente gli indirizzi dei vari CONTATTI:
```SQL 
CREATE TABLE Indirizzo(

    Addr_ID SERIAL NOT NULL,

    Via VARCHAR(60) NOT NULL,

    Citta VARCHAR(50) NOT NULL,

    CAP VARCHAR(12) NOT NULL,

    Nazione VARCHAR(50) NOT NULL,

PRIMARY KEY (Addr_ID),

UNIQUE (Via,Citta,CAP,Nazione)

);
```
-------------------------------------------------------------------------------
Tabella contenente le informazioni del singolo CONATTO:
```SQL
CREATE TABLE Contatto(

    Cont_ID SERIAL NOT NULL,

    Nome VARCHAR(50) NOT NULL,

    Cognome VARCHAR(50) NOT NULL,

    Ind_Foto VARCHAR(260),

    Indirizzo_P INTEGER NOT NULL,
    
FOREIGN KEY (Indirizzo_P) REFERENCES Indirizzo (Addr_ID) 
ON DELETE SET NULL ON UPDATE CASCADE,

PRIMARY KEY (Cont_ID)

);
```
-------------------------------------------------------------------------------
Tabella che rappresenta la relazione tra CONTATTO e INDIRIZZO
```SQL
CREATE TABLE Alloggio(

    Cont_ID INTEGER NOT NULL,

    Addr_ID INTEGER NOT NULL,

FOREIGN KEY (Cont_ID) REFERENCES Contatto (Cont_ID) 
ON DELETE CASCADE ON UPDATE CASCADE,

FOREIGN KEY (Addr_ID) REFERENCES Indirizzo (Addr_ID) 
ON DELETE CASCADE ON UPDATE CASCADE,

PRIMARY KEY (Cont_ID, Addr_ID)

);
```
-------------------------------------------------------------------------------
Creazione dell'enumeration per descrivere il tipo di numero:
```SQL
CREATE TYPE T_TYPE AS ENUM ('Fisso','Mobile');
```
-------------------------------------------------------------------------------
Tabella che contiene le informazioni sul singolo numero di telefono:
```SQL
CREATE TABLE Telefono(

    Numero VARCHAR(11) NOT NULL,

    Prefisso VARCHAR(5) NOT NULL,

    Tipo T_TYPE NOT NULL,

PRIMARY KEY (Numero,Prefisso)

);
```
-------------------------------------------------------------------------------
Tabella che mette in relazione il TELEFONO con il CONTATTO:
```SQL
CREATE TABLE Recapito(
    Rec_ID SERIAL NOT NULL,

    Cont_ID INTEGER NOT NULL,

    Numero VARCHAR(11) NOT NULL,
    
    Prefisso VARCHAR(5) NOT NULL,
    
    Numero_OUT VARCHAR(11),
    
    Prefisso_OUT VARCHAR(5),
    
FOREIGN KEY (Cont_ID) REFERENCES Contatto (Cont_ID) 
ON DELETE CASCADE ON UPDATE CASCADE,

FOREIGN KEY (Numero, Prefisso) REFERENCES Telefono (Numero, Prefisso),

FOREIGN KEY (Numero_OUT, Prefisso_OUT) REFERENCES Telefono (Numero, Prefisso),

PRIMARY KEY (Rec_ID),

UNIQUE (Cont_ID, Numero, Prefisso)

);
```
-------------------------------------------------------------------------------
Tabella che rappresenta il GRUPPO di CONTATTI:
```SQL
CREATE TABLE Gruppo(

    Group_ID SERIAL NOT NULL,

    Nome_G VARCHAR(50) NOT NULL,

    Descrizione VARCHAR(100),

PRIMARY KEY (Group_ID)

);
```
-------------------------------------------------------------------------------
Tabella che rappresenta la relazione tra CONTATTO e GRUPPO:
```SQL
CREATE TABLE Aggregazione(

    Group_ID INTEGER NOT NULL,

    Cont_ID INTEGER NOT NULL,

FOREIGN KEY (Group_ID) REFERENCES Gruppo (Group_ID) 
ON DELETE CASCADE ON UPDATE CASCADE,

FOREIGN KEY (Cont_ID) REFERENCES Contatto (Cont_ID) 
ON DELETE CASCADE ON UPDATE CASCADE,

PRIMARY KEY (Group_ID,Cont_ID)

);
```
-------------------------------------------------------------------------------
Tabella che rappresenta il singolo INDIRIZZO EMAIL del CONTATTO:
```SQL
CREATE TABLE Email(

    Indirizzo VARCHAR(319) NOT NULL,

    Utilizzo VARCHAR(60),

    Cont_ID SERIAL NOT NULL,

FOREIGN KEY (Cont_ID) REFERENCES Contatto (Cont_ID)
ON DELETE CASCADE ON UPDATE CASCADE,

PRIMARY KEY (Indirizzo)

);
```
-------------------------------------------------------------------------------
Tabella che rappresenta l'ACCOUNT del CONTATTO su un determinato MESSAGING PROVIDER:
```SQL
CREATE TABLE MessagingPR(

    PR_ID SERIAL NOT NULL,

    Nickname VARCHAR(50) NOT NULL,

    Frase_Benvenuto VARCHAR(100),
    
    Fornitore VARCHAR(50),
    
    Indirizzo VARCHAR(319) NOT NULL,

FOREIGN KEY (Indirizzo) REFERENCES Email (Indirizzo)
ON DELETE CASCADE ON UPDATE CASCADE,

PRIMARY KEY (PR_ID),

UNIQUE (PR_ID, Indirizzo)

);
```
-------------------------------------------------------------------------------
Il trigger impedisce l'eliminazione di recapiti nel caso in cui ne restino al piú due. Prima viene fatto un controllo sulla chiave esterna riferita a Contatto per assicurarsi non si tratti di una DELETE CASCADE:
```SQL
CREATE OR REPLACE FUNCTION controllo_eliminazione_recapiti_posseduti() RETURNS TRIGGER AS $controllo_eliminazione_recapiti_posseduti$

DECLARE

cont INTEGER := 0;

BEGIN

IF EXISTS(

SELECT *
FROM Contatto AS Cont
WHERE Cont.Cont_ID = old.Cont_ID

) THEN 

SELECT COUNT (Numero) INTO cont
FROM Recapito
WHERE Cont_ID = old.Cont_ID;

IF(Cont <= 2) THEN
RAISE EXCEPTION 'Il contatto possiede solo due o meno recapiti';

END IF;



END IF;

RETURN OLD;

END;


$controllo_eliminazione_recapiti_posseduti$

LANGUAGE plpgsql;

CREATE TRIGGER CONTROLLO_ELIMINAZIONE_RECAPITI_POSSEDUTI BEFORE DELETE ON RECAPITO

FOR EACH ROW EXECUTE FUNCTION controllo_eliminazione_recapiti_posseduti();

```
-------------------------------------------------------------------------------
Il seguente trigger controlla se nessun'altro utente sta utilizzando il numero da eliminare e procedere quindi all'eliminazione del numero dal database (Automatismo)
Inoltre controlla che l'utente al quale si sta tentando di cancellare questo numero non lo stia utilizzando come numero di reindirizzamento:

```SQL
CREATE OR REPLACE FUNCTION controllo_delete_telefono() RETURNS TRIGGER AS $controllo_delete_telefono$


BEGIN

IF(EXISTS (SELECT * FROM RECAPITO AS RE WHERE RE.NUMERO_OUT = OLD.NUMERO AND RE.PREFISSO_OUT=OLD.PREFISSO AND RE.CONT_ID=OLD.CONT_ID )) THEN

RAISE EXCEPTION 'Errore : Non puoi eliminare un numero che stai utilizzando come reindirizzamento';

ELSEIF (NOT EXISTS (SELECT * FROM RECAPITO AS RE WHERE RE.PREFISSO=OLD.PREFISSO AND RE.NUMERO = OLD.NUMERO AND RE.CONT_ID<>OLD.CONT_ID )) THEN

DELETE FROM TELEFONO WHERE TELEFONO.NUMERO=OLD.NUMERO AND TELEFONO.PREFISSO=OLD.PREFISSO;

END IF;




RETURN NULL;
END;

$controllo_delete_telefono$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER CONTROLLO_DELETE_TELEFONO AFTER DELETE ON RECAPITO
FOR EACH ROW EXECUTE FUNCTION controllo_delete_telefono();

```
-------------------------------------------------------------------------------
Il trigger controlla che l'inserimento dei recapiti avvenga nell'ordine corretto: prima Fisso, poi Mobile. In oltre controlla che venga mantenuto l'ordine anche su aggiornamento:

```SQL
CREATE OR REPLACE FUNCTION controllo_ordine_inserimento_recapiti() RETURNS TRIGGER AS $controllo_ordine_inserimento_recapiti$

DECLARE

Tipo_in Telefono.tipo%TYPE;
cont INTEGER := 0;

BEGIN

SELECT tel.tipo INTO Tipo_in
FROM Telefono AS tel
WHERE NEW.numero = tel.numero AND NEW.prefisso = tel.prefisso;

SELECT COUNT (Numero) INTO cont
FROM Recapito
WHERE Cont_ID = new.Cont_ID;

IF(Cont = 0 AND Tipo_in = 'Mobile') THEN

RAISE EXCEPTION 'Inserire prima un Fisso';

END IF;

IF (Cont = 1 AND Tipo_in = 'Fisso') THEN

RAISE EXCEPTION 'Inserire un Mobile';

END IF;


RETURN NEW;

END;

$controllo_ordine_inserimento_recapiti$

LANGUAGE plpgsql;

CREATE TRIGGER CONTROLLO_ORDINE_INSERIMENTO_RECAPITI BEFORE INSERT OR UPDATE ON RECAPITO
FOR EACH ROW EXECUTE FUNCTION controllo_ordine_inserimento_recapiti();
```
-------------------------------------------------------------------------------
Il trigger controlla che il tipo di numero reindirizzato sia sempre esatto sia in modifica che inserimento:
```SQL
CREATE OR REPLACE FUNCTION controllo_telefono_reindirizzato() RETURNS TRIGGER AS $controllo_telefono_reindirizzato$

DECLARE

Tipo_in Telefono.tipo%TYPE;

Tipo_out Telefono.tipo%TYPE;

BEGIN


SELECT tel.tipo INTO Tipo_in

FROM Telefono AS tel

WHERE NEW.numero = tel.numero AND NEW.prefisso = tel.prefisso;

SELECT tel.tipo INTO Tipo_out

FROM Telefono AS tel

WHERE NEW.Numero_OUT = tel.Numero AND NEW.Prefisso_OUT = tel.Prefisso;

IF(Tipo_in = Tipo_out) THEN

RAISE EXCEPTION 'Errore : i tipi non devono essere uguali';

ELSEIF((Tipo_in = 'Fisso') AND (Tipo_out IS NULL) ) THEN

RAISE EXCEPTION 'Errore : un numbero fisso deve avere necessariamente un mobile di reindirizzamento';

END IF;

RETURN NULL;

END;

$controllo_telefono_reindirizzato$ LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER CONTROLLO_TELEFONO_REINDIRIZZATO AFTER INSERT OR UPDATE ON RECAPITO

FOR EACH ROW EXECUTE FUNCTION controllo_telefono_reindirizzato();
```
