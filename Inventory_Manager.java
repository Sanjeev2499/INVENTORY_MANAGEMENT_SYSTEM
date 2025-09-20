package book;
import java.util.Scanner;
public class Main{
    private static final int  MAX_Items= 99;

    private static  String[] itemNames  =new String[MAX_Items];
    private static  int[] itemQuantities =new int[MAX_Items];
    private static double[] itemPrice   =new double[MAX_Items];

    private static int itemStored=0;

//Add Items
    public static void addItems(String Name,int Quantity,double Price){
        if(itemStored<=MAX_Items){
            itemNames[itemStored]=Name;
            itemQuantities[itemStored]=Quantity;
            itemPrice[itemStored]=Price;
            itemStored++;
            System.out.println("Item added successfully :"+Name);
        }else{
            System.out.println("Inventory Storage Full ! Try Later");
        }
    }

// Search Item

    public static int searchItems(String Name){
        for(int i=0;i<=itemStored;i++){
            if(itemNames[i].equalsIgnoreCase(Name)){
                return i;
            }

        }
        return -1;
    }

// Delete item
    public static void  deleteItem(String Name){
        int index=searchItems(Name);
        if(index !=-1){
            for(int i=index;i<-1;i++){
                itemNames[i]=itemNames[i+1];
                itemQuantities[i]=itemQuantities[i+1];
                itemPrice[i]=itemPrice[i+1];
            }
            itemStored--;
            System.out.println("Item Deleted:"+Name);
        }else{
            System.out.println("Ite, not found");
        }
    }

// Restock an item
public static  void restockItem(String Name,int quantity){
        int index =searchItems(Name);
        if(index !=1){
            itemQuantities[index] +=quantity;
            System.out.println("Restocked"+Name+".New Quantity"+itemQuantities[index]);
        }else{
            System.out.println("Item Not Found ");
        }
}

//Low-Stock Warning
    public static void LowStock(int lowitem){
        System.out.println("Low Stock items (below "+lowitem);
        for(int i=0;i<itemStored;i++){
                if(itemQuantities[i]<lowitem){
                    System.out.println(itemNames[i]+"Qty" +itemQuantities[i]);
                }
            }
        }
// Summary Report
    public static void summaryRepo(){
        double totalvalue=0;
        System.out.println("\n -- Inventory Report -- "+totalvalue);
        System.out.printf("%-10s %-10s %-10s\n", "Item", "Quantity", "Price");
        for (int i = 0; i < itemStored; i++) {
            System.out.printf("%-10s %-10d %-10.2f\n", itemNames[i], itemQuantities[i], itemPrice[i]);
            totalvalue += itemQuantities[i] * itemPrice[i];
        }
        System.out.println("Total Inventory Value = ₹" + totalvalue);
    }

 public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int choice;

     do {
         System.out.println("\n--- Grocery Store Inventory ---");
         System.out.println("1. Add Item");
         System.out.println("2. Delete Item");
         System.out.println("3. Restock Item");
         System.out.println("4. Search Item");
         System.out.println("5. Show Low Stock");
         System.out.println("6. Generate Report");
         System.out.println("7. Exit");
         System.out.print("Enter choice: ");
         choice = sc.nextInt();

         switch (choice) {
             case 1:
                 System.out.print("Enter item name: ");
                 String name = sc.next();
                 System.out.print("Enter quantity: ");
                 int qty = sc.nextInt();
                 System.out.print("Enter price: ");
                 double price = sc.nextDouble();
                 addItems(name, qty, price);
                 break;
             case 2:
                 System.out.print("Enter item name to delete: ");
                 deleteItem(sc.next());
                 break;
             case 3:
                 System.out.print("Enter item name to restock: ");
                 String restockName = sc.next();
                 System.out.print("Enter quantity to add: ");
                 restockItem(restockName, sc.nextInt());
                 break;
             case 4:
                 System.out.print("Enter item name to search: ");
                 String searchName = sc.next();
                 int index = searchItems(searchName);
                 if (index != -1) {
                     System.out.println("Found: " + itemNames[index] +
                             " | Qty: " + itemQuantities[index] +
                             " | Price: ₹" + itemPrice[index]);
                 } else {
                     System.out.println("Item not found.");
                 }
                 break;
             case 5:
                 System.out.print("Enter low stock threshold: ");
                 LowStock(sc.nextInt());
                 break;
             case 6:
                 summaryRepo();
                 break;
             case 7:
                 System.out.println("Exiting...");
                 break;
             default:
                 System.out.println("Invalid choice.");
         }
     } while (choice != 7);

     sc.close();
 }
}
