<script setup lang="ts">
defineProps<{ libros: any[] }>();
defineEmits<{ (e: 'pedir', id: number): void }>();
</script>

<template>
  <div class="w-full">
    <div v-if="libros.length === 0" 
         class="flex flex-col items-center justify-center py-24 bg-stone-100 rounded-4xl border-2 border-dashed border-stone-200">
        <div class="bg-white p-6 rounded-full shadow-sm mb-6 text-stone-300">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1" stroke="currentColor" class="w-16 h-16">
              <path stroke-linecap="round" stroke-linejoin="round" d="M12 6.042A8.967 8.967 0 0 0 6 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 0 1 6 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 0 1 6-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0 0 18 18a8.967 8.967 0 0 0-6 2.292m0-14.25v14.25" />
            </svg>
        </div>
        <h3 class="text-2xl font-black text-stone-800 tracking-tight">La biblioteca descansa</h3>
        <p class="text-stone-500 font-medium mt-1">Todos los volúmenes están actualmente en manos de lectores.</p>
    </div>

    <div v-else class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8">
        <div v-for="libro in libros" :key="libro.id" 
             class="group flex flex-col bg-white rounded-4xl border border-stone-100 overflow-hidden shadow-sm hover:shadow-2xl hover:shadow-stone-200 transition-all duration-500 transform hover:-translate-y-2">
            
            <div class="p-5 pb-0 flex justify-between items-center">
                <div class="bg-emerald-50 text-emerald-700 text-[10px] font-black tracking-widest px-3 py-1.5 rounded-xl border border-emerald-100 uppercase">
                    Disponible
                </div>
                <span class="text-stone-300 text-[10px] font-black tracking-widest uppercase">Ref. {{ String(libro.id).padStart(3, '0') }}</span>
            </div>
            
            <div class="p-8 grow flex flex-col">
                <h3 class="text-2xl font-black text-stone-900 leading-[1.1] mb-2 line-clamp-2 tracking-tighter">{{ libro.titulo }}</h3>
                <p class="text-stone-400 text-sm mb-8 grow font-bold uppercase tracking-tighter">Autor: {{ libro.autor }}</p>
                
                <button @click="$emit('pedir', libro.id)" 
                        class="w-full bg-stone-900 text-white font-black py-4 rounded-2xl hover:bg-stone-800 transition-all active:scale-95 flex justify-center items-center gap-3 shadow-lg shadow-stone-900/10 group/btn">
                    <span class="text-sm uppercase tracking-widest">Solicitar</span>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-5 h-5 group-hover/btn:translate-x-1 transition-transform">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M13.5 4.5 21 12m0 0-7.5 7.5M21 12H3" />
                    </svg>
                </button>
            </div>
        </div>
    </div>
  </div>
</template>