/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/**/*.{html,js,ts,jsx,tsx}',   // Asegura que tailwindbusque en todos los archivos dentro de src
    './resources/templates/**/*.html',   // Archivos HTML de Thymeleaf
    './src/main/resources/static/**/*.{html,js,css}', // Archivos estáticos generados en estáticos
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}
