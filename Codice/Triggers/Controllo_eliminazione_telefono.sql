/* Il seguente trigger controlla se nessun'altro utente sta utilizzando il numero da eliminare e procedere quindi all'eliminazione del numero dal database*/
/* Inoltre controlla che l'utente al quale si sta tentando di cancellare questo numero non lo stia utilizzando come numero di reindirizzamento da fisso a mobile */


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