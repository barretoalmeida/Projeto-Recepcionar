package model;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class ImpressoraTermica {

    public void imprimirSenha(String senha, String cursos, String nome) {

        try {

            // TEXTO DO CUPOM
            String texto =
            		"CADASTRO REALIZADO!\n" +
            		"====================\n" +
            		"\n" +
            		"   " + senha + "\n" +
            		"\n" +
            		"====================\n" +
            		"NOME: " + nome + "\n" +
            		"TIPO: " + cursos + "\n" +
            		"====================\n" +
            		"\n" +
            		"✨ BOA SORTE ✨";
            System.out.println(texto);
            
            // TESTE
            System.out.println("Impressão enviada com sucesso!");

            // CONVERTE TEXTO PARA BYTES
            byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);

            // PROCURA IMPRESSORA PADRÃO
            PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();

            if (impressora == null) {
                System.out.println("Nenhuma impressora encontrada.");
                return;
            }

            // ENVIA PARA IMPRESSÃO
            DocPrintJob job = impressora.createPrintJob();

            Doc doc = new SimpleDoc(
                    new ByteArrayInputStream(bytes),
                    DocFlavor.INPUT_STREAM.AUTOSENSE,
                    null
            );

            job.print(doc, null);

            System.out.println("Senha impressa com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


