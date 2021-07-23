/*2) Crie um banco de dados no SGBD com base no DER criado.*/
CREATE SCHEMA atividade02;
USE atividade02;

/*3) Popule o banco de dados criado.*/

CREATE TABLE departamento (
    cod_dpto integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome_dpto VARCHAR(30)    
);


CREATE TABLE funcionario (
    cod_func integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cod_dpto integer NOT NULL,
    nome_func varchar(50),
    qtd_dependente integer,
    salario FLOAT(10,2),
    cargo VARCHAR(30),        
    CONSTRAINT funcionario_cod_dpto_fkey FOREIGN KEY (cod_dpto) REFERENCES departamento(cod_dpto)
);


INSERT INTO departamento (nome_dpto) VALUES ('Ciencia'),('Desenvolvimento'),('Recursos Humanos'), ('Tecnologia'),('Atendimento ao Publico'),('Diretoria');

INSERT INTO funcionario (cod_dpto, nome_func, qtd_dependente, salario, cargo) VALUES (1,'Leonard Hofstadter',0, 19000.00, 'Fisico'),
(1,'Sheldon Cooper',0, 22000.00, 'Fisico Teorico'),
(2,'Howard Wolowitz',1, 14000.00, 'Engeiro'),
(3,'Penny',3,2200.00,'Auxiliar Administrativa'),
(1,'Sheldon Cooper',0, 22000.00, 'Fisico Teorico'),
(1,'Bernadette Maryann Rostenkowski',1, 25.000, 'Microbiologista'),
(5,'Stuart Bloom',0, 14000.00, 'Sac'),
(3,'Penny',3,2200.00,'Auxiliar Administrativa'),
(4,'Amy Farrah Fowler',4,5200.00,'Dba'),
(4,'Rajesh Koothrappali',4,1800.00,'Analista de Redes'),
(2,'Priya Koothrappali',4,8000.00,'Gerente'),
(6,'Erik Valcezio',4,8000.00,'Diretor'),
(3,'Lucky ska walker',0, 22000.00, 'Recrutador');


/*4) Faça uma VISÃO (view) para cada uma das seguintes consultas:

a. Mostre o nome do departamento que tem o maior número de funcionários, mostrando também a quantidade de funcionários desse departamento.*/


CREATE VIEW vw_qtd_func_to_dpto AS
SELECT DP.nome_dpto AS dpto_func, COUNT(FC.cod_func) AS qto_func FROM
departamento DP
JOIN funcionario FC
ON DP.cod_dpto = FC.cod_dpto
GROUP BY DP.nome_dpto
HAVING COUNT(FC.cod_func)=(SELECT COUNT(FC.cod_func) AS qto_func FROM
departamento DP
JOIN funcionario FC
ON DP.cod_dpto = FC.cod_dpto
GROUP BY DP.nome_dpto 
ORDER BY qto_func DESC LIMIT 1);


/*b. Mostre o nome do departamento que tem a menor quantidade de funcionários sem dependentes, com a quantidade de funcionários.*/

CREATE VIEW vw_dpto_to_qto_dependentes AS
SELECT temp.nome_dpto_func, temp.qto_func, total_dependente  FROM (SELECT DP.nome_dpto AS nome_dpto_func, COUNT(FC.cod_func) AS qto_func, DP.cod_dpto FROM
departamento DP
JOIN funcionario FC
ON DP.cod_dpto = FC.cod_dpto
GROUP BY DP.nome_dpto) temp
LEFT JOIN
(SELECT cod_dpto,SUM(qtd_dependente) AS total_dependente FROM funcionario 
GROUP BY cod_dpto  
HAVING SUM(qtd_dependente)=0) temp2
ON temp.cod_dpto = temp2.cod_dpto
WHERE temp2.total_dependente = 0;

/*c. Mostre o nome do departamento com os nomes dos seus respectivos funcionários de todos os departamentos cujo o nome começa com "DIR" */

CREATE VIEW vw_dpto_to_nome_like_dir AS
SELECT DP.nome_dpto AS nome_dpto, FC.nome_func FROM
departamento DP
JOIN funcionario FC
ON DP.cod_dpto = FC.cod_dpto
WHERE DP.nome_dpto LIKE 'DIR%'
GROUP BY DP.nome_dpto;

/*d. Mostre o nome do funcionário e do departamento ao qual pertence o funcionário com o maior salário.*/

CREATE VIEW vw_func_dpto_sal_max AS
SELECT FC.nome_func, DP.nome_dpto ,MAX(FC.salario) AS MAIOR_SALARIO FROM
departamento DP
JOIN funcionario FC
ON DP.cod_dpto = FC.cod_dpto
GROUP BY FC.nome_func
HAVING MAX(FC.salario) = (SELECT MAX(FC.salario) FROM
departamento DP
JOIN funcionario FC
ON DP.cod_dpto = FC.cod_dpto);

/*e. Mostre o nome do departamento e do funcionário de cada departamento que tem o cargo "Gerente" */

CREATE VIEW vw_depto_func_cargo AS
SELECT DP.nome_dpto AS nome_dpto, FC.nome_func, FC.cargo FROM
departamento DP
JOIN funcionario FC
ON DP.cod_dpto = FC.cod_dpto
WHERE FC.cargo = 'Gerente';

/*5) Crie dois usuários para acesso ao banco, sendo que 
a. O usuário "funcionario" possui com acesso LIMITADO.*/

CREATE USER 'funcionario'@'localhost' IDENTIFIED BY 'passfunc';

GRANT SELECT, SHOW VIEW ON atividade02.`funcionario` TO 'funcionario@localhost';

/*b.O usuário "gerente" possui com acesso completo.*/

CREATE USER 'gerente'@'localhost' IDENTIFIED BY 'passgerente';
GRANT ALL privileges ON `atividade02`.* TO 'gerente'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;





