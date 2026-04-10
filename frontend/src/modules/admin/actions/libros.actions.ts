import axios from 'axios';
import type { Libro } from '../../layout/interfaces/libro'; 

/**
 * @fileoverview Acciones de gestión del catálogo de libros (CRUD).
 * Exclusivo para usuarios con rol de Administrador.
 */

const API_BASE = 'http://localhost:8080';

/**
 * Genera la configuración estándar de cabeceras para las peticiones Axios.
 * Inyecta automáticamente el token de autorización de la sesión activa.
 * * @returns {Object} Configuración de cabeceras HTTP.
 */

const getConfig = () => ({
  headers: { 'Authorization': localStorage.getItem('auth'), 'Content-Type': 'application/json' }
});

/**
 * Recupera la lista completa de libros del catálogo.
 * @returns {Promise<Libro[]>} Array de objetos tipo Libro.
 */

export const obtenerLibros = async () => {
  const response = await axios.get(`${API_BASE}/admin/libros`, getConfig());
  return response.data;
};

/**
 * Registra un nuevo libro en la base de datos.
 * @param {Libro} libro - El objeto con los datos del nuevo libro.
 * @returns {Promise<Libro>} El libro creado.
 */

export const guardarNuevoLibro = async (libro: Libro) => {
  const response = await axios.post(`${API_BASE}/admin/libros`, libro, getConfig());
  return response.data;
};

/**
 * Actualiza los datos de un libro existente.
 * @param {number} id - Identificador único del libro a editar.
 * @param {Libro} libro - Objeto con los datos actualizados.
 * @returns {Promise<Libro>} El libro actualizado.
 */

export const actualizarLibro = async (id: number, libro: Libro) => {
  const response = await axios.put(`${API_BASE}/admin/libros/${id}`, libro, getConfig());
  return response.data;
};

/**
 * Elimina un libro del sistema.
 * @param {number} id - Identificador único del libro a borrar.
 * @returns {Promise<void>}
 */

export const borrarLibro = async (id: number) => {
  const response = await axios.delete(`${API_BASE}/admin/libros/${id}`, {
    ...getConfig(),
    data: {} 
  });
  return response.data;
};
