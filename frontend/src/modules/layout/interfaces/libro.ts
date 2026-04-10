export interface Libro{
    id: number;
    titulo: string;
    autor: string;
    isbn: string;
    anioPublicacion: number;
    editorial: string;
    estadoLibro: 'DISPONIBLE' | 'PRESTADO' | 'RESERVADO';
}