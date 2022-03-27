package com.teste.darkx;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements MenuListener, ActionListener {
    private JLabel textInicial, resumeText;
    private final JMenuBar menuBar = new JMenuBar();
    private JMenuItem menuItemCadastrar, menuItemExibir, menuItemSobre, menuItemSair;
    private List<Contatos> contatos = new ArrayList<>();

    public MainWindow(String title){
        super(title);
        this.setResizable(false);

        this.MakeAll();

        Font font = this.textInicial.getFont();
        this.textInicial.setFont(new Font(font.getName(), Font.BOLD, 20));
        this.textInicial.setAlignmentY(CENTER_ALIGNMENT);


        this.resumeText.setFont(new Font(this.resumeText.getFont().getName(), Font.ITALIC, 15));


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(textInicial);
        panel.add(this.resumeText);

        this.setJMenuBar(menuBar);
        this.getContentPane().add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void MakeAll(){
        this.textInicial = new JLabel("Bem vindo ao meu primeiro programa.");
        this.resumeText = new JLabel("<html>Esse programa foi criado no intuito de aprendizagem<br/>" +
                "Então pode conter bugs e erros e até erros de português.<br/><br/>" +
                "Obrigado pela atenção!!</html>");

        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuSobre = new JMenu("Sobre");

        this.menuItemCadastrar = new JMenuItem("Cadastrar");
        this.menuItemExibir = new JMenuItem("Exibir");

        this.menuItemSobre = new JMenuItem("Sobre...");
        this.menuItemSair = new JMenuItem("Sair");

        menuItemCadastrar.addActionListener(this);
        menuItemExibir.addActionListener(this);
        menuItemSobre.addActionListener(this);
        menuItemSair.addActionListener(this);

        menuSobre.addMenuListener(this);
        menuClientes.addMenuListener(this);

        menuClientes.add(menuItemCadastrar);
        menuClientes.add(menuItemExibir);

        menuSobre.add(menuItemSobre);
        menuSobre.add(new JSeparator());
        menuSobre.add(menuItemSair);

        menuBar.add(menuClientes);
        menuBar.add(menuSobre);
    }

    public void Run(){
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void menuSelected(MenuEvent e) {

    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.menuItemSair){
            System.exit(0);
        } if (e.getSource() == this.menuItemCadastrar) {
            RegisterClient a = new RegisterClient(this, contatos);
            contatos = a.Run();
        } if (e.getSource() == this.menuItemExibir){
            ViewValues();
        } if (e.getSource() == this.menuItemSobre) {
            JOptionPane.showMessageDialog(this,
                    "Esse menu foi feito no intuito de aprendizagem\n" +
                            "Pode Conter erros e algumas coisas que no futuro poderei resolver.\n\n" +
                            "Obrigado por instalar!!!");
        }
    }

    private void ViewValues(){
        if (!contatos.isEmpty()){
            StringBuilder Final = new StringBuilder();
            for (Contatos contact : contatos) {
                Final.append("Cliente ").append(contact.ID).append("\n");
                Final.append("  Nome: ").append(contact.name).append("\n");
                Final.append("  Email: ").append(contact.email).append("\n");
                Final.append("  Idade: ").append(contact.idade).append("\n");
            }
            JOptionPane.showMessageDialog(this, Final);
        } else{
            JOptionPane.showMessageDialog(this, "Não existe dados salvos!");
        }
    }
}
