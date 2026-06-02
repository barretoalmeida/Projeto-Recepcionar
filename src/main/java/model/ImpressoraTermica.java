package model;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ImpressoraTermica {

    private static final String IP_IMPRESSORA = "192.168.0.104";
    private static final int PORTA = 9100;

    public void imprimirSenha(String senha, String cursos, String nome) {

        try (Socket socket = new Socket(IP_IMPRESSORA, PORTA);
             OutputStream out = socket.getOutputStream()) {

            String texto =
                    "\n" +
                    "CADASTRO REALIZADO!\n" +
                    "====================\n" +
                    "\n" +
                    "Meu número da sorte:"+
                    "\n" + senha + "\n" +
                    "\n" +
                    "====================\n" +
                    "" + nome + "\n" +
                    "" + cursos + "\n" +
                    "====================\n" +
                    "\n" +
                    "✨ BOA SORTE! ✨\n\n\n";

            // Inicializa impressora
            out.write(new byte[]{0x1B, 0x40});
            
            out.write(new byte[] { 0x1B, 0x61, 0x01 });

            // Envia texto
            out.write(texto.getBytes(StandardCharsets.UTF_8));

            // Avança papel
            out.write("\n\n\n".getBytes());

            // Corte automático (se suportado)
            out.write(new byte[]{0x1D, 0x56, 0x41, 0x10});

            out.flush();

            System.out.println("Cupom enviado para impressora.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*import java.io.ByteArrayInputStream;
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
}*/


