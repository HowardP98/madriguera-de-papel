<script setup lang="ts">

import Navbar from './modules/layout/components/Navbar.vue';
import Footer from './modules/layout/components/Footer.vue';
import ModalAlerta from './modules/admin/components/ModalAlerta.vue'; 
import { alertaGlobal } from './router/alertaGlobal';
</script>

<template>
  <div class="min-h-screen bg-stone-100 text-stone-900 flex flex-col overflow-x-hidden">
    
    <Navbar />

    <main class="w-full grow flex flex-col"> 
      <router-view />
    </main>

    <Footer />

    <ModalAlerta 
        v-if="alertaGlobal.visible"
        :titulo="alertaGlobal.titulo"
        :mensaje="alertaGlobal.mensaje"
        :tipo="alertaGlobal.tipo"
        @cerrar="alertaGlobal.visible = false"
    />
    
  </div>
</template>

<style>
html, body {
  background-color: #f5f5f4 !important;
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}
</style>