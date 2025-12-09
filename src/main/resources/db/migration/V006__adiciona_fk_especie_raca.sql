ALTER TABLE especie
ADD COLUMN raca_id INT;

ALTER TABLE especie
ADD CONSTRAINT fk_especie_raca
FOREIGN KEY (raca_id) REFERENCES raca(id);