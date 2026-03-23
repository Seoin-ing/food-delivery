package com.example.food_delivery;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menus")
public class MenuController {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuController(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // 메뉴 전체 조회
    @GetMapping
    public List<Menu> getMenus(@PathVariable Long restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId);
    }

    // 메뉴 등록
    @PostMapping
    public Menu createMenu(@PathVariable Long restaurantId, @RequestBody Menu menu) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    // 메뉴 삭제
    @DeleteMapping("/{menuId}")
    public String deleteMenu(@PathVariable Long menuId) {
        menuRepository.deleteById(menuId);
        return "메뉴 삭제 완료!";
    }
}