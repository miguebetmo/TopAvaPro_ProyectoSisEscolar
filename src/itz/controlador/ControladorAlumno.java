package itz.controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import itz.modelo.Alumno;
import itz.modelo.Calificacion;
import itz.modelo.Kardex;
import itz.modelo.Materia;
import itz.vista.VentanaAlumno;

/**
 *
 * @author miguel
 */
public class ControladorAlumno {


    private Alumno modeloAlumno;
    private VentanaAlumno vistaAlumno;

    public ControladorAlumno(Alumno modeloAlumno, VentanaAlumno vistaAlumno) {
        this.modeloAlumno = modeloAlumno;
        this.vistaAlumno = vistaAlumno;
    }

    // CARGAR MATERIAS
    public void cargarMateria() {
        String archivo = "materias_" + modeloAlumno.getMatricula() + ".dat";
        ArrayList<Materia> materias = (ArrayList<Materia>) leerObjeto(archivo);

        if (materias != null) {
            vistaAlumno.mostrarMaterias(materias);
        } else {
            vistaAlumno.mostrarMensaje("No hay materias registradas.");
        }
    }

    // CARGAR CALIFICACIONES
    public void cargarCalificaciones() {
        String archivo = "calificaciones_" + modeloAlumno.getMatricula() + ".dat";
        ArrayList<Calificacion> calificaciones =
                (ArrayList<Calificacion>) leerObjeto(archivo);

        if (calificaciones != null) {
            vistaAlumno.mostrarCalificaciones(calificaciones);
        } else {
            vistaAlumno.mostrarMensaje("No hay calificaciones registradas.");
        }
    }

    // CARGAR KARDEX
    public void cargarKardex() {
        String archivo = "kardex_" + modeloAlumno.getMatricula() + ".dat";
        Kardex kardex = (Kardex) leerObjeto(archivo);

        if (kardex != null) {
            vistaAlumno.mostrarKardex(kardex);
        } else {
            vistaAlumno.mostrarMensaje("No existe kardex del alumno.");
        }
    }

    // METOD SERIALIZACION
    private void guardarObjeto(Object obj, String archivo) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            vistaAlumno.mostrarMensaje("Error al guardar datos.");
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
