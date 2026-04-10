<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { obtenerLibrosDisponibles, obtenerPrestamo, solicitarPrestamo, devolverPrestamo } from '../actions/user.actions';
import Catalogo from '../components/Catalogo.vue';
import Mochila from '../components/Mochila.vue';

import ModalConfirmar from '../../admin/components/ModalConfirmar.vue';
import ModalAlerta from '../../admin/components/ModalAlerta.vue';


const pestana = ref<'catalogo' | 'mochila'>('catalogo');
const librosDisponibles = ref<any[]>([]);
const misLibros = ref<any[]>([]);
const cargando = ref(false);

const conf = ref({ visible: false, titulo: '', mensaje: '', accion: () => {} });
const alerta = ref({ visible: false, titulo: '', mensaje: '', tipo: 'exito' as 'exito' | 'error' });

const cargarTodo = async () => {
    cargando.value = true;
    try {
        librosDisponibles.value = await obtenerLibrosDisponibles();
        misLibros.value = await obtenerPrestamo();
    } finally {
        cargando.value = false;
    }
};

const mostrarAlerta = (titulo: string, mensaje: string, tipo: 'exito' | 'error' = 'exito') => {
    alerta.value = { visible: true, titulo, mensaje, tipo };
};

const pedirLibro = (libroId: number) => {
    conf.value = {
        visible: true,
        titulo: 'Solicitar Préstamo',
        mensaje: '¿Estás seguro de que deseas solicitar este ejemplar?',
        accion: async () => {
            conf.value.visible = false;
            try {
                await solicitarPrestamo(libroId);
                mostrarAlerta('¡Aprobado!', 'El ejemplar ha sido añadido a tu mochila.', 'exito');
                await cargarTodo();
                pestana.value = 'mochila'; 
            } catch (error) { 
                mostrarAlerta('Operación fallida', 'No se ha podido procesar la solicitud del préstamo.', 'error');
            }
        }
    };
};

const devolverLibro = (libroId: number) => {
    conf.value = {
        visible: true,
        titulo: 'Devolver Ejemplar',
        mensaje: '¿Has terminado tu lectura y deseas devolverlo a la biblioteca?',
        accion: async () => {
            conf.value.visible = false;
            try {
                await devolverPrestamo(libroId);
                mostrarAlerta('Devolución exitosa', 'El libro ha vuelto a la estantería. ¡Gracias!', 'exito');
                await cargarTodo();
            } catch (error) { 
                mostrarAlerta('Error', 'Ha ocurrido un problema al intentar devolver el libro.', 'error');
            }
        }
    };
};

onMounted(cargarTodo);
</script>

<template>
    <div class="max-w-7xl mx-auto px-6 py-10 space-y-10 relative">
        
        <div class="flex flex-col md:flex-row justify-between items-start md:items-end gap-6">
            <div>
                <h1 class="text-5xl font-black text-stone-900 tracking-tighter mb-2">
                    {{ pestana === 'catalogo' ? 'Explorar' : 'Mis Libros' }}
                </h1>
                <p class="text-stone-500 text-lg font-medium">
                    {{ pestana === 'catalogo' ? 'Descubre tu próxima gran aventura literaria.' : 'Gestiona tus lecturas actuales y devoluciones.' }}
                </p>
            </div>

            <div class="bg-stone-200 p-1.5 rounded-2xl flex gap-1 shadow-inner border border-stone-300">
                <button @click="pestana = 'catalogo'"
                        :class="['px-6 py-2.5 rounded-xl font-bold transition-all duration-300 flex items-center gap-2', 
                                 pestana === 'catalogo' ? 'bg-stone-900 text-white shadow-lg' : 'text-stone-600 hover:bg-stone-300']">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-5 h-5">
                        <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
                    </svg>
                    Catálogo
                </button>
                <button @click="pestana = 'mochila'"
                        :class="['px-6 py-2.5 rounded-xl font-bold transition-all duration-300 flex items-center gap-2', 
                                 pestana === 'mochila' ? 'bg-stone-900 text-white shadow-lg' : 'text-stone-600 hover:bg-stone-300']">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-5 h-5">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 6.042A8.967 8.967 0 0 0 6 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 0 1 6 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 0 1 6-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0 0 18 18a8.967 8.967 0 0 0-6 2.292m0-14.25v14.25" />
                    </svg>
                    Mi Mochila
                    <span v-if="misLibros.length > 0" class="ml-1 px-2 py-0.5 rounded-lg text-[10px] bg-stone-500 text-white">
                        {{ misLibros.length }}
                    </span>
                </button>
            </div>
        </div>

        <main class="min-h-[50vh] transition-all duration-500">
            <div v-if="cargando" class="flex justify-center py-20">
                <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-stone-900"></div>
            </div>

            <div v-else class="animate-fade-in">
                <Catalogo v-if="pestana === 'catalogo'" :libros="librosDisponibles" @pedir="pedirLibro" />
                <Mochila v-if="pestana === 'mochila'" :libros="misLibros" @devolver="devolverLibro" />
            </div>
        </main>

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

<style scoped>
.animate-fade-in { animation: fadeIn 0.4s ease-out; }
@keyframes fadeIn { from { opacity: 0; transform: translateY(10px); } to { opacity: 1; transform: translateY(0); } }
</style>