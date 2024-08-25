
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculadora extends JFrame implements ActionListener {

    private JTextField primeiroNumeroField;
    private JTextField segundoNumeroField;
    private JTextField resultadoField;
    private JLabel primeiroNumeroLabel;
    private JLabel segundoNumeroLabel;
    private JLabel operacaoLabel;
    private JLabel resultadoLabel;
    private JComboBox<String> operadorComboBox;
    private JPanel panel;
    private JButton calcularButton;
    private JButton novoCalculoButton;

    public Calculadora() {
        super("Calculadora");

        primeiroNumeroField = new JTextField();
        segundoNumeroField = new JTextField();
        resultadoField = new JTextField();
        primeiroNumeroLabel = new JLabel("Inserir o primeiro número");
        segundoNumeroLabel = new JLabel("Inserir o segundo número");
        operacaoLabel = new JLabel("Selecione a operação");
        resultadoLabel = new JLabel("Resultado");

        operadorComboBox = new JComboBox<>();
        operadorComboBox.addItem("+");
        operadorComboBox.addItem("-");
        operadorComboBox.addItem("*");
        operadorComboBox.addItem("/");

        calcularButton = new JButton("Calcular");
        novoCalculoButton = new JButton("Novo Cálculo");

        panel = new JPanel();

        panel.add(primeiroNumeroLabel);
        panel.add(primeiroNumeroField);
        panel.add(segundoNumeroLabel);
        panel.add(segundoNumeroField);
        panel.add(operacaoLabel);
        panel.add(operadorComboBox);
        panel.add(resultadoLabel);
        panel.add(resultadoField);
        panel.add(calcularButton);
        panel.add(novoCalculoButton);

        setContentPane(panel);

        GridLayout layout = new GridLayout(5, 2, 10, 10);
        panel.setLayout(layout);

        resultadoField.setEditable(false);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        novoCalculoButton.addActionListener(this);
        calcularButton.addActionListener(this);

        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(windowListener);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        Object origem = evento.getSource();

        if (origem == novoCalculoButton) {
            primeiroNumeroField.setText("");
            segundoNumeroField.setText("");
            resultadoField.setText("");
            operadorComboBox.setSelectedItem("+");
        }

        if (origem == calcularButton) {
            try {
                double primeiroNumero = Double.parseDouble(primeiroNumeroField.getText());
                double segundoNumero = Double.parseDouble(segundoNumeroField.getText());

                String operador = (String) operadorComboBox.getSelectedItem();
                double resultado = 0;

                switch (operador) {
                    case "+":
                        resultado = primeiroNumero + segundoNumero;
                        break;
                    case "-":
                        resultado = primeiroNumero - segundoNumero;
                        break;
                    case "*":
                        resultado = primeiroNumero * segundoNumero;
                        break;
                    case "/":
                        if (segundoNumero == 0) {
                            resultadoField.setText("Erro: Divisão por zero");
                            return;
                        }
                        resultado = primeiroNumero / segundoNumero;
                        break;
                }

                resultadoField.setText(String.valueOf(resultado));
            } catch (NumberFormatException e) {
                resultadoField.setText("Erro: Entrada inválida");
            }
        }
    }

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        calc.setVisible(true);
        calc.setSize(500, 300);
        calc.setResizable(true);
    }
}