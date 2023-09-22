# DELETE FROM user;
# INSERT INTO user (id, name, email)
# VALUES (1, '魏昌进', 'mail@wcj.plus'),
#        (2, 'test', 'mail1@wcj.plus'),
#        (3, 'admin', 'mail2@wcj.plus');
--
-- DELETE FROM user_contribution;
-- INSERT INTO user_contribution (id, user_id, repository)
-- VALUES (1, 1, 'galaxy-sea/spring-cloud-apisix'),
--        (2, 2, 'spring-cloud/spring-cloud-commons'),
--        (3, 2, 'spring-cloud/spring-cloud-openfeign'),
--        (4, 2, 'alibaba/spring-cloud-alibaba'),
--        (5, 2, 'Tencent/spring-cloud-tencent'),
--        (6, 2, 'apache/apisix-docker');
--
-- DELETE FROM permission;
-- INSERT INTO permission (id, permission)
-- VALUES (1, 'github:pr:merge'),
--
--        (2, 'github:pr:close'),
--        (3, 'github:pr:open'),
--        (4, 'github:pr:comment');
--
--
-- DELETE FROM abac;
-- INSERT INTO abac (id, expression)
-- VALUES (1, 'contributions.contains(''galaxy-sea/spring-cloud-apisix'')'),
--        (2, 'name == ''admin'''),
--        (3, 'metadata.get(''ip'') == ''192.168.0.1''');
--
-- DELETE FROM abac_permission;
-- INSERT INTO abac_permission (id, abac_id, permission_id)
-- VALUES (1, 1, 1),
--
--        (2, 2, 1),
--        (3, 2, 2),
--        (4, 2, 3),
--        (5, 2, 4),
--
--        (6, 3, 1),
--        (7, 3, 2),
--        (8, 3, 3),
--        (9, 3, 4);