-- user_tb
INSERT INTO user_tb
    (username, password, nickname, name, tel, email, registered_at)
VALUES ('user1', '1234', '성재', '김성재', '01012345555', 'user1@gmail.com', NOW()),
       ('user2', '1234', '정현', '조정현', '01012346666', 'user2@gmail.com', NOW()),
       ('user3', '1234', '현정', '장현정', '01012347777', 'user3@gmail.com', NOW()),
       ('user4', '1234', '윤정', '최윤정', '01012348888', 'user4@gmail.com', NOW()),
       ('user5', '1234', '찬혁', '박찬혁', '01012349999', 'user5@gmail.com', NOW());

-- store_tb
INSERT INTO store_tb
(username, password, owner_name, owner_tel, owner_email, biz_num, name, tel, intro, file_path, opening_time, closing_time,
 closed_day, address, registered_at)
VALUES ('조정현', '1234', 'david1234', '01012345678', 'david1234@gmail.com', '123-456-7890', '단밤 카페', '01012345678',
        '단밤 라떼 맛집', '사진.jpeg', '07:00', '20:00', '매주 월요일', '부산시 부산진구 서면문화로 1 302-40', NOW()),
       ('김성재', '1234', 'jake1234', '01012345678', 'jake1234@gmail.com', '123-456-7890', '꿀밤 카페', '01012345678',
        '꿀밤 라떼 맛집', '사진.jpeg', '07:00', '20:00', '매주 화요일', '부산시 부산진구 서면문화로 2 302-40', NOW()),
       ('박찬혁', '1234', 'hyeok1234', '01012345678', 'hyeok1234@gmail.com', '123-456-7890', '유자 카페', '01012345678',
        '유자 라떼 맛집', '사진.jpeg', '07:00', '20:00', '매주 수요일', '부산시 부산진구 서면문화로 3 302-40', NOW()),
       ('장현정', '1234', 'hana1234', '01012345678', 'hana1234@gmail.com', '123-456-7890', '오렌지 카페', '01012345678',
        '오렌지 라떼 맛집', '사진.jpeg', '07:00', '20:00', '매주 목요일', '부산시 부산진구 서면문화로 4 302-40', NOW()),
       ('최윤정', '1234', 'qty1234', '01012345678', 'qty1234@gmail.com', '123-456-7890', '군밤 카페', '01012345678',
        '군밤 라떼 맛집', '사진.jpeg', '07:00', '20:00', '매주 금요일', '부산시 부산진구 서면문화로 5 302-40', NOW());


-- menu_tb
INSERT INTO menu_tb
    (store_id, category, name, price, description, registered_at)
