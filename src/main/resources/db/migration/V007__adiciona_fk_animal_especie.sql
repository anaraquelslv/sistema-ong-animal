ALTER TABLE animal
ADD COLUMN especie_id INT;

ALTER TABLE animal
ADD CONSTRAINT fk_animal_especie
FOREIGN KEY (especie_id) REFERENCES especie(id);