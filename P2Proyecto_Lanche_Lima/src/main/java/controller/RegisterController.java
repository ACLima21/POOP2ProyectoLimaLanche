package controller;

import model.User;
import view.RegisterInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class RegisterController implements ActionListener {

    private RegisterInterfaz view;

    public RegisterController(RegisterInterfaz view) {
        this.view = view;
        this.view.btnSignUp.addActionListener(this);
    }

    private void registerUser() {
        String fullName = view.tfFullName.getText();
        String username = view.tfUsername.getText();
        String email = view.tfEmail.getText();
        char[] passwordChars = view.pfPassword.getPassword();
        String password = new String(passwordChars);
        char[] confirmPasswordChars = view.pfConfirmPassword.getPassword();
        String confirmPassword = new String(confirmPasswordChars);
        String address = view.tfAddress.getText();
        String phone = view.tfPhone.getText();
        String profile = (String) view.cbProfile.getSelectedItem();

        java.util.Arrays.fill(passwordChars, '0');
        java.util.Arrays.fill(confirmPasswordChars, '0');

        User user = new User(fullName, username, email, password, confirmPassword, address, phone, profile);

        if (validateUser(user)) {
            System.out.println("Usuario registrado con éxito: " + user);
        } else {
            System.out.println("Error en el registro del usuario.");
        }
    }

    private boolean validateUser(User user) {
        boolean isValid = true;

        clearErrorMessages();

        if (!validateFullName(user.getTfFullName())) {
            view.lbErrorFullName.setText("El nombre solo puede contener letras.");
            isValid = false;
        }

        if (!validateUsername(user.getTfUsername())) {
            view.lbErrorUsername.setText("El nombre de usuario ya existe.");
            isValid = false;
        }

        if (!validateEmail(user.getTfEmail())) {
            view.lbErrorEmail.setText("Formato de correo inválido.");
            isValid = false;
        }

        if (!validateEmailExists(user.getTfEmail())) {
            view.lbErrorEmail.setText("El correo ya existe.");
            isValid = false;
        }

        if (!validatePassword(user.getPfPassword(), user.getPfConfirmPassword())) {
            view.lbErrorPassword.setText("Las contraseñas deben tener al menos 6 caracteres y coincidir.");
            view.lbErrorConfirmPassword.setText("Las contraseñas deben tener al menos 6 caracteres y coincidir.");
            isValid = false;
        }

        if (!validatePhone(user.getTfPhone())) {
            view.lbErrorPhone.setText("El teléfono debe contener solo números y tener 10 dígitos.");
            isValid = false;
        }

        if (!validateFields(user)) {
            isValid = false;
        }

        return isValid;
    }

    private void clearErrorMessages() {
        view.lbErrorFullName.setText("");
        view.lbErrorPassword.setText("");
        view.lbErrorAddress.setText("");
        view.lbErrorUsername.setText("");
        view.lbErrorConfirmPassword.setText("");
        view.lbErrorProfile.setText("");
        view.lbErrorEmail.setText("");
        view.lbErrorPhone.setText("");
    }

    private boolean validateFullName(String fullName) {
        return fullName.matches("[a-zA-Z ]+");
    }

    private boolean validateUsername(String username) {
        return true;
    }

    private boolean validateEmailExists(String email) {
        return true;
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean validatePassword(String password, String confirmPassword) {
        return password.length() >= 8 && password.equals(confirmPassword);
    }

    private boolean validatePhone(String phone) {
        return phone.matches("\\d{10}");
    }

    private boolean validateFields(User user) {
        boolean isValid = true;

        if (user.getTfFullName().isEmpty()) {
            view.lbErrorFullName.setText("Campo obligatorio.");
            isValid = false;
        }
        if (user.getTfUsername().isEmpty()) {
            view.lbErrorUsername.setText("Campo obligatorio.");
            isValid = false;
        }
        if (user.getTfEmail().isEmpty()) {
            view.lbErrorEmail.setText("Campo obligatorio.");
            isValid = false;
        }
        if (user.getPfPassword().isEmpty()) {
            view.lbErrorPassword.setText("Campo obligatorio.");
            isValid = false;
        }
        if (user.getTfAddress().isEmpty()) {
            view.lbErrorAddress.setText("Campo obligatorio.");
            isValid = false;
        }
        if (user.getTfPhone().isEmpty()) {
            view.lbErrorPhone.setText("Campo obligatorio.");
            isValid = false;
        }
        if (user.getCbProfile() == null
                || (!user.getCbProfile().equals("cliente") && !user.getCbProfile().equals("administrador"))) {
            view.lbErrorProfile.setText("Debe seleccionar un perfil.");
            isValid = false;
        }

        return isValid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnSignUp) {
            registerUser();
        }
    }

}
