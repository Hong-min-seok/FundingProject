-- 데이터삭제
DELETE FUNDINGJOIN;
DELETE WISHLIST;
DELETE PRODUCT;
DELETE ORDERS;
DELETE MEMBER;



COMMIT;

select * from member;

-- 상품데이터 12개
delete from product;
insert into product (name, price, image_path, description) values ('아이패드_SILVER', 260000, 'product01.jpg', '매일하는 작업들은 물론 4K 동영상 편집 가능');
insert into product (name, price, image_path, description) values ('아이패드_GRAY', 279000, 'product02.jpg',  'USB-C를 통한 파일전송까지 모두 가뿐한 A14 Bionic칩의 강력한성능 탑재');
insert into product (name, price, image_path, description) values ('아이패드_BLUE', 298000, 'product03.jpg',  'PDF에, 웹페이지에, 원하는 모든 문서에 Apple Pencil로 끄적끄적');
insert into product (name, price, image_path, description) values ('아이패드_PINK', 300000, 'product04.jpg',  'IPad전용으로 디자인된 강력한 OS탑재');
insert into product (name, price, image_path, description) values ('아이폰_BLACK', 500000, 'product05.jpg',  '듀얼 12MP카메라 시스템 울트라와이드 화면');
insert into product (name, price, image_path, description) values ('아이폰_GREEN', 512000, 'product06.jpg',  '센서 시프트 광학 이미지 흔들림 보정기능 제공');
insert into product (name, price, image_path, description) values ('아이폰_RED', 530000, 'product07.jpg',  '야간모드 인물 사진 및 듀얼 광학 이미지 흔들림 보정기능 제공');
insert into product (name, price, image_path, description) values ('아이폰_WHITE', 526800, 'product08.jpg',  '프로급 트리플 12MP 카메라 시스템 와이트 화면');
insert into product (name, price, image_path, description) values ('아이폰_NAVY', 490000, 'product09.jpg',  '슈퍼레티나 XDR 디스플레이');
insert into product (name, price, image_path, description) values ('애플워치_BLACK', 160000, 'product10.jpg',  '널찍한 Retina 디스플레이, 피트니스용 첨단 측정센서');
insert into product (name, price, image_path, description) values ('애플워치_WHITE', 178600, 'product11.jpg',  '건강과 안전을 지켜주는 강력한 기능 탑재');
insert into product (name, price, image_path, description) values ('애플워치_PINK', 185000, 'product12.jpg',  '일일 활동량을 기록하고, 즐겨하는 운동도 모두 정확하게 측정');
commit;

--멤버데이터 13개
--20대
insert into member(id, password, name, birthday, address, image_path) values ('ken', '1234', '김은나','1996/04/23','서울시 송파구', 'ken.jpg');
insert into member(id, password, name, birthday, address, image_path) values ('lyr', '1234', '임유림','1998/02/22','경기도 성남시 분당구', 'iyl.jpg');
insert into member(id, password, name, birthday, address, image_path) values ('hms', '1234', '홍민석','1996/01/01','인천시 부평구', 'hms.png');
insert into member(id, password, name, birthday, address, image_path) values ('lhb', '1234', '이효범','1994/02/01','경기도 부천시', 'lhb.jpg');

--30대
exec member_pack.procedure_insert_member('bhw', '1234', '방형욱','1988/04/05','경기도');
exec member_pack.procedure_insert_member('khg', '1234', '강현구','1985/12/25','경기도 수원시');


--40대
exec member_pack.procedure_insert_member('kns', '1234', '김남수','1974/08/18','서울시 송파구');
exec member_pack.procedure_insert_member('lck', '1234', '이채경','1976/06/05','서울시 송파구');

--50대
exec member_pack.procedure_insert_member('csh', '1234', '최성호','1967/07/15','서울시 송파구');
exec member_pack.procedure_insert_member('hjs', '1234', '허준수','1966/05/12','서울시 송파구');

--60대
exec member_pack.procedure_insert_member('cmj', '1234', '조민지','1955/01/01','인천시 부평구');
exec member_pack.procedure_insert_member('shw', '1234', '손형욱','1956/02/01','경기도 부천시');

--70대
exec member_pack.procedure_insert_member('kty', '1234', '김태연','1946/01/01','인천시 부평구');
update member set point = 999990;
commit;

-- 위시리스트 추가
var res number
exec :res := wishlist_pack.procedure_insert_wishlist('hms', 3);
print res
exec :res := wishlist_pack.procedure_insert_wishlist('hms', 7);
print res


-- 펀딩하기 8개 데이터
--3번 상품에 대한 펀딩 (아이패드) 
--총 201000원 펀딩 된 상태
var res number
exec :res := fundingjoin_pack.function_choice_fundingjoin(60000, 1000, 'ken')
print res
var res number
exec :res := fundingjoin_pack.function_choice_fundingjoin(120000, 1000, 'bhw')
print res
var res number
exec :res := fundingjoin_pack.function_choice_fundingjoin(20000, 1000, 'csh')
print res
var res number
exec :res := fundingjoin_pack.function_choice_fundingjoin(1000, 1000, 'lhb')
print res


--7번상품에 대한 펀딩 (아이폰)
--총 508000원 펀딩 된 상태
var res number
exec :res := fundingjoin_pack.function_choice_fundingjoin(260000, 1001, 'kty')
print res
var res number
exec :res := fundingjoin_pack.function_choice_fundingjoin(78000, 1001, 'cmj')
print res
var res number
exec :res := fundingjoin_pack.function_choice_fundingjoin(168000, 1001, 'hjs')
print res
var res number
exec :res := fundingjoin_pack.function_choice_fundingjoin(2000, 1001, 'lck')
print res


--주문하기 10개 데이터
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 12, '2020/03/01');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 11, '2020/04/01');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 6, '2020/04/23');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 10, '2020/05/01');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 6, '2020/06/01');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 8, '2020/06/05');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 1, '2020/06/15');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 2, '2020/06/21');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 5, '2020/07/01');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 9, '2020/07/6');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 9, '2020/07/28');

INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 3, '2021/01/01');
INSERT INTO orders(code,member_id, product_code, order_date) VALUES (seq_order.NEXTVAL, 'hms', 7, '2021/02/01');
commit;