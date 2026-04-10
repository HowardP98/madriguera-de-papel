<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter();

const volverAlSitioSeguro = () => {
    const role = localStorage.getItem('role');
    if (role === 'ADMIN') {
        router.push('/admin');
    } else if (role === 'USER' || role === 'ALUMNO' || role === 'PROFESOR') {
        router.push('/user');
    } else {
        router.push('/login');
    }
};
</script>

<template>
  <div class="min-h-screen bg-stone-100 flex flex-col items-center justify-center px-6 text-center relative overflow-hidden">
    
    <div class="absolute -top-32 -left-32 w-96 h-96 bg-stone-300 rounded-full mix-blend-multiply filter blur-3xl opacity-50"></div>
    <div class="absolute -bottom-32 -right-32 w-96 h-96 bg-stone-400 rounded-full mix-blend-multiply filter blur-3xl opacity-30"></div>

    <div class="relative z-10 max-w-lg">
        <h1 class="text-[10rem] leading-none font-black text-stone-900 drop-shadow-xl tracking-tighter">403</h1>
        
        <div class="bg-white p-8 rounded-3xl shadow-2xl border border-stone-200 mt-6 relative">
            <div class="absolute -top-6 left-1/2 -translate-x-1/2 w-12 h-12 bg-red-100 text-red-600 rounded-full flex items-center justify-center border-4 border-stone-100">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-6 h-6">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3Z" />
                </svg>
            </div>
            
            <h2 class="text-2xl font-black text-stone-800 mt-4 mb-2">Acceso Restringido</h2>
            <p class="text-stone-500 font-medium mb-8">
                Parece que te has perdido en la madriguera. Tu perfil actual no tiene los permisos necesarios para explorar esta zona.
            </p>
            
            <button @click="volverAlSitioSeguro" 
                    class="w-full bg-stone-900 text-white font-bold py-4 px-6 rounded-xl hover:bg-stone-950 transition-all shadow-md active:scale-95 flex items-center justify-center gap-2">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-5 h-5">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M9 15 3 9m0 0 6-6M3 9h12a6 6 0 0 1 0 12h-3" />
                </svg>
                Volver a mi zona segura
            </button>
        </div>
    </div>

  </div>
</template>