-- animal e responsavel são sempre repovoados pelo afterMigrate.sql a cada subida
-- da aplicação, então limpar aqui não perde dado real; apenas evita violar o
-- NOT NULL abaixo com linhas antigas sem tipo_id (animal precisa ser limpo
-- primeiro por causa da FK obrigatória para responsavel).
DELETE FROM animal;
DELETE FROM responsavel;

ALTER TABLE responsavel
ADD COLUMN tipo_id INT NOT NULL;

ALTER TABLE responsavel
ADD CONSTRAINT fk_responsavel_tipo
FOREIGN KEY (tipo_id) REFERENCES tipo(id);
