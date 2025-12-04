CREATE TABLE IF NOT EXISTS animal (
	id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    idade INT,
    sexo VARCHAR(10) NOT NULL CHECK (sexo IN ('MACHO', 'FEMEA')),
    porte VARCHAR(10) NOT NULL CHECK (porte IN ('PEQUENO', 'MEDIO', 'GRANDE')),
    castrado BOOLEAN,
    dt_resgate DATE NOT NULL,
    dt_saida DATE,
    cor_olhos VARCHAR(20),
    cor_pelagem VARCHAR(20),
    observacao TEXT
);