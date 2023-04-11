package com.app.project.util;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.UUID;
import java.util.function.UnaryOperator;

public class Helper {
    public static UUID generateUniqueIdentifier() {
        return UUID.randomUUID();
    }

    public static void setNumericInputFilter(TextField textField) {
        // Create a UnaryOperator to enforce numeric input
        UnaryOperator<TextFormatter.Change> numericFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        // Set the TextFormatter with the numeric filter to the TextField
        TextFormatter<String> textFormatter = new TextFormatter<>(numericFilter);
        textField.setTextFormatter(textFormatter);
    }

    public static class Item {
        private String displayText;
        private String value;

        public Item(String displayText, String value) {
            this.displayText = displayText;
            this.value = value;
        }

        public String getDisplayText() {
            return displayText;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return displayText;
        }
    }

    public static void setAPPIcon(Stage stage,String URL){
        if(URL.length()==0)
            URL="/icons/laughing.png";
        Image image=new Image(URL);
        stage.getIcons().add(image);
    }

}
