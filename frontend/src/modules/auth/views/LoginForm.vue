<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { realizarLogin } from '../actions/auth.actions'; 
import imgLogoLogin from '../../../assets/login/login-mapache.png';

const router = useRouter();

const username = ref('');
const password = ref('');
const errorMsg = ref('');
const loading = ref(false);

const handleLogin = async () => {
  loading.value = true;
  errorMsg.value = '';
  
  try {
    const { rol, token } = await realizarLogin(username.value, password.value);
    
    // Guardar sesion
    localStorage.setItem('auth', token);
    localStorage.setItem('role', rol);
    
    // Roles
    if (rol === 'ADMIN') {
        router.push('/admin');
    } else {
        router.push('/user');
    }

  } catch (error: any) {
    if (error.response && error.response.status === 401) {
        errorMsg.value = 'Usuario o contraseña incorrectos.'; 
    } else {
        errorMsg.value = 'Error de conexión (Revisa el backend)';
    }
  } finally {
    loading.value = false;
  }
};
</script>

<template>
  <div class="flex items-center justify-center min-h-screen bg-stone-100 relative overflow-hidden px-4">
    
    <div class="absolute -top-32 -left-32 w-96 h-96 bg-stone-300 rounded-full mix-blend-multiply filter blur-3xl opacity-50"></div>
    <div class="absolute -bottom-32 -right-32 w-96 h-96 bg-stone-400 rounded-full mix-blend-multiply filter blur-3xl opacity-30"></div>

    <div class="bg-white border-t-8 border-stone-800 p-10 rounded-xl shadow-2xl w-full max-w-md relative z-10">
        
        <div class="text-center mb-10">
            <img :src="imgLogoLogin" alt="Logo" class="w-32 h-auto mx-auto mb-6" />
            <h2 class="text-3xl font-extrabold text-stone-900 tracking-tight">Acceso Lectores</h2>
            <p class="text-stone-500 mt-2 text-sm">Introduce tus credenciales para continuar</p>
        </div>

        <form @submit.prevent="handleLogin" class="space-y-6">
            <div>
                <label class="block text-stone-700 font-semibold text-sm mb-2">Usuario</label>
                <input v-model="username" type="text" placeholder="Ej: admin" required
                       class="w-full bg-stone-50 border border-stone-300 text-stone-900 p-3 rounded-lg focus:border-stone-800 focus:ring-1 focus:ring-stone-800 focus:outline-none transition-all shadow-sm placeholder-stone-400"/>
            </div>
            
            <div>
                <label class="block text-stone-700 font-semibold text-sm mb-2">Contraseña</label>
                <input v-model="password" type="password" placeholder="••••••••" required
                       class="w-full bg-stone-50 border border-stone-300 text-stone-900 p-3 rounded-lg focus:border-stone-800 focus:ring-1 focus:ring-stone-800 focus:outline-none transition-all shadow-sm placeholder-stone-400"/>
            </div>

            <div v-if="errorMsg" class="bg-red-50 border-l-4 border-red-500 text-red-700 text-sm p-3 rounded font-medium flex items-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" viewBox="0 0 20 20" fill="currentColor">
                  <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7 4a1 1 0 11-2 0 1 1 0 012 0zm-1-9a1 1 0 00-1 1v4a1 1 0 102 0V6a1 1 0 00-1-1z" clip-rule="evenodd" />
                </svg>
                {{ errorMsg }}
            </div>

            <button type="submit" 
                    :disabled="loading"
                    class="w-full font-bold py-3 px-4 rounded-lg transform transition-all duration-200 mt-4
                           bg-stone-800 text-stone-100 shadow-md
                           hover:bg-stone-900 hover:shadow-lg focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-stone-800
                           disabled:opacity-70 disabled:cursor-not-allowed flex justify-center items-center">
                <span v-if="loading" class="flex items-center">
                    <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                        <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                        <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                    </svg>
                    Verificando...
                </span>
                <span v-else>Iniciar Sesión</span>
            </button>
        </form>
    </div>
  </div>
</template>

<style scoped>
</style>