package com.diw.practica.beans;

import com.diw.practica.model.Libro;
import com.diw.practica.model.Usuario;
import com.diw.practica.repository.LibroRepository;
import com.diw.practica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;

    @Autowired
    public AdminServiceImpl(UsuarioRepository usuarioRepository, LibroRepository libroRepository) {
        this.usuarioRepository = Objects.requireNonNull(usuarioRepository, "Repositorio de usuarios obligatorio");
        this.libroRepository = Objects.requireNonNull(libroRepository, "Repositorio de libros obligatorio");
    }

    @Override
    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        Objects.requireNonNull(usuario, "El usuario no puede ser nulo");
        usuario.setId(null); // Aseguramos que sea una creación
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Libro registrarLibro(Libro libro) {
        Objects.requireNonNull(libro, "El libro no puede ser nulo");
        libro.setId(null);

        // Corrección del error de campo: usamos 'estado'
        if (libro.getEstado() == null) {
            libro.setEstado(Libro.Estado.DISPONIBLE);
        }
        return libroRepository.save(libro);
    }

    @Override
    @Transactional
    public Optional<Libro> actualizarLibro(Integer libroId, Libro libroActualizado) {
        Objects.requireNonNull(libroId, "ID obligatorio");
        Objects.requireNonNull(libroActualizado, "Datos de actualización obligatorios");

        return libroRepository.findById(libroId).map(libroExistente -> {
            if (libroActualizado.getTitulo() != null) libroExistente.setTitulo(libroActualizado.getTitulo());
            if (libroActualizado.getAutor() != null) libroExistente.setAutor(libroActualizado.getAutor());
            if (libroActualizado.getIsbn() != null) libroExistente.setIsbn(libroActualizado.getIsbn());

            // Corrección: Usamos 'anio' en lugar de 'anioPublicacion'
            if (libroActualizado.getAnio() != 0) {
                libroExistente.setAnio(libroActualizado.getAnio());
            }

            if (libroActualizado.getEditorial() != null) libroExistente.setEditorial(libroActualizado.getEditorial());

            // Corrección: Usamos 'estado' en lugar de 'estadoLibro'
            if (libroActualizado.getEstado() != null) {
                libroExistente.setEstado(libroActualizado.getEstado());
            }

            return libroRepository.save(libroExistente);
        });
    }

    @Override
    @Transactional
    public boolean eliminarLibro(Integer libroId) {
        return libroRepository.findById(libroId).map(libro -> {
            libroRepository.delete(libro);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }
}