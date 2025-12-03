CREATE TABLE IF NOT EXISTS animal (
	id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    idade INT,
    sexo VARCHAR(10) CHECK (sexo IN ('MACHO', 'FÊMEA')),
    porte VARCHAR(10) CHECK (porte IN ('PEQUENO', 'MÉDIO', 'GRANDE')),
    castrado BOOLEAN,
    dt_resgate DATE,
    dt_saida DATE,
    cor_olhos VARCHAR(20),
    cor_pelagem VARCHAR(20),
    observacao TEXT
);