import axios from 'axios';

const API_BASE = 'http://localhost:8080';
const MI_ID = 3; 

const getConfig = () => ({
  headers: { 'Authorization': localStorage.getItem('auth') }
});

export const obtenerLibrosDisponibles = async () => {
    try {
        const res = await axios.get(`${API_BASE}/usuarios/libros/disponibles`, getConfig());
        return res.data;
    } catch (error) {
        console.error("Error cargando catálogo", error);
        return [];
    }
};

export const obtenerPrestamo = async () => {
    try {
        const res = await axios.get(`${API_BASE}/usuarios/${MI_ID}/prestamos`, getConfig());
        return res.data;
    } catch (e) {
        return [];
    }
};

export const solicitarPrestamo = async (libroId: number) => {
    const res = await axios.post(`${API_BASE}/usuarios/${MI_ID}/prestamos/${libroId}`, {}, getConfig());
    return res.data;
};

export const devolverPrestamo = async (libroId: number) => {
    const res = await axios.post(`${API_BASE}/usuarios/${MI_ID}/devoluciones/${libroId}`, {}, getConfig());
    return res.data;
};