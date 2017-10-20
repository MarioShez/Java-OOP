package fp.grados.excepciones;

public class ExcepcionPersonaNoValida extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ExcepcionPersonaNoValida(){
		
	}

	public ExcepcionPersonaNoValida(String s){
		super(s);
	}
}
