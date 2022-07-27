package lrcbuilder;

import java.util.ArrayList;

/**
 *
 * @author aldok
 */
public class LyricEditor extends javax.swing.JDialog {

    private final ArrayList<Lyric> lyrics;

    public LyricEditor(java.awt.Frame parent, boolean modal, ArrayList<Lyric> lyrics) {
        super(parent, modal);
        this.lyrics = lyrics == null ? new ArrayList<>() : lyrics;
        initComponents();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        lyricsTxt = new javax.swing.JTextArea();
        addBtn = new javax.swing.JButton();
        emptyLinesChBx = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lyric importer");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Paste or write the lyrics on the text area below.");

        lyricsTxt.setColumns(20);
        lyricsTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lyricsTxt.setLineWrap(true);
        lyricsTxt.setRows(5);
        lyricsTxt.setWrapStyleWord(true);
        lyricsTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lyricsTxtKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(lyricsTxt);

        addBtn.setBackground(null);
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/add.png"))); // NOI18N
        addBtn.setToolTipText("Add at least one valid line");
        addBtn.setBorderPainted(false);
        addBtn.setEnabled(false);
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        emptyLinesChBx.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emptyLinesChBx.setSelected(new DAOIO<Setting>(new Global().getFilePath(Global.SETTING_FILE)).read().getAddEmptyLines());
        emptyLinesChBx.setText("Add empty lines");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(emptyLinesChBx))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                        .addComponent(addBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emptyLinesChBx)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String[] values = lyricsTxt.getText().split("\n");
        lyrics.clear();

        for (int i = 0; i < values.length; i++) {
            if (emptyLinesChBx.isSelected() || !values[i].isBlank()) {
                lyrics.add(new Lyric(null, values[i]));
            }
        }

        this.dispose();
    }//GEN-LAST:event_addBtnActionPerformed

    private void lyricsTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lyricsTxtKeyReleased
        addBtn.setEnabled(!lyricsTxt.getText().isBlank());
        addBtn.setToolTipText(addBtn.isEnabled() ? "" : "Add at least one valid line");
    }//GEN-LAST:event_lyricsTxtKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JCheckBox emptyLinesChBx;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea lyricsTxt;
    // End of variables declaration//GEN-END:variables
}