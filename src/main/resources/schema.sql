CREATE TABLE if not exists mark
(
    id integer primary key not null,
    name text not null,
    value text not null
);

CREATE TABLE if not exists exam_type
(
    id   integer primary key not null,
    type text                not null
);

CREATE TABLE if not exists study_group
(
    id            integer primary key not null,
    name text             not null
);

CREATE TABLE if not exists subject
(
    id            integer primary key not null,
    name text             not null,
    short_name       text                 not null
);

CREATE TABLE if not exists student
(
    id            integer primary key not null,
    surname    text             not null,
    name text             not null,
    second_name       text                 not null,
    study_group_id integer not null,
    foreign key (study_group_id) references study_group (id)
    );

CREATE TABLE if not exists student_local
(
    id            integer primary key not null,
    surname    text             not null,
    name text             not null,
    second_name       text                 not null,
    study_group_id integer not null,
    foreign key (study_group_id) references study_group (id)
    );

CREATE TABLE if not exists study_plan
(
    id            integer primary key not null,
    subject_id    integer             not null,
    exam_type_id integer             not null,
    foreign key (subject_id) references subject,
    foreign key (exam_type_id) references exam_type (id)
    );

CREATE TABLE if not exists journal
(
    id integer primary key not null,
    student_id integer not null,
    study_plan_id integer not null,
    in_time bit not null,
    count integer not null,
    mark_id integer not null,
    foreign key (student_id) references student (id),
    foreign key (study_plan_id) references study_plan (id),
    foreign key (mark_id) references mark (id)
    );

//alter table study_plan add foreign key (subject_id) references subject ;
//alter table student add foreign key (study_group_id) references study_group (id);
//alter table study_plan add foreign key (exam_type_id) references exam_type (id);
//alter table journal add foreign key (student_id) references student (id);
//alter table journal add foreign key (study_plan_id) references study_plan (id);
//alter table journal add foreign key (mark_id) references mark (id);

insert into subject (id, name, SHORT_NAME) values (1, 'Проектирование информационных систем', 'ПрИС');
insert into subject (id, name, SHORT_NAME) values (2, 'Системы искусственного интеллекта', 'СИИ');
insert into subject (id, name, SHORT_NAME) values (3, 'Программная инженерия', 'ПИ');
insert into subject (id, name, SHORT_NAME) values (4, 'Национальная система информационной безопасности', 'НСИБ');
insert into subject (id, name, SHORT_NAME) values (5, 'Системный анализ', 'СисАнал');
insert into subject (id, name, SHORT_NAME) values (6, 'Распределенные базы данных', 'РБД');
insert into subject (id, name, SHORT_NAME) values (7, 'Системное программное обеспечение', 'СПО');

insert into  exam_type (id, type) values (1, 'Экзамен');
insert into  exam_type (id, type) values (2, 'Зачет');
insert into  exam_type (id, type) values (3, 'Зачет с оценкой');
insert into  exam_type (id, type) values (4, 'Курсовая');

INSERT INTO STUDY_PLAN (id, subject_id, exam_type_id) values (1, 1, 1);
INSERT INTO STUDY_PLAN (id, subject_id, exam_type_id) values (2, 1, 4);
INSERT INTO STUDY_PLAN (id, subject_id, exam_type_id) values (3, 2, 1);
INSERT INTO STUDY_PLAN (id, subject_id, exam_type_id) values (4, 3, 1);
INSERT INTO STUDY_PLAN (id, subject_id, exam_type_id) values (5, 4, 2);
INSERT INTO STUDY_PLAN (id, subject_id, exam_type_id) values (6, 5, 1);
INSERT INTO STUDY_PLAN (id, subject_id, exam_type_id) values (7, 6, 2);
INSERT INTO STUDY_PLAN (id, subject_id, exam_type_id) values (8, 7, 1);

insert into mark (id, name, value) values (1, 'Отлично', 5);
insert into mark (id, name, value) values (2, 'Хорошо', 4);
insert into mark (id, name, value) values (3, 'Удовлетворительно', 3);
insert into mark (id, name, value) values (4, 'Неудовлетворительно', 2);
insert into mark (id, name, value) values (5, 'Зачет', 'з');
insert into mark (id, name, value) values (6, 'Незачет', 'н');
insert into mark (id, name, value) values (7, 'Неявка', '');

insert into study_group (id, name) values (1, 'CBUU');
insert into student_local (id, surname, name, second_name, study_group_id) values (224689, 'Куб', 'Александр', 'Павлович', 1);