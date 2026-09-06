-- animal é sempre repovoada pelo afterMigrate.sql a cada subida da aplicação,
-- então limpar a tabela aqui não perde dado real, apenas evita violar o NOT NULL
-- abaixo com linhas antigas que não têm responsavel_id.
DELETE FROM animal;

ALTER TABLE animal
ADD COLUMN responsavel_id INT NOT NULL;

ALTER TABLE animal
ADD CONSTRAINT fk_animal_responsavel
FOREIGN KEY (responsavel_id) REFERENCES responsavel(id);
