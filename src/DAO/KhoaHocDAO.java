package DAO;

import ENTITY.KhoaHoc;
import UTILS.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>{
    String insert = "insert into KhoaHoc values(?,?,?,?,?,?,?)";
    String update = "update KhoaHoc set MaCD = ?,HocPhi = ?,ThoiLuong = ?,NgayKG = ?,GhiChu = ?,MaNV = ?,NgayTao = ? where MaKH = ?";
    String delete = "delete KhoaHoc where MaKH = ?";
    String selectAll_SQL = "select * from KhoaHoc";
    String selectById_SQL = "select * from KhoaHoc where MaKH = ?";
    @Override
    public void insert(KhoaHoc entity){
        try {
            XJdbc.update(insert, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao());
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(KhoaHoc entity){
        try {
            XJdbc.update(update, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao(), entity.getMaKH());
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(Integer id){
        try {
            XJdbc.update(delete, id);
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public KhoaHoc selectById(Integer id){
        List<KhoaHoc> list = this.selectBySql(selectById_SQL, id);
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }
    public List<KhoaHoc> selectByChuyenDe(String MaCD){
        String sql = "select * from khoahoc where MaCD = ?";
        return selectBySql(sql, MaCD);
    }
    @Override
    public List<KhoaHoc> selectAll(){
        return this.selectBySql(selectAll_SQL);
    }
    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args){
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()){
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getInt(1));
                kh.setMaCD(rs.getString(2));
                kh.setHocPhi(rs.getDouble(3));
                kh.setThoiLuong(rs.getInt(4));
                kh.setNgayKG(rs.getDate(5));
                kh.setGhiChu(rs.getString(6));
                kh.setMaNV(rs.getString(7));
                kh.setNgayTao(rs.getDate(8));
                list.add(kh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public List<Integer> getYear(){
        String sql = "select distinct year(NgayKG) from KhoaHoc order by year(NgayKG) desc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql);
            while (rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
