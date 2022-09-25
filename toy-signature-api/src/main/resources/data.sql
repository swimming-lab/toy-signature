-- ROLE
INSERT INTO AUTHORITY (authority_name)
VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (authority_name)
VALUES ('ROLE_ADMIN');

-- USERS
INSERT INTO USERS (id, email, password, image, name, phone, created_at, updated_at)
VALUES (101, 'admin@admin.admin', '$2a$10$FwTyB4dy9JFalDTsOBMxV.gtUE3FikM0MRm3aQbPJqYd9JY3gcWtG', NULL, 'admin', '01011111111', NOW(), NOW());
-- password=admin
INSERT INTO USERS (id, email, password, image, name, phone, created_at, updated_at)
VALUES (102, 'test1@naver.com', '$2a$10$eEom3YDJu1illn94/rtYY.JHwk193hQ6QPHsMNXeMqWjj/Xvgfbaq', NULL, 'test1', '01022222222', NOW(), NOW());
INSERT INTO USERS (id, email, password, image, name, phone, created_at, updated_at)
VALUES (103, 'test2@daum.net', '$2a$10$eEom3YDJu1illn94/rtYY.JHwk193hQ6QPHsMNXeMqWjj/Xvgfbaq', NULL, 'test2', '01033333333', NOW(), NOW());
-- password=test


-- USER_AUTHORITY
INSERT INTO USER_AUTHORITY (user_id, authority_name)
VALUES (101, 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (user_id, authority_name)
VALUES (102, 'ROLE_USER');

-- ITEM_TYPE
INSERT INTO ITEM_TYPE (type, heavy) VALUES ('굴삭기', '1톤');
INSERT INTO ITEM_TYPE (type, heavy) VALUES ('굴삭기', '1.5톤');
INSERT INTO ITEM_TYPE (type, heavy) VALUES ('굴삭기', '2톤');
INSERT INTO ITEM_TYPE (type, heavy) VALUES ('덤프트럭', '1톤');
INSERT INTO ITEM_TYPE (type, heavy) VALUES ('덤프트럭', '1.5톤');
INSERT INTO ITEM_TYPE (type, heavy) VALUES ('덤프트럭', '2톤');
INSERT INTO ITEM_TYPE (type, heavy) VALUES ('지게차', '1톤');
INSERT INTO ITEM_TYPE (type, heavy) VALUES ('지게차', '1.5톤');
INSERT INTO ITEM_TYPE (type, heavy) VALUES ('지게차', '2톤');

-- ITEM_BRAND
INSERT INTO ITEM_BRAND (brand_name) VALUES ('토요타');
INSERT INTO ITEM_BRAND (brand_name) VALUES ('현대');
INSERT INTO ITEM_BRAND (brand_name) VALUES ('벤츠');
INSERT INTO ITEM_BRAND (brand_name) VALUES ('볼보');

-- ITEMS
INSERT INTO ITEMS (id, created_at, updated_at, author_id, etc, insurance_yn, license_plate, routine_yn, sequence, brand_id, type_id, status) VALUES (101, NOW(), NOW(), 102, NULL, 'Y', 'A1234', 'Y', 1, 1, 1, '01');
INSERT INTO ITEMS (id, created_at, updated_at, author_id, etc, insurance_yn, license_plate, routine_yn, sequence, brand_id, type_id, status) VALUES (102, NOW(), NOW(), 102, NULL, 'Y', 'A5678', 'Y', 1, 2, 2, '01');
INSERT INTO ITEMS (id, created_at, updated_at, author_id, etc, insurance_yn, license_plate, routine_yn, sequence, brand_id, type_id, status) VALUES (103, NOW(), NOW(), 102, NULL, 'Y', 'B1234', 'Y', 1, 3, 1, '01');
INSERT INTO ITEMS (id, created_at, updated_at, author_id, etc, insurance_yn, license_plate, routine_yn, sequence, brand_id, type_id, status) VALUES (104, NOW(), NOW(), 102, NULL, 'Y', 'B5678', 'Y', 1, 4, 2, '01');

-- AGREEMENTS
INSERT INTO AGREEMENTS (id, created_at, updated_at, agreement_type, author_id, amount, end_date, etc, lessee_addr, lessee_id, lessee_name, lessee_tel_no, lessor_addr, lessor_id, lessor_name, lessor_tel_no, over_amount, start_date, status)
VALUES (101, NOW(), NOW(), '00', 102, 100000, NOW(), NULL, '경기도 부천시 오정구 원종동', 103, '침착맨', '010-2222-2222', '경기도 김포시 유현로', 102, '유수영', '010-1111-1111', 10000, NOW(), '02');
-- VALUES (101, NOW(), NOW(), NULL, 102, 100000, NOW(), NULL, '경기도 부천시 오정구 원종동', 103, '침착맨', '010-2222-2222', '경기도 김포시 유현로', 102, '유수영', '010-1111-1111', 10000, NOW(), NULL);
INSERT INTO AGREEMENT_ITEM (id, agreement_id, brand, heavy, insurance_yn, item_id, license_plate, routine_yn, type) VALUES (101, 101, '토요타', '1톤', 'Y', 101, 'A1234', 'Y', '굴삭기');

-- INSERT INTO AGREEMENTS (id, created_at, updated_at, agreement_type, author_id, amount, end_date, etc, lessee_addr, lessee_id, lessee_name, lessee_tel_no, lessor_addr, lessor_id, lessor_name, lessor_tel_no, over_amount, start_date, status)
-- VALUES (102, NOW(), NOW(), '00', 102, 200000, NOW(), NULL, '경기도 부천시 오정구 원종동', 103, '침착맨', '010-2222-2222', '경기도 김포시 유현로', 102, '유수영', '010-1111-1111', 20000, NOW(), '02');
-- INSERT INTO AGREEMENT_ITEM (id, agreement_id, brand, heavy, insurance_yn, item_id, license_plate, routine_yn, type) VALUES (102, 102, '현대', '1.5톤', 'Y', 102, 'A5678', 'Y', '굴삭기');

-- INSERT INTO AGREEMENTS (id, created_at, updated_at, agreement_type, author_id, amount, end_date, etc, lessee_addr, lessee_id, lessee_name, lessee_tel_no, lessor_addr, lessor_id, lessor_name, lessor_tel_no, over_amount, start_date, status)
-- VALUES (103, NOW(), NOW(), '00', 102, 300000, NOW(), NULL, '경기도 부천시 오정구 원종동', 103, '침착맨', '010-2222-2222', '경기도 김포시 유현로', 102, '유수영', '010-1111-1111', 30000, NOW(), '02');
-- INSERT INTO AGREEMENT_ITEM (id, agreement_id, brand, heavy, insurance_yn, item_id, license_plate, routine_yn, type) VALUES (103, 103, '벤츠', '1톤', 'Y', 103, 'B1234', 'Y', '굴삭기');
-- INSERT INTO AGREEMENT_ITEM (id, agreement_id, brand, heavy, insurance_yn, item_id, license_plate, routine_yn, type) VALUES (104, 103, '볼보', '1.5톤', 'Y', 104, 'B5678', 'Y', '굴삭기');