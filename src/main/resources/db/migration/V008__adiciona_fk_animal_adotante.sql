ALTER TABLE animal
ADD COLUMN adotante_id INT;

ALTER TABLE animal
ADD CONSTRAINT fk_animal_adotante
FOREIGN KEY (adotante_id) REFERENCES adotante(id);