<script setup lang="ts">
import type { Usuario } from '../../layout/interfaces/usuario.ts';

defineProps<{ usuarios: Usuario[] }>();
const emit = defineEmits(['verPrestamos']);
</script>

<template>
  <div class="bg-white shadow-2xl shadow-stone-200/50 rounded-3xl overflow-hidden border border-stone-100/50">
    <div class="overflow-x-auto">
      <table class="w-full text-left border-collapse">
        <thead>
          <tr class="bg-white border-b-2 border-stone-100">
            <th class="py-5 px-6 text-xs font-black text-stone-400 uppercase tracking-[0.15em]">Ref</th>
            <th class="py-5 px-6 text-xs font-black text-stone-400 uppercase tracking-[0.15em]">Identidad</th>
            <th class="py-5 px-6 text-xs font-black text-stone-400 uppercase tracking-[0.15em]">Privilegios</th>
            <th class="py-5 px-6 text-xs font-black text-stone-400 uppercase tracking-[0.15em] text-right">Actividad</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-stone-50">
          
          <tr v-for="user in usuarios" :key="user.id" class="group hover:bg-stone-50/50 transition-colors">
            
            <td class="py-5 px-6 text-stone-400 font-bold text-sm">
                {{ String(user.id).padStart(4, '0') }}
            </td>
            
            <td class="py-5 px-6 font-black text-stone-800 text-lg">
                {{ user.nombre }}
            </td>
            
            <td class="py-5 px-6">
                <div class="inline-flex items-center gap-2 font-bold text-xs tracking-wide">
                    <span class="w-2.5 h-2.5 rounded-full"
                          :class="{
                              'bg-blue-500 shadow-[0_0_8px_rgba(59,130,246,0.6)]': user.rol === 'ALUMNO',
                              'bg-purple-500 shadow-[0_0_8px_rgba(168,85,247,0.6)]': user.rol === 'PROFESOR',
                              'bg-rose-500 shadow-[0_0_8px_rgba(244,63,94,0.6)]': user.rol === 'ADMIN'
                          }">
                    </span>
                    <span :class="{
                        'text-blue-700': user.rol === 'ALUMNO',
                        'text-purple-700': user.rol === 'PROFESOR',
                        'text-rose-700': user.rol === 'ADMIN'
                    }">{{ user.rol }}</span>
                </div>
            </td>
            
            <td class="py-5 px-6 text-right">
              <button @click="emit('verPrestamos', user)" 
                      class="inline-flex items-center justify-center gap-2 bg-transparent text-stone-400 hover:text-stone-900 px-4 py-2.5 rounded-xl font-bold border-2 border-stone-200 hover:border-stone-900 transition-all">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-5 h-5"><path stroke-linecap="round" stroke-linejoin="round" d="M12 6.042A8.967 8.967 0 0 0 6 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 0 1 6 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 0 1 6-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0 0 18 18a8.967 8.967 0 0 0-6 2.292m0-14.25v14.25" /></svg>
                  Prestamos
              </button>
            </td>
          </tr>
          
          <tr v-if="usuarios.length === 0">
              <td colspan="4" class="py-16 text-center">
                  <span class="text-stone-400 font-bold text-lg">No hay lectores registrados en el sistema.</span>
              </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>