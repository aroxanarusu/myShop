package utility;

import products.Laptop;
import products.Phone;
import products.Produs;
import shop.Shop;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Shop shop = new Shop();
        shop.loadObjects();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Alegeti o optiune de mai jos: \n" +
                    "0. Iesire \n" +
                    "1. Adaugare produs \n" +
                    "2. Eliminare produs \n" +
                    "3. Afisare lista produse ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    shop.saveObjects();
                    break;
                case 1:
                    System.out.println("1.Produs electronic \n" +
                            "2.Produs standard");
                    int choice2 = Integer.parseInt(scanner.nextLine());
                    switch (choice2) {
                        case 1:
                            System.out.println("1.Telefon");
                            System.out.println("2.Laptop");
                            int choice3 = Integer.parseInt(scanner.nextLine());
                            do {
                                switch (choice3) {
                                    case 1:
                                        System.out.print("Introduceti un id ");
                                        long id = Long.parseLong(scanner.nextLine());
                                        System.out.print("\n" +
                                                "Introduceti pretul ");
                                        double price = Double.parseDouble(scanner.nextLine());
                                        System.out.print("\n" +
                                                "Introduceti numele produsului ");
                                        String name = scanner.nextLine();
                                        System.out.print("\n" +
                                                "Introduceti numarul de simuri ");
                                        int numbOfSims = Integer.parseInt(scanner.nextLine());
                                        System.out.print("\n" +
                                                "Are touch? true/false ");
                                        boolean hasTouch = Boolean.parseBoolean(scanner.nextLine());
                                        System.out.print("\n" +
                                                "Introduceti voltajul ");
                                        int voltaj = Integer.parseInt(scanner.nextLine());
                                        Produs phone = new Phone(id, price, name, numbOfSims, hasTouch, voltaj);
                                        shop.addProd(phone);
                                        choice3 = 0;
                                        break;
                                    case 2:
                                        System.out.print("Introduceti un id ");
                                        id = Long.parseLong(scanner.nextLine());
                                        System.out.print("\n" +
                                                "Introduceti pretul ");
                                        price = Double.parseDouble(scanner.nextLine());
                                        System.out.print("\n" +
                                                "Introduceti numele produsului ");
                                        name = scanner.nextLine();
                                        System.out.print("\n" +
                                                "Has graphic? true/false ");
                                        boolean hasGraphic = Boolean.parseBoolean(scanner.nextLine());
                                        System.out.print("\n" +
                                                "Introduceti numarul de porturi ");
                                        int numbOfPorts = Integer.parseInt(scanner.nextLine());
                                        System.out.print("\n" +
                                                "Introduceti voltajul ");
                                        voltaj = Integer.parseInt(scanner.nextLine());
                                        Produs laptop = new Laptop(id, price, name, hasGraphic, numbOfPorts, voltaj);
                                        shop.addProd(laptop);
                                        choice3 = 0;
                                        break;
                                }

                            } while (choice3 != 0);
                            break;
                        case 2:
                            System.out.println("Produs standard");
                            break;
                    }
                case 2:
                    System.out.print("Introduceti un id-ul produsului pe care doriti sa il eliminati ");
                    long id = Long.parseLong(scanner.nextLine());
                    if (shop.getProductById(id) == null) {
                        System.err.println("Produsul nu exista");
                    }
                    shop.removeProduct(id);
                    break;
                case 3:
                    shop.printProducts();
                    break;
                default:
                    System.out.println("Not a valid option");
                    break;
            }
        } while (choice != 0);

        scanner.close();

    }
}
