-- 회원
INSERT INTO user_tb
    (username, password, nickname, name, tel, email, registered_at)
VALUES ('user1', '1234', '성재', '김성재', '01012345555', 'user1@gmail.com', now()),
       ('user2', '1234', '정현', '조정현', '01012346666', 'user2@gmail.com', now()),
       ('user3', '1234', '현정', '장현정', '01012347777', 'user3@gmail.com', now()),
       ('user4', '1234', '윤정', '최윤정', '01012348888', 'user4@gmail.com', now()),
       ('user5', '1234', '찬혁', '박찬혁', '01012349999', 'user5@gmail.com', now());

-- 매장 업주 회원
INSERT INTO store_tb (username, password, owner_name, owner_tel, owner_email, biz_num, name, tel, intro, opening_time,
                      closing_time, closed_day, address, latitude, longitude, img_filename, registered_at)
VALUES ('david1234', '1234', '조정현', '010-1234-5678', 'david1234@gmail.com', '123-456-7890', '연의양과', '010-1234-5678',
        '까눌레와 다쿠아즈를 전문으로 하며, 매일 아침 제품들을 구워냅니다.', '07:00', '20:00', '매주 월요일', '부산 부산진구 서전로37번길 20 연의양과',
        '35.1587487392983', '129.064002552455', 'default/cafe1.png', NOW()),
       ('jake1234', '1234', '김성재', '010-2345-6789', 'jake1234@gmail.com', '234-567-8901', '커피스가모 인 서면', '010-2345-6789',
        '서면에 몇 없는 주차가능카페!', '07:00', '20:00', '매주 화요일', '부산 부산진구 가야대로755번길 23', '35.1588008058792', '129.054017961614',
        'default/cafe2.png', NOW()),
       ('hyeok1234', '1234', '박찬혁', '010-3456-7890', 'hyeok1234@gmail.com', '345-678-9012', '노이알트', '010-3456-7890',
        '계절의 색을 담은 디저트를 만듭니다.', '07:00', '20:00', '매주 수요일', '부산 부산진구 전포대로246번길 23 1층', '35.1586966174166',
        '129.06686313812', 'default/cafe3.png', NOW()),
       ('hana1234', '1234', '장현정', '010-4567-8901', 'hana1234@gmail.com', '456-789-0123', '블랙업커피 서면본점', '010-4567-8901',
        '블랙업커피 서면점은 2007년에 오픈하여 10년째 운영중인 메인 스토어 입니다.', '07:00', '20:00', '매주 목요일', '부산 부산진구 서전로10번길 41',
        '35.1560557306354', '129.059978704814', 'default/cafe4.png', NOW()),
       ('qty1234', '1234', '최윤정', '010-5678-9012', 'qty1234@gmail.com', '567-890-1234', '디저트 로지', '010-5678-9012',
        '전포 누리마을 뒷쪽 골목 안에 아담하게 자리잡고 있습니다.', '07:00', '20:00', '매주 금요일',
        '부산 부산진구 서전로38번길 43-13 105호(전포동, 성진전자 상가) 디저트로지', '35.1559460199003', '129.064563982545', 'default/cafe5.png',
        NOW());


