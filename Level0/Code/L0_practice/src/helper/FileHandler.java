package helper;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static <T> ArrayList<T> readListFromFile(String filePath, ArrayList<T> currentList) {
        ArrayList<T> objList;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            objList = (ArrayList<T>) ois.readObject();
            if (objList.size() > 0 && objList != null) {
                currentList.addAll(objList);
            }
        } catch (Exception e) {
            if (e instanceof ClassNotFoundException) {
                System.out.println("Ban dang doc sai file hoac sai model");
            } else {
                System.out.println("Loi khi doc file " + filePath);
            }
        }
        return currentList;
    }

    public static <T> void writeListToFile(String filePath, ArrayList<T> currentList) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(currentList);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Khong the ghi file");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Khong the tao file");
        }
    }
}
