CREATE SEQUENCE SQ_EXERCISE_ID  minvalue 1 increment by 1 start with 1 maxvalue 9999999999;
CREATE SEQUENCE SQ_CATEGORY_ID  minvalue 1 increment by 1 start with 1 maxvalue 9999999999;


INSERT INTO category (name) VALUES ('Peito');
INSERT INTO category (name) VALUES ('Costas');
INSERT INTO category (name) VALUES ('Ombro');
INSERT INTO category (name) VALUES ('Bíceps');
INSERT INTO category (name) VALUES ('Tríceps');
INSERT INTO category (name) VALUES ('Antebraço');
INSERT INTO category (name) VALUES ('Abdômen');
INSERT INTO category (name) VALUES ('Perna');

INSERT INTO exercise (name, category_id) VALUES ('Supino Reto', 1);
INSERT INTO exercise (name, category_id) VALUES ('Supino Inclinado', 1);
INSERT INTO exercise (name, category_id) VALUES ('Supino Declinado', 1);
INSERT INTO exercise (name, category_id) VALUES ('Crucifixo Reto', 1);
INSERT INTO exercise (name, category_id) VALUES ('Crucifixo Inclinado', 1);
INSERT INTO exercise (name, category_id) VALUES ('Crucifixo Declinado', 1);
INSERT INTO exercise (name, category_id) VALUES ('Barra Fixa', 2);
INSERT INTO exercise (name, category_id) VALUES ('Remada Curvada', 2);
INSERT INTO exercise (name, category_id) VALUES ('Puxada na Polia', 2);
INSERT INTO exercise (name, category_id) VALUES ('Desenvolvimento', 3);
INSERT INTO exercise (name, category_id) VALUES ('Elevação Lateral', 3);
INSERT INTO exercise (name, category_id) VALUES ('Remada Alta', 3);
INSERT INTO exercise (name, category_id) VALUES ('Rosca Direta', 4);
INSERT INTO exercise (name, category_id) VALUES ('Rosca Alternada', 4);
INSERT INTO exercise (name, category_id) VALUES ('Rosca Martelo', 4);
INSERT INTO exercise (name, category_id) VALUES ('Tríceps Testa', 5);
INSERT INTO exercise (name, category_id) VALUES ('Tríceps Corda', 5);
INSERT INTO exercise (name, category_id) VALUES ('Tríceps Francês', 5);
INSERT INTO exercise (name, category_id) VALUES ('Flexão de Punho', 6);
INSERT INTO exercise (name, category_id) VALUES ('Extensão de Punho', 6);
INSERT INTO exercise (name, category_id) VALUES ('Abdominal Supra', 7);
INSERT INTO exercise (name, category_id) VALUES ('Abdominal Infra', 7);
INSERT INTO exercise (name, category_id) VALUES ('Prancha', 7);
INSERT INTO exercise (name, category_id) VALUES ('Agachamento Livre', 8);
INSERT INTO exercise (name, category_id) VALUES ('Agachamento com Barra', 8);
INSERT INTO exercise (name, category_id) VALUES ('Leg Press', 8);
INSERT INTO exercise (name, category_id) VALUES ('Cadeira Extensora', 8);
INSERT INTO exercise (name, category_id) VALUES ('Cadeira Flexora', 8);
INSERT INTO exercise (name, category_id) VALUES ('Panturrilha em Pé', 8);
INSERT INTO exercise (name, category_id) VALUES ('Panturrilha Sentado', 8);

