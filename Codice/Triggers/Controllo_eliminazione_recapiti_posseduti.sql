/*Il trigger impedisce l'eliminazione di recapiti nel caso in cui ne rimangano solo due. Prima viene fatto un controllo sulla chiave esterna riferita a Contatto per assicurarsi non si tratti di una DELETE CASCADE*/

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
