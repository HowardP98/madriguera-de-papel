<script setup lang="ts">
defineProps<{
  titulo: string;
  mensaje: string;
  tipo?: 'exito' | 'error';
}>();

const emit = defineEmits(['cerrar']);
</script>

<template>
  <Teleport to="body">
    <div class="fixed inset-0 bg-stone-900/70 backdrop-blur-md z-50 flex items-center justify-center p-4 animate-fade-in">
      <div class="bg-white w-full max-w-sm rounded-4xl p-8 shadow-2xl border border-stone-100 text-center transform transition-all scale-100">

        <div class="w-20 h-20 rounded-full flex items-center justify-center mx-auto mb-6 shadow-inner border"
             :class="tipo === 'error' ? 'bg-red-50 text-red-500 border-red-100' : 'bg-emerald-50 text-emerald-500 border-emerald-100'">
          
          <svg v-if="tipo === 'error'" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-10 h-10">
            <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3Z" />
          </svg>

          <svg v-else xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-10 h-10">
            <path stroke-linecap="round" stroke-linejoin="round" d="M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
          </svg>
        </div>

        <h3 class="text-2xl font-black text-stone-900 mb-2 tracking-tight">{{ titulo }}</h3>
        <p class="text-stone-500 font-medium mb-8">{{ mensaje }}</p>
        
        <button @click="emit('cerrar')" class="w-full px-4 py-3.5 rounded-xl font-bold text-white bg-stone-900 hover:bg-stone-950 shadow-md shadow-stone-900/20 transition-colors">
          Entendido
        </button>

      </div>
    </div>
  </Teleport>
</template>

<style scoped>
.animate-fade-in { animation: fadeIn 0.2s ease-out; }
@keyframes fadeIn { from { opacity: 0; transform: scale(0.95); } to { opacity: 1; transform: scale(1); } }
</style>