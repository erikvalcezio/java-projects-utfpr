/*2) Crie um banco de dados no SGBD com base no DER criado.*/
CREATE SCHEMA atividade02;
USE atividade02;

/*3) Popule o banco de dados criado.*/

CREATE TABLE departamentos (
    cod_dpto integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome_dpto VARCHAR(30)    
);


CREATE TABLE funcionarios (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(20),
    nome_func varchar(50),
    quantidade_dependente integer,
    salario FLOAT(10,2),
    cargo VARCHAR(30),
    data_contratacao DATE,
	 cod_dpto integer NOT NULL,        
    CONSTRAINT funcionario_cod_dpto_fkey FOREIGN KEY (cod_dpto) REFERENCES departamentos(cod_dpto)
);


INSERT INTO departamentos (nome_dpto) VALUES ('Ciencia'),('Desenvolvimento'),('Recursos Humanos'), ('Tecnologia'),('Atendimento ao Publico'),('Diretoria');

INSERT INTO funcionarios (cod_dpto,  nome_func, cpf, quantidade_dependente, salario, cargo , data_contratacao) VALUES 
(1,'Leonard Hofstadter', '12345678910', 0, 19000.00, 'Fisico',  '2021/08/21'),
(1,'Sheldon Cooper', '12345678910', 0, 22000.00, 'Fisico Teorico', '2021/08/19'),
(2,'Howard Wolowitz','12345678910', 1, 14000.00, 'Engeiro', '2021/08/19'),
(3,'Penny','12345678910', 3,2200.00,'Auxiliar Administrativa', '2021/08/19'),
(1,'Sheldon Cooper', '12345678910', 0, 22000.00, 'Fisico Teorico', '2021/08/19'),
(1,'Bernadette Maryann Rostenkowski', '12345678910', 1, 25000.00, 'Microbiologista', '2021/08/19'),
(5,'Stuart Bloom','12345678910', 0, 14000.00, 'Sac', '2021/08/19'),
(3,'Penny', '12345678910', 3, 2200.00,'Auxiliar Administrativa', '2021/08/19'),
(4,'Amy Farrah Fowler', '12345678910', 4, 45200.00,'Dba', '2021/08/19'),
(4,'Rajesh Koothrappali', '12345678910', 0, 41800.00,'Analista de Redes', '2021/08/19'),
(2,'Priya Koothrappali', '12345678910', 2, 48000.00,'Gerente', '2021/08/19'),
(6,'Erik Valcezio', '12345678910', 4, 8000.00,'Diretor', '2021/08/19'),
(3,'Lucky ska walker','12345678910', 0, 22000.00, 'Recrutador', '2021/08/19');


/*4) Faça uma VISÃO (view) para cada uma das seguintes consultas:

a. Mostre o nome do departamento que tem o maior número de funcionários, mostrando também a quantidade de funcionários desse departamento.*/


CREATE VIEW vw_qtd_func_to_dpto AS
SELECT DP.nome_dpto AS dpto_func, COUNT(FC.id) AS qto_func FROM
departamentos DP
JOIN funcionarios FC
ON DP.cod_dpto = FC.cod_dpto
GROUP BY DP.nome_dpto
HAVING COUNT(FC.id)=(SELECT COUNT(FC.id) AS qto_func FROM
departamentos DP
JOIN funcionarios FC
ON DP.cod_dpto = FC.cod_dpto
GROUP BY DP.nome_dpto 
ORDER BY qto_func DESC LIMIT 1);



/*b. Mostre o nome do departamentos que tem a menor quantidade de funcionários sem dependentes, com a quantidade de funcionários.*/

