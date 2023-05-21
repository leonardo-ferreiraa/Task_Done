CREATE DATABASE DB_TAREFAS


CREATE TABLE TB_USUARIOS(
USU_ID SERIAL NOT NULL PRIMARY KEY,
USU_USUARIO VARCHAR(30) NOT NULL,
USU_SENHA VARCHAR (32) NOT NULL,
USU_NIVEL_ACESSO INT , 
USU_NOME VARCHAR(70) NOT NULL,
USU_IDADE INT NOT NULL,  
USU_TELEFONE VARCHAR (20) NOT NULL,
USU_DATA_CADASTRO TIMESTAMP NOT NULL,       
);


CREATE TABLE TB_TAREFAS(
TR_ID SERIAL NOT NULL PRIMARY KEY,
TR_TITULO VARCHAR(70) NOT NULL,
TR_TAREFA VARCHAR (500) NOT NULL,
TR_CONCLUIDO INT NOT NULL,
TR_DATA_CADASTRO TIMESTAMP NOT NULL,
TR_LOG TIMESTAMP NOT NULL,   
TR_ATIVO INT NOT NULL,
TR_USU_ID INTEGER REFERENCES TB_USUARIOS(USU_ID)   
);


--====================== TRIGGER APAGA TAREFAS DESATIVADAS  ==================================
CREATE OR REPLACE FUNCTION FN_DELETA_LOG_TAREFAS()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM TB_TAREFAS WHERE TR_ATIVO = 0 AND DATE_PART('DAY', TIMESTAMP 'now' - NEW.TR_LOG) >= 1;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER TRG_DELETA_TAREFAS_DESATIVADAS
BEFORE DELETE ON TB_TAREFAS
FOR EACH ROW 
EXECUTE FUNCTION FN_DELETA_LOG_TAREFAS();





--DELETE FROM TB_TAREFAS 
--WHERE TR_ATIVO = 0 AND DATE_PART('DAY', TIMESTAMP 'now' - TR_LOG) >= 1;





INSERT INTO TB_USUARIOS(USU_NOME, USU_IDADE, USU_TELEFONE, USU_DATA_CADASTRO, USU_LG_ID ) VALUES ('LEONARDO', '12345', 1, 'LEONARDO FERREIRA', 20, '99975264', NOW(), 1)   
INSERT INTO TB_TAREFAS (TR_TITULO, TR_TAREFA, TR_DATA_CADASTRO, TR_LOG, TR_ATIVO, TR_USU_ID) VALUES ('TESTE', 'TESTE', NOW(), NOW(), 1, 1) 
INSERT INTO TB_TAREFAS (TR_TITULO, TR_TAREFA, TR_DATA_CADASTRO, TR_LOG, TR_ATIVO, TR_USU_ID) VALUES ('TESTE', 'TESTE', NOW(), '2023-04-20 00:00:00.00000', 0, 1) 



DROP TRIGGER TRG_DELETA_TAREFAS_DESATIVADAS ON TB_TAREFAS
DROP FUNCTION FN_DELETA_LOG_TAREFAS()
DROP TABLE TB_TAREFAS
DROP TABLE TB_USUARIOS









