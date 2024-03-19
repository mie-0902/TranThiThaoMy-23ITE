package hk2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader in=null;
        FileWriter out=null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số lượng học viên: ");
        int n = scanner.nextInt();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin học viên thứ " + (i + 1) + ":");
            System.out.println("Nhập mã sinh viên:");
            String maSV = scanner.next();
            System.out.println("Nhập họ tên sinh viên:");
            String hoTen = scanner.next();
            System.out.println("Nhập giới tính sinh viên:");
            String gioiTinh = scanner.next();
            System.out.println("Nhập điểm python :");
            int diemPython = scanner.nextInt();
            System.out.println("Nhập điểm  java :");
            int diemJava = scanner.nextInt();
            students.add(new Student(maSV, hoTen, gioiTinh, diemPython, diemJava));
        }
        Collections.sort(students, Collections.reverseOrder());
        System.out.println("\nDanh sách học viên:");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.print("\nNhập họ tên học viên cần tìm: ");
        String hoTenTiem = scanner.next();
        List<Student> ketQuaTimKiem = students.stream()
                .filter(student -> student.getHoTen().equals(hoTenTiem))
                .collect(Collectors.toList());
        if (ketQuaTimKiem.isEmpty()) {
            System.out.println("Không tìm thấy học viên có họ tên " + hoTenTiem);
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Student student : ketQuaTimKiem) {
                System.out.println(student);
            }
        }
        System.out.println("\nDanh sách học viên đậu:");
        for (Student student : students) {
            if (student.getDiemTB() >= 5) {
                System.out.println(student);
            }
        }
        System.out.println("\nDanh sách học viên trượt:");
        for (Student student : students) {
            if (student.getDiemTB() < 5 && student.getGioiTinh().equals("Nam")) {
                System.out.println(student);
            }
        }
        System.out.println("\nDanh sách học viên trượt (phái nữ được đậu):");
        for (Student student : students) {
            if (student.getDiemTB() < 5 && student.getGioiTinh().equals("Nữ")) {
                System.out.println(student);
            }
        }

        try  {
            in= new FileReader("/Users/kimngan/Documents/java/inout/src/input.txt");
            out= new FileWriter("/Users/kimngan/Documents/java/inout/src/output.txt");
            for (Student student : students) {
out.write(student.toString() + "\n");
            }
        }finally {
            if( in!=null){
                in.close();
            }
            if(out !=null){
                out.close();
            }
        }
    }
}

class Student implements Comparable<Student> {
    private String maSV;
    private String hoTen;
    private String gioiTinh;
    private int diemPython;
    private int diemJava;
    private double diemTB;

    public Student(String maSV, String hoTen, String gioiTinh, int diemPython, int diemJava) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diemPython = diemPython;
        this.diemJava = diemJava;
        this.diemTB = (diemJava * 2 + diemPython) / 3;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public int getDiemPython() {
        return diemPython;
    }

    public int getDiemJava() {
        return diemJava;
    }

    public double getDiemTB() {
        return diemTB;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d %d %.2f %s", maSV, hoTen, gioiTinh, diemPython, diemJava, diemTB,
                diemTB >= 5 ? "Đậu" : "Trượt");
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(o.getDiemTB(), this.diemTB);
    }
}