package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author radames
 */
public class GUI extends JFrame {

    private Container cp;

    private JLabel lbPlaca = new JLabel("Placa");
    private JTextField tfPlaca = new JTextField(20);
    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField(50);
    private JLabel lbMarca = new JLabel("Marca");
    private JTextField tfMarca = new JTextField(50);
    private JLabel lbModelo = new JLabel("Modelo");
    private JTextField tfModelo = new JTextField(50);
    private JLabel lbPeso = new JLabel("Peso");
    private JTextField tfPeso = new JTextField(20);
    private JLabel lbAltura = new JLabel("Altura");
    private JTextField tfAltura = new JTextField(20);
    private JCheckBox cbAtivo = new JCheckBox("Ativo", false);
    private JButton btAdicionar = new JButton("Adicionar");
    private JButton btListar = new JButton("Listar");
    private JButton btBuscar = new JButton("Buscar");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JToolBar toolBar = new JToolBar();
    private JPanel painelNorte = new JPanel();
    private JPanel painelCentro = new JPanel();
    private JPanel painelSul = new JPanel();
    private JTextArea texto = new JTextArea();
    private JScrollPane scroll = new JScrollPane();

    private String acao = "";
    private String chavePrimaria = "";

    private Controle controle = new Controle();
    private Veiculo veiculo = new Veiculo();

    public GUI() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(800, 500);
        setTitle("CRUD Veiculo - Prova");
        setLocationRelativeTo(null);//centro do monitor

        cp = getContentPane();

