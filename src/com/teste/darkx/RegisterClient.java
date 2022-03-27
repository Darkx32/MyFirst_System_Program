package com.teste.darkx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegisterClient extends JDialog implements ActionListener {
    private JPanel panel = new JPanel();
    private JLabel labelName, labelMail, labelAge;
    private JTextField textFieldName, textFieldMail, textFieldAge;
    private JButton buttonSave = new JButton("Salvar");
    private JButton buttonCancelar = new JButton("Cancelar");
    private List<Contatos> ctn;

    public RegisterClient(JFrame frame, List<Contatos> ctn){
        super(frame);
        this.setTitle("Cadastro");
        this.ctn = ctn;
        this.setResizable(false);
        this.setLocationRelativeTo(frame);

        this.MakeAll();

        this.panel.add(labelName);
        this.panel.add(textFieldName);
        this.panel.add(labelMail);
        this.panel.add(textFieldMail);
        this.panel.add(labelAge);
        this.panel.add(textFieldAge);

        this.panel.add(buttonSave);
        this.panel.add(buttonCancelar);


        this.getContentPane().add(this.panel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void MakeAll(){
        this.labelName = new JLabel("Nome:");
        this.labelName.setFont(new Font(this.labelName.getFont().getName(), Font.BOLD, this.labelName.getFont().getSize()+7));
        this.labelMail = new JLabel("Email:");
        this.labelAge = new JLabel("Idade:");

        this.textFieldName = new JTextField(20);
        this.textFieldAge = new JTextField(20);
        this.textFieldMail = new JTextField(20);

        this.panel.setLayout(new GridLayout(4, 4));

        this.buttonSave.addActionListener(this);
        this.buttonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSave){
            if(VerifyValues()){
                Contatos contato = new Contatos(ctn.size() + 1, this.labelName.getText(), this.labelMail.getText(),
                        Integer.parseInt(this.textFieldAge.getText()));
                try {
                    ctn.add(contato);
                } finally {
                    textFieldAge.setText("");
                    textFieldName.setText("");
                    textFieldMail.setText("");
                    JOptionPane.showMessageDialog(this, "Valores salvos com sucesso!!");
                }

            } else{
                JOptionPane.showMessageDialog(this, "Valores informados não estão corretos.");
            }
        }
        if (e.getSource() == buttonCancelar){
            int n = JOptionPane.showConfirmDialog(this, "Deseja sair?");
            if (n==0){
                this.setVisible(false);
            }
        }
    }

    private boolean VerifyValues(){
        if (!this.textFieldName.getText().isEmpty() && !this.textFieldMail.getText().isEmpty() && !this.textFieldAge.getText().isEmpty()){
            if (this.textFieldMail.getText().contains("@") && !isNumber(textFieldName.getText()) && !isNumber(textFieldMail.getText())){
                return isNumber(textFieldAge.getText());
            }
            return false;
        }
        return false;
    }

    private boolean isNumber(String a){
        try{
            Double.parseDouble(a);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public List<Contatos> Run(){
        this.pack();
        this.setVisible(true);
        return ctn;
    }
}
