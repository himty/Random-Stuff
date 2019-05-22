import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * Some say that people only need the first and last characters of a word to be able
 * to read it (especially when skimming). We shall put this to the test.
 */
public class ReadableMesses extends JFrame {
    private int WIDTH = 500;
    private int HEIGHT = 400;

    private JTextArea inputText;
    private JTextArea outText;

    public static void main(String args[]) {
        ReadableMesses rm = new ReadableMesses();
    }


    public ReadableMesses() {
        setSize(WIDTH, HEIGHT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setOpaque(false);

        JLabel title = new JLabel("Readable Messes");
        title.setFont(new Font("Verdana",1,20));

        JButton convertBtn = new JButton("Convert");
        convertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outText.setText(convertText(inputText.getText()));
            }
        });

        JButton clearBtn = new JButton("  Clear  ");
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputText.setText("");
                outText.setText("");
            }
        });

        JLabel inputLabel = new JLabel("Input:");
        inputLabel.setFont(new Font("Verdana",1,15));

        inputText = new JTextArea();
        inputText.setWrapStyleWord(true);
        inputText.setLineWrap(true);
        JScrollPane inputTextPane = new JScrollPane(inputText,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inputTextPane.getViewport().setPreferredSize(new Dimension(WIDTH,(int) (HEIGHT * 0.4)));

        JLabel outLabel = new JLabel("Output:");
        outLabel.setFont(new Font("Verdana",1,15));

        outText = new JTextArea();
        outText.setEnabled(false);
        outText.setWrapStyleWord(true);
        outText.setLineWrap(true);
        JScrollPane outTextPane = new JScrollPane(outText,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outTextPane.getViewport().setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * 0.4)));

        panel.add(title);
        panel.add(convertBtn);
        panel.add(clearBtn);
        panel.add(inputLabel);
        panel.add(inputTextPane);
        panel.add(outLabel);
        panel.add(outTextPane);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private String convertText(String input) {
        Scanner scan = new Scanner(input);
        StringBuilder result = new StringBuilder();
        while (scan.hasNext()) {
            String nextLine = scan.nextLine();
            for (String nextToken : nextLine.split(" ")) {
                String[] tokens = nextToken.split("\\W");
                String separators = nextToken.replaceAll("[A-Za-z0-9]", "");

                for (int i = 0; i < tokens.length - 1; i++) {
                    result.append(shuffleStr(tokens[i]));
                    result.append(separators.charAt(i));
                }
                result.append(shuffleStr(tokens[tokens.length - 1]));
                if (separators.length() == tokens.length) {
                    result.append(separators.charAt(separators.length() - 1));
                }
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    private String shuffleStr(String s) {
        if (s.length() > 3) {
            java.util.List<String> originalMiddle = new ArrayList<>(java.util.List.of(s.substring(1, s.length() - 1).split("")));
            java.util.List<String> shuffledMiddle = (List) ((ArrayList<String>) originalMiddle).clone();

            int tries = 0;
            do {
                Collections.shuffle(shuffledMiddle);
                tries++;
            } while (shuffledMiddle.equals(originalMiddle) && tries < 5);

            String result = "" + s.charAt(0);
            for (String str : shuffledMiddle) {
                result += str;
            }

            return result + s.charAt(s.length() - 1);
        } else {
            return s;
        }
    }
}