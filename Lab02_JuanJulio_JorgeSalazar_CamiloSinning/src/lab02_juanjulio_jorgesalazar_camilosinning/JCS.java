/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02_juanjulio_jorgesalazar_camilosinning;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class JCS extends javax.swing.JFrame {

    Grafo grafo = new Grafo();
    int contadorIteracion = 0;
//    int vertices = 0;
    Runnable runnable;
    Thread hilo;

    public JCS() {
        initComponents();

        initialSettings.setVisible(true);
        initialSettings.setLocationRelativeTo(null);

        //ubicar UI
        //ubicar panel
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sy = screenSize.height;
        int sx = screenSize.width;
        backgroundPanel.setLocation(0, 0);
        backgroundPanel.setSize(sx, sy);
        backgroundPanel.setLayout(null);
        //Ubicar botones segun resolución de pantalla
        int x = backgroundPanel.getSize().width;
        styleLabel.setLocation(x / 2 - styleLabel.getSize().width / 2, 0);
        numberLabel.setLocation(styleLabel.getLocation().x + styleLabel.getSize().width / 2 - numberLabel.getSize().width / 2, 40);
        //ubicando botones superiores
        int xr = styleLabel.getLocation().x;
        nextButton.setLocation(xr + 150, 30);
        resetButton.setLocation(xr - 120, 30);
        //ubicando boton de close
        closeButton.setLocation(x - closeButton.getSize().width, 0);
        //Botones de reproduccion automatica
        int y = backgroundPanel.getSize().height;
        playStopPanel.setLocation(sx - 15 - playStopPanel.getSize().width, y / 2 - settingsPanel.getHeight() / 2);
        //ubicando settings
        settingsPanel.setLocation(15, y / 2 - settingsPanel.getHeight() / 2);
        //ubicar tablero
        tablero.setLocation(settingsPanel.getLocation().x + settingsPanel.getSize().width + 15, styleLabel.getLocation().y + styleLabel.getSize().height);
        tablero.setSize(sx - tablero.getLocation().x * 2, sy - tablero.getLocation().y - 50);
        //Fin ubicar UI
        loadingLabel.setVisible(false);
    }

    boolean sw = true;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        initialSettings = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nodosTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        withoutMaskButton = new javax.swing.JButton();
        allMaskButton = new javax.swing.JButton();
        maskRandomButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        closeButton1 = new javax.swing.JButton();
        errorLabel = new javax.swing.JTextField();
        errorLabel1 = new javax.swing.JTextField();
        loadingLabel = new javax.swing.JLabel();
        nodeInformation = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        mascarillaLabel = new javax.swing.JLabel();
        caminoLabel = new javax.swing.JLabel();
        backgroundPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        numberLabel = new javax.swing.JLabel();
        styleLabel = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        settingsPanel = new javax.swing.JPanel();
        numberNodesLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        allMaskButton1 = new javax.swing.JButton();
        maskRandomButton1 = new javax.swing.JButton();
        withoutMaskButton1 = new javax.swing.JButton();
        tablero = new javax.swing.JPanel(){
            @Override
            public void paint(Graphics g){
                if(i==1){
                    grafo.InicioGrafo(g);
                    i++;
                    initialSettings.dispose();
                }

            }

        }
        ;
        playStopPanel = new javax.swing.JPanel();
        playButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();

        initialSettings.setMinimumSize(new java.awt.Dimension(391, 605));
        initialSettings.setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 60)); // NOI18N
        jLabel3.setText("Número de nodos");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 340, -1));

        nodosTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nodosTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        nodosTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nodosTextFieldActionPerformed(evt);
            }
        });
        jPanel2.add(nodosTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 310, 30));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 70)); // NOI18N
        jLabel4.setText("Modo");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, -1, 50));

        withoutMaskButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Whithout MAsk.png"))); // NOI18N
        withoutMaskButton.setToolTipText("Without a mask");
        withoutMaskButton.setBorderPainted(false);
        withoutMaskButton.setContentAreaFilled(false);
        withoutMaskButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        withoutMaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withoutMaskButtonActionPerformed(evt);
            }
        });
        jPanel2.add(withoutMaskButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, -1, -1));

        allMaskButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mask2.png"))); // NOI18N
        allMaskButton.setToolTipText("With mask");
        allMaskButton.setBorderPainted(false);
        allMaskButton.setContentAreaFilled(false);
        allMaskButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        allMaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allMaskButtonActionPerformed(evt);
            }
        });
        jPanel2.add(allMaskButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, -1, -1));

        maskRandomButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/aleatorio.png"))); // NOI18N
        maskRandomButton.setToolTipText("Random");
        maskRandomButton.setBorderPainted(false);
        maskRandomButton.setContentAreaFilled(false);
        maskRandomButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        maskRandomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maskRandomButtonActionPerformed(evt);
            }
        });
        jPanel2.add(maskRandomButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, -1, -1));

        startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/start1.png"))); // NOI18N
        startButton.setToolTipText("Start");
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        jPanel2.add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, -1, -1));

        closeButton1.setBackground(new java.awt.Color(255, 255, 255));
        closeButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close sij mancha.png"))); // NOI18N
        closeButton1.setToolTipText("Close");
        closeButton1.setBorderPainted(false);
        closeButton1.setContentAreaFilled(false);
        closeButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(closeButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 80, -1));

        errorLabel.setEditable(false);
        errorLabel.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        errorLabel.setForeground(new java.awt.Color(255, 99, 71));
        errorLabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        errorLabel.setBorder(null);
        errorLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        errorLabel.setFocusable(false);
        errorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                errorLabelMouseClicked(evt);
            }
        });
        errorLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorLabelActionPerformed(evt);
            }
        });
        errorLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                errorLabelKeyReleased(evt);
            }
        });
        jPanel2.add(errorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 310, 30));

        errorLabel1.setEditable(false);
        errorLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        errorLabel1.setForeground(new java.awt.Color(255, 99, 71));
        errorLabel1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        errorLabel1.setBorder(null);
        errorLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        errorLabel1.setFocusable(false);
        errorLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                errorLabel1MouseClicked(evt);
            }
        });
        errorLabel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorLabel1ActionPerformed(evt);
            }
        });
        errorLabel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                errorLabel1KeyReleased(evt);
            }
        });
        jPanel2.add(errorLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 190, 30));

        loadingLabel.setFont(new java.awt.Font("SimSun-ExtB", 0, 36)); // NOI18N
        loadingLabel.setText("Loading...");
        jPanel2.add(loadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 190, 100));

        initialSettings.getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        nodeInformation.setMinimumSize(new java.awt.Dimension(122, 90));
        nodeInformation.setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMinimumSize(new java.awt.Dimension(120, 88));

        jLabel1.setText("Marcarilla:");

        jLabel5.setText("Camino de contagio:");

        mascarillaLabel.setText("None");

        caminoLabel.setText("Coming soon...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(mascarillaLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(caminoLabel)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addContainerGap(16, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mascarillaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(caminoLabel)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout nodeInformationLayout = new javax.swing.GroupLayout(nodeInformation.getContentPane());
        nodeInformation.getContentPane().setLayout(nodeInformationLayout);
        nodeInformationLayout.setHorizontalGroup(
            nodeInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        nodeInformationLayout.setVerticalGroup(
            nodeInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JCS.MAXIMIZED_BOTH);
        setUndecorated(true);
        getContentPane().setLayout(null);

        backgroundPanel.setBackground(new java.awt.Color(255, 255, 255));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setBackground(new java.awt.Color(255, 255, 255));
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close2.png"))); // NOI18N
        closeButton.setToolTipText("Close");
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        closeButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                closeButtonKeyPressed(evt);
            }
        });
        backgroundPanel.add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 0, 130, 140));

        numberLabel.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 48)); // NOI18N
        numberLabel.setText("0");
        numberLabel.setToolTipText("Contador");
        numberLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numberLabelMouseClicked(evt);
            }
        });
        backgroundPanel.add(numberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 40, 40, -1));

        styleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/contador.png"))); // NOI18N
        backgroundPanel.add(styleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 130, 130));

        resetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reset.png"))); // NOI18N
        resetButton.setToolTipText("Reset");
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        backgroundPanel.add(resetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 110, -1));

        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/play.png"))); // NOI18N
        nextButton.setToolTipText("Next");
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextButtonMouseClicked(evt);
            }
        });
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        backgroundPanel.add(nextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 80, -1));

        settingsPanel.setBackground(new java.awt.Color(255, 255, 255));
        settingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Settings", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tw Cen MT Condensed", 0, 24))); // NOI18N
        settingsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        numberNodesLabel.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        numberNodesLabel.setForeground(new java.awt.Color(102, 102, 102));
        numberNodesLabel.setText("0");
        settingsPanel.add(numberNodesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/partes.png"))); // NOI18N
        jLabel2.setToolTipText("Number of nodes");
        settingsPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 90, 80));

        allMaskButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mask2.png"))); // NOI18N
        allMaskButton1.setToolTipText("With a mask");
        allMaskButton1.setBorderPainted(false);
        allMaskButton1.setContentAreaFilled(false);
        allMaskButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allMaskButton1ActionPerformed(evt);
            }
        });
        settingsPanel.add(allMaskButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 90, 70));

        maskRandomButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/aleatorio.png"))); // NOI18N
        maskRandomButton1.setToolTipText("Random");
        maskRandomButton1.setBorderPainted(false);
        maskRandomButton1.setContentAreaFilled(false);
        maskRandomButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maskRandomButton1ActionPerformed(evt);
            }
        });
        settingsPanel.add(maskRandomButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 90, 70));

        withoutMaskButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Whithout MAsk.png"))); // NOI18N
        withoutMaskButton1.setToolTipText("Without mask");
        withoutMaskButton1.setBorderPainted(false);
        withoutMaskButton1.setContentAreaFilled(false);
        withoutMaskButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withoutMaskButton1ActionPerformed(evt);
            }
        });
        settingsPanel.add(withoutMaskButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 90, 60));

        backgroundPanel.add(settingsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 90, 200));

        tablero.setBackground(new java.awt.Color(255, 255, 255));
        tablero.setAutoscrolls(true);
        tablero.setOpaque(false);
        tablero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableroMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableroMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableroMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout tableroLayout = new javax.swing.GroupLayout(tablero);
        tablero.setLayout(tableroLayout);
        tableroLayout.setHorizontalGroup(
            tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        tableroLayout.setVerticalGroup(
            tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        backgroundPanel.add(tablero, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 1110, 450));

        playStopPanel.setBackground(new java.awt.Color(255, 255, 255));
        playStopPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Play/Stop", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tw Cen MT Condensed", 0, 24))); // NOI18N
        playStopPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/play1.png"))); // NOI18N
        playButton.setToolTipText("Play");
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        playStopPanel.add(playButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 90, 70));

        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/stop.png"))); // NOI18N
        stopButton.setToolTipText("Stop");
        stopButton.setBorderPainted(false);
        stopButton.setContentAreaFilled(false);
        stopButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        playStopPanel.add(stopButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 90, 70));

        backgroundPanel.add(playStopPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 270, 90, 200));

        getContentPane().add(backgroundPanel);
        backgroundPanel.setBounds(0, 0, 0, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int i = 1;
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed

        sw = false;
    }//GEN-LAST:event_stopButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        boolean sw2 = false, sw1 = false;
        //validaciones
        try {
            errorLabel.setText("");
            grafo.cantidadNodos = Integer.parseInt(nodosTextField.getText());
            Graficar.Radio = 20 * 10 / Integer.parseInt(nodosTextField.getText()) + 20;
            if (grafo.cantidadNodos <= 1) {
                errorLabel.setText("Inserte un numero valido");
            } else {
                sw2 = true;
                errorLabel.setText("");
            }
            if (grafo.modoGrafo != -1) {
                sw1 = true;
                errorLabel1.setText("");
            } else {
                errorLabel1.setText("Seleccione un modo");
            }
            if (sw2 && sw1) {
                startButton.setVisible(false);
                loadingLabel.setVisible(true);
                setVisible(true);
                switch (grafo.modoGrafo) {
                    case 0:
                        withoutMaskButton1.setVisible(true);
                        allMaskButton1.setVisible(false);
                        maskRandomButton1.setVisible(false);
                        break;
                    case 1:
                        withoutMaskButton1.setVisible(false);
                        allMaskButton1.setVisible(true);
                        maskRandomButton1.setVisible(false);
                        break;
                    default:
                        withoutMaskButton1.setVisible(false);
                        allMaskButton1.setVisible(false);
                        maskRandomButton1.setVisible(true);
                        break;
                }
                numberNodesLabel.setText(nodosTextField.getText());
                nodosTextField.setText("");
                withoutMaskButton.setEnabled(true);
                maskRandomButton.setEnabled(true);
                allMaskButton.setEnabled(true);
            }
        } catch (NumberFormatException e) {
            errorLabel.setText("Inserte un numero valido");
        }

    }//GEN-LAST:event_startButtonActionPerformed

    private void withoutMaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withoutMaskButtonActionPerformed

        withoutMaskButton.setEnabled(false);
        maskRandomButton.setEnabled(true);
        allMaskButton.setEnabled(true);
        grafo.modoGrafo = 0;

    }//GEN-LAST:event_withoutMaskButtonActionPerformed

    private void maskRandomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maskRandomButtonActionPerformed

        withoutMaskButton.setEnabled(true);
        maskRandomButton.setEnabled(false);
        allMaskButton.setEnabled(true);
        grafo.modoGrafo = 2;

    }//GEN-LAST:event_maskRandomButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        sw = true;
        runnable = new Runnable() {
            @Override
            public void run() {
                // Esto se ejecuta en segundo plano una única vez
                while (sw) {
                    // Pero usamos un truco y hacemos un ciclo infinito
                    try {
                        // En él, hacemos que el hilo duerma
                        Thread.sleep(3500);
                        // Y después realizamos las operaciones
                        nextButton.doClick();
                        // Así, se da la impresión de que se ejecuta cada cierto tiempo
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        hilo = new Thread(runnable);
        hilo.start();

    }//GEN-LAST:event_playButtonActionPerformed

    private void nodosTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nodosTextFieldActionPerformed

    }//GEN-LAST:event_nodosTextFieldActionPerformed

    private void allMaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allMaskButtonActionPerformed

        withoutMaskButton.setEnabled(true);
        maskRandomButton.setEnabled(true);
        allMaskButton.setEnabled(false);
        grafo.modoGrafo = 1;

    }//GEN-LAST:event_allMaskButtonActionPerformed

    private void closeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButton1ActionPerformed

        System.exit(0);

    }//GEN-LAST:event_closeButton1ActionPerformed

    private void errorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_errorLabelMouseClicked

    }//GEN-LAST:event_errorLabelMouseClicked

    private void errorLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorLabelActionPerformed

    }//GEN-LAST:event_errorLabelActionPerformed

    private void errorLabelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_errorLabelKeyReleased

    }//GEN-LAST:event_errorLabelKeyReleased

    private void errorLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_errorLabel1MouseClicked

    }//GEN-LAST:event_errorLabel1MouseClicked

    private void errorLabel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorLabel1ActionPerformed

    }//GEN-LAST:event_errorLabel1ActionPerformed

    private void errorLabel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_errorLabel1KeyReleased

    }//GEN-LAST:event_errorLabel1KeyReleased

    private void withoutMaskButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withoutMaskButton1ActionPerformed

    }//GEN-LAST:event_withoutMaskButton1ActionPerformed

    private void allMaskButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allMaskButton1ActionPerformed

    }//GEN-LAST:event_allMaskButton1ActionPerformed

    private void maskRandomButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maskRandomButton1ActionPerformed

    }//GEN-LAST:event_maskRandomButton1ActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed

        setVisible(false);
        initialSettings.setVisible(true);
        startButton.setVisible(true);
        loadingLabel.setVisible(false);
        i = 1;
        Grafo.sw = true;
        contadorIteracion = 0;
        numberLabel.setText(contadorIteracion + "");
        grafo.Infectados = null;
    }//GEN-LAST:event_resetButtonActionPerformed

    private void closeButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_closeButtonKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

    }//GEN-LAST:event_closeButtonKeyPressed

    private void tableroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableroMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableroMouseEntered

    private void nextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_nextButtonMouseClicked

    private void tableroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableroMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableroMouseClicked

    private void tableroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableroMousePressed

        Point point = MouseInfo.getPointerInfo().getLocation();
        int x = point.x;
        int y = point.y;
        y = y - tablero.getLocation().y;
        x = x - tablero.getLocation().x;
        nodosDibujados p = Graficar.misNodosDibujados;
        while (p != null) {
            if ((y < (p.y + Graficar.Radio / 2)) && (y > (p.y - Graficar.Radio / 2)) && (x > (p.x - Graficar.Radio / 2)) && (x < (p.x + Graficar.Radio / 2))) {
                if (grafo.TieneMascarilla(p.numero)) {
                    mascarillaLabel.setText("Sí");
                    caminoLabel.setText("");

                } else {
                    mascarillaLabel.setText("No");
                    caminoLabel.setText("");

                }
                if (!Graficar.PersonaEnferma(p.numero)) {
                    Grafo.sw1=true;
                    jLabel5.setText("Camino de contagio:");
                    grafo.CaminoMasPeligroso(p.numero - 1);
                    caminoLabel.setText(Grafo.caminomenor);
                }else{
                    Grafo.sw1=false;
                    grafo.proxInfectar(p.numero);
                    jLabel5.setText("Proximo a infectar:");
                    caminoLabel.setText(Grafo.proxNodo+"");
                }

                nodeInformation.setVisible(true);
                nodeInformation.setLocation(x, y);
                break;
            }

            p = p.link;
        }
        System.out.println(Grafo.nodom);

    }//GEN-LAST:event_tableroMousePressed

    private void tableroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableroMouseReleased

        nodeInformation.setVisible(false);

    }//GEN-LAST:event_tableroMouseReleased

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed

        Graphics g = tablero.getGraphics();
        grafo.Iteracion(g, grafo.Adyacencia);
        nodosDibujados p = Graficar.misNodosDibujados;
        while (p != null) {
            if(Graficar.PersonaEnferma(p.numero)){
                System.out.println("Infectado "+p.numero+" si");
            }else{
                 System.out.println("Infectado "+p.numero+" no");
            }
            
            if (Graficar.PersonaEnferma(p.numero)) {
                g.setColor(Color.white);
                g.drawOval((int) p.x - Graficar.Radio / 2, (int) p.y - Graficar.Radio / 2, Graficar.Radio, Graficar.Radio);
                g.setColor(Color.red);
                g.drawOval((int) p.x - Graficar.Radio / 2, (int) p.y - Graficar.Radio / 2, Graficar.Radio, Graficar.Radio);
            }
            p = p.link;
        }
        contadorIteracion++;
        numberLabel.setText(contadorIteracion + "");

    }//GEN-LAST:event_nextButtonActionPerformed

    private void numberLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numberLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_numberLabelMouseClicked

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
            java.util.logging.Logger.getLogger(JCS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JCS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JCS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JCS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JCS().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allMaskButton;
    private javax.swing.JButton allMaskButton1;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JLabel caminoLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton closeButton1;
    private javax.swing.JTextField errorLabel;
    private javax.swing.JTextField errorLabel1;
    private javax.swing.JDialog initialSettings;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel loadingLabel;
    private javax.swing.JLabel mascarillaLabel;
    private javax.swing.JButton maskRandomButton;
    private javax.swing.JButton maskRandomButton1;
    private javax.swing.JButton nextButton;
    private javax.swing.JDialog nodeInformation;
    private javax.swing.JTextField nodosTextField;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JLabel numberNodesLabel;
    private javax.swing.JButton playButton;
    private javax.swing.JPanel playStopPanel;
    private javax.swing.JButton resetButton;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel styleLabel;
    private javax.swing.JPanel tablero;
    private javax.swing.JButton withoutMaskButton;
    private javax.swing.JButton withoutMaskButton1;
    // End of variables declaration//GEN-END:variables
}