VALUES
-- 1번 매장 메뉴
(1, 'coffee', '아메리카노', 3000, '현대인의 필수 카페인', NOW()),
(1, 'coffee', '카페라떼', 4000, '부드러운 에스프레소와 부드러운 우유의 조화', NOW()),
(1, 'coffee', '바닐라라떼', 4500, '바닐라 향이 더해진 부드러운 카페 라떼', NOW()),
(1, 'coffee', '카페모카', 4500, '초콜릿의 달콤한 맛이 더해진 에스프레소 음료', NOW()),
(1, 'coffee', '카라멜마끼아또', 5000, '달콤한 카라멜과 부드러운 우유, 에스프레소의 조화', NOW()),
-- 2번 매장 메뉴
(2, 'coffee', '아메리카노', 3500, '시원한 아메리카노', NOW()),
(2, 'coffee', '카페 라떼', 4500, '부드러운 에스프레소와 우유의 조화', NOW()),
(2, 'coffee', '카페 모카', 5000, '달콤한 초콜릿 향이 나는 모카', NOW()),
(2, 'coffee', '바닐라 라떼', 4500, '부드러운 바닐라 향이 나는 라떼', NOW()),
(2, 'coffee', '카라멜 마끼아또', 5000, '달콤한 카라멜의 향과 에스프레소가 어우러진 음료', NOW()),
-- 3번 매장 메뉴
(3, 'coffee', '아메리카노', 3500, '신선하고 깔끔한 아메리카노 한 잔', NOW()),
(3, 'coffee', '카페 라떼', 4500, '부드러운 우유와 풍부한 에스프레소의 조합, 라떼의 완성', NOW()),
(3, 'coffee', '카페 모카', 5000, '진한 초콜릿과 부드러운 우유, 에스프레소의 환상적인 만남', NOW()),
(3, 'coffee', '바닐라 라떼', 4500, '달콤한 바닐라 향과 부드러운 우유가 어우러진 라떼', NOW()),
(3, 'coffee', '카라멜 마끼아또', 5000, '달콤한 카라멜과 진한 에스프레소의 환상적인 조화', NOW()),
-- 4번 매장 메뉴
(4, 'coffee', '아메리카노', 3600, '진하고 강렬한 아메리카노의 매력', NOW()),
(4, 'coffee', '카페 라떼', 4700, '부드러운 우유와 고소한 에스프레소의 만남, 라떼의 향연', NOW()),
(4, 'coffee', '카페 모카', 5200, '달콤한 초콜릿과 부드러운 우유, 에스프레소의 환상적인 조합', NOW()),
(4, 'coffee', '바닐라 라떼', 4700, '부드러운 바닐라의 달콤함이 가득한 라떼', NOW()),
(4, 'coffee', '카라멜 마끼아또', 5200, '부드러운 우유와 달콤한 카라멜, 진한 에스프레소의 조화', NOW()),
-- 5번 매장 메뉴
(5, 'coffee', '아메리카노', 3700, '끊임없는 플로우와 함께하는 강렬한 아메리카노의 세계로 초대합니다.', NOW()),
(5, 'coffee', '카페 라떼', 4800, '우아한 우유의 춤과 함께하는 부드러운 에스프레소의 라떼, 여유로운 한잔.', NOW()),
(5, 'coffee', '카페 모카', 5300, '달콤한 초콜릿의 향과 고소한 우유, 그리고 진한 에스프레소가 어우러진 세상.', NOW()),
(5, 'coffee', '바닐라 라떼', 4800, '부드러운 바닐라의 향과 우유의 깊은 맛이 담긴 라떼, 달콤한 여행의 시작.', NOW()),
(5, 'coffee', '카라멜 마끼아또', 5300, '달콤한 카라멜과 진한 에스프레소가 만나는 환상적인 순간, 취향을 자극하는 끝내주는 음료.', NOW());


-- option_tb
INSERT INTO option_tb
    (menu_id, name, price, is_required)
