USE education

CREATE TABLE Curs(
	Curs_ID int PRIMARY KEY,
	Curs_Nume nvarchar(50) NOT NULL,
	Profesor nvarchar(50),
	Durata int
)

CREATE TABLE Student(
	Student_ID INT PRIMARY KEY,
	Nume NVARCHAR(50) NOT NULL,
)

CREATE TABLE Inscriere(
	Inscriere_ID INT NOT NULL,
	Student_ID INT NOT NULL,
	Curs_ID INT NOT NULL
)

ALTER TABLE Inscriere 
ADD CONSTRAINT FK_Inscriere PRIMARY KEY (Student_ID, Curs_ID),
FOREIGN KEY (Student_ID) REFERENCES Student,
FOREIGN KEY (Curs_ID) REFERENCES Curs;

ALTER TABLE Inscriere
ADD Data_Inscriere DATE;

ALTER TABLE Inscriere
ADD Nota INT;

INSERT INTO Curs VALUES
(1, 'Matematica I', 'Prof. A', 10),
(2, 'Fizica I', 'Prof. B', 12),
(3, 'Informatica I', 'Prof. C', 8);

INSERT INTO Curs Values
(4, 'Biologie', 'Prof. C', 5);

INSERT INTO Student VALUES
(101, 'Ana'),
(102, 'Ion'),
(103, 'Maria'),
(104, 'Andrei');


INSERT INTO Inscriere VALUES
(1, 101, 1, '2023-01-01', 9.5),
(2, 102, 2, '2023-02-01', 8.0),
(3, 103, 1, '2023-01-15', 7.5),
(4, 101, 3, '2023-03-01', 8.5),
(5, 102, 3, '2023-03-01', 5.5);


CREATE TABLE Eveniment(
	Eveniment_ID INT NOT NULL,
	Curs_ID INT,
	Student_ID INT,
	Completat INT
)

ALTER TABLE Eveniment
ALTER COLUMN Curs_ID INT NOT NULL;


ALTER TABLE Eveniment
ALTER COLUMN Student_ID INT NOT NULL;

ALTER TABLE Eveniment
ADD CONSTRAINT FK_Eveniment PRIMARY KEY (Curs_ID, Student_ID),
FOREIGN KEY (Student_ID) REFERENCES Student,
FOREIGN KEY (Curs_ID) REFERENCES Curs;

INSERT INTO Eveniment VALUES
(1, 1, 101, 0, 'LeetCode 7'), (2, 1, 102, 1, 'FAANG 10'), (3, 3, 101, 0, 'CCC 2023');

--Selectam studenti cu nota peste 9 si studenti cu evenimentul necompletat
SELECT Student_ID, Curs_ID FROM Inscriere
WHERE Nota > 9
UNION
SELECT Student_ID, Curs_ID FROM Eveniment
WHERE Completat = 0;

INSERT INTO Inscriere (Inscriere_ID, Student_ID, Curs_ID, Data_Inscriere) VALUES (4, 102, 3, '2023-03-01');

DROP TABLE Capitol

CREATE TABLE Capitol(
	Capitol_ID INT NOT NULL,
	Curs_ID INT NOT NULL,
	Titlu NVARCHAR(50),
	Nr_Quiz INT,
	Nr_Lecti INT
);
ALTER TABLE Capitol
ADD CONSTRAINT FK_Curs FOREIGN KEY (Curs_ID) 
REFERENCES Curs;

INSERT INTO Capitol VALUES (4, 2, 'Integrala Riemann', 2, 6);
INSERT INTO Capitol VALUES 
(1, 1, 'Termodinamica I', 4, 3), (2, 3, 'Algoritm de Primaritate', 1, 1);


--Selectam cursurile si capitolele din baza de date
SELECT Curs.Curs_Nume AS 'Curs', Capitol.Titlu AS 'Capitol'
FROM Capitol
INNER JOIN Curs ON Capitol.Curs_ID = Curs.Curs_ID
ORDER BY Capitol_ID;

--Selectam evenimentele si capitolele din aceleasi cursuri
SELECT Eveniment.Eveniment_Nume AS 'Eveniment', Capitol.Titlu FROM Eveniment
INNER JOIN Capitol ON Eveniment.Curs_ID = Capitol.Curs_ID


--Selectam toti studenti si vedem notele obtinute de cei inscrisi intr-un curs
SELECT Student.Student_ID, Inscriere.Curs_ID, Inscriere.Nota From Student
LEFT JOIN Inscriere ON Inscriere.Student_ID = Student.Student_ID


--Selectam cate cursuri preda fiecare profesor
SELECT COUNT(Curs.Curs_ID) AS 'Cursuri Predate', Curs.Profesor
FROM Curs
GROUP BY Curs.Profesor;

SELECT * FROM Inscriere

--SELECTAM TOATE CURSURILE SI VEDEM MEDIA NOTELOR STUDENTILOR INSCRISI
SELECT Curs.Curs_Nume, AVG(Inscriere.Nota) AS 'Media Notelor' FROM Curs
LEFT JOIN Inscriere ON Curs.Curs_ID = Inscriere.Curs_ID
GROUP BY Curs.Curs_Nume;

--SELECTAM ACELE CURSURI CARE AU DURATA MINIM 5
SELECT Curs.Curs_Nume AS 'Nume Curs' FROM Curs
GROUP BY Curs.Curs_Nume
HAVING MIN(DISTINCT CURS.Durata) > 5

SELECT * FROM Capitol
SELECT * FROM Curs

--SELECTAM ACELE CAPITOLE CARE APARTIN DE CURSURI CU O DURATA MAI MARE DE 10
SELECT Capitol.Titlu as 'Nume Capitol', Capitol_ID FROM Capitol
WHERE EXISTS (SELECT Curs.Curs_ID FROM Curs WHERE Curs.Curs_ID = Capitol_ID AND Curs.Durata > 10)

--SELECTAM ACELE CURSURI CARE SUNT PREDATE DE PROFESORUL C
SELECT CURS.Curs_Nume FROM Curs
WHERE CURS.Curs_ID IN (SELECT Curs_ID FROM CURS WHERE CURS.Profesor = 'Prof. C')