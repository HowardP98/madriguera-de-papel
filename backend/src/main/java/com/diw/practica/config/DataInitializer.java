package com.diw.practica.config;

import com.diw.practica.model.Libro;
import com.diw.practica.model.Usuario;
import com.diw.practica.repository.LibroRepository;
import com.diw.practica.repository.UsuarioRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Inicializa datos de ejemplo en la base de datos al arrancar la aplicación.
 */
@Component
public class DataInitializer {

    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;

    public DataInitializer(UsuarioRepository usuarioRepository, LibroRepository libroRepository) {
        this.usuarioRepository = usuarioRepository;
        this.libroRepository = libroRepository;
    }

    /**
     * Crea usuarios y libros de ejemplo integrando tu lógica específica.
     */
    @PostConstruct
    @Transactional
    public void cargarDatosIniciales() {
        // Evitar duplicados: si ya hay datos, no hace nada
        if (usuarioRepository.count() > 0 || libroRepository.count() > 0) {
            return;
        }

        // 1. Crear Admin usando setters para evitar errores de constructor
        Usuario admin = new Usuario();
        admin.setNombre("Administradora");
        admin.setRol(Usuario.Rol.ADMIN);

        // Descomenta estas líneas si ya has añadido los campos username/password en tu clase Usuario
        // admin.setUsername("admin");
        // admin.setPassword("adminpass");

        usuarioRepository.save(admin);

        // 2. Crear Alumna
        Usuario alumna = new Usuario();
        alumna.setNombre("Alumna");
        alumna.setRol(Usuario.Rol.ALUMNO);

        // alumna.setUsername("user");
        // alumna.setPassword("userpass");

        usuarioRepository.save(alumna);

        // 3. Crear Libros (Asegúrate de que tu clase Libro tenga este constructor de 6 parámetros)
        Libro cleanCode = new Libro(
                "Clean Code",
                "Robert C. Martin",
                "978-0132350884",
                2008,
                "Prentice Hall",
                Libro.Estado.DISPONIBLE
        );

        Libro ddd = new Libro(
                "Domain-Driven Design",
                "Eric Evans",
                "978-0321125217",
                2003,
                "Addison-Wesley",
                Libro.Estado.PRESTADO
        );

        // Vincular el libro prestado a la alumna
        ddd.setPrestadoA(alumna);

        // Guardar los libros
        libroRepository.saveAll(List.of(cleanCode, ddd));

        System.out.println(">> Datos iniciales cargados correctamente.");
    }
}