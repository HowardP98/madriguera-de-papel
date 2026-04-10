<script setup lang="ts">
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import logoImg from '../../../assets/logo-biblioteca-publica.png'; 
import ModalAlerta from '../../admin/components/ModalAlerta.vue';

const route = useRoute(); 
const router = useRouter(); 

const alerta = ref({ visible: false, titulo: '', mensaje: '', tipo: 'exito' as 'exito' | 'error' });

const irAHome = () => router.push('/');

const cerrarSesion = () => {
    localStorage.clear();
    alerta.value = { 
        visible: true, 
        titulo: 'Sesión Cerrada', 
        mensaje: '¡Has cerrado sesión correctamente! Nos vemos pronto. 🐾', 
        tipo: 'exito' 
    };
}

const confirmarCierreYRedirigir = () => {
    alerta.value.visible = false;
    router.push('/'); 
}
</script>

<template>
    <nav class="bg-stone-900 px-4 py-1 border-b border-stone-700 flex justify-between items-center sticky top-0 z-50 shadow-md">
      
      <div class="flex items-center cursor-pointer select-none hover:opacity-80 transition" @click="irAHome">
        <img :src="logoImg" alt="Logo Biblioteca" class="w-40 object-contain drop-shadow-md" />
      </div>
      
      <div class="flex items-center gap-6">
        
        <template v-if="route.name === 'admin' || route.name === 'user'">
             <button @click="cerrarSesion" 
                     class="bg-red-700 hover:bg-red-800 text-white font-semibold px-5 py-2 rounded-lg transition-colors shadow-sm">
                 Cerrar Sesión
             </button>
        </template>

        

      </div>
    </nav>

    <ModalAlerta 
        v-if="alerta.visible"
        :titulo="alerta.titulo"
        :mensaje="alerta.mensaje"
        :tipo="alerta.tipo"
        @cerrar="confirmarCierreYRedirigir"
    />
</template>