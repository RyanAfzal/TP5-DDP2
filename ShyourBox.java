import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShyourBox {
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Cart> carts = new ArrayList<Cart>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ShyourBox shyourBox = new ShyourBox();
        System.out.println("Welcome to ShyourBox! Yuk beli jangan shy shy!");

        // Subject to change: file address.
        String productAddress = "input/daftarProduk.txt";
        String customerAddress = "input/daftarCustomer.txt";

        try{
            shyourBox.addProduct(productAddress);
            shyourBox.addCustomer(customerAddress);
        }

        catch (FileNotFoundException e){
            System.out.println(e);
        }

        catch(IOException e){
            System.out.println(e);
        }

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu" +
                    "\n1. Beli Produk" +
                    "\n2. Cari Produk" +
                    "\n3. Print Struk" +
                    "\n0. Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    shyourBox.buyProduct();
                    break;
                case 2:
                    System.out.print("Cari produk dengan nama: ");
                    String name = scanner.next();
                    shyourBox.searchProduct(name);
                    break;
                case 3:
                    shyourBox.printReceipt();
                    break;
                case 0:
                    System.out.println("Yay!");
                    break;
                default:
                    System.out.println("Pilihan menu tidak valid.");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }

    /**
     * Method untuk membeli produk.
     */
    public void buyProduct() {
        // TODO: Implement this method.
    }

    /**
     * Method untuk mencari produk berdasarkan nama.
     * 
     * @param name
     * @return
     */
    public Product searchProduct(String name) {
        // TODO: Implement this method.
        return null;
    }

    /**
     * Method untuk menambahkan produk pada file txt ke dalam list produk.
     * 
     * @param fileAddress
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void addProduct(String fileAddress) throws FileNotFoundException, IOException{
        // TODO: Implement this method.
        BufferedReader reader = new BufferedReader(new FileReader(fileAddress)); 
        String line;

        while((line = reader.readLine()) != null){
            if(line.matches("^[a-zA-Z]+,\\s*[a-zA-Z\\s]+,\\s*\\d+,\\s*\\d+,\\s*[a-zA-Z]+$")){
                String[] lineSplit = line.split(",");
                if((lineSplit[0].equalsIgnoreCase("Fruit") || lineSplit[0].equalsIgnoreCase("Veggie"))){
                    if(lineSplit[0].equalsIgnoreCase("Fruit")){
                        if(lineSplit[4].trim().equalsIgnoreCase("Lokal")){
                            this.products.add(new Fruit(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , true));
                        }

                        else if(lineSplit[4].trim().equalsIgnoreCase("Impor")){
                            this.products.add(new Fruit(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , false));
                        }
                    }

                    else if(lineSplit[0].equalsIgnoreCase("Veggie")){
                        if(lineSplit[4].trim().equalsIgnoreCase("Organik")){
                            this.products.add(new Veggie(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , true));
                        }

                        else if(lineSplit[4].trim().equalsIgnoreCase("Konvensional")){
                            this.products.add(new Veggie(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , false));
                        }
                    }
                }
            }
        }
    }

    /**
     * Method untuk menambahkan customer pada file txt ke dalam list customer.
     * 
     * @param fileAddress
     */
    public void addCustomer(String fileAddress) throws FileNotFoundException, IOException {
        // TODO: Implement this method.
        BufferedReader reader = new BufferedReader(new FileReader(fileAddress)); 
        String line;

        while((line = reader.readLine()) != null){
            if(line.matches("^[a-zA-Z\\s]+,\\s*[a-zA-Z]+$")){
                String[] lineSplit = line.split(",");
                if(lineSplit[1].trim().trim().equalsIgnoreCase("Premium") || lineSplit[1].trim().trim().equalsIgnoreCase("Reguler") ){
                    if(lineSplit[1].trim().trim().equalsIgnoreCase("Premium")){
                        this.customers.add(new Customer(lineSplit[0] , true));
                    }

                    else if(lineSplit[1].trim().trim().equalsIgnoreCase("Reguler")){
                        this.customers.add(new Customer(lineSplit[0] , false));
                    }
                }
            }
        }
    }

    /**
     * Method untuk mencetak struk belanja pada file txt.
     */
    public void printReceipt() {
        // TODO: Implement this method.
    }

}