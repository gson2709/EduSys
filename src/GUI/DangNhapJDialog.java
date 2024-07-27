package GUI;

import DAO.NhanVienDAO;
import ENTITY.NhanVien;
import UTILS.Auth;
import UTILS.MsgBox;
import UTILS.XImage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Font;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author admin
 */
public class DangNhapJDialog extends javax.swing.JFrame {

    /**
     * Creates new form DangNhapJDialog
     */
    public DangNhapJDialog() {
        
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(XImage.getAppIcon());
        setTitle("EduSys- Đăng nhập");
    }
    NhanVienDAO nv = new NhanVienDAO();
    public static final Logger logger = Logger.getLogger(DangNhapJDialog.class);
    
    void dangNhap(){
        PropertyConfigurator.configure("src\\log\\log4j.properties");
        logger.info("Nguoi dung dang nhap!.");
        if (txtMaNV.getText().trim().length()==0)
            MsgBox.alert(this, "Mã nhân viên không được để trống");
        else{
            String MaNV = txtMaNV.getText();
            String password = new String(txtMatKhau.getPassword());
            NhanVien nhanvien = nv.selectById(MaNV);
            if (nhanvien==null || !bcrypt(password, nhanvien.getMatKhau())) {
                MsgBox.alert(this, "Sai tên đăng nhập hoặc mật khẩu");
                logger.warn("Ai đó đã nhập đúng mã nhân viên "+nhanvien.getMaNV()+" nhưng sai mật khẩu");
            } 
            else if (nhanvien!=null && !bcrypt(password, nhanvien.getMatKhau())){
                MsgBox.alert(this, "Sai tên đăng nhập hoặc mật khẩu");
                logger.warn("Ai đó đã nhập đúng mã nhân viên "+nhanvien.getMaNV()+" nhưng sai mật khẩu");
            }
            else{
                Auth.user = nhanvien;
                this.dispose();
                new EDUSYSJFrame().setVisible(true);
            }
        }
    }
    boolean bcrypt(String password, String hash){
        boolean maHoa = BCrypt.checkpw(password, hash);
        return maHoa;
    }
    public void forgetPassword(){
        if (txtMaNV.getText().length()==0)
            MsgBox.alert(this, "Bạn chưa đăng nhập");
        else{
            int code = (int) Math.floor(((Math.random() * 899999) + 100000));
            Auth.user = nv.selectById(txtMaNV.getText());
            String username = "truongson9a2nd@gmail.com";
            String password = "ztywchbxjblibdir";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(Auth.user.getEmail())
                );
                message.setSubject("OTP");
                message.setContent("<html><b>"+code+"</b></html>" + " là mã xác thực mật khẩu của bạn", "text/html; charset=utf-8");

                Transport.send(message);
                Date date = new Date();
                int minute = date.getMinutes();
                new QuenMatKhau(code,minute).setVisible(true);
                dispose();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
    void qrCode(){
        try {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result==JFileChooser.APPROVE_OPTION){
                lblQrCode.setText("");
                lblQrCode.setIcon(XImage.readQrCode(chooser.getSelectedFile().getName(), lblQrCode));  
                String path = chooser.getSelectedFile().getPath();
                String qrCode = XImage.getQrCode(path);
                String maNV = qrCode.substring(14);
                if (nv.selectById(maNV)!=null){
                    txtMaNV.setText(maNV);
                }
                else
                    MsgBox.alert(this, "File QR Code không chứa thông tin nhân viên");
            }
        } catch (Exception e) {
            MsgBox.alert(this, "File QR Code không hợp lệ");
            logger.error(e);
            e.printStackTrace();
        }
    }
    void ketThuc(){
        if (MsgBox.confirm(this, "Bạn có muốn kết thúc ứng dụng?"))
            System.exit(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        lblMatKhau = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        lblForget = new javax.swing.JLabel();
        lblQrCode = new javax.swing.JLabel();
        chkShowPassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\OneDrive - Đại học FPT- FPT University\\Pictures\\Screenshots\\Screenshot 2023-09-16 210225.png")); // NOI18N

        lblMaNV.setText("Tên đăng nhập");

        lblMatKhau.setText("Mật khẩu");

        txtMatKhau.setEchoChar('*');

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/key.png"))); // NOI18N
        btnLogin.setText("Ðăng nhập");
        btnLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Exit.png"))); // NOI18N
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });

        lblForget.setText("Quên mật khẩu");
        lblForget.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblForgetMouseMoved(evt);
            }
        });
        lblForget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblForgetMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblForgetMouseExited(evt);
            }
        });

        lblQrCode.setText("Đăng nhập bằng QR Code");
        lblQrCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblQrCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQrCodeMouseClicked(evt);
            }
        });

        chkShowPassword.setText("Show Password");
        chkShowPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkShowPasswordMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMaNV)
                            .addComponent(lblMatKhau)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLogin)
                                .addGap(18, 18, 18)
                                .addComponent(btnKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblForget)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(txtMaNV))
                        .addGap(18, 18, 18)
                        .addComponent(lblQrCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkShowPassword)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblMaNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMatKhau)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblQrCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkShowPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblForget)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        dangNhap();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        ketThuc();
    }//GEN-LAST:event_btnKetThucActionPerformed

    private void lblForgetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetMouseClicked
        forgetPassword();
    }//GEN-LAST:event_lblForgetMouseClicked

    private void lblForgetMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetMouseMoved
        lblForget.setFont(new Font("Segoe UI", 1, 12));
        lblForget.setText("<html><u>Quên mật khẩu</u></html>");
        lblForget.setForeground(Color.blue);
    }//GEN-LAST:event_lblForgetMouseMoved

    private void lblForgetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetMouseExited
        lblForget.setFont(new Font("Segoe UI", 0, 12));
        lblForget.setText("Quên mật khẩu");
        lblForget.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblForgetMouseExited

    private void chkShowPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkShowPasswordMouseClicked
        if (chkShowPassword.isSelected()){
            txtMatKhau.setEchoChar((char)0);
        }
        else
            txtMatKhau.setEchoChar('*');
    }//GEN-LAST:event_chkShowPasswordMouseClicked

    private void lblQrCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQrCodeMouseClicked
        qrCode();
    }//GEN-LAST:event_lblQrCodeMouseClicked

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
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhapJDialog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblForget;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblQrCode;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    // End of variables declaration//GEN-END:variables
}
