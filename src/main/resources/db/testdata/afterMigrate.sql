TRUNCATE
    animal,
    adotante,
    especie,
    raca,
    tipo,
    responsavel
RESTART IDENTITY CASCADE;

-- 1. Inserindo as Espécies (Gato e Cachorro)
INSERT INTO especie (nome) VALUES
                               ('CACHORRO'),
                               ('GATO');

-- 1.1 Inserindo os Tipos de Responsável (Assumindo ID 1=Abrigo, 2=Lar Temporário, 3=ONG, 4=Protetor Independente)
INSERT INTO tipo (nome) VALUES
                            ('ABRIGO'),
                            ('LAR_TEMPORARIO'),
                            ('ONG'),
                            ('PROTETOR_INDEPENDENTE');

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

-- 4. Inserindo os Responsáveis (origem dos animais resgatados)
INSERT INTO responsavel (nome, cpf, telefone1, email, logradouro, bairro, cidade, estado, cep, num_endereco, tipo_id)
VALUES ('Maria Protetora', '98765432100', '11988887777', 'maria.protetora@email.com', 'Rua dos Protetores', 'Vila Nova', 'São Paulo', 'SP', '02002000', '50', 4);

INSERT INTO responsavel (nome, cnpj, telefone1, email, logradouro, bairro, cidade, estado, cep, num_endereco, tipo_id)
VALUES ('ONG Patinhas Carentes', '11444777000161', '1133334444', 'contato@patinhascarentes.org', 'Avenida dos Animais', 'Jardim Esperança', 'São Paulo', 'SP', '03003000', '200', 3);

-- 5. Inserindo os 5 Animais (Cobrindo todos os status do CHECK)

-- Animal 1: Cachorro SRD, Disponível
INSERT INTO animal (nome, idade_meses, sexo, porte, status, castrado, dt_resgate, raca_id, cor_pelagem, observacao, responsavel_id)
VALUES ('Paçoca', 24, 'MACHO', 'MEDIO', 'DISPONIVEL', TRUE, '2023-11-10', 1, 'Caramelo', 'Muito dócil e brincalhão', 1);

-- Animal 2: Gato Siamês, Em Tratamento
INSERT INTO animal (nome, idade_meses, sexo, porte, status, castrado, dt_resgate, raca_id, cor_olhos, observacao, responsavel_id)
VALUES ('Luna', 48, 'FEMEA', 'PEQUENO', 'EM_TRATAMENTO', TRUE, '2024-01-05', 3, 'Azul', 'Realizando tratamento dermatológico', 2);

-- Animal 3: Cachorro Labrador, Adotado (Vinculado ao Adotante ID 1)
INSERT INTO animal (nome, idade_meses, sexo, porte, status, castrado, dt_resgate, dt_saida, raca_id, adotante_id, responsavel_id)
VALUES ('Thor', 60, 'MACHO', 'GRANDE', 'ADOTADO', TRUE, '2023-06-20', '2023-12-01', 2, 1, 2);

-- Animal 4: Gato Persa, Quarentena (Recém chegado)
INSERT INTO animal (nome, idade_meses, sexo, porte, status, castrado, dt_resgate, raca_id, observacao, responsavel_id)
VALUES ('Garfield', 72, 'MACHO', 'MEDIO', 'QUARENTENA', FALSE, CURRENT_DATE, 4, 'Aguardando exames iniciais', 1);

-- Animal 5: Cachorro SRD, Óbito (Infelizmente faleceu)
INSERT INTO animal (nome, idade_meses, sexo, porte, status, castrado, dt_resgate, dt_saida, raca_id, observacao, responsavel_id)
VALUES ('Velhinho', 180, 'MACHO', 'PEQUENO', 'OBITO', TRUE, '2023-01-01', '2023-01-15', 1, 'Faleceu de causas naturais devido à idade avançada', 1);