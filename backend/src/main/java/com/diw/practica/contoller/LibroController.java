package com.diw.practica.contoller;

import com.diw.practica.model.Libro;
import com.diw.practica.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST público para la gestión operativa del catálogo de libros.
 * Permite listar, actualizar y eliminar ejemplares desde la interfaz de usuario.
 * Incluye configuración CORS para habilitar la comunicación segura con el frontend.
 */
@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:5173") // Crucial para que Vue conecte
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;
    /**
     * Recupera la lista completa de libros disponibles en la base de datos.
     *
     * @return Una lista de objetos Libro.
     */

    @GetMapping
    public List<Libro> listarTodos() {
        return (List<Libro>) libroRepository.findAll();
    }
    /**
     * Actualiza la información (título, autor, estado) de un libro existente en el sistema.
     *
     * @param id El identificador único del libro a modificar.
     * @param detalles Objeto con los nuevos datos del libro extraídos del cuerpo de la petición.
     * @return El objeto Libro actualizado y persistido en la base de datos.
     * @throws RuntimeException Si no se encuentra ningún libro asociado al ID proporcionado.
     */
    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Integer id, @RequestBody Libro detalles) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libro.setTitulo(detalles.getTitulo());
        libro.setAutor(detalles.getAutor());
        libro.setEstado(detalles.getEstado());

        return libroRepository.save(libro);
    }
    /**
     * Elimina un libro de la base de datos de forma permanente.
     *
     * @param id El identificador único del libro a eliminar.
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        libroRepository.deleteById(id);
    }
}
