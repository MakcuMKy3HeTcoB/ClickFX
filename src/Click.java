import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Click extends Application {

    private int clickCount = 0; // Счётчик нажатий
    private long lastClickTime = 0; // Время последнего нажатия
    private double maxSpeed = 0; // Максимальная скорость нажатий

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button btn = new Button("Нажми!");
        Label speedLabel = new Label();
        Label maxSpeedLabel = new Label();

        VBox vbox = new VBox(10, maxSpeedLabel, btn, speedLabel);
        vbox.setStyle("-fx-alignment: center;");

        btn.setOnAction(_ -> {
            clickCount++;
            long currentTime = System.currentTimeMillis();

            if (clickCount > 1) {
                double speed = 1000.0 / (currentTime - lastClickTime);
                speedLabel.setText(String.format("Скорость: %.2f наж/с", speed));
                maxSpeed = Math.max(maxSpeed, speed);
                maxSpeedLabel.setText(String.format("Макс. скорость: %.2f наж/с", maxSpeed));
            }

            lastClickTime = currentTime; // Обновляем время последнего нажатия
            btn.setText("Вы нажали " + clickCount + " раз(а)!"); // Обновляем текст кнопки

            // Отображаем метки после первого нажатия
            if (clickCount == 1) {
                maxSpeedLabel.setVisible(true);
                speedLabel.setVisible(true);
            }
        });

        Scene scene = new Scene(vbox, 250, 200); // Создаем сцену
        stage.setScene(scene);
        stage.setTitle("Кнопка");
        stage.show();
    }
}