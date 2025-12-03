TRUNCATE
    animal
RESTART IDENTITY CASCADE;

INSERT INTO animal
(nome, idade, sexo, porte, castrado, dt_resgate, dt_saida, cor_olhos, cor_pelagem, observacao)
VALUES
('Rex', 3, 'MACHO', 'GRANDE', TRUE, '2024-01-10', NULL, 'castanho', 'marrom', 'Cão dócil encontrado na rua.'),
('Luna', 2, 'FÊMEA', 'MÉDIO', FALSE, '2024-02-05', '2024-03-01', 'azul', 'preta', 'Gata resgatada com filhotes.'),
('Thor', 1, 'MACHO', 'PEQUENO', TRUE, '2024-03-15', NULL, 'verde', 'branca', 'Filhote muito ativo.'),
('Mia', 4, 'FÊMEA', 'PEQUENO', TRUE, '2024-01-22', '2024-02-18', 'castanho', 'cinza', 'Muito amigável com crianças.'),
('Bob', 6, 'MACHO', 'MÉDIO', FALSE, '2023-12-30', NULL, 'preto', 'caramelo', 'Chegou com machucados leves.');