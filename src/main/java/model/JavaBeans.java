package model;

public class JavaBeans {
	private String id;
	private String nome;
	private String data_nascimento;
	private String tipo;
	private String cursos;
	
	public JavaBeans() {
		super();
	}
	
	public JavaBeans(String id, String nome, String data_nascimento, String tipo, String cursos) {
		super();
		this.id = id;
		this.nome = nome;
		this.data_nascimento = data_nascimento;
		this.tipo = tipo;
		this.cursos = cursos;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data_nascimento;
	}
	public void setData(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCursos() {
		return cursos;
	}
	public void setCursos(String cursos) {
		this.cursos = cursos;
	}
}
