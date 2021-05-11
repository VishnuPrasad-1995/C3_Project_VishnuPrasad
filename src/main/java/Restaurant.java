import javax.swing.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private Integer totalvalue = 0;
    private Boolean flag = false;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        boolean value1 = (getCurrentTime()).isAfter(this.openingTime);
        boolean value2 = (getCurrentTime()).isBefore(this.closingTime);
            if ((value1 && value2) == true ){
                return true;
            }
            else return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        if (isRestaurantOpen() == true) {
            return menu;
        }
        return null;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }



    public int orderValue(List<String> selectedItems) {

        int total = 0;
        if (selectedItems.isEmpty()){
            return total;
        }
        else {
            for(String I : selectedItems){
                for (Item item : menu) {
                    if (item.getName().equals(I))
                        total = total + item.getPrice();
                }
            }
            return total;
        }

    }


    public String getName() {
        return name;
    }

}
