TRUNCATE
    animal,
    adotante,
    status_animal,
    especie,
    raca
RESTART IDENTITY CASCADE;

INSERT INTO raca (nome) VALUES
('SRD'),
('Vira-lata'),
('Persa'),
('Siamês'),
('Labrador');

INSERT INTO especie (nome, raca_id) VALUES
('GATO', 3),      -- Persa
('GATO', 4),      -- Siamês
('CACHORRO', 1),  -- SRD
('CACHORRO', 2),  -- Vira-lata
('CACHORRO', 5);  -- Labrador

INSERT INTO status_animal (nome, devolvido) VALUES
('RECEM-RESGATADO', FALSE),
('EM TRATAMENTO', FALSE),
('DISPONIVEL PARA ADOCAO', FALSE),
('ADOTADO', FALSE),
('EM QUARENTENA', FALSE);

INSERT INTO adotante (
    CPF, RG, orgao_RG, nome, data_nasc, profissao, renda_mensal, estado_civil,
    escolaridade, telefone1, telefone2, email, instagram,
    logradouro, bairro, cidade, estado, CEP, num_endereco, complemento
) VALUES
('12345678901', '1234567', 'SSP', 'Maria Silva', '1990-05-10', 'Professora', 3500.00,
 'SOLTEIRO', 'SUPERIOR COMPLETO', '11999990000', NULL, 'maria@email.com',
 '@maria', 'Rua A', 'Centro', 'São Paulo', 'SP', '01000000', '100', NULL);

 INSERT INTO animal
(nome, idade, sexo, porte, castrado, dt_resgate, dt_saida, cor_olhos, cor_pelagem, observacao, especie_id, adotante_cpf, status_id)
VALUES
('Thor', 3, 'MACHO', 'GRANDE', TRUE, '2024-01-10', NULL, 'Castanhos', 'Preta', 'Muito dócil', 5, NULL, 1),
('Mimi', 2, 'FEMEA', 'PEQUENO', FALSE, '2024-02-01', NULL, 'Azuis', 'Branca', 'Medrosa', 1, NULL, 2),
('Rex', 5, 'MACHO', 'MEDIO', TRUE, '2023-12-20', '2024-03-01', 'Castanhos', 'Caramelo', 'Adotado rapidamente', 3, '12345678901', 4),
('Luna', 1, 'FEMEA', 'PEQUENO', FALSE, '2024-03-15', NULL, 'Verdes', 'Cinza', 'Filhote', 2, NULL, 3),
('Bob', 4, 'MACHO', 'MEDIO', FALSE, '2023-11-10', NULL, 'Castanhos', 'Amarelo', 'Aguardando avaliação', 4, NULL, 5);