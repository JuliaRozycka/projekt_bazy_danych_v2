create database punkt_szczepien;

use punkt_szczepien;

drop table pacjenci;
create table pacjenci(
pesel varchar(11) primary key,
id_login int,
imie varchar(30),
nazwisko varchar(30),
numer_telefonu varchar(12),
foreign key (id_login) references uzytkownicy(id)
);

drop table uzytkownicy;
create table uzytkownicy(
id INT PRIMARY KEY auto_increment,
login varchar(30),
haslo varchar(30)
);

select * from uzytkownicy;

create table pracownicy(
id INT PRIMARY KEY auto_increment,
login varchar(30),
haslo varchar(30)
);

drop table szczepienia;
create table szczepienia(
id int primary key,
nazwa_szczepienia varchar(45),
producent varchar(45),
status enum("oczekujące", "zrealizowane","niezrealizowane")
);

drop table wizyty;
create table wizyty(
data date,
godzina time,
pesel_pacjenci varchar(11),
id_szczepienia int,
foreign key (pesel_pacjenci) references pacjenci(pesel),
foreign key (id_szczepienia) references szczepienia(id),
constraint id_wizyty primary key(data, godzina)
);


INSERT INTO pacjenci(pesel,imie,nazwisko,numer_telefonu) VALUES
('12345678911','Kasia','Czak','+48193047289');

INSERT INTO szczepienia(id, nazwa_szczepienia, producent, status) values
('1','covid','pfizer','oczekujące'),
('2','odra','pfizer','zrealizowane'),
('3','covid','pfizer','zrealizowane'),
('4','ospa','pfizer','zrealizowane');

insert into wizyty(data, godzina, pesel_pacjenci, id_szczepienia) values
('2020-10-10', '12:00:00','12345678911','1'),
('2020-12-12', '12:00:00','12345678911','2'),
('2021-11-11', '12:00:00','12345678911','3'),
('2022-01-12', '12:00:00','12345678911','4');



select * from pacjenci;
select * from wizyty;
select * from szczepienia;

drop view archiwum;
create view archiwum as 
select p.pesel, p.imie, p.nazwisko, s.nazwa_szczepienia, s.producent, 
w.data, w.godzina from wizyty w 
join pacjenci p on p.pesel = w.pesel_pacjenci
join szczepienia s on s.id = w.id_szczepienia
where s.status = "zrealizowane";

select * from archiwum;

drop view widok_uzytkownika;
create view widok_uzytkownika as
select p.pesel, s.nazwa_szczepienia, s.producent, 
w.data, w.godzina, s.status from wizyty w
join pacjenci p on p.pesel = w.pesel_pacjenci
join szczepienia s on s.id = w.id_szczepienia;

select * from widok_uzytkownika;

create view oczekujace_szczepienia as 
select p.pesel, p.imie, p.nazwisko, s.nazwa_szczepienia, s.producent, 
w.data, w.godzina from wizyty w 
join pacjenci p on p.pesel = w.pesel_pacjenci
join szczepienia s on s.id = w.id_szczepienia
where s.status = "oczekujące";

create view niezrealizowane_szczepienia as 
select p.pesel, p.imie, p.nazwisko, s.nazwa_szczepienia, s.producent, 
w.data, w.godzina from wizyty w 
join pacjenci p on p.pesel = w.pesel_pacjenci
join szczepienia s on s.id = w.id_szczepienia
where s.status = "niezrealizowane";

select * from oczekujace_szczepienia;

UPDATE szczepienia SET status = 'zrealizowane' WHERE id = '1';


CREATE USER 'punkt_szczepien'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON punkt_szczepien.* TO 'punkt_szczepien'@'localhost';
SHOW GRANTS FOR 'punkt_szczepien'@'localhost';

CREATE USER 'uzytkownik'@'localhost' IDENTIFIED BY 'password';
GRANT SELECT ON punkt_szczepien.archiwum TO 'uzytkownik'@'localhost';
GRANT SELECT ON punkt_szczepien.oczekujace_szczepienia TO 'uzytkownik'@'localhost';
SHOW GRANTS FOR 'uzytkownik'@'localhost';
SHOW GRANTS FOR 'KatCza'@'localhost';
drop procedure czyPelnoletnia;

DELIMITER //
CREATE PROCEDURE czyPelnoletnia(IN pesel varchar(11), OUT wynik boolean)
BEGIN

	declare liczba INT;
    declare rok INT;
    declare miesiac INT;
    declare dzien INT;
    SET liczba = cast(substring(pesel, 3,1) AS unsigned);
    IF liczba > 1 THEN
		 SET rok = 2000 + cast(substring(pesel, 1,2) AS unsigned);
         SET miesiac = cast(substring(pesel, 3,2) AS unsigned)-20;
	ELSE
		SET rok = 1900 + cast(substring(pesel, 1,2) AS unsigned);
		SET miesiac = cast(substring(pesel, 3,2) AS unsigned);
	END IF;
    SET dzien = cast(substring(pesel, 5,2) AS unsigned);
    IF timestampdiff(YEAR, concat(rok,'-',miesiac,'-',dzien), curdate())>18
    THEN SET wynik = true;
    else set wynik = false;
    end if;
   
END; //
DELIMITER ;


drop procedure czyMozeZaszczepic;
DELIMITER //
CREATE PROCEDURE czyMozeZaszczepic(IN pesell varchar(11), IN newData date,IN newTyp varchar(45), OUT wynik boolean)
BEGIN
	declare oldData date;
    declare oldTyp varchar(45);
    
    SET wynik = true;
    
    SET oldData = (SELECT MAX(data) from archiwum where pesel like pesell and nazwa_szczepienia = newTyp GROUP BY pesel);
    SET oldTyp = (SELECT MAX(nazwa_szczepienia) from archiwum where pesel like pesell and nazwa_szczepienia = newTyp GROUP BY pesel);
    
    IF datediff(newData, oldData) < 365 THEN
		SET wynik = false;
	END IF;
    
    SET oldData = (SELECT MAX(data) from archiwum where pesel like pesell GROUP BY pesel);
    SET oldTyp = (SELECT MAX(nazwa_szczepienia) from archiwum where pesel like pesell GROUP BY pesel);
    
    IF datediff(newData, oldData) < 21 THEN
		SET wynik = false;
	END IF;
    
END; //
DELIMITER ;



DROP PROCEDURE IF EXISTS add_User;
DELIMITER $$
CREATE PROCEDURE add_User(IN p_Name VARCHAR(45), IN p_Passw VARCHAR(200))
BEGIN
    DECLARE `_HOST` CHAR(14) DEFAULT '@\'localhost\'';
    SET `p_Name` := CONCAT('\'', REPLACE(TRIM(`p_Name`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\''),
    `p_Passw` := CONCAT('\'', REPLACE(`p_Passw`, CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\'');
    SET @`sql` := CONCAT('CREATE USER ', `p_Name`, `_HOST`, ' IDENTIFIED BY ', `p_Passw`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT ALL PRIVILEGES ON *.* TO ', `p_Name`, `_HOST`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    DEALLOCATE PREPARE `stmt`;
    FLUSH PRIVILEGES;
END$$

DELIMITER ;
