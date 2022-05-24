CREATE OR REPLACE FUNCTION controllo_telefoni_posseduti() RETURNS TRIGGER AS $controllo_controllo_telefoni_posseduti$

DECLARE

cont INTEGER

BEGIN

SELECT CONT (Numero) INTO cont
FROM Recapito
WHERE Cont_ID == new.Cont_ID

IF(Cont <2) THEN
RAISE EXCEPTION 'Il contatto possiede meno di due Telefoni';

END IF;

RETURN NULL;

END;


$controllo_controllo_telefoni_posseduti$

LANGUAGE plpgsql;

CREATE TRIGGER CONTROLLO_TELEFONI_POSSEDUTI AFTER DELETE ON RECAPITO

FOR EACH ROW EXECUTE FUNCTION controllo_telefoni_posseduti();
