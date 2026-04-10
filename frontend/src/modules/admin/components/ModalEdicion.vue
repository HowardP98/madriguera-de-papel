<script setup lang="ts">
import type { Libro } from '../../layout/interfaces/libro.ts';
defineProps<{ libro: Libro }>();
const emit = defineEmits(['cerrar', 'guardar']);
</script>

<template>
  <Teleport to="body">
    <div class="fixed inset-0 bg-stone-900/70 backdrop-blur-sm z-50 flex items-center justify-center p-4">
      <div class="bg-stone-50 w-full max-w-2xl rounded-2xl p-8 shadow-2xl border border-stone-300">
        <h3 class="text-2xl font-extrabold text-stone-900 mb-6 border-b border-stone-200 pb-2">Editar Libro</h3>
        
        <form @submit.prevent="emit('guardar')" class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div class="flex flex-col gap-1">
            <label class="text-xs font-semibold text-stone-600 uppercase">Título</label>
            <input v-model="libro.titulo" class="p-3 rounded-lg border border-stone-300 outline-none focus:border-stone-800 focus:ring-1 focus:ring-stone-800" required />
          </div>
          <div class="flex flex-col gap-1">
            <label class="text-xs font-semibold text-stone-600 uppercase">Autor</label>
            <input v-model="libro.autor" class="p-3 rounded-lg border border-stone-300 outline-none focus:border-stone-800 focus:ring-1 focus:ring-stone-800" required />
          </div>
          <div class="flex flex-col gap-1">
            <label class="text-xs font-semibold text-stone-600 uppercase">ISBN</label>
            <input v-model="libro.isbn" class="p-3 rounded-lg border border-stone-300 outline-none focus:border-stone-800 focus:ring-1 focus:ring-stone-800" required />
          </div>
          <div class="flex flex-col gap-1">
            <label class="text-xs font-semibold text-stone-600 uppercase">Editorial</label>
            <input v-model="libro.editorial" class="p-3 rounded-lg border border-stone-300 outline-none focus:border-stone-800 focus:ring-1 focus:ring-stone-800" required />
          </div>
          
          <div class="col-span-1 md:col-span-2 flex justify-end gap-3 mt-4">
            <button type="button" @click="emit('cerrar')" class="px-6 py-2.5 rounded-lg text-stone-600 hover:bg-stone-200 font-semibold transition-colors">Cancelar</button>
            <button type="submit" class="bg-stone-800 text-stone-100 px-6 py-2.5 rounded-lg font-bold shadow-md hover:bg-stone-900 transition-colors">Guardar Cambios</button>
          </div>
        </form>
      </div>
    </div>
  </Teleport>
</template>