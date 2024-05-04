package shop.project.pathorderserver.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.errors.exception.Exception401;
import shop.project.pathorderserver._core.errors.exception.Exception403;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.MenuOption;
import shop.project.pathorderserver.menu.MenuOptionRepository;
import shop.project.pathorderserver.menu.MenuRepository;
import shop.project.pathorderserver.order.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;

    // 매장 목록보기
    public List<StoreResponse.StoreListDTO> getStoreList() {
        List<Store> stores = storeRepository.findAll();

        return stores.stream().map(StoreResponse.StoreListDTO::new).toList();
    }

    // 매장 상세보기
    public StoreResponse.StoreInfoDTO getStoreInfo(int storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));

        return new StoreResponse.StoreInfoDTO(store);
    }

    // 매장 상세보기 - 사업자 정보
    public StoreResponse.StoreBizInfoDTO getStoreBizInfo(int storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));

        return new StoreResponse.StoreBizInfoDTO(store);
    }

    // 매장 메뉴보기
    public StoreResponse.StoreMenuListDTO getStoreMenuList(int storeId) {
        Store store // 매장 정보
                = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));
        List<Menu> menus // 매장 메뉴 정보
                = menuRepository.findAllByStoreId(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 메뉴입니다."));

        return new StoreResponse.StoreMenuListDTO(store, menus);
    }

    // 매장 메뉴 옵션보기
    public StoreResponse.StoreMenuOptionDTO getStoreMenuDetail(int storeId, int menuId) {
        Store store // 매장 정보
                = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));
        Menu menu // 매장 메뉴 정보
                = menuRepository.findById(menuId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 메뉴입니다."));
        List<MenuOption> optionList // 매장 메뉴 옵션 정보
                = menuOptionRepository.findByMenuId(menuId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 옵션입니다."));

        return new StoreResponse.StoreMenuOptionDTO(store, menu, optionList);
    }

    /*------------------------------------------------------------------------------------- 매장 관리자 -----------------*/
    // 매장 관리자 등록
    public StoreResponse.JoinDTO createStore(StoreRequest.JoinDTO reqDTO) {
        Store store = new Store(reqDTO);
        storeRepository.save(store);

        return new StoreResponse.JoinDTO(store);
    }

    // 매장 관리자 로그인
    public SessionStore getStore(StoreRequest.LoginDTO reqDTO) {
        Store store = storeRepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("유저네임 또는 패스워드가 일치하지 않습니다."));

        return new SessionStore(store);
    }

    // TODO: 매장 관리자 - 매장 정보 보기
    public StoreResponse.StoreDTO getStoreDetail(int storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception403("조회할 권한이 없습니다."));

        return new StoreResponse.StoreDTO(store);
    }

    @Transactional // TODO: 매장 관리자 - 매장 정보 수정하기
    public SessionStore updateStore(int sessionStoreId, StoreRequest.UpdateDTO reqDTO) {
        Store store = storeRepository.findById(sessionStoreId)
                .orElseThrow(() -> new Exception403("수정할 권한이 없습니다."));

        store.update(reqDTO);

        SessionStore sessionStore = new SessionStore(store);

        return sessionStore;
    }

    @Transactional // 매장 관리자 - 매장 메뉴 등록하기
    public StoreResponse.CreateMenuDTO createMenu(int storeId, StoreRequest.CreateMenuDTO reqDTO) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));
        // TODO: 이미지 base64디코딩
        Menu menu = new Menu(reqDTO, store);

        return new StoreResponse.CreateMenuDTO(menuRepository.save(menu));
    }

    // 매장 관리자 - 메뉴 목록보기
    public StoreResponse.MenuListDTO getMenuList(int storeId) {
        List<Menu> menus = menuRepository.findAllByStoreId(storeId)
                .orElseThrow(() -> new Exception404("메뉴를 찾을 수 없습니다."));

        return new StoreResponse.MenuListDTO(menus);
    }

    // 매장 관리자 - 메뉴 정보 및 옵션 보기
    public StoreResponse.MenuDetailDTO getMenuDetail(int menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 메뉴입니다."));
        List<MenuOption> menuOptions = menuOptionRepository.findByMenuId(menuId)
                .orElse(new ArrayList<>());

        return new StoreResponse.MenuDetailDTO(menu, menuOptions);
    }

    @Transactional // TODO: 매장 관리자 - 메뉴 수정하기
    public StoreResponse.UpdateMenuDTO updateMenu(int menuId, StoreRequest.UpdateMenuDTO reqDTO) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 메뉴입니다."));

        menu.setPrice(reqDTO.getPrice());
        menu.setCategory(reqDTO.getCategory());
        menu.setName(reqDTO.getName());
        menu.setImgFilename(reqDTO.getImgFilename());
        menu.setDescription(reqDTO.getDescription());

        StoreResponse.UpdateMenuDTO respDTO = new StoreResponse.UpdateMenuDTO(menu);
        return respDTO;
    }

    @Transactional // 매장 관리자 - 메뉴 삭제하기
    public void deleteMenu(int menuId) {
        menuOptionRepository.deleteByMenuId(menuId);
        menuRepository.deleteById(menuId);
    }

    @Transactional // 매장 관리자 - 메뉴 옵션 등록하기
    public StoreResponse.CreateMenuOptionDTO createMenuOption(int menuId, StoreRequest.CreateMenuOptionDTO reqDTO) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 메뉴입니다."));
        MenuOption menuOption = new MenuOption(reqDTO, menu);
        menuOptionRepository.save(menuOption);

        return new StoreResponse.CreateMenuOptionDTO(menuId, menuOption);
    }

    /* 메뉴 옵션은 메뉴 상세보기에서 처리?
    // TODO: 매장 관리자 - 메뉴 옵션 목록보기
    public void getMenuOptionList(int menuId) {
    }
    */
    @Transactional // 매장 관리자 - 메뉴 옵션 수정하기
    public StoreResponse.UpdateMenuOptionDTO updateMenuOption(int menuOptionId, StoreRequest.UpdateMenuOptionDTO reqDTO) {
        MenuOption menuOption = menuOptionRepository.findById(menuOptionId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 메뉴 옵션입니다."));
        menuOption.update(reqDTO);

        return new StoreResponse.UpdateMenuOptionDTO(menuOption);
    }

    @Transactional // TODO: 매장 관리자 - 메뉴 옵션 삭제하기
    public void deleteMenuOption(int menuOptionId) {
        menuOptionRepository.deleteById(menuOptionId);
    }

    // 매장 관리자 - 주문내역 목록보기
    public StoreResponse.OrderListDTO getOrderList(int storeId) {
        List<Order> orderList = orderRepository.findByStoreId(storeId)
                .orElseThrow(() -> new Exception404("주문 내역이 없습니다."));

        return new StoreResponse.OrderListDTO(orderList);
    }

    @Transactional(readOnly = true)// 매장 관리자 - 주문내역 상세보기
    public StoreResponse.OrderDetailDTO getOrderDetail(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));

        List<OrderMenu> orderMenuList = orderMenuRepository.findAllByOrderId(orderId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 메뉴입니다."));

        return new StoreResponse.OrderDetailDTO(order, orderMenuList);
    }

    @Transactional // TODO: 매장 관리자 - 주문 업데이트
    public StoreResponse.UpdateOrderDTO updateOrder(int orderId, StoreRequest.UpdateOrderDTO reqDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new Exception404("주문이 없습니다."));

        order.setStatus(reqDTO.getStatus());

        StoreResponse.UpdateOrderDTO respDTO = new StoreResponse.UpdateOrderDTO(order);
        return respDTO;
    }

    public HashMap<String, Object> getCurrentOrders(int storeId) {
        // 전체 오더 리스트
        List<Order> orderList = orderRepository.findOrdersByStoreId(storeId)
                .orElseThrow();

        // 오더 리스트
        List<StoreResponse.CurrentOrderDTO> currentOrderDTOList = new ArrayList<>();
        orderList.stream().map(order -> {
            return currentOrderDTOList.add(StoreResponse.CurrentOrderDTO.builder()
                    .order(order)
                    .menuList(order.getOrderMenus())
                    .build());
        }).toList();
        System.out.println(currentOrderDTOList.getFirst().getStatus());

        List<StoreResponse.CurrentOrderDTO> pendingOrderList = new ArrayList<>();
        List<StoreResponse.CurrentOrderDTO> cookingOrderList = new ArrayList<>();
//        pendingOrderList = currentOrderDTOList.stream()
//                .filter(order -> order.getStatus().equals("접수대기"))
//                .toList();
//        cookingOrderList = currentOrderDTOList.stream()
//                .filter(order -> order.getStatus().equals("조리중"))
//                .toList();
        for (int i = 0; i < currentOrderDTOList.size(); i++) {
            if (currentOrderDTOList.get(i).getStatus() == OrderStatus.접수대기){
                pendingOrderList.add(currentOrderDTOList.get(i));
            }
            if (currentOrderDTOList.get(i).getStatus() == OrderStatus.조리중){
                cookingOrderList.add(currentOrderDTOList.get(i));
            }
        }
        System.out.println(pendingOrderList.getFirst().getStatus());
        System.out.println(cookingOrderList);

        HashMap<String, Object> orderFilteredByStatus = new HashMap<>();
        orderFilteredByStatus.put("pendingOrderList", pendingOrderList);
        orderFilteredByStatus.put("cookingOrderList", cookingOrderList);
        return orderFilteredByStatus;
    }
}
