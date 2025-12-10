ALTER TABLE animal
ADD COLUMN raca_id INT;

ALTER TABLE animal
ADD CONSTRAINT fk_animal_raca
FOREIGN KEY (raca_id) REFERENCES raca(id);