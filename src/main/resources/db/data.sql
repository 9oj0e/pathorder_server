-- user_tb
INSERT INTO user_tb
    (username, password, nickname, name, tel, email, registered_at)
VALUES ('user1', '1234', '성재', '김성재', '01012345555', 'user1@gmail.com', now()),
       ('user2', '1234', '정현', '조정현', '01012346666', 'user2@gmail.com', now()),
       ('user3', '1234', '현정', '장현정', '01012347777', 'user3@gmail.com', now()),
       ('user4', '1234', '윤정', '최윤정', '01012348888', 'user4@gmail.com', now()),
       ('user5', '1234', '찬혁', '박찬혁', '01012349999', 'user5@gmail.com', now());

-- 매장
INSERT INTO store_tb
(username, password, owner_name, owner_tel, owner_email, biz_num, name, tel, intro, img_src, opening_time, closing_time,
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
    (store_id, category, name, price, description, released_at)
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


-- 옵션
INSERT INTO option_tb
    (menu_id, name, price, is_required)
VALUES
-- 아이스, 샷추가, 설탕시럽, 바닐라시럽, 헤이즐넛시럽
-- ice는 1이면 ice, 0이면 hot
-- 1번 가게
-- 아메리카노
(1, 'ice', 0, 1),
(1, 'hot', 0, 1),
(1, 'shot', 500, 0),
(1, 'sugar syrup', 0, 0),
(1, 'vanilla syrup', 500, 0),
(1, 'hazelnut syrup', 500, 0),
-- 카페라떼
(2, 'ice', 0, 1),
(2, 'hot', 0, 1),
(2, 'shot', 500, 0),
(2, 'sugar syrup', 0, 0),
(2, 'vanilla syrup', 500, 0),
(2, 'hazelnut syrup', 500, 0),
-- 바닐라라떼
(3, 'ice', 0, 1),
(3, 'hot', 0, 1),
(3, 'shot', 500, 0),
(3, 'sugar syrup', 0, 0),
(3, 'vanilla syrup', 500, 0),
(3, 'hazelnut syrup', 500, 0),
-- 카페모가
(4, 'ice', 0, 1),
(4, 'hot', 0, 1),
(4, 'shot', 500, 0),
(4, 'sugar syrup', 0, 0),
(4, 'vanilla syrup', 500, 0),
(4, 'hazelnut syrup', 500, 0),
-- 카라멜마키아토
(5, 'ice', 0, 1),
(5, 'hot', 0, 1),
(5, 'shot', 500, 0),
(5, 'sugar syrup', 0, 0),
(5, 'vanilla syrup', 500, 0),
(5, 'hazelnut syrup', 500, 0),
-- 2번 가게
-- 아메리카노
(6, 'ice', 0, 1),
(6, 'hot', 0, 1),
(6, 'shot', 500, 0),
(6, 'sugar syrup', 0, 0),
(6, 'vanilla syrup', 500, 0),
(6, 'hazelnut syrup', 500, 0),
-- 카페라떼
(7, 'ice', 0, 1),
(7, 'hot', 0, 1),
(7, 'shot', 500, 0),
(7, 'sugar syrup', 0, 0),
(7, 'vanilla syrup', 500, 0),
(7, 'hazelnut syrup', 500, 0),
-- 바닐라라떼
(8, 'ice', 0, 1),
(8, 'hot', 0, 1),
(8, 'shot', 500, 0),
(8, 'sugar syrup', 0, 0),
(8, 'vanilla syrup', 500, 0),
(8, 'hazelnut syrup', 500, 0),
-- 카페모가
(9, 'ice', 0, 1),
(9, 'hot', 0, 1),
(9, 'shot', 500, 0),
(9, 'sugar syrup', 0, 0),
(9, 'vanilla syrup', 500, 0),
(9, 'hazelnut syrup', 500, 0),
-- 카라멜마키아토
(10, 'ice', 0, 1),
(10, 'hot', 0, 1),
(10, 'shot', 500, 0),
(10, 'sugar syrup', 0, 0),
(10, 'vanilla syrup', 500, 0),
(10, 'hazelnut syrup', 500, 0),
-- 3번 가게
-- 아메리카노
(11, 'ice', 0, 1),
(11, 'hot', 0, 1),
(11, 'shot', 500, 0),
(11, 'sugar syrup', 0, 0),
(11, 'vanilla syrup', 500, 0),
(11, 'hazelnut syrup', 500, 0),
-- 카페라떼
(12, 'ice', 0, 1),
(12, 'hot', 0, 1),
(12, 'shot', 500, 0),
(12, 'sugar syrup', 0, 0),
(12, 'vanilla syrup', 500, 0),
(12, 'hazelnut syrup', 500, 0),
-- 바닐라라떼
(13, 'ice', 0, 1),
(13, 'hot', 0, 1),
(13, 'shot', 500, 0),
(13, 'sugar syrup', 0, 0),
(13, 'vanilla syrup', 500, 0),
(13, 'hazelnut syrup', 500, 0),
-- 카페모가
(14, 'ice', 0, 1),
(14, 'hot', 0, 1),
(14, 'shot', 500, 0),
(14, 'sugar syrup', 0, 0),
(14, 'vanilla syrup', 500, 0),
(14, 'hazelnut syrup', 500, 0),
-- 카라멜마키아토
(15, 'ice', 0, 1),
(15, 'hot', 0, 1),
(15, 'shot', 500, 0),
(15, 'sugar syrup', 0, 0),
(15, 'vanilla syrup', 500, 0),
(15, 'hazelnut syrup', 500, 0),
-- 4번 가게
-- 아메리카노
(16, 'ice', 0, 1),
(16, 'hot', 0, 1),
(16, 'shot', 500, 0),
(16, 'sugar syrup', 0, 0),
(16, 'vanilla syrup', 500, 0),
(16, 'hazelnut syrup', 500, 0),
-- 카페라떼
(17, 'ice', 0, 1),
(17, 'hot', 0, 1),
(17, 'shot', 500, 0),
(17, 'sugar syrup', 0, 0),
(17, 'vanilla syrup', 500, 0),
(17, 'hazelnut syrup', 500, 0),
-- 바닐라라떼
(18, 'ice', 0, 1),
(18, 'hot', 0, 1),
(18, 'shot', 500, 0),
(18, 'sugar syrup', 0, 0),
(18, 'vanilla syrup', 500, 0),
(18, 'hazelnut syrup', 500, 0),
-- 카페모가
(19, 'ice', 0, 1),
(19, 'hot', 0, 1),
(19, 'shot', 500, 0),
(19, 'sugar syrup', 0, 0),
(19, 'vanilla syrup', 500, 0),
(19, 'hazelnut syrup', 500, 0),
-- 카라멜마키아토
(20, 'ice', 0, 1),
(20, 'hot', 0, 1),
(20, 'shot', 500, 0),
(20, 'sugar syrup', 0, 0),
(20, 'vanilla syrup', 500, 0),
(20, 'hazelnut syrup', 500, 0),
-- 4번 가게
-- 아메리카노
(21, 'ice', 0, 1),
(21, 'hot', 0, 1),
(21, 'shot', 500, 0),
(21, 'sugar syrup', 0, 0),
(21, 'vanilla syrup', 500, 0),
(21, 'hazelnut syrup', 500, 0),
-- 카페라떼
(22, 'ice', 0, 1),
(22, 'hot', 0, 1),
(22, 'shot', 500, 0),
(22, 'sugar syrup', 0, 0),
(22, 'vanilla syrup', 500, 0),
(22, 'hazelnut syrup', 500, 0),
-- 바닐라라떼
(23, 'ice', 0, 1),
(23, 'hot', 0, 1),
(23, 'shot', 500, 0),
(23, 'sugar syrup', 0, 0),
(23, 'vanilla syrup', 500, 0),
(23, 'hazelnut syrup', 500, 0),
-- 카페모가
(24, 'ice', 0, 1),
(24, 'hot', 0, 1),
(24, 'shot', 500, 0),
(24, 'sugar syrup', 0, 0),
(24, 'vanilla syrup', 500, 0),
(24, 'hazelnut syrup', 500, 0),
-- 카라멜마키아토
(25, 'ice', 0, 1),
(25, 'hot', 0, 1),
(25, 'shot', 500, 0),
(25, 'sugar syrup', 0, 0),
(25, 'vanilla syrup', 500, 0),
(25, 'hazelnut syrup', 500, 0);

-- order_tb
INSERT INTO order_tb
(store_id, store_name, customer_id, customer_name, request, total_amount, status, created_at)
VALUES (1, '단밤 카페', 1, '김성재', '얼음 많이 넣어주세요.', 3000, '조리중', now());
INSERT INTO order_tb
(store_id, store_name, customer_id, customer_name, request, total_amount, created_at)
VALUES (1, '단밤 카페', 2, '조정현', '얼음 많이 넣어주세요.', 3000, now());