VALUES
-- 아이스, 샷추가, 설탕시럽, 바닐라시럽, 헤이즐넛시럽
-- ice는 1이면 ice, 0이면 hot
-- 1번 가게
-- 아메리카노
(1, 'ICE', 0, 1),
(1, 'HOT', 0, 1),
(1, 'shot', 500, 0),
(1, 'sugar syrup', 0, 0),
(1, 'vanilla syrup', 500, 0),
(1, 'hazelnut syrup', 500, 0),
-- 카페라떼
(2, 'ICE', 0, 1),
(2, 'HOT', 0, 1),
(2, 'shot', 500, 0),
(2, 'sugar syrup', 0, 0),
(2, 'vanilla syrup', 500, 0),
(2, 'hazelnut syrup', 500, 0),
-- 바닐라라떼
(3, 'ICE', 0, 1),
(3, 'HOT', 0, 1),
(3, 'shot', 500, 0),
(3, 'sugar syrup', 0, 0),
(3, 'vanilla syrup', 500, 0),
(3, 'hazelnut syrup', 500, 0),
-- 카페모가
(4, 'ICE', 0, 1),
(4, 'HOT', 0, 1),
(4, 'shot', 500, 0),
(4, 'sugar syrup', 0, 0),
(4, 'vanilla syrup', 500, 0),
(4, 'hazelnut syrup', 500, 0),
-- 카라멜마키아토
(5, 'ICE', 0, 1),
(5, 'HOT', 0, 1),
(5, 'shot', 500, 0),
(5, 'sugar syrup', 0, 0),
(5, 'vanilla syrup', 500, 0),
(5, 'hazelnut syrup', 500, 0),
-- 2번 가게
-- 아메리카노
(6, 'ICE', 0, 1),
(6, 'HOT', 0, 1),
(6, 'shot', 500, 0),
(6, 'sugar syrup', 0, 0),
(6, 'vanilla syrup', 500, 0),
(6, 'hazelnut syrup', 500, 0),
-- 카페라떼
(7, 'ICE', 0, 1),
(7, 'HOT', 0, 1),
(7, 'shot', 500, 0),
(7, 'sugar syrup', 0, 0),
(7, 'vanilla syrup', 500, 0),
(7, 'hazelnut syrup', 500, 0),
-- 바닐라라떼
(8, 'ICE', 0, 1),
(8, 'HOT', 0, 1),
(8, 'shot', 500, 0),
(8, 'sugar syrup', 0, 0),
(8, 'vanilla syrup', 500, 0),
(8, 'hazelnut syrup', 500, 0),
-- 카페모가
(9, 'ICE', 0, 1),
(9, 'HOT', 0, 1),
(9, 'shot', 500, 0),
(9, 'sugar syrup', 0, 0),
(9, 'vanilla syrup', 500, 0),
(9, 'hazelnut syrup', 500, 0),
-- 카라멜마키아토
(10, 'ICE', 0, 1),
(10, 'HOT', 0, 1),
(10, 'shot', 500, 0),
(10, 'sugar syrup', 0, 0),
(10, 'vanilla syrup', 500, 0),
(10, 'hazelnut syrup', 500, 0),
-- 3번 가게
-- 아메리카노
(11, 'ICE', 0, 1),
(11, 'HOT', 0, 1),
(11, 'shot', 500, 0),
(11, 'sugar syrup', 0, 0),
(11, 'vanilla syrup', 500, 0),
(11, 'hazelnut syrup', 500, 0),
-- 카페라떼
(12, 'ICE', 0, 1),
(12, 'HOT', 0, 1),
(12, 'shot', 500, 0),
(12, 'sugar syrup', 0, 0),
(12, 'vanilla syrup', 500, 0),
(12, 'hazelnut syrup', 500, 0),
-- 바닐라라떼
(13, 'ICE', 0, 1),
(13, 'HOT', 0, 1),
(13, 'shot', 500, 0),
(13, 'sugar syrup', 0, 0),
(13, 'vanilla syrup', 500, 0),
(13, 'hazelnut syrup', 500, 0),
-- 카페모가
(14, 'ICE', 0, 1),
(14, 'HOT', 0, 1),
(14, 'shot', 500, 0),
(14, 'sugar syrup', 0, 0),
(14, 'vanilla syrup', 500, 0),
(14, 'hazelnut syrup', 500, 0),
-- 카라멜마키아토
(15, 'ICE', 0, 1),
(15, 'HOT', 0, 1),
(15, 'shot', 500, 0),
(15, 'sugar syrup', 0, 0),
(15, 'vanilla syrup', 500, 0),
(15, 'hazelnut syrup', 500, 0),
-- 4번 가게
-- 아메리카노
(16, 'ICE', 0, 1),
(16, 'HOT', 0, 1),
(16, 'shot', 500, 0),
(16, 'sugar syrup', 0, 0),
(16, 'vanilla syrup', 500, 0),
(16, 'hazelnut syrup', 500, 0),
-- 카페라떼
(17, 'ICE', 0, 1),
(17, 'HOT', 0, 1),
(17, 'shot', 500, 0),
(17, 'sugar syrup', 0, 0),
(17, 'vanilla syrup', 500, 0),
(17, 'hazelnut syrup', 500, 0),
-- 바닐라라떼
(18, 'ICE', 0, 1),
(18, 'HOT', 0, 1),
(18, 'shot', 500, 0),
(18, 'sugar syrup', 0, 0),
(18, 'vanilla syrup', 500, 0),
(18, 'hazelnut syrup', 500, 0),
-- 카페모가
(19, 'ICE', 0, 1),
(19, 'HOT', 0, 1),
(19, 'shot', 500, 0),
(19, 'sugar syrup', 0, 0),
(19, 'vanilla syrup', 500, 0),
(19, 'hazelnut syrup', 500, 0),
-- 카라멜마키아토
(20, 'ICE', 0, 1),
(20, 'HOT', 0, 1),
(20, 'shot', 500, 0),
(20, 'sugar syrup', 0, 0),
(20, 'vanilla syrup', 500, 0),
(20, 'hazelnut syrup', 500, 0),
-- 4번 가게
-- 아메리카노
(21, 'ICE', 0, 1),
(21, 'HOT', 0, 1),
(21, 'shot', 500, 0),
(21, 'sugar syrup', 0, 0),
(21, 'vanilla syrup', 500, 0),
(21, 'hazelnut syrup', 500, 0),
-- 카페라떼
(22, 'ICE', 0, 1),
(22, 'HOT', 0, 1),
(22, 'shot', 500, 0),
(22, 'sugar syrup', 0, 0),
(22, 'vanilla syrup', 500, 0),
(22, 'hazelnut syrup', 500, 0),
-- 바닐라라떼
(23, 'ICE', 0, 1),
(23, 'HOT', 0, 1),
(23, 'shot', 500, 0),
(23, 'sugar syrup', 0, 0),
(23, 'vanilla syrup', 500, 0),
(23, 'hazelnut syrup', 500, 0),
-- 카페모가
(24, 'ICE', 0, 1),
(24, 'HOT', 0, 1),
(24, 'shot', 500, 0),
(24, 'sugar syrup', 0, 0),
(24, 'vanilla syrup', 500, 0),
(24, 'hazelnut syrup', 500, 0),
-- 카라멜마키아토
(25, 'ICE', 0, 1),
(25, 'HOT', 0, 1),
(25, 'shot', 500, 0),
(25, 'sugar syrup', 0, 0),
(25, 'vanilla syrup', 500, 0),
(25, 'hazelnut syrup', 500, 0);


