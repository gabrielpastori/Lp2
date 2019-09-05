/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nomeDaClasse = "Atleta";
        List<String> atributo = new ArrayList<>();
        List<String> codigo = new ArrayList<>();
        atributo.add("int;id");
        atributo.add("String;nome");
        atributo.add("double;altura");
        atributo.add("String;esporte");
        atributo.add("double;peso");

        codigo.add("package Main;\n"
                + "public class " + nomeDaClasse + " {");
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("private " + aux[0] + " " + aux[1] + ";");
        }

        codigo.add("\n"
                + " public " + nomeDaClasse + "() {\n"
                + "    }");

        for (int i = 0; i < codigo.size(); i++) {
            System.out.println(codigo.get(i));

        }
    }

}
