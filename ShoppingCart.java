
package shoppingcart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @Nada
 */
public class ShoppingCart {

   static Cart cart = new Cart();
   static CartStore store = new CartStore();
   
    public static void main(String[] args) {
        System.out.println("\t********************WELCOME TO THE ONLINE JAVA STORE********************\n");
        
    
    
     try{
    loadItems("C:\\Userلs\\Amcِ\\Documents\\NetBeansProjects\\ShoppingCart\\src\\shoppingcart\\Products.txt");

    }catch(FileNotFoundException e){
        System.out.println(e.getMessage());
    }finally{
         manageItems();
        System.out.println("Products loaded !\n\n");
    }
   
   
}
    
   /**
 * Name: loadItems
 * @param fileName (String)
 * @throws FileNotFoundException
 *
 * Inside the function:
 *   1. loads items from <fileName>.
 *      - while loop runs through every line in <fileName>
 *      - scan.nextLine() picks up the entire line.
 *      - splits each String using the ";" separator.
 *      - splits both fields in each String using the "=" separator.
 *      - Parse each price into a Double.
 *   2. adds all items to the store object's items field.
 */
    public static void loadItems(String fileName) throws FileNotFoundException{
        FileInputStream file = new FileInputStream(fileName);
        Scanner in = new Scanner(file);
       
        for(int i = 0 ; in.hasNextLine() ; i++){
           String line = in.nextLine();
           String[] items = line.split(";");
           for(int j = 0 ; j < items.length ; j++){
               String[] fields = items[j].split("=");
               store.setItem(i, j, new Item(fields[0],Double.parseDouble(fields[1])));
           }
         
        }  
        in.close();
    }
    
    public static void manageItems(){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("\t********************WELCOME TO THE ONLINE JAVA STORE********************\n");
            System.out.println(store);
            System.out.println("Options: \n\ta) Add to cart\n\tb) Remove from cart \n\tc) Checkout");
            String responce = in.nextLine();
            
            switch(responce){
                case "a":
                    System.out.println("\nChoose an aisle number between : 1 - 7");
                    int row = in.hasNextInt() ? in.nextInt() - 1 : 404;
                    in.nextLine();
                    System.out.println("\nChoose an aisle number between : 1 - 3");
                    int col = in.hasNextInt() ? in.nextInt() - 1 : 404;
                    in.nextLine();
                    
                    if(row == 404 || col == 404){
                        continue;
                    }else if(row < 0 || row > 6 || col < 0 || col > 2){
                        continue;
                    }
                    Item item = store.getItem(row, col);
                    if(!(cart.addItem(item))) {
                       System.out.println(item.getName()+" is already in your shopping cart!");
                    }else{
                      System.out.println(item.getName()+" was added to your shopping cart!");
                    }
                    break;
                case "b":
                    if(cart.isEmpty()){
                        continue;
                    }
                    System.out.println("Enter the item you would like to remove :");
                    String name = in.nextLine();
                    cart.removeItem(name);
                    break;
                case "c":
                    if(cart.isEmpty()){
                        continue;
                    }
                    System.out.println(cart.checkout());
                    in.close();
                    return;//close the entire function , make sure nothing comes after this
                default:
                    continue;
            }
            System.out.println("\n\nSHOPPING CART\n\n"+cart);
            System.out.println("Enter anything to continue");
            in.nextLine();
            
        }
    }
}
