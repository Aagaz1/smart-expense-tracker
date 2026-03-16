package com.expense;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import com.expense.ui.LoginForm;

public class Main {

    public static void main(String[] args) {
        try {
    UIManager.setLookAndFeel(new FlatLightLaf());
} catch (Exception e) {
    e.printStackTrace();
}
        new LoginForm();

    }

}