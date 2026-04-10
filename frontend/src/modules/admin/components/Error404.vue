<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter();

const volverAlInicio = () => {
    router.push('/'); 
};
</script>

<template>
  <div class="min-h-screen bg-stone-100 flex flex-col items-center justify-center px-6 text-center relative overflow-hidden">
    
    <div class="absolute -top-32 -left-32 w-96 h-96 bg-stone-300 rounded-full mix-blend-multiply filter blur-3xl opacity-50"></div>
    <div class="absolute -bottom-32 -right-32 w-96 h-96 bg-stone-400 rounded-full mix-blend-multiply filter blur-3xl opacity-30"></div>

    <div class="relative z-10 max-w-lg">
        <h1 class="text-[10rem] leading-none font-black text-stone-900 drop-shadow-xl tracking-tighter">404</h1>
        
        <div class="bg-white p-8 rounded-3xl shadow-2xl border border-stone-200 mt-6 relative">
            <div class="absolute -top-6 left-1/2 -translate-x-1/2 w-12 h-12 bg-stone-200 text-stone-600 rounded-full flex items-center justify-center border-4 border-stone-100">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-6 h-6">
                  <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
                </svg>
            </div>
            
            <h2 class="text-2xl font-black text-stone-800 mt-4 mb-2">Página no encontrada</h2>
            <p class="text-stone-500 font-medium mb-8">
                Parece que te has adentrado demasiado en la madriguera. El documento o la ruta que buscas no existe.
            </p>
            
            <button @click="volverAlInicio" 
                    class="w-full bg-stone-900 text-white font-bold py-4 px-6 rounded-xl hover:bg-stone-950 transition-all shadow-md active:scale-95 flex items-center justify-center gap-2">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-5 h-5">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25" />
                </svg>
                Volver al inicio
            </button>
        </div>
    </div>

  </div>
</template>