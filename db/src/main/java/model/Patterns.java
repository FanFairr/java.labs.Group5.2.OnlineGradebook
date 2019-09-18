package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Patterns {
    public static final String LOGIN = "login";
    public static final String REDLOGIN = "redirect:/login";
    public static final String INDEX = "index";
    public static final String REGISTRATION = "registration";
    public static final String REDMAINPAGE = "redirect:/mainPage";
    public static final String REDREGISTRATION = "redirect:/registration";
    public static final String ADMINOPTION = "adminOption";
    public static final String MAINPAGESTUDENT = "mainPageStudent";
    public static final String MAINPAGETEACHER = "mainPageTeacher";
    public static final String MAINPAGEADMIN = "mainPageAdmin";
    public static final String SUBJECT = "subject";
    public static final String REDSUBJECT = "redirect:/subject?id=";
    public static final String TASK = "task";
    public static final String USER = "user";
    public static final String VALIDATE = "validate";
    public static final String PERSON = "person";
    public static final String SUBJECTS = "subjects";
    public static final String STUDENTSUBJECTS = "studentSubjects";
    public static final String TEACHERSUBJECTS = "teacherSubjects";
    public static final String MARKS = "marks";
    public static final String STUDENTINFO = "studentInfo";
    public static final String TASKS = "tasks";
    public static final String SUBJECTID = "subjectId";
    public static final String STUDENTID = "studentId";
    public static final String STUDENTS = "students";
    public static final String PERSONINFO = "personInfo";
    public static final String OPTION = "option";
    public static final String TEACHERS = "teachers";

    public static final String firstRequest = "declare res number := 0;\n" +
            "begin\n" +
            "\n" +
            "  select count(*) into res from ALL_SEQUENCES where lower(sequence_name) = 'person_seq';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('create sequence person_seq');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from ALL_SEQUENCES where lower(sequence_name) = 'subject_seq';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('create sequence subject_seq');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from ALL_SEQUENCES where lower(sequence_name) = 'task_seq';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('create sequence task_seq');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from ALL_SEQUENCES where lower(sequence_name) = 'mark_seq';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('create sequence mark_seq');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from all_tables where lower(table_name) = 'person';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('\n" +
            "      Create table Person (\n" +
            "        id_person Number NOT NULL ,\n" +
            "        Name Varchar2 (20) NOT NULL ,\n" +
            "        login Varchar2 (20) NOT NULL ,\n" +
            "        password Varchar2 (255) NOT NULL ,\n" +
            "        email Varchar2 (30) ,\n" +
            "        status Varchar2 (10) ,\n" +
            "        primary key (id_person)\n" +
            "      )\n" +
            "\t');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from all_tables where lower(table_name) = 'student';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('\n" +
            "      Create table Student (\n" +
            "        id_person Number NOT NULL ,\n" +
            "        course Varchar2 (20),\n" +
            "        primary key (id_person)\n" +
            "      )\n" +
            "\t');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from all_tables where lower(table_name) = 'teacher';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('\n" +
            "      Create table Teacher (\n" +
            "        id_person Number NOT NULL ,\n" +
            "        primary key (id_person)\n" +
            "      )\n" +
            "\t');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from all_tables where lower(table_name) = 'subject';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('\n" +
            "      Create table Subject (\n" +
            "        id_subject Number NOT NULL ,\n" +
            "        subject_name Varchar2 (30),\n" +
            "        content Varchar2 (500),\n" +
            "        primary key (id_subject)\n" +
            "      )\n" +
            "\t');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from all_tables where lower(table_name) = 'task';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('\n" +
            "      Create table Task (\n" +
            "        task_id Varchar2 (20) NOT NULL ,\n" +
            "        id_subject Number NOT NULL ,\n" +
            "        name Varchar2 (20),\n" +
            "        content Varchar2 (200),\n" +
            "        max_mark Number,\n" +
            "        primary key (task_id)\n" +
            "      )\n" +
            "\t');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from all_tables where lower(table_name) = 'teacher_subject';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('\n" +
            "      Create table teacher_subject (\n" +
            "        id_person Number NOT NULL ,\n" +
            "        id_subject Number NOT NULL\n" +
            "      )\n" +
            "\t');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from all_tables where lower(table_name) = 'student_subject';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('\n" +
            "      Create table student_subject (\n" +
            "        id_person Number NOT NULL ,\n" +
            "        id_subject Number NOT NULL\n" +
            "      )\n" +
            "\t');\n" +
            "  end if;\n" +
            "\n" +
            "  select count(*) into res from all_tables where lower(table_name) = 'mark';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('\n" +
            "      Create table Mark (\n" +
            "        id_mark Number NOT NULL ,\n" +
            "        task_id Varchar2 (20) NOT NULL ,\n" +
            "        id_student Number NOT NULL ,\n" +
            "        id_teacher Number NOT NULL ,\n" +
            "        mark_score Number,\n" +
            "        primary key (id_mark)\n" +
            "      )\n" +
            "\t');\n" +
            "  end if;\n" +
            "  select count(*) into res from all_tables where lower(table_name) = 'person1';\n" +
            "  if res = 0 then\n" +
            "    execute immediate('\n" +
            "      create table person1 as select * from person\n" +
            "\t');\n" +
            "  end if;\n" +
            "\n" +
            "end;";

    public static final String secondRequest = "begin\n" +
            "\n\n" +
            "execute immediate('Alter table Student add  foreign key (id_person) references Person (id_person)');\n" +
            "execute immediate('Alter table Teacher add  foreign key (id_person) references Person (id_person)');\n" +
            "execute immediate('Alter table Mark add  foreign key (id_student) references Student (id_person)');\n" +
            "execute immediate('Alter table Mark add  foreign key (id_teacher) references Teacher (id_person)');\n" +
            "execute immediate('Alter table student_subject add  foreign key (id_person) references Student (id_person)');\n" +
            "execute immediate('Alter table teacher_subject add  foreign key (id_person) references Teacher (id_person)');\n" +
            "execute immediate('Alter table teacher_subject add  foreign key (id_subject) references Subject (id_subject)');\n" +
            "execute immediate('Alter table student_subject add  foreign key (id_subject) references Subject (id_subject)');\n" +
            "execute immediate('Alter table Task add  foreign key (id_subject) references Subject (id_subject)');\n" +
            "execute immediate('Alter table Mark add  foreign key (task_id) references Task (task_id)');\n" +
            "execute immediate('create or replace trigger insertP\n" +
            "  after insert on PERSON\n" +
            "  for each row\n" +
            "  begin\n" +
            "    insert into person1 values (:new.ID_PERSON, :new.NAME, :new.LOGIN, :new.PASSWORD, :new.EMAIL, :new.STATUS);\n" +
            "  end;');\n" +
            "end;";

    public static final String thirdRequest = "create or replace trigger insertPerson\n" +
            "    after insert on PERSON\n" +
            "    for each row\n" +
            "    declare status_person VARCHAR2(10);\n" +
            "    begin\n" +
            "      select status into status_person from PERSON1 where LOGIN = :new.LOGIN;\n" +
            "      if status_person = 'student' then\n" +
            "        insert into student values (:new.ID_PERSON, '');\n" +
            "      elsif status_person = 'teacher' then\n" +
            "        insert into TEACHER values (:new.ID_PERSON);\n" +
            "      end if;\n" +
            "    end;";

    public static final ArrayList<String> insertList = new ArrayList<>(Arrays.asList(
            "insert into PERSON values (PERSON_SEQ.nextval, 'adm', 'adm', '$2a$10$b415rJHRJas7b6Ysk2yG1Ozq.AExaK9BY7bdC1HDHCqLubtNzD1yi', 'admin@gmail.com', 'admin')",
            "insert into person values (PERSON_SEQ.nextval, 'Andrey', 'andrey', '$2a$10$DeAafjSI3Tj0LdelMr9v5OHrc3phcfq6p302x250D4FpaK6v22uhm', 'druce@gmail.com', 'student')",
            "insert into person values (PERSON_SEQ.nextval, 'asd', 'asd', '$2a$10$yMMVsxecMCdJELRXeKvfKepjMqjkAPXcd91GHVxCa4OyeLwq5dLku', 'asd@gmail.com', 'student')",
            "insert into person values (PERSON_SEQ.nextval, 'Boris Kuzikov', 'Boris', '$2a$10$SPKpl.IhbP01AdKmGnJvWO91UvYz3IeFWV.5O1i8/AQqWipOLlPBG', 'borisdb@gmail.com', 'teacher')",
            "insert into person values (PERSON_SEQ.nextval, 'Oleg', 'Oleg Berest', '$2a$10$//tRgWXXgPxbD5Do8KBkpOJrLkYzxfqy43P3cOKpFz/9U74RhS8Bm', 'oleg_programist@gmail.com', 'teacher')",
            "insert into person values (PERSON_SEQ.nextval, 'Jeka', 'Jeka', '$2a$10$qCvTl2jNBitsgalpDvRyNuASfD0675NaJfBp2ZRAEV8d354rIcB0C', 'jeka@gmail.com', 'student')",
            "insert into SUBJECT values (SUBJECT_SEQ.nextval, 'Database', 'In this course you can learn about such a subject as \"Databases\". " +
                    "For the course will be studied: the creation of tables, indexes, triggers, the use of aggregate functions and much more.')",
            "insert into SUBJECT values (SUBJECT_SEQ.nextval, 'Programming', 'This course is designed for those who want to get to our company ...')",
            "insert into SUBJECT values (SUBJECT_SEQ.nextval, 'QA', 'We will test something.')",
            "insert into SUBJECT values (SUBJECT_SEQ.nextval, 'Analyst', 'I do not know why you need this subject.')",
            "insert into task values (TASK_SEQ.nextval, 1, 'Laba 1', 'Print everything from the emp table.', 4)",
            "insert into task values (TASK_SEQ.nextval, 1, 'Laba 2', 'Accounting needs a report containing the employee number, his current salary and salary increased by 20%. Name this column New Salary.', 6)",
            "insert into task values (TASK_SEQ.nextval, 1, 'Laba 3', 'Form a request that displays the name of the employee and his commission. " +
                    "If there is no commission in the column, “No commissions” should be displayed.', 5)",
            "insert into task values (TASK_SEQ.nextval, 1, 'Laba 4', 'Form a query that displays the name of the employee and the city in which he works.', 5)",
            "insert into task values (TASK_SEQ.nextval, 2, 'Practice 1', 'Create a class \"Task\" and complete it.', 5)",
            "insert into task values (TASK_SEQ.nextval, 2, 'Practice 2', 'Create a class \"ArrayTaskList\" and complete it.', 5)",
            "insert into task values (TASK_SEQ.nextval, 2, 'Practice 3', 'Create a class \"LinkedTaskList\" and complete it.', 5)",
            "insert into task values (TASK_SEQ.nextval, 2, 'Practice 4', 'Create iterator for all class.', 5)",
            "insert into task values (TASK_SEQ.nextval, 3, 'Practice 1', 'Test something.', 20)",
            "insert into task values (TASK_SEQ.nextval, 3, 'Practice 2', 'Test something.', 30)",
            "insert into task values (TASK_SEQ.nextval, 3, 'Practice 3', 'Test something.', 25)",
            "insert into task values (TASK_SEQ.nextval, 4, 'Practice 1', 'Analyze something.', 100)",
            "insert into task values (TASK_SEQ.nextval, 4, 'Practice 2', 'Analyze something.', 100)",
            "insert into task values (TASK_SEQ.nextval, 4, 'Practice 3', 'Analyze something.', 100)",
            "insert into TEACHER_SUBJECT values (4, 1)",
            "insert into TEACHER_SUBJECT values (4, 3)",
            "insert into TEACHER_SUBJECT values (5, 2)",
            "insert into TEACHER_SUBJECT values (5, 4)"));
}
