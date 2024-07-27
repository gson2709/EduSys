package DAO;

import ENTITY.ChuyenDe;
import UTILS.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{
    String insert = "insert into ChuyenDe values(?,?,?,?,?,?)";
    String update = "update ChuyenDe set TenCD = ?,HocPhi = ?,ThoiLuong = ?,Hinh = ?,MoTa = ? where MaCD = ?";
    String delete = "delete ChuyenDe where MaCD = ?";
    String selectAll_SQL = "select * from ChuyenDe";
    String selectById_SQL = "select * from ChuyenDe where MaCD = ?";
    @Override
    public void insert(ChuyenDe entity){
        try {
            XJdbc.update(insert, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa());
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    @Override
    public void update(ChuyenDe entity){
        try {
            XJdbc.update(update, entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa(), entity.getMaCD());
        } 
        catch (Exception e) {
            throw new RuntimeException();
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
    public ChuyenDe selectById(String id){
        List<ChuyenDe> list = this.selectBySql(selectById_SQL, id);
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }
    @Override
    public List<ChuyenDe> selectAll(){
        return this.selectBySql(selectAll_SQL);
    }
    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args){
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()){
                ChuyenDe cd = new ChuyenDe();
                cd.setMaCD(rs.getString(1));
                cd.setTenCD(rs.getString(2));
                cd.setHocPhi(rs.getDouble(3));
                cd.setThoiLuong(rs.getInt(4));
                cd.setHinh(rs.getString(5));
                cd.setMoTa(rs.getString(6));
                list.add(cd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
