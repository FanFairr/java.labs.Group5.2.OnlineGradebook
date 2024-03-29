declare res number := 0;
begin
  select count(*) into res from ALL_SEQUENCES where lower(sequence_name) = 'person_seq';
  if res = 0 then
    execute immediate('create sequence person_seq');
  end if;
  select count(*) into res from ALL_SEQUENCES where lower(sequence_name) = 'subject_seq';
  if res = 0 then
    execute immediate('create sequence subject_seq');
  end if;
  select count(*) into res from ALL_SEQUENCES where lower(sequence_name) = 'task_seq';
  if res = 0 then
    execute immediate('create sequence task_seq');
  end if;
  select count(*) into res from ALL_SEQUENCES where lower(sequence_name) = 'mark_seq';
  if res = 0 then
    execute immediate('create sequence mark_seq');
  end if;
  select count(*) into res from all_tables where lower(table_name) = 'person';
  if res = 0 then
    execute immediate('
      Create table Person (
        id_person Number NOT NULL ,
        Name Varchar2 (20) NOT NULL ,
        login Varchar2 (20) NOT NULL ,
        password Varchar2 (255) NOT NULL ,
        email Varchar2 (30) ,
        status Varchar2 (10) ,
        primary key (id_person)
      )');
  end if;
  select count(*) into res from all_tables where lower(table_name) = 'student';
  if res = 0 then
    execute immediate('
      Create table Student (
        id_person Number NOT NULL ,
        course Varchar2 (20),
        primary key (id_person)
      )');
      execute immediate('Alter table Student add  foreign key (id_person) references Person (id_person)');
  end if;
  select count(*) into res from all_tables where lower(table_name) = 'teacher';
  if res = 0 then
    execute immediate('
      Create table Teacher (
        id_person Number NOT NULL ,
        primary key (id_person)
      )');
      execute immediate('Alter table Teacher add  foreign key (id_person) references Person (id_person)');
  end if;
  select count(*) into res from all_tables where lower(table_name) = 'subject';
  if res = 0 then
    execute immediate('
      Create table Subject (
        id_subject Number NOT NULL ,
        subject_name Varchar2 (30),
        content Varchar2 (500),
        primary key (id_subject)
      )');
  end if;
  select count(*) into res from all_tables where lower(table_name) = 'task';
  if res = 0 then
    execute immediate('
      Create table Task (
        task_id Varchar2 (20) NOT NULL ,
        id_subject Number NOT NULL ,
        name Varchar2 (20),
        content Varchar2 (200),
        max_mark Number,
        primary key (task_id)
      )');
      execute immediate('Alter table Task add  foreign key (id_subject) references Subject (id_subject)');
  end if;
  select count(*) into res from all_tables where lower(table_name) = 'teacher_subject';
  if res = 0 then
    execute immediate('
      Create table teacher_subject (
        id_person Number NOT NULL ,
        id_subject Number NOT NULL
      )');
      execute immediate('Alter table teacher_subject add  foreign key (id_person) references Teacher (id_person)');
  execute immediate('Alter table teacher_subject add  foreign key (id_subject) references Subject (id_subject)');

  end if;
  select count(*) into res from all_tables where lower(table_name) = 'student_subject';
  if res = 0 then
    execute immediate('
      Create table student_subject (
        id_person Number NOT NULL ,
        id_subject Number NOT NULL
      )');
      execute immediate('Alter table student_subject add  foreign key (id_person) references Student (id_person)');
  execute immediate('Alter table student_subject add  foreign key (id_subject) references Subject (id_subject)');
  end if;
  select count(*) into res from all_tables where lower(table_name) = 'mark';
  if res = 0 then
    execute immediate('
      Create table Mark (
        id_mark Number NOT NULL ,
        task_id Varchar2 (20) NOT NULL ,
        id_student Number NOT NULL ,
        id_teacher Number NOT NULL ,
        mark_score Number,
        primary key (id_mark)
      )');
       execute immediate('Alter table Mark add  foreign key (id_student) references Student (id_person)');
  execute immediate('Alter table Mark add  foreign key (id_teacher) references Teacher (id_person)');
  execute immediate('Alter table Mark add  foreign key (task_id) references Task (task_id)');
  end if;
  select count(*) into res from all_tables where lower(table_name) = 'person1';
  if res = 0 then
    execute immediate('
      create table person1 as select * from person');
	execute immediate('create or replace trigger insertPerson
	after insert on PERSON
	for each row
	declare status_peson VARCHAR2(10);
	begin
	 insert into person1 values (:new.ID_PERSON, :new.NAME, :new.LOGIN, :new.PASSWORD, :new.EMAIL, :new.STATUS);
	  select status into status_peson from PERSON1 where id_person = :new.id_person;
	  if status_peson = ''student'' then
		insert into student values (:new.ID_PERSON, '''');
	  elsif status_peson = ''teacher'' then
		insert into TEACHER values (:new.ID_PERSON);
	  end if;
	end;');
  end if;
  execute immediate('insert into PERSON values (PERSON_SEQ.nextval, ''adm'', ''adm'', ''$2a$10$b415rJHRJas7b6Ysk2yG1Ozq.AExaK9BY7bdC1HDHCqLubtNzD1yi'', ''admin@gmail.com'', ''admin'')');
	execute immediate('insert into person values (PERSON_SEQ.nextval, ''Andrey'', ''andrey'', ''$2a$10$DeAafjSI3Tj0LdelMr9v5OHrc3phcfq6p302x250D4FpaK6v22uhm'', ''druce@gmail.com'', ''student'')');
	execute immediate('insert into person values (PERSON_SEQ.nextval, ''asd'', ''asd'', ''$2a$10$yMMVsxecMCdJELRXeKvfKepjMqjkAPXcd91GHVxCa4OyeLwq5dLku'', ''asd@gmail.com'', ''student'')');
	execute immediate('insert into person values (PERSON_SEQ.nextval, ''Boris Kuzikov'', ''Boris'', ''$2a$10$SPKpl.IhbP01AdKmGnJvWO91UvYz3IeFWV.5O1i8/AQqWipOLlPBG'', ''borisdb@gmail.com'', ''teacher'')');
	execute immediate('insert into person values (PERSON_SEQ.nextval, ''Oleg'', ''Oleg Berest'', ''$2a$10$//tRgWXXgPxbD5Do8KBkpOJrLkYzxfqy43P3cOKpFz/9U74RhS8Bm'', ''oleg_programist@gmail.com'', ''teacher'')');
	execute immediate('insert into person values (PERSON_SEQ.nextval, ''Jeka'', ''Jeka'', ''$2a$10$qCvTl2jNBitsgalpDvRyNuASfD0675NaJfBp2ZRAEV8d354rIcB0C'', ''jeka@gmail.com'', ''student'')');
	execute immediate('insert into SUBJECT values (SUBJECT_SEQ.nextval, ''Database'', ''In this course you can learn about such a subject as "Databases".
		For the course will be studied: the creation of tables, indexes, triggers, the use of aggregate functions and much more.'')');
	execute immediate('insert into SUBJECT values (SUBJECT_SEQ.nextval, ''Programming'', ''This course is designed for those who want to get to our company ...'')');
	execute immediate('insert into SUBJECT values (SUBJECT_SEQ.nextval, ''QA'', ''We will test something.'')');
	execute immediate('insert into SUBJECT values (SUBJECT_SEQ.nextval, ''Analyst'', ''I do not know why you need this subject.'')');
	execute immediate('insert into task values (TASK_SEQ.nextval, 1, ''Laba 1'', ''Print everything from the emp table.'', 4)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 1, ''Laba 2'', ''Accounting needs a report containing the employee number, his current salary and salary increased by 20%. Name this column New Salary.'', 6)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 1, ''Laba 3'', ''Form a request that displays the name of the employee and his commission.
		If there is no commission in the column, “No commissions” should be displayed.'', 5)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 1, ''Laba 4'', ''Form a query that displays the name of the employee and the city in which he works.'', 5)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 2, ''Practice 1'', ''Create a class "Task" and complete it.'', 5)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 2, ''Practice 2'', ''Create a class "ArrayTaskList" and complete it.'', 5)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 2, ''Practice 3'', ''Create a class "LinkedTaskList" and complete it.'', 5)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 2, ''Practice 4'', ''Create iterator for all class.'', 5)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 3, ''Practice 1'', ''Test something.'', 20)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 3, ''Practice 2'', ''Test something.'', 30)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 3, ''Practice 3'', ''Test something.'', 25)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 4, ''Practice 1'', ''Analyze something.'', 100)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 4, ''Practice 2'', ''Analyze something.'', 100)');
	execute immediate('insert into task values (TASK_SEQ.nextval, 4, ''Practice 3'', ''Analyze something.'', 100)');
	execute immediate('insert into TEACHER_SUBJECT values (4, 1)');
	execute immediate('insert into TEACHER_SUBJECT values (4, 3)');
	execute immediate('insert into TEACHER_SUBJECT values (5, 2)');
	execute immediate('insert into TEACHER_SUBJECT values (5, 4)');
end;