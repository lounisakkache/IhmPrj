package com.mycompany.ihmprj;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
public abstract class Controle {
     protected int MouseListenerCounter;

     abstract protected void Add_Actions_Listener();
     abstract protected void Add_Focus_Listener();
     abstract protected Boolean verifyFields();
     abstract protected void ClearListeners(JTextField TextField);
    protected void SetupGraphics(JTextField Component, Color ForegroundColor, Color BackgroundColor, Border border, String Text){
        Component.setForeground(ForegroundColor);
        Component.setFont(new Font("Verdana", Font.PLAIN+Font.BOLD, 10));
       // Component.setBackground(BackgroundColor);
        Component.setText(Text);
        Component.setBorder(border);
    }
    protected Boolean IsFormatValide(String RegularExpression,String Field){
         String Regex="";
         switch(RegularExpression.toLowerCase()){
             case "password" : Regex="\"^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{4,15}$\"" ;break;
             case "prenom" : ;
             case "nom" : Regex="[a-zA-Z ]" ;break;
             case "pseudo" : Regex="[a-zA-Z0-9 ]" ;break;
             case "email" : Regex="[^@]+@[^\\.]+\\..{2,}" ;break;
             case "phone" : Regex="^[+]*[(]?[0-9]{1,4}[)]?[-\\s\\./0-9]*$" ;break;
         }
         return Pattern.compile(Regex).matcher(Field).matches();
     }
 }

 class SignUPViewController extends Controle implements ActionListener {
    private  Model UserInfo;
    private SignUPView SignUP;
    private List Users;

     public List getUsers() {
         return Users;
     }

     public SignUPViewController(Model M, SignUPView V1) {
        this.SignUP = V1;
        this.UserInfo = M;
        this.Users=new List();
        Users.Push(new Model("Amri","Younes","Anes65","y0un35@ya.ru","+213794147573","Anes65","Anes65"));
        Users.Push(new Model("Akkache","Lounis","Lounis01","Akkche@gmail.com","+213794147573","Lounis2001","Lounis2001"));
        Add_Actions_Listener();
        Add_Focus_Listener();
        this.SignUP.getLoginLabel1().addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                SignUP.setVisible(false);
                new LogInViewController(new LogInView(),Users);
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {

            }
            public void mouseEntered(MouseEvent e) {

            }
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String Cmd = e.getActionCommand();
        switch (Cmd) {
            case "Cancel":
                    ResetAllFields();
                break;
            case "OK":
                    SubmitData();
                     MouseListenerCounter=0;
                    if(verifyFields()==Boolean.TRUE)
                            Users.Push(UserInfo).DisplayUserList();
                    else {
                            JButton Ok=(JButton) e.getSource();
                             Ok.setEnabled(false);
                            Ok.setBackground(Color.lightGray);
                            }
                break;
            case "ShowPassword":  JCheckBox check=(JCheckBox) e.getSource();
                if(check.isSelected())
                     SignUP.getPassField().setEchoChar((char)0);
                else
                    SignUP.getPassField().setEchoChar('*');
                break;
            case "Terms":   check=(JCheckBox) e.getSource();
                if(check.isSelected()) {
                    SignUP.getOKBtn().setEnabled(true);
                    SignUP.getOKBtn().setBackground(SignUP.getANNBtn().getBackground());
                }else {
                    SignUP.getOKBtn().setEnabled(false);
                    SignUP.getOKBtn().setBackground(Color.lightGray);
                }break;
    }}

     protected void Add_Actions_Listener() {
         this.SignUP.getANNBtn().addActionListener(this);
         this.SignUP.getANNBtn().setActionCommand("Cancel");
         this.SignUP.getOKBtn().addActionListener(this);
         this.SignUP.getOKBtn().setActionCommand("OK");
         this.SignUP.getBoxButton().addActionListener(this);
         this.SignUP.getBoxButton().setActionCommand("ShowPassword");
         this.SignUP.getBoxButton1().addActionListener(this);
         this.SignUP.getBoxButton1().setActionCommand("Terms");
     }

     protected void Add_Focus_Listener() {
         Border Bottomborder=BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
         this.SignUP.getNomField().addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                SignUP.getNomField().setBorder(Bottomborder);
                System.out.println("NomField FocusGained");
            }
            public void focusLost(FocusEvent e) {
                SignUP.getNomField().setBorder(null);
                System.out.println("NomField FocusLost");

            }
        });
         this.SignUP.getPrenomField().addFocusListener(new FocusListener() {
             public void focusGained(FocusEvent e) {
                 SignUP.getPrenomField().setBorder(Bottomborder);
                 System.out.println("PrenomField FocusGained");
             }
             public void focusLost(FocusEvent e) {
                 SignUP.getPrenomField().setBorder(null);
                 System.out.println("PrenomField FocusLost");

             }
         });
         this.SignUP.getPseudoField().addFocusListener(new FocusListener() {
             public void focusGained(FocusEvent e) {
                 SignUP.getPseudoField().setBorder(Bottomborder);
                 System.out.println("PseudoField FocusGained");
             }
             public void focusLost(FocusEvent e) {
                 SignUP.getPseudoField().setBorder(null);
                 System.out.println("PseudoField FocusLost");

             }
         });
         this.SignUP.getEmailField().addFocusListener(new FocusListener() {
             public void focusGained(FocusEvent e) {
                 SignUP.getEmailField().setBorder(Bottomborder);
                 System.out.println("EmailField FocusGained");
             }
             public void focusLost(FocusEvent e) {
                 SignUP.getEmailField().setBorder(null);
                 System.out.println("EmailField FocusLost");

             }
         });
         this.SignUP.getTeleField().addFocusListener(new FocusListener() {
             public void focusGained(FocusEvent e) {
                 SignUP.getTeleField().setBorder(Bottomborder);
                 System.out.println("PhoneField FocusGained");
             }
             public void focusLost(FocusEvent e) {
                 SignUP.getTeleField().setBorder(null);
                 System.out.println("PhoneField FocusLost");

             }
         });
         this.SignUP.getPassField().addFocusListener(new FocusListener() {
             public void focusGained(FocusEvent e) {
                 SignUP.getPassField().setBorder(Bottomborder);
                 System.out.println("PasswordField FocusGained");
             }
             public void focusLost(FocusEvent e) {
                 SignUP.getPassField().setBorder(null);
                 System.out.println("Password FocusLost");

             }
         });
         this.SignUP.getConfField().addFocusListener(new FocusListener() {
             public void focusGained(FocusEvent e) {
                 SignUP.getConfField().setBorder(Bottomborder);
                 System.out.println("ConfirmationField FocusGained");
             }
             public void focusLost(FocusEvent e) {
                 SignUP.getConfField().setBorder(null);
                 System.out.println("ConfirmationField FocusLost");

             }
         });

     }

     private void SubmitData() {
        UserInfo.setNom(SignUP.getNomField().getText());
        UserInfo.setPrenom(SignUP.getPrenomField().getText());
        UserInfo.setPseudo(SignUP.getPseudoField().getText());
        UserInfo.setEmail(SignUP.getEmailField().getText());
        UserInfo.setTele(SignUP.getTeleField().getText());
        UserInfo.setMotPass(new String(SignUP.getPassField().getPassword()));
        UserInfo.setConfirmation(new String(SignUP.getConfField().getPassword()));
    }

    @Override
    protected Boolean verifyFields() {
        Boolean VerificationResult= Boolean.TRUE;
        Border border =BorderFactory.createLineBorder(Color.red, 2,true);
        Color redColor=new Color(255, 230, 230);
                                                         // Verification de la conditions associées aux champs
        if (UserInfo.getNom().length() == 0 && UserInfo.getPrenom().length() == 0 && UserInfo.getPseudo().length() == 0) {
            SetupGraphics(SignUP.getPseudoField(),Color.red,redColor,border,"Pseudo Field is required.");
            ClearListeners (SignUP.getPseudoField());
            VerificationResult=Boolean.FALSE;
        }

        if (UserInfo.getTele().length() == 0 && UserInfo.getEmail().length() == 0 ) {
            SetupGraphics(SignUP.getEmailField(),Color.red,redColor,border,"Email Field is required.");
            ClearListeners (SignUP.getEmailField());
            SetupGraphics(SignUP.getTeleField(),Color.red,redColor,border,"Phone Field is required.");
            ClearListeners (SignUP.getTeleField());
            VerificationResult=Boolean.FALSE;
        }

        if (UserInfo.getMotPass().length() == 0) {
            SignUP.getPassField().setBackground(new Color(255, 230, 230));
            SignUP.getPassMsg().setForeground(Color.red);
            SignUP.getPassMsg().setText("Password Field is Required");
            SignUP.getPassField().setBorder(border);
             ClearListeners (SignUP.getPassField());
            VerificationResult=Boolean.FALSE;
        } else if (!IsFormatValide("password",UserInfo.getMotPass())) {
            SignUP.getPassField().setBackground(Color.white);
            SignUP.getPassField().setBorder(border);
            SignUP.getPassMsg().setForeground(Color.blue);
            SignUP.getPassMsg().setText("Wrong Format !.");
            ClearListeners (SignUP.getPassField());
            VerificationResult=Boolean.FALSE;
        }

        if (UserInfo.getConfirmation().length() == 0) {
            SignUP.getConfField().setBackground(new Color(255, 230, 230));
            SignUP.getConfField().setBorder(border);
            SignUP.getConfPassMsg().setForeground(Color.red);
            SignUP.getConfPassMsg().setText("Confirmation is Required");
            ClearListeners (SignUP.getConfField());
            VerificationResult=Boolean.FALSE;
        } else if (!UserInfo.getConfirmation().equals(UserInfo.getMotPass())) {
            SignUP.getConfField().setBackground(new Color(255, 230, 230));
            SignUP.getConfField().setBorder(border);
            SignUP.getConfPassMsg().setForeground(Color.red);
            SignUP.getConfPassMsg().setText("Wrong Password .");

            ClearListeners (SignUP.getConfField());
            VerificationResult=Boolean.FALSE;
        }
                                                                // Verification de la Format des Champs
        if (UserInfo.getNom().length() != 0 &&  !IsFormatValide("NOM",UserInfo.getNom())) {
            SetupGraphics(SignUP.getNomField(),Color.blue,Color.white,border,"Wrong Format !.");
            ClearListeners (SignUP.getNomField());
            VerificationResult=Boolean.FALSE;
        }

        if (UserInfo.getPrenom().length() != 0 && !IsFormatValide("PRENOM",UserInfo.getPrenom())) {
            SetupGraphics(SignUP.getPrenomField(),Color.blue,Color.white,border,"Wrong Format !.");
            ClearListeners (SignUP.getPrenomField());
            VerificationResult=Boolean.FALSE;
        }

        if (UserInfo.getPseudo().length() != 0 && !IsFormatValide("PSEUDO",UserInfo.getPseudo())) {
            SetupGraphics(SignUP.getPseudoField(),Color.blue,Color.white,border,"Wrong Format !.");
            ClearListeners (SignUP.getPseudoField());
            VerificationResult=Boolean.FALSE;
        }

        if (UserInfo.getEmail().length() != 0 && !IsFormatValide("EMAIL",UserInfo.getEmail())) {
            SetupGraphics(SignUP.getEmailField(),Color.blue,Color.white,border,"Wrong Format !.");
            ClearListeners (SignUP.getEmailField());
            VerificationResult=Boolean.FALSE;
        }

        if (UserInfo.getTele().length() != 0 && !IsFormatValide("PHONE",UserInfo.getTele())) {
            SetupGraphics(SignUP.getTeleField(),Color.blue,Color.white,border,"Wrong Format !.");
            ClearListeners (SignUP.getTeleField());
            VerificationResult=Boolean.FALSE;
        }

            // Verification d'Existence d'un Utilisateur
        int VerificationUtilisateur=Users.VerifyExistance(UserInfo.getPseudo(),UserInfo.getEmail());
        if (VerificationUtilisateur==1){
            SetupGraphics(SignUP.getPseudoField(),Color.blue,Color.white,border,"Pseudo Already Exist !.");
            ClearListeners (SignUP.getPseudoField());
            VerificationResult=Boolean.FALSE;
        }else if (VerificationUtilisateur>1){
            SetupGraphics(SignUP.getEmailField(),Color.blue,Color.white,border,"Email Already Exist!.");
            ClearListeners (SignUP.getEmailField());
            VerificationResult=Boolean.FALSE;
        }
        return VerificationResult;
    }

    @Override
     protected void ClearListeners (JTextField TextField) {
         MouseListenerCounter++;
        MouseListener Click= new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("ClearListeners  Field");
                JTextField component=(JTextField) e.getComponent();
                component.setText("");
                component.setForeground(Color.black);
                component.setBackground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("MouseListener Removed"+MouseListenerCounter);
                JTextField component=(JTextField) e.getComponent();
                component.setBorder(null);
                component.setFont(new Font("DialogInput", Font.PLAIN, 14));
                component.removeMouseListener(this);
                if(--MouseListenerCounter==0) {
                    SignUP.getOKBtn().setEnabled(true);
                    SignUP.getOKBtn().setBackground(SignUP.getANNBtn().getBackground());
                }
                if (component== SignUP.getPassField())
                    SignUP.getPassMsg().setText("");
                if (component== SignUP.getConfField())
                    SignUP.getConfPassMsg().setText("");
            }
        };
         TextField.addMouseListener(Click);
    }

     private void ResetAllFields() {
        SignUP.dispose();
        SignUP = new SignUPView();
        new SignUPViewController(UserInfo, SignUP);
    }
}

 class LogInViewController extends Controle implements ActionListener{
     private  String Email,Pseudo,Password;
     private  LogInView LogIn;
     private  List UserList;
     private  int MouseListenerCounter;

     public LogInViewController(LogInView V1,List Users){
         this.LogIn=V1;
         this.UserList=Users;
         Add_Actions_Listener();
     }
     public void actionPerformed(ActionEvent e) {
         String Cmd = e.getActionCommand();
         switch (Cmd) {
             case "Connect":
                 MouseListenerCounter=0;
                 if(verifyFields()==Boolean.FALSE){
                     JButton Ok=(JButton) e.getSource();
                     Ok.setEnabled(false);
                     Ok.setBackground(Color.lightGray);
                 }else{
                    if (UserList.VerifyLogInRequest(this.Email,this.Pseudo,this.Password)){
                        //new view
                        this.LogIn.dispose();
                    }
                 }
                 break;
             case "Cancel": LogIn.dispose(); break;
             case "ShowPassword":
                 JCheckBox check=(JCheckBox) e.getSource();
                 if(check.isSelected())
                     LogIn.getPasswordField().setEchoChar((char)0);
                 else
                     LogIn.getPasswordField().setEchoChar('*');
                 break;
         }
     }

     protected void Add_Actions_Listener() {
         this.LogIn.getBoxButton().addActionListener(this);
         this.LogIn.getBoxButton().setActionCommand("ShowPassword");
         this.LogIn.getConnectButton().addActionListener(this);
         this.LogIn.getConnectButton().setActionCommand("Connect");
         this.LogIn.getPasswordField().setEchoChar('*');
         this.LogIn.getSignUP().setForeground(Color.BLUE.darker());
         this.LogIn.getSignUP().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     }

     protected void Add_Focus_Listener() {

     }

     public Boolean verifyFields(){
         Boolean VerificationResult= Boolean.TRUE;
         Border border =BorderFactory.createLineBorder(Color.BLUE, 5);
         Color redColor=new Color(255, 230, 230);
         if((LogIn.getEmailField().getText().length()==0)){
             SetupGraphics(LogIn.getEmailField(),Color.red,redColor,border,"Email or Pseudo is Required");
             MouseListenerCounter++;
             ClearListeners (LogIn.getEmailField());
             VerificationResult=Boolean.FALSE;
         }else if (IsFormatValide("EMAIL",LogIn.getEmailField().getText())){
             this.Email=new String(LogIn.getEmailField().getText());
             this.Pseudo=null;
         }else if (IsFormatValide("PSEUDO",LogIn.getEmailField().getText())){
             this.Email=null;
             this.Pseudo=new String(LogIn.getEmailField().getText());
         }else {
             SetupGraphics(LogIn.getEmailField(),Color.blue,Color.white,border,"Wrong Format !.");
             MouseListenerCounter++;
             ClearListeners (LogIn.getEmailField());
             VerificationResult=Boolean.FALSE;
         }

         this.Password=new String(LogIn.getPasswordField().getPassword());
         if(Password.length()==0){
             SetupGraphics(LogIn.getPasswordField(),Color.red,redColor,border,"Password is Required");
             LogIn.getPasswordField().setEchoChar((char)0);
             ClearListeners (LogIn.getPasswordField());
             VerificationResult=Boolean.FALSE;
             MouseListenerCounter++;
         }
         else if( !IsFormatValide("PASSWORD",Password)){
             SetupGraphics(LogIn.getPasswordField(),Color.blue,redColor,border,"Wrong Format !.");
             LogIn.getPasswordField().setEchoChar((char)0);
             ClearListeners (LogIn.getPasswordField());
             VerificationResult=Boolean.FALSE;
             MouseListenerCounter++;
         }
        return VerificationResult;
     }

     public void ClearListeners (JTextField TextField) {
         MouseListener Click= new MouseListener() {
             @Override
             public void mouseClicked(MouseEvent e) {
             }
             @Override
             public void mousePressed(MouseEvent e) {

             }
             @Override
             public void mouseReleased(MouseEvent e) {

             }
             @Override
             public void mouseEntered(MouseEvent e) {
                 System.out.println("ClearListeners from Field");
                 JTextField component= (JTextField) e.getComponent();
                 component.setText("");
                 component.setForeground(Color.black);
                 component.setBackground(Color.white);
             }
             @Override
             public void mouseExited(MouseEvent e) {
                 System.out.println("MouseListener Removed");
                 JTextField component= (JTextField) e.getComponent();
                 component.removeMouseListener(this);
                 if ((--MouseListenerCounter)==0){
                     LogIn.getConnectButton().setEnabled(true);
                     LogIn.getConnectButton().setBackground(new Color(255, 255, 255));
                 }
                 if (component== LogIn.getPasswordField()){
                    LogIn.getPasswordField().setText("");
                    LogIn.getPasswordField().setEchoChar('*');
                }
             }
         };
         TextField.addMouseListener(Click);
     }
}