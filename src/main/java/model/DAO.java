package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/recepcionar?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";

	// Testando a conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int tipoCadastro(JavaBeans cadastro) {

	    int idGerado = 0;

	    String create = "insert into cadastro(tipo) values (?)";

	    try {

	        Connection con = conectar();

	        PreparedStatement pst = con.prepareStatement(
	            create,
	            PreparedStatement.RETURN_GENERATED_KEYS
	        );

	        pst.setString(1, cadastro.getTipo());

	        pst.executeUpdate();

	        ResultSet rs = pst.getGeneratedKeys();

	        if (rs.next()) {
	            idGerado = rs.getInt(1);
	        }

	        con.close();

	    } catch (Exception e) {
	        System.out.println(e);
	    }

	    return idGerado;
	}
	
	
	

	//Fazndo a parte do cadastro
	public void inserirCadastro(JavaBeans cadastro) {
		String update = "update cadastro set nome=?, data_nascimento=?, cursos=? where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, cadastro.getNome());
			pst.setDate(2, java.sql.Date.valueOf(cadastro.getData()));
			pst.setString(3, cadastro.getCursos());
			pst.setString(4, cadastro.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
