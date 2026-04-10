<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';

// Acciones e Interfaces
import { obtenerLibros, guardarNuevoLibro, actualizarLibro, borrarLibro } from '../actions/libros.actions';
import { obtenerUsuarios } from '../actions/usuarios.actions';
import type { Libro } from '../../layout/interfaces/libro.ts';
import type { Usuario } from '../../layout/interfaces/usuario.ts';

// Componentes
import TablaLibros from './TablaLibros.vue';
import ModalEdicion from './ModalEdicion.vue';

import ModalConfirmar from './ModalConfirmar.vue'; 
import ModalAlerta from './ModalAlerta.vue';

const libros = ref<Libro[]>([]);
const usuarios = ref<Usuario[]>([]); 
const busquedaLibro = ref('');
const mostrandoFormLibro = ref(false);

const nuevoLibro = ref<Libro>({ titulo: '', autor: '', isbn: '', editorial: '', anioPublicacion: 2026, estadoLibro: 'DISPONIBLE' } as Libro);
const libroEnEdicion = ref<Libro | null>(null);

// Estados para los modales visuales
const conf = ref({ visible: false, titulo: '', mensaje: '', accion: async () => {} });
const alerta = ref({ visible: false, titulo: '', mensaje: '', tipo: 'exito' as 'exito' | 'error' });

const mostrarAlerta = (titulo: string, mensaje: string, tipo: 'exito' | 'error' = 'exito') => {
    alerta.value = { visible: true, titulo, mensaje, tipo };
};

const cargarDatos = async () => {
    try {
        libros.value = await obtenerLibros();
        usuarios.value = await obtenerUsuarios();
    } catch (e) { console.error(e); }
};

const librosFiltrados = computed(() => {
    if (!busquedaLibro.value) return libros.value;
    const b = busquedaLibro.value.toLowerCase();
    return libros.value.filter(l => l.titulo.toLowerCase().includes(b) || l.autor.toLowerCase().includes(b) || l.isbn.includes(b));
});

const crearLibro = async () => {
    try {
        await guardarNuevoLibro(nuevoLibro.value);
        await cargarDatos();
        mostrandoFormLibro.value = false;
        nuevoLibro.value = { titulo: '', autor: '', isbn: '', editorial: '', anioPublicacion: 2026, estadoLibro: 'DISPONIBLE' } as Libro;
        mostrarAlerta('¡Éxito!', '¡Libro añadido al catálogo! 📚', 'exito');
    } catch (e) { 
        mostrarAlerta('Error', 'Error al crear el libro.', 'error'); 
    }
};

const eliminar = (id: number) => {
    conf.value = {
        visible: true,
        titulo: 'Eliminar Obra',
        mensaje: '¿Seguro que deseas eliminar este libro del catálogo?',
        accion: async () => {
            conf.value.visible = false;
            try { 
                await borrarLibro(id); 
                await cargarDatos(); 
                mostrarAlerta('Eliminado', 'El libro ha sido retirado correctamente.', 'exito');
            } catch (e) { 
                mostrarAlerta('Error', 'Error al borrar el libro.', 'error'); 
            }
        }
    };
};

const editar = (libro: Libro) => libroEnEdicion.value = JSON.parse(JSON.stringify(libro));

const guardarEdicion = async () => {
    if (!libroEnEdicion.value?.id) return;
    try {
        await actualizarLibro(libroEnEdicion.value.id, libroEnEdicion.value);
        await cargarDatos();
        libroEnEdicion.value = null;
        mostrarAlerta('¡Actualizado!', '¡Libro actualizado correctamente! ✏️', 'exito');
    } catch (e) { 
        mostrarAlerta('Error', 'Error al editar el libro.', 'error'); 
    }
};

onMounted(cargarDatos);
</script>

