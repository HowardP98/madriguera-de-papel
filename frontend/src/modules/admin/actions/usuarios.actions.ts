import axios from 'axios';
import type { Usuario } from '../../layout/interfaces/usuario'; 

const API_BASE = 'http://localhost:8080';

const getConfig = () => ({
  headers: { 'Authorization': localStorage.getItem('auth'), 'Content-Type': 'application/json' }
});

export const obtenerUsuarios = async () => {
  const response = await axios.get(`${API_BASE}/admin/usuarios`, getConfig());
  return response.data;
};

export const guardarNuevoUsuario = async (usuario: Usuario) => {
  const response = await axios.post(`${API_BASE}/admin/usuarios`, usuario, getConfig());
  return response.data;
};

export const prestarLibroAUsuario = async (usuarioId: number, libroId: number) => {
  const response = await axios.post(`${API_BASE}/usuarios/${usuarioId}/prestamos/${libroId}`, {}, getConfig());
  return response.data;
};