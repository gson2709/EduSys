package GUI;

import DAO.KhoaHocDAO;
import DAO.ThongKeDAO;
import ENTITY.KhoaHoc;
import UTILS.Auth;
import UTILS.Excel;
import UTILS.MsgBox;
import UTILS.XImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ThongKeJDialog extends javax.swing.JFrame {

    /**
     * Creates new form ThongKeJDialog
     */
     
    public ThongKeJDialog() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("EduSys - Tổng hợp & thống kê");
        setIconImage(XImage.getAppIcon());
        selectTab(0);
        fillComboBoxKhoaHoc();
        fillComboBoxYear();
        fillTableBangDiem();
        fillTableNguoiHoc();
        fillTableDiemCD();
        fillTableDoanhThu();
        if (!Auth.isManager())
            jTabbedPane.remove(3);
    }   
    ThongKeDAO tkdao = new ThongKeDAO();
    KhoaHocDAO khdao = new KhoaHocDAO();
    XSSFWorkbook book;
    XSSFSheet sheet;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboKhoaHoc = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBangDiem = new javax.swing.JTable();
        btnExport = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblNguoiHoc = new javax.swing.JTable();
        btnExport2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiemChuyenDe = new javax.swing.JTable();
        btnExport3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        btnExport4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 255));
        lblTitle.setText("TỔNG HỢP THỐNG KÊ");

        jLabel2.setText("KHÓA HỌC:");

        cboKhoaHoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhoaHocItemStateChanged(evt);
            }
        });

        tblBangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MÃ NGƯỜI HỌC", "HỌ VÀ TÊN", "ĐIỂM", "XẾP LOẠI"
            }
        ));
        jScrollPane3.setViewportView(tblBangDiem);

        btnExport.setText("Xuất tệp excel");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboKhoaHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExport)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExport)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("BẢNG ĐIỂM", jPanel2);

        tblNguoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NĂM", "SỐ NGƯỜI HỌC", "ĐĂNG KÍ SỚM NHẤT", "ĐĂNG KÍ MUỘN NHẤT"
            }
        ));
        jScrollPane5.setViewportView(tblNguoiHoc);

        btnExport2.setText("Xuất tệp excel");
        btnExport2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExport2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExport2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExport2)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("NGƯỜI HỌC", jPanel3);

        tblDiemChuyenDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CHUYÊN ĐỀ", "SỐ LƯỢNG HỌC VIÊN", "ĐIỂM THẤP NHẤT", "ĐIỂM CAO NHẤT", "ĐIỂM TRUNG BÌNH"
            }
        ));
        jScrollPane1.setViewportView(tblDiemChuyenDe);

        btnExport3.setText("Xuất tệp excel");
        btnExport3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExport3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExport3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExport3)
                .addGap(15, 15, 15))
        );

        jTabbedPane.addTab("ĐIỂM CHUYÊN ĐỀ", jPanel4);

        jLabel3.setText("NĂM");

        cboYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboYearItemStateChanged(evt);
            }
        });

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CHUYÊN ĐỀ", "SỐ KHÓA HỌC", "SỐ HỌC VIÊN", "DOANH THU", "HỌC PHÍ THẤP NHẤT", "HỌC PHÍ CAO NHẤT", "HỌC PHÍ TRUNG BÌNH"
            }
        ));
        jScrollPane4.setViewportView(tblDoanhThu);

        btnExport4.setText("Xuất tệp excel");
        btnExport4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExport4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExport4)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExport4)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("DOANH THU", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle)
                    .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboKhoaHocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhoaHocItemStateChanged
        fillTableBangDiem();
    }//GEN-LAST:event_cboKhoaHocItemStateChanged

    private void cboYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboYearItemStateChanged
        fillTableDoanhThu();
    }//GEN-LAST:event_cboYearItemStateChanged

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        BangDiemToExcel();
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnExport2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExport2ActionPerformed
        NguoiHocToExcel();
    }//GEN-LAST:event_btnExport2ActionPerformed

    private void btnExport3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExport3ActionPerformed
        DiemChuyenDeToExcel();
    }//GEN-LAST:event_btnExport3ActionPerformed

    private void btnExport4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExport4ActionPerformed
        DoanhThuToExcel();
    }//GEN-LAST:event_btnExport4ActionPerformed
    public void selectTab(int select){
        jTabbedPane.setSelectedIndex(select);
    }
    void fillComboBoxKhoaHoc(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhoaHoc.getModel();
        model.removeAllElements();
        List<KhoaHoc> list = khdao.selectAll();
        for (KhoaHoc kh:list){
            model.addElement(kh);
        }
        cboKhoaHoc.setModel(model);
        cboKhoaHoc.getEditor().getItem().toString();
    }
    void fillComboBoxYear(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboYear.getModel();
        model.removeAllElements();
        List<Integer> list = khdao.getYear();
        for (Integer year:list){
            model.addElement(year);
        }
        cboYear.setModel(model);
    }
    void fillTableBangDiem(){
        DefaultTableModel model = (DefaultTableModel) tblBangDiem.getModel();
        model.setRowCount(0);
        KhoaHoc kh = (KhoaHoc) cboKhoaHoc.getSelectedItem();
        List<Object[]> list = tkdao.getBangDiem(kh.getMaKH());
        for (Object[] row:list){
            double diem = (double) row[2];
            model.addRow(new Object[]{row[0],row[1],diem,getHocLuc(diem)});
        }
        tblBangDiem.setModel(model);
    }
    void fillTableNguoiHoc(){
        DefaultTableModel model = (DefaultTableModel) tblNguoiHoc.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.getLuongNguoiHoc();
        for (Object[] row:list){
            model.addRow(new Object[]{row[0],row[1],row[2],row[3]});
        }
        tblNguoiHoc.setModel(model);
    }
    void fillTableDiemCD(){
        DefaultTableModel model = (DefaultTableModel) tblDiemChuyenDe.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.getDiemChuyenDe();
        for (Object[] row:list){
            model.addRow(new Object[]{row[0],row[1],row[2],row[3],row[4]});
        }
        tblDiemChuyenDe.setModel(model);
    }
    void fillTableDoanhThu(){
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        int year = (int) cboYear.getSelectedItem();
        List<Object[]> list = tkdao.getDoanhThu(year);
        for (Object[] row:list){
            model.addRow(new Object[]{row[0],row[1],row[2],row[3],row[4],row[5],row[6]});
        }
        tblDoanhThu.setModel(model);
    }
    String getHocLuc(double diem){
        if (diem>=9)
            return "Xuất sắc";
        if (diem>=7.5)
            return "Giỏi";
        if (diem>=6.5)
            return "Khá";
        if (diem>=5)
            return "Trung bình";
        return "Chưa đạt";
    }
    void BangDiemToExcel(){
        book = new XSSFWorkbook();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhoaHoc.getModel();
        try {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String header[] = {"MÃ NGƯỜI HỌC", "HỌ VÀ TÊN", "ĐIỂM", "XẾP LOẠI"};
                for (int i = 0; i < model.getSize(); i++) {
                    int year = (int) model.getElementAt(i);
                    List<Object[]> list = tkdao.getDoanhThu(year);
                    sheet = book.createSheet(model.getElementAt(i).toString());
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

                    Row row = sheet.createRow(0);
                    Cell cell = row.createCell(0);
                    cell.setCellValue("BẢNG ĐIỂM THEO KHÓA HỌC");
                    cell.setCellStyle(titleFormat());

                    row = sheet.createRow(2);
                    for (int j = 0; j < header.length; j++) {
                        cell = row.createCell(j);
                        cell.setCellValue(header[j]);
                        cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, true));

                    }

                    int rownum = 3;
                    for (Object[] rows : list) {
                        row = sheet.createRow(rownum++);
                        cell = row.createCell(0);
                        cell.setCellValue((String) rows[0]);
                        cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.LEFT, false));
                        cell = row.createCell(1);
                        cell.setCellValue((int) rows[1]);
                        cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                        cell = row.createCell(2);
                        cell.setCellValue((int) rows[2]);
                        cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                        cell = row.createCell(3);
                        cell.setCellValue((double) rows[3]);
                        cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                        cell = row.createCell(4);
                        cell.setCellValue((double) rows[4]);
                        cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                        cell = row.createCell(5);
                        cell.setCellValue((double) rows[5]);
                        cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                        cell = row.createCell(6);
                        cell.setCellValue((double) rows[6]);
                        cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    }
                    for (int k = 0; k < header.length; k++) {
                        sheet.autoSizeColumn(k);
                    }
                    FileOutputStream out = new FileOutputStream(chooser.getSelectedFile());
                    book.write(out);
                    out.close();
                    MsgBox.alert(this, "Xong");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    void NguoiHocToExcel(){
        book = new XSSFWorkbook();
        sheet = book.createSheet("Người học");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        List<Object[]> list = tkdao.getLuongNguoiHoc();
        String header[] = {"NĂM","SỐ NGƯỜI HỌC","ĐĂNG KÍ SỚM NHẤT","ĐĂNG KÍ MUỘN NHẤT"};
        try {
                Row row = sheet.createRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue("THỐNG KÊ NGƯỜI HỌC THEO TỪNG NĂM");
                cell.setCellStyle(titleFormat());
                
                row = sheet.createRow(2);
                for (int i=0;i<header.length;i++){
                    cell = row.createCell(i);
                    cell.setCellValue(header[i]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, true));
                     
                }
                
                int rownum = 3;
                for (Object[] rows:list){
                    row = sheet.createRow(rownum++);
                    cell = row.createCell(0);
                    cell.setCellValue((int) rows[0]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(1);
                    cell.setCellValue((int) rows[1]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(2);
                    cell.setCellValue((Date) rows[2]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(3);
                    cell.setCellValue((Date) rows[3]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                }
                for (int k=0;k<header.length;k++)
                    sheet.autoSizeColumn(k);
                FileOutputStream out = new FileOutputStream(new File("D:\\FPT Polytechnic\\SOF2041 - Project Example\\NguoiHoc.xlsx"));
                book.write(out);
                out.close();
                MsgBox.alert(this, "Xong");
            }   
        catch (Exception e) {
            e.printStackTrace();
        }
    } 
    void DiemChuyenDeToExcel(){
        book = new XSSFWorkbook();
        sheet = book.createSheet("Điểm chuyên đề");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
        List<Object[]> list = tkdao.getDiemChuyenDe();
        String header[] = {"CHUYÊN ĐỀ","SỐ LƯỢNG HỌC VIÊN","ĐIỂM THẤP NHẤT","ĐIỂM CAO NHẤT","ĐIỂM TRUNG BÌNH"};
        try {
                Row row = sheet.createRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue("THỐNG KÊ ĐIỂM CHUYÊN ĐỀ");
                cell.setCellStyle(titleFormat());
                
                row = sheet.createRow(2);
                for (int i=0;i<header.length;i++){
                    cell = row.createCell(i);
                    cell.setCellValue(header[i]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, true));
                     
                }
                
                int rownum = 3;
                for (Object[] rows:list){
                    row = sheet.createRow(rownum++);
                    cell = row.createCell(0);
                    cell.setCellValue((String) rows[0]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.LEFT, false));
                    cell = row.createCell(1);
                    cell.setCellValue((int) rows[1]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(2);
                    cell.setCellValue((double) rows[2]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(3);
                    cell.setCellValue((double) rows[3]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(4);
                    cell.setCellValue((double) rows[4]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                }
                for (int k=0;k<header.length;k++)
                    sheet.autoSizeColumn(k);
                FileOutputStream out = new FileOutputStream(new File("D:\\FPT Polytechnic\\SOF2041 - Project Example\\DiemChuyenDe.xlsx"));
                book.write(out);
                out.close();
                MsgBox.alert(this, "Xong");
            }   
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    void DoanhThuToExcel(){
        book = new XSSFWorkbook();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboYear.getModel();
        String header[] = {"CHUYÊN ĐỀ","SỐ KHÓA HỌC","SỐ HỌC VIÊN","DOANH THU","HỌC PHÍ THẤP NHẤT","HỌC PHÍ CAO NHẤT","HỌC PHÍ TRUNG BÌNH"};
        try {
            for (int i=0;i<model.getSize();i++){
                int year = (int) model.getElementAt(i);
                List<Object[]> list = tkdao.getDoanhThu(year);
                sheet = book.createSheet(model.getElementAt(i).toString());
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
                
                Row row = sheet.createRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue("BẢNG ĐIỂM THEO KHÓA HỌC");
                cell.setCellStyle(titleFormat());
                
                row = sheet.createRow(2);
                for (int j=0;j<header.length;j++){
                    cell = row.createCell(j);
                    cell.setCellValue(header[j]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER,true));
                     
                }
                
                int rownum = 3;
                for (Object[] rows:list){
                    row = sheet.createRow(rownum++);
                    cell = row.createCell(0);
                    cell.setCellValue((String) rows[0]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.LEFT, false));
                    cell = row.createCell(1);
                    cell.setCellValue((int) rows[1]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(2);
                    cell.setCellValue((int) rows[2]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(3);
                    cell.setCellValue((double) rows[3]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(4);
                    cell.setCellValue((double) rows[4]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(5);
                    cell.setCellValue((double) rows[5]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(6);
                    cell.setCellValue((double) rows[6]);
                    cell.setCellStyle(cellFormat(BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                }
                for (int k=0;k<header.length;k++)
                    sheet.autoSizeColumn(k);
            }         
            FileOutputStream out = new FileOutputStream(new File("D:\\FPT Polytechnic\\SOF2041 - Project Example\\DoanhThu.xlsx"));
            book.write(out);
            out.close();
            MsgBox.alert(this, "Xong");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private CellStyle titleFormat(){
        XSSFCellStyle style = book.createCellStyle();
        Font font = book.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        font.setColor(HSSFColorPredefined.BLUE.getIndex());
        font.setFontHeightInPoints((short) 15);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
    public CellStyle cellFormat(BorderStyle border, HorizontalAlignment align, boolean bold){
        XSSFCellStyle style = book.createCellStyle();
        Font font = book.createFont();
        font.setFontName("Arial");
        font.setBold(bold);
        style.setFont(font);
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderBottom(border);
        style.setBorderRight(border);
        style.setAlignment(align); 
        return style;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeJDialog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnExport2;
    private javax.swing.JButton btnExport3;
    private javax.swing.JButton btnExport4;
    private javax.swing.JComboBox<String> cboKhoaHoc;
    private javax.swing.JComboBox<String> cboYear;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblBangDiem;
    private javax.swing.JTable tblDiemChuyenDe;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblNguoiHoc;
    // End of variables declaration//GEN-END:variables
}
