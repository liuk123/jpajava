DELETE FROM user_contribution;
DELETE FROM user;
INSERT INTO user (id, name, email, username)
VALUES (1, 'liuk', 'mail@wcj.plus', 'liuk1'),
       (2, 'test', 'mail1@wcj.plus', 'test1'),
       (3, 'admin', 'mail2@wcj.plus', 'admin1');

INSERT INTO user_contribution (id, user_id, repository)
VALUES (1, 1, 'galaxy-sea/spring-cloud-apisix'),
       (2, 2, 'spring-cloud/spring-cloud-commons'),
       (3, 2, 'spring-cloud/spring-cloud-openfeign'),
       (4, 2, 'alibaba/spring-cloud-alibaba'),
       (5, 3, 'Tencent/spring-cloud-tencent'),
       (6, 3, 'apache/apisix-docker');


DELETE FROM permission;
INSERT INTO permission (id, permission)
VALUES  (1, 'github:pr:merge'),
        (2, 'github:pr:close'),
        (3, 'github:pr:open'),
        (4, 'github:pr:comment'),
        (5, 'npm:install'),
        (6, 'npm:update'),
        (7, 'npm:build');

DELETE FROM abac;
INSERT INTO abac (id, expression)
VALUES (1, 'contributions.contains("galaxy-sea/spring-cloud-apisix")'),
       (2, 'username == "admin1"'),
       (3, 'metadata.get("ip") == "192.168.0.1"');

DELETE FROM abac_permissions;
INSERT INTO abac_permissions (abac_id, permissions_id)
VALUES  (1, 1),
        (2, 1),
        (2, 2),
        (2, 3),
        (2, 4),
        (3, 1),
        (3, 2),
        (3, 3),
        (3, 4);

DELETE FROM role;
INSERT INTO role (id, name)
VALUES (1, 'teacher'),
    (2,'student'),
    (3, 'principal');
