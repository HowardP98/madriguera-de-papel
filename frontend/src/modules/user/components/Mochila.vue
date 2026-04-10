<script setup lang="ts">
defineProps<{ libros: any[] }>();
defineEmits<{ (e: 'devolver', id: number): void }>();
</script>

<template>
  <div class="w-full">
    <div v-if="libros.length === 0" 
         class="flex flex-col items-center justify-center py-24 bg-stone-100 rounded-[2.5rem] border-2 border-dashed border-stone-200">
        <div class="bg-white p-6 rounded-3xl shadow-sm mb-6 text-stone-200">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1" stroke="currentColor" class="w-16 h-16">
              <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
            </svg>
        </div>
        <h3 class="text-2xl font-black text-stone-800 tracking-tight">Tu espacio está vacío</h3>
        <p class="text-stone-500 font-medium mt-1">Explora el catálogo para seleccionar tus próximas lecturas.</p>
    </div>

    <div v-else class="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div v-for="libro in libros" :key="libro.id" 
             class="group bg-white p-7 rounded-4xl border border-stone-100 shadow-sm hover:shadow-xl hover:shadow-stone-200 transition-all duration-500 flex flex-col sm:flex-row justify-between items-center gap-6 relative overflow-hidden">
            
            <div class="absolute left-0 top-0 bottom-0 w-2 bg-stone-800 group-hover:w-3 transition-all duration-500"></div>

            <div class="pl-4 w-full">
                <div class="flex items-center gap-3 mb-3">
                    <span class="bg-stone-100 text-stone-500 text-[9px] font-black tracking-[0.2em] px-3 py-1.5 rounded-lg border border-stone-200 uppercase">
                        En Lectura
                    </span>
                </div>
                <h3 class="font-black text-2xl text-stone-900 tracking-tighter leading-tight">{{ libro.titulo }}</h3>
                <p class="text-stone-400 text-sm font-bold mt-1 uppercase tracking-tighter italic">Autor: {{ libro.autor }}</p>
            </div>
            
            <button @click="$emit('devolver', libro.id)" 
                    class="w-full sm:w-auto shrink-0 bg-transparent border-2 border-stone-200 text-stone-400 hover:border-red-600 hover:text-red-600 px-8 py-3 rounded-2xl font-black text-xs uppercase tracking-widest transition-all duration-300 flex items-center justify-center gap-3 active:scale-95">
                <span>Devolver</span>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-4 h-4">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M9 15 3 9m0 0 6-6M3 9h12a6 6 0 0 1 0 12h-3" />
                </svg>
            </button>
        </div>
    </div>
  </div>
</template>