-- order_tb, order_menu_tb, order_option_tb
-- order_id: 1
INSERT INTO order_tb
(store_id, store_name, customer_id, customer_name, request, total_amount, created_at)
VALUES (1, '단밤 카페', 1, '김성재', '바닐라라떼 얼음 많이 넣어주세요.', (3000 + (3000 + 500) + 4500), NOW());
INSERT INTO order_menu_tb(order_id, name, qty, price, created_at)
VALUES (1, '아메리카노', 1, 3000, NOW()), -- id: 1
       (1, '아메리카노', 1, 3000, NOW()), -- id: 2
       (1, '바닐라라떼', 1, 4500, NOW()); -- id: 3
INSERT INTO order_option_tb(order_menu_id, name, price, created_at)
VALUES (1, 'ICE', 0, NOW()),
       (2, 'HOT', 0, NOW()),
       (2, 'vanilla syrup', 500, NOW()),
       (3, 'ICE', 0, NOW());

-- order_id: 2
INSERT INTO order_tb (store_id, store_name, customer_id, customer_name, request, total_amount, created_at)
VALUES (1, '단밤 카페', 2, '정현', '카라멜 마끼아또 얼음은 적게 넣어주세요.', ((4000) + (4500 + 500) + (4500 + 500) + (5000 + 500)), NOW());
INSERT INTO order_menu_tb (order_id, name, qty, price, created_at)
VALUES (2, '카페 라떼', 1, 4000, NOW()), -- id: 4
       (2, '카페 모카', 1, 4500, NOW()), -- id: 5
       (2, '카페 모카', 1, 4500, NOW()), -- id: 6
       (2, '카라멜 마끼아또', 1, 5000, NOW()); -- id: 7
INSERT INTO order_option_tb (order_menu_id, name, price, created_at)
VALUES (4, 'HOT', 0, NOW()),
       (4, 'sugar syrup', 0, NOW()),
       (5, 'ICE', 0, NOW()),
       (5, 'shot', 500, NOW()),
       (6, 'ICE', 0, NOW()),
       (6, 'vanilla syrup', 500, NOW()),
       (7, 'ICE', 0, NOW()),
       (7, 'hazelnut syrup', 500, NOW());

-- order_id: 3
INSERT INTO order_tb (store_id, store_name, customer_id, customer_name, request, total_amount, created_at)
VALUES (2, '꿀밤 카페', 3, '현정', '후기 남길게요. 무료로 샷 추가해주세요.', ((3500 + 500) + (3500) + (4500 + 500) + (5000 + 500)), NOW());
INSERT INTO order_menu_tb (order_id, name, qty, price, created_at)
VALUES (3, '아메리카노', 1, 3500, NOW()),  -- id: 8
       (3, '아메리카노', 1, 3500, NOW()),  -- id: 9
       (3, '바닐라 라떼', 1, 4500, NOW()), -- id: 10
       (3, '카페 모카', 1, 5000, NOW()); -- id: 11
INSERT INTO order_option_tb (order_menu_id, name, price, created_at)
VALUES (8, 'ICE', 0, NOW()),
       (8, 'shot', 500, NOW()),
       (9, 'HOT', 0, NOW()),
       (9, 'sugar syrup', 0, NOW()),
       (10, 'ICE', 0, NOW()),
       (10, 'vanilla syrup', 500, NOW()),
       (11, 'HOT', 0, NOW()),
       (11, 'hazelnut syrup', 500, NOW());

