<script setup lang="ts">
import { ref } from 'vue';
import GestorLibros from '../components/GestorLibros.vue';
import GestorUsuarios from '../components/GestorUsuarios.vue';

const vistaActualPanel = ref<'libros' | 'usuarios'>('libros');
</script>

<template>
  <div class="container mx-auto px-4 py-10 flex flex-col md:flex-row gap-10 max-w-360">
    
    <aside class="w-full md:w-72 bg-stone-900 rounded-3xl shadow-2xl flex flex-col p-6 h-max shrink-0 relative overflow-hidden">
        <div class="absolute -right-10 -top-10 w-32 h-32 bg-stone-800 rounded-full blur-2xl opacity-50"></div>

        <h2 class="text-stone-400 text-sm font-black uppercase tracking-[0.2em] mb-8 relative z-10">Panel Admin</h2>
        
        <nav class="flex flex-col gap-3 relative z-10">
            <button @click="vistaActualPanel = 'libros'"
                    class="flex items-center gap-4 px-5 py-4 rounded-2xl transition-all duration-300 w-full group"
                    :class="vistaActualPanel === 'libros' 
                            ? 'bg-stone-100 text-stone-900 shadow-lg translate-x-2' 
                            : 'text-stone-400 hover:bg-stone-800 hover:text-stone-200'">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-6 h-6 transition-transform group-hover:scale-110">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M12 6.042A8.967 8.967 0 0 0 6 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 0 1 6 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 0 1 6-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0 0 18 18a8.967 8.967 0 0 0-6 2.292m0-14.25v14.25" />
                </svg>
                <span class="font-bold tracking-wide">Catálogo General</span>
            </button>

            <button @click="vistaActualPanel = 'usuarios'"
                    class="flex items-center gap-4 px-5 py-4 rounded-2xl transition-all duration-300 w-full group"
                    :class="vistaActualPanel === 'usuarios' 
                            ? 'bg-stone-100 text-stone-900 shadow-lg translate-x-2' 
                            : 'text-stone-400 hover:bg-stone-800 hover:text-stone-200'">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-6 h-6 transition-transform group-hover:scale-110">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M15 19.128a9.38 9.38 0 0 0 2.625.372 9.337 9.337 0 0 0 4.121-.952 4.125 4.125 0 0 0-7.533-2.493M15 19.128v-.003c0-1.113-.285-2.16-.786-3.07M15 19.128v.106A12.318 12.318 0 0 1 8.624 21c-2.331 0-4.512-.645-6.374-1.766l-.001-.109a6.375 6.375 0 0 1 11.964-3.07M12 6.375a3.375 3.375 0 1 1-6.75 0 3.375 3.375 0 0 1 6.75 0Zm8.25 2.25a2.625 2.625 0 1 1-5.25 0 2.625 2.625 0 0 1 5.25 0Z" />
                </svg>
                <span class="font-bold tracking-wide">Lectores</span>
            </button>
        </nav>
    </aside>

    <main class="flex-1 min-w-0">
        <GestorLibros v-if="vistaActualPanel === 'libros'" />
        <GestorUsuarios v-if="vistaActualPanel === 'usuarios'" />
    </main>

  </div>
</template>