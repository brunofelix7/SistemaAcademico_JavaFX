-- Administrador
INSERT INTO `sistemaacademico`.`administrador` (`celular`, `cpf`, `dataAdmissao`, `dataNascimento`, `email`, `login`, `nome`, `rg`, `senha`, `telefone`) 
VALUES ('988340564', '06807087403', '2015-04-09', '1989-11-06', 'bruno.felix10@hotmail.com', 'admin', 'Administrador', '3347570', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918', '3234 2610');

-- Cursos
INSERT INTO curso (nome) VALUES ("Sistemas para Internet");
INSERT INTO curso (nome) VALUES ("Ciência da Computação");
INSERT INTO curso (nome) VALUES ("Gestão de TI");
INSERT INTO curso (nome) VALUES ("Redes de Computadores");

-- Professores
INSERT INTO `sistemaacademico`.`professor` (`celular`, `cpf`, `dataAdmissao`, `dataNascimento`, `email`, `login`, `nome`, `rg`, `senha`, `telefone`, `titulacao`) 
VALUES ("99900 4531", "765.309.009-45", "2014-06-03", "1978-01-18", "rafael@unipe.br", "rafaelJogos_JP", "Rafael Albuquerque", "3454981", "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918", "2106 9877", "Especialista");
INSERT INTO `sistemaacademico`.`professor` (`celular`, `cpf`, `dataAdmissao`, `dataNascimento`, `email`, `login`, `nome`, `rg`, `senha`, `telefone`, `titulacao`) 
VALUES ("99310 1111", "120.090.874-07", "2016-03-22", "1985-08-08", "tiagomaya@unipe.br", "tiagoPHP_JP", "Tiago Maya", "5566777", "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918", "2121 2525", "Especialista");
INSERT INTO `sistemaacademico`.`professor` (`celular`, `cpf`, `dataAdmissao`, `dataNascimento`, `email`, `login`, `nome`, `rg`, `senha`, `telefone`, `titulacao`) 
VALUES ("98714 1326", "543.354.100-03", "2015-03-07", "1980-04-04", "eduardo.ribas@unipe.br", "ribasPESSOA_JP", "Eduardo Ribas", "1700765", "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918", "2106 7777", "Especialista");

-- Disciplinas
INSERT INTO disciplina (nome, periodo, professor_codigo) VALUES ("Algoritmos e Programação", "P1", 1);
INSERT INTO disciplina (nome, periodo, professor_codigo) VALUES ("Programação Dinâmica para Web", "P3", 2);
INSERT INTO disciplina (nome, periodo, professor_codigo) VALUES ("Programação Avançada para Web", "P4", 3);
INSERT INTO disciplina (nome, periodo, professor_codigo) VALUES ("Desenvolvimento de Jogos", "P4", 2);

-- Alunos
INSERT INTO aluno (celular, cpf, dataMatricula, dataNascimento, email, login, nome, rg, senha, telefone) 
VALUES ("83 98834 1123", "068-071-856-03", "2015-02-04", "1989-11-06", "bruno.felix10@hotmail.com", "bruno_JP", "Bruno Felix", "3324576", "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918", "83 3234 1546");
INSERT INTO aluno (celular, cpf, dataMatricula, dataNascimento, email, login, nome, rg, senha, telefone) 
VALUES ("83 98714 3455", "654-123-452-30", "2015-02-04", "1995-06-07", "jmprojects@hotmail.com", "joao_JP", "João Marcus", "2314578", "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918", "83 2106 7656");
INSERT INTO aluno (celular, cpf, dataMatricula, dataNascimento, email, login, nome, rg, senha, telefone) 
VALUES ("83 98834 1123", "123-987-856-03", "2015-02-04", "1995-07-08", "emerson.lemos@hotmail.com", "emerson_JP", "Emerson Lemos", "4347670", "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918", "83 3242 1098");

-- Enderecos
INSERT INTO endereco (bairro, cep, cidade, codigo_aluno, complemento, estado, numero, rua) 
VALUES ("Funcionarios IV", "58079-666", "João Pessoa", 1, "AP 101", "PB", 77, "Rua Com. José Formiga de Assis");
INSERT INTO endereco (bairro, cep, cidade, codigo_aluno, complemento, estado, numero, rua) 
VALUES ("Funcionarios II", "50192-570", "João Pessoa", 2, NULL, "PB", 101, "Rua Maria Carneiro dos Santos");
INSERT INTO endereco (bairro, cep, cidade, codigo_aluno, complemento, estado, numero, rua) 
VALUES ("Bancários", "50012-340", "João Pessoa", 3, "AP 301", "PB", 201, "Av. Sérgio Guerra");

-- Trigger para tabela aluno
DELIMITER $$

CREATE TRIGGER set_id_aluno AFTER INSERT ON aluno
FOR EACH ROW
BEGIN
	UPDATE endereco SET codigo_aluno= NEW.codigo ORDER BY codigo DESC LIMIT 1;
END $$

DELIMITER ;

-- Trigger para tabela professor
DELIMITER $$

CREATE TRIGGER set_id_professor AFTER INSERT ON professor
FOR EACH ROW
BEGIN
	UPDATE disciplina SET professor_codigo= NEW.codigo ORDER BY codigo DESC LIMIT 1;
END $$

DELIMITER ;