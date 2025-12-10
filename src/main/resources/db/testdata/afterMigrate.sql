TRUNCATE
    animal,
    adotante,
    status_animal,
    especie,
    raca
RESTART IDENTITY CASCADE;

INSERT INTO especie (nome) VALUES
('GATO'),
('CACHORRO');

INSERT INTO raca (nome, especie_id) VALUES
('SRD', 1),         -- Gato
('Persa', 1),       -- Gato
('Siamês', 1),      -- Gato
('Labrador', 2),    -- Cachorro
('Poodle', 2);      -- Cachorro

INSERT INTO status_animal (nome) VALUES
('DISPONIVEL_PARA_ADOCAO'),
('EM_TRATAMENTO'),
('EM_QUARENTENA'),
('ADOTADO'),
('RECEM_RESGATADO'),
('FALECIDO');

INSERT INTO adotante (
    CPF, RG, orgao_RG, nome, data_nasc, profissao, renda_mensal, estado_civil, escolaridade,
    telefone1, telefone2, email, instagram, logradouro, bairro, cidade, estado, CEP, num_endereco, complemento
) VALUES
('12345678901', '11223344', 'SSP', 'Maria Silva', '1990-05-10', 'Professora', 3000.00, 'SOLTEIRO', 'SUPERIOR',
 '11999999999', NULL, 'maria@gmail.com', 'maria_silva', 'Rua A', 'Centro', 'São Paulo', 'SP', '01000000', '123', 'Apto 10'),

('22233344455', '55667788', 'SSP', 'João Pereira', '1985-09-21', 'Engenheiro', 7000.00, 'CASADO', 'SUPERIOR',
 '11988888888', '11977777777', 'joao@gmail.com', 'jp_engineer', 'Rua B', 'Jardins', 'São Paulo', 'SP', '01400000', '55', NULL);

INSERT INTO animal (
    nome, idade, sexo, porte, castrado, dt_resgate, dt_saida, cor_olhos, cor_pelagem,
    observacao, raca_id, adotante_id, status_id
) VALUES
('Mimi', 2, 'FEMEA', 'PEQUENO', TRUE, '2024-01-10', NULL, 'Verde', 'Branca',
 'Gata dócil', 2, NULL, 1),   -- Persa - disponível

('Rex', 3, 'MACHO', 'GRANDE', FALSE, '2024-02-02', NULL, 'Castanho', 'Preta',
 'Muito ativo', 4, NULL, 1),  -- Labrador - disponível

('Luna', 1, 'FEMEA', 'MEDIO', TRUE, '2024-03-05', NULL, 'Azul', 'Cinza',
 'Assustada no resgate', 3, NULL, 2),  -- Siamês - em tratamento

('Thor', 4, 'MACHO', 'GRANDE', TRUE, '2024-01-15', '2024-02-01', 'Castanho', 'Amarela',
 'Adotado rapidamente', 4, 1, 4), -- Labrador adotado por Maria

('Bob', 2, 'MACHO', 'PEQUENO', FALSE, '2024-02-20', NULL, 'Preto', 'Branca',
 'Em quarentena', 5, NULL, 3);  -- Poodle - quarentena