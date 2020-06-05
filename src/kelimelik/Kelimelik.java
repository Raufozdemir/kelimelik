/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kelimelik;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.JButton;

/**
 *
 * @author ert
 */
public class Kelimelik extends javax.swing.JFrame {
    char   harfy=' ';
    String deneme = "";
    String kelime = "ALSOLUCANA";
    String harf = "";

    JButton[][] btn = new JButton[16][16];

    /**
     * Creates new form Kelimelik
     */
    public Kelimelik() {
        initComponents();
        //initBTN();
        initBTN2(10, 4, 2, 7);
    }

    public static int ver() {
        int sayi = (int) (Math.random() * 9);
        return sayi;
    }

    public void initBTN2(int oyunAlani, int x2kare, int x3kare, int kullanilmaz) {
        int Aboyut=10;
        int Kboyut=0;
        int Boyut=0;
        int[] ozelKareAlani = new int[x2kare + x3kare + kullanilmaz];
        //ozel alanlari ayarlamak için 
        for (int i = 0; i < ozelKareAlani.length; i++) {
            int a = ver();
            ozelKareAlani[i] = 10 * ver() + ver();
            //ver işlemi 0-9 arasında değer veriyor. 
            //Böylece bir sayısı 10 ile çarpınca elimizde 10 luk sayı oluşacak
            //Bu sayısı bir rakamla toplayınca elimizde 0-99 arası bir sayı oluşacak
        }
        System.out.println(Arrays.toString(ozelKareAlani));

        //Butonları sırası ile yerleştirmek için iki adet for ile yapılacaktır.
        //İlk For döngüsü x ekseni için
        //ikinci For döngüsü Y eksenini kontrol etmek için yapılacaktır.
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <=10; y++) {
                //kontol yeri özel hücre kontrolü
                int kontrol[] = icindeVarmi(ozelKareAlani, 10 * x + y);//Kontol 
                //icindeVarmi fonksiyornu geriye 2 elemanlı dizi döndürüyor.
                //Bu dizinin ilk elamnı 1 olarak gelirse bu oluşacak button bir özel butondur.
                //ikinci gelen indis ise bu elemanın hangi özel button olduğunu söylemektedir. 
                if (1 == kontrol[0]) {
                    //Eğer özel karakter ise
                    System.out.println("ÖZEL KARE x= " + x + " y= " + y);
                    System.out.println("   Dizi İNDİSİ= " + kontrol[1]);

                    //indis durumuna göre buton oluşturma
                    if (kontrol[1] >= 0 && kontrol[1] < x2kare) {

                        System.out.println("        Sari Kare");
                        btnCreate(x, y, Color.yellow, true);

                    } else if (kontrol[1] >= x2kare && kontrol[1] < x2kare + x3kare) {
                        btnCreate(x, y, Color.red, true);

                    } else if (kontrol[1] >= x2kare + x3kare && kontrol[1] < x2kare + x3kare + kullanilmaz) {
                        
                        System.out.println("        Siyah Kare");
                        btnCreate(x, y, Color.black, false);
                    }
                } else {
                    System.out.println("normal KARE x= " + x + " y= " + y);
                    btnCreate(x, y, Color.green, true);
                }

            }
        }
            Aboyut=(Aboyut%2)+(Aboyut/2);
            Kboyut=kelime.length()/2;
            Boyut=Aboyut-Kboyut;
            System.out.println(Boyut+" "+Aboyut+" "+Kboyut);
        for (int i = 0; i < kelime.length(); i++) {
            btn[Boyut+1+i][5].setText(String.valueOf(kelime.charAt(i)));
        }
        
    }

    //Kolay bir şekilde butonları oluşturmamıza yarayan kısa kod
    public void btnCreate(int x, int y, Color renk, boolean olay) {
        btn[x][y] = new JButton(harf);
        btn[x][y].setFont(new Font("Tahoma", Font.PLAIN, 12));
        btn[x][y].setBounds(x * 50, y * 50, 50, 50);
        btn[x][y].setBackground(renk);
        btn[x][y].setForeground(Color.black);
        btn[x][y].setName(Integer.toString(x) + Integer.toString(y));
        jPanel2.add(btn[x][y]);
        if (olay) {
            btn[x][y].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    System.out.println(me.getComponent().getName() + " " + "Tıklandııı");
                    deneme = me.getComponent().getName();
                    Color clr = me.getComponent().getBackground();
                    if (clr == Color.white) {
                        me.getComponent().setBackground(renk);

                    } else {
                        me.getComponent().setBackground(Color.white);
                    }

                }

                @Override
                public void mousePressed(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

            });
        }

    }

    //Kontrol bloğunda kullanılan bir kısım
    public static int[] icindeVarmi(int[] dizi, int degisken) {
        int[] snc = new int[2];
        for (int i = 0; i < dizi.length; i++) {
            if (dizi[i] == degisken) {
                snc[0] = 1;
                snc[1] = i;
                return snc;
            }

        }
        snc[0] = 0;
        snc[1] = dizi.length;
        return snc;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SBT_LBL_OyunBilgileri = new javax.swing.JLabel();
        SBT_LBL_OyunAlani = new javax.swing.JLabel();
        LBL_OyunAlani = new javax.swing.JLabel();
        SBT_LBL_KapaliAlan = new javax.swing.JLabel();
        LBL_KapaliAlan = new javax.swing.JLabel();
        SBT_LBL_KazanmPuani = new javax.swing.JLabel();
        LBL_KazanmPuani = new javax.swing.JLabel();
        SBT_LBL_TopamOyun = new javax.swing.JLabel();
        LBL_TopamOyun = new javax.swing.JLabel();
        SBT_LBL_2xSayisi = new javax.swing.JLabel();
        LBL_2xSayisi = new javax.swing.JLabel();
        SBT_LBL_3xSayisi = new javax.swing.JLabel();
        LBL_3xSayisi = new javax.swing.JLabel();
        SBT_LBL_Oyuncular = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        LST_OyuncuList = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        BTN_Temizle = new javax.swing.JButton();
        BTN_Gönder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SBT_LBL_OyunBilgileri.setFont(new java.awt.Font("Noto Sans", 3, 18)); // NOI18N
        SBT_LBL_OyunBilgileri.setText("Oyun Bilgileri");
        SBT_LBL_OyunBilgileri.setName("SBT_LBL_OyunBilgileri"); // NOI18N

        SBT_LBL_OyunAlani.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        SBT_LBL_OyunAlani.setForeground(new java.awt.Color(0, 0, 0));
        SBT_LBL_OyunAlani.setText("Oyun Alanı");
        SBT_LBL_OyunAlani.setName("SBT_LBL_OyunAlani"); // NOI18N

        LBL_OyunAlani.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        LBL_OyunAlani.setText("16x16");
        LBL_OyunAlani.setName("LBL_OyunAlani"); // NOI18N

        SBT_LBL_KapaliAlan.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        SBT_LBL_KapaliAlan.setForeground(new java.awt.Color(0, 0, 0));
        SBT_LBL_KapaliAlan.setText("Kapalı Alan");
        SBT_LBL_KapaliAlan.setName("SBT_LBL_KapaliAlan"); // NOI18N

        LBL_KapaliAlan.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        LBL_KapaliAlan.setText("16x16");
        LBL_KapaliAlan.setName("LBL_KapaliAlan"); // NOI18N

        SBT_LBL_KazanmPuani.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        SBT_LBL_KazanmPuani.setForeground(new java.awt.Color(0, 0, 0));
        SBT_LBL_KazanmPuani.setText("Kazanma Puanı");
        SBT_LBL_KazanmPuani.setName("SBT_LBL_KazanmPuani"); // NOI18N

        LBL_KazanmPuani.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        LBL_KazanmPuani.setText("16x16");
        LBL_KazanmPuani.setName("LBL_KazanmPuani"); // NOI18N

        SBT_LBL_TopamOyun.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        SBT_LBL_TopamOyun.setForeground(new java.awt.Color(0, 0, 0));
        SBT_LBL_TopamOyun.setText("Toplam Oyun");
        SBT_LBL_TopamOyun.setName("SBT_LBL_TopamOyun"); // NOI18N

        LBL_TopamOyun.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        LBL_TopamOyun.setText("16x16");
        LBL_TopamOyun.setName("LBL_TopamOyun"); // NOI18N

        SBT_LBL_2xSayisi.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        SBT_LBL_2xSayisi.setForeground(new java.awt.Color(0, 0, 0));
        SBT_LBL_2xSayisi.setText("2x Sayısı");
        SBT_LBL_2xSayisi.setName("SBT_LBL_2xSayisi"); // NOI18N

        LBL_2xSayisi.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        LBL_2xSayisi.setText("16x16");
        LBL_2xSayisi.setName("LBL_2xSayisi"); // NOI18N

        SBT_LBL_3xSayisi.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        SBT_LBL_3xSayisi.setForeground(new java.awt.Color(0, 0, 0));
        SBT_LBL_3xSayisi.setText("3x Sayısı");
        SBT_LBL_3xSayisi.setName("SBT_LBL_3xSayisi"); // NOI18N

        LBL_3xSayisi.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        LBL_3xSayisi.setText("16x16");
        LBL_3xSayisi.setName("LBL_3xSayisi"); // NOI18N

        SBT_LBL_Oyuncular.setText("Oyuncular");
        SBT_LBL_Oyuncular.setName("SBT_LBL_Oyuncular"); // NOI18N

        LST_OyuncuList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        LST_OyuncuList.setName("LST_OyuncuList"); // NOI18N
        jScrollPane1.setViewportView(LST_OyuncuList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SBT_LBL_OyunAlani)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LBL_OyunAlani))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SBT_LBL_KapaliAlan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LBL_KapaliAlan))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(SBT_LBL_KazanmPuani)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(LBL_KazanmPuani))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SBT_LBL_TopamOyun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LBL_TopamOyun))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(SBT_LBL_2xSayisi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LBL_2xSayisi))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SBT_LBL_3xSayisi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LBL_3xSayisi))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SBT_LBL_OyunBilgileri)
                            .addComponent(SBT_LBL_Oyuncular))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SBT_LBL_OyunBilgileri)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SBT_LBL_OyunAlani)
                    .addComponent(LBL_OyunAlani))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SBT_LBL_KapaliAlan)
                    .addComponent(LBL_KapaliAlan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SBT_LBL_KazanmPuani)
                    .addComponent(LBL_KazanmPuani))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SBT_LBL_TopamOyun)
                    .addComponent(LBL_TopamOyun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SBT_LBL_2xSayisi)
                    .addComponent(LBL_2xSayisi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SBT_LBL_3xSayisi)
                    .addComponent(LBL_3xSayisi))
                .addGap(18, 18, 18)
                .addComponent(SBT_LBL_Oyuncular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel16.setText("KELİMENİZİ AŞAĞI YAZ SONRA BİRAZ DENE YAZMAYI ÖĞRENEBİLİRSİN ");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        BTN_Temizle.setText("Temizle");

        BTN_Gönder.setText("Gönder");
        BTN_Gönder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_GönderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTN_Temizle)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_Gönder)))
                .addContainerGap(381, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTN_Temizle)
                        .addComponent(BTN_Gönder))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 172, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void BTN_GönderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GönderActionPerformed
        // TODO add your handling code here:
        System.out.println("Gönder Tıklandı");
        if (deneme.equals("99")) {
            System.out.println("if içi");

        }
    }//GEN-LAST:event_BTN_GönderActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Kelimelik.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kelimelik.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kelimelik.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kelimelik.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kelimelik().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Gönder;
    private javax.swing.JButton BTN_Temizle;
    private javax.swing.JLabel LBL_2xSayisi;
    private javax.swing.JLabel LBL_3xSayisi;
    private javax.swing.JLabel LBL_KapaliAlan;
    private javax.swing.JLabel LBL_KazanmPuani;
    private javax.swing.JLabel LBL_OyunAlani;
    private javax.swing.JLabel LBL_TopamOyun;
    private javax.swing.JList<String> LST_OyuncuList;
    private javax.swing.JLabel SBT_LBL_2xSayisi;
    private javax.swing.JLabel SBT_LBL_3xSayisi;
    private javax.swing.JLabel SBT_LBL_KapaliAlan;
    private javax.swing.JLabel SBT_LBL_KazanmPuani;
    private javax.swing.JLabel SBT_LBL_OyunAlani;
    private javax.swing.JLabel SBT_LBL_OyunBilgileri;
    private javax.swing.JLabel SBT_LBL_Oyuncular;
    private javax.swing.JLabel SBT_LBL_TopamOyun;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
