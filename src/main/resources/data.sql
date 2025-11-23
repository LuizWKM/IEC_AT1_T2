-- Arquivo SQL para popular o banco de dados H2 com dados iniciais
-- Este arquivo será carregado automaticamente pelo Spring Boot se colocado em src/main/resources/data.sql

INSERT INTO fighters (name, nationality, age, martial_art_style, belt_rank, wins, losses, weight_class) VALUES
('Bruce Lee', 'Hong Kong', 32, 'Jeet Kune Do', 'Mestre', 100, 0, 'Peso Leve'),
('Anderson Silva', 'Brasil', 48, 'Muay Thai', 'Faixa Preta', 34, 11, 'Peso Médio'),
('Georges St-Pierre', 'Canadá', 42, 'Karatê', 'Faixa Preta', 26, 2, 'Peso Meio-Médio'),
('Ronda Rousey', 'EUA', 36, 'Judô', 'Faixa Preta', 12, 2, 'Peso Galo'),
('Conor McGregor', 'Irlanda', 35, 'Boxe', 'Faixa Marrom', 22, 6, 'Peso Leve'),
('Lyoto Machida', 'Brasil', 45, 'Karatê Shotokan', 'Faixa Preta', 26, 10, 'Peso Meio-Pesado'),
('Chuck Norris', 'EUA', 83, 'Tang Soo Do', 'Grão-Mestre', 183, 10, 'Peso Médio'),
('Khabib Nurmagomedov', 'Rússia', 35, 'Sambo', 'Mestre', 29, 0, 'Peso Leve'),
('Israel Adesanya', 'Nigéria', 34, 'Kickboxing', 'Faixa Preta', 24, 2, 'Peso Médio'),
('Jon Jones', 'EUA', 36, 'Luta Greco-Romana', 'Faixa Azul', 27, 1, 'Peso Pesado');
