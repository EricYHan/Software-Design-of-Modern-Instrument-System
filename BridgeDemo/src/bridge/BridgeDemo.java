package bridge;

import javax.swing.*;//GUI Packge
import java.awt.*;//java视窗
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BridgeDemo extends JFrame{
    private JPanel phoneSelectPanel;
    private JLabel phoneTypeLabel;
    static JButton blushGold;
    static JButton seaBlue;
    static JButton iceWhite;
    static JButton goldBlack;
    static JComboBox phoneTypeCombo = new JComboBox();

    public BridgeDemo() {
        phoneSelectPanel = new JPanel();
        phoneTypeLabel = new JLabel("请选择手机类型",JLabel.CENTER);

        phoneTypeCombo.addItem("Huawei_P30");
        phoneTypeCombo.addItem("Huawei_P40");
        blushGold = new JButton("BlushGold");
        seaBlue = new JButton("SeaBlue");
        iceWhite = new JButton("LightBlue");
        goldBlack = new JButton("GoldBlack");
        blushGold.addActionListener(new SelectThemeListener());
        seaBlue.addActionListener(new SelectThemeListener());
        iceWhite.addActionListener(new SelectThemeListener());
        goldBlack.addActionListener(new SelectThemeListener());

        phoneSelectPanel.setLayout(new GridLayout(6, 1));
        phoneSelectPanel.add(phoneTypeLabel);
        phoneSelectPanel.add(phoneTypeCombo);
        phoneSelectPanel.add(blushGold);
        phoneSelectPanel.add(seaBlue);
        phoneSelectPanel.add(iceWhite);
        phoneSelectPanel.add(goldBlack);

        setBounds(300, 300, 400, 400);
        setContentPane(phoneSelectPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        //phoneSelect();
        BridgeDemo phoneSelect = new BridgeDemo();
        phoneSelect.setVisible(true);
    }
    public static void show(String phoneName) {
        JFrame jFrame = new JFrame("BridgeDemo");
        Container contentPane = jFrame.getContentPane();
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel(new ImageIcon("src/bridge/" + phoneName + ".png"));
        jPanel.setLayout(new GridLayout(1, 1));
        jPanel.setBorder(BorderFactory.createTitledBorder(phoneName));
        jPanel.add(jLabel);
        contentPane.add(jPanel, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

class SelectThemeListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String phoneType = "bridge." + BridgeDemo.phoneTypeCombo.getSelectedItem().toString();
        String themeType = "bridge.";
        if (e.getSource().equals(BridgeDemo.blushGold)) {
            themeType += BridgeDemo.blushGold.getText();
        }
        else if (e.getSource().equals(BridgeDemo.goldBlack)) {
            themeType += BridgeDemo.goldBlack.getText();
        }
        else if (e.getSource().equals(BridgeDemo.iceWhite)) {
            themeType += BridgeDemo.iceWhite.getText();
        }
        else if (e.getSource().equals(BridgeDemo.seaBlue)) {
            themeType += BridgeDemo.seaBlue.getText();
        }
        //System.out.print(themeType);
        Class<?> pType;
        Class<?> tType;
        try {
            pType = Class.forName(phoneType);
            tType = Class.forName(themeType);
            Object phoneObj = null;
            Object themeObj = null;
            try {
                phoneObj = pType.newInstance();
                themeObj = tType.newInstance();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            HuaweiPhone phone = (HuaweiPhone) phoneObj;
            Theme theme = (Theme) themeObj;
            phone.setTheme(theme);
            String typeName = phone.getName();
            BridgeDemo.show(typeName);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

//实现化角色：颜色
interface Theme {
    String getTheme();
}

class BlushGold implements Theme {
    public String getTheme() {
        return "胭脂金";
    }
}

class SeaBlue implements Theme {
    public String getTheme() {
        return "深海蓝";
    }
}

class LightBlue implements Theme {
    public String getTheme() {
        return "浅蓝色";
    }
}

//具体实现化角色：耀金黑
class GoldBlack implements Theme {
    public String getTheme() {
        return "耀金黑";
    }
}

//抽象化角色：华为手机
abstract class HuaweiPhone {
    protected Theme theme;

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public abstract String getName();
}

//拓展抽象化角色：P40
class Huawei_P40 extends HuaweiPhone {
    public String getName() {
        return "Huawei_P40_" + theme.getTheme();
    }
}

//拓展抽象化角色：P30
class Huawei_P30 extends HuaweiPhone {
    public String getName() {
        return "Huawei_P30_" + theme.getTheme();
    }
}