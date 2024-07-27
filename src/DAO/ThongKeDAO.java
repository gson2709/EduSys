package DAO;

import UTILS.XJdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()){
                Object[] vals = new Object[cols.length];
                for (int i=0;i<cols.length;i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Object[]> getBangDiem(int MaKH){
        String sql = "{call sp_BangDiem(?)}";
        String cols[] = {"MaNH","HoTen","Diem"};
        return this.getListOfArray(sql, cols, MaKH);
    }
    public List<Object[]> getLuongNguoiHoc(){
        String sql = "{call sp_LuongNguoiHoc}";
        String cols[] = {"Nam","SoLuong","DauTien","CuoiCung"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getDiemChuyenDe(){
        String sql = "{call sp_DiemChuyenDe}";
        String cols[] = {"ChuyenDe","SoHV","ThapNhat","CaoNhat","TrungBinh"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getDoanhThu(int nam){
        String sql = "{call sp_DoanhThu(?)}";
        String cols[] = {"ChuyenDe","SoKH","SoHV","DoanhThu","ThapNhat","CaoNhat","TrungBinh"};
        return this.getListOfArray(sql, cols, nam);
    }
}
