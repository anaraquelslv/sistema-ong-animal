TRUNCATE
    animal,
    adotante,
    especie,
    raca
RESTART IDENTITY CASCADE;

-- 1. Inserindo as Espécies (Gato e Cachorro)
INSERT INTO especie (nome) VALUES
                               ('CACHORRO'),
                               ('GATO');

-- 2. Inserindo Raças (Assumindo ID 1=Cachorro, ID 2=Gato)
INSERT INTO raca (nome, especie_id) VALUES
                                        ('SRD (Vira-lata)', 1), -- ID 1
                                        ('Labrador', 1),        -- ID 2
                                        ('Siamês', 2),          -- ID 3
                                        ('Persa', 2);           -- ID 4

-- 3. Inserindo um Adotante (Necessário para o animal com status ADOTADO)
INSERT INTO adotante (
    CPF, RG, orgao_RG, nome, data_nasc, profissao, renda_mensal,
    estado_civil, escolaridade, telefone1, email, logradouro, bairro,
    cidade, estado, CEP, num_endereco
) VALUES (
             '12345678900', '12345678', 'SSP', 'João da Silva', '1990-05-15',
             'Desenvolvedor', 5000.00, 'SOLTEIRO', 'SUPERIOR_COMPLETO',
             '11999999999', 'joao@email.com', 'Rua das Flores', 'Centro',
             'São Paulo', 'SP', '01001000', '100'
         );

-- 4. Inserindo os 5 Animais (Cobrindo todos os status do CHECK)

-- Animal 1: Cachorro SRD, Disponível
INSERT INTO animal (nome, idade, sexo, porte, status, castrado, dt_resgate, raca_id, cor_pelagem, observacao)
VALUES ('Paçoca', 2, 'MACHO', 'MEDIO', 'DISPONIVEL', TRUE, '2023-11-10', 1, 'Caramelo', 'Muito dócil e brincalhão');

-- Animal 2: Gato Siamês, Em Tratamento
INSERT INTO animal (nome, idade, sexo, porte, status, castrado, dt_resgate, raca_id, cor_olhos, observacao)
VALUES ('Luna', 4, 'FEMEA', 'PEQUENO', 'EM_TRATAMENTO', TRUE, '2024-01-05', 3, 'Azul', 'Realizando tratamento dermatológico');

-- Animal 3: Cachorro Labrador, Adotado (Vinculado ao Adotante ID 1)
INSERT INTO animal (nome, idade, sexo, porte, status, castrado, dt_resgate, dt_saida, raca_id, adotante_id)
VALUES ('Thor', 5, 'MACHO', 'GRANDE', 'ADOTADO', TRUE, '2023-06-20', '2023-12-01', 2, 1);

-- Animal 4: Gato Persa, Quarentena (Recém chegado)
INSERT INTO animal (nome, idade, sexo, porte, status, castrado, dt_resgate, raca_id, observacao)
VALUES ('Garfield', 6, 'MACHO', 'MEDIO', 'QUARENTENA', FALSE, CURRENT_DATE, 4, 'Aguardando exames iniciais');

-- Animal 5: Cachorro SRD, Óbito (Infelizmente faleceu)
INSERT INTO animal (nome, idade, sexo, porte, status, castrado, dt_resgate, dt_saida, raca_id, observacao)
VALUES ('Velhinho', 15, 'MACHO', 'PEQUENO', 'OBITO', TRUE, '2023-01-01', '2023-01-15', 1, 'Faleceu de causas naturais devido à idade avançada');