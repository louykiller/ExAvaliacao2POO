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
        if(subTabela == null){
            System.out.println("Tabela já está ordenada!");
        }
        else{
            System.out.println("Sub-Tabela:");
            ImprimeTabela(subTabela);
        }
    }

    public static void ImprimeTabela(int[] t){
        System.out.print("{");
        for(int i = 0; i < t.length; i++){
            if(i != t.length - 1){
                System.out.printf("%d, ", t[i]);
            }
            else{
                System.out.printf("%d}", t[i]);
            }
        }
        System.out.println();
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
        // Verificar a partir de quando e ate quando os numeros estão desordenados
        int inicio = 0, fim = 0;
        boolean Inicio = false;
        for(int k = 0; k < t.length; k++){
            // se o inicio ainda n tiver sido definido e o t[k] for diferente de tOrdenada[k]
            if(Inicio == false && t[k] != tOrdenada[k]){
                // colocar Inicio a true e atribuir a inicio o valor de k
                Inicio = true;
                inicio = k;
            }
            else{
                // depois de ser descoberto inicio ver até onde os numeros estão desordenados
                if(t[k] != tOrdenada[k]){
                    fim = k;
                }
            }
        }
        if(inicio == fim){
            return null;
        }
        // criar uma subtabela com length = fim-inicio+1 pois fim e inicio referem se a indices e referem se a posição do elemento na tabela e não ao numero do elemento
        int[] subTabela = new int[fim-inicio+1];
        // copiar para a subTabela a sub-cadeia do t original
        System.arraycopy(t, inicio, subTabela, 0, fim-inicio+1);
        return subTabela;
    }
}
