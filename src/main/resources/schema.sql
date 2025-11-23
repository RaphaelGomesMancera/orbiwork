CREATE TABLE IF NOT EXISTS TRILHA (
                                      ID SERIAL PRIMARY KEY,
                                      NOME VARCHAR(255) NOT NULL,
    DESCRICAO VARCHAR(500) NOT NULL
    );

CREATE TABLE IF NOT EXISTS CURSO (
                                     ID SERIAL PRIMARY KEY,
                                     TITULO VARCHAR(255) NOT NULL,
    CARGA_HORARIA INT NOT NULL,
    TRILHA_ID INT NOT NULL REFERENCES TRILHA(ID)
    );

CREATE TABLE IF NOT EXISTS USUARIO (
                                       ID SERIAL PRIMARY KEY,
                                       NOME VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(255) NOT NULL
    );
