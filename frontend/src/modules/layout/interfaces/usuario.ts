import type { Libro } from './libro';

export interface Usuario {
  id?: number;
  nombre: string;    
  rol: string;       
  libros?: Libro[]; 
}