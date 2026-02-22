package itz.controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

import itz.modelo.Alumno;
import itz.modelo.Calificacion;
import itz.modelo.Horario;
import itz.modelo.Profesor;
import itz.vista.VentanaProfesor;

/**
 *
 * @author miguel
 */

public class ControladorProfesor {
    
    private Profesor modeloProfesor;
    private VentanaProfesor vistaProfesor;

    public ControladorProfesor(Profesor modeloProfesor, VentanaProfesor vistaProfesor) {
        this.modeloProfesor = modeloProfesor;
        this.vistaProfesor = vistaProfesor;
    }

    public void cargarAlumno() {
        String archivo = "alumnos_" + modeloProfesor.getNumEmpleado() + ".dat";
        
        List<Alumno> alumnos = (List<Alumno>) leerObjeto(archivo);
        
        if (alumnos != null) {
            vistaProfesor.mostrarAlumnos(alumnos);
        } else {
            vistaProfesor.mostrarMensaje("No hay alumnos asignados");
        }
    }

    public void registrarCalificaciones() {
        List<Calificacion> califs = vistaProfesor.obtenerCalificaciones();
        String archivo = "calificaciones_prof_" + modeloProfesor.getNumEmpleado() + ".dat";

        guardarObjeto(califs, archivo);
        vistaProfesor.mostrarMensaje("Calificaciones Guardadas Correctamente");
    }

    public void cargarHorario() {
        String archivo = "horario_" + modeloProfesor.getNumEmpleado() + ".dat";
        Horario horario = (Horario) leerObjeto(archivo);

        if (horario != null) {
            vistaProfesor.mostrarHorario(horario);
        } else {
            vistaProfesor.mostrarMensaje("No hay horario asignado.");
        }
    }

    private void guardarObjeto(Object obj, String archivo) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            vistaProfesor.mostrarMensaje("Error al guardar datos.");
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
