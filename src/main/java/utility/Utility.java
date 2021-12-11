package utility;

import products.Produs;
import shop.Shop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    private static Utility utility;

    public static Utility getInstance() {

        if (utility == null) {
            utility = new Utility();
        }

        return utility;

    }


    public void writeToFile(List<Produs> produsList) {
        if (produsList != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("database/fileOut.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                for (Produs produs : produsList) {
                    objectOutputStream.writeObject(produs);
                }
                fileOutputStream.close();
                objectOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Produs> readFromFile() {
        List<Produs> produsList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("database/fileOut.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                Produs produs = (Produs) objectInputStream.readObject();

                produsList.add(produs);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return produsList;

    }


}