CREATE VIEW vw_dpto_to_qto_dependentes AS
SELECT temp.nome_dpto_func, temp.qto_func, total_dependente  FROM (SELECT DP.nome_dpto AS nome_dpto_func, COUNT(FC.id) AS qto_func, DP.cod_dpto FROM
departamentos DP
JOIN funcionarios FC
ON DP.cod_dpto = FC.cod_dpto
GROUP BY DP.nome_dpto) temp
LEFT JOIN
(SELECT cod_dpto,SUM(quantidade_dependente) AS total_dependente FROM funcionarios 
GROUP BY cod_dpto  
HAVING SUM(quantidade_dependente)=0) temp2
ON temp.cod_dpto = temp2.cod_dpto
WHERE temp2.total_dependente = 0;

/*c. Mostre o nome do departamentos com os nomes dos seus respectivos funcionários de todos os departamentos cujo o nome começa com "DIR" */

CREATE VIEW vw_dpto_to_nome_like_dir AS
SELECT DP.nome_dpto AS nome_dpto, FC.nome_func FROM
departamentos DP
JOIN funcionarios FC
ON DP.cod_dpto = FC.cod_dpto
WHERE DP.nome_dpto LIKE 'DIR%'
GROUP BY DP.nome_dpto;

/*d. Mostre o nome do funcionário e do departamentos ao qual pertence o funcionário com o maior salário.*/

CREATE VIEW vw_func_dpto_sal_max AS
SELECT FC.nome_func, DP.nome_dpto ,MAX(FC.salario) AS MAIOR_SALARIO FROM
departamentos DP
JOIN funcionarios FC
ON DP.cod_dpto = FC.cod_dpto
GROUP BY FC.nome_func
HAVING MAX(FC.salario) = (SELECT MAX(FC.salario) FROM
departamentos DP
JOIN funcionarios FC
ON DP.cod_dpto = FC.cod_dpto);

/*e. Mostre o nome do departamentos e do funcionário de cada departamentos que tem o cargo "Gerente" */

CREATE VIEW vw_depto_func_cargo AS
SELECT DP.nome_dpto AS nome_dpto, FC.nome_func, FC.cargo FROM
departamentos DP
JOIN funcionarios FC
ON DP.cod_dpto = FC.cod_dpto
WHERE FC.cargo = 'Gerente';

/*5) Crie dois usuários para acesso ao banco, sendo que 
a. O usuário "funcionarios" possui com acesso LIMITADO.*/

CREATE USER 'funcionarios'@'localhost' IDENTIFIED BY 'passfunc';

GRANT SELECT, SHOW VIEW ON atividade02.`funcionarios` TO 'funcionarios@localhost';

/*b.O usuário "gerente" possui com acesso completo.*/

CREATE USER 'gerente'@'localhost' IDENTIFIED BY 'passgerente';
GRANT ALL privileges ON `atividade02`.* TO 'gerente'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;

/*Atividade05
DROP TABLE departamentos;
DROP TABLE funcionarios;
create table departamentos (cod_dpto bigint not null auto_increment, nome_dpto varchar(64) not null, primary key (cod_dpto)) engine=INNODB*/

/*EXIBE DADOS*/
SELECT f FROM funcionarios f
LEFT JOIN departamentos d
ON d.cod_dpto = f.cod_dpto
WHERE d.nome_dpto = 'avua';

/*Atividade 06 Repository Spring Data*/
USE atividade02;
DROP PROCEDURE IF EXISTS proc_aumenta_salario;

DELIMITER $$
USE atividade02$$
CREATE PROCEDURE proc_aumenta_salario(IN value_porc_aum INTEGER, OUT resultado VARCHAR(254))

BEGIN
/* AUMENTA O VALOR DO SALARIO EM PORCETAGEM CONFORME VALOR PASSADO */
UPDATE Funcionarios SET SALARIO = SALARIO + ((value_porc_aum * SALARIO) / 100);

SELECT CONCAT('SALARIO AUMENTADO EM ', value_porc_aum , '% PARA TODOS FUNCIONARIOS') INTO resultado;
END$$

/* 
--testando a procedure
SELECT * FROM funcionarios;
CALL proc_aumenta_salario(10);
SELECT * FROM funcionarios;*/



