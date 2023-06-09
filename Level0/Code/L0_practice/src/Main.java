import java.io.IOException;
import java.util.ArrayList;

import static validator.Validation.getInput;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = getInput("Nhap vao so luong Sinh vien", Integer::parseInt, input -> input >= 0);
        ArrayList<SinhVien> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // (C) create
            list.add(SinhVienController.createSinhVienByConsole());
        }
        System.out.println("========================================================================================");
        SinhVienController.printList(list);
        String qKey = getInput("Nhap vao id sinh vien ban muon tim kiem:", String::trim, input -> input.length() < 10);
        // (R) Read
        SinhVienController.FoundSinhVien foundSinhVien = SinhVienController.findSinhVien(list, qKey, "STUDENT_ID");
        if (foundSinhVien != null) {
            System.out.println(foundSinhVien.getFoundSinhVien());
        }
        System.out.println("========================================================================================");
        String updateStudent = getInput("Nhap vao id sinh vien ban muon sua:", String::trim, input -> input.length() < 10);
        SinhVienController.FoundSinhVien foundSinhVienUpdate = SinhVienController.findSinhVien(list, updateStudent, "STUDENT_ID");
        if (foundSinhVienUpdate != null) {
            // (U) update
            SinhVienController.updateSinhVien(list, foundSinhVien);
        }
        System.out.println("========================================================================================");
        String deleteStudent = getInput("Nhap vao id sinh vien ban muon xoa:", String::trim, input -> input.length() < 10);
        SinhVienController.FoundSinhVien foundSinhVienDel = SinhVienController.findSinhVien(list, deleteStudent, "STUDENT_ID");
        if (foundSinhVienDel != null) {
            // (D) delete
            SinhVienController.deleteSinhVien(foundSinhVienDel, list);
        }
        System.out.println("========================================================================================");
        // % theo hoc luc
        SinhVienController.printPercentageStatistics(list, SinhVien::getHocLuc);
        System.out.println("========================================================================================");
        // % theo diem
        SinhVienController.printPercentageStatistics(list, SinhVien::getGpa);
        System.out.println("========================================================================================");
        SinhVienController.printByHocLuc(list);
        System.out.println("========================================================================================");
        SinhVienController.writeSinhVienListToFile(list, "sinhVien.txt");
    }
}