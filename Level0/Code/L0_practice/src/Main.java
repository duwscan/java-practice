import java.io.IOException;
import java.util.ArrayList;

import static validator.Validation.getInput;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(":");
        int n = getInput("Nhap vao so luong Sinh vien", Integer::parseInt, input -> input >= 0);
        ArrayList<SinhVien> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(SinhVienController.createSinhVienByConsole());
        }
        SinhVienController.printList(list);
        String qKey = getInput("Nhap vao id sinh vien ban muon tim kiem:", String::trim, input -> input.length() < 10);
        SinhVienController.FoundSinhVien foundSinhVien = SinhVienController.findSinhVien(list, qKey, "STUDENT_ID");
        if (foundSinhVien != null) {
            System.out.println(foundSinhVien.getFoundSinhVien());
            SinhVienController.updateSinhVien(list, foundSinhVien);
        }
        SinhVienController.printByHocLuc(list);
        SinhVienController.printPercentageSinhVienHocLuc(list);
        SinhVienController.printOverallPercentage(list);
        SinhVienController.writeSinhVienListToFile(list, "sinhVien.txt");
    }
}