-- 매장 메뉴
INSERT INTO menu_tb
(store_id, category, name, price, description, img_filename, registered_at)
VALUES
-- 1번 매장 메뉴
(1, 'coffee', '아메리카노', 3000, '현대인의 필수 카페인', 'default/americano.png', NOW()),
(1, 'coffee', '카페라떼', 4000, '부드러운 에스프레소와 부드러운 우유의 조화', 'default/latte.png', NOW()),
(1, 'coffee', '바닐라라떼', 4500, '바닐라 향이 더해진 부드러운 카페 라떼', 'default/latte.png', NOW()),
(1, 'coffee', '카페모카', 4500, '초콜릿의 달콤한 맛이 더해진 에스프레소 음료', 'default/mocha.png', NOW()),
(1, 'coffee', '카라멜마끼아또', 5000, '달콤한 카라멜과 부드러운 우유, 에스프레소의 조화', 'default/macchiato.png', NOW()),
-- 2번 매장 메뉴
(2, 'coffee', '아메리카노', 3500, '시원한 아메리카노', 'default/americano.png', NOW()),
(2, 'coffee', '카페 라떼', 4500, '부드러운 에스프레소와 우유의 조화', 'default/latte.png', NOW()),
(2, 'coffee', '카페 모카', 5000, '달콤한 초콜릿 향이 나는 모카', 'default/mocha.png', NOW()),
(2, 'coffee', '바닐라 라떼', 4500, '부드러운 바닐라 향이 나는 라떼', 'default/latte.png', NOW()),
(2, 'coffee', '카라멜 마끼아또', 5000, '달콤한 카라멜의 향과 에스프레소가 어우러진 음료', 'default/macchiato.png', NOW()),
-- 3번 매장 메뉴
(3, 'coffee', '아메리카노', 3500, '신선하고 깔끔한 아메리카노 한 잔', 'default/americano.png', NOW()),
(3, 'coffee', '카페 라떼', 4500, '부드러운 우유와 풍부한 에스프레소의 조합, 라떼의 완성', 'default/latte.png', NOW()),
(3, 'coffee', '카페 모카', 5000, '진한 초콜릿과 부드러운 우유, 에스프레소의 환상적인 만남', 'default/mocha.png', NOW()),
(3, 'coffee', '바닐라 라떼', 4500, '달콤한 바닐라 향과 부드러운 우유가 어우러진 라떼', 'default/latte.png', NOW()),
(3, 'coffee', '카라멜 마끼아또', 5000, '달콤한 카라멜과 진한 에스프레소의 환상적인 조화', 'default/macchiato.png', NOW()),
-- 4번 매장 메뉴
(4, 'coffee', '아메리카노', 3600, '진하고 강렬한 아메리카노의 매력', 'default/americano.png', NOW()),
(4, 'coffee', '카페 라떼', 4700, '부드러운 우유와 고소한 에스프레소의 만남, 라떼의 향연', 'default/latte.png', NOW()),
(4, 'coffee', '카페 모카', 5200, '달콤한 초콜릿과 부드러운 우유, 에스프레소의 환상적인 조합', 'default/mocha.png', NOW()),
(4, 'coffee', '바닐라 라떼', 4700, '부드러운 바닐라의 달콤함이 가득한 라떼', 'default/latte.png', NOW()),
(4, 'coffee', '카라멜 마끼아또', 5200, '부드러운 우유와 달콤한 카라멜, 진한 에스프레소의 조화', 'default/macchiato.png', NOW()),
-- 5번 매장 메뉴
(5, 'coffee', '아메리카노', 3700, '끊임없는 플로우와 함께하는 강렬한 아메리카노의 세계로 초대합니다.', 'default/americano.png', NOW()),
(5, 'coffee', '카페 라떼', 4800, '우아한 우유의 춤과 함께하는 부드러운 에스프레소의 라떼, 여유로운 한잔.', 'default/latte.png', NOW()),
(5, 'coffee', '카페 모카', 5300, '달콤한 초콜릿의 향과 고소한 우유, 그리고 진한 에스프레소가 어우러진 세상.', 'default/mocha.png', NOW()),
(5, 'coffee', '바닐라 라떼', 4800, '부드러운 바닐라의 향과 우유의 깊은 맛이 담긴 라떼, 달콤한 여행의 시작.', 'default/latte.png', NOW()),
(5, 'coffee', '카라멜 마끼아또', 5300, '달콤한 카라멜과 진한 에스프레소가 만나는 환상적인 순간, 취향을 자극하는 끝내주는 음료.', 'default/macchiato.png', NOW());


-- 매장 메뉴 옵션
INSERT INTO menu_option_tb
    (menu_id, name, price, is_required)