-- order_id: 4
INSERT INTO order_tb (store_id, store_name, customer_id, customer_name, request, total_amount, created_at)
VALUES (3, '유자 카페', 4, '윤정', '후기 쓸게요. 에스프레소 샷 추가해주삼.', ((3500 + 500) + (3500 + 500) + (3500) + (4500 + 500) + (5000)),
        NOW());
INSERT INTO order_menu_tb (order_id, name, qty, price, created_at)
VALUES (4, '아메리카노', 1, 3500, NOW()), -- id: 12
       (4, '아메리카노', 1, 3500, NOW()), -- id: 13
       (4, '아메리카노', 1, 3500, NOW()), -- id: 14
       (4, '카페 라떼', 1, 4500, NOW()), -- id: 15
       (4, '카페 모카', 1, 5000, NOW()); -- id: 16
INSERT INTO order_option_tb (order_menu_id, name, price, created_at)
VALUES (12, 'ICE', 0, NOW()),
       (12, 'vanilla syrup', 500, NOW()),
       (13, 'ICE', 0, NOW()),
       (13, 'hazelnut syrup', 500, NOW()),
       (14, 'ICE', 0, NOW()),
       (15, 'HOT', 0, NOW()),
       (15, 'shot', 500, NOW()),
       (16, 'HOT', 0, NOW());

-- order_id: 5
INSERT INTO order_tb (store_id, store_name, customer_id, customer_name, request, total_amount, created_at)
VALUES (4, '오렌지 카페', 5, '찬혁', '뜨거운 아메리카노 엄청 뜨겁게 해주세요.', ((3600) + (3600) + (4700 + 500) + (5200 + 500) + (5200)),
        NOW());
INSERT INTO order_menu_tb (order_id, name, qty, price, created_at)
VALUES (5, '아메리카노', 1, 3600, NOW()),  -- id: 17
       (5, '아메리카노', 1, 3600, NOW()),  -- id: 18
       (5, '바닐라 라떼', 1, 4700, NOW()), -- id: 19
       (5, '카페 모카', 1, 5200, NOW()),  -- id: 20
       (5, '카페 모카', 1, 5200, NOW()); -- id: 21
INSERT INTO order_option_tb (order_menu_id, name, price, created_at)
VALUES (17, 'ICE', 0, NOW()),
       (18, 'HOT', 0, NOW()),
       (19, 'ICE', 0, NOW()),
       (19, 'vanilla syrup', 500, NOW()),
       (20, 'ICE', 0, NOW()),
       (20, 'hazelnut syrup', 500, NOW()),
       (21, 'ICE', 0, NOW()),
       (21, 'sugar syrup', 0, NOW());

-- order_id: 6
INSERT INTO order_tb (store_id, store_name, customer_id, customer_name, request, total_amount, created_at)
VALUES (1, '단밤 카페', 4, '윤정', '뜨거운 아메리카노 반 샷만 넣어주세요.', ((3000 + 500) + (3000 + 500) + (4500 + 500)), NOW());
INSERT INTO order_menu_tb (order_id, name, qty, price, created_at)
VALUES (6, '아메리카노', 1, 3000, NOW()), -- id: 22
       (6, '아메리카노', 1, 3000, NOW()), -- id: 23
       (6, '바닐라라떼', 1, 4500, NOW()); -- id: 24
INSERT INTO order_option_tb (order_menu_id, name, price, created_at)
VALUES (22, 'HOT', 0, NOW()),
       (22, 'vanilla syrup', 500, NOW()),
       (23, 'ICE', 0, NOW()),
       (23, 'vanilla syrup', 500, NOW()),
       (24, 'ICE', 0, NOW()),
       (24, 'vanilla syrup', 500, NOW());

-- order_id: 7(request 없음)
INSERT INTO order_tb (store_id, store_name, customer_id, customer_name, total_amount, created_at)
VALUES (1, '단밤 카페', 5, '찬혁', ((3000 + 500) + (3000) + (4500 + 500)), NOW());
INSERT INTO order_menu_tb (order_id, name, qty, price, created_at)
VALUES (7, '아메리카노', 1, 3000, NOW()), -- id: 25
       (7, '아메리카노', 1, 3000, NOW()), -- id: 26
       (7, '바닐라 라떼', 1, 4500, NOW()); -- id: 27
