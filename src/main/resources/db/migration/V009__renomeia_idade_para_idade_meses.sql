ALTER TABLE animal RENAME COLUMN idade TO idade_meses;
ALTER TABLE animal DROP CONSTRAINT IF EXISTS animal_idade_check;
ALTER TABLE animal ADD CONSTRAINT animal_idade_meses_check CHECK (idade_meses >= 0);
