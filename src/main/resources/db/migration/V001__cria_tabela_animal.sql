CREATE TABLE IF NOT EXISTS animal (
	id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    idade INT CHECK (idade >= 0),
    sexo VARCHAR(10) NOT NULL CHECK (sexo IN ('MACHO', 'FEMEA')),
    porte VARCHAR(10) NOT NULL CHECK (porte IN ('PEQUENO', 'MEDIO', 'GRANDE')),
    castrado BOOLEAN DEFAULT FALSE,
    dt_resgate DATE NOT NULL CHECK (dt_resgate <= CURRENT_DATE),
    dt_saida DATE,
    cor_olhos VARCHAR(20),
    cor_pelagem VARCHAR(20),
    observacao TEXT,
    CHECK (dt_saida IS NULL OR dt_saida >= dt_resgate)
);