<template>
    <div class="space-y-8 relative">
        <div class="flex flex-col lg:flex-row justify-between items-start lg:items-end gap-6">
            <div>
                <h1 class="text-5xl font-black text-stone-900 tracking-tighter mb-2">Catálogo</h1>
                <p class="text-stone-500 text-lg font-medium">Gestión del inventario literario</p>
            </div>

            <div class="flex flex-col sm:flex-row gap-4 w-full lg:w-auto">
                <div class="relative group flex-1 sm:w-72">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="absolute left-4 top-3.5 w-5 h-5 text-stone-400 group-focus-within:text-stone-800 transition-colors">
                      <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
                    </svg>
                    <input v-model="busquedaLibro" placeholder="Buscar por título, autor o ISBN..." 
                           class="pl-12 pr-4 py-3 rounded-2xl w-full bg-white shadow-sm border-0 focus:ring-4 focus:ring-stone-200 text-stone-700 outline-none transition-all font-medium placeholder:font-normal"/>
                </div>
                
                <button @click="mostrandoFormLibro = !mostrandoFormLibro" 
                        class="bg-stone-900 text-white font-bold px-6 py-3 rounded-2xl flex items-center justify-center gap-3 transition-transform hover:-translate-y-1 shadow-lg shadow-stone-900/30">
                    <span class="text-xl leading-none" v-if="!mostrandoFormLibro">+</span>
                    <span class="text-xl leading-none" v-else>✕</span>
                    <span>{{ mostrandoFormLibro ? 'Cerrar' : 'Nuevo Libro' }}</span>
                </button>
            </div>
        </div>

        <div v-if="mostrandoFormLibro" class="bg-white p-8 rounded-3xl shadow-xl shadow-stone-200/50">
            <h3 class="text-xl font-black text-stone-800 mb-6">Añadir Obra al Catálogo</h3>
            <form @submit.prevent="crearLibro" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                <div class="flex flex-col gap-2">
                    <label class="text-xs font-bold text-stone-400 uppercase tracking-wider">Título</label>
                    <input v-model="nuevoLibro.titulo" required class="p-3 bg-stone-50 rounded-xl border-0 focus:ring-2 focus:ring-stone-900 outline-none font-medium" />
                </div>
                <div class="flex flex-col gap-2">
                    <label class="text-xs font-bold text-stone-400 uppercase tracking-wider">Autor</label>
                    <input v-model="nuevoLibro.autor" required class="p-3 bg-stone-50 rounded-xl border-0 focus:ring-2 focus:ring-stone-900 outline-none font-medium" />
                </div>
                <div class="flex flex-col gap-2">
                    <label class="text-xs font-bold text-stone-400 uppercase tracking-wider">ISBN</label>
                    <input v-model="nuevoLibro.isbn" required class="p-3 bg-stone-50 rounded-xl border-0 focus:ring-2 focus:ring-stone-900 outline-none font-medium" />
                </div>
                <div class="flex flex-col gap-2">
                    <label class="text-xs font-bold text-stone-400 uppercase tracking-wider">Editorial</label>
                    <input v-model="nuevoLibro.editorial" required class="p-3 bg-stone-50 rounded-xl border-0 focus:ring-2 focus:ring-stone-900 outline-none font-medium" />
                </div>
                
                <div class="lg:col-span-4 flex justify-end mt-2">
                    <button type="submit" class="bg-stone-800 text-white font-bold py-3 px-8 rounded-xl hover:bg-stone-950 transition-colors shadow-md">
                        Registrar Obra
                    </button>
                </div>
            </form>
        </div>

        <TablaLibros 
            :libros="librosFiltrados" 
            @editar="editar" 
            @eliminar="eliminar" 
        />

        <ModalEdicion 
            v-if="libroEnEdicion" 
            :libro="libroEnEdicion" 
            @cerrar="libroEnEdicion = null" 
            @guardar="guardarEdicion" 
        />

        <ModalConfirmar 
            v-if="conf.visible"
            :titulo="conf.titulo"
            :mensaje="conf.mensaje"
            @cancelar="conf.visible = false"
            @confirmar="conf.accion"
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