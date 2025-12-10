ALTER TABLE animal
ADD COLUMN adotante_cpf VARCHAR(11);

ALTER TABLE animal
ADD CONSTRAINT fk_animal_adotante
FOREIGN KEY (adotante_cpf) REFERENCES adotante(CPF);