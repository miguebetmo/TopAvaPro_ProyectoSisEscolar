package itz.controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import itz.modelo.Alumno;
import itz.vista.VentanaAlumno;
import itz.vista.VentanaInscripcion;
import itz.modelo.Materia;

/**
 *
 * @author miguel
 */
public class ControladorInscripcion {
    
    private Alumno modeloAlumno;
    private VentanaInscripcion vistaInscripcion;


    public ControladorInscripcion (Alumno modeloAlumno, VentanaInscripcion vistaInscripcion) {
        this.modeloAlumno = modeloAlumno;
        this.vistaInscripcion = vistaInscripcion;
    }


    public void cargarMateriasDisponibles () {
        List<Materia> materias = modeloAlumno.getMateriasDisponibles();
        vistaInscripcion.mostrarMateriasDisponibles(materias);
    }

    public void inscribirAlumno () {

    Materia materiaSeleccionada = vistaInscripcion.getMateriaSeleccionada();

    boolean inscrito = modeloAlumno.inscribirseMateria(materiaSeleccionada);

    if (inscrito) {
        guardarObjeto(modeloAlumno, "alumnos.dat");

        vistaInscripcion.mostrarMensaje("Alumno inscrito correctamente.");
    } else {
        vistaInscripcion.mostrarMensaje("No se pudo realizar la inscripción.");
    }
}
 
   private void guardarObjeto(Object obj, String archivo) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            vistaInscripcion.mostrarMensaje("Error al guardar datos.");
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

}
