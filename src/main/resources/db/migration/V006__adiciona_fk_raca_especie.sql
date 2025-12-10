ALTER TABLE raca
ADD COLUMN especie_id INT;

ALTER TABLE raca
ADD CONSTRAINT fk_raca_especie
FOREIGN KEY (especie_id) REFERENCES especie(id);