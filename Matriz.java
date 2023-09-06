package upb.Matricez;

import edu.princeton.cs.algs4.StdOut;

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

    public void setMatrix(int[][] x) {
        matrix = x;
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

    @Override
    public boolean equals(Object otraMatriz) {

        if (otraMatriz == null)
            return false;
        Matriz nuevito = (Matriz) otraMatriz;

        // comprobando dimensiones iguales
        if (this.filas != nuevito.filas || this.columnas != nuevito.columnas)
            return false;

        // comprobando componente por componente
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (this.matrix[i][j] != nuevito.matrix[i][j])
                    return false;
            }
        }
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

    public static void main(String[] args) throws Exception {

        Matriz matrix1 = new Matriz(3, 4);
        Matriz matrix2 = new Matriz(3, 4);

        StdOut.println("\nMatrix 1:");
        StdOut.println(matrix1.toString());

        StdOut.println("Matrix 2:");
        StdOut.println(matrix2.toString());

        Matriz matrixA = new Matriz();
        int [][] matrizA = {{2,2,2},{2, 2, 2}, {2,2,2}, {2,2,2}};
        matrixA.setMatrix(matrizA);

        Matriz matrixB = new Matriz();
        int [][] matrizB = {{2,2,2},{2, 2, 2}, {2,2,2}, {2,2,2}};
        matrixB.setMatrix(matrizB);

        Matriz matrixC = new Matriz();
        int [][] matrizC = {{4,4,4},{4, 4, 4}, {4,4,4}, {4,4,4}};
        matrixC.setMatrix(matrizC);

        Matriz sumado = matrixA.sumar(matrixB);

        assert(matrixC.equals(sumado));

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
        String integrantes[] = { "000491739", "000493912" };
        StdOut.println("--------------INTEGRANTES----------------");
        StdOut.println("\n    EL GRUPO QUE NOS TOCO FUE EL: " + equipo.hashEquipo(integrantes) + "\n");
        StdOut.println("-----------------------------------------\n");

    }

}
