package view;

import controller.StudentController;
import model.Student;
import type.SearchBy;
import static helper.StatisticsHandler.printPercentageStatistics;
import static validator.Validator.getInput;

public class Menu {
    public static void myMenu() {
        StudentController studentController = StudentController.getInstance();
        while (true) {
            System.out.println("""
                    1.Nhap sinh vien
                    2.Tim kiem sinh vien
                    3.Cap nhat sinh vien
                    4.Xoa nhan vien
                    5.Thong ke % sinh vien theo hoc luc
                    6.Thong ke % diem trung binh
                    7.Danh sach theo hoc luc
                    0.Out
                    """);
            int choice = getInput("Nhap vao lua chon cua ban:", Integer::parseInt, input -> input >= 0 && input <= 7);
            switch (choice) {
                case 1 -> {
                    int amount = getInput("Nhap vao so luong sinh vien:", Integer::parseInt, input -> input > 0);
                    studentController.addStudent(amount);
                }
                case 2 -> studentController.findStudent("tim kiem", SearchBy.STUDENT_ID);
                case 3 -> studentController.updateStudent();
                case 4 -> studentController.deleteStudent();
                case 5 -> printPercentageStatistics(studentController.getAll(), Student::getRate);
                case 6 -> printPercentageStatistics(studentController.getAll(), Student::getGpa);
                case 7 -> studentController.getByRate();
                case 0 -> {
                    studentController.writeStudentsListToFile();
                    return;
                }
            }
        }

    }
}
