--changeset Alexander:1
-- Заполнение таблицы role

insert into role(name)
values
    ('ROLE_ADMIN'),
    ('ROLE_MENTOR'),
    ('ROLE_STUDENT');

-- Заполнение таблицы admin

insert into admin( family, name, email, role_id, password)
values
    ('Samartsev', 'Aleksandr', 'top_secret99@mail.ru', 1, '$2y$10$51ABYWy1u/EKuGIsmQCnxeOzE9sYBB/w8etyjl/ExyPoPL/c89k2u');

-- Заполнение таблицы mentor

insert into mentor( family, name, email, role_id, password)
values
    ('Almazov', 'Grigory', 'Almazov@mail.ru', 2, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru'),
    ('Evmenov', 'Anton', 'Evmenov@mail.ru', 2, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru');

-- Заполнение таблицы student

insert into student( family, name, email, role_id, password)
values
    ('Ivanov', 'Ivan', 'aaa@mail.ru', 3, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru'),
    ('Semenov', 'Semen', 'bbb@mail.ru', 3, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru'),
    ('Smirnov', 'Alex', 'ccc@mail.ru', 3, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru'),
    ('Petrov', 'Petr', 'vvv@mail.ru', 3, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru');

insert into users(email, role_id, password)
values
    ('top_secret99@mail.ru', 1, '$2y$10$51ABYWy1u/EKuGIsmQCnxeOzE9sYBB/w8etyjl/ExyPoPL/c89k2u'),
    ('Almazov@mail.ru', 2, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru'),
    ('Evmenov@mail.ru', 2, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru'),
    ('aaa@mail.ru', 3, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru'),
    ('bbb@mail.ru', 3, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru'),
    ('ccc@mail.ru', 3, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru'),
    ('vvv@mail.ru', 3, '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru');