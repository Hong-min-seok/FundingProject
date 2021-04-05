-- �����ͻ���
DELETE FUNDINGJOIN;
DELETE WISHLIST;
DELETE PRODUCT;
DELETE ORDERS;
DELETE MEMBER;



COMMIT;

select * from member;

-- ��ǰ������ 12��
delete from product;
insert into product (name, price, image_path, description) values ('�����е�_SILVER', 260000, 'product01.jpg', '�����ϴ� �۾����� ���� 4K ������ ���� ����');
insert into product (name, price, image_path, description) values ('�����е�_GRAY', 279000, 'product02.jpg',  'USB-C�� ���� �������۱��� ��� ������ A14 BionicĨ�� �����Ѽ��� ž��');
insert into product (name, price, image_path, description) values ('�����е�_BLUE', 298000, 'product03.jpg',  'PDF��, ����������, ���ϴ� ��� ������ Apple Pencil�� ��������');
insert into product (name, price, image_path, description) values ('�����е�_PINK', 300000, 'product04.jpg',  'IPad�������� �����ε� ������ OSž��');
insert into product (name, price, image_path, description) values ('������_BLACK', 500000, 'product05.jpg',  '��� 12MPī�޶� �ý��� ��Ʈ����̵� ȭ��');
insert into product (name, price, image_path, description) values ('������_GREEN', 512000, 'product06.jpg',  '���� ����Ʈ ���� �̹��� ��鸲 ������� ����');
insert into product (name, price, image_path, description) values ('������_RED', 530000, 'product07.jpg',  '�߰���� �ι� ���� �� ��� ���� �̹��� ��鸲 ������� ����');
insert into product (name, price, image_path, description) values ('������_WHITE', 526800, 'product08.jpg',  '���α� Ʈ���� 12MP ī�޶� �ý��� ����Ʈ ȭ��');
insert into product (name, price, image_path, description) values ('������_NAVY', 490000, 'product09.jpg',  '���۷�Ƽ�� XDR ���÷���');
insert into product (name, price, image_path, description) values ('���ÿ�ġ_BLACK', 160000, 'product10.jpg',  '������ Retina ���÷���, ��Ʈ�Ͻ��� ÷�� ��������');
insert into product (name, price, image_path, description) values ('���ÿ�ġ_WHITE', 178600, 'product11.jpg',  '�ǰ��� ������ �����ִ� ������ ��� ž��');
insert into product (name, price, image_path, description) values ('���ÿ�ġ_PINK', 185000, 'product12.jpg',  '���� Ȱ������ ����ϰ�, ����ϴ� ��� ��� ��Ȯ�ϰ� ����');
commit;

--��������� 13��
--20��
insert into member(id, password, name, birthday, address, image_path) values ('ken', '1234', '������','1996/04/23','����� ���ı�', 'ken.jpg');
insert into member(id, password, name, birthday, address, image_path) values ('lyr', '1234', '������','1998/02/22','��⵵ ������ �д籸', 'iyl.jpg');
insert into member(id, password, name, birthday, address, image_path) values ('hms', '1234', 'ȫ�μ�','1996/01/01','��õ�� ����', 'hms.png');
insert into member(id, password, name, birthday, address, image_path) values ('lhb', '1234', '��ȿ��','1994/02/01','��⵵ ��õ��', 'lhb.jpg');

--30��
exec member_pack.procedure_insert_member('bhw', '1234', '������','1988/04/05','��⵵');
exec member_pack.procedure_insert_member('khg', '1234', '������','1985/12/25','��⵵ ������');


--40��
exec member_pack.procedure_insert_member('kns', '1234', '�賲��','1974/08/18','����� ���ı�');
exec member_pack.procedure_insert_member('lck', '1234', '��ä��','1976/06/05','����� ���ı�');

--50��
exec member_pack.procedure_insert_member('csh', '1234', '�ּ�ȣ','1967/07/15','����� ���ı�');
exec member_pack.procedure_insert_member('hjs', '1234', '���ؼ�','1966/05/12','����� ���ı�');

--60��
exec member_pack.procedure_insert_member('cmj', '1234', '������','1955/01/01','��õ�� ����');
exec member_pack.procedure_insert_member('shw', '1234', '������','1956/02/01','��⵵ ��õ��');

--70��
exec member_pack.procedure_insert_member('kty', '1234', '���¿�','1946/01/01','��õ�� ����');
update member set point = 999990;
commit;

-- ���ø���Ʈ �߰�
var res number
exec :res := wishlist_pack.procedure_insert_wishlist('hms', 3);
print res
exec :res := wishlist_pack.procedure_insert_wishlist('hms', 7);
print res


-- �ݵ��ϱ� 8�� ������
--3�� ��ǰ�� ���� �ݵ� (�����е�) 
--�� 201000�� �ݵ� �� ����
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


--7����ǰ�� ���� �ݵ� (������)
--�� 508000�� �ݵ� �� ����
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


--�ֹ��ϱ� 10�� ������
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