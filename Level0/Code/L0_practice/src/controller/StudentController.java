package controller;

import helper.FileHandler;
import model.Person;
import model.Student;
import validator.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static helper.FileHandler.readListFromFile;
import static helper.PrintHandler.printByCondition;
import static validator.Validation.*;

public class StudentController {
    private static StudentController studentController;
    private ArrayList<Student> list;
    private final String dataSource = "students.dat";

    private StudentController() {
        list = readListFromFile(this.dataSource, new ArrayList<>());
        if (list != null && list.size() > 0) {
            Person.idIncrement = list.get(list.size() - 1).getId() + 1;
        }
    }

    public static StudentController getInstance() {
        if (studentController == null) {
            studentController = new StudentController();
        }
        return studentController;
    }

    public List<Student> getAll() {
        return list;
    }

    private static class FoundStudent {
        Student foundStudent;
        int index;

        FoundStudent(Student foundStudent, int index) {
            this.foundStudent = foundStudent;
            this.index = index;
        }

        public Student getFoundStudent() {
            return foundStudent;
        }


        public int getIndex() {
            return index;
        }
    }

    public Student createAStudentByConsole() {
        String name = getInput("Nhap vao ten sinh vien(Ten <" + Validation.MAX_NAME_LENGTH + "):", String::trim, Validation::nameRule);
        LocalDate dob = getInput("Nhap vao ngay thang nam sinh(YYYY-MM-DD)(Y >" + Validation.MIN_START_YEAR + "):", input -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date;
            try {
                date = LocalDate.parse(input, formatter);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return date;
        }, Validation::dateOfBirthRule);
        String address = getInput("Nhap vao dia chi (Chuoi <" + Validation.MAX_ADDRESS_LENGTH + "):", String::trim, Validation::addressRule);
        Double height = getInput("Nhap vao chieu cao don vi tu cm " + Validation.MIN_HEIGHT + "-" + Validation.MAX_HEIGHT + " :", Double::parseDouble, Validation::heightRule);
        Double weight = getInput("Nhap vao can nang don vi tu kg  " + Validation.MIN_WEIGHT + "-" + Validation.MAX_WEIGHT + " :", Double::parseDouble, Validation::weightRule);
        String school = getInput("Nhap vao truong hoc  (Chuoi <" + Validation.MAX_SCHOOL_LENGTH + "):", String::trim, Validation::schoolNameRule);
        int enrollYear = getInput("Nhap vao can nam bat dau hoc Y > " + Validation.MIN_START_YEAR + " :", Integer::parseInt, Validation::enrollYearRule);
        double gpa = getInput("Nhap vao diem trung binh tich luy " + MIN_GPA + "-" + MAX_GPA + ":", Double::parseDouble, Validation::gpaRule);
        Student aStudent = new Student(name, dob, address, height, weight, school, enrollYear, gpa);
        System.out.println("Sinh vien vua nhap:");
        System.out.println(aStudent);
        return aStudent;
    }

    public void addStudent(Integer amount) {
        int defaultAmount = 1;
        if (amount == null || amount <= 0) {
            amount = defaultAmount;
        }
        for (int i = 0; i < amount; i++) {
            list.add(createAStudentByConsole());
        }
    }

    public <T> FoundStudent findStudent(T key, String action) {
        for (int i = 0; i < this.list.size(); i++) {
            if (studentsFilter(key, action, list.get(i))) {
                System.out.println(list.get(i));
                return new FoundStudent(list.get(i), i);
            }
        }
        System.out.println("Khong tim thay du lieu phu hop");
        return null;
    }

    private static <T> boolean studentsFilter(T key, String action, Student student) {
        switch (action) {
            case "STUDENT_ID" -> {
                return key.equals(student.getStudentId());
            }
            case "STUDENT_NAME" -> {
                return key.equals(student.getName());
            }
        }
        return false;
    }

