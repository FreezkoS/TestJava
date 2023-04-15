import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ConfProperties {
    /**
     * создаем защищенные статические переменные fileInputStream и PROPERTIES
     */
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;

    /**
     * создаем статический блок кода, который будет выполняться при загрузке класса
     */
    static {
        try {
            //указание пути до файла с настройками
            fileInputStream = new FileInputStream("src/main/resources/conf.properties");
            // создаем объект класса Properties
            PROPERTIES = new Properties();
            // загружаем настройки из файла
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //обработка возможного исключения (нет файла и т.п.)
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace(); } } }
    /**
     * метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}