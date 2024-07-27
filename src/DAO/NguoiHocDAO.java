package DAO;

import ENTITY.NguoiHoc;
import ENTITY.NguoiHoc;
import UTILS.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{
    String insert = "insert into NguoiHoc values(?,?,?,?,?,?,?,?,?)";
    String update = "update NguoiHoc set HoTen = ?,GioiTinh = ?,NgaySinh = ?,DienThoai = ?,Email = ?,GhiChu = ?,MaNV = ? where MaNH = ?";
    String delete = "delete NguoiHoc where MaNH = ?";
    String selectAll_SQL = "select * from NguoiHoc";
    String selectById_SQL = "select * from NguoiHoc where MaNH = ?";
    @Override
    public void insert(NguoiHoc entity){
        try {
            XJdbc.update(insert, entity.getMaNH(), entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(NguoiHoc entity){
        try {
            XJdbc.update(update, entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getMaNH());
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
    public NguoiHoc selectById(String id){
        List<NguoiHoc> list = this.selectBySql(selectById_SQL, id);
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }
    @Override
    public List<NguoiHoc> selectAll(){
        return this.selectBySql(selectAll_SQL);
    }
    public List<NguoiHoc> selectByKeyWord(String keyword){
        String sql = "select * from nguoihoc where HoTen like ?";
        return selectBySql(sql, "%"+keyword+"%");
    }
    public List<NguoiHoc> selectNotInCourse(int MaKH, String keyword){
        String sql = "select * from nguoihoc where HoTen like ? and MaNH not in (select MaNH from HocVien where MaKH = ?)";
        return selectBySql(sql, "%"+keyword+"%",MaKH);
    }
    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args){
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()){
                NguoiHoc nh = new NguoiHoc();
                nh.setMaNH(rs.getString(1));
                nh.setHoTen(rs.getString(2));
                nh.setGioiTinh(rs.getBoolean(3));
                nh.setNgaySinh(rs.getDate(4));
                nh.setDienThoai(rs.getString(5));
                nh.setEmail(rs.getString(6));
                nh.setGhiChu(rs.getString(7));
                nh.setMaNV(rs.getString(8));
                nh.setNgayDK(rs.getDate(9));
                list.add(nh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
