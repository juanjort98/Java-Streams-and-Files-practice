public class Product{
 
    int price;
    String name;


    public Product(int price, String name){
        this.price = price;
        this.name = name;

    }

    public int getPrice(){
        return price;
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }
    

    

}