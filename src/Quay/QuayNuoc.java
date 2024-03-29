package Quay;

import KhoHang.KhoHang;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuayNuoc extends KhoHang {
    public static int soLuong = 0;
    public static float giaCa = 0;

    public QuayNuoc(String Ten, String MaSo, String NgayNhapHang, String XuatXu, int SoLuong, float GiaCa) {
        super(Ten, MaSo, NgayNhapHang, XuatXu, SoLuong, GiaCa);
    }

    public QuayNuoc() {

    }

    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tên sản phẩm: ");
        String Ten = sc.nextLine();
        System.out.print("Mã sản phẩm: ");
        String MaSo = sc.nextLine();
        System.out.print("Ngày nhập hàng: ");
        String NgayNhapHang = sc.nextLine();
        System.out.print("Xuất xứ: ");
        String XuatXu = sc.nextLine();
        do {
            System.out.print("Số lượng nhập vào: ");
            try {
                soLuong = sc.nextInt();
            } catch (Exception ignored) {
                System.out.println("Không hợp lệ, vui lòng nhập lại!");
                sc.nextLine();
            }
        } while (soLuong == 0);

        do {
            System.out.print("Giá dự định: ");
            try {
                giaCa = sc.nextFloat();
            } catch (Exception ignored) {
                System.out.println("Không hợp lệ, vui lòng nhập lại!");
                sc.nextLine();
            }
        } while (giaCa == 0);
        QuayNuoc nuoc = new QuayNuoc(Ten, MaSo, NgayNhapHang, XuatXu, soLuong, giaCa);
        nuocList.add(nuoc);
    }

    public void del() {
        KhoHang.xuatNuoc();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã số sản phẩm muốn xoá: ");
        String MaSo = sc.nextLine();
        QuayNuoc nuoc = nuocList.stream().filter(o -> o.getMaSo().equals(MaSo)).findFirst().orElse(null);
        if (nuoc == null) {
            System.out.println("Không tồn tại.");
        }
        nuocList.remove(nuoc);
        System.out.println("Xác nhận xoá.");
    }

    @Override
    public void showThongTin() {
        nuocList.forEach(o -> System.out.println(o.toString()));
    }

    public List<QuayNuoc> TimKiemQuayNuoc(String MaSo) {
        return nuocList.stream().filter(o -> o.getMaSo().contains(MaSo)).collect(Collectors.toList());
    }

    @Override
    public void TimKiem() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã số sản phẩm muốn tìm: ");
        String a = sc.nextLine();
        TimKiemQuayNuoc(a).forEach(quayNuoc -> {
            System.out.println(quayNuoc.toString());
        });
    }

    public List<QuayNuoc> SuaThongTinNuoc(int stt, QuayNuoc nuoc) {
        nuocList.set(stt, nuoc);
        return nuocList;
    }

    @Override
    public void SuaThongTin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập STT muốn sửa thông tin: ");
        int a;
        try {
            a = sc.nextInt();
        } catch (Exception ignored) {
            System.out.println("Không tồn tại món hàng này!");
            return;
        }
        System.out.print("Tên sản phẩm: ");
        sc.nextLine();
        String Ten = sc.nextLine();
        System.out.print("Mã sản phẩm: ");
        String MaSo = sc.nextLine();
        System.out.print("Ngày nhập hàng: ");
        String NgayNhapHang = sc.nextLine();
        System.out.print("Xuất xứ: ");
        String XuatXu = sc.nextLine();
        do {
            System.out.print("Số lượng nhập vào: ");
            try {
                soLuong = sc.nextInt();
            } catch (Exception ignored) {
                System.out.println("Không hợp lệ, vui lòng nhập lại!");
                sc.nextLine();
            }
        } while (soLuong == 0);

        do {
            System.out.print("Giá dự định: ");
            try {
                giaCa = sc.nextFloat();
            } catch (Exception ignored) {
                System.out.println("Không hợp lệ, vui lòng nhập lại!");
                sc.nextLine();
            }
        } while (giaCa == 0);
        QuayNuoc nuoc = new QuayNuoc(Ten, MaSo, NgayNhapHang, XuatXu, soLuong, giaCa);
        SuaThongTinNuoc(a - 1, nuoc);
    }

    @Override
    public void inputFile(String URL) throws IOException {
        File file = new File(URL);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");
            String ten = row[0];
            String maSo = row[1];
            String ngayNhapHang = row[2];
            String xuatSu = row[3];
            int soLuong = Integer.parseInt(row[4]);
            float giaCa = Float.parseFloat(row[5]);

            QuayNuoc nuoc = new QuayNuoc(ten, maSo, ngayNhapHang, xuatSu, soLuong, giaCa);
            nuocList.add(nuoc);
        }
        reader.close();
    }

    @Override
    public String toString() {
        return "Tên sản phẩm: " + Ten + " - "
                + "Mã sản phẩm: " + MaSo + " - "
                + "Xuất xứ: " + XuatXu + " - "
                + "Số lượng: " + SoLuong + " - "
                + "Giá cả: " + GiaCa;
    }
}