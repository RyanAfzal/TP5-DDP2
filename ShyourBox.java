import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Order;

public class ShyourBox {
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Cart> carts = new ArrayList<Cart>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    public static void main(String[] args) throws FileNotFoundException, IOException, MalformedURLException{
        ShyourBox shyourBox = new ShyourBox();
        System.out.println("Welcome to ShyourBox! Yuk beli jangan shy shy!");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int pilihanSumber = 0;

        //Pilih sumber dan cek input sampai sesuai
        boolean inputBenar = false;
        while (!inputBenar) {
            System.out.println("Sumber: " + 
                "\n1. File txt pada folder input"+
                "\n2. dari website");
            System.out.print("Pilih sumber(dalam angka) : ");

            try {
                pilihanSumber = scanner.nextInt();
                System.out.println();

                if (pilihanSumber == 1 || pilihanSumber == 2) {
                    inputBenar = true;
                } else {
                    System.out.println("Input tidak valid");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid");
                System.out.println();
                scanner.nextLine(); // Handle enter
            }
        }


        // Subject to change: file address.
        String productAddress = "input/daftarProduk.txt";
        String customerAddress = "input/daftarCustomer.txt";
        String sumberWebsite = "https://fikriadidharma.github.io/shyourbox1/";

        //Program utama
        try{
            if (pilihanSumber == 1){
                shyourBox.addCustomer(customerAddress);
                shyourBox.addProduct(productAddress);
            }

            else if (pilihanSumber == 2){
                shyourBox.addCustomerAndProduct(sumberWebsite);
            }
        }

        //Jika file tidak ditemukan
        catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan");
        }

        //Jika sumber dari website tetapi link tidak valid
        catch (java.net.MalformedURLException ex) {
            System.out.println("URL Tidak Valid");
        }

        //Terjadi error pada IO
        catch(IOException e){
            System.out.println("IO Errors");
        }


        //Pilihan fitur yang dapat dipilih
        int choice = -1;
        System.out.println();
        do {
            try{
                System.out.println("Menu" +
                        "\n1. Beli Produk" +
                        "\n2. Cari Produk" +
                        "\n3. Print Struk" +
                        "\n0. Keluar");
                System.out.print("Pilih menu: ");
                choice = scanner.nextInt();
                scanner.nextLine(); //Handle enter

                switch (choice) {
                    case 1:
                        shyourBox.buyProduct();
                        break;
                    case 2:
                        System.out.print("Cari produk dengan nama: ");
                        String name = scanner.nextLine();
                        if(shyourBox.searchProduct(name) != null){
                            System.out.println("Produk Ditemukan!");

                            if(shyourBox.searchProduct(name) instanceof Fruit){
                                Fruit buah = (Fruit) shyourBox.searchProduct(name);

                                if(buah.isLocal()){
                                    System.out.println("[Buah Lokal]");
                                }

                                else{
                                    System.out.println("[Buah non-Lokal]");
                                }

                                
                            }

                            else if(shyourBox.searchProduct(name) instanceof Veggie){
                                Veggie sayuran = (Veggie) shyourBox.searchProduct(name);

                                if(sayuran.isOrganic()){
                                    System.out.println("[Sayur Organik]");
                                }

                                else{
                                    System.out.println("[Sayur non-Organik]");
                                }

                            }

                            System.out.println("Nama Produk: " + shyourBox.searchProduct(name).getProductName());
                            System.out.println("Harga: " + shyourBox.searchProduct(name).getPrice());
                            System.out.println("Stok: " + shyourBox.searchProduct(name).getStock());
                            System.out.println();
                        }

                        else{
                            System.out.println("Produk tidak ditemukan!");
                            System.out.println();
                        }
                        break;
                    case 3:
                        shyourBox.printReceipt();
                        System.out.println("Struk telah dicetak");
                        System.out.println();
                        break;
                    case 0:
                        System.out.println();
                        System.out.println("Yay!");
                        break;
                    default:
                        System.out.println("Pilihan menu tidak valid.");
                        System.out.println();
                        break;
                }
            }

            catch (Exception e){
                System.out.println("Input tidak valid");
                scanner.nextLine(); //Handle enter
                System.out.println();
            }
        } while (choice != 0);
        scanner.close();
    }

