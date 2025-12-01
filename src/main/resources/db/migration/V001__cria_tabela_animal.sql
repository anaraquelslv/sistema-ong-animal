CREATE TABLE IF NOT EXISTS animal (
  id INT PRIMARY KEY,
  nome VARCHAR(50),
  idade INT,
  sexo VARCHAR(10) CHECK (sexo IN ('macho', 'femea')),
  porte VARCHAR(10) CHECK (porte IN ('pequeno', 'medio', 'grande')),
  castrado BOOLEAN,
  dt_resgate DATE,
  dt_saida DATE,
  cor_olhos VARCHAR(20),
  cor_pelagem VARCHAR(20),
  observacao TEXT
);