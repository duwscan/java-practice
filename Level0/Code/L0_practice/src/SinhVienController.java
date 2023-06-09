import validator.Validation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static validator.Validation.*;

public class SinhVienController {
    public static class FoundSinhVien {
        SinhVien foundSinhVien;
        int index;

        FoundSinhVien(SinhVien foundSinhVien, int index) {
            this.foundSinhVien = foundSinhVien;
            this.index = index;
        }

        public SinhVien getFoundSinhVien() {
            return foundSinhVien;
        }


        public int getIndex() {
            return index;
        }
    }

    public static SinhVien createSinhVienByConsole() {
        String name = getInput("Nhap vao ten sinh vien(Ten <" + Validation.MAX_NAME_LENGTH + "):", String::trim, Validation::NameRule);
        LocalDate dob = getInput("Nhap vao ngay thang nam sinh(YYYY-MM-DD)(Y >" + Validation.MIN_START_YEAR + "):", input -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date;
            try {
                date = LocalDate.parse(input, formatter);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return date;
        }, Validation::DateOfBirthRule);
        String address = getInput("Nhap vao dia chi (Chuoi <" + Validation.MAX_ADDRESS_LENGTH + "):", String::trim, Validation::AddressRule);
        Double height = getInput("Nhap vao chieu cao don vi tu cm " + Validation.MIN_HEIGHT + "-" + Validation.MAX_HEIGHT + " :", Double::parseDouble, Validation::HeightRule);
        Double weight = getInput("Nhap vao can nang don vi tu kg  " + Validation.MIN_WEIGHT + "-" + Validation.MAX_WEIGHT + " :", Double::parseDouble, Validation::WeightRule);
        String school = getInput("Nhap vao truong hoc  (Chuoi <" + Validation.MAX_SCHOOL_LENGTH + "):", String::trim, Validation::SchoolNameRule);
        int enrollYear = getInput("Nhap vao can nam bat dau hoc Y > " + Validation.MIN_START_YEAR + " :", Integer::parseInt, Validation::EnrollYearRule);
        double gpa = getInput("Nhap vao diem trung binh tich luy " + MIN_GPA + "-" + MAX_GPA + ":", Double::parseDouble, Validation::GPARule);
        SinhVien aSinhVien = new SinhVien(name, dob, address, height, weight, school, enrollYear, gpa);
        System.out.println("Sinh vien vua nhap");
        System.out.println(aSinhVien);
        return aSinhVien;
    }

    public static <T> FoundSinhVien findSinhVien(ArrayList<SinhVien> arr, T key, String action) {
        for (int i = 0; i < arr.size(); i++) {
            if (sinhVienFilter(key, action, arr.get(i))) {
                return new FoundSinhVien(arr.get(i), i);
            }
        }
        return null;
    }

    private static <T> boolean sinhVienFilter(T key, String action, SinhVien sinhVien) {
        switch (action) {
            case "STUDENT_ID" -> {
                return key.equals(sinhVien.getStudentId());
            }
            case "STUDENT_NAME" -> {
                return key.equals(sinhVien.getName());
            }
        }
        return false;
    }

    public static void updateSinhVien(ArrayList<SinhVien> arr, FoundSinhVien updateSinhVien) {
        System.out.println("Ban muon sua thuoc tinh nao cua Sinh Vien:");

        for (int i = 0; i < UPDATABLE_PROPERTIES.length; i++) {
            System.out.println((i + 1) + " Sua " + UPDATABLE_PROPERTIES[i]);
        }
        int choice = getInput("Nhap vao lua chon cua ban:", Integer::parseInt, Validation::UpdateSinhVienRule);
        int propertyIndex = choice - 1;
        processUpdate(arr, updateSinhVien, propertyIndex);
        System.out.println(arr.get(updateSinhVien.index));
    }

