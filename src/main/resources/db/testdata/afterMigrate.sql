TRUNCATE animal RESTART IDENTITY CASCADE;

INSERT INTO animal
(nome, idade, sexo, porte, castrado, dt_resgate, dt_saida, cor_olhos, cor_pelagem, observacao)
VALUES
-- 1. Thor
('Thor', 3, 'MACHO', 'GRANDE', TRUE, '2024-01-10', NULL, 'Castanhos', 'Preta', 'Muito dócil'),

-- 2. Mimi
('Mimi', 2, 'FEMEA', 'PEQUENO', FALSE, '2024-02-01', NULL, 'Azuis', 'Branca', 'Medrosa'),

-- 3. Rex (Já saiu/adotado)
('Rex', 5, 'MACHO', 'MEDIO', TRUE, '2023-12-20', '2024-03-01', 'Castanhos', 'Caramelo', 'Adotado rapidamente'),

-- 4. Luna
('Luna', 1, 'FEMEA', 'PEQUENO', FALSE, '2024-03-15', NULL, 'Verdes', 'Cinza', 'Filhote'),

-- 5. Bob
('Bob', 4, 'MACHO', 'MEDIO', FALSE, '2023-11-10', NULL, 'Castanhos', 'Amarelo', 'Aguardando avaliação');