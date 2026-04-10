<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';

// acciones
import { obtenerUsuarios, guardarNuevoUsuario } from '../actions/usuarios.actions';
import type { Usuario } from '../../layout/interfaces/usuario.ts';

// componentes hijos
import TablaUsuarios from './TablaUsuarios.vue';
import ModalListaLibros from './ModalListaLibros.vue';
import ModalAlerta from './ModalAlerta.vue';

const usuarios = ref<Usuario[]>([]);
const busquedaUsuario = ref('');
const mostrandoFormUsuario = ref(false);

const nuevoUsuario = ref<Usuario>({ nombre: '', rol: 'ALUMNO' } as Usuario);
const usuarioSeleccionado = ref<Usuario | null>(null);

const alerta = ref({ visible: false, titulo: '', mensaje: '', tipo: 'exito' as 'exito' | 'error' });

const mostrarAlerta = (titulo: string, mensaje: string, tipo: 'exito' | 'error' = 'exito') => {
    alerta.value = { visible: true, titulo, mensaje, tipo };
};

const cargarUsuarios = async () => {
    try { usuarios.value = await obtenerUsuarios(); } 
    catch (e) { console.error("Error usuarios", e); }
};

const usuariosFiltrados = computed(() => {
    if (!busquedaUsuario.value) return usuarios.value;
    const b = busquedaUsuario.value.toLowerCase();
    return usuarios.value.filter(u => u.nombre.toLowerCase().includes(b) || u.rol.toLowerCase().includes(b));
});

const crearUsuario = async () => {
    try {
        await guardarNuevoUsuario(nuevoUsuario.value);
        await cargarUsuarios();
        mostrandoFormUsuario.value = false;
        nuevoUsuario.value = { nombre: '', rol: 'ALUMNO' } as Usuario;
        mostrarAlerta('Registrado', '¡Lector registrado en el sistema! 👤', 'exito');
    } catch (e) { 
        mostrarAlerta('Error', 'Error al crear el usuario.', 'error'); 
    }
};

onMounted(cargarUsuarios);
</script>

<template>
    <div class="space-y-8 relative">
        <div class="flex flex-col lg:flex-row justify-between items-start lg:items-end gap-6">
            <div>
                <h1 class="text-5xl font-black text-stone-900 tracking-tighter mb-2">Comunidad</h1>
                <p class="text-stone-500 text-lg font-medium">Directorio de lectores y permisos de acceso</p>
            </div>

            <div class="flex flex-col sm:flex-row gap-4 w-full lg:w-auto">
                <div class="relative group flex-1 sm:w-72">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="absolute left-4 top-3.5 w-5 h-5 text-stone-400 group-focus-within:text-stone-800 transition-colors">
                      <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
                    </svg>
                    <input v-model="busquedaUsuario" placeholder="Buscar por nombre o rol..." 
                           class="pl-12 pr-4 py-3 rounded-2xl w-full bg-white shadow-sm border-0 focus:ring-4 focus:ring-stone-200 text-stone-700 outline-none transition-all font-medium placeholder:font-normal"/>
                </div>
                
                <button @click="mostrandoFormUsuario = !mostrandoFormUsuario" 
                        class="bg-stone-900 text-white font-bold px-6 py-3 rounded-2xl flex items-center justify-center gap-3 transition-transform hover:-translate-y-1 shadow-lg shadow-stone-900/30">
                    <span class="text-xl leading-none" v-if="!mostrandoFormUsuario">+</span>
                    <span class="text-xl leading-none" v-else>✕</span>
                    <span>{{ mostrandoFormUsuario ? 'Cerrar' : 'Nuevo Lector' }}</span>
                </button>
            </div>
        </div>

        <div v-if="mostrandoFormUsuario" class="bg-white p-8 rounded-3xl shadow-xl shadow-stone-200/50">
            <h3 class="text-xl font-black text-stone-800 mb-6">Dar de Alta a un Lector</h3>
            <form @submit.prevent="crearUsuario" class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div class="flex flex-col gap-2">
                    <label class="text-xs font-bold text-stone-400 uppercase tracking-wider">Nombre Completo</label>
                    <input v-model="nuevoUsuario.nombre" required class="p-3 bg-stone-50 rounded-xl border-0 focus:ring-2 focus:ring-stone-900 outline-none font-medium" />
                </div>
                <div class="flex flex-col gap-2">
                    <label class="text-xs font-bold text-stone-400 uppercase tracking-wider">Nivel de Acceso</label>
                    <select v-model="nuevoUsuario.rol" class="p-3 bg-stone-50 rounded-xl border-0 focus:ring-2 focus:ring-stone-900 outline-none font-medium cursor-pointer">
                        <option value="ALUMNO">Alumno</option>
                        <option value="PROFESOR">Profesor</option>
                        <option value="ADMIN">Administrador</option>
                    </select>
                </div>
                
                <div class="md:col-span-2 flex justify-end mt-2">
                    <button type="submit" class="bg-stone-800 text-white font-bold py-3 px-8 rounded-xl hover:bg-stone-950 transition-colors shadow-md">
                        Crear Perfil
                    </button>
                </div>
            </form>
        </div>

        <TablaUsuarios 
            :usuarios="usuariosFiltrados" 
            @verPrestamos="usuarioSeleccionado = $event" 
        />

        <ModalListaLibros 
            :usuario="usuarioSeleccionado" 
            @cerrar="usuarioSeleccionado = null" 
        />

        <ModalAlerta 
            v-if="alerta.visible"
            :titulo="alerta.titulo"
            :mensaje="alerta.mensaje"
            :tipo="alerta.tipo"
            @cerrar="alerta.visible = false"
        />
    </div>
</template>