    /**
     * Method untuk membeli produk.
     */
    public void buyProduct() {
        // TODO: Implement this method.
        Scanner input = new Scanner(System.in);
        boolean adaCustomer = false;
        int indexCustomer = 0;
        System.out.print("Masukkan nama customer: ");
        String namaCustomer = input.nextLine();

        for (int i = 0 ; i < this.customers.size(); i++){
            if (this.customers.get(i).getName().equalsIgnoreCase(namaCustomer)){
                adaCustomer = true;
                indexCustomer = i;
                break;
            }
        }

        if(adaCustomer){
            System.out.println("====MASUKKAN ITEM KE KERANJANG====");
            System.out.println();

            boolean selesaiBelanja = false;

            while(!selesaiBelanja){
                System.out.print("Masukkan nama produk: ");
                String produkYangDibeli = input.nextLine();

                if(produkYangDibeli.equalsIgnoreCase("X")){
                    selesaiBelanja = true;
                    System.out.println("Terima kasih sudah berbelanja, " + namaCustomer);
                    System.out.println();
                }

                else{
                    Product produkDibeli = searchProduct(produkYangDibeli);

                    if (produkDibeli != null){
                        System.out.print("Masukkan Jumlah (kg): ");
                        int jumlahDibeli = input.nextInt();
                        input.nextLine();

                        if(produkDibeli.getStock() >= jumlahDibeli){
                            OrderItem produkDanJumlahDibeli = new OrderItem(produkDibeli, jumlahDibeli);
                            this.customers.get(indexCustomer).getCart().addOrderItem(produkDanJumlahDibeli);
                            produkDibeli.setStock(jumlahDibeli);
                            System.out.println("Produk Berhasil ditambahkan!");
                            System.out.println();
                        }
    
                        else if(produkDibeli.getStock() < jumlahDibeli){
                            System.out.println("Mohon maaf, stok tidak mencukupi!");
                            System.out.println();
                        }
                    }


                    else{
                        System.out.println("Mohon maaf, produk tidak tersedia!");
                        System.out.println();
                    }
                }
            }
        }

        else{
            System.out.println("Mohon maaf, customer atas nama " + namaCustomer + " tidak terdaftar!");
            System.out.println();
        }
        
    }

    /**
     * Method untuk mencari produk berdasarkan nama.
     * 
     * @param name nama produk yang ingin dicari
     * @return produk yang dicari
     */
    public Product searchProduct(String name) {
        // TODO: Implement this method.
        for(Product product : products){
            if(product.getProductName().equalsIgnoreCase(name)){
                return product;
            }
        }

        return null;
    }

