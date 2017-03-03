-- 创建数据库
create database TeachingAssistantSystem;
use TeachingAssistantSystem;

-- 创建用户表
create table User(
	userID varchar(20) not null,
	userName varchar(30) not null,
    userType varchar(20) not null,
    password varchar(50) not null,
    phoneNumber varchar(20),
    email varchar(30),
    primary key (userID, userType)
);
select * from user;

-- 创建文件信息表

create table FileInfo(
	id varchar(100) primary key,
	name varchar(200) not null,
    uploadTime date,
	savePath varchar(100) not null,
    description varchar(200),
	uploader varchar(30)
);

-- 创建通知表
create table Notice(
	id varchar(100) primary key,
    author varchar(100),
    releaseDate date not null,
    title varchar(200) not null,
    content text
);

-- 创建成绩表
create table Grade(
	studentID varchar(20) not null,
    gradeType varchar(20) not null,
    score varchar(20) default '未录入' not null,
    primary key (studentID, gradeType)
);
select * from Grade;

-- 教师视图的成绩表
create view TeacherView(studentName, studentID, score) as
select distinct username,userID,score from 
User join (select studentID,SUM(score) as score from Grade group by studentID) tGrade 
on userID=studentID;