        cp.setLayout(new BorderLayout());
        cp.add(painelNorte, BorderLayout.NORTH);
        cp.add(painelCentro, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelSul.setLayout(new GridLayout(1, 1));
        texto.setText("\n\n\n\n\n");//5 linhas de tamanho
        scroll.setViewportView(texto);
        painelSul.add(scroll);

        painelNorte.setLayout(new GridLayout(1, 1));
        painelNorte.add(toolBar);

        painelCentro.setLayout(new GridLayout(6, 2));

        painelCentro.add(lbNome);
        painelCentro.add(tfNome);
        painelCentro.add(lbMarca);
        painelCentro.add(tfMarca);
        painelCentro.add(lbModelo);
        painelCentro.add(tfModelo);
        painelCentro.add(lbPeso);
        painelCentro.add(tfPeso);
        painelCentro.add(lbAltura);
        painelCentro.add(tfAltura);
        painelCentro.add(cbAtivo);

        toolBar.add(lbPlaca);
        toolBar.add(tfPlaca);
        toolBar.add(btAdicionar);
        toolBar.add(btBuscar);
        toolBar.add(btListar);
        toolBar.add(btAlterar);
        toolBar.add(btExcluir);
        toolBar.add(btSalvar);
        toolBar.add(btCancelar);

        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        tfNome.setEditable(false);
        tfMarca.setEditable(false);
        tfModelo.setEditable(false);
        tfPeso.setEditable(false);//atributos começam bloqueados
        tfAltura.setEditable(false);//atributos começam bloqueados
        cbAtivo.setEnabled(false);
        texto.setEditable(false);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btAdicionar.setVisible(false);
                if (tfPlaca.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(cp, "Placa não pode ser vazio");
                    tfPlaca.requestFocus();
                    tfPlaca.selectAll();
                } else {
                    chavePrimaria = tfPlaca.getText();//para uso no adicionar
                    veiculo = controle.buscar(Integer.valueOf(tfPlaca.getText()));
                    if (veiculo == null) {//nao encontrou
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfMarca.setText("");
                        tfModelo.setText("");
                        tfPeso.setText("");
                        tfAltura.setText("");
                        cbAtivo.setSelected(false);
                        texto.setText("Não encontrou na lista - pode Adicionar\n\n\n");//limpa o campo texto

                    } else {//encontrou
                        tfNome.setText(veiculo.getNome());
                        tfMarca.setText(veiculo.getMarca());
                        tfModelo.setText(veiculo.getModelo());
                        tfPeso.setText(String.valueOf(veiculo.getPeso()));
                        tfAltura.setText(String.valueOf(veiculo.getAltura()));
                        cbAtivo.setSelected(veiculo.isAtivo());
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        texto.setText("Encontrou na lista - pode Alterar ou Excluir\n\n\n");//limpa o campo texto
                        tfNome.setEditable(false);
                        tfMarca.setEditable(false);
                        tfModelo.setEditable(false);
                        tfPeso.setEditable(false);//atributos começam bloqueados
                        tfAltura.setEditable(false);//atributos começam bloqueados
                        cbAtivo.setEnabled(false);
                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "adicionar";
                tfPlaca.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                tfPlaca.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);

                btAdicionar.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(true);
                tfMarca.setEditable(true);
                tfModelo.setEditable(true);
                tfPeso.setEditable(true);//atributos começam bloqueados
                tfAltura.setEditable(true);//atributos começam bloqueados
                cbAtivo.setEnabled(true);
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "alterar";
                tfPlaca.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                tfPlaca.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(true);
                tfMarca.setEditable(true);
                tfModelo.setEditable(true);
                tfPeso.setEditable(true);//atributos começam bloqueados
                tfAltura.setEditable(true);//atributos começam bloqueados
                cbAtivo.setEnabled(true);
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfPlaca.setEditable(false);
                tfNome.setText("");
                tfMarca.setText("");
                tfModelo.setText("");
                tfPeso.setText("");
                tfAltura.setText("");
                cbAtivo.setSelected(false);
                tfPlaca.requestFocus();
                tfPlaca.selectAll();
                texto.setText("Cancelou\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(false);
                tfMarca.setEditable(false);
                tfModelo.setEditable(false);
                tfPeso.setEditable(false);//atributos começam bloqueados
                tfAltura.setEditable(false);//atributos começam bloqueados
                cbAtivo.setEnabled(false);
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("alterar")) {
                    Veiculo veiculoAntigo = veiculo;
                    veiculo.setNome(tfNome.getText());
                    veiculo.setMarca(tfMarca.getText());
                    veiculo.setModelo(tfModelo.getText());
                    veiculo.setPeso(Float.valueOf(tfPeso.getText()));
                    veiculo.setAltura(Double.valueOf(tfAltura.getText()));
                    veiculo.setAtivo(cbAtivo.isSelected());
                    controle.alterar(veiculo, veiculoAntigo);
                    texto.setText("Registro alterado\n\n\n\n\n");//limpa o campo texto
                } else {//adicionar
                    veiculo = new Veiculo();
                    veiculo.setPlaca(Integer.valueOf(tfPlaca.getText()));
                    veiculo.setNome(tfNome.getText());
                    veiculo.setMarca(tfMarca.getText());
                    veiculo.setModelo(tfModelo.getText());
                    veiculo.setPeso(Float.valueOf(tfPeso.getText()));
                    veiculo.setAltura(Double.valueOf(tfAltura.getText()));                    
                    veiculo.setAtivo(cbAtivo.isSelected());
                   
                    controle.adicionar(veiculo);
                    texto.setText("Foi adicionado um novo registro\n\n\n\n\n");//limpa o campo texto
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfPlaca.setEditable(true);
                 tfNome.setText("");
                tfMarca.setText("");
                tfModelo.setText("");
                tfPeso.setText("");
                tfAltura.setText("");
                tfPlaca.requestFocus();
                tfPlaca.selectAll();
                tfNome.setEditable(false);
                tfMarca.setEditable(false);
                tfModelo.setEditable(false);
                tfPeso.setEditable(false);//atributos começam bloqueados
                tfAltura.setEditable(false);//atributos começam bloqueados
                cbAtivo.setEnabled(false);
            }

        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfPlaca.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                if (JOptionPane.YES_OPTION
                        == JOptionPane.showConfirmDialog(null,
                                "Confirma a exclusão do registro <Nome = " + veiculo.getNome() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.excluir(veiculo);
                }
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfPlaca.setEditable(true);
                tfNome.setText("");
                tfPeso.setText("");
                cbAtivo.setSelected(false);
                cbAtivo.setEnabled(true);
                tfPlaca.requestFocus();
                tfPlaca.selectAll();
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                texto.setText("Excluiu o registro de " 
                        + veiculo.getPlaca()+ " - " 
                        + veiculo.getNome() + "\n\n\n\n\n");//limpa o campo texto
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Veiculo> lt = controle.listar();
                texto.setText("Id - Nome - Salário - Aposentado\n");//limpa o textArea
                for (int i = 0; i < lt.size(); i++) {
                    texto.append(lt.get(i).getPlaca()+ "-"
                            + lt.get(i).getNome() + "-"
                            + lt.get(i).getMarca()+ "-"
                            + lt.get(i).getModelo()+ "-"
                            + lt.get(i).getPeso()+ " - " 
                            + lt.get(i).getAltura()+ " - " 
                            + (lt.get(i).isAtivo()? "Ativo" : "Não ativo") + "\n");
                }
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
            }
        });
        setVisible(true);

    }

}
