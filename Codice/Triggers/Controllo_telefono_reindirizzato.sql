/*Il trigger controlla che il reindirizzamento su Recapito avvenga sencondo le direttive date*/
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


CREATE OR REPLACE TRIGGER CONTROLLO_TELEFONO_REINDIRIZZATO AFTER INSERT ON RECAPITO

FOR EACH ROW EXECUTE FUNCTION controllo_telefono_reindirizzato();