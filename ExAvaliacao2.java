import java.util.Scanner;
public class ExAvaliacao2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Número de elementos da tabela: ");
        int n = sc.nextInt();
        if(n <= 2){
            System.out.print("Número de elementos inválido!");
            sc.close();
            return;
        }
        int[] tabela = new int[n];
        for(int i = 0; i < tabela.length; i++){
            System.out.printf("Elemento %d: ", i+1);
            tabela[i] = sc.nextInt();
        }
        sc.close();
        System.out.print("Tabela = ");
        ImprimeTabela(tabela);
        int[] subTabela = ProcurarSubCadeia(tabela);
        System.out.print("\nSub-Tabela = ");
        ImprimeTabela(subTabela);
    }

    public static void ImprimeTabela(int[] t){
        for(int i: t){
            System.out.printf("%d ", i);
        }
    }

    public static int MaxTabela(int[] t){
        int max = 0;
        for(int i: t){
            if(i > max){
                max = i;
            }
        }
        return max;
    }

    public static int[] ProcurarSubCadeia(int[] t){
        int[] tOrdenada = new int[t.length];
        // Descobrir o numero max da tabela e dar o valor de t[0] a tOrdenada[0] para ser comparada
        int Max = MaxTabela(t);
        tOrdenada[0] = t[0];
        // ordenar t em tOrdenada
        for(int i = 0; i < t.length; i++){
            int max = Max;
            for(int j = 0; j < t.length; j++){
                // se i = 0 entao significa que é o primeiro numero por isso tem de ser o mais pequeno da tabela
                if(i == 0){
                    if(t[j] < tOrdenada[i]){
                        tOrdenada[i] = t[j];
                    }
                }
                // se for diferente de 0 entao tOrdenada[i] tem de estar entre tOrdenada[i-1] e o max
                // o max está sempre a diminuir mas nunca é menor que tOrdenada[i-1]
                else{
                    if(tOrdenada[i-1] < t[j] && t[j] <= max){
                        max = t[j];
                        tOrdenada[i] = max;
                    }
                }
            }
        }
        // Verificar quais os numeros fora de ordem e adiciona-los a subcadeia
        int indice = 0;
        int[] subCadeia = new int[t.length];
        for(int k = 0; k < t.length; k++){
            if(t[k] != tOrdenada[k]){
                subCadeia[indice++] = t[k];
            }
        }
        // copiar a subCadeia para a subTabela para o caso de o length da subCadeia for menor que o length de t, não aparecerem 0's
        int[] subTabela = new int[indice];
        System.arraycopy(subCadeia, 0, subTabela, 0, indice);
        return subTabela;
    }
}
