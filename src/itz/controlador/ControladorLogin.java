package itz.controlador;

import itz.modelo.Usuario;
import itz.vista.VentanaLogin;

public class ControladorLogin {

    private Usuario modeloUsuario;
    private VentanaLogin vistaLogin;

    public ControladorLogin(Usuario modeloUsuario, VentanaLogin vistaLogin) {
        this.modeloUsuario = modeloUsuario;
        this.vistaLogin = vistaLogin;
    }

    public void iniciarSesion() {

        String usuario = vistaLogin.obtenerUsuario();
        String password = vistaLogin.obtenerPassword();

        boolean valido = validarCredenciales(usuario, password);

        if (valido) {
            vistaLogin.mostrarMensaje("Inicio de sesión exitoso.");
        } else {
            vistaLogin.mostrarMensaje("Usuario o contraseña incorrectos.");
        }
    }

    public boolean validarCredenciales(String usuario, String password) {

        return modeloUsuario.verificarCredenciales(usuario, password);
    }
}
