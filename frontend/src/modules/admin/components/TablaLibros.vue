<script setup lang="ts">
import type { Libro } from '../../layout/interfaces/libro.ts';

defineProps<{ libros: Libro[] }>();
const emit = defineEmits(['editar', 'eliminar']);
</script>

<template>
  <div class="bg-white shadow-2xl shadow-stone-200/50 rounded-3xl overflow-hidden border border-stone-100/50">
    <div class="overflow-x-auto">
      <table class="w-full text-left border-collapse">
        <thead>
          <tr class="bg-white border-b-2 border-stone-100">
            <th class="py-5 px-6 text-xs font-black text-stone-400 uppercase tracking-[0.15em]">Ref</th>
            <th class="py-5 px-6 text-xs font-black text-stone-400 uppercase tracking-[0.15em]">Obra</th>
            <th class="py-5 px-6 text-xs font-black text-stone-400 uppercase tracking-[0.15em]">Publicación</th>
            <th class="py-5 px-6 text-xs font-black text-stone-400 uppercase tracking-[0.15em] text-center">Disponibilidad</th>
            <th class="py-5 px-6 text-xs font-black text-stone-400 uppercase tracking-[0.15em] text-right">Acciones</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-stone-50">
          
          <tr v-for="libro in libros" :key="libro.id" class="group hover:bg-stone-50/50 transition-colors">
            
            <td class="py-4 px-6 text-stone-400 font-bold text-sm">
                {{ String(libro.id).padStart(4, '0') }}
            </td>
            
            <td class="py-4 px-6">
                <div class="font-black text-stone-800 text-lg leading-tight">{{ libro.titulo }}</div>
                <div class="text-stone-500 font-medium text-sm mt-1">{{ libro.autor }}</div>
            </td>

            <td class="py-4 px-6">
                <div class="font-black text-stone-800 text-lg leading-tight">{{ libro.editorial }}</div>
                <div class="text-stone-500 font-medium text-sm mt-1">ISBN: {{ libro.isbn }}</div>
            </td>
            
            <td class="py-4 px-6 text-center">
                <div class="inline-flex items-center gap-2 font-bold text-sm"
                     :class="{'text-emerald-600': libro.estadoLibro === 'DISPONIBLE', 'text-stone-400': libro.estadoLibro === 'PRESTADO', 'text-amber-500': libro.estadoLibro === 'RESERVADO'}">
                    <span class="w-2.5 h-2.5 rounded-full"
                          :class="{'bg-emerald-500 shadow-[0_0_10px_rgba(16,185,129,0.5)]': libro.estadoLibro === 'DISPONIBLE', 'bg-stone-300': libro.estadoLibro === 'PRESTADO', 'bg-amber-500 shadow-[0_0_10px_rgba(245,158,11,0.5)]': libro.estadoLibro === 'RESERVADO'}">
                    </span>
                    {{ libro.estadoLibro }}
                </div>
            </td>
            
            <td class="py-4 px-6 text-right">
              <div class="flex justify-end gap-1 opacity-60 group-hover:opacity-100 transition-opacity">
                
                <template v-if="libro.estadoLibro === 'DISPONIBLE'">
                    <button @click="emit('editar', libro)" class="p-2 text-stone-400 hover:text-blue-600 hover:bg-blue-50 rounded-xl transition-all" title="Editar">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-5 h-5"><path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" /></svg>
                    </button>
                    <button @click="emit('eliminar', libro.id)" class="p-2 text-stone-400 hover:text-red-600 hover:bg-red-50 rounded-xl transition-all" title="Eliminar">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-5 h-5"><path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" /></svg>
                    </button>
                </template>
                
                <template v-else>
                    <div class="p-2 text-stone-200" title="Bloqueado">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-5 h-5"><path stroke-linecap="round" stroke-linejoin="round" d="M16.5 10.5V6.75a4.5 4.5 0 1 0-9 0v3.75m-.75 11.25h10.5a2.25 2.25 0 0 0 2.25-2.25v-6.75a2.25 2.25 0 0 0-2.25-2.25H6.75a2.25 2.25 0 0 0-2.25 2.25v6.75a2.25 2.25 0 0 0 2.25 2.25Z" /></svg>
                    </div>
                </template>
              </div>
            </td>
          </tr>
          
          <tr v-if="libros.length === 0">
              <td colspan="5" class="py-16 text-center">
                  <span class="text-stone-400 font-bold text-lg">El catálogo está vacío.</span>
              </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>