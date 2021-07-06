package sweeper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
    /**
     * Пример для Oci777 (Краснодар)
     * @author licvidator
     * @version 1.0
     */
    public class ShowDB extends JDialog
    {
        public ShowDB() {
            try
            {
                // Устанавливаем менеджер расположения как в дотнете
                this.getContentPane().setLayout(new BorderLayout());
                // Создаем табличку и добавляем ее в центр окна
                JTable dbTable = new JTable();
                // JScrollPane нужна для отображения заголовка (ну и для скроллинга таблицы) -
                // в противном случае P_1 и P_2 в заголовке отображены не будут
                JScrollPane pane = new JScrollPane();
                pane.getViewport().add(dbTable);
                this.getContentPane().add(pane, BorderLayout.CENTER);

                // Получаю данные из БД
                Vector<Vector<String>> values = getDataFromDB();

                // "Шапка" - т.е. имена полей
                Vector<String> header = new Vector<String>();
                header.add("NAME");
                header.add("TIME");

                // Помещаю в модель таблицы данные
                DefaultTableModel dtm = (DefaultTableModel)dbTable.getModel();
                // Сначала данные, потом шапка
                dtm.setDataVector(values, header);
                // Ну все, теперь только размеры, видимость и чтобы по крестику закрывалось :-)
                this.setTitle("RECORDS");
                this.setResizable(false);
                this.setSize(350, 220);
                this.setLocation(640,300);
                this.setModal(true);
                this.setVisible(true);
                this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }

        /**
         * Возвращает набор значений в виде вектора - такими их умеет кушать модель
         * @return List список, состоящий из списков вида <P_1, P_2>
         * @throws Exception ибо лениво расписывать все.
         */
        public Vector<Vector<String>> getDataFromDB() throws Exception
        {
            // переменная под результат
            Vector<Vector<String>> result = new Vector<>();
            // Регистрируем драйвер в менеджере
            Class.forName("org.sqlite.JDBC");
            Connection co = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\BUTS\\Desktop\\MineSweeper\\out\\artifacts\\MineSweeper_jar\\records.db");
            Statement stmt = co.createStatement();

            // Ну кто же так таблицы называет!!!
            String query = "SELECT name, time  FROM users order by time";
            // Выполняем запрос, который у нас в переменной query
            ResultSet resultSet = stmt.executeQuery(query);

            // пока у нас есть данные - выполняем цикл
            String name, time;

            while(resultSet.next())
            {
                // Создаем новый список <P_1, P_2>
                Vector<String> element = new Vector<String>();

                // Первой колонкой у нас объявлен P_1
                name = resultSet.getString(1);
                // Второй - P_2
                time = resultSet.getString(2);
                // Добавляем по порядку
                element.add(name);
                element.add(time);

                // Присоединяем список к результату
                result.add(element);
            }
            // Освобождаем все ресурсы:
            resultSet.close();
            stmt.close();
            co.close();

            return result;
        }
}
