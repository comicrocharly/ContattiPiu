/*Il trigger controlla che l'inserimento dei recapiti avvenga nell'ordine corretto: prima Fisso, poi Mobile*/
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

CREATE TRIGGER CONTROLLO_ORDINE_INSERIMENTO_RECAPITI BEFORE INSERT ON RECAPITO

FOR EACH ROW EXECUTE FUNCTION controllo_ordine_inserimento_recapiti();