    private static void processUpdate(ArrayList<SinhVien> arr, FoundSinhVien updateSinhVien, int propertyIndex) {
        SinhVien updated = updateSinhVien.getFoundSinhVien();
        System.out.println("Nhap gia tri ban muon sua cho" + UPDATABLE_PROPERTIES[propertyIndex] + ":");
        switch (UPDATABLE_PROPERTIES[propertyIndex]) {
            case "TEN" ->
                    updated.setName(getInput("Nhap vao ten sinh vien(Ten <" + Validation.MAX_NAME_LENGTH + "):", String::trim, Validation::NameRule));
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
                    }, Validation::DateOfBirthRule));
            case "DIA_CHI" ->
                    updated.setAddress(getInput("Nhap vao dia chi (Chuoi <" + Validation.MAX_ADDRESS_LENGTH + "):", String::trim, Validation::AddressRule));
            case "CHIEU_CAO" ->
                    updated.setHeight(getInput("Nhap vao chieu cao don vi tu cm " + Validation.MIN_HEIGHT + "-" + Validation.MAX_HEIGHT + " :", Double::parseDouble, Validation::HeightRule));
            case "CAN_NANG" ->
                    updated.setWeight(getInput("Nhap vao can nang don vi tu kg  " + Validation.MIN_WEIGHT + "-" + Validation.MAX_WEIGHT + " :", Double::parseDouble, Validation::WeightRule));
            case "TEN_TRUONG" ->
                    updated.setUniversityName(getInput("Nhap vao truong hoc  (Chuoi <" + Validation.MAX_SCHOOL_LENGTH + "):", String::trim, Validation::SchoolNameRule));
            case "NAM_HOC" ->
                    updated.setEnrollYear(getInput("Nhap vao can nam bat dau hoc Y > " + Validation.MIN_START_YEAR + " :", Integer::parseInt, Validation::EnrollYearRule));
            case "DIEM" -> {
                updated.setGpa(getInput("Nhap vao diem trung binh tich luy " + MIN_GPA + "-" + MAX_GPA + ":", Double::parseDouble, Validation::GPARule));
                updated.setHocLuc();
            }
        }
        arr.set(updateSinhVien.index, updated);
    }

    public static void printList(ArrayList<SinhVien> arr) {
        for (SinhVien i : arr) {
            System.out.println(i);
        }
    }

    public static void printByHocLuc(ArrayList<SinhVien> arr) {
        System.out.println("Ban xem muon xem danh sach hoc sinh theo:");
        for (int i = 0; i < HOC_LUC.length; i++) {
            System.out.println((i + 1) + "." + HOC_LUC[i]);
        }
        int choice = getInput("Nhap vao lua chon cua ban:", Integer::parseInt, Validation::getChoiceHocLucRule);
        printByCondition(nodeInList -> nodeInList.getHocLuc().getAlias().equals(HOC_LUC[choice - 1]), arr);
    }

    private interface ListByCondition<T> {
        boolean condition(T nodeInList);
    }

    public static <T> void printByCondition(ListByCondition<T> condition, ArrayList<T> arr) {
        for (T el : arr) {
            if (condition.condition(el)) {
                System.out.println(el);
            }
        }
    }

    public static void printPercentageSinhVienHocLuc(ArrayList<SinhVien> arr) {
        arr.sort(Comparator.comparingDouble(SinhVien::getPercentage).reversed());
        for (SinhVien a : arr) {
            System.out.println(arr.indexOf(a) + ".Hoc luc:" + a.getPercentage());
        }
    }

    public static void printOverallPercentage(ArrayList<SinhVien> arr) {
        Map<HocLuc, Double> hocLucFrequency = new HashMap<>();
        for (SinhVien score : arr) {
            HocLuc hocLuc = HocLuc.getHocLuc(score.getGpa());
            hocLucFrequency.put(hocLuc, hocLucFrequency.getOrDefault(hocLuc, 0.0) + 1);
        }
        int totalScores = arr.size();
        for (HocLuc hocLuc : hocLucFrequency.keySet()) {
            Double frequency = hocLucFrequency.get(hocLuc);
            double percentage = frequency / totalScores * 100;
            System.out.println(hocLuc.getAlias() + ": " + percentage + "%");
        }
    }

    public static void writeSinhVienListToFile(ArrayList<SinhVien> sinhVienList, String fileName) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            for (SinhVien sinhVien : sinhVienList) {
                writer.write(sinhVien.toString());
                writer.newLine();
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
