package es.studium.miavatar;

public interface OnDialogoListener {
    public void onNombreElegido(String nombre);
    public void onSexoElegido(int sexo);            // 0 = Hombre, 1 = Mujer
    public void onEspecieElegida(int especie);      // 0 Elfo, 1 Enano, 2 Hobbit, 3 Humano
    public void onProfesionElegida(int profesion);  // 0 Arquero, 1 Guerrero, 2 Mago, 3 Herrero, 4 Minero
    public void onDialogoCancelado();
}