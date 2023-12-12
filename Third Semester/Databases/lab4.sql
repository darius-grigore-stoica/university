USE education

GO
CREATE OR ALTER FUNCTION isValidStudent(
	@Student_ID INT,
	@Nume NVARCHAR(50)
) RETURNS bit AS 
BEGIN
	DECLARE @return_value bit;

	SET @return_value = 'true';

	IF ISNUMERIC(@Student_ID) = 0
		SET @return_value = 'false';

	RETURN @return_value;
END

CREATE OR ALTER PROCEDURE InserareStudent
	@Student_ID INT,
	@Nume NVARCHAR(50)
AS 
BEGIN
	IF dbo.isValidStudent(@Student_ID, @Nume) = 1
		INSERT INTO Student VALUES (@Student_ID, @Nume)
	ELSE PRINT 'INVALID STUDENT'
END


GO
CREATE  OR ALTER FUNCTION isValidInscriere(
	@Inscriere_ID INT,
	@Student_ID INT,
	@Curs_ID INT,
	@Data_Inscriere DATE,
	@Nota DECIMAL(4,2)
) RETURNS bit AS
BEGIN
	DECLARE @return_value bit

	SET @return_value = 'true'

	IF ISNUMERIC(@Inscriere_ID) = 0
		SET @return_value = 'false'
	IF ISNUMERIC(@Student_ID) = 0
		SET @return_value = 'false'
	IF ISNUMERIC(@Curs_ID) = 0
		SET @return_value = 'false'
	RETURN @return_value
END

GO
CREATE OR ALTER PROCEDURE InserareInscriere
	@Inscriere_ID INT,
	@Student_ID INT,
	@Curs_ID INT,
	@Data_Inscriere DATE,
	@Nota DECIMAL(4,2)
AS 
BEGIN
	IF dbo.isValidInscriere(@Inscriere_ID, @Student_ID, @Curs_ID, @Data_Inscriere, @Nota) = 1
		INSERT INTO Inscriere VALUES (@Inscriere_ID, @Student_ID, @Curs_ID, @Data_Inscriere, @Nota)
	ELSE PRINT 'INVALID ENROLLMENT'
END

GO
CREATE  OR ALTER FUNCTION isCurs(
	@Curs_ID INT,
	@Curs_Nume INT,
	@Profesor nvarchar(50),
	@Durata INT
) RETURNS bit AS
BEGIN
	DECLARE @return_value bit

	SET @return_value = 'true'

	IF ISNUMERIC(@Curs_ID) = 0
		SET @return_value = 'false'
	IF ISNUMERIC(@Durata) = 0
		SET @return_value = 'false'
	RETURN @return_value
END

GO
CREATE OR ALTER PROCEDURE InserareCurs
	@Curs_ID INT,
	@Curs_Nume INT,
	@Profesor nvarchar(50),
	@Durata INT
AS
BEGIN
	IF dbo.isCurs(@Curs_ID, @Curs_Nume, @Profesor, @Durata) = 1
		INSERT INTO Curs VALUES (@Curs_ID, @Curs_Nume, @Profesor, @Durata)
	ELSE PRINT 'INVALID ENROLLMENT'
END

GO
CREATE VIEW [InscriereStudent] AS
SELECT AVG(Inscriere.Nota) AS 'Nota'
FROM Inscriere
INNER JOIN Curs ON Curs.Curs_ID = Inscriere.Curs_ID
WHERE Inscriere.Data_Inscriere > '2023-10-15'
GROUP BY Curs.Profesor
HAVING AVG(Inscriere.Nota) > 5

GO
CREATE OR ALTER TRIGGER TRIGGER_INSERARE
ON Capitol
FOR INSERT
AS
BEGIN
	DECLARE 
	@Capitol_ID INT,
	@Curs_ID INT, 
	@Titlu NVARCHAR(50),
	@Nr_Quiz INT,
	@Nr_Lecti INT,
	@CurrentDateTime DATETIME = GETDATE()

	SELECT @Capitol_ID = Capitol_ID, @Curs_ID = Curs_ID, @Titlu = Titlu, @Nr_Lecti = Nr_Lecti, @Nr_Quiz = Nr_Quiz
	FROM INSERTED
	PRINT 'DATE : ' + @CurrentDateTime + 'INSERTED' + 'Capitol'
END

GO
CREATE OR ALTER TRIGGER TRIGGER_DELETE
ON Inscriere
FOR DELETE
AS
BEGIN
	DECLARE 
	@CurrentDateTime DATETIME = GETDATE()

	SELECT Inscriere_ID, Curs_ID, Data_Inscriere, Nota
	FROM DELETED
	PRINT 'DATE : ' + @CurrentDateTime + 'Deleted' + 'ENROLLMENT'
END
