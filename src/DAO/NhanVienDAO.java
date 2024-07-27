package DAO;

import ENTITY.NhanVien;
import UTILS.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class NhanVienDAO extends EduSysDAO<NhanVien, String>{
    String insert = "insert into NhanVien values(?,?,?,?,?)";
    String update = "update NhanVien set HoTen = ?,MatKhau = ?,VaiTro = ?,Email = ? where MaNV = ?";
    String delete = "delete NhanVien where MaNV = ?";
    String selectAll_SQL = "select * from NhanVien";
    String selectById_SQL = "select * from NhanVien where MaNV = ?";
    @Override
    public void insert(NhanVien entity){
        try {
            XJdbc.update(insert, entity.getMaNV(), entity.getHoTen(), entity.getMatKhau(), entity.isVaiTro(), entity.getEmail());
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(NhanVien entity){
        try {
            XJdbc.update(update, entity.getHoTen(), entity.getMatKhau(), entity.isVaiTro(), entity.getEmail(), entity.getMaNV());
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(String id){
        try {
            XJdbc.update(delete, id);
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public NhanVien selectById(String id){
        List<NhanVien> list = this.selectBySql(selectById_SQL, id);
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }
    @Override
    public List<NhanVien> selectAll(){
        return this.selectBySql(selectAll_SQL);
    }
    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args){
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setHoTen(rs.getString(2));
                nv.setMatKhau(rs.getString(3));
                nv.setVaiTro(rs.getBoolean(4));
                nv.setEmail(rs.getString(5));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public boolean hasManvIn(String MaNV){
        String sql_NV = "select * from nhanvien where (MaNV in (select MaNV from khoahoc) or MaNV in (select MaNV from nguoihoc)) and MaNV = ?";
        List<NhanVien> list = selectBySql(sql_NV, MaNV);
        return list.isEmpty();
    }
}
