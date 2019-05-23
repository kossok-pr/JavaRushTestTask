USE test;

DROP TABLE IF EXISTS part;
CREATE TABLE part (
	id INT(11) NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	oblig BOOLEAN NOT NULL,
	count INT(11) NOT NULL,
PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO part(name, oblig, count)
VALUES
('Процессоры', TRUE, 268),
('Материнские платы', TRUE, 845),
('Видеокарты', TRUE, 111),
('Оперативная память', TRUE, 109),
('Блоки питания', TRUE, 751),
('Корпуса', TRUE, 690),
('Термопаста', FALSE, 596),
('Кулеры для процессоров', TRUE, 458),
('Вентиляторы для корпуса', FALSE, 839),
('Охлаждение для видеокарты', FALSE, 395),
('Охлаждение для жетского диска', FALSE, 730),
('Крепление для кулера', FALSE, 755),
('Жесткие диски', TRUE, 288),
('SSD-накопители', TRUE, 667),
('Внешние боксы для накопителей', FALSE, 728),
('Док-станции для накопителей', FALSE, 626),
('Салазки для накопителей', FALSE, 9),
('Оптические приводы', FALSE, 115),
('Звуковые карты', FALSE, 930),
('Тюнеры', FALSE, 656),
('Платы видеозахвата', FALSE, 442),
('Контроллеры', FALSE, 509),
('Мониторы', TRUE, 591),
('Клавиатуры', TRUE, 998),
('Мыши', TRUE, 797),
('Коврики для мыши', FALSE, 201),
('Графические планшеты', FALSE, 294),
('Внешние накопители', FALSE, 744),
('USB-разветвлители', FALSE, 554),
('Маршрутизаторы', FALSE, 319),
('МФУ', FALSE, 124),
('Принтеры', FALSE, 329),
('Сканеры', FALSE, 33),
('Веб-камеры', FALSE, 148);