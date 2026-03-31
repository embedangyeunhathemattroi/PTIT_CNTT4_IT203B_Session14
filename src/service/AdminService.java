package service;

import model.PC;
import model.Food;
import model.Drink;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdminService {

    // ====== List tạm cho PC, Food & Drink ======
    private final List<PC> pcs = new ArrayList<>();
    private final List<Food> foods = new ArrayList<>();
    private final List<Drink> drinks = new ArrayList<>();

    // ====== ID Counter cho Food & Drink ======
    private int foodIdCounter = 1;
    private int drinkIdCounter = 1;

    // ================== PC ==================
    public List<PC> getAllPCs() {
        return pcs;
    }

    public boolean isPCNameExists(String name) {
        return pcs.stream().anyMatch(pc -> pc.getName().equalsIgnoreCase(name));
    }

    public boolean addPC(String name, int category, String config, double price, String status) {
        int id = pcs.size() + 1;
        pcs.add(new PC(id, name, category, config, price, status));
        return true;
    }

    public PC getPCById(int id) {
        return pcs.stream().filter(pc -> pc.getId() == id).findFirst().orElse(null);
    }

    public boolean updatePC(int id, String name, String config, double price) {
        PC pc = getPCById(id);
        if (pc == null)
            return false;
        pc.setName(name);
        pc.setConfig(config);
        pc.setPrice(price);
        return true;
    }

    public boolean deletePC(int id) {
        return pcs.removeIf(pc -> pc.getId() == id);
    }

    // ================== Food ==================
    public List<Food> getAllFoodsSorted() {
        foods.sort(Comparator.comparing(Food::getName)
                .thenComparing(Food::getPrice));
        return foods;
    }

    public boolean isFoodNameExists(String name) {
        return foods.stream().anyMatch(f -> f.getName().equalsIgnoreCase(name));
    }

    public boolean addFood(String name, String desc, double price, int stock) {
        if (isFoodNameExists(name))
            return false;
        name = capitalize(name);
        desc = capitalize(desc);
        Food f = new Food(foodIdCounter++, name, desc, price, stock);
        return foods.add(f);
    }

    public Food getFoodById(int id) {
        return foods.stream().filter(f -> f.getFoodId() == id).findFirst().orElse(null);
    }

    public boolean updateFood(int id, String name, String desc, double price, int stock) {
        Food f = getFoodById(id);
        if (f == null)
            return false;
        f.setName(capitalize(name));
        f.setDescription(capitalize(desc));
        f.setPrice(price);
        f.setStock(stock);
        return true;
    }

    public boolean deleteFood(int id) {
        return foods.removeIf(f -> f.getFoodId() == id);
    }

    // ================== Drink ==================
    public List<Drink> getAllDrinksSorted() {
        drinks.sort(Comparator.comparing(Drink::getName)
                .thenComparing(Drink::getPrice));
        return drinks;
    }

    public boolean isDrinkNameExists(String name) {
        return drinks.stream().anyMatch(d -> d.getName().equalsIgnoreCase(name));
    }

    public boolean addDrink(String name, String desc, double price, int stock) {
        if (isDrinkNameExists(name))
            return false;
        name = capitalize(name);
        desc = capitalize(desc);
        Drink d = new Drink(drinkIdCounter++, name, desc, price, stock);
        return drinks.add(d);
    }

    public Drink getDrinkById(int id) {
        return drinks.stream().filter(d -> d.getDrinkId() == id).findFirst().orElse(null);
    }

    public boolean updateDrink(int id, String name, String desc, double price, int stock) {
        Drink d = getDrinkById(id);
        if (d == null)
            return false;
        d.setName(capitalize(name));
        d.setDescription(capitalize(desc));
        d.setPrice(price);
        d.setStock(stock);
        return true;
    }

    public boolean deleteDrink(int id) {
        return drinks.removeIf(d -> d.getDrinkId() == id);
    }

    // ======= Utility =======
    private String capitalize(String s) {
        if (s == null || s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}