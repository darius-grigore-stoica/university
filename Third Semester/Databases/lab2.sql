USE Education

SELECT * FROM [User];

INSERT INTO [User] VALUES
(1, 'A', 'Password123'), (2, 'B', 'SecurePass!'), (3, 'C', 'T456'), (4, 'D', '7aBc$Xyz'), (5, 'E', 'P@ssw0rd');

SELECT * FROM Course;

INSERT INTO Course VALUES
(1, 10, 6), (2, 4, 2), (3, 12, 8), (4, 5, 3), (5, 3, 3);

UPDATE Course
SET no_credits = no_credits / 2
WHERE no_credits > 5 AND no_credits < 10;


ALTER TABLE Enrollment
ALTER COLUMN user_id int NOT NULL;

ALTER TABLE Enrollment
ALTER COLUMN course_id int NOT NULL;

ALTER TABLE Enrollment
ADD CONSTRAINT PK_Enrollment PRIMARY KEY (user_id, course_id),
	FOREIGN KEY (user_id) REFERENCES [User](id),
	FOREIGN KEY (course_id) REFERENCES Course(id);

SELECT * FROM Enrollment

INSERT INTO Enrollment VALUES
(1, 1, 3), (2, 1, 4), (3, 2, 1), (4, 4, 5), (5, 2, 2)

DELETE FROM Enrollment
WHERE user_id = 1;

SELECT * FROM Chapter;

INSERT INTO Chapter VALUES
(1, 1, 2), (2, 3, 4), (3, 2, 2), (4, 1, 5), (5, 3, 2);

SELECT * FROM Quiz

UPDATE Quiz
SET no_questions = no_questions + 1
WHERE no_questions is NOT NULL;

INSERT INTO Quiz VALUES
(1, 3, 7),(2, 1, 2), (3, 5, 5), (4, 4, 9), (5, 2, 1)

DROP TABLE Question

IF OBJECT_ID(N'Question', N'U') IS NULL
CREATE TABLE Question(
	question_id int NOT NULL,
	quiz_id int NOT NULL,
	content nvarchar(255),
)

ALTER TABLE Question
ADD CONSTRAINT PK_Quiz_Question PRIMARY KEY (question_id),
FOREIGN KEY (quiz_id) REFERENCES Quiz(id);

SELECT * FROM Question

INSERT INTO Question VALUES
(1, 2, NULL), (2, 2, 'HELLO EVERYONE TODAY WE ARE...')

UPDATE Question
SET content = '404 PAGE NOT FOUND'
WHERE content is NULL;
