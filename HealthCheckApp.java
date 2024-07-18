import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HealthCheckApp {
    private static Map<String, String[]> responses = new HashMap<>();
    private static Random random = new Random();

    static {
        responses.put("悪い", new String[]{
            "大丈夫ですか？大学はお休みですね。",
            "酒を飲むな",
            "お大事にしてください。大学は休みなさい"
        });
        responses.put("眠い", new String[]{
            "あと3時間寝ましょう",
            "午後は休みましょう",
            "気合で大学にいきなさい",
            "恥を知れ、恥を、大学に行け"
        });
        responses.put("いい", new String[]{
            "大学へ行こう",
            "大学は最高",
            "休むなんてゆるさない"
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("体調チェックアプリ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("現在の体調:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        String[] conditions = {"悪い", "眠い", "いい"};
        JComboBox<String> conditionList = new JComboBox<>(conditions);
        conditionList.setBounds(100, 20, 165, 25);
        panel.add(conditionList);

        JButton checkButton = new JButton("チェック");
        checkButton.setBounds(10, 80, 150, 25);
        panel.add(checkButton);

        JLabel responseLabel = new JLabel("");
        responseLabel.setBounds(10, 110, 300, 25);
        panel.add(responseLabel);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCondition = (String) conditionList.getSelectedItem();
                if (responses.containsKey(selectedCondition)) {
                    String[] possibleResponses = responses.get(selectedCondition);
                    String response = possibleResponses[random.nextInt(possibleResponses.length)];
                    responseLabel.setText(response);
                } else {
                    responseLabel.setText("すみません、その体調の状態には対応していません。");
                }
            }
        });
    }
}
