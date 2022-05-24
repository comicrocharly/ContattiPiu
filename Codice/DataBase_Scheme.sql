CREATE DATABASE ProgettoBasi;

CREATE SCHEMA ContattiPiu AUTHORIZATION uvmzphxd;


CREATE TABLE Indirizzo(

    Addr_ID SERIAL NOT NULL,

    Via VARCHAR(60) NOT NULL,

    Citta VARCHAR(50) NOT NULL,

    CAP VARCHAR(12) NOT NULL,

    Nazione VARCHAR(50) NOT NULL,

PRIMARY KEY (Addr_ID),

UNIQUE (Via,Citta,CAP,Nazione)

);

CREATE TABLE Contatto(

    Cont_ID SERIAL NOT NULL,

    Nome VARCHAR(50) NOT NULL,

    Cognome VARCHAR(50) NOT NULL,

    Ind_Foto VARCHAR(260),

    Indirizzo_P INTEGER NOT NULL,
    
FOREIGN KEY (Indirizzo_P) REFERENCES Indirizzo (Addr_ID),

PRIMARY KEY (Cont_ID)

);

CREATE TABLE Alloggio(

    Cont_ID INTEGER NOT NULL,

    Addr_ID INTEGER NOT NULL,

FOREIGN KEY (Cont_ID) REFERENCES Contatto (Cont_ID),

FOREIGN KEY (Addr_ID) REFERENCES Indirizzo (Addr_ID),

PRIMARY KEY (Cont_ID, Addr_ID)

);

CREATE TYPE 'T_TYPE' AS ENUM (Fisso,Mobile);

CREATE TABLE Telefono(

    Numero VARCHAR(11) NOT NULL,

    Prefisso VARCHAR(5) NOT NULL,

    Tipo T_TYPE NOT NULL,

PRIMARY KEY (Numero,Prefisso)

);

CREATE TABLE Recapito(
    Rec_ID SERIAL NOT NULL

    Cont_ID INTEGER NOT NULL,

    Numero VARCHAR(11) NOT NULL,
    
    Prefisso VARCHAR(5) NOT NULL,
    
    Numero_OUT VARCHAR(11),
    
    Prefisso_OUT VARCHAR(5),
    
FOREIGN KEY (Cont_ID) REFERENCES Contatto (Cont_ID),

FOREIGN KEY (Numero, Prefisso) REFERENCES Telefono (Numero, Prefisso),

FOREIGN KEY (Numero_OUT, Prefisso_OUT) REFERENCES Telefono (Numero, Prefisso),

PRIMARY KEY (Rec_ID),

UNIQUE (Cont_ID, Numero, Prefisso)

);

CREATE TABLE Gruppo(

    Group_ID SERIAL NOT NULL,

    Nome_G VARCHAR(50) NOT NULL,

    Descrizione VARCHAR(100),

PRIMARY KEY (Group_ID)

);

CREATE TABLE Aggregazione(

    Group_ID INTEGER NOT NULL,

    Cont_ID INTEGER NOT NULL,

FOREIGN KEY (Group_ID) REFERENCES Gruppo (Group_ID),

FOREIGN KEY (Cont_ID) REFERENCES Contatto (Cont_ID),

PRIMARY KEY (Group_ID,Cont_ID)

);

CREATE TABLE Email(

    Indirizzo VARCHAR(319) NOT NULL,

    Utilizzo VARCHAR(60),

    Cont_ID SERIAL NOT NULL,

FOREIGN KEY (Cont_ID) REFERENCES Contatto (Cont_ID),

PRIMARY KEY (Indirizzo)

);

CREATE TABLE MessagingPR(

    PR_ID SERIAL NOT NULL,

    Nickname VARCHAR(50) NOT NULL,

    Frase_Benvenuto VARCHAR(100),
    
    Fornitore VARCHAR(50),
    
    Indirizzo VARCHAR(100) NOT NULL,

FOREIGN KEY (Indirizzo) REFERENCES Email (Indirizzo),

PRIMARY KEY (PR_ID),

UNIQUE (PR_ID, Indirizzo)

);