    public void updateStudent(FoundStudent updateStudent) {
        if (updateStudent == null) {
            System.out.println("Khong the cap nhat neu khong ton tai");
            return;
        }
        System.out.println("Ban muon sua thuoc tinh nao cua Sinh Vien:");

        for (int i = 0; i < UPDATABLE_PROPERTIES.length; i++) {
            System.out.println((i + 1) + " Sua " + UPDATABLE_PROPERTIES[i]);
        }
        int choice = getInput("Nhap vao lua chon cua ban:", Integer::parseInt, Validation::updateStudentRule);
        int propertyIndex = choice - 1;
        processUpdate(updateStudent, propertyIndex);
        System.out.println(list.get(updateStudent.index));
    }

    private void processUpdate(FoundStudent updateStudent, int propertyIndex) {
        Student updated = updateStudent.getFoundStudent();
        System.out.println("Nhap gia tri ban muon sua cho" + UPDATABLE_PROPERTIES[propertyIndex] + ":");
        switch (UPDATABLE_PROPERTIES[propertyIndex]) {
            case "TEN" ->
                    updated.setName(getInput("Nhap vao ten sinh vien(Ten <" + Validation.MAX_NAME_LENGTH + "):", String::trim, Validation::nameRule));
            case "NGAY_SINH" ->
                    updated.setDateOfBirth(getInput("Nhap vao ngay thang nam sinh(YYYY-MM-DD)(Y >" + Validation.MIN_START_YEAR + "):", input -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate date;
                        try {
                            date = LocalDate.parse(input, formatter);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        return date;
                    }, Validation::dateOfBirthRule));
            case "DIA_CHI" ->
                    updated.setAddress(getInput("Nhap vao dia chi (Chuoi <" + Validation.MAX_ADDRESS_LENGTH + "):", String::trim, Validation::addressRule));
            case "CHIEU_CAO" ->
                    updated.setHeight(getInput("Nhap vao chieu cao don vi tu cm " + Validation.MIN_HEIGHT + "-" + Validation.MAX_HEIGHT + " :", Double::parseDouble, Validation::heightRule));
            case "CAN_NANG" ->
                    updated.setWeight(getInput("Nhap vao can nang don vi tu kg  " + Validation.MIN_WEIGHT + "-" + Validation.MAX_WEIGHT + " :", Double::parseDouble, Validation::heightRule));
            case "TEN_TRUONG" ->
                    updated.setUniversityName(getInput("Nhap vao truong hoc  (Chuoi <" + Validation.MAX_SCHOOL_LENGTH + "):", String::trim, Validation::schoolNameRule));
            case "NAM_HOC" ->
                    updated.setEnrollYear(getInput("Nhap vao can nam bat dau hoc Y > " + Validation.MIN_START_YEAR + " :", Integer::parseInt, Validation::enrollYearRule));
            case "DIEM" -> {
                updated.setGpa(getInput("Nhap vao diem trung binh tich luy " + MIN_GPA + "-" + MAX_GPA + ":", Double::parseDouble, Validation::gpaRule));
                updated.setRate();
            }
        }
        this.list.set(updateStudent.index, updated);
    }

    public void getByRate() {
        System.out.println("Ban xem muon xem danh sach hoc sinh theo:");
        for (int i = 0; i < HOC_LUC.length; i++) {
            System.out.println((i + 1) + "." + HOC_LUC[i]);
        }
        int choice = getInput("Nhap vao lua chon cua ban:", Integer::parseInt, Validation::rateRule);
        printByCondition(nodeInList -> nodeInList.getRate().getAlias().equals(HOC_LUC[choice - 1]), list);
    }

    public void writeStudentsListToFile() {
        FileHandler.writeListToFile(this.dataSource, list);
    }

    public void deleteStudent(FoundStudent delStudent) {
        if (delStudent != null) {
            list.remove(delStudent.index);
            System.out.println("Xoa thanh cong");
        } else {
            System.out.println("Khong the xoa neu khong ton tai");
        }
    }
}