VALUES
-- 아이스, 샷추가, 설탕시럽, 바닐라시럽, 헤이즐넛시럽
-- 아이스는 1이면 아이스, 0이면 hot
-- 1번 가게
-- 아메리카노
(1, '아이스', 0, 1),
(1, '핫', 0, 1),
(1, '샷추가', 500, 0),
(1, '설탕시럽', 0, 0),
(1, '바닐라시럽', 500, 0),
(1, '헤이즐넛시럽', 500, 0),
-- 카페라떼
(2, '아이스', 0, 1),
(2, '핫', 0, 1),
(2, '샷추가', 500, 0),
(2, '설탕시럽', 0, 0),
(2, '바닐라시럽', 500, 0),
(2, '헤이즐넛시럽', 500, 0),
-- 바닐라라떼
(3, '아이스', 0, 1),
(3, '핫', 0, 1),
(3, '샷추가', 500, 0),
(3, '설탕시럽', 0, 0),
(3, '바닐라시럽', 500, 0),
(3, '헤이즐넛시럽', 500, 0),
-- 카페모가
(4, '아이스', 0, 1),
(4, '핫', 0, 1),
(4, '샷추가', 500, 0),
(4, '설탕시럽', 0, 0),
(4, '바닐라시럽', 500, 0),
(4, '헤이즐넛시럽', 500, 0),
-- 카라멜마키아토
(5, '아이스', 0, 1),
(5, '핫', 0, 1),
(5, '샷추가', 500, 0),
(5, '설탕시럽', 0, 0),
(5, '바닐라시럽', 500, 0),
(5, '헤이즐넛시럽', 500, 0),
-- 2번 가게
-- 아메리카노
(6, '아이스', 0, 1),
(6, '핫', 0, 1),
(6, '샷추가', 500, 0),
(6, '설탕시럽', 0, 0),
(6, '바닐라시럽', 500, 0),
(6, '헤이즐넛시럽', 500, 0),
-- 카페라떼
(7, '아이스', 0, 1),
(7, '핫', 0, 1),
(7, '샷추가', 500, 0),
(7, '설탕시럽', 0, 0),
(7, '바닐라시럽', 500, 0),
(7, '헤이즐넛시럽', 500, 0),
-- 바닐라라떼
(8, '아이스', 0, 1),
(8, '핫', 0, 1),
(8, '샷추가', 500, 0),
(8, '설탕시럽', 0, 0),
(8, '바닐라시럽', 500, 0),
(8, '헤이즐넛시럽', 500, 0),
-- 카페모가
(9, '아이스', 0, 1),
(9, '핫', 0, 1),
(9, '샷추가', 500, 0),
(9, '설탕시럽', 0, 0),
(9, '바닐라시럽', 500, 0),
(9, '헤이즐넛시럽', 500, 0),
-- 카라멜마키아토
(10, '아이스', 0, 1),
(10, '핫', 0, 1),
(10, '샷추가', 500, 0),
(10, '설탕시럽', 0, 0),
(10, '바닐라시럽', 500, 0),
(10, '헤이즐넛시럽', 500, 0),
-- 3번 가게
-- 아메리카노
(11, '아이스', 0, 1),
(11, '핫', 0, 1),
(11, '샷추가', 500, 0),
(11, '설탕시럽', 0, 0),
(11, '바닐라시럽', 500, 0),
(11, '헤이즐넛시럽', 500, 0),
-- 카페라떼
(12, '아이스', 0, 1),
(12, '핫', 0, 1),
(12, '샷추가', 500, 0),
(12, '설탕시럽', 0, 0),
(12, '바닐라시럽', 500, 0),
(12, '헤이즐넛시럽', 500, 0),
-- 바닐라라떼
(13, '아이스', 0, 1),
(13, '핫', 0, 1),
(13, '샷추가', 500, 0),
(13, '설탕시럽', 0, 0),
(13, '바닐라시럽', 500, 0),
(13, '헤이즐넛시럽', 500, 0),
-- 카페모가
(14, '아이스', 0, 1),
(14, '핫', 0, 1),
(14, '샷추가', 500, 0),
(14, '설탕시럽', 0, 0),
(14, '바닐라시럽', 500, 0),
(14, '헤이즐넛시럽', 500, 0),
-- 카라멜마키아토
(15, '아이스', 0, 1),
(15, '핫', 0, 1),
(15, '샷추가', 500, 0),
(15, '설탕시럽', 0, 0),
(15, '바닐라시럽', 500, 0),
(15, '헤이즐넛시럽', 500, 0),
-- 4번 가게
-- 아메리카노
(16, '아이스', 0, 1),
(16, '핫', 0, 1),
(16, '샷추가', 500, 0),
(16, '설탕시럽', 0, 0),
(16, '바닐라시럽', 500, 0),
(16, '헤이즐넛시럽', 500, 0),
-- 카페라떼
(17, '아이스', 0, 1),
(17, '핫', 0, 1),
(17, '샷추가', 500, 0),
(17, '설탕시럽', 0, 0),
(17, '바닐라시럽', 500, 0),
(17, '헤이즐넛시럽', 500, 0),
-- 바닐라라떼
(18, '아이스', 0, 1),
(18, '핫', 0, 1),
(18, '샷추가', 500, 0),
(18, '설탕시럽', 0, 0),
(18, '바닐라시럽', 500, 0),
(18, '헤이즐넛시럽', 500, 0),
-- 카페모가
(19, '아이스', 0, 1),
(19, '핫', 0, 1),
(19, '샷추가', 500, 0),
(19, '설탕시럽', 0, 0),
(19, '바닐라시럽', 500, 0),
(19, '헤이즐넛시럽', 500, 0),
-- 카라멜마키아토
(20, '아이스', 0, 1),
(20, '핫', 0, 1),
(20, '샷추가', 500, 0),
(20, '설탕시럽', 0, 0),
(20, '바닐라시럽', 500, 0),
(20, '헤이즐넛시럽', 500, 0),
-- 4번 가게
-- 아메리카노
(21, '아이스', 0, 1),
(21, '핫', 0, 1),
(21, '샷추가', 500, 0),
(21, '설탕시럽', 0, 0),
(21, '바닐라시럽', 500, 0),
(21, '헤이즐넛시럽', 500, 0),
-- 카페라떼
(22, '아이스', 0, 1),
(22, '핫', 0, 1),
(22, '샷추가', 500, 0),
(22, '설탕시럽', 0, 0),
(22, '바닐라시럽', 500, 0),
(22, '헤이즐넛시럽', 500, 0),
-- 바닐라라떼
(23, '아이스', 0, 1),
(23, '핫', 0, 1),
(23, '샷추가', 500, 0),
(23, '설탕시럽', 0, 0),
(23, '바닐라시럽', 500, 0),
(23, '헤이즐넛시럽', 500, 0),
-- 카페모가
(24, '아이스', 0, 1),
(24, '핫', 0, 1),
(24, '샷추가', 500, 0),
(24, '설탕시럽', 0, 0),
(24, '바닐라시럽', 500, 0),
(24, '헤이즐넛시럽', 500, 0),
-- 카라멜마키아토
(25, '아이스', 0, 1),
(25, '핫', 0, 1),
(25, '샷추가', 500, 0),
(25, '설탕시럽', 0, 0),
(25, '바닐라시럽', 500, 0),
(25, '헤이즐넛시럽', 500, 0);

