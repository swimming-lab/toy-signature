-- ROLE
INSERT INTO AUTHORITY (authority_name)
VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (authority_name)
VALUES ('ROLE_ADMIN');

-- USERS
INSERT INTO USERS (id, email, password, image, name, created_at, updated_at)
VALUES (101, 'admin@admin.admin', '$2a$10$FwTyB4dy9JFalDTsOBMxV.gtUE3FikM0MRm3aQbPJqYd9JY3gcWtG', NULL, 'admin', NOW(), NOW());
-- password=admin
INSERT INTO USERS (id, email, password, image, name, created_at, updated_at)
VALUES (102, 'test@test.test', '$2a$10$eEom3YDJu1illn94/rtYY.JHwk193hQ6QPHsMNXeMqWjj/Xvgfbaq', NULL, 'test', NOW(), NOW());
-- password=test

-- USER_AUTHORITY
INSERT INTO USER_AUTHORITY (user_id, authority_name)
VALUES (101, 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (user_id, authority_name)
VALUES (102, 'ROLE_USER');

INSERT INTO EQUIP_TYPE (type, heavy) VALUES ('굴삭기', '1톤');
INSERT INTO EQUIP_TYPE (type, heavy) VALUES ('굴삭기', '1.5톤');
INSERT INTO EQUIP_TYPE (type, heavy) VALUES ('굴삭기', '2톤');
INSERT INTO EQUIP_TYPE (type, heavy) VALUES ('덤프트럭', '1톤');
INSERT INTO EQUIP_TYPE (type, heavy) VALUES ('덤프트럭', '1.5톤');
INSERT INTO EQUIP_TYPE (type, heavy) VALUES ('덤프트럭', '2톤');
INSERT INTO EQUIP_TYPE (type, heavy) VALUES ('지게차', '1톤');
INSERT INTO EQUIP_TYPE (type, heavy) VALUES ('지게차', '1.5톤');
INSERT INTO EQUIP_TYPE (type, heavy) VALUES ('지게차', '2톤');

INSERT INTO EQUIP_BRAND (brand_name) VALUES ('토요타');
INSERT INTO EQUIP_BRAND (brand_name) VALUES ('현대');
INSERT INTO EQUIP_BRAND (brand_name) VALUES ('벤츠');
