<script setup lang="ts">
import { ref, computed } from 'vue';
import type { Usuario } from '../../layout/interfaces/usuario.ts';

const props = defineProps<{ usuario: Usuario | null }>();
const emit = defineEmits(['cerrar']);

const busqueda = ref('');
const librosFiltrados = computed(() => {
  const lista = props.usuario?.libros || [];
  if (!busqueda.value) return lista;
  const b = busqueda.value.toLowerCase();
  return lista.filter(l => l.titulo.toLowerCase().includes(b) || l.autor.toLowerCase().includes(b));
});
</script>

<template>
  <Teleport to="body">
    <div v-if="usuario" class="fixed inset-0 bg-stone-950/80 backdrop-blur-md z-100 flex items-center justify-center p-4">
      
      <div class="bg-stone-50 w-full max-w-xl rounded-[2.5rem] shadow-2xl border-t-8 border-stone-900 overflow-hidden flex flex-col max-h-[90vh]">
        
        <div class="p-8 pb-4">
          <div class="flex justify-between items-start mb-2">
            <div>
              <span class="bg-stone-200 text-stone-600 text-[10px] font-black uppercase tracking-[0.2em] px-3 py-1 rounded-full">Historial de Lector</span>
              <h3 class="text-3xl font-black text-stone-900 mt-2 tracking-tighter">{{ usuario.nombre }}</h3>
            </div>
            <button @click="emit('cerrar')" class="bg-stone-200 hover:bg-stone-900 hover:text-white text-stone-600 p-2 rounded-2xl transition-all">
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="3" stroke="currentColor" class="w-5 h-5"><path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" /></svg>
            </button>
          </div>
          <p class="text-stone-400 text-sm font-medium">Consulta el registro de obras asignadas a este perfil.</p>
        </div>

        <div class="px-8 mb-4">
          <div class="relative group">
            <input v-model="busqueda" placeholder="Filtrar por título..." 
                   class="w-full bg-stone-100 border-none rounded-2xl py-3 pl-12 pr-4 text-stone-700 font-bold placeholder:text-stone-300 focus:ring-2 focus:ring-stone-900 transition-all outline-none text-sm"/>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="absolute left-4 top-3 w-5 h-5 text-stone-300 group-focus-within:text-stone-900 transition-colors">
              <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
            </svg>
          </div>
        </div>

        <div class="grow overflow-y-auto px-8 py-2 custom-scrollbar">
          <div v-if="librosFiltrados.length > 0" class="space-y-4 pb-6">
            <div v-for="l in librosFiltrados" :key="l.id" 
                 class="group bg-white border border-stone-100 p-5 rounded-3xl flex items-center gap-5 shadow-sm hover:shadow-md hover:-translate-y-1 transition-all">
              
              <div class="w-14 h-14 bg-stone-50 rounded-2xl flex items-center justify-center text-stone-300 border border-stone-100 group-hover:bg-stone-900 group-hover:text-white transition-colors">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-7 h-7">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M12 6.042A8.967 8.967 0 0 0 6 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 0 1 6 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 0 1 6-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0 0 18 18a8.967 8.967 0 0 0-6 2.292m0-14.25v14.25" />
                </svg>
              </div>

              <div>
                <h4 class="font-black text-stone-800 leading-tight">{{ l.titulo }}</h4>
                <p class="text-xs font-bold text-stone-400 mt-1 flex items-center gap-1 uppercase tracking-tighter">
                  <span class="w-1.5 h-1.5 bg-emerald-500 rounded-full"></span>
                  {{ l.autor }}
                </p>
              </div>
            </div>
          </div>

          <div v-else class="flex flex-col items-center justify-center py-16 text-center">
            <div class="bg-stone-100 p-6 rounded-full mb-4">
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1" stroke="currentColor" class="w-12 h-12 text-stone-300">
                <path stroke-linecap="round" stroke-linejoin="round" d="M12 6.042A8.967 8.967 0 0 0 6 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 0 1 6 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 0 1 6-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0 0 18 18a8.967 8.967 0 0 0-6 2.292m0-14.25v14.25" />
              </svg>
            </div>
            <p class="text-stone-400 font-black italic tracking-tight">Sin registros que mostrar</p>
          </div>
        </div>

        <div class="p-8 pt-4">
          <button @click="emit('cerrar')" class="w-full bg-stone-900 text-white py-4 rounded-2xl font-black text-sm uppercase tracking-widest shadow-xl shadow-stone-900/20 hover:scale-[1.02] active:scale-95 transition-all">
            Volver al Panel
          </button>
        </div>

      </div>
    </div>
  </Teleport>
</template>

<style scoped>
.custom-scrollbar::-webkit-scrollbar { width: 4px; }
.custom-scrollbar::-webkit-scrollbar-track { background: transparent; }
.custom-scrollbar::-webkit-scrollbar-thumb { background: #e7e5e4; border-radius: 10px; }
.custom-scrollbar::-webkit-scrollbar-thumb:hover { background: #d6d3d1; }
</style>