    /**
     * Method untuk menambahkan produk pada file txt ke dalam list produk.
     * 
     * @param fileAddress address dari file yang berisi daftar produk tersedia
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void addProduct(String fileAddress) throws FileNotFoundException, IOException{
        // TODO: Implement this method.
        int jmlBerhasil = 0;
        int jmlGagal = 0;
        BufferedReader reader = new BufferedReader(new FileReader(fileAddress)); 
        String line;

        while((line = reader.readLine()) != null){
            try{
                if(line.matches("^[a-zA-Z]+,\\s*[a-zA-Z\\s]+,\\s*\\d+,\\s*\\d+,\\s*[a-zA-Z]+$")){
                    String[] lineSplit = line.split(",");
                    if((lineSplit[0].equalsIgnoreCase("Fruit") || lineSplit[0].equalsIgnoreCase("Veggie"))){
                        if(lineSplit[0].equalsIgnoreCase("Fruit")){
                            if(lineSplit[4].trim().equalsIgnoreCase("Lokal")){
                                this.products.add(new Fruit(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , true));
                                jmlBerhasil += 1;
                            }

                            else if(lineSplit[4].trim().equalsIgnoreCase("Impor")){
                                this.products.add(new Fruit(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , false));
                                jmlBerhasil += 1;
                            }

                            else{
                                jmlGagal += 1;
                                throw new FormatSalahException();
                            }
                        }

                        else if(lineSplit[0].equalsIgnoreCase("Veggie")){
                            if(lineSplit[4].trim().equalsIgnoreCase("Organik")){
                                this.products.add(new Veggie(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , true));
                                jmlBerhasil += 1;
                            }

                            else if(lineSplit[4].trim().equalsIgnoreCase("Konvensional")){
                                this.products.add(new Veggie(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , false));
                                jmlBerhasil += 1;
                            }

                            else{
                                jmlGagal += 1;
                                throw new FormatSalahException();
                            }
                        }
                    }

                    else{
                        jmlGagal += 1;
                        throw new FormatSalahException();
                    }
                }

                else{
                    jmlGagal += 1;
                    throw new FormatSalahException();
                }
            }

            catch (FormatSalahException e){
                System.out.println("Format error pada: " + line);
            }
        }
        reader.close();
        System.out.println("Berhasil menambahkan "+ jmlBerhasil + " Produk");
        System.out.println("Gagal menambahkan "+ jmlGagal + " Produk");
    }

    /**
     * Method untuk menambahkan customer pada file txt ke dalam list customer.
     * 
     * @param fileAddress address file yang berisi daftar customer yang ada
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void addCustomer(String fileAddress) throws FileNotFoundException, IOException{
        // TODO: Implement this method.
        int jmlBerhasil = 0;
        int jmlGagal = 0;
        BufferedReader reader = new BufferedReader(new FileReader(fileAddress)); 
        String line;

        while((line = reader.readLine()) != null){
            try{
                if(line.matches("^[a-zA-Z\\s]+,\\s*[a-zA-Z]+$")){
                    String[] lineSplit = line.split(",");
                    if(lineSplit[1].trim().equalsIgnoreCase("Premium") || lineSplit[1].trim().equalsIgnoreCase("Reguler") ){
                        if(lineSplit[1].trim().equalsIgnoreCase("Premium")){
                            Customer currentCustomer = new Customer(lineSplit[0] , true);
                            this.customers.add(currentCustomer);
                            this.carts.add(currentCustomer.getCart());
                            jmlBerhasil += 1;
                        }

                        else if(lineSplit[1].trim().equalsIgnoreCase("Reguler")){
                            Customer currentCustomer = new Customer(lineSplit[0] , false);
                            this.customers.add(currentCustomer);
                            this.carts.add(currentCustomer.getCart());
                            jmlBerhasil += 1;
                        }
                    }

                    else{
                        jmlGagal += 1;
                        throw new FormatSalahException();
                    }
                }

                else{
                    jmlGagal += 1;
                    throw new FormatSalahException();
                }
            }

            catch (FormatSalahException e){
                System.out.println("Format error pada: " + line);
            }
        }
        reader.close();
        System.out.println("Berhasil menambahkan " + jmlBerhasil + " Customer");
        System.out.println("Gagal menambahkan " + jmlGagal + " Customer");
    }

    /**
     * Untuk menambahkan customer dan produk dari file pada link website
     * @param fileAddress address file yang berisi daftar customer dan produk yang ada
     * @throws MalformedURLException
     * @throws IOException
     */
    public void addCustomerAndProduct(String fileAddress) throws MalformedURLException, IOException{
        int jmlBerhasilCustomer = 0;
        int jmlGagalCustomer = 0;
        int jmlBerhasilProduct = 0;
        int jmlGagalProduct = 0;
        String line = "";

        java.net.URL url = new java.net.URL(fileAddress); 
        Scanner input = new Scanner(url.openStream());
        while (input.hasNext()) {
            try{
                line = input.nextLine();

                //Untuk add Customer
                if(line.toLowerCase().trim().endsWith("premium") || line.toLowerCase().trim().endsWith("reguler")){
                    if(line.matches("^[a-zA-Z\\s]+,\\s*[a-zA-Z]+$")){
                        String[] lineSplit = line.split(",");
                        if(lineSplit[1].trim().equalsIgnoreCase("Premium") || lineSplit[1].trim().equalsIgnoreCase("Reguler") ){
                            if(lineSplit[1].trim().equalsIgnoreCase("Premium")){
                                Customer currentCustomer = new Customer(lineSplit[0] , true);
                                this.customers.add(currentCustomer);
                                this.carts.add(currentCustomer.getCart());
                                jmlBerhasilCustomer += 1;
                            }
        
                            else if(lineSplit[1].trim().equalsIgnoreCase("Reguler")){
                                Customer currentCustomer = new Customer(lineSplit[0] , false);
                                this.customers.add(currentCustomer);
                                this.carts.add(currentCustomer.getCart());
                                jmlBerhasilCustomer += 1;
                            }
                        }
        
                        else{
                            jmlGagalCustomer += 1;
                            throw new FormatSalahException();
                        }
                    }
        
                    else{
                        jmlGagalCustomer += 1;
                        throw new FormatSalahException();
                    }
                }

                //Untuk add Product
                if(line.startsWith("Fruit,")||line.startsWith("Veggie,")){
                    if(line.matches("^[a-zA-Z]+,\\s*[a-zA-Z\\s]+,\\s*\\d+,\\s*\\d+,\\s*[a-zA-Z]+$")){
                        String[] lineSplit = line.split(",");
                        if((lineSplit[0].equalsIgnoreCase("Fruit") || lineSplit[0].equalsIgnoreCase("Veggie"))){
                            if(lineSplit[0].equalsIgnoreCase("Fruit")){
                                if(lineSplit[4].trim().equalsIgnoreCase("Lokal")){
                                    this.products.add(new Fruit(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , true));
                                    jmlBerhasilProduct += 1;
                                }
        
                                else if(lineSplit[4].trim().equalsIgnoreCase("Impor")){
                                    this.products.add(new Fruit(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , false));
                                    jmlBerhasilProduct += 1;
                                }
        
                                else{
                                    jmlGagalProduct += 1;
                                    throw new FormatSalahException();
                                }
                            }
        
                            else if(lineSplit[0].equalsIgnoreCase("Veggie")){
                                if(lineSplit[4].trim().equalsIgnoreCase("Organik")){
                                    this.products.add(new Veggie(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , true));
                                    jmlBerhasilProduct += 1;
                                }
        
                                else if(lineSplit[4].trim().equalsIgnoreCase("Konvensional")){
                                    this.products.add(new Veggie(lineSplit[1].trim() , Integer.parseInt(lineSplit[2].trim()) , Integer.parseInt(lineSplit[3].trim()) , false));
                                    jmlBerhasilProduct += 1;
                                }
        
                                else{
                                    jmlGagalProduct += 1;
                                    throw new FormatSalahException();
                                }
                            }
                        }
        
                        else{
                            jmlGagalProduct += 1;
                            throw new FormatSalahException();
                        }
                    }
        
                    else{
                        jmlGagalProduct += 1;
                        throw new FormatSalahException();
                    }
                }
            }

            catch (FormatSalahException e){
                System.out.println("Format error pada: " + line);
            }
        }
        System.out.println("Berhasil menambahkan " + jmlBerhasilCustomer + " Customer");
        System.out.println("Gagal menambahkan " + jmlGagalCustomer + " Customer");
        System.out.println("Berhasil menambahkan "+ jmlBerhasilProduct + " Produk");
        System.out.println("Gagal menambahkan "+ jmlGagalProduct + " Produk");
    }

    /**
     * Method untuk mencetak struk belanja pada file txt.
     */
    public void printReceipt() throws IOException {
        // TODO: Implement this method.
        PrintWriter writer = new PrintWriter(new FileWriter("Struk.txt"));
        writer.println("Berikut adalah rekap perbelanjaan hari ini:");
        writer.println();

        for(Customer customer : customers){
            if(customer.getCart().getOrderItemList().isEmpty() == false){
                writer.println("=============================");
                writer.println("Nama Customer: " + customer.getName());
                writer.println("Daftar Belanja:");
                for(OrderItem item : customer.getCart().getOrderItemList()){
                    writer.println(item.getProduct().getProductName() + " " + item.getQuantity() + "kg " + item.getFinalPrice());
                }
                writer.println();
                writer.println("Total Perbelanjaan: " + customer.getCart().getTotalPrice());
            }
        }
        writer.close();
    }

}