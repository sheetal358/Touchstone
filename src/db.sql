
CREATE TABLE IF NOT EXISTS GroupMaster (
  groupId int(11) NOT NULL,
  name varchar(30) NOT NULL,
  description varchar(50) NOT NULL,
  members varchar(10) DEFAULT NULL,
  ownerId int(11) NOT NULL,
  groupCode varchar(20) DEFAULT NULL,
  status int(11) NOT NULL,
  PRIMARY KEY (groupId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/

CREATE TABLE IF NOT EXISTS Message (
  id int(11) NOT NULL,
  Message varchar(500) NOT NULL,
  userName varchar(20) NOT NULL,
  createdOn varchar(10) NOT NULL,
  groupName varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/

CREATE TABLE IF NOT EXISTS Notification (
  id int(11) NOT NULL,
  Notification varchar(500) NOT NULL,
  userName varchar(20) NOT NULL,
  createdOn varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/

CREATE TABLE IF NOT EXISTS QuestionMaster (
  id int(5) NOT NULL,
  description varchar(100) NOT NULL,
  type int(1) NOT NULL,
  difficultyLevel int(1) NOT NULL,
  ans int(1) NOT NULL,
  options varchar(200) DEFAULT NULL,
  quizId int(5) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/

CREATE TABLE IF NOT EXISTS QuizGroup (
  GroupId int(11) NOT NULL,
  QuizId int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/

CREATE TABLE IF NOT EXISTS QuizMaster (
  id int(5) NOT NULL,
  name varchar(100) NOT NULL,
  status int(1) NOT NULL,
  duration int(5) NOT NULL,
  createdOn varchar(10) DEFAULT NULL,
  ownerId int(5) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/

CREATE TABLE IF NOT EXISTS QuizResult (
  qrId int(11) NOT NULL,
  resultId int(11) NOT NULL,
  questionId int(11) NOT NULL,
  answer int(11) NOT NULL,
  timeTaken float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/

CREATE TABLE IF NOT EXISTS ResultMaster (
  resultId int(11) NOT NULL,
  userId int(11) NOT NULL,
  quizId int(11) NOT NULL,
  result float NOT NULL,
  date varchar(30) NOT NULL,
  timeTaken float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/

CREATE TABLE IF NOT EXISTS UserMaster (
  id int(11) NOT NULL,
  Name varchar(40) NOT NULL,
  MailId varchar(40) NOT NULL,
  Password varchar(40) NOT NULL,
  Question varchar(300) NOT NULL,
  Ans varchar(400) NOT NULL,
  Role int(11) NOT NULL,
  GroupCode varchar(25) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/

INSERT INTO UserMaster (id, Name, MailId, Password, Question, Ans, Role, GroupCode) VALUES
(1, 'abhaya kumar', 'abhaya@gmail.com', 'abhaya123', 'What is your favorite sport?', 'cricket', 1, NULL);


