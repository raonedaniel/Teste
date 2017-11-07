package classededados;

public class Matriz {
    //ATRIBUTOS.
    private int quantidadeDeLinhas = 0;
    private int quantidadeDeColunas = 0;
    private int[][] mat = null;
    
    
    //CONSTRUTOR.
    public Matriz(int qLinha,int qColuna) throws Exception{
        if(qLinha < 0) throw new Exception ("Quantidade de linhas não pode ser negativa.");
        if(qColuna < 0) throw new Exception ("Quantidade de colunas não pode ser negativa.");
        if(qLinha == 0) throw new Exception ("Quantidade de linhas não pode ser zero.");
        if(qColuna == 0) throw new Exception ("Quantidade de colunas não pode ser zero.");
        this.quantidadeDeLinhas = qLinha;
        this.quantidadeDeColunas = qColuna;
        this.mat = new int[quantidadeDeLinhas][quantidadeDeColunas];
    }
    
    //MÉTODOS.

    public int getQuantidadeDeLinhas() {
        return quantidadeDeLinhas;
    }

    public int getQuantidadeDeColunas() {
        return quantidadeDeColunas;
    }
    public int getElemento(int linha, int coluna) throws Exception{
        if(linha >= (quantidadeDeLinhas) && linha < 0) throw new Exception("Linha inexistente.");
        if(coluna >= quantidadeDeColunas && coluna < 0) throw new Exception("Coluna inexistente.");
        return mat[linha][coluna];
    }
    public String getMatriz(){
        String saida = "";
        for (int linha = 0; linha < quantidadeDeLinhas; linha++) {
            for (int coluna = 0; coluna < quantidadeDeColunas; coluna++) {
                saida += mat[linha][coluna] + " ";
            }
            saida += "\n";
        }
        return saida;
    }
    public void setElemento(int linha, int coluna, int elemento) throws Exception{
        if(linha >= (quantidadeDeLinhas) && linha < 0) throw new Exception("Linha inexistente.");
        if(coluna >= quantidadeDeColunas && coluna < 0) throw new Exception("Coluna inexistente.");
        this.mat[linha][coluna] = elemento;
    }
    
