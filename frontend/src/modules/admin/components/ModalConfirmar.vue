<script setup lang="ts">
defineProps<{
  titulo: string;
  mensaje: string;
}>();

const emit = defineEmits(['confirmar', 'cancelar']);
</script>

<template>
  <Teleport to="body">
    <<div class="fixed inset-0 bg-stone-900/70 backdrop-blur-md z-50 flex items-center justify-center p-4 animate-fade-in">
    
      <div class="bg-white w-full max-w-sm rounded-4xl p-8 shadow-2xl border border-stone-100 text-center transform transition-all scale-100">
        
        <div class="w-20 h-20 bg-stone-100 text-stone-600 rounded-full flex items-center justify-center mx-auto mb-6 shadow-inner border border-stone-200">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-10 h-10">
            <path stroke-linecap="round" stroke-linejoin="round" d="M9.879 7.519c1.171-1.025 3.071-1.025 4.242 0 1.172 1.025 1.172 2.687 0 3.712-.203.179-.43.326-.67.442-.745.361-1.45.999-1.45 1.827v.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Zm-9 5.25h.008v.008H12v-.008Z" />
          </svg>
        </div>

        <h3 class="text-2xl font-black text-stone-900 mb-2 tracking-tight">{{ titulo }}</h3>
        <p class="text-stone-500 font-medium mb-8">{{ mensaje }}</p>
        
        <div class="flex gap-3 w-full">
          <button @click="emit('cancelar')" class="flex-1 px-4 py-3.5 rounded-xl font-bold text-stone-500 bg-stone-100 hover:bg-stone-200 transition-colors">
            Cancelar
          </button>
          <button @click="emit('confirmar')" class="flex-1 px-4 py-3.5 rounded-xl font-bold text-white bg-stone-900 hover:bg-stone-950 shadow-md shadow-stone-900/20 transition-colors">
            Confirmar
          </button>
        </div>

      </div>
    </div>
  </Teleport>
</template>

<style scoped>
.animate-fade-in { animation: fadeIn 0.2s ease-out; }
@keyframes fadeIn { from { opacity: 0; transform: scale(0.95); } to { opacity: 1; transform: scale(1); } }
</style>