INSERT INTO order_option_tb (order_menu_id, name, price, created_at)
VALUES (25, 'ice', 0, NOW()),
       (25, 'shot', 500, NOW()),
       (26, 'HOT', 0, NOW()),
       (26, 'sugar syrup', 0, NOW()),
       (27, 'ice', 0, NOW()),
       (27, 'vanilla syrup', 500, NOW());

-- order_id: 8
INSERT INTO order_tb (store_id, store_name, customer_id, customer_name, request, total_amount, created_at)
VALUES (1, '단밤 카페', 3, '현정', '캐리어에 담아주세요.', ((3000 + 500) + (3000 + 500) + (3000 + 500) + (4500 + 500)), NOW());
INSERT INTO order_menu_tb (order_id, name, qty, price, created_at)
VALUES (8, '아메리카노', 1, 3000, NOW()), -- id: 28
       (8, '아메리카노', 1, 3000, NOW()), -- id: 29
       (8, '아메리카노', 1, 3000, NOW()), -- id: 30
       (8, '바닐라라떼', 1, 4500, NOW()); -- id: 31
INSERT INTO order_option_tb (order_menu_id, name, price, created_at)
VALUES (28, 'ICE', 0, NOW()),
       (28, 'vanilla syrup', 500, NOW()),
       (29, 'ICE', 0, NOW()),
       (29, 'vanilla syrup', 500, NOW()),
       (30, 'hot', 0, NOW()),
       (30, 'vanilla syrup', 500, NOW()),
       (31, 'ICE', 0, NOW()),
       (31, 'vanilla syrup', 500, NOW());

-- order_id: 9(request 없음)
INSERT INTO order_tb (store_id, store_name, customer_id, customer_name, total_amount, created_at)
VALUES (4, '오렌지 카페', 2, '정현', ((3000 + 500) + (3000) + (4500 + 500)), NOW());
INSERT INTO order_menu_tb (order_id, name, qty, price, created_at)
VALUES (9, '아메리카노', 1, 3000, NOW()), -- id: 32
       (9, '아메리카노', 1, 3000, NOW()), -- id: 33
       (9, '바닐라 라떼', 1, 4500, NOW()); -- id: 34
INSERT INTO order_option_tb (order_menu_id, name, price, created_at)
VALUES (32, 'ICE', 0, NOW()),
       (32, 'shot', 500, NOW()),
       (33, 'HOT', 0, NOW()),
       (33, 'sugar syrup', 0, NOW()),
       (34, 'ICE', 0, NOW()),
       (34, 'vanilla syrup', 500, NOW());

-- order_id: 10(request 없음)
INSERT INTO order_tb (store_id, store_name, customer_id, customer_name, total_amount, created_at)
VALUES (3, '유자 카페', 5, '찬혁', ((3000 + 500) + (3000) + (4500 + 500)), NOW());
INSERT INTO order_menu_tb (order_id, name, qty, price, created_at)
VALUES (10, '아메리카노', 1, 3000, NOW()), -- id: 35
       (10, '아메리카노', 1, 3000, NOW()), -- id: 36
       (10, '바닐라 라떼', 1, 4500, NOW()); -- id: 37
INSERT INTO order_option_tb (order_menu_id, name, price, created_at)
VALUES (35, 'ICE', 0, NOW()),
       (35, 'shot', 500, NOW()),
       (36, 'HOT', 0, NOW()),
       (36, 'sugar syrup', 0, NOW()),
       (37, 'ICE', 0, NOW()),
       (37, 'vanilla syrup', 500, NOW());

-- order_id: 11(request 없음)
INSERT INTO order_tb (store_id, store_name, customer_id, customer_name, total_amount, created_at)
VALUES (2, '꿀밤 카페', 5, '찬혁', ((3500 + 500) + (3500) + (5000 + 500)), NOW());
INSERT INTO order_menu_tb (order_id, name, qty, price, created_at)
VALUES (11, '아메리카노', 1, 3500, NOW()), -- id: 38
       (11, '아메리카노', 1, 3500, NOW()), -- id: 39
       (11, '카페 모카', 1, 5000, NOW()); -- id: 40
INSERT INTO order_option_tb (order_menu_id, name, price, created_at)
VALUES (38, 'ICE', 0, NOW()),
       (38, 'shot', 500, NOW()),
       (39, 'HOT', 0, NOW()),
       (39, 'sugar syrup', 0, NOW()),
       (40, 'HOT', 0, NOW()),
       (40, 'vanilla syrup', 500, NOW());