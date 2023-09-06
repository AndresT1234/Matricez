package upb.Matricez;

public class Matriz {

    private int filas;
    private int columnas;
    private int[][] matrix;

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

    // falta
    public Matriz producto(Matriz otraMatriz) {
        Matriz resultado = new Matriz(getfila(), getColunmas());
        return resultado;
    }

    public static void main(String[] args) {

        Matriz matrix1 = new Matriz(3, 4);
        Matriz matrix2 = new Matriz(3, 4);

        System.out.println("Matrix 1:");
        System.out.println(matrix1.toString());

        System.out.println("Matrix 2:");
        System.out.println(matrix2.toString());

        try {
            Matriz suma = matrix1.sumar(matrix2);
            System.out.println("Resultado de la suma:");
            System.out.println(suma.toString());
        } catch (Exception upss) {
            System.out.println("Error :(  " + upss.getMessage());
        }

    }

}
