import controller.StudentController;

import java.io.IOException;
import java.util.ArrayList;

import static helper.PrintHandler.printList;
import static validator.Validation.getInput;

public class Main {
    public static void main(String[] args) throws IOException {
//        int n = getInput("Nhap vao so luong Sinh vien", Integer::parseInt, input -> input >= 0);
//        ArrayList<model.Student> list = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            // (C) create
//            list.add(controller.StudentController.createSinhVienByConsole());
//        }
//        System.out.println("========================================================================================");
//        controller.StudentController.printList(list);
//        String qKey = getInput("Nhap vao id sinh vien ban muon tim kiem:", String::trim, input -> input.length() < 10);
//        // (R) Read
//        controller.StudentController.FoundSinhVien foundSinhVien = controller.StudentController.findSinhVien(list, qKey, "STUDENT_ID");
//        if (foundSinhVien != null) {
//            System.out.println(foundSinhVien.getFoundSinhVien());
//        }
//        System.out.println("========================================================================================");
//        String updateStudent = getInput("Nhap vao id sinh vien ban muon sua:", String::trim, input -> input.length() < 10);
//        controller.StudentController.FoundSinhVien foundSinhVienUpdate = controller.StudentController.findSinhVien(list, updateStudent, "STUDENT_ID");
//        if (foundSinhVienUpdate != null) {
//            // (U) update
//            controller.StudentController.updateSinhVien(list, foundSinhVien);
//        }
//        System.out.println("========================================================================================");
//        String deleteStudent = getInput("Nhap vao id sinh vien ban muon xoa:", String::trim, input -> input.length() < 10);
//        controller.StudentController.FoundSinhVien foundSinhVienDel = controller.StudentController.findSinhVien(list, deleteStudent, "STUDENT_ID");
//        if (foundSinhVienDel != null) {
//            // (D) delete
//            controller.StudentController.deleteSinhVien(foundSinhVienDel, list);
//        }
//        System.out.println("========================================================================================");
//        // % theo hoc luc
//        controller.StudentController.printPercentageStatistics(list, model.Student::getHocLuc);
//        System.out.println("========================================================================================");
//        // % theo diem
//        controller.StudentController.printPercentageStatistics(list, model.Student::getGpa);
//        System.out.println("========================================================================================");
//        controller.StudentController.printByHocLuc(list);
//        System.out.println("========================================================================================");
//        controller.StudentController.writeSinhVienListToFile(list, "sinhVien.txt");


        StudentController studentController = StudentController.getInstance();

        studentController.addStudent(1);
//        printList(studentController.getAll());
        studentController.writeStudentsListToFile();
    }
}