    public Matriz adicionar(Matriz objeto) throws Exception{
        if(!(this.quantidadeDeColunas == objeto.quantidadeDeColunas && this.quantidadeDeLinhas == objeto.quantidadeDeLinhas)) throw new Exception("Para adição, quantidade de linhas e colunas de ambas as matrizes precisam ser iguais.");
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        for (int i = 0; i < this.quantidadeDeLinhas; i++) {
            for (int j = 0; j < this.quantidadeDeColunas; j++) {
                aux.mat[i][j] = this.mat[i][j] + objeto.mat[i][j];
            }
        }
        return aux;
    }
    public Matriz subtrair(Matriz objeto) throws Exception{
        if(!(this.quantidadeDeColunas == objeto.quantidadeDeColunas && this.quantidadeDeLinhas == objeto.quantidadeDeLinhas)) throw new Exception("Para subtração, quantidade de linhas e colunas de ambas as matrizes precisam ser iguais.");
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        for (int i = 0; i < this.quantidadeDeLinhas; i++) {
            for (int j = 0; j < this.quantidadeDeColunas; j++) {
                aux.mat[i][j] = this.mat[i][j] - objeto.mat[i][j];
            }
        }
        return aux;
    }
    public Matriz multiplicar(Matriz objeto) throws Exception{
        if(this.quantidadeDeColunas != objeto.quantidadeDeLinhas) throw new Exception ("Para multiplicação de matrizes o número de colunas da 1ª matriz deve ser igual ao número de linhas da 2ª.");
        Matriz aux = new Matriz(this.quantidadeDeLinhas, objeto.quantidadeDeColunas);
        int soma = 0;
        for (int i = 0; i < this.quantidadeDeLinhas; i++) {
            for (int j = 0; j < objeto.quantidadeDeColunas; j++) {
                    soma = 0;
                for (int k = 0; k < this.quantidadeDeColunas; k++) {
                    soma += this.mat[i][k]*objeto.mat[k][j];
                    aux.mat[i][j] = soma;
                }
            }
        }
        return aux;
    }
    public boolean eTiangularInferior(){
        if(quantidadeDeColunas != quantidadeDeLinhas) return false;
        else {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    if (i < j && mat[i][j] != 0) return false;
                }
            }
            return true;
        }
    }
    public boolean eTiangularSuperior(){
        if(quantidadeDeColunas != quantidadeDeLinhas) return false;
        else {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    if (i > j && mat[i][j] != 0) return false;
                }
            }
            return true;
        }
    }
    public boolean eSimetrica(){
        if(quantidadeDeColunas != quantidadeDeLinhas) return false;
        else { 
            for (int linha = 0; linha < mat.length; linha++) {
                for (int coluna = 0; coluna < mat.length; coluna++) {
                    if (mat[linha][coluna] != mat[coluna][linha]) return false;
                }
            }
            return true;
        }
    }
    public boolean eIndentidade(){
        if(quantidadeDeColunas != quantidadeDeLinhas) return false;
        else {
            for (int linha = 0; linha < quantidadeDeLinhas; linha++) {
                for (int coluna = 0; coluna < quantidadeDeColunas; coluna++) {
                    if(linha == coluna && mat[linha][coluna] != 1) return false;
                    else if (linha != coluna && mat[linha][coluna] != 0) return false;
                }
            }
            return true;
        }
    }
    public Matriz calcularTransposta() throws Exception{
        Matriz aux = new Matriz(this.quantidadeDeColunas, this.quantidadeDeLinhas);
        for (int i = 0; i < this.quantidadeDeColunas; i++) {
            for (int j = 0; j < this.quantidadeDeLinhas; j++) {
                aux.mat[i][j] = this.mat[j][i];
            }
        }
        return aux;
    }
    public Matriz calcularPotencia(int expoente) throws Exception{
        if(expoente < 0) throw new Exception ("Expoente não pode ser negativo. ");
        Matriz aux = new Matriz(quantidadeDeLinhas, quantidadeDeColunas);
        if(expoente == 0 ){
            for (int i = 0; i < quantidadeDeLinhas; i++) {
                for (int j = 0; j < quantidadeDeColunas; j++) {
                    if(i == j) aux.mat[i][j] = 1;
                    else aux.mat[i][j] = 0;
                }
            }
            return aux;
        }
        else if(expoente == 1) return this;
        else{
            for (int i = 0; i < quantidadeDeLinhas; i++) {
                for (int j = 0; j < quantidadeDeColunas; j++) {
                    aux.mat[i][j] = this.mat[i][j];
                }
            }
            for (int i = 0; i < expoente-1; i++) {
                aux = aux.multiplicar(this);
            }
            return aux;
        }
    }
    public Matriz multiplicaPorK(int constante) throws Exception{
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        for (int i = 0; i < this.quantidadeDeLinhas; i++) {
            for (int j = 0; j < quantidadeDeColunas; j++) {
                aux.mat[i][j] = this.mat[i][j] * constante; 
            }
        }
        return aux;
    }
    public boolean eIgual(Matriz objeto) throws Exception{
        for (int i = 0; i < quantidadeDeLinhas; i++) {
            for (int j = 0; j < quantidadeDeColunas; j++) {
                if(this.mat[i][j] != objeto.mat[i][j]) return false; 
            }
        }
        return true;
    }
    public boolean eDiferente(Matriz objeto){
        for (int i = 0; i < quantidadeDeLinhas; i++) {
            for (int j = 0; j < quantidadeDeColunas; j++) {
                if(this.mat[i][j] != objeto.mat[i][j]) return true; 
            }
        }
        return false;
    }
    public boolean eOrtogonal() throws Exception{
        if(this.quantidadeDeLinhas != this.quantidadeDeColunas) return false;
        return this.multiplicar(this.calcularTransposta()).eIndentidade();
    }
    public boolean ePermutacao() throws Exception{
        if(this.quantidadeDeLinhas != this.quantidadeDeColunas) return false;
        int soma = 0;
        int soma2 = 0;
        for (int i = 0; i < quantidadeDeLinhas; i++) {
            soma = 0;
            soma2 = 0;
            for (int j = 0; j < quantidadeDeColunas; j++) {
                if(this.mat[i][j] != 0 && this.mat[i][j] != 1) return false;
                soma += this.mat[i][j];
                soma2 += this.mat[j][i];
                if(soma > 1) return false;
                if(soma2 > 1) return false;
            }
        }
        return true;
    }
}
