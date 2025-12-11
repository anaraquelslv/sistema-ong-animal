TRUNCATE
    animal,
    adotante,
    status_animal,
    especie,
    raca
RESTART IDENTITY CASCADE;

-- 1. Inserir Espécies (Domínio fixo)
INSERT INTO especie (nome) VALUES
                               ('CACHORRO'), -- ID 1
                               ('GATO');     -- ID 2

-- 2. Inserir Status do Animal
INSERT INTO status_animal (nome) VALUES
                                     ('DISPONIVEL'),    -- ID 1
                                     ('ADOTADO'),       -- ID 2
                                     ('EM_TRATAMENTO'), -- ID 3
                                     ('FALECIDO');      -- ID 4

-- 3. Inserir Raças (Ligadas às espécies)
INSERT INTO raca (nome, especie_id) VALUES
                                        ('Sem Raça Definida (Vira-lata)', 1), -- ID 1 (Cachorro)
                                        ('Labrador', 1),                      -- ID 2 (Cachorro)
                                        ('Sem Raça Definida (Vira-lata)', 2), -- ID 3 (Gato)
                                        ('Siamês', 2),                        -- ID 4 (Gato)
                                        ('Poodle', 1);                        -- ID 5 (Cachorro)

-- 4. Inserir um Adotante (Necessário para testar animais com status ADOTADO)
-- Nota: A data de nascimento deve ser anterior a 21 anos atrás devido ao CHECK constraint.
INSERT INTO adotante (
    CPF, RG, orgao_RG, nome, data_nasc, profissao, renda_mensal,
    estado_civil, escolaridade, telefone1, email, logradouro,
    bairro, cidade, estado, CEP, num_endereco
) VALUES (
             '12345678900', '12.345.678-9', 'SSP/SP', 'Carlos Eduardo Souza', '1990-05-15',
             'Desenvolvedor', 5500.00, 'SOLTEIRO', 'SUPERIOR_COMPLETO',
             '11999998888', 'carlos.souza@email.com', 'Rua das Flores',
             'Centro', 'São Paulo', 'SP', '01001000', '100'
         ); -- ID 1

-- ---------------------------------------------------------
-- 5. INSERTS NA TABELA ANIMAL (O seu pedido principal)
-- ---------------------------------------------------------

-- Animal 1: Cachorro Vira-lata, Disponível, Porte Médio
INSERT INTO animal (
    nome, idade, sexo, porte, castrado, dt_resgate,
    cor_pelagem, observacao, raca_id, status_id
) VALUES (
             'Rex', 3, 'MACHO', 'MEDIO', TRUE, '2023-01-10',
             'Caramelo', 'Muito dócil e brincalhão.', 1, 1
         );

-- Animal 2: Gato Siamês, Em Tratamento, Porte Pequeno
INSERT INTO animal (
    nome, idade, sexo, porte, castrado, dt_resgate,
    cor_olhos, cor_pelagem, observacao, raca_id, status_id
) VALUES (
             'Luna', 2, 'FEMEA', 'PEQUENO', TRUE, '2023-06-20',
             'Azul', 'Bege e Marrom', 'Chegou com a pata machucada.', 4, 3
         );

-- Animal 3: Cachorro Labrador, Adotado (Vinculado ao adotante ID 1)
INSERT INTO animal (
    nome, idade, sexo, porte, castrado, dt_resgate, dt_saida,
    cor_pelagem, raca_id, status_id, adotante_id
) VALUES (
             'Thor', 5, 'MACHO', 'GRANDE', TRUE, '2022-11-01', '2023-02-15',
             'Preto', 2, 2, 1
         );

-- Animal 4: Gato Vira-lata, Disponível, Filhote (0 anos)
INSERT INTO animal (
    nome, idade, sexo, porte, castrado, dt_resgate,
    cor_pelagem, observacao, raca_id, status_id
) VALUES (
             'Mingau', 0, 'MACHO', 'PEQUENO', FALSE, '2023-10-05',
             'Branco e Preto', 'Filhote encontrado na caixa.', 3, 1
         );

-- Animal 5: Cachorro Poodle, Disponível, Idoso
INSERT INTO animal (
    nome, idade, sexo, porte, castrado, dt_resgate,
    cor_pelagem, observacao, raca_id, status_id
) VALUES (
             'Belinha', 12, 'FEMEA', 'PEQUENO', TRUE, '2023-09-01',
             'Branco', 'Necessita de cuidados especiais por conta da idade.', 5, 1
         );