-- order_tb, order_menu_tb, order_menu_option_tb
-- 과거의 주문
-- order_id: 1
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, status, created_at)
VALUES (1, '연의양과', 5, '찬혁', '아이스 아메리카노 엄청 뜨겁게 해주세요.', ((3600) + (3600) + (4700 + 500) + (5200 + 500) + (5200)),
        'SERVED',
        DATEADD('DAY', -2, CURRENT_TIMESTAMP));
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (1, '아메리카노', 1, 3600, 3600, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),        -- id: 1
       (1, '아메리카노', 1, 3600, 3600, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),        -- id: 2
       (1, '바닐라 라떼', 1, 4700, 4700 + 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)), -- id: 3
       (1, '카페 모카', 1, 5200, 5200 + 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),  -- id: 4
       (1, '카페 모카', 1, 5200, 5200, DATEADD('DAY', -2, CURRENT_TIMESTAMP)); -- id: 5
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (1, 1, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (1, 2, '핫', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (1, 3, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (1, 3, '바닐라시럽', 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (1, 4, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (1, 4, '헤이즐넛시럽', 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (1, 5, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (1, 5, '설탕시럽', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP));

-- order_id: 2
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, status, created_at)
VALUES (1, '연의양과', 2, '정현', '아이스 아메리카노 엄청 뜨겁게 해주세요.', ((3600) + (3600) + (4700 + 500) + (5200 + 500) + (5200)),
        'SERVED',
        DATEADD('DAY', -2, CURRENT_TIMESTAMP));
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (2, '아메리카노', 1, 3600, 3600, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),        -- id: 6
       (2, '아메리카노', 1, 3600, 3600, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),        -- id: 7
       (2, '바닐라 라떼', 1, 4700, 4700 + 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)), -- id: 8
       (2, '카페 모카', 1, 5200, 5200 + 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),  -- id: 9
       (2, '카페 모카', 1, 5200, 5200, DATEADD('DAY', -2, CURRENT_TIMESTAMP)); -- id: 10
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (2, 6, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (2, 7, '핫', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (2, 8, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (2, 8, '바닐라시럽', 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (2, 9, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (2, 9, '헤이즐넛시럽', 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (2, 10, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (2, 10, '설탕시럽', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP));

-- order_id: 3
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, status, created_at)
VALUES (1, '연의양과', 3, '현정', '아이스 아메리카노 엄청 뜨겁게 해주세요.', ((3600) + (3600) + (4700 + 500) + (5200 + 500) + (5200)),
        'SERVED',
        DATEADD('DAY', -2, CURRENT_TIMESTAMP));
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (3, '아메리카노', 1, 3600, 3600, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),        -- id: 11
       (3, '아메리카노', 1, 3600, 3600, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),        -- id: 12
       (3, '바닐라 라떼', 1, 4700, 4700 + 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)), -- id: 13
       (3, '카페 모카', 1, 5200, 5200 + 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),  -- id: 14
       (3, '카페 모카', 1, 5200, 5200, DATEADD('DAY', -2, CURRENT_TIMESTAMP)); -- id: 15
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (3, 11, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (3, 12, '핫', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (3, 13, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (3, 13, '바닐라시럽', 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (3, 14, '아이스', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (3, 14, '헤이즐넛시럽', 500, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (3, 15, '핫', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
       (3, 15, '설탕시럽', 0, DATEADD('DAY', -2, CURRENT_TIMESTAMP));


-- 더미 더 만드는 중
-- order_id: 4
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, status, created_at)
VALUES (1, '연의양과', 3, '현정', '후기 남길게요. 무료로 샷 추가해주세요.', ((3500 + 500) + (3500) + (4500 + 500) + (5000 + 500)), 'SERVED',
        DATEADD('DAY', -1, CURRENT_TIMESTAMP));
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (4, '아메리카노', 1, 3500, 3500 + 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),  -- id: 16
       (4, '아메리카노', 1, 3500, 3500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),        -- id: 17
       (4, '바닐라 라떼', 1, 4500, 4500 + 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)), -- id: 18
       (4, '카페 모카', 1, 5000, 5000 + 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)); -- id: 19
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (4, 16, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (4, 16, '샷추가', 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (4, 17, '핫', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (4, 17, '설탕시럽', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (4, 18, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (4, 18, '바닐라시럽', 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (4, 19, '핫', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (4, 19, '헤이즐넛시럽', 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP));

-- order_id: 5
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, status, created_at)
VALUES (1, '연의양과', 4, '윤정', '후기 쓸게요. 에스프레소 샷 추가해주삼.', ((3500 + 500) + (3500 + 500) + (3500) + (4500 + 500) + (5000)),
        'SERVED',
        DATEADD('DAY', -1, CURRENT_TIMESTAMP));
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (5, '아메리카노', 1, 3500, 3500 + 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)), -- id: 20
       (5, '아메리카노', 1, 3500, 3500 + 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)), -- id: 21
       (5, '아메리카노', 1, 3500, 3500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),       -- id: 22
       (5, '카페 라떼', 1, 4500, 4500 + 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)), -- id: 23
       (5, '카페 모카', 1, 5000, 5000, DATEADD('DAY', -1, CURRENT_TIMESTAMP)); -- id: 24
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (5, 20, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (5, 20, '바닐라시럽', 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (5, 21, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (5, 21, '헤이즐넛시럽', 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (5, 22, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (5, 23, '핫', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (5, 23, '샷추가', 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (5, 24, '핫', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP));

-- order_id: 6
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, status, created_at)
VALUES (1, '연의양과', 5, '찬혁', '아이스 아메리카노 엄청 뜨겁게 해주세요.', ((3600) + (3600) + (4700 + 500) + (5200 + 500) + (5200)),
        'SERVED',
        DATEADD('DAY', -1, CURRENT_TIMESTAMP));
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (6, '아메리카노', 1, 3600, 3600, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),        -- id: 25
       (6, '아메리카노', 1, 3600, 3600, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),        -- id: 26
       (6, '바닐라 라떼', 1, 4700, 4700 + 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)), -- id: 27
       (6, '카페 모카', 1, 5200, 5200 + 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),  -- id: 28
       (6, '카페 모카', 1, 5200, 5200, DATEADD('DAY', -1, CURRENT_TIMESTAMP)); -- id: 29
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (6, 25, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (6, 26, '핫', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (6, 27, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (6, 27, '바닐라시럽', 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (6, 28, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (6, 28, '헤이즐넛시럽', 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (6, 29, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (6, 29, '설탕시럽', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP));

-- order_id: 7
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, status, created_at)
VALUES (1, '연의양과', 1, '성재', '아이스 아메리카노 엄청 뜨겁게 해주세요.', ((3600) + (3600) + (4700 + 500) + (5200 + 500) + (5200)),
        'SERVED',
        DATEADD('DAY', -1, CURRENT_TIMESTAMP));
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (7, '아메리카노', 1, 3600, 3600, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),        -- id: 30
       (7, '아메리카노', 1, 3600, 3600, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),        -- id: 31
       (7, '바닐라 라떼', 1, 4700, 4700 + 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)), -- id: 32
       (7, '카페 모카', 1, 5200, 5200 + 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),  -- id: 33
       (7, '카페 모카', 1, 5200, 5200, DATEADD('DAY', -1, CURRENT_TIMESTAMP)); -- id: 34
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (7, 30, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (7, 31, '핫', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (7, 32, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (7, 32, '바닐라시럽', 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (7, 33, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (7, 33, '헤이즐넛시럽', 500, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (7, 34, '아이스', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
       (7, 34, '설탕시럽', 0, DATEADD('DAY', -1, CURRENT_TIMESTAMP));

--  오늘의 주문
-- order_id: 8
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, created_at)
VALUES (1, '연의양과', 1, '성재', '바닐라라떼 얼음 많이 넣어주세요.', (3000 + (3000 + 500) + 4500), now());
INSERT INTO order_menu_tb(order_id, name, qty, price, total_price, created_at)
VALUES (8, '아메리카노', 1, 3000, 3000, now()),       -- id: 35
       (8, '아메리카노', 1, 3000, 3000 + 500, now()), -- id: 36
       (8, '바닐라라떼', 1, 4500, 4500, now()); -- id: 37
INSERT INTO order_menu_option_tb(order_id, order_menu_id, name, price, created_at)
VALUES (8, 35, '아이스', 0, now()),
       (8, 36, '핫', 0, now()),
       (8, 36, '바닐라시럽', 500, now()),
       (8, 37, '아이스', 0, now());

-- order_id: 9
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, created_at)
VALUES (1, '연의양과', 2, '정현', '카라멜 마끼아또 얼음은 적게 넣어주세요.', ((4000) + (4500 + 500) + (4500 + 500) + (5000 + 500)),
        now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (9, '카페 라떼', 1, 4000, 4000, now()),       -- id: 38
       (9, '카페 모카', 1, 4500, 4500 + 500, now()), -- id: 39
       (9, '카페 모카', 1, 4500, 4500 + 500, now()), -- id: 40
       (9, '카라멜 마끼아또', 1, 5000, 5000 + 500, now()); -- id: 41
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (9, 38, '핫', 0, now()),
       (9, 38, '설탕시럽', 0, now()),
       (9, 39, '아이스', 0, now()),
       (9, 39, '샷추가', 500, now()),
       (9, 40, '아이스', 0, now()),
       (9, 40, '바닐라시럽', 500, now()),
       (9, 41, '아이스', 0, now()),
       (9, 41, '헤이즐넛시럽', 500, now());

-- order_id: 10
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, created_at)
VALUES (2, '커피스가모 인 서면', 3, '현정', '후기 남길게요. 무료로 샷 추가해주세요.', ((3500 + 500) + (3500) + (4500 + 500) + (5000 + 500)),
        now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (10, '아메리카노', 1, 3500, 3500 + 500, now()),  -- id: 42
       (10, '아메리카노', 1, 3500, 3500, now()),        -- id: 43
       (10, '바닐라 라떼', 1, 4500, 4500 + 500, now()), -- id: 44
       (10, '카페 모카', 1, 5000, 5000 + 500, now()); -- id: 45
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (10, 42, '아이스', 0, now()),
       (10, 42, '샷추가', 500, now()),
       (10, 43, '핫', 0, now()),
       (10, 43, '설탕시럽', 0, now()),
       (10, 44, '아이스', 0, now()),
       (10, 44, '바닐라시럽', 500, now()),
       (10, 45, '핫', 0, now()),
       (10, 45, '헤이즐넛시럽', 500, now());

-- order_id: 11
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, created_at)
VALUES (3, '노이알트', 4, '윤정', '후기 쓸게요. 에스프레소 샷 추가해주삼.', ((3500 + 500) + (3500 + 500) + (3500) + (4500 + 500) + (5000)),
        now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (11, '아메리카노', 1, 3500, 3500 + 500, now()), -- id: 46
       (11, '아메리카노', 1, 3500, 3500 + 500, now()), -- id: 47
       (11, '아메리카노', 1, 3500, 3500, now()),       -- id: 48
       (11, '카페 라떼', 1, 4500, 4500 + 500, now()), -- id: 49
       (11, '카페 모카', 1, 5000, 5000, now()); -- id: 50
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (11, 46, '아이스', 0, now()),
       (11, 46, '바닐라시럽', 500, now()),
       (11, 47, '아이스', 0, now()),
       (11, 47, '헤이즐넛시럽', 500, now()),
       (11, 48, '아이스', 0, now()),
       (11, 49, '핫', 0, now()),
       (11, 49, '샷추가', 500, now()),
       (11, 50, '핫', 0, now());

-- order_id: 12
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, created_at)
VALUES (4, '블랙업커피 서면본점', 5, '찬혁', '뜨거운 아메리카노 엄청 뜨겁게 해주세요.', ((3600) + (3600) + (4700 + 500) + (5200 + 500) + (5200)),
        now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (12, '아메리카노', 1, 3600, 3600, now()),        -- id: 51
       (12, '아메리카노', 1, 3600, 3600, now()),        -- id: 52
       (12, '바닐라 라떼', 1, 4700, 4700 + 500, now()), -- id: 53
       (12, '카페 모카', 1, 5200, 5200 + 500, now()),  -- id: 54
       (12, '카페 모카', 1, 5200, 5200, now()); -- id: 55
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (12, 51, '아이스', 0, now()),
       (12, 52, '핫', 0, now()),
       (12, 53, '아이스', 0, now()),
       (12, 53, '바닐라시럽', 500, now()),
       (12, 54, '아이스', 0, now()),
       (12, 54, '헤이즐넛시럽', 500, now()),
       (12, 55, '아이스', 0, now()),
       (12, 55, '설탕시럽', 0, now());

-- order_id: 13
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, created_at)
VALUES (1, '연의양과', 4, '윤정', '뜨거운 아메리카노 반 샷만 넣어주세요.', ((3000 + 500) + (3000 + 500) + (4500 + 500)),
        now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (13, '아메리카노', 1, 3000, 3000 + 500, now()), -- id: 56
       (13, '아메리카노', 1, 3000, 3000 + 500, now()), -- id: 57
       (13, '바닐라라떼', 1, 4500, 4500 + 500, now()); -- id: 58
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (13, 56, '핫', 0, now()),
       (13, 56, '바닐라시럽', 500, now()),
       (13, 57, '아이스', 0, now()),
       (13, 57, '바닐라시럽', 500, now()),
       (13, 58, '아이스', 0, now()),
       (13, 58, '바닐라시럽', 500, now());

-- order_id: 14(request 없음)
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, total_price, created_at)
VALUES (1, '연의양과', 5, '찬혁', ((3000 + 500) + (3000) + (4500 + 500)), now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (14, '아메리카노', 1, 3000, 3000 + 500, now()), -- id: 59
       (14, '아메리카노', 1, 3000, 3000, now()),       -- id: 60
       (14, '바닐라 라떼', 1, 4500, 4500 + 500, now()); -- id: 61
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (14, 59, '아이스', 0, now()),
       (14, 59, '샷추가', 500, now()),
       (14, 60, '핫', 0, now()),
       (14, 60, '설탕시럽', 0, now()),
       (14, 61, '아이스', 0, now()),
       (14, 61, '바닐라시럽', 500, now());

-- order_id: 15
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, request, total_price, created_at)
VALUES (1, '연의양과', 3, '현정', '캐리어에 담아주세요.', ((3000 + 500) + (3000 + 500) + (3000 + 500) + (4500 + 500)),
        now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (15, '아메리카노', 1, 3000, 3000 + 500, now()), -- id: 62
       (15, '아메리카노', 1, 3000, 3000 + 500, now()), -- id: 63
       (15, '아메리카노', 1, 3000, 3000 + 500, now()), -- id: 64
       (15, '바닐라라떼', 1, 4500, 4500 + 500, now()); -- id: 65
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (15, 62, '아이스', 0, now()),
       (15, 62, '바닐라시럽', 500, now()),
       (15, 63, '아이스', 0, now()),
       (15, 63, '바닐라시럽', 500, now()),
       (15, 64, '핫', 0, now()),
       (15, 64, '바닐라시럽', 500, now()),
       (15, 65, '아이스', 0, now()),
       (15, 65, '바닐라시럽', 500, now());

-- order_id: 16(request 없음)
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, total_price, created_at)
VALUES (4, '블랙업커피 서면본점', 2, '정현', ((3000 + 500) + (3000) + (4500 + 500)), now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (16, '아메리카노', 1, 3000, 3000 + 500, now()), -- id: 66
       (16, '아메리카노', 1, 3000, 3000, now()),       -- id: 67
       (16, '바닐라 라떼', 1, 4500, 4500 + 500, now()); -- id: 68
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (16, 66, '아이스', 0, now()),
       (16, 66, '샷추가', 500, now()),
       (16, 67, '핫', 0, now()),
       (16, 67, '설탕시럽', 0, now()),
       (16, 68, '아이스', 0, now()),
       (16, 68, '바닐라시럽', 500, now());

-- order_id: 17(request 없음)
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, total_price, created_at)
VALUES (3, '노이알트', 5, '찬혁', ((3000 + 500) + (3000) + (4500 + 500)), now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (17, '아메리카노', 1, 3000, 3000 + 500, now()), -- id: 69
       (17, '아메리카노', 1, 3000, 3000, now()),       -- id: 70
       (17, '바닐라 라떼', 1, 4500, 4500 + 500, now()); -- id: 71
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (17, 69, '아이스', 0, now()),
       (17, 69, '샷추가', 500, now()),
       (17, 70, '핫', 0, now()),
       (17, 70, '설탕시럽', 0, now()),
       (17, 71, '아이스', 0, now()),
       (17, 71, '바닐라시럽', 500, now());

-- order_id: 18(request 없음)
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, total_price, created_at)
VALUES (2, '커피스가모 인 서면', 5, '찬혁', ((3500 + 500) + (3500) + (5000 + 500)), now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (18, '아메리카노', 1, 3500, 3500 + 500, now()), -- id: 72
       (18, '아메리카노', 1, 3500, 3500, now()),       -- id: 73
       (18, '카페 모카', 1, 5000, 5000 + 500, now()); -- id: 74
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (18, 72, '아이스', 0, now()),
       (18, 72, '샷추가', 500, now()),
       (18, 73, '핫', 0, now()),
       (18, 73, '설탕시럽', 0, now()),
       (18, 74, '핫', 0, now()),
       (18, 74, '바닐라시럽', 500, now());

-- order_id: 19 (request 없음)
INSERT INTO order_tb (store_id, store_name, customer_id, customer_nickname, total_price, created_at)
VALUES (5, '디저트 로지', 5, '찬혁', ((3500 + 500) + (3500) + (5000 + 500)), now());
INSERT INTO order_menu_tb (order_id, name, qty, price, total_price, created_at)
VALUES (19, '아메리카노', 1, 3500, 3500 + 500, now()), -- id: 75
       (19, '아메리카노', 1, 3500, 3500, now()),       -- id: 76
       (19, '카페 모카', 1, 5000, 5000 + 500, now()); -- id: 77
INSERT INTO order_menu_option_tb (order_id, order_menu_id, name, price, created_at)
VALUES (19, 75, '아이스', 0, now()),
       (19, 75, '샷추가', 500, now()),
       (19, 76, '핫', 0, now()),
       (19, 76, '설탕시럽', 0, now()),
       (19, 77, '핫', 0, now()),
       (19, 77, '바닐라시럽', 500, now());

-- 유저가 좋아요 한 데이터
INSERT INTO like_tb (user_id, store_id, created_at)
VALUES (1, 1, NOW()),
       (1, 2, NOW()),
       (1, 3, NOW()),
       (2, 2, NOW()),
       (2, 3, NOW()),
       (2, 4, NOW()),
       (3, 1, NOW()),
       (3, 3, NOW()),
       (3, 5, NOW()),
       (4, 2, NOW()),
       (4, 4, NOW()),
       (4, 5, NOW()),
       (5, 1, NOW()),
       (5, 3, NOW()),
       (5, 4, NOW());

INSERT INTO review_tb (user_id, store_id, content, created_at)
VALUES (1, 1, '맛있어요', NOW());
INSERT INTO review_tb (user_id, store_id, content, created_at)
VALUES (1, 2, '맛있어요', NOW());
INSERT INTO review_tb (user_id, store_id, content, created_at)
VALUES (2, 1, '맛있어요', NOW());
INSERT INTO review_tb (user_id, store_id, content, created_at)
VALUES (2, 2, '맛있어요', NOW());