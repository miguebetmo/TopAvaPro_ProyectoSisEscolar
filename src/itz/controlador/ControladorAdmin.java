package itz.controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import itz.modelo.Administrador;
import itz.modelo.Alumno;
import itz.modelo.Materia;
import itz.modelo.Profesor;
import itz.vista.VentanaAdmin;

public class ControladorAdmin {

    private Administrador modeloAdmin;
    private VentanaAdmin vistaAdmin;

    public ControladorAdmin(Administrador modeloAdmin, VentanaAdmin vistaAdmin) {
        this.modeloAdmin = modeloAdmin;
        this.vistaAdmin = vistaAdmin;
    }

    public void crearAlumno() {

        Alumno alumno = vistaAdmin.obtenerDatosAlumno();

        String archivo = "alumnos.dat";
        List<Alumno> lista = (List<Alumno>) leerObjeto(archivo);

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(alumno);
        guardarObjeto(lista, archivo);

        vistaAdmin.mostrarMensaje("Alumno creado correctamente");
    }

    public void editarAlumno() {

        String matricula = vistaAdmin.obtenerMatricula();
        Alumno datosActualizados = vistaAdmin.obtenerDatosAlumno();

        String archivo = "alumnos.dat";
        List<Alumno> lista = (List<Alumno>) leerObjeto(archivo);

        if (lista == null) {
            vistaAdmin.mostrarMensaje("No hay alumnos registrados");
            return;
        }

        boolean encontrado = false;

        for (Alumno a : lista) {
            if (a.getMatricula().equals(matricula)) {
                a.setNombre(datosActualizados.getNombre());
                a.setCarrera(datosActualizados.getCarrera());
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            guardarObjeto(lista, archivo);
            vistaAdmin.mostrarMensaje("Alumno actualizado correctamente");
        } else {
            vistaAdmin.mostrarMensaje("Alumno no encontrado");
        }
    }

    public void crearProfesor() {

        Profesor profesor = vistaAdmin.obtenerDatosProfesor();

        String archivo = "profesores.dat";
        List<Profesor> lista = (List<Profesor>) leerObjeto(archivo);

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(profesor);
        guardarObjeto(lista, archivo);

        vistaAdmin.mostrarMensaje("Profesor creado correctamente");
    }

    public void crearMateria() {

        Materia materia = vistaAdmin.obtenerDatosMateria();

        String archivo = "materias.dat";
        List<Materia> lista = (List<Materia>) leerObjeto(archivo);

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(materia);
        guardarObjeto(lista, archivo);

        vistaAdmin.mostrarMensaje("Materia creada correctamente");
    }

    public void asignarMateria() {

        String matricula = vistaAdmin.obtenerMatricula();
        Materia materia = vistaAdmin.obtenerMateriaSeleccionada();

        String archivo = "materias_" + matricula + ".dat";
        List<Materia> lista = (List<Materia>) leerObjeto(archivo);

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(materia);
        guardarObjeto(lista, archivo);

        vistaAdmin.mostrarMensaje("Materia asignada al alumno correctamente");
    }

    // SERIALIZACIÓN

    private void guardarObjeto(Object obj, String archivo) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(archivo))) {

            oos.writeObject(obj);

        } catch (IOException e) {
            vistaAdmin.mostrarMensaje("Error al guardar datos.");
        }
    }

    private Object leerObjeto(String archivo) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(archivo))) {

            return ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
