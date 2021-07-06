package sweeper;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class SavingWindow extends JFrame
{
    public static JFrame frameSecondary = new JFrame("Save Results");
    private JPanel panel;
    private JButton save;
    private JButton close;
    private JTextField name;
    private JLabel showMessage, inputName, finalTime;

    Font font = new Font("Tahoma", Font.BOLD, 18);

    int timeResult = Game.timeSpent;
    String playerName = name.getText();

    public SavingWindow()
    {
        inputName.setText("Enter your name");
        inputName.setFont(font);
        initCloseButton();
        initFinalTime();
        showMessage.setFont(font);
        initSaveButton();
    }

    public void initFinalTime()
    {
        finalTime.setFont(font);
        finalTime.setText("Your time is: " + timeResult);
    }

    public void initSaveButton()
    {
        save.setText("Save");
        save.setFont(font);
        save.addActionListener(e -> {
            playerName = name.getText();startDB();frameSecondary.setVisible(false);});
    }

    public void initCloseButton()
    {
        close.setText("Відміна");
        close.setFont(font);
        close.addActionListener(e -> frameSecondary.dispose());
    }

    public void startDB()
    {
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.startDB();
    }

    public void initFrameSecondary()
    {
        frameSecondary.setContentPane(new SavingWindow().panel);
        frameSecondary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSecondary.setSize(400,300);
        frameSecondary.setVisible(true);
        frameSecondary.setLocationRelativeTo(null);
        frameSecondary.setResizable(false);
    }

public class ConnectionDB
{
    Connection co;
    public void startDB()
    {
        ConnectionDB connectionDB = new ConnectionDB();
        if (connectionDB.openConnection())
        {
            connectionDB.insertIntoDB();
            connectionDB.closeConnection();
        }
    }

    boolean openConnection()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\BUTS\\Desktop\\MineSweeper\\out\\artifacts\\MineSweeper_jar\\records.db");
            System.out.println("Connected");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    void insertIntoDB()
    {
        try
        {
            String query =
                    "INSERT INTO users (name, time) " +
                            "VALUES ('" + playerName + "', '" + timeResult + "')";
            Statement statement = co.createStatement();
            statement.executeUpdate(query);

            System.out.println("Rows added");
            closeConnection();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    void closeConnection()
    {
        try
        {
            co.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
}

