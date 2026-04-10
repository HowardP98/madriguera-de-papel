import { ref } from 'vue';

export const alertaGlobal = ref({
    visible: false,
    titulo: '',
    mensaje: '',
    tipo: 'error' as 'exito' | 'error'
});

export const dispararAlerta = (titulo: string, mensaje: string, tipo: 'exito' | 'error' = 'error') => {
    alertaGlobal.value = { visible: true, titulo, mensaje, tipo };
};