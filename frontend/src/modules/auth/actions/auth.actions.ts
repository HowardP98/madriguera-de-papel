import axios from 'axios';

/**
 * @fileoverview Acciones relacionadas con la autenticación de usuarios.
 * Maneja la conexión con el endpoint de login del backend.
 */

const API_BASE = 'http://localhost:8080';

/**
 * Realiza el inicio de sesión contra el servidor utilizando Basic Auth.
 * Determina el rol del usuario basándose en los códigos de estado HTTP devueltos.
 * * @param {string} username - El nombre de usuario ingresado en el formulario.
 * @param {string} password - La contraseña ingresada en el formulario.
 * @returns {Promise<{rol: string, token: string}>} Objeto con el rol detectado y el token codificado.
 * @throws {Error} Si las credenciales son incorrectas (401) o hay un fallo de red.
 */

// Revisa si es Admin o User
export const realizarLogin = async (username: string, password: string) => {
    const authHeader = 'Basic ' + btoa(`${username}:${password}`);
    const config = { headers: { 'Authorization': authHeader } };

    try {
        // ruta de ADMIN
        await axios.get(`${API_BASE}/admin/libros`, config);
        return { rol: 'ADMIN', token: authHeader };

    } catch (error: any) {
        // Si devuelve un 403 no es admin
        if (error.response && error.response.status === 403) {
            await axios.get(`${API_BASE}/usuarios/libros/disponibles`, config);
            return { rol: 'USER', token: authHeader };
        }
        
        // Si es 401 
        throw error;
    }
};
