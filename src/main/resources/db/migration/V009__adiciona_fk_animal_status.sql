ALTER TABLE animal
ADD COLUMN status_id INT NOT NULL;

ALTER TABLE animal
ADD CONSTRAINT fk_animal_status
FOREIGN KEY (status_id) REFERENCES status_animal(id);