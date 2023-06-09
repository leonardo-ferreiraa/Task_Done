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
USU_LOG_ACESSO TIMESTAMP NOT NULL    
);





CREATE TABLE TB_TAREFAS(
TR_ID SERIAL NOT NULL PRIMARY KEY,
TR_TITULO VARCHAR(70) NOT NULL,
TR_TAREFA VARCHAR (500) NOT NULL,
TR_CONCLUIDO BOOLEAN NOT NULL DEFAULT FALSE,
TR_DATA_CADASTRO TIMESTAMP NOT NULL,
TR_LOG TIMESTAMP NOT NULL,   
TR_ATIVO BOOLEAN NOT NULL DEFAULT TRUE,
TR_USU_ID INTEGER REFERENCES TB_USUARIOS(USU_ID)   
);


--==================================== JOB / TRIGGER APAGA TAREFAS DESATIVADAS  =========================================

--==== CRIA FUNÇÃO QUE APAGA AS TAREFAS DESATIVADAS 

CREATE OR REPLACE FUNCTION FN_DELETA_LOG_TAREFAS()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM TB_TAREFAS WHERE TR_ATIVO = 0 AND DATE_PART('DAY', TIMESTAMP 'now' - NEW.TR_LOG) >= 1;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

--==== CRIA TRIGGER QUE ACIONA A FUNÇÃO

--CREATE TRIGGER TRG_DELETA_TAREFAS_DESATIVADAS
--BEFORE DELETE ON TB_TAREFAS
--FOR EACH ROW 
--EXECUTE FUNCTION FN_DELETA_LOG_TAREFAS();


--==== CRIA JOB PARA ACIONAR A TRIGGER

SELECT pgagent.pga_job_step(
  pjobid := DB_TAREFAS.pga_job_add(
    pname := 'Apaga_Tarefas_inativadas',
    pjobdesc := 'Executa FN_DELETA_LOG_TAREFAS() uma vez na semana'
  ),
  pjobstep := pgagent.pga_step_add(
    pjobid := 1,
    pstepname := 'step aciona FN_DELETA_LOG_TAREFAS',
    pstepkind := 's',
    psteptype := 'plpgsql',
    pstepcmd := 'SELECT FN_DELETA_LOG_TAREFAS()'
  )
);




-- Agendar o job para ser executado 
SELECT DB_TAREFAS.pga_schedule_add(
  pschedagentid := 1,
  pschedname := 'Agendar Job',
  pscheddesc := 'Aciona Job Semanalmente',
  pjobs := ARRAY[1],
  pminutes := ARRAY[0],
  pdays := NULL,
  pmonths := NULL,
  pweekdays := ARRAY[1],
  pstart := '20:00:00'::TIME,
  pend := NULL
);






--DELETE FROM TB_TAREFAS 
--WHERE TR_ATIVO = 0 AND DATE_PART('DAY', TIMESTAMP 'now' - TR_LOG) >= 1;





INSERT INTO TB_USUARIOS(USU_USUARIO, USU_SENHA, USU_NIVEL_ACESSO, USU_NOME, USU_IDADE, USU_TELEFONE, USU_DATA_CADASTRO, USU_LOG_ACESSO) VALUES ('LEONARDO', '12345', 1, 'LEONARDO FERREIRA', 20, '99975264', NOW(),  NOW())   
INSERT INTO TB_TAREFAS (TR_TITULO, TR_TAREFA, TR_CONCLUIDO, TR_DATA_CADASTRO, TR_LOG, TR_ATIVO, TR_USU_ID) VALUES ('TESTE', 'TESTE', TRUE, NOW(), NOW(), TRUE, 1) 
INSERT INTO TB_TAREFAS (TR_TITULO, TR_TAREFA, TR_CONCLUIDO, TR_DATA_CADASTRO, TR_LOG, TR_ATIVO, TR_USU_ID) VALUES ('TESTE', 'TESTE', NOW(), '2023-04-20 00:00:00.00000', FALSE, 1) 

SELECT * FROM TB_USUARIOS;
SELECT * FROM TB_TAREFAS;
SELECT TR_TITULO, TR_TAREFA FROM TB_TAREFAS WHERE USU_ID = 1


DROP TRIGGER TRG_DELETA_TAREFAS_DESATIVADAS ON TB_TAREFAS
DROP FUNCTION FN_DELETA_LOG_TAREFAS()
DROP TABLE TB_TAREFAS
DROP TABLE TB_USUARIOS



SELECT * FROM pg_extension WHERE extname = 'pgagent'




/*
ALTERAÇÕES:
-TR_ATIVO AGORA É UM BOOLEAN, NÃO MAIS UM INT

*/



