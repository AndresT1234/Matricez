package upb.Matricez;

import edu.princeton.cs.algs4.StdOut; //veneco

public class Matriz {

    private int filas;
    private int columnas;
    private int[][] matrix;

    public Matriz() {
    }

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matrix = new int[filas][columnas];
        inicializarMatriz();

    }

    public int getfila() {
        return filas;
    }

    public int getColunmas() {
        return columnas;
    }

    public int[][] getmatrix() {
        return matrix;
    }

    @Override
    public String toString() {

        String resultado = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                resultado += matrix[i][j] + " ";
            }
            resultado += "\n";
        }
        return resultado;

    }

    // falta
    @Override
    public boolean equals(Object otraMatriz) {
        return true;
    }

    public void inicializarMatriz() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                this.matrix[i][j] = (int) (Math.random() * 5);
            }
        }
    }

    public int hashEquipo(String[] idMiembros) {
        int resultado = 0;
        for (String amiguitos : idMiembros) {
            resultado += amiguitos.hashCode();
        }
        return resultado % 5;
    }

    public Matriz sumar(Matriz nuevamatriz) throws Exception {
        if (this.filas != nuevamatriz.filas || this.columnas != nuevamatriz.columnas) {
            throw new Exception("Las matrices deben de tener la mismas dimensiónes para poder realizar la suma");
        }

        Matriz resultado = new Matriz(getfila(), getColunmas());

        for (int i = 0; i < getfila(); i++) {
            for (int j = 0; j < getColunmas(); j++) {
                resultado.matrix[i][j] = this.matrix[i][j] + nuevamatriz.matrix[i][j];
            }
        }
        return resultado;
    }

    public Matriz multiplicar(Matriz nuevamatriz) throws Exception {
        if (this.filas != nuevamatriz.filas || this.columnas != nuevamatriz.columnas) {
            throw new Exception(
                    "Las matrices deben de tener la mismas dimensiónes para poder realizar la multiplicacion");
        }

        Matriz resultado = new Matriz(getfila(), getColunmas());

        for (int i = 0; i < getfila(); i++) {
            for (int j = 0; j < getColunmas(); j++) {
                resultado.matrix[i][j] = this.matrix[i][j] * nuevamatriz.matrix[i][j];
            }
        }
        return resultado;
    }

    public static void main(String[] args) {

        Matriz matrix1 = new Matriz(3, 4);
        Matriz matrix2 = new Matriz(3, 4);

        StdOut.println("\nMatrix 1:");
        StdOut.println(matrix1.toString());

        StdOut.println("Matrix 2:");
        StdOut.println(matrix2.toString());

        try {

            Matriz suma = matrix1.sumar(matrix2);
            StdOut.println("Resultado de la suma:");
            StdOut.println(suma.toString() + "\n");

            Matriz multiplicacion = matrix1.multiplicar(matrix2);
            StdOut.println("Resultado de la multiplicacion:");
            StdOut.println(multiplicacion.toString());

        } catch (Exception upss) {
            StdOut.println("Tuvimos Error :(   --> " + upss.getMessage());
        }

        Matriz equipo = new Matriz();
        String integrantes[] = { "000493912", "000491739" };
        StdOut.println("--------------INTEGRANTES----------------");
        StdOut.println("\n    EL GRUPO QUE NOS TOCO FUE EL: " + equipo.hashEquipo(integrantes) + "\n");
        StdOut.println("-----------------------------------------\n");

    }

}
