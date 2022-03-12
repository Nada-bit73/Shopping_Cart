
package shoppingcart;

import java.util.ArrayList;

/**
 *
 * @Nada
 */
public class Cart {
    ArrayList<Item> items;
    
    
   public Cart(){
       this.items = new ArrayList<Item>();
   }
   
   public Item getItem(int index){
       return new Item(items.get(index));
   }
   
   public void setItem(int index,Item item){
       items.set(index,new Item(item));
   }
   
   /**
  * Name: add
  * @param item
  * @return boolean
  *
  * Inside the function:
  *   1. Adds an item to the cart if it wasn't already added.
  */
   
   public boolean addItem(Item item){
      
   //contains will use the equals() in items class!!
       if(this.items.contains(item)){
            return false ;
       }else{  
            items.add(new Item(item));
               return true ;
           }
                
           
   }
   
 /**
 * Name: remove
 * @param name
 *
 * Inside the function:
 *   1. Removes the item that matches the name passed in.
 */
   
   
   public void removeItem(String name){
       boolean isFound = false;
       if(items.isEmpty()){
           throw new IllegalStateException("cannot remove item from empty cart !");
       }
        for(int i = 0 ; i < items.size() ; i++){
            if(items.get(i).getName().equals(name)){
                this.items.remove(i);
                isFound = true;
            }
           
                
       }
        
        if(isFound == false){
              throw new IllegalStateException("The item you are trying to remove does not exist..");
            }
 }
       
/**
 *  Name: checkout
 *  @return (String)
 *
 *  Inside the function:
 *   1. Calculates the subtotal (price before tax).
 *   2. Calculates the tax (assume tax is 13%).
 *   3. Calculates total: subtotal + tax
 *   4. Returns a String that resembles a receipt. See below.
 */
   
   public String checkout(){
       if(items.isEmpty()){
           throw new IllegalStateException("your cart is Empty !");
       }
       double subtotal = 0 ;
       double total = 0;
       double tax = 1.13;
       for(int i = 0 ; i < items.size() ; i++){
         subtotal += (items.get(i).getPrice());
   }
       
     total = subtotal + tax;
     
        return  "\tRECEIPT\n\n" +
        "\tSubtotal: $" + subtotal + "\n" +
        "\tTax: $" + tax + "\n" +
        "\tTotal: $" + total + "\n";
   
}
   
   public boolean isEmpty(){
    return this.items.isEmpty();
}
   
  /*
   Set a String variable temp that equals "".

Loop through every item in the items field.

During each run, add the toString of each item.

During each run, add one line \n to temp.

return temp;
   */
   
   public String toString(){
       String temp = "";
        for(int i = 0 ; i < items.size() ; i++){
           
            temp += items.get(i).getName();
            temp += " : ";
             temp +="$ ";
            temp += items.get(i).getPrice();
            
            temp +="\n\n";
            
        }
        
        
       return temp;
   }
}
