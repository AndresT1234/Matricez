using System;

namespace Matricez
{
    class Matriz
    {
        private int filas;
        private int columnas;
        private int[,] matrix;

        public Matriz()
        {
        }

        public Matriz(int filas, int columnas)
        {
            this.filas = filas;
            this.columnas = columnas;
            this.matrix = new int[filas, columnas];
            InicializarMatriz();
        }

        public int GetFilas()
        {
            return filas;
        }

        public int GetColumnas()
        {
            return columnas;
        }

        public void SetMatrix(int[,] x)
        {
            matrix = x;
        }

        public override string ToString()
        {
            string resultado = "";
            for (int i = 0; i < filas; i++)
            {
                for (int j = 0; j < columnas; j++)
                {
                    resultado += matrix[i, j] + " ";
                }
                resultado += "\n";
            }
            return resultado;
        }

        public bool equals(object otraMatriz)
        {
            if (otraMatriz == null)
                return false;

            Matriz nuevito = (Matriz)otraMatriz;

            // Comprobando dimensiones iguales
            if (this.filas != nuevito.filas || this.columnas != nuevito.columnas)
                return false;

            // Comprobando componente por componente
            for (int i = 0; i < filas; i++)
            {
                for (int j = 0; j < columnas; j++)
                {
                    if (this.matrix[i, j] != nuevito.matrix[i, j])
                        return false;
                }
            }
            return true;
        }

        public void InicializarMatriz()
        {
            Random random = new Random(DateTime.Now.Millisecond);//un milisegundo del tiempo actual, una semilla al metodo
            for (int i = 0; i < filas; i++)
            {
                for (int j = 0; j < columnas; j++)
                {
                    this.matrix[i, j] = random.Next(6);
                }
            }
        }

        public int HashEquipo(string[] idMiembros)
        {
            int resultado = 0;
            int valor;
            foreach (string amiguitos in idMiembros)
            {
                valor = amiguitos.GetHashCode();
                resultado += valor;
                // Console.WriteLine($"Para {amiguitos} el valor es {valor} y el resultado va en ... {resultado}"); prueba
            }
            return Math.Abs(resultado % 5); //como da -4, se saca el valor absoluta ya que el hascode me da un valor con signo
        }

        public Matriz Sumar(Matriz nuevamatriz)
        {
            if (this.filas != nuevamatriz.filas || this.columnas != nuevamatriz.columnas)
            {
                throw new Exception("Las matrices deben tener las mismas dimensiones para poder realizar la suma");
            }

            Matriz resultado = new Matriz(GetFilas(), GetColumnas());

            for (int i = 0; i < GetFilas(); i++)
            {
                for (int j = 0; j < GetColumnas(); j++)
                {
                    resultado.matrix[i, j] = this.matrix[i, j] + nuevamatriz.matrix[i, j];
                }
            }
            return resultado;
        }

        public Matriz Multiplicar(Matriz nuevamatriz)
        {
            if (GetColumnas() != nuevamatriz.filas)
            {
                return null;
            }

            Matriz resultado = new Matriz(GetFilas(), nuevamatriz.columnas);

            for (int i = 0; i < GetFilas(); i++)
            {
                for (int j = 0; j < nuevamatriz.columnas; j++)
                {
                    int suma = 0;
                    for (int k = 0; k < GetColumnas(); k++)
                    {
                        suma += this.matrix[i, k] * nuevamatriz.matrix[k, j];
                    }
                    resultado.matrix[i, j] = suma;
                }
            }
            return resultado;
        }

        public Matriz Potencia(int potencia)
        {
            if (GetFilas() != GetColumnas())
            {
                throw new Exception("La matriz no es cuadrada, no se pueden calcular potencias.");
            }

            Matriz resultado = new Matriz(GetFilas(), GetColumnas());

            // Copiar la matriz original en baseMatriz
            Matriz baseMatriz = new Matriz(GetFilas(), GetColumnas());

            for (int i = 0; i < GetFilas(); i++)
            {
                for (int j = 0; j < GetColumnas(); j++)
                {
                    baseMatriz.matrix[i, j] = this.matrix[i, j];
                }
            }

            // Inicializar la matriz resultado como la matriz identidad
            for (int i = 0; i < filas; i++)
            {
                resultado.matrix[i, i] = 1;
            }

            while (potencia > 0)
            {
                if (potencia % 2 == 1)
                {
                    resultado = resultado.Multiplicar(baseMatriz);
                }
                baseMatriz = baseMatriz.Multiplicar(baseMatriz);
                potencia /= 2;
            }

            return resultado;
        }

        public static void Main(string[] args)
        {

            Matriz matrixA = new Matriz();
            int[,] matrizAArray = { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } };
            matrixA.SetMatrix(matrizAArray);

            Matriz matrixB = new Matriz();
            int[,] matrizBArray = { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } };
            matrixB.SetMatrix(matrizBArray);

            Matriz matrixC = new Matriz();
            int[,] matrizCArray = { { 4, 4, 4 }, { 4, 4, 4 }, { 4, 4, 4 }, { 4, 4, 4 } };
            matrixC.SetMatrix(matrizCArray);

            Matriz sumado = matrixA.Sumar(matrixB);

            if (matrixC.Equals(sumado))
            {
                Console.WriteLine("La suma es igual a la matriz C.\n");
            }
            else
            {
                Console.WriteLine("La suma no es igual a la matriz C.\n");
            }

            Matriz matrix1 = new Matriz(3, 3);
            Matriz matrix2 = new Matriz(3, 3);

            Console.WriteLine("\nMatrix 1:");
            Console.WriteLine(matrix1.ToString());

            Console.WriteLine("Matrix 2:");
            Console.WriteLine(matrix2.ToString());

            Matriz suma = matrix1.Sumar(matrix2);
            Console.WriteLine("Resultado de la suma:");
            Console.WriteLine(suma.ToString() + "\n");

            Matriz multiplicacion = matrix1.Multiplicar(matrix2);
            Console.WriteLine("Resultado de la multiplicación:");
            Console.WriteLine(multiplicacion.ToString());

            Matriz equipo = new Matriz();
            string[] integrantes = { "491739", "493912" };
            Console.WriteLine("--------------INTEGRANTES----------------");
            Console.WriteLine("\n    EL GRUPO QUE NOS TOCÓ FUE EL: " + equipo.HashEquipo(integrantes) + "\n");
            Console.WriteLine("-----------------------------------------\n");

            try
            {
                Console.WriteLine("Calcular potencias de una matriz cuadrada\n");
                int potencia = 4; // Potencia 
                Matriz matrizOriginal = matrix1.Potencia(potencia);
                // Imprime la matriz resultado 
                Console.WriteLine("matriz1^" + potencia + ":\n");
                Console.WriteLine(matrizOriginal.ToString());
                
            }
            catch (Exception upss)
            {
                Console.WriteLine("Tuvimos un error :( --> " + upss.Message);
            }

        }
    }
}