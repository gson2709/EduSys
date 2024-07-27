package DAO;

import ENTITY.HocVien;
import UTILS.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HocVienDAO extends EduSysDAO<HocVien, Integer>{
    String insert = "insert into HocVien values (?,?,?)";
    String update = "update HocVien set Diem = ? where MaHV = ?";
    String delete = "delete HocVien where MaHV = ?";
    String select_by_id_SQL = "select * from hocvien where MaHV = ?";
    @Override
    public void update(HocVien entity){
        try {
            XJdbc.update(update, entity.getDiem(), entity.getMaHV());
        } 
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    @Override
    public void delete(Integer id){
        try {
            XJdbc.update(delete, id);
        } 
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public List<HocVien> selectByKhoaHoc(int MaKH){
        String sql = "select * from hocvien where MaKH = ?";
        return selectBySql(sql, MaKH);
    }
    @Override
    protected List<HocVien> selectBySql(String sql, Object... args){
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()){
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt(1));
                hv.setMaKH(rs.getInt(2));
                hv.setMaNH(rs.getString(3));
                hv.setDiem(rs.getDouble(4));
                list.add(hv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void insert(HocVien entity) {
        try {
            XJdbc.update(insert, entity.getMaKH(), entity.getMaNH(), entity.getDiem());
        } 
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public HocVien selectById(Integer id) {
        List<HocVien> list = selectBySql(select_by_id_SQL, id);
        if (!list.isEmpty()){
            return list.get(0);
        }
        else{
            return null;
        }
    }

    @Override
    public List<HocVien> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
